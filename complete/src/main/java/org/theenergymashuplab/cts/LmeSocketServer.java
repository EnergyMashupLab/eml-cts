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

package org.theenergymashuplab.cts;


import java.net.*;
import java.io.*;
import java.lang.Thread;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.concurrent.ConcurrentHashMap;

// Not in Parity
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.theenergymashuplab.cts.controller.LmeRestController;
import org.theenergymashuplab.cts.controller.payloads.EiCreateTenderPayload;
import org.theenergymashuplab.cts.controller.payloads.EiCreateTransactionPayload;
import org.theenergymashuplab.cts.controller.payloads.MarketCreateTransactionPayload;


/*
 * DIFFERS FROM PARITY VERSION
 * 
 * Overview:
 * 		This Socket Server accepts connections on LME_PORT:
 * 
 * 		The CtsBridge Socket Client in Parity opens the LME IP address on LME_PORT.
 * 
 * 		Client (CtsBridge) writes JSON-serialized MarketCreateTenderPayloads which
 * 		are read by this CtsSockerServer, deserialized, and put on
 * 		bridge.createTenderQ for further processing in CtsBridge.
 * 
 * 		CtsBridge loops, taking the first element of createTenderQ,
 * 		inserts the information into the POE order entry service.
 * 		That call to bridgeExecute returns the parity OrderId.	
 */

public class LmeSocketServer extends Thread	{
	private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private static final Logger logger = LogManager.getLogger(LmeSocketServer.class);
    private BufferedReader in;
    
    // Socket Server in LME for CreateTransaction
    public static final int LME_PORT = 39401;
    // Socket Server in Market for CreateTender 
    public static final int MARKET_PORT = 39402;
    public final int port = LME_PORT;
    String jsonReceived = null;
    MarketCreateTransactionPayload payload; // socket read from Market
    public static EiCreateTransactionPayload eiCreatePayload;
    
    /*
     * map is from (Long)MarketCreateTransactionPayload.getMatchNumber() to the
     * EiCreateTenderPayload built from the MarketCreateTransaction.
     * 
     * Typically one member only
     */
	public static ConcurrentHashMap<Long, EiCreateTransactionPayload>
		eiCreateTransactionMatchNumberMap =
			new ConcurrentHashMap<Long, EiCreateTransactionPayload>();	
    
    final ObjectMapper mapper = new ObjectMapper();
    // to put EiCreateTransactionPayload in lme.eiCreateTransactionQueue 
    public LmeRestController lme;	

