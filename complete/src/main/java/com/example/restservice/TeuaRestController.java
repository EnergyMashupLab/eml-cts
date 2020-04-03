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
	
	/*
	 * GET - /teua/{#}/party responds with PartyId
	 */
	@GetMapping("/party")
	public ActorId getParty() {
		return this.partyId;
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
		tempTender.print();	// DEBUG
		
		tempPostResponse = restTemplate.postForObject(
				"http://sc/createTender", 
				tempCreate,
				EiCreatedTransaction.class);
		
		/*
			public EiCreatedTender(
				TenderId tenderId,
				ActorId partyId,
				ActorId counterPartyId,
				EiResponse response)
		 */
		
		/*
		 *	Will POST EiCreateTransaction back to the SC at /sc 
		 *	Shouldn't require that SC reply with an
		 *	EiCreatedTransaction but that serves as an ACK
		 */
		
		tempCreated = new EiCreatedTransaction(
				tempTransaction.getTransactionId(),
				tempCreate.getPartyId(),
				tempCreate.getCounterPartyId(),
				new EiResponse(200, "OK"));
		
		return tempCreated;
	}	
}
