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
 * 		This CtsBridge Socket Server accepts connections on MARKET_PORT.
 * 
 * 		The LME Socket Client opens the LME IP address on MARKET_PORT.
 * 
 * 		Client writes JSON-serialized MarketCreateTenderPayloads which
 * 		are read by this CtsSockerServer, deserialized, and put on
 * 		bridge.createTenderQ for further processing in CtsBridge.
 * 
 * 		CtsBridge loops, taking the first element of createTenderQ,
 * 		inserts the information into the POE order entry service.
 * 		That call to bridgeExecute returns the parity OrderId.	
 */

public class CtsSocketServer extends Thread	{
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
	private static final Logger logger = LogManager.getLogger(CtsSocketServer.class);
    private BufferedReader in;
    
    // Socket Server in LME for CreateTransaction
    public static final int LME_PORT = 39401;
    // Socket Server in Market for CreateTender 
    public static final int MARKET_PORT = 39402;
    public final int port = LME_PORT;
    String jsonReceived = null;
    MarketCreateTransactionPayload payload; // from Market
    public static EiCreateTransactionPayload eiCreatePayload;
    
    final ObjectMapper mapper = new ObjectMapper();
//    CtsBridge bridge;	// to access bridge.createTenderQ
    LmeRestController lme;	// to access lme.createTransactionQ

    @Override
    public void run() {
    	//	port is set in constructor
 		System.err.println("CtsSocketServer.run() port: " + port +
 				" '" + Thread.currentThread().getName() + "'");
 		
    	EiCreateTransactionPayload eiCreateTransaction;
    	EiCreateTenderPayload eiCreateTender;
    	EiTransaction transaction;
    	EiTender tender;
   	
        try {
            serverSocket = new ServerSocket(port);
            clientSocket = serverSocket.accept();
            if (clientSocket == null)
            	System.err.println("CtsSocketServer: clientSocket null after accept");
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(
            			new InputStreamReader(clientSocket.getInputStream()));
            if (in == null || out == null)	System.err.println("in or out null");
        
            System.err.println("LME:CtsSocketServer before while loop");
            
            while (true)	{
            	//	blocking read on BufferedReader from Bridge
            	//	and a JSON serialized MarketCreateTransactionPayload 
            	
            	logger.info("CTS:SocketClient while loop head");     	
            	jsonReceived = in.readLine();       
                
            	System.err.println("CtsSocketServer: start: jsonReceived is '" + 
            		jsonReceived  + "' Thread " + Thread.currentThread().getName());
                
                if (jsonReceived == null)	break;
                payload = mapper.readValue(
                		jsonReceived, MarketCreateTransactionPayload.class);
                                
                logger.info("payload received object: " + payload.toString());
                

                // Construct a new EiCreateTransactionPayload using values from payload and
              	// selected fields from saved EiCreateTender that participated in the trade
                eiCreateTender = LmeRestController.ctsTenderIdToCreateTenderMap.get(payload.ctsTenderId);
                
                tender = eiCreateTender.getTender();	// original tender
                tender.setQuantity(payload.getQuantity());
                tender.setPrice(payload.getPrice());
                // other fields of tender as in EiCreateTender - tenderId, interval, expireTime, side
                // parties from EiCreateTender 
                transaction = new EiTransaction(tender);
                
                eiCreateTransaction = new EiCreateTransactionPayload(
                		transaction,
                		eiCreateTender.getPartyId(),
                		eiCreateTender.getCounterPartyId());
                
 
            	System.err.println("LME: CtsSocketServer EiCreateTransaction " + eiCreateTransaction.toString());
                   
                // Put in the LME transaction queue for further processing
                lme.eiCreateTransactionQ.put(eiCreateTransaction);
                logger.info("CtsSocketServer: put Transaction on eiCreateTransactionQ " + eiCreateTransaction.toString());
            	}	
        }	catch (IOException  e) {       	
	        //	LOG.debug(e.getMessage());
	    	System.err.println("CtsSocketServer: IOException in readLine?");
	    	e.printStackTrace();
	    } 	catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	    }

    }


    public void shutdown() {
        try {
            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
        	System.err.println("CtsSocketServer stop: " + e.getMessage());
        	//	logger.info("CtsSocketServer: " + e.getMessage());
        }
    }
    
    public CtsSocketServer()	{
    	System.err.println("LME:CtsSocketServer: 0 param constructor Port: " +
    			port + " " + Thread.currentThread().getName());
    	
    }
    
    public CtsSocketServer(int port)	{
    	System.err.println("LME:CtsSocketServer: 1 param constructor Port: " +
    			port + " " + Thread.currentThread().getName());
    	
    	CtsSocketServer server = new CtsSocketServer();	
    	server.start();
    	// TODO Lambda Expression for separate thread - current is in thread
    }
    
    public CtsSocketServer(int port, LmeRestController lme)	{
    	System.err.println("LME:CtsSocketServer: 2 param constructor Port: " +
    			port + " " + Thread.currentThread().getName());
 
    	this.lme = lme;
    	if (lme == null)	{
    		System.err.println("CtsSocketServer: constructor:this.lme is null");
    	}
    }
}