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
import org.theenergymashuplab.cts.ActorIdType;
import org.theenergymashuplab.cts.EiResponse;
import org.theenergymashuplab.cts.EiTender;
import org.theenergymashuplab.cts.EiTransaction;
import org.theenergymashuplab.cts.LmeSendTransactions;
import org.theenergymashuplab.cts.LmeSocketClient;
import org.theenergymashuplab.cts.LmeSocketServer;
import org.theenergymashuplab.cts.TenderIdType;
import org.theenergymashuplab.cts.controller.payloads.EICanceledTenderPayload;
import org.theenergymashuplab.cts.controller.payloads.EiCancelTenderPayload;
import org.theenergymashuplab.cts.controller.payloads.EiCreateTenderPayload;
import org.theenergymashuplab.cts.controller.payloads.EiCreateTransactionPayload;
import org.theenergymashuplab.cts.controller.payloads.EiCreatedTenderPayload;
import org.theenergymashuplab.cts.dto.EiCanceledResponse;
import org.theenergymashuplab.cts.sbe.EiTenderEncoderDEcoder;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;

import org.agrona.concurrent.UnsafeBuffer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.List;

import baseline.*;

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
@RequestMapping("/lme")
public class LmeRestController {
	private static final AtomicLong counter = new AtomicLong();
	private static EiTender currentTender;
	private static EiTransaction currentTransaction;
	private static TenderIdType currentTenderId;
	// TODO assign in constructor?
	private static final ActorIdType partyId  = new ActorIdType();
	
	private static Boolean lmeSocketClientNotRunning = true;
	private static Boolean lmeSocketServerNotRunning = true;
	
	// queueFromLme is used by LmeSocketClient to accept CreateTenderPayload
	// queue.put here in LME, take in LmeSocketClient which connects to the market
	// Queue capacity is not an issue
	public static BlockingQueue<EiCreateTenderPayload> queueFromLme 
		= new ArrayBlockingQueue<EiCreateTenderPayload>(20);
	public static LmeSocketClient lmeSocketClient = new LmeSocketClient();
	
	// parallel for MarketCreateTransactionPayloads here in LME.
	// queue.put by LmeSocketServer, queue.take here in LME to produce an 
	// EiCreateTransactionPayload to be forwarded to LMA
	public static BlockingQueue<EiCreateTransactionPayload> eiCreateTransactionQueue =
			new ArrayBlockingQueue<EiCreateTransactionPayload>(20);
	public static LmeSocketServer lmeSocketServer = new LmeSocketServer();
	
	LmeSendTransactions lmeSendTransactionsThread = new LmeSendTransactions();
	
	/*
	 * HashMap to correlate CTS TenderIdType and EiCreateTenderPayload
	 * referenced by the MarketCreateTransaction
	 */
	// 	TODO count down original quantity from Transactions against the Tender;
	//	remove from map when zero
	// 	Need wrapper class Long to use the long ctsTenderId as returned
	public static HashMap<Long, EiCreateTenderPayload> ctsTenderIdToCreateTenderMap =
			new HashMap<Long, EiCreateTenderPayload>();

	
	private static final Logger logger = LogManager.getLogger(
			LmeRestController.class);
	
	  MessageHeaderDecoder messageHeaderDecoder = new MessageHeaderDecoder();
	  EiCreateTenderPayloadDecoder eiCreateTenderPayloadDecoder = new EiCreateTenderPayloadDecoder();
	  ByteBuffer bbf = ByteBuffer.allocate(4096);
	  UnsafeBuffer buffer = new UnsafeBuffer(bbf);
	  
	  MessageHeaderEncoder messageHeaderEncoder = new MessageHeaderEncoder();
	  EiCreatedTenderPayloadEncoder eiCreatedTenderPayloadEncoder = new EiCreatedTenderPayloadEncoder();
	
	  LmeRestController()	{
		logger.trace("LmeRestController zero arg constructor. partyId " + partyId);
		
		//	Start thread to read createTransactionQ and send
		lmeSendTransactionsThread.start();
		logger.debug("Starting lmeThread");
		
		
		// start market connection sockets
		if (lmeSocketClientNotRunning) {
			lmeSocketClientNotRunning = false;
			lmeSocketClient.start();
			logger.debug("Starting lmeSocketClient");
			
		}
		
		if (lmeSocketServerNotRunning)	{
			lmeSocketServerNotRunning = false;
			lmeSocketServer.start();
			logger.debug("Starting lmeSocketServer");
		}
	}
	
	/*
	 * GET - /lme/party responds with PartyId
	 */
	@GetMapping("/party")
	public ActorIdType getParty() {
		return LmeRestController.partyId;
	}
	
	
	/*
	 * POST - /createTender
	 * 		RequestBody is EiCreateTenderPayload from LMA
	 * 		ResponseBody is EiCreatedTenderPayload
	 */
	
