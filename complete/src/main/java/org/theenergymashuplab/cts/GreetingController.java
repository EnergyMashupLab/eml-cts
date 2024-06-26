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

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.theenergymashuplab.cts.controller.payloads.ClientCreateTenderPayload;
import org.theenergymashuplab.cts.controller.payloads.ClientCreatedTenderPayload;
import org.theenergymashuplab.cts.controller.payloads.EiCancelTenderPayload;
import org.theenergymashuplab.cts.controller.payloads.EiCreateTenderPayload;
import org.theenergymashuplab.cts.controller.payloads.EiCreateTransactionPayload;


/*
 * NOT USED
 * TODO Candidate for deletion in future release
 */
@RestController
public class GreetingController {

	private static final String template = "Hello, %s!";
	private static final AtomicLong counter = new AtomicLong();

	/*
	 * From original tutorial (Apache 2.0 licensed) kept for function test
	 */
	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}

	/*
	 * GET methods are to return JSON EiCreateTender and EiCreateTransaction to use with Postman testing
	 */
	/*
	 * GET - /CreateTender responds with a new EiCreateTender
	 * 		ResponseBody is EiCreateTender
	 * 	DEBUG version - uses RandomEiTender, sequential actorId, response tenderId not correlated to any RequestBody
	 */
	@GetMapping("/CreateTender")
	public EiCreateTenderPayload eiCreateTenderPayload(@RequestParam(name = "number", defaultValue = "tid not assigned") String tid) {
		EiTenderType tempTender = new RandomEiTender().randomTender();
		
		// actor Ids will come from POST RequestBody
		return new EiCreateTenderPayload(tempTender, new ActorIdType(), new ActorIdType());		
	}
	
	/*
	 * GET - /CreateTransaction responds with a new EiCreateTransaction
	 * 		ResponseBody is EiCreateTransaction
	 * 	DEBUG version - uses RandomEiTender, sequential actorId, response tenderId not correlated to any RequestBody
	 */
	@GetMapping("/CreateTransaction")
	public EiCreateTransactionPayload eiCreateTransactionPayload(@RequestParam(name = "number", 
							defaultValue = "tid not assigned") String tid) {
		EiTenderType tempTender = new RandomEiTender().randomTender();
		EiCreateTransactionPayload tempEiCreateTransactionPayload;
		EiTransaction tempTransaction;

		tempTransaction = new EiTransaction(tempTender);
		
		// create a new EiCreateTransaction body using random tender held in tempTender and sequential new ActorIds
		tempEiCreateTransactionPayload = new EiCreateTransactionPayload(
				tempTransaction,
				new ActorIdType(), 
				new ActorIdType(),
				new TransactionIdType());
		
		return tempEiCreateTransactionPayload;
	}
	
	/*
	 * GET - /CancelTender responds with a new EiCancelTender
	 * 		ResponseBody is EiCancelTender
	 */
	@GetMapping("/CancelTender")
	public EiCancelTenderPayload eiCancelTender(@RequestParam(name = "number", defaultValue = "tid not assigned") String tid) {
		EiTenderType tempTender = new RandomEiTender().randomTender();
		EiCancelTenderPayload tempEiCancelTender;
		EiTransaction tempTransaction;

		tempTransaction = new EiTransaction(tempTender);
		
		// create a new EiCreateTransaction body using random tender held in tempTender and sequential new ActorIds
		tempEiCancelTender = new EiCancelTenderPayload(
				new TenderIdType(),
				new ActorIdType(), 
				new ActorIdType(),
				tempTender.getMarketOrderId());
		/*
		 * 	public EiCancelTender(TenderId tenderId, ActorId party, ActorId counterParty) {
		 */
		
		return tempEiCancelTender;
	}
	
	/*
	 * GET - /clientCreateTender responds with a new ClientCreateTenderPayload
	 * 		ResponseBody is ClientCreatedTenderPayload
	 * 	DEBUG version - uses RandomEiTender, sequential actorId, response tenderId not correlated
	 * 	to any RequestBody
	 */
	@GetMapping("/clientCreateTender")
	public ClientCreateTenderPayload clientCreateTenderPayload(
					@RequestParam(name = "number", defaultValue = "tid not assigned") String tid) {
		EiTenderType tempTender = new RandomEiTender().randomTender();		
		
		// assign fields for random tender and fill in missing values
		// tempClientCreateTenderPayload.
		
		// actor Ids will come from POST RequestBody
		
		// CURRENTLY, TENDER DETAIL IMPLEMENTATION IS UNSTABLE
		// THIS IS A WORKAROUND TO ENSURE THAT APPLICATION AT LEAST
		// WORKS WITH INTERVAL TENDERS
		TenderDetail tenderDetail = tempTender.getTenderDetail();
		if (tenderDetail.getClass() != TenderIntervalDetail.class) {
			throw new IllegalArgumentException("Currently only support simple Interval Tenders");
		}
		TenderIntervalDetail tenderIntervalDetail = (TenderIntervalDetail) tenderDetail;
		return new ClientCreateTenderPayload(tempTender.getSide(),tenderIntervalDetail.getQuantity(), tenderIntervalDetail.getPrice());		
	}

	/*
	 * GET - /clientCreatedTender responds with a new ClientCreatedTenderPayload for ResponseBody
	 *
	 */
	@GetMapping("/clientCreatedTender")
	public ClientCreatedTenderPayload clientCreatedTenderPayload(
					@RequestParam(name = "number", defaultValue = "tid not assigned") String tid) {		
		
		ClientCreatedTenderPayload tempClientCreatedTenderPayload = new ClientCreatedTenderPayload(new TenderIdType().value()) ;
		
		System.err.println("/clientCreatedTender" + tempClientCreatedTenderPayload.toString());
		return tempClientCreatedTenderPayload;
	}
	
	
}
