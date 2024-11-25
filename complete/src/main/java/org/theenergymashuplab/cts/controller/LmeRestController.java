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
import org.theenergymashuplab.cts.*;
import org.theenergymashuplab.cts.controller.payloads.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.concurrent.*;
import java.awt.JobAttributes.SidesType;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.HashMap;

@RestController
@RequestMapping("/lme")
public class LmeRestController {
	private static final AtomicLong counter = new AtomicLong();
	private static EiTenderType currentTender;
	private static EiTransaction currentTransaction;
	private static TenderIdType currentTenderId;
	//Default arraylist that will hold all of our quotes
	//For higher performance consider an alternate data structure such as a hash table
	//private static List<EiQuoteType> currentQuotes = Collections.synchronizedList(new ArrayList<>());
	private static List<EiQuoteType> currentQuotes = new ArrayList<>();


	// TODO assign in constructor?
	private static final ActorIdType partyId  = new ActorIdType();

	private static Boolean lmeSocketClientNotRunning = true;
	private static Boolean lmeSocketServerNotRunning = true;

	// queueFromLme is used by LmeSocketClient to accept CreateTenderPayload
	// queue.put here in LME, take in LmeSocketClient which connects to the market
	// Queue capacity is not an issue
	public static BlockingQueue<EiCreateTenderPayload> queueFromLme = new LinkedBlockingQueue<EiCreateTenderPayload>();

	public static BlockingQueue<EiCreateQuotePayload> queueQuoteFromLme = new LinkedBlockingQueue<EiCreateQuotePayload>();

	public static LmeSocketClient lmeSocketClient = new LmeSocketClient();

	// parallel for MarketCreateTransactionPayloads here in LME.
	// queue.put by LmeSocketServer, queue.take here in LME to produce an
	// EiCreateTransactionPayload to be forwarded to LMA
	public static BlockingQueue<EiCreateTransactionPayload> eiCreateTransactionQueue =
			new LinkedBlockingQueue<EiCreateTransactionPayload>();
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
	public EiCreatedTenderPayload 	postEiCreateTender(
			@RequestBody EiCreateTenderPayload eiCreateTender)	{
		EiTenderType tempTender;
		EiCreateTenderPayload tempCreate = null;
		EiCreateTenderPayload mapPutReturnValue = null;
		EiCreatedTenderPayload tempCreated;
		Boolean addQsuccess = false;

		tempCreate = eiCreateTender;
		tempTender = eiCreateTender.getTender();

		logger.debug("LmeController before constructor for EiCreatedTender " +
				tempTender.toString());
		logger.debug("lme/createTender " + eiCreateTender.toString());

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
		addQsuccess = queueFromLme.add(tempCreate);
		logger.debug("queueFomLme addQsuccess " + addQsuccess +
				" TenderId " + tempTender.getTenderId());

		/* TODO Not conforming with March 2024 spec. The market (parity) is where the market order id should come from
		 * Currently, there's no way to retrieve the market order id of a tender after it has been submitted.
		 * The only place where parity sends back it's assigned market order id is after the tender has been matched
		 * with a different tender, leading to a transaction
		 *
		 * In short, this isn't where the market order id should be set, it should be retrieved from parity */
		tempTender.setMarketOrderId(new MarketOrderIdType());

		// put EiCreateTenderPayload in map to build EiCreateTransactionPayload
		// from MarketCreateTransaction
		mapPutReturnValue = ctsTenderIdToCreateTenderMap.put(tempCreate.getTender().getTenderId().value(),
				tempCreate);

		// Decouple orderEntered insertion from market with immediate return to LMA
		//	TODO consider return value if value already in map
		tempCreated = new EiCreatedTenderPayload(tempTender.getTenderId(),
				tempCreate.getPartyId(),
				tempCreate.getCounterPartyId(),
				new EiResponse(200, "OK"),
				tempCreate.getRequestId());

		return tempCreated;
	}


