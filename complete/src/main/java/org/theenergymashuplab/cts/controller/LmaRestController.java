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

package org.theenergymashuplab.cts.controller;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// For RestTemplate
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;
import org.theenergymashuplab.cts.controller.payloads.EICanceledTenderPayload;
import org.theenergymashuplab.cts.controller.payloads.EiCancelTenderPayload;
import org.theenergymashuplab.cts.controller.payloads.EiCreateTenderPayload;
import org.theenergymashuplab.cts.controller.payloads.EiCreateTransactionPayload;
import org.theenergymashuplab.cts.controller.payloads.EiCreatedTenderPayload;
import org.theenergymashuplab.cts.controller.payloads.EiCreatedTransactionPayload;
import org.theenergymashuplab.cts.controller.payloads.PositionAddPayload;
import org.theenergymashuplab.cts.TenderIdType;
import org.theenergymashuplab.cts.SideType;
import org.theenergymashuplab.cts.Interval;
import org.theenergymashuplab.cts.EiTransaction;
import org.theenergymashuplab.cts.EiTender;
import org.theenergymashuplab.cts.ActorIdType;

/*
 *	SBE next steps

This code shows an intermediate step between the default Spring JSON serialization
and an implementation where SBE or JSON is chosen on a link-by-link basis.

At present, we use the Spring default serialization for content type application/octet-stream,
serialize the message into a byte array (see additional comments in RestTemplate invocations
below).

In the future, we plan that the SBE encoders and decoders will be integrated as Spring
HttpMessageConverters so setting the content type in the HttpHeader will indicate to Spring
which serialization to use.

That has the additional advantage that the choice between SBE and JSON can be made link-by-link
or message-by-message, but we anticipate  link-by-link.

*/
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
@RequestMapping("/lma")
public class LmaRestController {
	private static final AtomicLong counter = new AtomicLong();
	private static EiTender currentTender;
	private static EiTransaction currentTransaction;
	private static TenderIdType currentTenderId;
	private static final ActorIdType partyId  = new ActorIdType();
	private static String tempTeuaUri = "http://localhost:8080/teua/1/createTransaction";
	
	// 	partyId to URI for posting EiCreateTransaction to /teua/{id}
	//	pushed here by TEUA which has the ActorId and {id} information
	public static ConcurrentHashMap<Long, String> postLmaToTeuaPartyIdMap;
	//	Initialized in TeuaRestController as this static map
	
	private static final Logger logger = LogManager.getLogger(
			LmaRestController.class);
	
	public LmaRestController()	{	// zero parameter constructor
		logger.trace("LMA zero parameter constructor");
	}
	
	/*
	 * GET - /lma/party responds with PartyId
	 */
	@GetMapping("/party")
	public ActorIdType getParty() {
		return LmaRestController.partyId;
	}
	
	/*
	 * POST - /createTender - POSTed by TEUA/EMA to LMA
	 *				Forwarded to LME
	 * 		RequestBody is EiCreateTender
	 * 		ResponseBody is EiCreatedTender
	 */
	@PostMapping("/createTender")
	public byte[] postEiCreateTender(
			@RequestBody byte[] eiCreateTenderByteArr)	{

		EiCreateTenderPayload tempCreate;
		// Will pass on eiCreateTender body to LME and return its response tempPostResponse
		EiCreatedTenderPayload tempPostResponse; 

		// Is class scope OK for builder?
		final RestTemplateBuilder builder = new RestTemplateBuilder();
		RestTemplate restTemplate;	// scope is function postEiCreateTender	
    	restTemplate = builder.build();
    	
		// save CreateTender message as sent by TEUA
		//tempCreate = eiCreateTender;	
		
		//logger.debug("postEiCreateTender to LME. TenderId " + tempCreate.getTender().getTenderId().toString());
		/*
		 * Pass on to LME and use POST responseBody in reply to origin
		 */
    	/* Default Spring serialization is JSON; the default code is commented out below */
		byte[] EiCreatedTenderByteArr = restTemplate.postForObject("http://localhost:8080/lme/createTender", 
				eiCreateTenderByteArr, 
				byte[].class);
		
		logger.trace("LMA after forward to LME and before return " + eiCreateTenderByteArr.toString());
		
		/*
		tempCreated = new EiCreatedTender(tempTender.getTenderId(),
				tempCreate.getPartyId(),
				tempCreate.getCounterPartyId(),
				new EiResponse(200, "OK"));
		*/
		return EiCreatedTenderByteArr;
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
		EiCreateTransactionPayload tempCreate;
		EiCreatedTransactionPayload tempPostResponse;
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

		logger.debug("Start of EiCreateTransaction in LMA");
		/*
		 * Originated by LME and forwarded by LMA to TEUA based on market match
		 * and party. Rewrite messages so party and counterpary are counter-symmetric
		 */
		//	local temporary variables
		tempCreate = eiCreateTransactionPayload;
		tempTender = tempCreate.getTransaction().getTender();

		tempPartyId = tempCreate.getPartyId();
		positionParty = tempPartyId;
		positionInterval = tempTender.getInterval();
		logger.debug("positionParty.toString is " + positionParty + " positionInterval " +
				positionInterval.toString() + " tempPartyId " + tempPartyId.toString());
		
		positionUri = "http://localhost:8080/position/" +
				positionParty.toString() +
				"/add";
		
		positionQuantity = (tempTender.getSide() == SideType.BUY ? tempTender.getQuantity() :
				-tempTender.getQuantity());
		
		logger.info("positionUri '" + positionUri + " positionQuantity " + positionQuantity);

		// add the algebraic signed position from EiCreateTransactionPayload and send
		positionAddPayload = new PositionAddPayload(positionInterval, positionQuantity);

		logger.trace("Before call to " + positionUri);
		positionResponse = restTemplate.postForObject(
				positionUri,
				positionAddPayload,
				String.class);
		logger.debug("return from " + positionUri +
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
			logger.trace("LMA posting EiCreateTran to " + tempTeuaUri + " partyId " +
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
	 * TODO Implement EiCancelTender and ClientCancelTender
	 * 
	 * POST - /cancelTender
	 * 		RequestBody is EiCancelTender
	 * 		ResponseBody is EiCanceledTender
	 */

	@PostMapping("/cancelTender")
	public EICanceledTenderPayload postEiCancelTenderPayload(
			@RequestBody EiCancelTenderPayload eiCancelTender)	{
		EiCancelTenderPayload tempCancel;	
		EICanceledTenderPayload tempPostResponse;

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
