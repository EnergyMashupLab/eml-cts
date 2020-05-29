package com.example.restservice;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;

//For RestTemplate
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/lme")
public class LmeRestController {
	private static final AtomicLong counter = new AtomicLong();
	private static EiTender currentTender;
	private static EiTransaction currentTransaction;
	private static TenderIdType currentTenderId;
	// TODO assign in constructor?
	private static final ActorIdType partyId  = new ActorIdType();
	
	private static Boolean ctsSocketClientNotRunning = true;
	private static Boolean ctsSocketServerNotRunning = true;
	
	// queueFromLme is used by CtsSocketClient to accept CreateTenderPayload
	// queue.put here in LME, take in CtsSocketClient which connects to the market
	// TODO tune queue capacity
	public static BlockingQueue<EiCreateTenderPayload> queueFromLme = new ArrayBlockingQueue(20);
	public static CtsSocketClient ctsSocketClient = new CtsSocketClient();
	
	// parallel for MarketCreateTransactionPayloads here in LME.
	// queue.put by CtsSocketServer, queue.take here in LME to produce an EiCreateTransactionPayload
	// forwarded to LMA
	public static BlockingQueue<EiCreateTransactionPayload> eiCreateTransactionQ = new ArrayBlockingQueue(20);
	public static CtsSocketServer ctsSocketServer = new CtsSocketServer();
	
	/*
	 * HashMap to correlate CTS TenderIdType and EiCreateTenderPayload
	 * referenced by the MarketCreateTransaction
	 */
	// TODO count down original quantity from Transactions against the Tender; remove when zero
	// Need wrapper class Long to use the long ctsTenderId as returned
	public static HashMap<Long, EiCreateTenderPayload> ctsTenderIdToCreateTenderMap = new HashMap<Long, EiCreateTenderPayload>();

	
	private static final Logger logger = LogManager.getLogger(
			LmeRestController.class);
	
	/*
	 * GET - /lme/party responds with PartyId
	 */
	@GetMapping("/party")
	public ActorIdType getParty() {
		return this.partyId;
	}
	
	
	/*
	 * POST - /createTender
	 * 		RequestBody is EiCreateTenderPayload from LMA
	 * 		ResponseBody is EiCreatedTenderPayload
	 */
	
	@PostMapping("/createTender")
	public EiCreatedTenderPayload 	postEiCreateTender(
			@RequestBody EiCreateTenderPayload eiCreateTender)	{
		EiTender tempTender;
		EiCreateTenderPayload tempCreate;
		EiCreatedTenderPayload tempCreated;
		Boolean addQsuccess = false;
		
		tempCreate = eiCreateTender;
		tempTender = eiCreateTender.getTender();
		
		// start market connection sockets
		if (ctsSocketClientNotRunning) {
			ctsSocketClientNotRunning = false;
			ctsSocketClient.start();
		}
		
		if (ctsSocketServerNotRunning)	{
			ctsSocketServerNotRunning = false;
			ctsSocketServer.start();
		}

//		logger.info("LmeController before constructor for EiCreatedTender " +
//		tempTender.toString());
		
		/*	ResponseBody
			public EiCreatedTender(
				TenderId tenderId,
				ActorId partyId,queueF
				EiResponse response)
		 */
		
		// Conversion to MarketCreateTenderPayload is in CtsSocketServer at Market
		// Non-blocking add returns true if OK, false if queue is full - check return value
		addQsuccess = queueFromLme.add(tempCreate);
		System.err.println("Lme.postEiCreateTender queueFromLme addQsucces " + addQsuccess +
				" Length " + queueFromLme.size() + " TenderId " + tempTender.getTenderId());
		
		// save EiCreateTenderPayload in map to construct EiCreateTransactionPayload from MarketCreateTransaction
		ctsTenderIdToCreateTenderMap.put(tempCreate.getTender().getTenderId().value(), tempCreate);	
				
		// Decouple orderEntered insertion from market
		// Immediate return without waiting for orderEntered message (pending)
		tempCreated = new EiCreatedTenderPayload(tempTender.getTenderId(),
				tempCreate.getPartyId(),
				tempCreate.getCounterPartyId(),
				new EiResponse(200, "OK"));
		
		//	System.err.println("Lme createTender: CreatedTenderPayload is " + tempCreated.toString());

		logger.info("Forward rewritten tender via socket  and return. " + tempCreated.toString());
		
		/*
		 * TODO FORWARD TO MARKET - JSON serialization
		 */
		
		return tempCreated;
	}
	
