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
	private static TenderId currentTenderId;
	// TODO assign in constructor?
	private static final ActorId partyId  = new ActorId();
	
	private static final Logger logger = LogManager.getLogger(
			LmeRestController.class);
	
	/*
	 * GET - /lme/party responds with PartyId
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
	public EiCreatedTender 	postEiCreateTender(
			@RequestBody EiCreateTender eiCreateTender)	{
		EiTender tempTender;
		EiCreateTender tempCreate;
		EiCreatedTender tempCreated;
		
		tempCreate = eiCreateTender;
		tempTender = eiCreateTender.getTender();
//		tempTender.print();	// DEBUG
		
		/*
			public EiCreatedTender(
				TenderId tenderId,
				ActorId partyId,
				ActorId counterPartyId,
				EiResponse response)
		 */
		
		tempCreated = new EiCreatedTender(tempTender.getTenderId(),
				tempCreate.getPartyId(),
				tempCreate.getCounterPartyId(),
				new EiResponse(200, "OK"));
		
		// Turn into an order and forward to the parity engine
		System.err.println(
			"Ready to forward rewritten tender to Parity as an order");
		
		return tempCreated;
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

//		tempCancel.print();	// DEBUG
		
		tempCanceled = new EICanceledTender(
				tempCancel.getPartyId(),
				tempCancel.getCounterPartyId(),
				new EiResponse(200, "OK"));
		
		return tempCanceled;
	}

	/*
	 * MatchFound is invoked when a match is found in the Parity Engine
	 * Data from Parity includes both orders rewritten to show actual
	 * clearing price and actual quantity.
	 * 
	 * The parameters come from two orders that are rewritten with the new
	 * quantity and price.
	 * 
	 * The party and counterParty remain the same and are packaged in two new
	 * EiCreateTransaction payloads and POSTed to the LMA
	 * 
	 * The glue code from Parity to/from CTS maps the Parity Order numbers
	 * and instrument names to the original TenderIds 
	 */
	public static void MatchFound(EiTender tenderMatchOne,
			EiTender tenderMatchTwo)	{
		// Is class scope OK for builder?
		final RestTemplateBuilder builder = new RestTemplateBuilder(); 
		RestTemplate restTemplate;	// scope is function MatchFound

	   	restTemplate = builder.build();
	   	
	   	//DEBUG for now
	   	System.err.println("in MatchFound Matched Tender one " + 
	   			tenderMatchOne.toString() +
	   			" Matched Tender two " +
	   			tenderMatchTwo.toString());
		}

	/*
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

	public static TenderId getCurrentTenderId() {
		return currentTenderId;
	}

	public static void setCurrentTenderId(TenderId currentTenderId) {
		LmeRestController.currentTenderId = currentTenderId;
	}

	public static AtomicLong getCounter() {
		return counter;
	}

	public static ActorId getPartyid() {
		return partyId;
	}
	*/
}