    @Override
    public void run() {
//		port is set in constructor
// 		System.err.println("LmeSocketServer.run() port " + port +
// 				" '" + Thread.currentThread().getName() + "'");
 		
    	EiCreateTransactionPayload eiCreateTransaction;
    	EiCreateTenderPayload eiCreateTender;
    	EiTransaction transaction;
    	EiTenderType tender;
    	//	For lookup and return value in eiCreateTransactionMatchNumberMap
    	Long matchNumberLong; // cannot use long for HashMap key
    	EiCreateTransactionPayload matchEiCreateTransaction, tempCreate;
    	
        /*
         * The Map takes (Long)MarketCreateTransactionPayload.getMatchNumber() to the
         * EiCreateTenderPayload built from that MarketCreateTransaction.
         * 
         * In the while loop we take a MarketCreateTransaction and build an
         * EiCreateTransactionPayload.
         * 
         * If the matchNumber is in the Map (the first of two transactions has been processed)
         * we send the one stored in the Map (and delete the entry).
         * 
         * Then we send the one in hand that correlated by matchNumber.
         */
    	try	{
            	serverSocket = new ServerSocket(port);
	            clientSocket = serverSocket.accept();
	            if (clientSocket == null)
	            	System.err.println("LmeSocketServer: clientSocket null after accept");
	            out = new PrintWriter(clientSocket.getOutputStream(), true);
	            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	    }	catch (IOException e)	{
	        //	logger.debug(e.getMessage());
	    	System.err.println("LmeSocketServer: accept " + e.getMessage());
	    	e.printStackTrace();
	    }

        if (in == null || out == null)	System.err.println("in or out null");   
        
//      logger.info("LME:LmeSocketServer before while loop");
        
        while (true)	{
        	//	blocking read for MarketCreateTransactionPayload from market
        	try	{
        	logger.trace("CTS:SocketClient while loop head");     	
        	jsonReceived = in.readLine(); 
        	// TODO add further checks for return values and IOException
            
        	logger.debug("LME received " + jsonReceived);
            
        	// couldn't read from socket TODO have thrown IOException
        	if (jsonReceived == null)	continue;	
            
            payload = mapper.readValue(
            		jsonReceived, MarketCreateTransactionPayload.class);                          
            logger.trace("payload received object: " + payload.toString());
            matchNumberLong = payload.getMatchNumber();	// autoboxing
            
            // 	Check for non-CTS tenders. If the CtsTenderId is not in
            //	the map, it's from outside CTS or otherwise erroneous.
            // 	TODO Ignore for now
            
            //	Get original Tender for this MarketCreateTransaction
            eiCreateTender =
            	LmeRestController.ctsMarketOrderIdToCreateTenderMap.get(payload.getCtsTenderId());
            
            // LATER TODO clean up and remove entry when tender quantity becomes zero
            if (eiCreateTender == null) {
            	// no match in Map - try again
            	continue;
            }	else	{
            	/*
            	 * Need to find a Transaction with same matchNumber and process both.
            	 * 
            	 * When [Cts]TenderId has a value in the TenderId map we extract
            	 * the original tender, and build the EiCreateTransactionPayload using
				 * original parties and TenderId, with cleared quantity and price.
            	 */
            	
            	// recover original tender for attributes. CounterParty rewritten below
                tender = eiCreateTender.getTender();
                
                // CURRENTLY, TENDER DETAIL IMPLEMENTATION IS UNSTABLE
				// THIS IS A WORKAROUND TO ENSURE THAT APPLICATION AT LEAST
				// WORKS WITH INTERVAL TENDERS
				TenderDetail tenderDetail = tender.getTenderDetail();
				if (tenderDetail.getClass() != TenderIntervalDetail.class) {
					throw new IllegalArgumentException("Currently only support simple Interval Tenders");
				}
				TenderIntervalDetail tenderIntervalDetail = (TenderIntervalDetail) tenderDetail;
                
            	logger.trace("Original EiCreateTenderPayload " + eiCreateTender.toString());
                
            	tenderIntervalDetail.setQuantity(payload.getQuantity());
            	tenderIntervalDetail.setPrice(payload.getPrice());
                //	other fields of tender as in EiCreateTender - tenderId,
                //	interval, expireTime, side
            	logger.debug("Reconstituted tender " + tender.getTenderId().toString());
                
                //	The EiCreateTransaction uses original TenderId
                transaction = new EiTransaction(tender);
                
                eiCreateTransaction = new EiCreateTransactionPayload(
                		transaction,
                		eiCreateTender.getPartyId(),
                		eiCreateTender.getCounterPartyId());
                
            	logger.trace("LmeSocketServer EiCreateTransaction " +
                		eiCreateTransaction.toString());
              	/*
            	 * Determine whether a previous EiCreateTransaction built from
            	 * the same Parity matchNumber was saved in eiCreateTransactionMatchNumberMap
            	 * 
            	 * If so, send it then send the current EiCreateTransactionPay;oad
            	 * If not, save this EiCrerateTransactionPayload in Map key value is
            	 * matchNumber in MarketCreateTransactionPayload from Parity
            	 */
            	matchEiCreateTransaction = eiCreateTransactionMatchNumberMap.get(matchNumberLong);
            	if (matchEiCreateTransaction == null) {
            		//	no value in map for matchNumberLong so this is the first
            		//	Insert working EiCreateTransactionPayload into the map with key
            		//	Send nothing until this matchNumber matches a future MarketCreateTransaction
            		eiCreateTransactionMatchNumberMap.put(matchNumberLong, eiCreateTransaction);
            		continue;
            	}	else	{
            		// 	matchEiCreateTransaction - a previous EiCreateTransaction in map
            		//	eiCreateTransaction - the just-received
            		//	Rewrite in place the CounterParty for both and send the earlier first
            		logger.trace("matchEiCreateTransaction before " + matchEiCreateTransaction.toString());
            		logger.trace("Party match " + matchEiCreateTransaction.getPartyId().toString());

            		logger.trace("eiCreateTransaction before " + eiCreateTransaction.toString());
            		logger.trace("Party eiCreate " + eiCreateTransaction.getPartyId().toString());
            		
            		matchEiCreateTransaction.setCounterPartyId(
            				eiCreateTransaction.getPartyId());
            		logger.trace("matchEiCreateTransaction after update " + matchEiCreateTransaction.toString());

             		eiCreateTransaction.setCounterPartyId(
            				matchEiCreateTransaction.getPartyId());
               		logger.trace("eiCreateTransaction after update " + eiCreateTransaction.toString());

                    // Put both in the LME transaction queue for further processing - older first
            		LmeRestController.eiCreateTransactionQueue.put(matchEiCreateTransaction);
            		LmeRestController.eiCreateTransactionQueue.put(eiCreateTransaction);
                    
            		logger.debug("LmeSocketServer enqueued " +             
            				" " + matchEiCreateTransaction.toString());
            		logger.debug("LmeSocketServer enqueued " +
                    		" " + eiCreateTransaction.toString());
                    // safe to remove the previous HashMap entry for this matchNumber as there was
                    // exactly one in the HashMap
                    tempCreate = eiCreateTransactionMatchNumberMap.remove(matchNumberLong);
                    if (tempCreate.equals(matchEiCreateTransaction))	{
                    	// correctly removed from map
                    	logger.debug(
                    		"Removed matchEiCreateTransaction from eiCreateTransactionMatchNumberMap");
                    	logger.debug("Removed matchEiCreateTransaction from map size now " +
                    			eiCreateTransactionMatchNumberMap.size());
                    }	else	{
                    	// error - wasn't in the map - should never reach this code
                    	logger.info("Consistency Error in eiCreateTransactionMatchNumberMap - match wasn't in map");
                    }
            	}
              
        	}	
        }	catch (IOException  e) {       	
        	logger.info(e.getMessage());
	    	System.err.println("LmeSocketServer: IOException in readLine? " +
	    			e.getMessage());
	    	e.printStackTrace();
	    } 	catch (InterruptedException e) {
	    	System.err.println("LmeSocketServer: InterruptedException in readLine? " +
	    			e.getMessage());
			e.printStackTrace();
	    }
    }
}


    public void shutdown() {
        try {
            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
        	System.err.println("LmeSocketServer stop: " + e.getMessage());
        	logger.info("LmeSocketServer: " + e.getMessage());
        }
    }
    
    public LmeSocketServer()	{
//    	System.err.println("LmeSocketServer: 0 param constructor Port: " +
//    			port + " " + Thread.currentThread().getName());
    }
    
    public LmeSocketServer(int port)	{
//    	System.err.println("LME:LmeSocketServer: 1 param constructor Port: " +
//    			port + " " + Thread.currentThread().getName());
    	
//    	LmeSocketServer server = new LmeSocketServer();	
//    	server.start();
    	// TODO Lambda Expression for separate thread - current is in thread
    }
    
    public LmeSocketServer(int port, LmeRestController lme)	{
//    	System.err.println("LME:LmeSocketServer: 2 param constructor Port: " +
//    			port + " " + Thread.currentThread().getName());
 
    	this.lme = lme;
    	if (lme == null)	{
    		System.err.println("LmeSocketServer: constructor:this.lme is null");
    	}
    }
}