	/*
	 * POST - /cancelTender
	 * 		RequestBody is EiCancelTender from TEUA/Client by way of LMA
	 * 		ResponseBody is EiCanceledTender
	 */
	@PostMapping("/cancelTender")
	public EICanceledTenderPayload postEiCancelTenderPayload(
			@RequestBody EiCancelTenderPayload eiCancelTenderPayload)	{
		TenderIdType tempTenderId;
		EiCancelTenderPayload tempCancel;	
		EICanceledTenderPayload tempCanceled;
		
		tempCancel = eiCancelTenderPayload;
		tempTenderId = eiCancelTenderPayload.getTenderId();

//		tempCancel.print();	// DEBUG
		
		tempCanceled = new EICanceledTenderPayload(
				tempCancel.getPartyId(),
				tempCancel.getCounterPartyId(),
				new EiResponse(200, "OK"));
		
		return tempCanceled;
	}
	
	/*
	 * 	Take first EiCreateTransactionPayload from eiCreateTransactionQ and
	 * 	Post it to the LMA.
	 * 
	 * This should be a thread.
	 */
	public void setUpCreateTransaction()		{
		EiCreatedTransactionPayload tempPostResponse;
		EiCreateTransactionPayload eiCreateTransaction = null;
		
		try {
			eiCreateTransaction = eiCreateTransactionQ.take();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.err.println("Lme.setUpCreateTransaction " + eiCreateTransaction.toString());
		
		tempPostResponse = postEiCreateTransaction(eiCreateTransaction);
		
		// TODO process tempPost Response TBD
		}
	
	public EiCreatedTransactionPayload postEiCreateTransaction(@RequestBody EiCreateTransactionPayload eiCreateTransaction)	{
		// createTransactionQ.take() is the @RequestBody
		EiTransaction tempTransaction;
		EiCreatedTransactionPayload tempPostResponse;
		
		final RestTemplateBuilder builder = new RestTemplateBuilder();
		RestTemplate restTemplate;	// scope is function postEiCreateTransaction
		restTemplate = builder.build();		
		
		tempTransaction = eiCreateTransaction.getTransaction();
		logger.info("LmaController: postEiCreateTransaction: Transaction  " + tempTransaction.toString());
		/*
		 * Pass on to LMA and use POST responseBody in reply origin
		 */
		tempPostResponse = restTemplate.postForObject("http://localhost:8080/lma/createTransaction", 
				eiCreateTransaction, 
				EiCreatedTransactionPayload.class);
		
		logger.info("LMA after forward to LME and before return " + tempPostResponse.toString());
		
		/*
			tempCreated = new EiCreatedTender(tempTender.getTenderId(),
				tempCreate.getPartyId(),
				tempCreate.getCounterPartyId(),
				new EiResponse(200, "OK"));
		*/
		
		return tempPostResponse;
	}

	public static BlockingQueue<EiCreateTenderPayload> getQueueFromLme() {
		return queueFromLme;
	}

	public static void setQueueFromLme(BlockingQueue<EiCreateTenderPayload> queueFromLme) {
		LmeRestController.queueFromLme = queueFromLme;
	}


	public static EiTender getCurrentTender() {
		return currentTender;
	}


	public static void setCurrentTender(EiTender currentTender) {
		LmeRestController.currentTender = currentTender;
	}


	public static EiTransaction getCurrentTransaction() {
		return currentTransaction;
	}


	public static void setCurrentTransaction(EiTransaction currentTransaction) {
		LmeRestController.currentTransaction = currentTransaction;
	}


	public static TenderIdType getCurrentTenderId() {
		return currentTenderId;
	}


	public static void setCurrentTenderId(TenderIdType currentTenderId) {
		LmeRestController.currentTenderId = currentTenderId;
	}


	public static Boolean getCtsSocketClientNotRunning() {
		return ctsSocketClientNotRunning;
	}


	public static void setCtsSocketClientNotRunning(Boolean ctsSocketClientNotRunning) {
		LmeRestController.ctsSocketClientNotRunning = ctsSocketClientNotRunning;
	}


	public static CtsSocketClient getCtsSocketClient() {
		return ctsSocketClient;
	}


	public static void setCtsSocketClient(CtsSocketClient ctsSocketClient) {
		LmeRestController.ctsSocketClient = ctsSocketClient;
	}


	public static AtomicLong getCounter() {
		return counter;
	}


	public static ActorIdType getPartyid() {
		return partyId;
	}


	public static Logger getLogger() {
		return logger;
	}
	
	
	
	
}