	@PostMapping("/createStreamTender")
	public EiCreatedStreamTenderPayload postEiCreateStreamTender(
			@RequestBody EiCreateStreamTenderPayload eiCreateStreamTenderPayload){
		EiCreateStreamTenderPayload tempCreateStreamTenderPayload;
		EiCreatedStreamTenderPayload response = new EiCreatedStreamTenderPayload();
		EiCreateTenderPayload tempCreate;
		EiTenderType tempTender;
		TenderIntervalDetail tenderDetail;
		BridgeInterval currentStartInterval;
		BridgeInstant currentStartInstant = new BridgeInstant();
		List<Long> createdTenders = new ArrayList<>();
		TenderStreamDetail tenderStreamDetail;
		ActorIdType partyID;
		ActorIdType counterPartyID;
		CtsStreamType stream;
		Boolean addQSuccess = false;

		//Deserialize from the request
		tempCreateStreamTenderPayload = eiCreateStreamTenderPayload;

		//Grab the stream for us to use
		tenderStreamDetail = (TenderStreamDetail)tempCreateStreamTenderPayload.getTender().getTenderDetail();
		stream = tenderStreamDetail.getStream();
		partyID = tempCreateStreamTenderPayload.getPartyId();
		counterPartyID = tempCreateStreamTenderPayload.getCounterPartyId();

		//Debug what we got
		logger.debug("lme/createStreamTender " + tempCreateStreamTenderPayload.toString());

		/* ================ Forwarding to the market ====================== */

		/**
		 * Design of this component:
		 * 	In the CTS market, stream tenders do not exist. They are simply a client-side semantic that allows clients to construct a stream tender
		 * 	specifying a stream of resource purchases or sales. Stream tenders themselves are just a sequence of separate tenders with
		 * 	different prices and quantities, arranged in sequential intervals of the same length. So, we can leverage the existing
		 * 	architecture around creating tenders to generate a sequence of createTender requests according to the prices and intervals
		 * 	outlined in the stream tender object. This may later be changed with the implementation of "allOrNone", but currently, it serves our
		 * 	purposes
		 */

		//Construct the bridge interval
		BridgeInterval startInterval = new BridgeInterval(tenderStreamDetail.getIntervalDurationInMinutes(), stream.getStreamStart());
		currentStartInterval = startInterval;

		//So for each interval the we have in the stream intervals
		for(CtsStreamIntervalType interval : stream.getStreamIntervals()){
			//Create the individual Tender Interval payload
			tenderDetail = new TenderIntervalDetail(currentStartInterval.asInterval(), interval.getStreamIntervalPrice(), interval.getStreamIntervalQuantity());

			//Advance the interval by however many minutes we specify
			currentStartInstant.setInstant(currentStartInterval.getDtStart().asInstant().plusSeconds(tenderStreamDetail.getIntervalDurationInMinutes()*60));
			//Set the current start interval
			currentStartInterval.setDtStart(currentStartInstant);
			//Create the new individual interval tender
			tempTender = new EiTenderType(tempCreateStreamTenderPayload.getTender().getExpirationTime(), tempCreateStreamTenderPayload.getTender().getSide(), tenderDetail);

			//Construct the EiCreateTender payload to be forwarded to LMA
			tempCreate = new EiCreateTenderPayload(tempTender, partyID, counterPartyID);

			//set party and counterParty -partyId saved in actorIds, counterParty is lmePartyId
			tempCreate.setPartyId(partyID);
			tempCreate.setCounterPartyId(counterPartyID);

			System.out.println(tempCreate.toString());

			addQSuccess = queueFromLme.add(tempCreate);
			logger.debug("queueFomLme addQsuccess " + addQSuccess +
					" TenderId " + tempTender.getTenderId());

			//Grab the tender ID and store
			createdTenders.add(tempTender.getTenderId().value());
		}

		//Make a new return value with the created tenders
		logger.trace("Stream Tender Creation Sequence: TEUA before return ClientCreatedTender to Client/SC " +
				createdTenders.toString());

		/* ================================================================ */

		response.setPartyId(partyID);
		response.setResponse(new EiResponse(200, "OK"));
		response.setCounterPartyId(counterPartyID);
		response.setCreatedTenders(createdTenders);

		return response;
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
		TenderIdType tempTenderId;
		EiCancelTenderPayload tempCancel;
		EICanceledTenderPayload tempCanceled;

		tempCancel = eiCancelTenderPayload;
		tempTenderId = eiCancelTenderPayload.getTenderId();

//		tempCancel.print();	// DEBUG

		EiCanceledResponseType eiCanceledResponse = new EiCanceledResponseType(
				CancelReasonType.REQUESTED,
				tempCancel.getMarketOrderId(),
				0,  // TODO Not up to March 2024 spec: Retrieve remaining quantity left once canceling tenders is implemented
				false  // TODO Not up to March 2024 spec: Change to true once canceling tenders has been implemented
		);

		tempCanceled = new EICanceledTenderPayload(
				tempCancel.getPartyId(),
				tempCancel.getCounterPartyId(),
				new EiResponse(200, "OK"),
				eiCanceledResponse,
				tempCancel.getRequestId());

		return tempCanceled;
	}



