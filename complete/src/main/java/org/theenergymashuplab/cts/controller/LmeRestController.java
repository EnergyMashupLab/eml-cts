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

import org.springframework.boot.rsocket.server.RSocketServer.Transport;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
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
	//Quote and tender tickers
	private static QuoteTickerType quoteTicker = new QuoteTickerType();
	private static TenderTickerType tenderTicker = new TenderTickerType();
	//Default arraylist that will hold all of our quotes
	//For higher performance consider an alternate data structure such as a hash table
	private static HashMap<Integer, EiQuoteType> currentQuotes = new HashMap<>();


	// TODO assign in constructor?
	private static final ActorIdType partyId  = new ActorIdType();
	
	private static Boolean lmeSocketClientNotRunning = true;
	private static Boolean lmeSocketServerNotRunning = true;
	
	// queueFromLme is used by LmeSocketClient to accept CreateTenderPayload
	// queue.put here in LME, take in LmeSocketClient which connects to the market
	// Queue capacity is not an issue
	public static BlockingQueue<EiCreateTenderPayload> queueFromLme = new ArrayBlockingQueue<EiCreateTenderPayload>(200);

	public static BlockingQueue<EiCreateQuotePayload> queueQuoteFromLme = new ArrayBlockingQueue<EiCreateQuotePayload>(200);

	public static LmeSocketClient lmeSocketClient = new LmeSocketClient();
	
	// parallel for MarketCreateTransactionPayloads here in LME.
	// queue.put by LmeSocketServer, queue.take here in LME to produce an 
	// EiCreateTransactionPayload to be forwarded to LMA
	public static BlockingQueue<EiCreateTransactionPayload> eiCreateTransactionQueue =
			new ArrayBlockingQueue<EiCreateTransactionPayload>(200);
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
		//Set the tender Ticker
		//TODO not fully implemented
		tenderTicker.setTender(tempTender);
		
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
			synchronized(currentQuotes){
				currentQuotes.put(tempQuote.hashCode(), tempQuote);
			}

			/**
		 	* Synchronized for possibility to multithreading
		 	*/
			synchronized(quoteTicker){
				quoteTicker.setQuote(tempQuote);
				//TODO send out subscription update here
			}

			//Temporary storage, NOT the same as overall one
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
		//Currently not in use
		response.setInResponseTo(new RefIdType());
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
		
		//Grab the quote payload and quote itself
		tempCreate = eiCreateQuote;
		tempQuote = eiCreateQuote.getQuote();

		logger.debug("LmeController before constructor for EiCreatedQuote " +
				tempQuote.toString());
		logger.debug("lme/createQuote " + eiCreateQuote.toString());
		

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
			currentQuotes.put(tempQuote.hashCode(), tempQuote);
		}

		/**
		 * Synchronized for possibility to multithreading
		 */
		synchronized(quoteTicker){
			quoteTicker.setQuote(tempQuote);
			//TODO send out subscription update here
		}

		tempCreated = new EiCreatedQuotePayload(tempCreate.getCounterPartyId(),
												tempQuote.getMarketOrderId(),
												tempCreate.getPartyId(),
												tempQuote.getQuoteId(),
												new EiResponse(200, "OK"));

		tempCreated.setCounterPartyId(tempCreated.getCounterPartyId());
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
		//These quotes will be used for the list/quote grabbing
		EiQuoteType tempQuote = new EiQuoteType();
		EiQuoteType listQuote = new EiQuoteType();

		//We'll need two of these, one for buyer one for seller
		EiCreateTransactionPayload buyerTransaction = new EiCreateTransactionPayload();
		EiCreateTransactionPayload sellerTransaction = new EiCreateTransactionPayload();

		//Grab the transaction ID
		TransactionIdType transactionId = eiAcceptQuote.getTransaction().getTransactionId();

		//Dummy for our POST responses
		EiCreatedTransactionPayload tempCreated;
		
		//To be filled out by JSON
		EiAcceptQuotePayload tempAccept;

		//This will be sent back to the sending party
		EiAcceptedQuotePayload response = new EiAcceptedQuotePayload();

		//The buyer and seller both see
		EiTransaction tempTransaction;

		//This tender will eventually be sent to the buyer
		EiTenderType buyerTender = new EiTenderType();
		buyerTender.setSide(SideType.BUY);

		//This tender will eventually be sent to the seller 
		EiTenderType sellerTender = new EiTenderType();
		sellerTender.setSide(SideType.SELL);
		
		//We will send this back whenever we have an issue
		EiTenderType badBuyerTender = new EiTenderType();
		//This is how we will signify it as bad
		badBuyerTender.setTenderDetail(new TenderIntervalDetail(new Interval(), -1, -1));
		badBuyerTender.setSide(SideType.BUY);
	
		//We will send this back whenever we have an issue
		EiTenderType badSellerTender = new EiTenderType();
		//This is how we will signify it as bad
		badSellerTender.setTenderDetail(new TenderIntervalDetail(new Interval(), -1, -1));
		badSellerTender.setSide(SideType.SELL);


		//For sendinng back to LMA
		final RestTemplateBuilder builder = new RestTemplateBuilder();
		RestTemplate restTemplate;
		restTemplate = builder.build();
		
		//For later on
		boolean exists = false;
		
		//Grab the quote payload and quote itself
		tempAccept = eiAcceptQuote;

		//Grab this for convenience
		tempTransaction = tempAccept.getTransaction();

		//Set this for us to search
		tempQuote.setMarketOrderId(tempAccept.getReferencedQuoteId());

		/**
		 * The buyer is the party the seller is the counterparty
		 */
		buyerTransaction.setPartyId(tempAccept.getPartyId());
		buyerTransaction.setCounterPartyId(tempAccept.getCounterPartyId());
		buyerTransaction.setMarketTransactionId(transactionId);
		buyerTransaction.setRequestId(new RefIdType());

		/**
		 * For the message we will send the seller, these will be flipped
		 */
		sellerTransaction.setPartyId(tempAccept.getCounterPartyId());
		sellerTransaction.setCounterPartyId(tempAccept.getPartyId());
		sellerTransaction.setMarketTransactionId(transactionId);
		sellerTransaction.setRequestId(new RefIdType());

		//This will be the same no matter what here
		response.setTransactionId(transactionId);

		//Synchronized because this can be multithreaded
		synchronized(currentQuotes){
			//If we can't find a quote here, that's the end for us
			if(currentQuotes.containsKey(tempQuote.hashCode()) == false){
				System.out.println("Quote with:" + tempQuote.getMarketOrderId().toString() + " does not exist. ERROR");
				//Log it
				logger.debug("LMEController did not find quote for EiAcceptedQuote: " + tempQuote.getMarketOrderId().toString() + 
							"will now exit");

				//Set a bad response to send out
				response.setResponse(new EiResponse(500, "Referenced Quote ID does not exist in the QDM"));

				//Set the transactions here as bad so that the recipients know
				buyerTransaction.setTransaction(new EiTransaction(badBuyerTender));
				sellerTransaction.setTransaction(new EiTransaction(badSellerTender));

				//Log it
				logger.trace("Quote not found");

			//If we get here, we know that we have the quote so we will act upon it
			} else {
				listQuote = currentQuotes.get(tempQuote.hashCode());
				System.out.println(listQuote);
				//Grab the quantity
				long quoteQuantity = ((TenderIntervalDetail)listQuote.getTenderDetail()).getQuantity();
				//Grab the price
				long quotePrice = ((TenderIntervalDetail)listQuote.getTenderDetail()).getPrice();
				//Grab the tender from the premade transaction
				EiTenderType tempTender = tempAccept.getTransaction().getTender();
				System.out.println("TENDER IS:"+tempTender);
				//Grab the accept quantity
				long tempQuantity = ((TenderIntervalDetail)tempTender.getTenderDetail()).getQuantity();
				//Grab the accept price
				long tempPrice = ((TenderIntervalDetail)tempTender.getTenderDetail()).getPrice();

				/**
				 * There are two areas where we could have an issue here:
				 * 		1.) The Accepter is asking for more than the current quantity
				 * 		2.) The Accepter is bidding a lower price than the current price
				 * 	On each of these cases, we will send a blank eiAccepted quote as it failed
				 */
				if(tempQuantity > quoteQuantity || tempPrice < quotePrice){
					System.out.println("Quote will be rejected due to bad quantity/price");
					//Flag that this is bad with a bad response
					response.setResponse(new EiResponse(500, "Quote not accepted due to price/quantity mismatch"));

					//Set the transactions here as bad so that the recipients know
					buyerTransaction.setTransaction(new EiTransaction(badBuyerTender));
					sellerTransaction.setTransaction(new EiTransaction(badSellerTender));


				} else {
					//Update the quantity that we currently have available
					((TenderIntervalDetail)listQuote.getTenderDetail()).setQuantity(quoteQuantity - tempQuantity);

					//DEBUG
					System.out.println("Quote will be LIFTED");
					System.out.println("After transaction: " + listQuote.toString());

					//Set this just for buyer info
					buyerTender.setExpirationTime(listQuote.getExpirationTime());
					sellerTender.setExpirationTime(listQuote.getExpirationTime());

					//Set the tender detail for ourselves
					buyerTender.setTenderDetail(listQuote.getTenderDetail());
					sellerTender.setTenderDetail(listQuote.getTenderDetail());
					
					//Set the transactions
					buyerTransaction.setTransaction(new EiTransaction(buyerTender));
					sellerTransaction.setTransaction(new EiTransaction(sellerTender));

					//Make a new return value with the created Quotes
					logger.trace("Quote Accepted");
				}
			}
		}

		/**
		 * At this point the QDM will send out two EiCreateTransaction Payloads 
		 * containing the transaction tender to both the party and counterparty
		 */
		//Send the buyer transaction out to be handled by LMA
		restTemplate.postForObject("http://localhost:8080/lma/createTransaction", 
				buyerTransaction, 
				EiCreatedTransactionPayload.class);

		//Send the seller transaction out to be handled by LMA
		restTemplate.postForObject("http://localhost:8080/lma/createTransaction", 
				sellerTransaction, 
				EiCreatedTransactionPayload.class);

		//These ID's will be set for the response
		response.setPartyId(tempAccept.getPartyId());
		response.setCounterPartyId(tempAccept.getCounterPartyId());

		/**
		 * The QDM sends an EiAcceptedQuotePayload back to the original sending party
		 */
		return response;
	}


	@PostMapping("/manageSubscription")
	public EiManagedTickerSubscriptionPayload postEiManagedTicker(
			@RequestBody EiManageTickerSubscriptionPayload eiManageTickerSubscriptionPayload)	{
		TickerType tempTickerType;
		EiResponseType tempResponse;
		SubscriptionActionType tempSubscriptionActionTaken;
		RefIdType tempSubscriptionRequestId;
		EiManageTickerSubscriptionPayload tempSubscribe = null;
		EiManagedTickerSubscriptionPayload tempSubscribed;

		tempSubscribe = eiManageTickerSubscriptionPayload;
		tempTickerType = eiManageTickerSubscriptionPayload.getTickerType();
		tempSubscriptionActionTaken = eiManageTickerSubscriptionPayload.getSubscriptionActionRequested();
		tempSubscriptionRequestId = eiManageTickerSubscriptionPayload.getSubscriptionRequestId();


		/* TODO Not conforming with March 2024 spec. The market (parity) is where the market order id should come from
		 * Currently, there's no way to retrieve the market order id of a tender after it has been submitted.
		 * The only place where parity sends back it's assigned market order id is after the tender has been matched
		 * with a different tender, leading to a transaction
		 *
		 * In short, this isn't where the market order id should be set, it should be retrieved from parity */

		tempSubscribed = new EiManagedTickerSubscriptionPayload("Multicast session started successfully",
				tempSubscriptionActionTaken,
				new EiResponseType(),
				//new EiResponse(200, "OK"),
				tempSubscriptionRequestId, tempTickerType);


		return tempSubscribed;
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
