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


/*
 * 
 * Actor for SC or other client for integration with CTS via POST to TEUA.
 * 
 * Client integration code will work with this RestController to send Tenders
 * (buy and sell) defined in the SC and receive CreateTransaction messages that
 * say what's traded, quantity, price, and what ctsTenderId is associated
 * with the (possibly partly executed tender)
 */

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import org.theenergymashuplab.cts.ClientTender;
import org.theenergymashuplab.cts.SideType;
import org.theenergymashuplab.cts.TenderIdType;
import org.theenergymashuplab.cts.controller.payloads.ClientCreateTenderPayload;
import org.theenergymashuplab.cts.controller.payloads.ClientCreateTransactionPayload;
import org.theenergymashuplab.cts.controller.payloads.ClientCreatedTenderPayload;
import org.theenergymashuplab.cts.controller.payloads.ClientCreatedTransactionPayload;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Random;

@RestController
@RequestMapping("/client")	// will be dynamic URI with /sc/{id}/
public class ClientRestController {
	
	private static final Logger logger = LogManager.getLogger(
			ClientRestController.class);
    final ObjectMapper mapper = new ObjectMapper();
    
    // for managing client/{id} and teua/{id}
    public final int DEFAULT_COUNT = 20;
    public int idLimit;	// number of teuas hence of clients
    
    //	Array uses teua/number/... and matching client/number
	//	to give the URI string to which to post
    String[] postClientCreateTenderUri;
    String uriPrefix = "http://localhost:8080/teua/";
    String uriSuffix = "/clientCreateTender";
    String idString = null;
    int workingId = 0;

	// for randomized quantity and price for testing
	final static Random rand = new Random();	
	
	// Constructor for class ClientRestController - zero parameters
	public ClientRestController()	{
		int i;
		
		this.idLimit = DEFAULT_COUNT;
		postClientCreateTenderUri = new String[this.idLimit];

		for (i = 0; i < this.idLimit; i++)	{
			postClientCreateTenderUri[i] = uriPrefix+
					String.valueOf(i) + uriSuffix;
		}
	}
	
	// Constructor for class ClientRestController - zero parameters
	public ClientRestController(int howMany)	{
		int i;
		postClientCreateTenderUri = new String[howMany];
		this.idLimit = howMany;
		
		for (i = 0; i < this.idLimit; i++)	{
			postClientCreateTenderUri[i] = uriPrefix+
					String.valueOf(i) + uriSuffix;
		}
	}
	

	
	
	@PostMapping("/{id}/clientCreateTransaction")
	public ClientCreatedTransactionPayload 	postTransaction(
		@RequestBody ClientCreateTransactionPayload clientCreateTransactionPayload,
		@PathVariable String id)	{
		
		ClientCreateTransactionPayload tempClientCreateTransaction;
		tempClientCreateTransaction = clientCreateTransactionPayload;
		
		// Build response
		ClientCreatedTransactionPayload response = 
					new ClientCreatedTransactionPayload();
			
		logger.info("/clientCreateTransaction POSTed to /client/" +
				id + "/clientCreateTransaction " +
				tempClientCreateTransaction.toString());
		
		/*
		 * INSERT SC/Client code to track tenders and transactions against then
		 */

		return response;
		}

	
	/*
	 * GET - /client/clientTender responds with a randomized clientCreateTender,
	 * serialized to JSON for use with Postman for functional testing
	 * 
	 * The ClientRestController also accepts those payloads (e.g. from Postman)
	 * and forwards them unchanged to our TEUA.
	 */
	@GetMapping("/clientTender")
	public ClientTender getClientTender() {
		SideType randSide;
		long randQuantity;
		long randPrice;
		ClientTender tempTender;
		
		randQuantity = 20 + rand.nextInt(80);
		randPrice = 75 + rand.nextInt(50);	
		randSide =SideType.BUY;
		if (rand.nextInt(100) > 50)	{
				randSide = SideType.SELL;
			}
		
		tempTender = new ClientTender(randSide, randQuantity, randPrice);
		//	System.err.println(tempTender.toString());	
		// ClientTender(SideType side, long quantity, long price)	
		
		return  tempTender;
	}	
	
	/*
	 * GET - /client/clientTransaction responds with a randomized clientCreateTransaction,
	 * serialized to JSON for use with Postman for functional testing
	 */
	@GetMapping("/clientTransaction")
	public ClientCreateTransactionPayload getClientTransaction() {
		SideType randSide;
		long randQuantity;
		long randPrice;
		TenderIdType tempTenderId = new TenderIdType();
		long ctsTenderId;
		ClientCreateTransactionPayload tempTransaction;
		
		ctsTenderId = tempTenderId.value();
		
		randQuantity = 20 + rand.nextInt(80);
		randPrice = 75 + rand.nextInt(50);	
		randSide =SideType.BUY;
		if (rand.nextInt(100) > 50)	{
				randSide = SideType.SELL;
			}
		
		tempTransaction = new ClientCreateTransactionPayload(
				randSide, 
				randQuantity,
				randPrice,
				ctsTenderId
				);
		//	System.err.println(tempTender.toString());	
		// ClientTender(SideType side, long quantity, long price)	
		
		logger.trace("End of /client/clientTransaction Return " + tempTransaction.toString());
		
		return  tempTransaction;
	}
	
	/*
	 * POST - /clientCreateTender - a simple pass through to TEUA
	 * 
	 * Simplifies use of Postman for testing
	 * 
	 * NOTE that the quantity in a ClientCreateTender is FULL REQUIREMENTS for the
	 * Interval. The User Agent will adjust that request by energy already bought or sold
	 * on behalf of this client for the Interval, to get a net amount to go from the client's position
	 * (energy already bought or sold, netted) to the Full Requirements amount for Interval.
	 */
	@PostMapping("/clientCreateTender")
	public ClientCreatedTenderPayload postClientCreateTender(
				@RequestBody ClientCreateTenderPayload clientCreateTender)	{
		ClientCreateTenderPayload tempCreate;	
		
		final RestTemplateBuilder builder = new RestTemplateBuilder();
		// scope is function postEiCreateTender
		RestTemplate restTemplate = builder.build();	
		tempCreate = clientCreateTender;	// as received

		/*
		 * Forward the @RequestBody as received,
		 * Wait for and return the @ReponseBody as received
		 */
		System.err.println("/clientCreateTender received " + clientCreateTender.toString());
		logger.debug("before forwarding CLientCreateTender to TEUA " +
				tempCreate.toString());
		
		//	And forward to the TEUA
		restTemplate = builder.build();
		ClientCreatedTenderPayload result = restTemplate.postForObject
			("http://localhost:8080/teua/clientCreateTender", tempCreate,
					ClientCreatedTenderPayload.class);		
		
		logger.debug("Result is " + result.toString());
		
		return result;
	}
}
