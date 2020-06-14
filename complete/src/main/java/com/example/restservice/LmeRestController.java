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
	
	private static Boolean lmeSocketClientNotRunning = true;
	private static Boolean lmeSocketServerNotRunning = true;
	
	// queueFromLme is used by LmeSocketClient to accept CreateTenderPayload
	// queue.put here in LME, take in LmeSocketClient which connects to the market
	// TODO tune queue capacity
	public static BlockingQueue<EiCreateTenderPayload> queueFromLme = new ArrayBlockingQueue(20);
	public static LmeSocketClient lmeSocketClient = new LmeSocketClient();
	
	// parallel for MarketCreateTransactionPayloads here in LME.
	// queue.put by LmeSocketServer, queue.take here in LME to produce an 
	// EiCreateTransactionPayload to be forwarded to LMA
	public static BlockingQueue<EiCreateTransactionPayload> eiCreateTransactionQ =
			new ArrayBlockingQueue(20);
	public static LmeSocketServer lmeSocketServer = new LmeSocketServer();
	
	LmeSendTransactions lmeSendTransactionsThread = new LmeSendTransactions();
	
	/*
	 * HashMap to correlate CTS TenderIdType and EiCreateTenderPayload
	 * referenced by the MarketCreateTransaction
	 */
	// TODO count down original quantity from Transactions against the Tender; remove when zero
	// Need wrapper class Long to use the long ctsTenderId as returned
	public static HashMap<Long, EiCreateTenderPayload> ctsTenderIdToCreateTenderMap =
			new HashMap<Long, EiCreateTenderPayload>();

	
	private static final Logger logger = LogManager.getLogger(
			LmeRestController.class);
	
	LmeRestController()	{
//		logger.info("LmeRestController zero arg constructor. partyId " + partyId);
		
		//	Start thread to read createTransactionQ and send
		lmeSendTransactionsThread.start();
		logger.debug("Starting lmeThread");
		
		
		// start market connection sockets
		if (lmeSocketClientNotRunning) {
			lmeSocketClientNotRunning = false;
			lmeSocketClient.start();
			logger.debug("Starting lmeSocketClient");
			
		}
		
		if (lmeSocketServerNotRunning)	{
			lmeSocketServerNotRunning = false;
			lmeSocketServer.start();
			logger.debug("Starting lmeSocketServer");
		}
	}
	
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
		EiCreateTenderPayload tempCreate = null;
		EiCreateTenderPayload mapPutReturnValue = null;
		EiCreatedTenderPayload tempCreated;
		Boolean addQsuccess = false;
		
		tempCreate = eiCreateTender;
		tempTender = eiCreateTender.getTender();

//		logger.debug("LmeController before constructor for EiCreatedTender " +
//		tempTender.toString());
		logger.info("lme/createTender LME partyId " + partyId);
		
		/*	ResponseBody
			public EiCreatedTender(
				TenderId tenderId,
				ActorId partyId,queueF
				EiResponse response)
		 */
		
		// Forward to market
		// Conversion to MarketCreateTenderPayload is in LmeSocketClient here
		// TODO Non-blocking add returns true if OK, false if queue is full
		
		// TODO switch to blocking after verification
		addQsuccess = queueFromLme.add(tempCreate);
		logger.debug("queueFomLme addQsuccess " + addQsuccess +
				" TenderId " + tempTender.getTenderId());
		
		// put EiCreateTenderPayload in map to build EiCreateTransactionPayload
		// from MarketCreateTransaction
		mapPutReturnValue = ctsTenderIdToCreateTenderMap.put(tempCreate.getTender().getTenderId().value(),
				tempCreate);	
		
		if (mapPutReturnValue == null) {
			logger.debug("mapPutReturnValue is null - new entry");
		}	else	{
			logger.debug("mapPutReturnValue non-null - previous entry " + mapPutReturnValue.toString());
		}
		
		// Decouple orderEntered insertion from market with immediate return to LMA
		//	TODO consider return value if value already in map
		tempCreated = new EiCreatedTenderPayload(tempTender.getTenderId(),
				tempCreate.getPartyId(),
				tempCreate.getCounterPartyId(),
				new EiResponse(200, "OK"));
		
		return tempCreated;
	}
	
	
	/*
	 *	POST EiCreateTransaction to LMA
	 *
	 *	is in LmeSendTransaction
	 */
	
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


	public static Boolean getLmeSocketClientNotRunning() {
		return lmeSocketClientNotRunning;
	}


	public static void setLmeSocketClientNotRunning(Boolean lmeSocketClientNotRunning) {
		LmeRestController.lmeSocketClientNotRunning = lmeSocketClientNotRunning;
	}


	public static LmeSocketClient getLmeSocketClient() {
		return lmeSocketClient;
	}


	public static void setLmeSocketClient(LmeSocketClient lmeSocketClient) {
		LmeRestController.lmeSocketClient = lmeSocketClient;
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
