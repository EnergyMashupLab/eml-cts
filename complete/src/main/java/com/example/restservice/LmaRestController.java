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

<<<<<<< HEAD
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// For RestTemplate
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
=======
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
>>>>>>> 8655af1048267b3bcadd1666f9f7b648d0dd74e5

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
	 * 		RequestBody is EiCreateTender
	 * 		ResponseBody is EiCreatedTender
	 */
	
<<<<<<< HEAD
	@PostMapping("/createTender")
	public EiCreatedTender 	postEiCreateTender(
			@RequestBody EiCreateTender eiCreateTender)	{
=======
	@PostMapping(path="/createTender", consumes = "application/json", produces = "application/json")
	public EiCreatedTender 	postEiCreateTender(@RequestBody EiCreateTender eiCreateTender)	{
>>>>>>> 8655af1048267b3bcadd1666f9f7b648d0dd74e5
		EiTender tempTender;
		EiCreateTender tempCreate;
		EiCreatedTender tempCreated;
		// for LME response to be passed along EiCreateTender
		EiCreatedTender tempPostResponse; 

<<<<<<< HEAD
		// Is class scope OK for builder?
		final RestTemplateBuilder builder = new RestTemplateBuilder();
		RestTemplate restTemplate;	// scope is function postEiCreateTender
		
		logger.info("LmaController before builder for /createTender");
    	restTemplate = builder.build();
		logger.info("LmaController after builder for /createTender and before tempCreate save");
    	// save CreateTender message as sent by TEUA
		tempCreate = eiCreateTender;	
		logger.info("LmaController before extracting tender");
		tempTender = eiCreateTender.getTender(); // and pull out Tender
		logger.info("LmaController after extracting tender");
		tempTender.print();	// DEBUG
		logger.info("LmaController after printing tender");
=======
		tempTender = eiCreateTender.getTender();
//		System.err.println("in postEiCreateTender body");
//		tempTender.print();	
>>>>>>> 8655af1048267b3bcadd1666f9f7b648d0dd74e5
		
		/*
		 * Pass on to LME and use response to reply to origin
		 */
		tempPostResponse = restTemplate.postForObject("http://lme/createTender", 
				tempCreate, 
				EiCreatedTender.class);
		
		System.err.println("LMA after forward to LME " +
					tempPostResponse.toString());
		
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
	
<<<<<<< HEAD
	@PostMapping("/createTransaction")
	public EiCreatedTransaction postEiCreateTransaction(
			@RequestBody EiCreateTransaction eiCreateTransaction)	{
=======
	@PostMapping(path="/createTransaction", consumes = "application/json", produces = "application/json")
	public EiCreatedTransaction 	postEiCreateTransaction(@RequestBody EiCreateTransaction eiCreateTransaction)	{
>>>>>>> 8655af1048267b3bcadd1666f9f7b648d0dd74e5
		EiTender tempTender;
		EiTransaction tempTransaction;
		EiCreateTransaction tempCreate;
		EiCreatedTransaction tempCreated, tempPostResponse;
		
		// Is class scope OK for builder?
		final RestTemplateBuilder builder = new RestTemplateBuilder();
		RestTemplate restTemplate;	// scope is function postEiCreateTender
		
	   	restTemplate = builder.build();
	   	
		/*
		 * Sent by LME based on market match
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
			public EiCreatedTender(
				TenderId tenderId,
				ActorId partyId,
				ActorId counterPartyId,
				EiResponse response)
		 */
<<<<<<< HEAD
		
		/* Return the EiCreatedTransaction payload received from the TEUA

=======
//		System.err.println("in LMA createTransaction before new return object");
>>>>>>> 8655af1048267b3bcadd1666f9f7b648d0dd74e5
		tempCreated = new EiCreatedTransaction(tempTransaction.getTransactionId(),
				tempCreate.getPartyId(),
				tempCreate.getCounterPartyId(),
				new EiResponse(200, "OK"));
		*/
		
		/*
		 * Now send the same EiCreateTender payload to the requesting TEUA
		 * NOTE with dynamic URIs will require party-Id lookup
		 */
		tempPostResponse = restTemplate.postForObject("http://teua/createTender", 
				tempCreate,
				EiCreatedTransaction.class);
		
		System.err.println("LMA after forward to TEUA " +
					tempPostResponse.toString());
		
		// And send the response from the TEUA back to the LME
		// NOTE that this is synchronous; consider async responses
		
<<<<<<< HEAD
		return tempPostResponse;
=======
//		System.err.println("in LMA createTransaction after new return object");
		return tempCreated;
>>>>>>> 8655af1048267b3bcadd1666f9f7b648d0dd74e5
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
		EICanceledTender tempCanceled;
		
		tempCancel = eiCancelTender;
		tempTenderId = eiCancelTender.getTenderId();

		tempCancel.print();	// DEBUG
		
		tempCanceled = new EICanceledTender(
				tempCancel.getPartyId(),
				tempCancel.getCounterPartyId(),
				new EiResponse(200, "OK"));
		
		return tempCanceled;
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