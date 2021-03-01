/*
 * Copyright 2019-2020 The Energy Mashup Lab
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

// DOCKER STATIC - this is where the LmaRestController class is imported
package org.theenergymashuplab.cts.controller;

//import java.util.Random;
//import java.util.concurrent.atomic.AtomicLong;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

//	DOCKER SHARED
import org.theenergymashuplab.cts.ActorIdType;
import org.theenergymashuplab.cts.EiResponse;
import org.theenergymashuplab.cts.EiTender;
import org.theenergymashuplab.cts.EiTransaction;
import org.theenergymashuplab.cts.TenderIdType;
import org.theenergymashuplab.cts.controller.payloads.ClientCreateTenderPayload;
import org.theenergymashuplab.cts.controller.payloads.ClientCreateTransactionPayload;
import org.theenergymashuplab.cts.controller.payloads.ClientCreatedTenderPayload;
import org.theenergymashuplab.cts.controller.payloads.ClientCreatedTransactionPayload;
import org.theenergymashuplab.cts.controller.payloads.EICanceledTenderPayload;
import org.theenergymashuplab.cts.controller.payloads.EiCancelTenderPayload;
import org.theenergymashuplab.cts.controller.payloads.EiCreateTenderPayload;
import org.theenergymashuplab.cts.controller.payloads.EiCreateTransactionPayload;
import org.theenergymashuplab.cts.controller.payloads.EiCreatedTenderPayload;
import org.theenergymashuplab.cts.controller.payloads.EiCreatedTransactionPayload;
import org.theenergymashuplab.cts.dto.EiCanceledResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/*

Issues in separating the TEUA and LMA in different dockers. Tags are in comments below.

(1) Addressing of TEUAs, LMA Tag: DOCKER ADDRESSING

(1.5) Multiplicity >> available IP addresses	Tag: DOCKER MULTIPLICITY

(2) Reference to attributes/methods of another class Static references	Tag: DOCKER STATIC

(3) Hidden shared values	Tag: DOCKER SHARED

(4) Determine IP address to send to EML-CTS LMA	Tag: DOCKER LMA-IP

(5) ClientRestController or direct POST to TEUA?	Tag: None; see separate notes

*/

/*
 *	DOCKER ADDRESSING
 *	The URI localhost:8080/teua/{teuaId}/createTransaction doesn't work when the
 * TEUA is not in the same JVM as the sender
 */
@RestController
@RequestMapping("/teua")	// use dynamic URIs - this supports one
public class TeuaRestController {

	private final ActorIdType partyId  = new ActorIdType(); /* STATIC */
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
	private Long[] actorNumericIds; 	    // actorNumericIds for each client/{id}
	private ActorIdType[] actorIds;			// ActorIdType values for the created actors
	String[] postClientCreateTransactionUri;	// URI to post to client[i]

/*
*	DOCKER STATIC
*  LmaRestController.postLmaToTeuaPartyIdMap is a static reference to class LmaRestController
*  
*  This reference does not work if LmaRestController is not in the same JVM (and Docker) as 
*  the caller here in TeuaRestController.
*  
*  Since the TeuaRestController gets and manages the Party IDs it has to fill in the 
*  "return address" that the LMA uses to POST to the two parties in an EiTransaction
*  
*  Either pass each entry to a new REST API in the LMA or create the table and send it
*	
*	The LMA uses the hashmap; it's populated in the standalone non-docker eml-cts by the TEUA.
*/

//	public static ConcurrentHashMap<ActorIdType, String> postLmaToTeuaPartyIdMap; in LMA
    
    // for managing client/{id} and teua/{id}
    public final int DEFAULT_COUNT = 20;
    public final int MAX_COUNT = 2000;
    private int idLimit;

	/*
	*	DOCKER LMA-IP
	*	One way to ensure that the TEUA instance or class knows the IP address to talk to
	*  the LMA is to pass it on the command line.
	*
	*	When this class runs in a Docker it will be as part of an application, which can take
	*	the LMA's IP address on the command line.
	*	
	*	Then main() calls **new TeuaRestController(howMany, LMA-IP-Address)** with a value taken from
	*	the command line arguments.
	*
	*	HowMany on the command line would specify the number of dynamic URI-addressed TEUAs,
	*	while the IP address to connect to the LMA would also be on the command line.
	*/
	
