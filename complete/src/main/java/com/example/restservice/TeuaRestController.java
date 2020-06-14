package com.example.restservice;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

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
//	private static final AtomicLong counter = new AtomicLong();
//	private static EiTender currentTender;
//	private static EiTransaction currentTransaction;
//	private static TenderIdType currentTenderId;
	// For /teua/operations without an {id} PathVariable
	// Array for PathVariable values set from constructor
	private final ActorIdType partyId  = new ActorIdType();
//	private ActorIdType marketPartyId;
	private ActorIdType lmePartyId = null;

	private static final Logger logger = LogManager.getLogger(
			TeuaRestController.class);
	
    /*
	 *	Two arrays use teua/{id}/... and matching client/{id} to give the
	 *	Actor ID and the URI string to which to post.postLmaToTeuaByPartyId
	 *	will be copied to the LMA so the LMA knows where to post an 
	 *	EiCreateTransactionPayload directly
	 *	TODO verify on use that the {id} string is convertable to int
	 */
	private ActorIdType[] actorIds; 	    // ActorIds for each client/{id}
	String[] postClientCreateTransactionUri;	// URI to post to client[i]
	private String[] postUriClientTransaction;
	public static ConcurrentHashMap<ActorIdType, String> postLmaToTeuaPartyIdMap;
    
    // for managing client/{id} and teua/{id}
    public final int DEFAULT_COUNT = 20;
    public final int MAX_COUNT = 2000;
    private int idLimit;
    private String idString = null;
    private int myWorkingId = 0;

	// for randomized quantity and price for testing
	final static Random rand = new Random();	
	
	// Constructor for class TeuaRestController - zero parameters
	public TeuaRestController()	{
		this.idLimit = DEFAULT_COUNT;
		logger.info("Built TeuaRestController zero param constructor idLimit " +
					this.idLimit);
		initMapArray(idLimit);
	}
	
	// Constructor for class ClientRestController - zero parameters
	public TeuaRestController(int howMany)	{
		if (howMany > MAX_COUNT)	{
			logger.info("Constructor one parameter howMany " + howMany +
					" > " + MAX_COUNT + " set to " + DEFAULT_COUNT);
			this.idLimit = DEFAULT_COUNT;
		}	else {
			idLimit = howMany;
		}	
		initMapArray(idLimit);
	}
	
	// called by both constructors
	public void initMapArray(int howMany)	{
		int i;    
		String clientUri, teuaUri;

	    //	HOOK for SC/Client IP address/port - default is localhost
	    String clientUriPrefix = "http://localhost:8080/client/";
	    String clientUriSuffix = "/clientCreateTransaction";
	    String teuaUriPrefix = "http://localhost:8080/teua/";
	    String teuaUriSuffix = "/createTransaction";
	
		// Initialize global parallel arrays
	    actorIds = new ActorIdType[idLimit];
		postClientCreateTransactionUri = new String[this.idLimit];
		//	TODO verify behavior
		postLmaToTeuaPartyIdMap = new ConcurrentHashMap<ActorIdType, String>(idLimit);

		for (i = 0; i < this.idLimit; i++)	{
			clientUri = clientUriPrefix + String.valueOf(i) + clientUriSuffix;
			postClientCreateTransactionUri[i] = clientUri;
			actorIds[i] = new ActorIdType();
			teuaUri = teuaUriPrefix + String.valueOf(i) + teuaUriSuffix;
			postLmaToTeuaPartyIdMap.put(actorIds[i], teuaUri);
			logger.info("i = " + i + " " + clientUri + " " + teuaUri +
					" actorId " + actorIds[i].toString());
		}
		
		logger.info("partyId in actorIds[0] " + actorIds[0].toString());
		logger.info("Push setPostLmaToTeuaPartyIdMap to LMA");
		//	push map to LMA
		LmaRestController.setPostLmaToTeuaPartyIdMap(postLmaToTeuaPartyIdMap);
		
		
		// dump TeuaRestController.postLmaToTeuaPartyIdMap
		for (Map.Entry<ActorIdType, String> entry : postLmaToTeuaPartyIdMap.entrySet())	{
			ActorIdType key = entry.getKey();
			Object value = entry.getValue();
			logger.info("postLmaToTeuaPartyIdMap " + key.toString() + " " + value.toString());
		}
	}
	
	/*
	 * GET - /teua/{#}/party responds with PartyId json
	 */
	@GetMapping("/party")
	public ActorIdType getParty() {
		return this.partyId;
	}
	
	
	/*
	 * POST - /createTransaction
	 * 		RequestBody is EiCreateTransaction
	 * 		ResponseBody is EiCreatedTransaction
	 */
	
	@PostMapping("{teuaId}/createTransaction")
	public EiCreatedTransactionPayload postEiCreateTransactionPayload(
			@PathVariable String teuaId,
			@RequestBody EiCreateTransactionPayload eiCreateTransactionPayload
			)	{
		EiTender tempTender;
		EiTransaction tempTransaction;
		// tempPostReponse responds to POST to /sc
		EiCreateTransactionPayload tempCreate;
		EiCreatedTransactionPayload tempCreated, tempPostResponse;
		ClientCreatedTransactionPayload clientCreated;
		ClientCreateTransactionPayload	clientCreate;
		Integer numericTeuaId = -1;
		
		// Is class scope OK for builder?
		final RestTemplateBuilder builder = new RestTemplateBuilder();
		// scope is function postEiCreateTransactionPayload
		RestTemplate restTemplate;	
	   	restTemplate = builder.build();
		
		tempCreate = eiCreateTransactionPayload;
		tempTransaction = eiCreateTransactionPayload.getTransaction();
		tempTender = tempCreate.getTransaction().getTender();
//		logger.debug("TEUA received createTransaction " +
//				tempCreate.toString() + " before forward to Client " 
//				);
		
		/*
		 * Build ClientCreateTransactionPayload to POST to client with same id
		 */	
		clientCreate = new ClientCreateTransactionPayload(
				/* side 	*/	tempTender.getSide(),
				/* quantity	*/	tempTender.getQuantity(),
				/* price	*/	tempTender.getPrice(),
				/* tenderId	*/	tempTender.getTenderId().value()
				);
		
//		logger.info("Built ClientCreateTransactionPayload " +
//				clientCreate.toString());

		numericTeuaId = Integer.valueOf(teuaId);
		logger.info(" Forwarding ClientCreateTransaction to " +
				postClientCreateTransactionUri[numericTeuaId]);
		
		clientCreated = restTemplate.postForObject(
				postClientCreateTransactionUri[numericTeuaId],
					clientCreate,
					ClientCreatedTransactionPayload.class);
		
		//	Log return value (success true or false)
		logger.debug("TEUA return from client/SC " + clientCreated.getSuccess());
		
		// 	Return EiCreatedTransactionPayload to sender
		//	NOTE responds before response from Client/SC
		//	TODO change EiResponse code if clientCreated indicates failure
		tempCreated = new EiCreatedTransactionPayload(
				tempTransaction.getTransactionId(),
				tempCreate.getPartyId(),
				tempCreate.getCounterPartyId(),
				new EiResponse(200, "OK"));
		
//		logger.info("tempCreated constructed before return " + tempCreated.toString());
		
		return tempCreated;
	}
	
	
	/*
	 * POST - /cancelTender sent to LMA, received from Client/SC
	 * 		RequestBody is EiCancelTender
	 * 		ResponseBody is EiCanceledTender
	 */
	
	@PostMapping("/cancelTender")
	public EICanceledTenderPayload postEiCancelTender(
				@RequestBody EiCancelTenderPayload eiCancelTender)	{
		TenderIdType tempTenderId;
		EiCancelTenderPayload tempCancel;	
		EICanceledTenderPayload tempCanceled;
		
		tempCancel = eiCancelTender;
		tempTenderId = eiCancelTender.getTenderId();

		
		tempCanceled = new EICanceledTenderPayload(
				tempCancel.getPartyId(),
				tempCancel.getCounterPartyId(),
				new EiResponse(200, "OK"));
		
		return tempCanceled;
	}

	
	/*
	 * POST - /clientCreateTender processed and sent to LMA,
	 * received from Client/SC
	 * 		RequestBody is ClientCreateTenderPayload
	 * 		ResponseBody is ClientCreatedTenderPayload
	 * 
	 * Forward EiCreateTenderPayload to LMA
	 */	
	@PostMapping("{teuaId}/clientCreateTender")
	public ClientCreatedTenderPayload postEiCreateTender(
			@PathVariable String teuaId,
			@RequestBody ClientCreateTenderPayload clientCreateTender)	{
		TenderIdType tempTenderId;
		ClientCreateTenderPayload tempCreate;	
		ClientCreatedTenderPayload tempCreated, tempReturn;
		EiTender tender;
		EiCreateTenderPayload eiCreateTender;
		EiCreatedTenderPayload lmaCreatedTender;		
		Integer numericTeuaId = -1;
		
		final RestTemplateBuilder builder = new RestTemplateBuilder();
		// scope is function postEiCreateTender
		RestTemplate restTemplate = builder.build();
				
		if (lmePartyId == null)	{
			// builder = new RestTemplateBuilder();
			restTemplate = builder.build();
			lmePartyId = restTemplate.getForObject(
					"http://localhost:8080/lme/party",
					ActorIdType.class);
		}
			
		numericTeuaId = Integer.valueOf(teuaId);
		logger.info("postEiCreateTender teuaId " + teuaId +
					" actorIds[teuaId] " + actorIds[numericTeuaId].toString());
		
		tempCreate = clientCreateTender;	// save the parameter

		/*
		 * Create a new EiTender using the interval, quantity, price,and expiration
		 * time  sent by the Client/SC, and insert it via the constructor in a
		 * new EiCreateTenderPayload.
		 * 
		 * partyId is in actorIds[] , counterPartyId is the LME representing
		 * the market and the POST is to the LMA..
		 */
		tender = new EiTender(
				tempCreate.getInterval(), tempCreate.getQuantity(),
				tempCreate.getPrice(), tempCreate.getBridgeExpireTime().asInstant(),
				tempCreate.getSide());
		
		// 	Construct the EiCreateTender payload to be forwarded to LMA
		eiCreateTender = new EiCreateTenderPayload(tender, actorIds[numericTeuaId],
				this.lmePartyId);
//			System.err.println("clientCreateTender: eiCreateTender is " +
//					eiCreateTender.toString());	
		
		logger.info("TEUA sending EiCreateTender to LMA. " +
				eiCreateTender.toString());
		
		
		//	And forward to the LMA
		restTemplate = builder.build();
		EiCreatedTenderPayload result = restTemplate.postForObject
			("http://localhost:8080/lma/createTender", eiCreateTender,
					EiCreatedTenderPayload.class);
		
		// and select CTS TenderId and put in ClientCreatedTenderPayload
		
		tempReturn = new ClientCreatedTenderPayload(result.getTenderId().value());
//		logger.debug("TEUA before return ClientCreatedTender to Client/SC " +
//				tempReturn.toString());
		
		return tempReturn;
	}
	
	
}
