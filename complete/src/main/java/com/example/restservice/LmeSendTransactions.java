package com.example.restservice;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;



public class LmeSendTransactions	extends Thread	{
	
	private static final Logger logger = LogManager.getLogger(
			LmeSendTransactions.class);
	final RestTemplateBuilder builder = new RestTemplateBuilder();
	
	/*
	 * 	Take first EiCreateTransactionPayload from eiCreateTransactionQ and
	 * 	Post it to the LMA.
	 */
	@Override
	public void run()		{
		EiCreatedTransactionPayload tempPostResponse;
		EiCreateTransactionPayload eiCreateTransaction = null;
		
		while(true)	{
			try {
			eiCreateTransaction = LmeRestController.eiCreateTransactionQ.take();
		} catch (InterruptedException e) {
			System.err.println("InterruptedException in LmeSendTransactions");
			e.printStackTrace();
		}
		if (eiCreateTransaction != null)	{
			logger.info("CreateTransaction " + eiCreateTransaction.toString());
			tempPostResponse = postClientCreateTransaction(eiCreateTransaction);
		}
		else logger.info("eiCreateTransaction null");
		
		// TODO process tempPost Response TBD
		}
	}
	
	public EiCreatedTransactionPayload postClientCreateTransaction(
				@RequestBody EiCreateTransactionPayload eiCreateTransaction)	{
		EiTransaction tempTransaction;
		EiCreatedTransactionPayload tempPostResponse;
		
		//	TODO should this be a PUT? Parity does not get a response back, but logging
		//	that the CreateTransaction was received may be useful
		

		RestTemplate restTemplate;	// scope is function postEiCreateTransaction
		restTemplate = builder.build();		
		
		tempTransaction = eiCreateTransaction.getTransaction();
		logger.info("LmeSendTransactions.postClientCreateTransaction " +
				tempTransaction.toString());
		/*
		 * Pass on to LMA and use POST responseBody in reply origin
		 */
		tempPostResponse = restTemplate.postForObject("http://localhost:8080/lma/createTransaction", 
				eiCreateTransaction, 
				EiCreatedTransactionPayload.class);
		
		logger.info("LMA after forward to UA and before return " + tempPostResponse.toString());
		
		/*
			tempCreated = new EiCreatedTender(tempTender.getTenderId(),
				tempCreate.getPartyId(),
				tempCreate.getCounterPartyId(),
				new EiResponse(200, "OK"));
		*/
		
		return tempPostResponse;
	}

}
