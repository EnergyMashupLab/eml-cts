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

import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.utils.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
// For RestTemplate
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;
import org.theenergymashuplab.cts.controller.payloads.EICanceledTenderPayload;
import org.theenergymashuplab.cts.controller.payloads.EiCancelTenderPayload;
import org.theenergymashuplab.cts.controller.payloads.EiCreateTenderPayload;
import org.theenergymashuplab.cts.controller.payloads.EiCreateTenderPayloadRabbit;
import org.theenergymashuplab.cts.controller.payloads.EiCreateTransactionPayload;
import org.theenergymashuplab.cts.controller.payloads.EiCreatedTenderPayload;
import org.theenergymashuplab.cts.controller.payloads.EiCreatedTransactionPayload;
import org.theenergymashuplab.cts.controller.payloads.PositionAddPayload;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.ConnectionFactory;

import org.theenergymashuplab.cts.TenderIdType;
import org.theenergymashuplab.cts.SideType;
import org.theenergymashuplab.cts.Interval;
import org.theenergymashuplab.cts.EiTransaction;
import org.theenergymashuplab.cts.EiTender;
import org.theenergymashuplab.cts.EiTenderRabbit;
import org.theenergymashuplab.cts.ActorIdType;
import org.theenergymashuplab.cts.BridgeInstant;
import org.theenergymashuplab.cts.BridgeInterval;
import org.theenergymashuplab.cts.EiResponse;


@RestController
@RequestMapping("/lma")
public class LmaRestController implements Serializable {
	private static final AtomicLong counter = new AtomicLong();
	private static EiTender currentTender;
	private static EiTransaction currentTransaction;
	private static TenderIdType currentTenderId;
	private static final ActorIdType partyId  = new ActorIdType();
	private static String tempTeuaUri = "http://localhost:8080/teua/1/createTransaction";
	
	private RabbitTemplate rabbitTemplate = new RabbitTemplate(new CachingConnectionFactory());
	
	@Autowired
	private TopicExchange exchange; 
	
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
	public EiCreatedTenderPayload 	postEiCreateTender(
			@RequestBody EiCreateTenderPayload eiCreateTender) {

		EiCreateTenderPayload tempCreate;
		// Will pass on eiCreateTender body to LME and return its response tempPostResponse
		EiCreatedTenderPayload tempPostResponse; 
    	
		// save CreateTender message as sent by TEUA
		EiTender receivedTender = eiCreateTender.getTender();
		EiTenderRabbit rabbitTender = new EiTenderRabbit(receivedTender.getTenderId(), new BridgeInterval(receivedTender.getInterval()),
				receivedTender.getQuantity(), receivedTender.getPrice(), new BridgeInstant(receivedTender.getExpirationTime()), receivedTender.getSide());
		
    	
    	tempCreate = eiCreateTender;
    	EiCreateTenderPayloadRabbit rabbitCreate = new EiCreateTenderPayloadRabbit(rabbitTender, tempCreate.getPartyId(), tempCreate.getCounterPartyId(), tempCreate.getRequestId());
		//tempCreate = new EiCreateTenderPayloadRabbit(eiCreateTender);	
		
		logger.info("postEiCreateTender to LME. TenderId " +
				rabbitTender.getTenderId().toString());
		/*
		 * Pass on to LME and use POST responseBody in reply to origin
		 */
		
		String jsonStr = "";
		ObjectMapper Obj = new ObjectMapper();
		
        try {  
            // Converting the Java object into a JSON string  
            jsonStr = Obj.writeValueAsString(rabbitCreate);  
            // Displaying Java object into a JSON string   
        }  
        catch (IOException e) {  
            e.printStackTrace();  
        }  
        
		 
		rabbitTemplate.convertAndSend(exchange.getName(), "foo.bar.baz", jsonStr);
		
		//logger.trace("LMA after forward to LME and before return " + tempPostResponse.toString());
		
		/*IMPORTANT:
		 * This return has shortcutted the origional architecture. The LME is supposed to return this 
		 * response to the LMA. The LMA is creating and returning because it was easier than setting up another
		 * RabbitMQ send/recieve to handle it. That is what will need to be done in the future, but this
		 * is just a proof of concept so the shortcut was implemented for ease.
		 */
		EiCreatedTenderPayload tempCreated = new EiCreatedTenderPayload(tempCreate.getTender().getTenderId(),
				tempCreate.getPartyId(),
				tempCreate.getCounterPartyId(),
				new EiResponse(200, "OK"));
		
		return tempCreated;
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

		logger.info("Start of EiCreateTransaction in LMA");
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
		logger.info("positionParty.toString is " + positionParty + " positionInterval " +
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
		logger.info("return from " + positionUri +
				" result " + positionResponse);


		/*
		 * 	Pass the EiCreateTransaction payload to the TEUA/EMA keyed by partyId in 
		 * 	the EiCreateTransactionPayload
		 */
		
		logger.trace("tempCreate partyId toString " + tempPartyId.toString() + " " +
				tempCreate.toString());
		tempTeuaUri = postLmaToTeuaPartyIdMap.get(tempCreate.getPartyId().value());
		
		logger.info("tempTeuaUri is '" + tempTeuaUri + "'");
		
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
