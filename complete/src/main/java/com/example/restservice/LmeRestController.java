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
	private static TenderIdType currentTenderId;
	// TODO assign in constructor?
	private static final ActorIdType partyId  = new ActorIdType();
	
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

		logger.info("LmeController before constructor for EiCreatedTender " +
					  tempTender.toString());
		
		/*
			public EiCreatedTender(
				TenderId tenderId,
				ActorId partyId,
				ActorId counterPartyId,
				EiResponse response)
		 */
		
		tempCreated = new EiCreatedTenderPayload(tempTender.getTenderId(),
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
	 * The glue code in the Parity Client (in CtsBridge) maps the Parity Order numbers
	 * and instrument names to the original TenderIds and Tenders
	 * 
	 * The service request is POSTed to /lme/marketCreateTransaction from the market
	 */
	
	
	
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
		 */
		
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
	
	
	
	
	
	
	
	
	
	
	
	
	public static void MatchFound(EiTender tenderMatchOne,
			EiTender tenderMatchTwo)	{

		EiCreateTransactionPayload	eiCreateTransactionPayload = new EiCreateTransactionPayload();
		EiCreatedTransactionPayload eiCreatedTransactionPayload, tempPostResponse;

		// Is class scope OK for builder?
		final RestTemplateBuilder builder = new RestTemplateBuilder(); 
		RestTemplate restTemplate;	// scope is function MatchFound
		restTemplate = builder.build();
	   	
		//DEBUG for now
		System.err.println("in MatchFound Matched Tender one " + 
				tenderMatchOne.toString() +
				" Matched Tender two " +
				tenderMatchTwo.toString());

		/* construct an EiCreateTransaction payload and send to LMA */
		// TODO transaction, party, counterParty from Instrument, actors, orders in Parity
		// not the autogenerated no parameter constructor
		tempPostResponse = restTemplate.postForObject(
				"http://localhost:8080/lma/createTransaction", 
				eiCreateTransactionPayload, EiCreatedTransactionPayload.class);

		/* and process the EiCreatedTransaction response */
		}
}
