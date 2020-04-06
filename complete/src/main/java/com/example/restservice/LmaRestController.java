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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// For RestTemplate
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/lma")
public class LmaRestController {
	private static final AtomicLong counter = new AtomicLong();
	private static EiTender currentTender;
	private static EiTransaction currentTransaction;
	private static TenderId currentTenderId;
	// TODO assign in constructor?
	private static final ActorId partyId  = new ActorId();
	
	private static final Logger logger = LogManager.getLogger(
			LmaRestController.class);
	
	
	/*
	 * GET - /lma/party responds with PartyId
	 */
	@GetMapping("/party")
	public ActorId getParty() {
		return this.partyId;
	}
	
	/*
	 * POST - /createTender - POSTed by TEUA/EMA to LMA
	 *				Forwarded to LME
	 * 		RequestBody is EiCreateTender
	 * 		ResponseBody is EiCreatedTender
	 */

	@PostMapping("/createTender")
	public EiCreatedTender 	postEiCreateTender(
			@RequestBody EiCreateTender eiCreateTender)	{

		EiTender tempTender;
		EiCreateTender tempCreate;
		EiCreatedTender tempCreated;
		// Will pass on eiCreateTender body to LME and return its response tempPostResponse
		EiCreatedTender tempPostResponse; 

		// Is class scope OK for builder?
		final RestTemplateBuilder builder = new RestTemplateBuilder();
		RestTemplate restTemplate;	// scope is function postEiCreateTender
		
		logger.info("LmaController before builder for /createTender");
    		restTemplate = builder.build();
		logger.info("LmaController after builder for /createTender and before tempCreate save");
		// save CreateTender message as sent by TEUA
		tempCreate = eiCreateTender;	
		logger.info("LmaController before extracting tender");
		tempTender = tempCreate.getTender(); // and pull out Tender
		logger.info("LmaController after extracting tender " + tempTender.toString());
		
		logger.info("LmaController after printing tender before forward to LME--");
		/*
		 * Pass on to LME and use POST responseBody in reply to origin
		 */
		tempPostResponse = restTemplate.postForObject("http://localhost:8080/lme/createTender", 
				tempCreate, 
				EiCreatedTender.class);
		
		logger.info("LMA after forward to LME and before return " + tempPostResponse.toString());
		
		/*
		tempCreated = new EiCreatedTender(tempTender.getTenderId(),
				tempCreate.getPartyId(),
				tempCreate.getCounterPartyId(),
				new EiResponse(200, "OK"));
		*/
		
		return tempPostResponse;
	}
	
	/*
	 * POST - /createTransaction - comes from LME based on market matches
	 * 		RequestBody is EiCreateTransaction
	 * 		ResponseBody is EiCreatedTransaction
	 */
	
	@PostMapping("/createTransaction")
	public EiCreatedTransaction postEiCreateTransaction(
			@RequestBody EiCreateTransaction eiCreateTransaction)	{

		EiTender tempTender;
		EiTransaction tempTransaction;
		EiCreateTransaction tempCreate;
		EiCreatedTransaction tempCreated, tempPostResponse;
		
		// Is class scope OK for builder?
		final RestTemplateBuilder builder = new RestTemplateBuilder();
		RestTemplate restTemplate;	// scope is function postEiCreateTender
		restTemplate = builder.build();
	   	
		/*
		 * Originated by LME and forwarded by LMA to TEUA based on market match
		 * NOTE synchronous, uses TEUA EiCreatedTransaction back to LME
		 */
		
		tempCreate = eiCreateTransaction;
		tempTransaction = eiCreateTransaction.getTransaction();
		tempTender = tempCreate.getTransaction().getTender();
		tempTender.print();	// DEBUG
		
		/*
		 * Send on to requesting TEUA/EMA
		 */
				
		/* 
		 * Return the EiCreatedTransaction payload received from the TEUA
		*/
		logger.info("LmaController before sending EiCreateTransaction to TEUA");
		/*
		 * Pass the EiCreateTransaction payload to the TEUA
		 * NOTE with dynamic URIs will require party-Id lookup
		 */
		tempPostResponse = restTemplate.postForObject("http://localhost:8080/teua/createTransaction", 
				tempCreate,
				EiCreatedTransaction.class);
		logger.info("LmaController after EiCreatedTransaction response from teua to EiCreateTender");
				
		// And send the EiCreatedTransaction from the TEUA to the LME
		return tempPostResponse;
	}
	
	
	/*
	 * POST - /cancelTender
	 * 		RequestBody is EiCancelTender
	 * 		ResponseBody is EiCanceledTender
	 */

	@PostMapping("/cancelTender")
	public EICanceledTender postEiCancelTender(
			@RequestBody EiCancelTender eiCancelTender)	{
		TenderId tempTenderId;
		EiCancelTender tempCancel;	
		EICanceledTender tempCanceled, tempPostResponse;

		// Is class scope OK for builder?
		final RestTemplateBuilder builder = new RestTemplateBuilder();
		RestTemplate restTemplate;	// scope is function postEiCreateTender

		logger.info("LmaController before builder for /cancelTender");
		restTemplate = builder.build();
		logger.info("LmaController after builder for /createTender and before tempCreate save");

		// save CancelTender message as sent by TEUA
		logger.info("LmaController before forward to LME");
		tempCancel = eiCancelTender;
		
		/*
		tempCanceled = new EICanceledTender(
				tempCancel.getPartyId(),
				tempCancel.getCounterPartyId(),
				new EiResponse(200, "OK"));
		*/

		logger.info("LmaController before forward to LME");
		/*
		 * Pass on to LME and use POST responseBody EiCanceledTender in reply to origin
		 */
		tempPostResponse = restTemplate.postForObject("http://localhost:8080/lme/cancelTender",			
				tempCancel, 		
				EICanceledTender.class);
		
		logger.info("LMA after forward to LME and before return " + tempPostResponse.toString());
		
		return tempPostResponse;
	}

	public static EiTender getCurrentTender() {
		return currentTender;
	}

	public static void setCurrentTender(EiTender currentTender) {
		LmaRestController.currentTender = currentTender;
	}

	public static EiTransaction getCurrentTransaction() {
		return currentTransaction;
	}

	public static void setCurrentTransaction(EiTransaction currentTransaction) {
		LmaRestController.currentTransaction = currentTransaction;
	}

	public static TenderId getCurrentTenderId() {
		return currentTenderId;
	}

	public static void setCurrentTenderId(TenderId currentTenderId) {
		LmaRestController.currentTenderId = currentTenderId;
	}

	public static AtomicLong getCounter() {
		return counter;
	}

	public static ActorId getPartyid() {
		return partyId;
	}
	
	
}