	// Constructor for class TeuaRestController - zero parameters
	public TeuaRestController()	{
		this.idLimit = DEFAULT_COUNT;
		logger.trace("Built TeuaRestController zero param constructor idLimit " +
					this.idLimit);
		initMapArray(idLimit);
	}
	
	// Constructor for class ClientRestController - howMany TEUAs to create
	//		 as the single parameter
	// DOCKER MULTIPLICITY Address multiplicity per IP address issues
	public TeuaRestController(int howMany)	{
		if (howMany > MAX_COUNT)	{
			logger.trace("Constructor one parameter howMany " + howMany +
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

	    // DOCKER ADDRESSING for SC/Client/User IP address/port - default is localhost
	    // DOCKER ADDRESSING Fix address for client - get from client?	
		 String clientUriPrefix = "http://localhost:8080/client/";
	    String clientUriSuffix = "/clientCreateTransaction";

		// Use my IP address - this is how the LMA sends to me
		// DOCKER ADDRESSING
	    String teuaUriPrefix = "http://localhost:8080/teua/";
	    String teuaUriSuffix = "/createTransaction";
	    ActorIdType tempActorId;
	    String mapReturns;
	
		/*
		*	DOCKER SHARED
		*  Class ActorIdType uses a hidden shared value contained as a static value
		*  inside UidType. This guarantees unique party ID values, but doesn't do so
		*  if class UidType is instantiated inside two different JVMs.
		*  
		*  Solution:
		*		EITHER instantiate class ActorIdType  and class UidType in the Teua and pass
		*  	a new ActorId to the LMM
		*  	OR
		*  	GET new ActorIds from the endpoint that holds the static UidType
		*/

		/*
		*	DOCKER MULTIPLICITY
		*
		*	This code establishes the multiple TEUAs for this IP address.
		*  Early uses include thousands of TEUAs, so one TEUA instance per Docker
		*  container will not work. Some combination of TEUA IP addresses and Docker
		*  containers plus multiplexing with the same or similar dynamic URI seems
		*  indicated.
		*/

		// Initialize global parallel arrays
	    actorNumericIds = new Long[idLimit];
	    actorIds = new ActorIdType[idLimit];
		postClientCreateTransactionUri = new String[this.idLimit];
		//	Triple the size should limit collisions and chain length

		// Place in LMA partyId to URI map 
		//		- a spontaneous EiTransaction goes to the UA for partyId
		LmaRestController.postLmaToTeuaPartyIdMap = 
					new ConcurrentHashMap<Long, String>(idLimit*3);

		
		// For each array index construct a string URI for the callback
		//	DOCKER ADDRESSING
		// and store TEUA[i]'s partyn ID in the array 
		for (i = 0; i < this.idLimit; i++)	{
			clientUri = clientUriPrefix + String.valueOf(i) + clientUriSuffix;
			postClientCreateTransactionUri[i] = clientUri;
			tempActorId = new ActorIdType();
			actorIds[i] = tempActorId;
			actorNumericIds[i] = tempActorId.value();
			teuaUri = teuaUriPrefix + String.valueOf(i) + teuaUriSuffix;

			/*
			*	DOCKER STATIC see note above. Static reference to class LmaRestController which may
			*  not be in this application
			*/
		
			// Retrieve the LMA's mapping for logging
			mapReturns = LmaRestController.postLmaToTeuaPartyIdMap.put(actorNumericIds[i], teuaUri);
			
			logger.trace("mapReturns '"+ mapReturns + "' for key " + actorNumericIds[i]);
			logger.trace("Map size " + LmaRestController.postLmaToTeuaPartyIdMap.size() +
					" i = " + i + " " + clientUri + " " + teuaUri +
					" actorId " + actorNumericIds[i].toString());
		}
	}
	
	/*
	 * GET - /teua/{#}/party responds with PartyId json TODO
	 */
	@GetMapping("/party")
	public ActorIdType getParty() {
		return this.partyId;
	}
	
	
	/*
	 * POST - /createTransaction
	 * 		RequestBody is EiCreateTransaction
	 * 		ResponseBody is EiCreatedTransaction
	 * 
	 * Position for this Party was updated in LMA
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
		EiCreatedTransactionPayload tempCreated;
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
		logger.trace("TEUA received createTransaction " +
				tempCreate.toString() + " before forward to Client " 
				);
		
		/*
		 * Build ClientCreateTransactionPayload to POST to client with same id
		 */	
		clientCreate = new ClientCreateTransactionPayload(
				/* side 	*/	tempTender.getSide(),
				/* quantity	*/	tempTender.getQuantity(),
				/* price	*/	tempTender.getPrice(),
				/* tenderId	*/	tempTender.getTenderId().value()
				);
		
		logger.trace("Built ClientCreateTransactionPayload " +
				clientCreate.toString());

		numericTeuaId = Integer.valueOf(teuaId);
		logger.debug(" Forwarding ClientCreateTransaction to " +
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
		
		logger.debug("tempCreated constructed before return " + tempCreated.toString());
		
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
		final RestTemplate restTemplate = new RestTemplate();

		return restTemplate.postForObject("http://localhost:8080/lma/cancelTender", eiCancelTender,
				EICanceledTenderPayload.class);
	}

	
	/*
	 * POST - /clientCreateTender processed and sent to LMA,
	 * received from Client/SC
	 * 		RequestBody is ClientCreateTenderPayload
	 * 		ResponseBody is ClientCreatedTenderPayload
	 * 
	 * Query PositionManager for the TEUA's PartyId, net full requirements energy request
	 * (positive or negative) and forward the EiCreateTenderPayload with adjusted quanity
	 * and possible different Side to LMA
	 * 
	 * Processing the request from the Building Controller (SC/Client)
	 * Return the ClientCreatedTenderPayload which is just CTS TenderId for new tender
	 * 
	 * NOTE that the quantity in a ClientCreateTender is FULL REQUIREMENTS for the
	 * Interval. The User Agent will adjust that request by energy already bought or sold
	 * on behalf of this client for the Interval, to get a net amount to go from the client's position
	 * (energy already bought or sold, netted) to the Full Requirements amount for Interval.
	 */
	@PostMapping("{teuaId}/clientCreateTender")
	public ClientCreatedTenderPayload postClientCreateTender(
			@PathVariable String teuaId,
			@RequestBody ClientCreateTenderPayload clientCreateTender)	{
		ClientCreateTenderPayload tempClientCreateTender;	
		ClientCreatedTenderPayload tempReturn;
		EiTender tender;
		EiCreateTenderPayload eiCreateTender;	
		Integer numericTeuaId = -1;
		String positionUri;
		
		
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
		
		
		//convert to URI for position manager
		positionUri = "/position/" 
				 + actorIds[numericTeuaId] +
				"/getPosition";
		logger.debug("positionUri is " + positionUri);
		
		logger.debug("numericTeuaId is " + numericTeuaId +" String is " + teuaId);		
		logger.debug("postEiCreateTender teuaId " +
			teuaId +
			" actorNumericIds[teuaId] " +
			actorIds[numericTeuaId].toString());
		
		tempClientCreateTender = clientCreateTender;	// save the parameter

		/*
		 * Create a new EiTender using the interval, quantity, price,and expiration
		 * time  sent by the Client/SC, and insert it via the constructor in a
		 * new EiCreateTenderPayload.
		 * 
		 * partyId is in actorNumericIds[] , counterPartyId is the LME representing
		 * the market and the POST is to the LMA.
		 * 
		 * if Building sends to /teua/7 that means it's client 7
		 */
		tender = new EiTender(
				tempClientCreateTender.getInterval(),
				tempClientCreateTender.getQuantity(),
				tempClientCreateTender.getPrice(),
				tempClientCreateTender.getBridgeExpireTime().asInstant(),
				tempClientCreateTender.getSide());
		
		// 	Construct the EiCreateTender payload to be forwarded to LMA
		eiCreateTender = new EiCreateTenderPayload(tender, actorIds[numericTeuaId],
				this.lmePartyId);
		// set party and counterParty -partyId saved in actorIds, counterParty is lmePartyId
		eiCreateTender.setPartyId(actorIds[numericTeuaId]);
		eiCreateTender.setCounterPartyId(lmePartyId);
		
		logger.trace("TEUA sending EiCreateTender to LMA " +
				eiCreateTender.toString());
			
		//	And forward to the LMA DOCKER CHECK
		restTemplate = builder.build();
		EiCreatedTenderPayload result = restTemplate.postForObject
			("http://localhost:8080/lma/createTender", eiCreateTender,
					EiCreatedTenderPayload.class);
		
		// and put CtsTenderId in ClientCreatedTenderPayload
		tempReturn = new ClientCreatedTenderPayload(result.getTenderId().value());
		logger.trace("TEUA before return ClientCreatedTender to Client/SC " +
				tempReturn.toString());
		
		return tempReturn;
	}
	
}
