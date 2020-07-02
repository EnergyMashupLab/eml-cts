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
		EiCreatedTransactionPayload tempPostResponse;
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
