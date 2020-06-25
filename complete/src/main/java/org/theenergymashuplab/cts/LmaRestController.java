package org.theenergymashuplab.cts;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// For RestTemplate
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.theenergymashuplab.cts.controller.payloads.PositionGetPayload;
import org.theenergymashuplab.cts.controller.payloads.PositionAddPayload;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/lma")
public class LmaRestController {
	private static final AtomicLong counter = new AtomicLong();
	private static EiTender currentTender;
	private static EiTransaction currentTransaction;
	private static TenderIdType currentTenderId;
	// TODO assign in constructor?
	private static final ActorIdType partyId  = new ActorIdType();
	private static boolean dumpMap = true;
	private static String tempTeuaUri = "http://localhost:8080/teua/1/createTransaction";
	
	// 	partyId to URI for posting EiCreateTransaction to /teua/{id}
	//	pushed here by TEUA which has the ActorId and {id} information
	public static ConcurrentHashMap<Long, String> postLmaToTeuaPartyIdMap;
	//	Initialized in TeuaRestController as this static map
	
	private static final Logger logger = LogManager.getLogger(
			LmaRestController.class);
	
	public void LmaRestController()	{	// zero parameter constructor
		logger.trace("LMA zero parameter constructor");
	}
	
	/*
	 * GET - /lma/party responds with PartyId
	 */
	@GetMapping("/party")
	public ActorIdType getParty() {
		return this.partyId;
	}
	
