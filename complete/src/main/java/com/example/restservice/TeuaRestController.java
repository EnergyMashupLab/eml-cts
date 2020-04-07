package com.example.restservice;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//For RestTemplate
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/*
 * NEXT STEPS: incorporate dynamic URIs for the TEUAs, of the form
 *  /teua/{number}/party, etc.
 */
@RestController
@RequestMapping("/teua")	// use dynamic URIs - this supports one
public class TeuaRestController {
	private static final AtomicLong counter = new AtomicLong();
	private static EiTender currentTender;
	private static EiTransaction currentTransaction;
	private static TenderId currentTenderId;
	// TODO assign in constructor?
	private final ActorId partyId  = new ActorId();
	private static final Logger logger = LogManager.getLogger(
			TeuaRestController.class);
	
	/*
	 * GET - /teua/{#}/party responds with PartyId
	 */
	@GetMapping("/party")
	public ActorId getParty() {
		return this.partyId;
	}

	/*
	 * POST - /createTender
	 * 		RequestBody is EiCreateTender
	 * 		ResponseBody is EiCreatedTender
	 */
	
	@PostMapping("/createTender")
	public EiCreatedTender 	postEiCreateTender(@RequestBody EiCreateTender eiCreateTender)	{
		EiTender tempTender;
		EiCreateTender tempCreate;
		EiCreatedTender tempCreated;
		
		tempCreate = eiCreateTender;

		tempTender = eiCreateTender.getTender();
		logger.info("TeuaController before response from SC POST of EiCreateTender");
		
		/*
			public EiCreatedTender(
				TenderId tenderId,
				ActorId partyId,
				ActorId counterPartyId,
				EiResponse response)
		 */
		
		/*
		 * TODO forward eiCreateTender to LMA, respond to SC request
		 */
		
		tempCreated = new EiCreatedTender(tempTender.getTenderId(),
				tempCreate.getPartyId(),
				tempCreate.getCounterPartyId(),
				new EiResponse(200, "OK"));
		
		return tempCreated;
	}
	
	/*
	 * POST - /createTransaction
	 * 		RequestBody is EiCreateTransaction
	 * 		ResponseBody is EiCreatedTransaction
	 */
	
	@PostMapping("/createTransaction")
	public EiCreatedTransaction postEiCreateTransaction(
			@RequestBody EiCreateTransaction eiCreateTransaction)	{
		EiTender tempTender;
		EiTransaction tempTransaction;
		// tempPostReponse responds to POST to /sc
		EiCreateTransaction tempCreate;
		EiCreatedTransaction tempCreated, tempPostResponse;
		
		// Is class scope OK for builder?
		final RestTemplateBuilder builder = new RestTemplateBuilder();
		// scope is function postEiCreateTender
		RestTemplate restTemplate;	
	   	restTemplate = builder.build();
		
		tempCreate = eiCreateTransaction;
		tempTransaction = eiCreateTransaction.getTransaction();
		tempTender = tempCreate.getTransaction().getTender();
		logger.info("TeuaController before forward of EiCreateTransaction to SC");
		
		/*
		 * TODO 
		 * 		define simplified createTransaction and createdTender for SC-TEUA
		 * 
		 * WILL BE TO /sc/createTransaction
		 * 
		 * tempPostResponse = restTemplate.postForObject(
				"http://sc/createTransaction", 
				tempCreate,
				EiCreatedTransaction.class);
		 */
				
		/*
		 *	Will POST EiCreateTransaction back to the SC at /sc 
		 *	Shouldn't require that SC reply with an
		 *	EiCreatedTransaction but something that serves as an ACK
		 */
		
		/*
		 * TODO
		 * Build createdTransaction payload from response from SC
		 * In the alternative, use constructor without ACK from SC
		 */
		tempCreated = new EiCreatedTransaction(
				tempTransaction.getTransactionId(),
				tempCreate.getPartyId(),
				tempCreate.getCounterPartyId(),
				new EiResponse(200, "OK"));
		
		return tempCreated;
	}
	
	
	/*
	 * POST - /cancelTender sent to LMA
	 * 		RequestBody is EiCancelTender
	 * 		ResponseBody is EiCanceledTender
	 */
	
	@PostMapping("/cancelTender")
	public EICanceledTender postEiCancelTender(@RequestBody EiCancelTender eiCancelTender)	{
		TenderId tempTenderId;
		EiCancelTender tempCancel;	
		EICanceledTender tempCanceled;
		
		tempCancel = eiCancelTender;
		tempTenderId = eiCancelTender.getTenderId();

		
		
		tempCanceled = new EICanceledTender(
				tempCancel.getPartyId(),
				tempCancel.getCounterPartyId(),
				new EiResponse(200, "OK"));
		
		return tempCanceled;
	}
/*

	public static EiTender getCurrentTender() {
		return currentTender;
	}

	public static void setCurrentTender(EiTender currentTender) {
		TeuaRestController.currentTender = currentTender;
	}

	public static EiTransaction getCurrentTransaction() {
		return currentTransaction;
	}

	public static void setCurrentTransaction(EiTransaction currentTransaction) {
		TeuaRestController.currentTransaction = currentTransaction;
	}

	public static TenderId getCurrentTenderId() {
		return currentTenderId;
	}

	public static void setCurrentTenderId(TenderId currentTenderId) {
		TeuaRestController.currentTenderId = currentTenderId;
	}

	public static AtomicLong getCounter() {
		return counter;
	}

	public ActorId getPartyId() {
		return partyId;
	}
*/
}
