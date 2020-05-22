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
	
	// queue put here; queue get in CtsSocketClient
	public static BlockingQueue<EiCreateTenderPayload> queueFromLme = new ArrayBlockingQueue(20);
	public static CtsSocketClient ctsSocketClient = new CtsSocketClient();
	
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
		
		tempCreate = eiCreateTender;
		tempTender = eiCreateTender.getTender();
		
		if (ctsSocketClientNotRunning) {
			ctsSocketClientNotRunning = false;
			ctsSocketClient.start();
		}

		logger.info("LmeController before constructor for EiCreatedTender " +
					  tempTender.toString());
		
		/*	ResponseBody
			public EiCreatedTender(
				TenderId tenderId,
				ActorId partyId,
				ActorId counterPartyId,
				EiResponse response)
		 */
		
		// Conversion to MarketCreateTenderPayload is in CtsSocketServer to Market
		// Non-blocking add returns true if OK, false if queue is full
		// Send to CtsBridge to place order in parity engine		
		System.err.println("Lme.postEiCreateTender queueFromLme space? " + queueFromLme.add(tempCreate) + " Length " + queueFromLme.size() + " TenderId " + tempTender.getTenderId());
		
		if (ctsSocketClientNotRunning) {
			ctsSocketClientNotRunning = false;
			ctsSocketClient.start();
		}
		
		// Response is from LME; decouple orderEntered call from market	
		tempCreated = new EiCreatedTenderPayload(tempTender.getTenderId(),
				tempCreate.getPartyId(),
				tempCreate.getCounterPartyId(),
				new EiResponse(200, "OK"));
		
		// DEBUG
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
	
	
// TODO FROM MARKET - NOW MOVED TO SOCKET SERVER
/*
	@PostMapping("/marketCreateTransaction")
	public MarketCreatedTransactionPayload 	postMarketCreateTransaction	(
			@RequestBody MarketCreateTransactionPayload marketCreateTransaction)	{
		EiTender tempTender;
		MarketCreateTransactionPayload tempCreate;
		MarketCreatedTransactionPayload tempCreated;
		
		tempCreate = marketCreateTransaction;
		// need to lookup and retrieve EiTender from TenderId in the MarketCreateTransactionPayload
		tempTender = eiCreateTender.getTender();
		// need matched quantity, original tender ID, maybe not the original tender

		logger.info("LmeController before constructor for EiCreatedTender " +
					  tempTender.toString());
		
		/*
			public EiCreatedTender(
				TenderId tenderId,
				ActorId partyId,
				ActorId counterPartyId,
				EiResponse response)
// fix nested comment
		
		tempCreated = new MarketCreatedTransactionPayload(tempTender.getTenderId(),
				tempCreate.getPartyId(),
				tempCreate.getCounterPartyId(),
				new EiResponse(200, "OK"));
		
		// DEBUG
		tempCreated.print();
		// Turn into an order and forward to the parity engine
		logger.info("Ready to forward rewritten tender as Parity order and return. EiCreatedTender " +
						tempCreated.toString());
		
		return tempCreated;
	}	
	*/
		
	// REPURPOSE TO SENDING CreateTransaction to LMA
	
	// TODO needs price and quantity match. Socket reads MarketEiCreateTransaction payload
	// for each side of the match. Note that there may be more than one match made on a tender
	
//	public static void MatchFound(EiTender tenderMatchOne,
//			EiTender tenderMatchTwo)	{
//		EiCreateTransactionPayload	eiCreateTransactionPayload = new EiCreateTransactionPayload();
//		EiCreatedTransactionPayload eiCreatedTransactionPayload, tempPostResponse;
//		final RestTemplateBuilder builder = new RestTemplateBuilder(); 
//		RestTemplate restTemplate;	// scope is function MatchFound
//		restTemplate = builder.build();
//	   	
//		//DEBUG for now
//		System.err.println("in MatchFound Matched Tender one " + 
//				tenderMatchOne.toString() +
//				" Matched Tender two " +
//				tenderMatchTwo.toString());
//
//		/* construct an EiCreateTransaction payload and send to LMA */
//		// TODO transaction, party, counterParty from Instrument, actors, orders in Parity
//		// not the autogenerated no parameter constructor
//		tempPostResponse = restTemplate.postForObject(
//				"http://localhost:8080/lma/createTransaction", 
//				eiCreateTransactionPayload, EiCreatedTransactionPayload.class);
//
//		/* and process the EiCreatedTransaction response */
//		}

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