	@PostMapping("/createStreamQuote")
	public EiCreatedStreamQuotePayload postEiCreateStreamQuote(
			@RequestBody EiCreateStreamQuotePayload eiCreateStreamQuotePayload){
		EiCreateStreamQuotePayload tempCreateStreamQuotePayload;
		EiCreatedStreamQuotePayload response = new EiCreatedStreamQuotePayload();
		EiCreateQuotePayload tempCreate;
		EiQuoteType tempQuote;
		TenderIntervalDetail quoteDetail;
		BridgeInterval currentStartInterval;
		BridgeInstant currentStartInstant = new BridgeInstant();
		//A list of all of the created quotes
		List<EiQuoteType> createdQuotes = new ArrayList<>();
		TenderStreamDetail quoteStreamDetail;
		ActorIdType partyID;
		ActorIdType counterPartyID;
		CtsStreamType stream;
		Boolean addQSuccess = false;

		//Deserialize from the request
		tempCreateStreamQuotePayload = eiCreateStreamQuotePayload;

		//Grab the stream for us to use
		quoteStreamDetail = (TenderStreamDetail)tempCreateStreamQuotePayload.getQuote().getTenderDetail();

		stream = quoteStreamDetail.getStream();
		partyID = tempCreateStreamQuotePayload.getPartyId();
		counterPartyID = tempCreateStreamQuotePayload.getCounterPartyId();

		//Debug what we got
		logger.debug("lme/createStreamQuote " + tempCreateStreamQuotePayload.toString());

		/* ================ Forwarding to the market ====================== */

		/**
		 * Design of this component:
		 * 	In the CTS market, stream Quotes do not exist. They are simply a client-side semantic that allows clients to construct a stream Quote
		 * 	specifying a stream of resource purchases or sales. Stream Quotes themselves are just a sequence of separate Quotes with
		 * 	different prices and quantities, arranged in sequential intervals of the same length. So, we can leverage the existing
		 * 	architecture around creating Quotes to generate a sequence of createQuote requests according to the prices and intervals
		 * 	outlined in the stream Quote object. This may later be changed with the implementation of "allOrNone", but currently, it serves our
		 * 	purposes
		 */

		//Construct the bridge interval
		BridgeInterval startInterval = new BridgeInterval(quoteStreamDetail.getIntervalDurationInMinutes(), stream.getStreamStart());
		currentStartInterval = startInterval;

		//So for each interval the we have in the stream intervals
		for(CtsStreamIntervalType interval : stream.getStreamIntervals()){
			//Create the individual Quote Interval payload
			quoteDetail = new TenderIntervalDetail(currentStartInterval.asInterval(), interval.getStreamIntervalPrice(), interval.getStreamIntervalQuantity());

			//Advance the interval by however many minutes we specify
			currentStartInstant.setInstant(currentStartInterval.getDtStart().asInstant().plusSeconds(quoteStreamDetail.getIntervalDurationInMinutes()*60));
			//Set the current start interval
			currentStartInterval.setDtStart(currentStartInstant);
			//Create the new individual interval Quote
			tempQuote = new EiQuoteType(tempCreateStreamQuotePayload.getQuote().getExpirationTime(), tempCreateStreamQuotePayload.getQuote().getSide(), quoteDetail);
			//FIXME later on-- forced to be 1 now
			tempQuote.setMarketOrderId(new MarketOrderIdType(1));

			/**
			 * Remember here: a quote IS a tender and inherits from parent TenderBase
			 */

			//Set the marketOrderID here
			tempQuote.setMarketOrderId(new MarketOrderIdType());
			//No execution instructions
			tempQuote.setExecutionInstructions(null);
			//Not private -- we want to publish
			tempQuote.setPrivateQuote(false);
			//Always energy for right now
			tempQuote.setResourceDesignator(ResourceDesignatorType.ENERGY);
			//The quote is tradeable
			tempQuote.setTradeable(true);

			//Add this into the current quotes arraylist
			//currentQuotes contains all of the quotes created
			currentQuotes.add(tempQuote);

			//Add this into the big list of all created quotes
			createdQuotes.add(tempQuote);
		}

		//Make a new return value with the created Quotes
		logger.trace("Stream Quote Creation Sequence: TEUA before return ClientCreatedQuote to Client/SC " +
				createdQuotes.toString());

		/* ================================================================ */

		response.setPartyId(partyID);
		response.setResponse(new EiResponse(200, "Stream Quote Creation Succeeded"));
		response.setCounterPartyId(counterPartyID);
		response.setCreatedQuotes(createdQuotes);
		return response;
	}

