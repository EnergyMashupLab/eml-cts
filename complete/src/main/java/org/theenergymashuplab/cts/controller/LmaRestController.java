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

import javax.accessibility.AccessibleHypertext;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.module.ModuleDescriptor.Builder;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import org.agrona.concurrent.UnsafeBuffer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.propertyeditors.ByteArrayPropertyEditor;
// For RestTemplate
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.theenergymashuplab.cts.*;
import org.theenergymashuplab.cts.controller.payloads.*;
import org.theenergymashuplab.cts.generated_files.EiCreateTransactionPayloadDecoder;
import org.theenergymashuplab.cts.generated_files.EiCreateTransactionPayloadEncoder;
import org.theenergymashuplab.cts.generated_files.MessageHeaderDecoder;
import org.theenergymashuplab.cts.generated_files.MessageHeaderEncoder;
import org.theenergymashuplab.cts.sbe.EiTransactionPayloadEncoderDecoder;

@RestController
@RequestMapping("/lma")
public class LmaRestController {
	private static final AtomicLong counter = new AtomicLong();
	private static EiTenderType currentTender;
	private static EiTransaction currentTransaction;
	private static TenderIdType currentTenderId;
	private static final ActorIdType partyId  = new ActorIdType();
	private static String tempTeuaUri = "http://localhost:8080/teua/1/createTransaction";
	private static String tempTeuaUriTicker = "http://localhost:8080/teua/1/sendTickerUpdate";
	
