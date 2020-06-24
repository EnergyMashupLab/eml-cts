package org.theenergymashuplab.cts;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LmeSendTransactions	extends Thread	{
	
	private static final Logger logger = LogManager.getLogger(
			LmeSendTransactions.class);
	final RestTemplateBuilder builder = new RestTemplateBuilder();
	
	/*
	 * 	Take first EiCreateTransactionPayload from eiCreateTransactionQueue and
	 * 	Post it to the LMA.
	 */
	@Override
	public void run()		{
		EiCreateTransactionPayload eiCreateTransaction = null;
		
		while(true)	{
			try {
			eiCreateTransaction = LmeRestController.eiCreateTransactionQueue.take();
		} catch (InterruptedException e) {
			System.err.println("InterruptedException in LmeSendTransactions");
			e.printStackTrace();
		}
		if (eiCreateTransaction != null)	{
			logger.debug("CreateTransaction " + eiCreateTransaction.toString());
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
		logger.debug("LmeSendTransactions.postClientCreateTransaction " +
				tempTransaction.toString());
		/*
		 * Pass on to LMA and use POST responseBody in reply origin
		 */
		tempPostResponse = restTemplate.postForObject("http://localhost:8080/lma/createTransaction", 
				eiCreateTransaction, 
				EiCreatedTransactionPayload.class);
				
		/*
			tempCreated = new EiCreatedTender(tempTender.getTenderId(),
				tempCreate.getPartyId(),
				tempCreate.getCounterPartyId(),
				new EiResponse(200, "OK"));
		*/
		
		return tempPostResponse;
	}

}