	/*
	 * POST - /createTender - POSTed by TEUA/EMA to LMA
	 *				Forwarded to LME
	 * 		RequestBody is EiCreateTender
	 * 		ResponseBody is EiCreatedTender
	 */
	@PostMapping("/createTender")
	public EiCreatedTenderPayload 	postEiCreateTender(
			@RequestBody EiCreateTenderPayload eiCreateTender)	{

		EiTender tempTender;
		EiCreateTenderPayload tempCreate;
		EiCreatedTenderPayload tempCreated;
		// Will pass on eiCreateTender body to LME and return its response tempPostResponse
		EiCreatedTenderPayload tempPostResponse; 

		// Is class scope OK for builder?
		final RestTemplateBuilder builder = new RestTemplateBuilder();
		RestTemplate restTemplate;	// scope is function postEiCreateTender	
    	restTemplate = builder.build();
    	
		// save CreateTender message as sent by TEUA
		tempCreate = eiCreateTender;	
		tempTender = tempCreate.getTender(); // and pull out Tender
		
		logger.info("postEiCreateTender to LME. TenderId " +
				tempCreate.getTender().getTenderId().toString());
		/*
		 * Pass on to LME and use POST responseBody in reply to origin
		 */
		tempPostResponse = restTemplate.postForObject("http://localhost:8080/lme/createTender", 
				tempCreate, 
				EiCreatedTenderPayload.class);
		
		logger.trace("LMA after forward to LME and before return " + tempPostResponse.toString());
		
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
	public EiCreatedTransactionPayload postEiCreateTransactionPayload(
			@RequestBody EiCreateTransactionPayload eiCreateTransactionPayload)	{

		EiTender tempTender;
		ActorIdType tempPartyId;
		EiTransaction tempTransaction;
		EiCreateTransactionPayload tempCreate;
		EiCreatedTransactionPayload tempCreated, tempPostResponse;
		// Is class scope OK for builder?
		final RestTemplateBuilder builder = new RestTemplateBuilder();
		RestTemplate restTemplate;	// scope is function postEiCreateTender
		restTemplate = builder.build();
		
		ActorIdType positionParty;
		Interval positionInterval;
		String positionUri;
		long positionQuantity;
		String positionResponse;
		PositionAddPayload positionAddPayload;

		
//		//convert to URI for position manager
//				positionUri = "/position/" +
//						" actorNumericIds[teuaId].toString()" +
//						"/getPosition";
//				logger.info("positionUri is " + positionUri);
//				
//				logger.debug("numericTeuaId is " + numericTeuaId +" String is " + teuaId);		
//				logger.debug("postEiCreateTender teuaId " +
//					teuaId +
//					" actorNumericIds[teuaId] " +
//					actorIds[numericTeuaId].toString());


//		// request position for  positionParty in positionInterval
////		RestTemplate restTemplate = builder.build();
//		positionUri = "http://localhost:8080/position/" +
//				positionParty.toString() +
//				"getPosition";
//		
//		
//		positionResponseList = restTemplate.getForObject(
//				positionUri,
//				PositionResponseList.class);
//		logger.info("return from " + positionUri +
//				" result " + positionResponseList.toString());

		
		/*
		 * Originated by LME and forwarded by LMA to TEUA based on market match
		 * and party. Rewrite messages so party and counterpary are counter-symmetric
		 */
		//	local temporary variables
		tempCreate = eiCreateTransactionPayload;
		tempTransaction = eiCreateTransactionPayload.getTransaction();
		tempTender = tempCreate.getTransaction().getTender();

		tempPartyId = tempCreate.getPartyId();
		positionParty = tempPartyId;
		positionInterval = tempTender.getInterval();
		positionUri = "http://localhost:8080/position/" +
				positionParty.toString() +
				"add";
		
		positionQuantity = (tempTender.getSide() == SideType.BUY ? tempTender.getQuantity() :
				-tempTender.getQuantity());
		
//		restTemplate = builder.build();

		// add the algebraic signed position from EiCreateTransactionPayload and send
		positionAddPayload = new PositionAddPayload(positionInterval, positionQuantity);

		positionResponse = restTemplate.postForObject(
				positionUri,
				positionAddPayload,
				String.class);
		logger.info("return from " + positionUri +
				" result " + positionResponse);

		/*
		 * 	Pass the EiCreateTransaction payload to the TEUA/EMA keyed by partyId in 
		 * 	the EiCreateTransactionPayload
		 */
		
		logger.trace("tempCreate partyId toString " + tempPartyId.toString() + " " +
				tempCreate.toString());
		tempTeuaUri = postLmaToTeuaPartyIdMap.get(tempCreate.getPartyId().value());
		
		logger.debug("tempTeuaUri is '" + tempTeuaUri + "'");
		
		if (tempTeuaUri == null) {
			logger.info("tempTeuaUri is null - postLmaToTeuaPartyIdMap had no entry for " +
					tempCreate.getPartyId().toString());
			// dump LmaRestController.postLmaToTeuaPartyIdMap
			// use the value shown in TEUA initialization of the map

			tempTeuaUri = "http://localhost:8080/teua/1/createTransaction"; // default if error
			logger.info("tempTeuaUri is null. Using " + tempTeuaUri);

//			if (dumpMap)	{
//				dumpMap = false;	// log map first time only - it doesn't change
//				for (Map.Entry<Long, String> entry : postLmaToTeuaPartyIdMap.entrySet())	{
//					Long key = entry.getKey();
//					Object value = entry.getValue();
//					logger.info("postLmaToTeuaPartyIdMap " + key.toString() + " " + value.toString());
//				}
//			}
		}	else	{
			logger.debug("LMA posting EiCreateTran to " + tempTeuaUri + " partyId " +
					tempCreate.getPartyId().toString() +
					" counterPartyId " + tempCreate.getCounterPartyId().toString() +
					" " + tempCreate.getTransaction().toString());
		}
		
		tempPostResponse = restTemplate.postForObject(tempTeuaUri, 
				tempCreate,
				EiCreatedTransactionPayload.class);
				
		// And send the EiCreatedTransaction from the TEUA back to the LME
		return tempPostResponse;
	}
	
	
	/*
	 * POST - /cancelTender
	 * 		RequestBody is EiCancelTender
	 * 		ResponseBody is EiCanceledTender
	 */

	@PostMapping("/cancelTender")
	public EICanceledTenderPayload postEiCancelTenderPayload(
			@RequestBody EiCancelTenderPayload eiCancelTender)	{
		TenderIdType tempTenderId;
		EiCancelTenderPayload tempCancel;	
		EICanceledTenderPayload tempCanceled, tempPostResponse;

		// Is class scope OK for builder?
		final RestTemplateBuilder builder = new RestTemplateBuilder();
		RestTemplate restTemplate;	// scope is function postEiCreateTender

		logger.info("LMA before builder for /cancelTender");
		restTemplate = builder.build();

		// save CancelTender message as sent by TEUA
		logger.info("LMA before forward CancelTender to LME");
		tempCancel = eiCancelTender;
		
		/*
		tempCanceled = new EICanceledTender(
				tempCancel.getPartyId(),
				tempCancel.getCounterPartyId(),
				new EiResponse(200, "OK"));
		*/

		/*
		 * Pass on to LME and use POST responseBody EiCanceledTender in reply to origin
		 */
		tempPostResponse = restTemplate.postForObject("http://localhost:8080/lme/cancelTender",			
				tempCancel, 		
				EICanceledTenderPayload.class);
		
		logger.info("LMA after forward CancelTender to LME and before return " + tempPostResponse.toString());
		
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

	public static TenderIdType getCurrentTenderId() {
		return currentTenderId;
	}

	public static void setCurrentTenderId(TenderIdType currentTenderId) {
		LmaRestController.currentTenderId = currentTenderId;
	}

	public static ConcurrentHashMap<Long, String> getPostLmaToTeuaPartyIdMap() {
		return postLmaToTeuaPartyIdMap;
	}

	public static void setPostLmaToTeuaPartyIdMap(ConcurrentHashMap<Long, String> postLmaToTeuaPartyIdMap) {
		LmaRestController.postLmaToTeuaPartyIdMap = postLmaToTeuaPartyIdMap;
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