	// 	partyId to URI for posting EiCreateTransaction to /teua/{id}
	//	pushed here by TEUA which has the ActorId and {id} information
	public static ConcurrentHashMap<Long, String> postLmaToTeuaPartyIdMap;
	public static ConcurrentHashMap<Long, String> postLmaToTeuaPartyIdMapForQuotes;
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
	@PostMapping("/jsoncreateTender")
	public EiCreatedTenderPayload 	postEiCreateTender(
			@RequestBody EiCreateTenderPayload eiCreateTender)	{

		EiCreateTenderPayload tempCreate;
		// Will pass on eiCreateTender body to LME and return its response tempPostResponse
		EiCreatedTenderPayload tempPostResponse; 

		// Is class scope OK for builder?
		final RestTemplateBuilder builder = new RestTemplateBuilder();
		RestTemplate restTemplate;	// scope is function postEiCreateTender	
    	restTemplate = builder.build();
    	
		// save CreateTender message as sent by TEUA
		tempCreate = eiCreateTender;	
		
		logger.debug("postEiCreateTender to LME. TenderId " +
				tempCreate.getTender().getTenderId().toString());
		/*
		 * Pass on to LME and use POST responseBody in reply to origin
		 */
		tempPostResponse = restTemplate.postForObject("http://localhost:8080/lme/jsoncreateTender", 
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
	
	
	@PostMapping("/createTender")
	public byte[] 	postEiCreateTender(
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
		 
//		logger.debug("postEiCreateTender to LME. TenderId " +
//				tempCreate.getTender().getTenderId().toString());
		/*
		 * Pass on to LME and use POST responseBody in reply to origin
		 */
		
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
	public byte[] postEiCreateTransactionPayload(
			@RequestBody byte[] eiCreateTransactionPayload)	{

        UnsafeBuffer buffer = new UnsafeBuffer(eiCreateTransactionPayload);
        MessageHeaderDecoder headerDecoder = new MessageHeaderDecoder();
        EiCreateTransactionPayloadDecoder decoder = new EiCreateTransactionPayloadDecoder();

        headerDecoder.wrap(buffer, 0);
        int actingBlockLength = headerDecoder.blockLength();
        int actingVersion = headerDecoder.version();
        int headerLength = headerDecoder.encodedLength();

		
		EiTenderType tempTender;
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
		tempCreate = EiTransactionPayloadEncoderDecoder
	            .eiCreateTransactionDecode(decoder, buffer, headerLength, actingBlockLength, actingVersion);
;
		tempTender = tempCreate.getTransaction().getTender();
		
		// CURRENTLY, TENDER DETAIL IMPLEMENTATION IS UNSTABLE
		// THIS IS A WORKAROUND TO ENSURE THAT APPLICATION AT LEAST
		// WORKS WITH INTERVAL TENDERS
		TenderDetail tenderDetail = tempTender.getTenderDetail();
		if (tenderDetail.getClass() != TenderIntervalDetail.class) {
			throw new IllegalArgumentException("Currently only support simple Interval Tenders");
		}
		TenderIntervalDetail tenderIntervalDetail = (TenderIntervalDetail) tenderDetail;

		tempPartyId = tempCreate.getPartyId();
		positionParty = tempPartyId;
		positionInterval = tenderIntervalDetail.getInterval();
		logger.debug("positionParty.toString is " + positionParty + " positionInterval " +
				positionInterval.toString() + " tempPartyId " + tempPartyId.toString());
		
		positionUri = "http://localhost:8080/position/" +
				positionParty.toString() +
				"/add";
		
		positionQuantity = (tempTender.getSide() == SideType.BUY ? tenderIntervalDetail.getQuantity() :
				-tenderIntervalDetail.getQuantity());
		
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
        UnsafeBuffer outBuffer = new UnsafeBuffer(new byte[4096]);
        MessageHeaderEncoder headerEncoder = new MessageHeaderEncoder();
        EiCreateTransactionPayloadEncoder encoder = new EiCreateTransactionPayloadEncoder();

        int encodedLength = EiTransactionPayloadEncoderDecoder
            .eiCreateTransactionEncode(encoder, outBuffer, headerEncoder, tempCreate);

        byte[] outgoingBytes = new byte[encodedLength];
        outBuffer.getBytes(0, outgoingBytes);
        
     // Send SBE-encoded payload to TEUA
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        HttpEntity<byte[]> request = new HttpEntity<>(outgoingBytes, headers);

        ResponseEntity<byte[]> response = restTemplate.exchange(
            tempTeuaUri,
            HttpMethod.POST,
            request,
            byte[].class
        );

        // Return SBE response bytes back to LME
        return response.getBody();
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

	@PostMapping("/createStreamTender")
	public EiCreatedStreamTenderPayload postEiCreateStreamTender(
		@RequestBody EiCreateStreamTenderPayload eiCreateStreamTender){

		EiCreateStreamTenderPayload tempEiCreateStreamTender;
		EiCreatedStreamTenderPayload tempEiCreatedStreamTender;

		//Initialize the builder
		final RestTemplateBuilder builder = new RestTemplateBuilder();
		RestTemplate restTemplate;	// scope is method postEiCreateStreamTender	
    	restTemplate = builder.build();

		//Deserialize what was posted to us
		tempEiCreateStreamTender = eiCreateStreamTender;

		//Log it
		logger.debug("postEiCreateStreamTender to LME. TenderId " +
				tempEiCreateStreamTender.getTender().getTenderId().toString());
		/*
		 * Pass on to LME and use POST responseBody in reply to origin
		 */
		tempEiCreatedStreamTender = restTemplate.postForObject("http://localhost:8080/lme/createStreamTender", 
				tempEiCreateStreamTender,
				EiCreatedStreamTenderPayload.class);
		
		//Log it
		logger.trace("LMA after forward to LME and before return " + tempEiCreatedStreamTender.toString());

		//Return the response
		return tempEiCreatedStreamTender;
	}

	@PostMapping("/createStreamQuote")
	public EiCreatedStreamQuotePayload postEiCreateStreamQuote(
			@RequestBody EiCreateStreamQuotePayload eiCreateStreamQuote){

		EiCreateStreamQuotePayload tempEiCreateStreamQuote;
		EiCreatedStreamQuotePayload tempEiCreatedStreamQuote;

		//Initialize the builder
		final RestTemplateBuilder builder = new RestTemplateBuilder();
		RestTemplate restTemplate;	// scope is method postEiCreateStreamTender
		restTemplate = builder.build();

		//Deserialize what was posted to us
		tempEiCreateStreamQuote = eiCreateStreamQuote;

		//Log it
		logger.debug("postEiCreateStreamTender to LME. TenderId " +
				tempEiCreateStreamQuote.getQuote().getQuoteId().toString());
		/*
		 * Pass on to LME and use POST responseBody in reply to origin
		 */
		tempEiCreatedStreamQuote = restTemplate.postForObject("http://localhost:8080/lme/createStreamQuote",
				tempEiCreateStreamQuote,
				EiCreatedStreamQuotePayload.class);

		//Log it
		logger.trace("LMA after forward to LME and before return " + tempEiCreatedStreamQuote.toString());

		//Return the response
		return tempEiCreatedStreamQuote;
	}

	/*
	 * POST - /createTender - POSTed by TEUA/EMA to LMA
	 *				Forwarded to LME
	 * 		RequestBody is EiCreateTender
	 * 		ResponseBody is EiCreatedTender
	 */
	@PostMapping("/createQuote")
	public EiCreatedQuotePayload postEiCreateQuote(
			@RequestBody EiCreateQuotePayload eiCreateQuote)	{

		EiCreateQuotePayload tempCreate;
		// Will pass on eiCreateTender body to LME and return its response tempPostResponse
		EiCreatedQuotePayload tempPostResponse; 

		// Is class scope OK for builder?
		final RestTemplateBuilder builder = new RestTemplateBuilder();
		RestTemplate restTemplate;	// scope is function postEiCreateTender	
    	restTemplate = builder.build();
    	
		// save CreateTender message as sent by TEUA
		tempCreate = eiCreateQuote;	
		
		logger.debug("postEiCreateTender to LME. TenderId " +
				tempCreate.getQuote().getQuoteId().toString());
		/*
		 * Pass on to LME and use POST responseBody in reply to origin
		 */
		tempPostResponse = restTemplate.postForObject("http://localhost:8080/lme/createQuote", 
				tempCreate, 
				EiCreatedQuotePayload.class);
		
		logger.trace("LMA after forward to LME and before return " + tempPostResponse.toString());
	
		return tempPostResponse;
	}

	/*
	 * POST - /createTender - POSTed by TEUA/EMA to LMA
	 *				Forwarded to LME
	 * 		RequestBody is EiCreateTender
	 * 		ResponseBody is EiCreatedTender
	 */
	@PostMapping("/acceptQuote")
	public EiAcceptedQuotePayload postEiAcceptQuote(
			@RequestBody EiAcceptQuotePayload eiAcceptQuote)	{
		EiAcceptQuotePayload tempAccept;
		// Will pass on eiCreateTender body to LME and return its response tempPostResponse
		EiAcceptedQuotePayload tempPostResponse; 
		ActorIdType tempPartyId;
		String positionUri;


		// Is class scope OK for builder?
		final RestTemplateBuilder builder = new RestTemplateBuilder();
		RestTemplate restTemplate;	// scope is function postEiCreateTender	
    	restTemplate = builder.build();
    	
		// save CreateTender message as sent by TEUA
		tempAccept = eiAcceptQuote;	
		
		logger.debug("postEiAcceptQuote to LME. ReferencedQuoteId: " +
				tempAccept.getReferencedQuoteId().toString());

		//Build the position URI
		tempPartyId = tempAccept.getPartyId();
		positionUri = "http://localhost:8080/position/" +
				tempPartyId.toString() +
				"/add";

		/*
		 * Pass on to LME and use POST responseBody in reply to origin
		 */
		tempPostResponse = restTemplate.postForObject("http://localhost:8080/lme/acceptQuote", 
				tempAccept, 
				EiAcceptedQuotePayload.class);
		
		logger.trace("LMA after forward to LME and before return " + tempPostResponse.toString());

	
		return tempPostResponse;
	}

	/**
	 * Mapping to cancel a quote
	 */
	@PostMapping("/cancelQuote")
	public EICanceledQuotePayload postEiCancelQuote(
		@RequestBody EiCancelQuotePayload eiCancelQuote){
		EiCancelQuotePayload tempCancel;
		EICanceledQuotePayload tempPostResponse;
		ActorIdType tempPartyId;
		String positionUri;

		final RestTemplateBuilder builder = new RestTemplateBuilder();
		RestTemplate restTemplate;
		restTemplate = builder.build();

		//Save the message
		tempCancel = eiCancelQuote;
	
		logger.debug("postEiCancelQuote to LME. ReferencedQuoteId: " +
				tempCancel.getMarketQuoteIds().toString());

		//Build the position URI
		tempPartyId = tempCancel.getPartyId();
		positionUri = "http://localhost:8080/position/" +
				tempPartyId.toString() +
				"/add";

		/*
		 * Pass on to LME and use POST responseBody in reply to origin
		 */
		
		tempPostResponse = restTemplate.postForObject("http://localhost:8080/lme/cancelQuote", 
				tempCancel, 
				EICanceledQuotePayload.class);
		
		logger.trace("LMA after forward to LME and before return " + tempPostResponse.toString());
		return tempPostResponse;
	}


	@PostMapping("/manageSubscription")
	public byte[] postEiManagedTickerSubscription(
			@RequestBody byte[] eiManageTickerSubscriptionByteArr
	){
		EiManageTickerSubscriptionPayload tempManage;
		// Will pass on eiCreateTender body to LME and return its response tempPostResponse
		EiManagedTickerSubscriptionPayload tempPostResponse;

		// Is class scope OK for builder?
		final RestTemplateBuilder builder = new RestTemplateBuilder();
		RestTemplate restTemplate;	// scope is function postEiCreateTender
		restTemplate = builder.build();

		// save CreateTender message as sent by TEUA
		//tempManage = eiManageTickerSubscriptionPayload;

		/*
		 * Pass on to LME and use POST responseBody in reply to origin
		 */
		byte[] EiManagedTickerSubscription = restTemplate.postForObject("http://localhost:8080/lme/manageSubscription",
				eiManageTickerSubscriptionByteArr,
				byte[].class);

		logger.trace("LMA after forward to LME and before return " + EiManagedTickerSubscription.toString());

		return EiManagedTickerSubscription;

	}


	@PostMapping("/sendUpdates")
	public QuoteTickerType postEiCreateTransactionPayload(
			@RequestBody QuoteTickerType quoteTickerType)	{
		System.out.println("Entering the sendUpdate postmapping methods");

		ActorIdType tempPartyId;
		QuoteTickerType tempQuoteTickerType;
		// Is class scope OK for builder?
		final RestTemplateBuilder builder = new RestTemplateBuilder();
		RestTemplate restTemplate;	// scope is function postEiCreateTender
		restTemplate = builder.build();

		/*
		 * Originated by LME and forwarded by LMA to TEUA based on market match
		 * and party. Rewrite messages so party and counterpary are counter-symmetric
		 */

		//	local temporary variables
		tempQuoteTickerType = quoteTickerType;

		tempPartyId = tempQuoteTickerType.getParty();

		logger.trace("PartyId in  the post mapping methods "+tempPartyId.value());

		/*
		 * 	Pass the QuoteTickerType payload to the TEUA/EMA keyed by partyId in
		 * 	the QuoteTickerType
		 */
		logger.trace("tempCreate partyId toString " + tempPartyId.toString() + " " +
				tempQuoteTickerType.toString());

		tempTeuaUriTicker = postLmaToTeuaPartyIdMapForQuotes.get(tempQuoteTickerType.getParty().value());

		logger.debug("tempTeuaUri is '" + tempTeuaUriTicker + "'");

		if (tempTeuaUriTicker == null) {
			logger.info("tempTeuaUri is null - postLmaToTeuaPartyIdMap had no entry for " +
					tempQuoteTickerType.getParty().toString());
			// dump LmaRestController.postLmaToTeuaPartyIdMap
			// use the value shown in TEUA initialization of the map

			tempTeuaUriTicker = "http://localhost:8080/teua/1/sendTickerUpdate"; // default if error
			logger.info("tempTeuaUri is null. Using " + tempTeuaUriTicker);

		}	else	{
			logger.trace("LMA posting EiCreateTran to " + tempTeuaUriTicker + " partyId " +
					tempQuoteTickerType.getParty().toString() +
					" counterPartyId " + tempQuoteTickerType.getCounterParty().toString() +
					" " + tempQuoteTickerType.getSubscriptionId().toString());
		}


		//Send update back to TEUA
		QuoteTickerType tempPostResponse = restTemplate.postForObject(tempTeuaUriTicker,
				tempQuoteTickerType,
				QuoteTickerType.class);

		return tempPostResponse;
	}





	public static EiTenderType getCurrentTender() {
		return currentTender;
	}

	public static void setCurrentTender(EiTenderType currentTender) {
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