	/*
	 * POST - /createQuote
	 * 		RequestBody is EiCreateQuotePayload from LMA
	 * 		ResponseBody is EiCreatedQuotePayload
	 *
	 * Methodology:
	 * 	Quotes never make it into the market. They are essentially tenders, but instead of
	 * 	being forwarded to market, they are stored locally here in an arrayList and acted
	 *  upon entirely in this class
	 */

	@PostMapping("/createQuote")
	public EiCreatedQuotePayload postEiCreateQuote(
			@RequestBody EiCreateQuotePayload eiCreateQuote)	{
		EiQuoteType tempQuote;
		EiCreateQuotePayload tempCreate = null;
		EiCreatedQuotePayload tempCreated;
		boolean addSuccess = false;

		//Grab the quote payload and quote itself
		tempCreate = eiCreateQuote;
		tempQuote = eiCreateQuote.getQuote();

		logger.debug("LmeController before constructor for EiCreatedQuote " +
				tempQuote.toString());
		logger.debug("lme/createQuote " + eiCreateQuote.toString());

		/*	ResponseBody
			public EiCreatedTender(
				TenderId tenderId,
				ActorId partyId,queueF
				EiResponse response)
		 */


		//Save the queue in our arraylist

		//logger.debug("queueFomLme addQsuccess " + addQsuccess +
		//		" TenderId " + tempTender.getTenderId());

		/* TODO Not conforming with March 2024 spec. The market (parity) is where the market order id should come from
		 * Currently, there's no way to retrieve the market order id of a tender after it has been submitted.
		 * The only place where parity sends back it's assigned market order id is after the tender has been matched
		 * with a different tender, leading to a transaction
		 *
		 * In short, this isn't where the market order id should be set, it should be retrieved from parity */
		tempQuote.setMarketOrderId(new MarketOrderIdType(1));
		System.out.println("Added quote with order ID: " + tempQuote.getMarketOrderId());
		//Add this quote into the volatile storage. It will never hit the database
		//currentQuotes is an arraylist containing all quotes. Since we may be multithreaded here, we will
		//lock
		synchronized(currentQuotes){
			currentQuotes.add(tempQuote);
		}

		tempCreated = new EiCreatedQuotePayload(tempCreate.getCounterPartyId(),
				tempQuote.getMarketOrderId(),
				tempCreate.getPartyId(),
				tempQuote.getQuoteId(),
				new EiResponse(200, "OK"));

		//FIXME later on
		tempCreated.setInResponseTo(new RefIdType());
		//Make a new return value with the created Quotes
		logger.trace("Quote Created");

		return tempCreated;
	}


