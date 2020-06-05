package com.example.restservice;


import java.net.*;
import java.io.*;
import java.lang.Runnable;
import java.lang.Thread;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.databind.ser.std.*;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.AbstractQueue;
import java.util.AbstractCollection;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;

import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;

// Not in Parity
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


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
    
    final ObjectMapper mapper = new ObjectMapper();
    // to put EiCreateTransactionPayload in lme.eiCreateTransactionQ 
    public LmeRestController lme;	

    @Override
    public void run() {
    	//	port is set in constructor
 		System.err.println("LmeSocketServer.run() port " + port +
 				" '" + Thread.currentThread().getName() + "'");
 		
    	EiCreateTransactionPayload eiCreateTransaction;
    	EiCreateTenderPayload eiCreateTender;
    	EiTransaction transaction;
    	EiTender tender;
      
            try	{
            	serverSocket = new ServerSocket(port);
 
	            clientSocket = serverSocket.accept();
	            if (clientSocket == null)
	            	System.err.println("LmeSocketServer: clientSocket null after accept");
	            out = new PrintWriter(clientSocket.getOutputStream(), true);
	            in = new BufferedReader(
	            			new InputStreamReader(clientSocket.getInputStream()));
            }	catch (IOException e)	{
    	        //	LOG.debug(e.getMessage());
    	    	System.err.println("LmeSocketServer: accept " + e.getMessage());
    	    	e.printStackTrace();
            }
    
            if (in == null || out == null)	System.err.println("in or out null");        
//            System.err.println("LME:LmeSocketServer before while loop");
            
            while (true)	{
            	//	blocking read on BufferedReader for a MarketCreateTransactionPayload 
            	
            	try	{
//            	logger.debug("CTS:SocketClient while loop head");     	
            	jsonReceived = in.readLine(); 
            	// TODO add checks for return values and IOException
                
            	logger.info("LME got " + jsonReceived);
                
            	// couldn't read from socket TODO should be IOException
            	if (jsonReceived == null)	continue;	
                
                payload = mapper.readValue(
                		jsonReceived, MarketCreateTransactionPayload.class);                          
//                logger.info("payload received object: " + payload.toString());
                
                // 	Check for non-CTS tenders. If the CtsTenderId is not in
                //	the map, it's from outside CTS or otherwise erroneous.
                // TODO Ignore for now
                eiCreateTender = LmeRestController.ctsTenderIdToCreateTenderMap.get(payload.ctsTenderId);
                
                // TODO clean up and remove entry when tender quantity becomes zero
                if (eiCreateTender == null) {
                	// no match in Map - try again
                	continue;
                }	else	{
                	// 	match in map - extract original tender, build EiCreateTransactionPayload using 
                	//	original parties and TenderId, with cleared quantity and price

	                tender = eiCreateTender.getTender();	// recover original tender attributes
//                	logger.debug("Original tender " + tender.toString());
	                
	                tender.setQuantity(payload.getQuantity());
	                tender.setPrice(payload.getPrice());
	                // other fields of tender as in EiCreateTender - tenderId, interval, expireTime, side
//                	logger.info("Reconsistuted tender " + tender.toString());
	                
	                //	The EiCreateTransaction uses original TenderId
	                transaction = new EiTransaction(tender);
	                
	                eiCreateTransaction = new EiCreateTransactionPayload(
	                		transaction,
	                		eiCreateTender.getPartyId(),
	                		eiCreateTender.getCounterPartyId());
	                
//	            	logger.debug("LmeSocketServer EiCreateTransaction " + eiCreateTransaction.toString());
	              
	                // Put in the LME transaction queue for further processing
	                lme.eiCreateTransactionQ.put(eiCreateTransaction);
//	                logger.info("LME enqueued eiCreateTransactionQ TenderId " +
//	                		eiCreateTransaction.getTransaction().getTender().getTenderId().value() +
//	                		" " + eiCreateTransaction.toString());
            	}	
	        }	catch (IOException  e) {       	
		        //	LOG.debug(e.getMessage());
		    	System.err.println("LmeSocketServer: IOException in readLine? " + e.getMessage());
		    	e.printStackTrace();
		    } 	catch (InterruptedException e) {
		    	System.err.println("LmeSocketServer: InterruptedException in readLine? " + e.getMessage());
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