	@PostMapping("/createTender")
	public byte[] postEiCreateTender(
			@RequestBody byte[] eiCreateTenderByteArr) throws Exception	{
		EiTender tempTender;
		EiCreateTenderPayload tempCreate = null;
		EiCreateTenderPayload mapPutReturnValue = null;
		EiCreatedTenderPayload tempCreated;
		Boolean addQsuccess = false;
		
		//tempCreate = eiCreateTender;
		//tempTender = eiCreateTender.getTender();
		
		//Decode EiCreateTenderPayload
				int bufferOffset_lengthToRead = messageHeaderDecoder.encodedLength();
				buffer.putBytes(0, eiCreateTenderByteArr, 0, bufferOffset_lengthToRead);
				messageHeaderDecoder.wrap(buffer, 0);

				//We have got the id, Now based on ID we will use correct decoder
				int templateId = messageHeaderDecoder.templateId();

				//Length encoded message
				int actingBlockLength = messageHeaderDecoder.blockLength();


				//Length encoded message
				int actingVersion = messageHeaderDecoder.version();


				buffer.putBytes(0, eiCreateTenderByteArr, bufferOffset_lengthToRead, actingBlockLength);

				EiCreateTenderPayload EiCreateTenderResponse = EiTenderEncoderDEcoder.eiCreateTenderPayloadDecode(eiCreateTenderPayloadDecoder, buffer, bufferOffset_lengthToRead, actingBlockLength, actingVersion);

		//logger.debug("LmeController before constructor for EiCreatedTender " +tempTender.toString());
		logger.debug("lme/createTender " + EiCreateTenderResponse.toString());
		
		/*	ResponseBody
			public EiCreatedTender(
				TenderId tenderId,
				ActorId partyId,queueF
				EiResponse response)
		 */
		
		// Forward to market
		// Conversion to MarketCreateTenderPayload is in LmeSocketClient here
		// TODO Non-blocking add returns true if OK, false if queue is full
		
		// TODO switch .add() to blocking .take() after verification
		addQsuccess = queueFromLme.add(EiCreateTenderResponse);
		logger.debug("queueFomLme addQsuccess " + addQsuccess +
				" TenderId " + EiCreateTenderResponse.getTender().getTenderId());
		
		// put EiCreateTenderPayload in map to build EiCreateTransactionPayload
		// from MarketCreateTransaction
		mapPutReturnValue = ctsTenderIdToCreateTenderMap.put(EiCreateTenderResponse.getTender().getTenderId().value(),
				EiCreateTenderResponse);	
		
		if (mapPutReturnValue == null) {
			logger.debug("mapPutReturnValue is null - new entry");
		}	else	{
			logger.debug("mapPutReturnValue non-null - previous entry " + mapPutReturnValue.toString());
		}
		
		
		// Decouple orderEntered insertion from market with immediate return to LMA
		//	TODO consider return value if value already in map
		tempCreated = new EiCreatedTenderPayload(EiCreateTenderResponse.getTender().getTenderId(),
				EiCreateTenderResponse.getPartyId(),
				EiCreateTenderResponse.getCounterPartyId(),
				new EiResponse(200, "OK"));
		
		int encodingLengthPlusHeader = EiTenderEncoderDEcoder.eiCreatedTenderEncode(eiCreatedTenderPayloadEncoder, buffer, messageHeaderEncoder, tempCreated);
		
		return buffer.byteArray();
	}
	
	
	/*
	 *	POST EiCreateTransaction to LMA
	 *
	 *	is in LmeSendTransaction
	 */
	
	/*
	 * POST - /cancelTender
	 * 		RequestBody is EiCancelTender from TEUA/Client by way of LMA
	 * 		ResponseBody is EiCanceledTender
	 */
	@PostMapping("/cancelTender")
	public EICanceledTenderPayload postEiCancelTenderPayload(
			@RequestBody EiCancelTenderPayload eiCancelTenderPayload)	{
		List<TenderIdType> tempTenderIDs;
		EiCancelTenderPayload tempCancel;	
		EICanceledTenderPayload tempCanceled;
		
		tempCancel = eiCancelTenderPayload;
		tempTenderIDs = eiCancelTenderPayload.getTenderIDs();

//		tempCancel.print();	// DEBUG
		
		tempCanceled = new EICanceledTenderPayload(
				tempCancel.getPartyId(),
				tempCancel.getCounterPartyId(),
				new EiResponse(200, "OK"),
				new EiCanceledResponse());
		
		return tempCanceled;
	}
	

	public static BlockingQueue<EiCreateTenderPayload> getQueueFromLme() {
		return queueFromLme;
	}

	public static void setQueueFromLme(BlockingQueue<EiCreateTenderPayload> queueFromLme) {
		LmeRestController.queueFromLme = queueFromLme;
	}


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


	public static TenderIdType getCurrentTenderId() {
		return currentTenderId;
	}


	public static void setCurrentTenderId(TenderIdType currentTenderId) {
		LmeRestController.currentTenderId = currentTenderId;
	}


	public static Boolean getLmeSocketClientNotRunning() {
		return lmeSocketClientNotRunning;
	}


	public static void setLmeSocketClientNotRunning(Boolean lmeSocketClientNotRunning) {
		LmeRestController.lmeSocketClientNotRunning = lmeSocketClientNotRunning;
	}


	public static LmeSocketClient getLmeSocketClient() {
		return lmeSocketClient;
	}


	public static void setLmeSocketClient(LmeSocketClient lmeSocketClient) {
		LmeRestController.lmeSocketClient = lmeSocketClient;
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