	/**
	 * Accept a quote and make the underlying transaction happen
	 */
	@PostMapping("/acceptQuote")
	public EiAcceptedQuotePayload postEiAcceptQuote(
			@RequestBody EiAcceptQuotePayload eiAcceptQuote)	{
		EiQuoteType tempQuote = new EiQuoteType();
		EiQuoteType listQuote = new EiQuoteType();
		EiAcceptQuotePayload tempAccept = null;
		EiAcceptedQuotePayload sellerAccepted;
		EiAcceptedQuotePayload buyerAccepted;
		EiTenderType transactionTender;

		//These dummy transactions with negative IDs will be given back if we have a
		//failed transaction.
		//TODO this may need to be changed later, but it is the best way that I can think of to
		//indicate that an Accept quote failed
		TransactionIdType tempTransactionID = new TransactionIdType();
		tempTransactionID.setMyUidId(-1);
		TransactionIdType tempTransactionID2 = new TransactionIdType();
		tempTransactionID2.setMyUidId(-1);

		//In anticipation, we'll need a transaction here
		boolean exists = false;

		//Grab the quote payload and quote itself
		tempAccept = eiAcceptQuote;
		//Set this for us to search
		tempQuote.setMarketOrderId(tempAccept.getReferencedQuoteId());

		//We can create these now before searching
		buyerAccepted = new EiAcceptedQuotePayload();
		buyerAccepted.setPartyId(tempAccept.getPartyId());
		buyerAccepted.setCounterPartyId(tempAccept.getCounterPartyId());

		//Synchronized because this can be multithreaded
		synchronized(currentQuotes){
			//Search through our list
			for(int i = 0; i < currentQuotes.size(); i++){
				//Grab the quote at the current index
				listQuote = currentQuotes.get(i);
				//DEBUG statement comment out when done
				System.out.println("In list: " + listQuote.getMarketOrderId());
				if(listQuote.getMarketOrderId().getMyUidId() == tempQuote.getMarketOrderId().getMyUidId()){
					logger.debug("LMEController successfully found quote for EiAcceptedQuote: " + tempQuote.getMarketOrderId().toString());
					//DEBUG statement comment out
					exists = true;
					break;
				}
			}
			//If we can't find a quote here, that's the end for us
			if(!exists){
				System.out.println("Quote with:" + tempQuote.getMarketOrderId().toString() + " does not exist. ERROR");
				logger.debug("LMEController did not find quote for EiAcceptedQuote: " + tempQuote.getMarketOrderId().toString() +
						"will now exit");
				buyerAccepted.setResponse(new EiResponse(500, "Referenced Quote ID does not exist in the QDM"));


				//Currently here we'll just set these transactions to have an ID of -1(bad)
				buyerAccepted.setTransactionId(tempTransactionID);
				buyerAccepted.setRecipientTransactionId(tempTransactionID2);

				//Log it
				logger.trace("Quote not found");

				//If we get here, we know that we have the quote so we will act upon it
			} else {
				//Grab the quantity
				long quoteQuantity = ((TenderIntervalDetail)listQuote.getTenderDetail()).getQuantity();
				//Grab the price
				long quotePrice = ((TenderIntervalDetail)listQuote.getTenderDetail()).getPrice();
				//Grab the tender from the premade transaction
				transactionTender = tempAccept.getTransaction().getTender();
				System.out.println("TENDER IS:"+transactionTender);
				//Grab the accept quantity
				long tempQuantity = ((TenderIntervalDetail)transactionTender.getTenderDetail()).getQuantity();
				//Grab the accept price
				long tempPrice = ((TenderIntervalDetail)transactionTender.getTenderDetail()).getPrice();

				/**
				 * There are two areas where we could have an issue here:
				 * 		1.) The Accepter is asking for more than the current quantity
				 * 		2.) The Accepter is bidding a lower price than the current price
				 * 	On each of these cases, we will send a blank eiAccepted quote as it failed
				 */
				if(tempQuantity > quoteQuantity || tempPrice < quotePrice){
					System.out.println("Quote will be rejected due to bad quantity/price");
					//Currently here we'll just set these transactions to have an ID of -1(bad)
					buyerAccepted.setTransactionId(tempTransactionID);
					buyerAccepted.setRecipientTransactionId(tempTransactionID2);
					buyerAccepted.setResponse(new EiResponse(500, "Quote not accepted due to price/quantity mismatch"));
					//And we'll get out
					return buyerAccepted;
				}

				//If we are buying the whole thing we'll have to delete it
				if(quoteQuantity == tempQuantity){
					currentQuotes.remove(listQuote);
				} else {
					//Update the quantity that we currently have available
					((TenderIntervalDetail)listQuote.getTenderDetail()).setQuantity(quoteQuantity - tempQuantity);
				}

				//DEBUG
				System.out.println("Quote will be LIFTED");
				System.out.println("After transaction: " + listQuote.toString());

				//We are buying
				transactionTender.setSide(SideType.BUY);
				//Set this just for buyer info
				transactionTender.setExpirationTime(listQuote.getExpirationTime());


				//Make a new return value with the created Quotes
				logger.trace("Quote Accepted");
			}
		}
		//Set the transactionID
		buyerAccepted.setTransactionId(tempAccept.getTransaction().getTransactionId());

		//Set the transaction ID here
		return buyerAccepted;
	}

	public static BlockingQueue<EiCreateTenderPayload> getQueueFromLme() {
		return queueFromLme;
	}

	public static void setQueueFromLme(BlockingQueue<EiCreateTenderPayload> queueFromLme) {
		LmeRestController.queueFromLme = queueFromLme;
	}

	public static BlockingQueue<EiCreateQuotePayload> getQueueQuoteFromLme() {
		return queueQuoteFromLme;
	}

	public static void setQueueQuoteFromLme(BlockingQueue<EiCreateQuotePayload> queueQuoteFromLme) {
		LmeRestController.queueQuoteFromLme = queueQuoteFromLme;
	}


	public static EiTenderType getCurrentTender() {
		return currentTender;
	}


	public static void setCurrentTender(EiTenderType currentTender) {
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