package com.example.restservice;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class GreetingController {

	private static final String template = "Hello, %s!";
	private static final String tenderTemplate = "GET %s!";
	private static final AtomicLong counter = new AtomicLong();
	private static EiTender currentTender;

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
	 * 	DEBUG version - uses CreateRandomTender, sequential actorId, response tenderId not correlated to any RequestBody
	 */
	@GetMapping("/CreateTender")
	public EiCreateTender eiCreateTender(@RequestParam(name = "number", defaultValue = "tid not assigned") String tid) {
		EiTender tempTender = new CreateRandomTender().randomTender();
		EiCreateTender tempEiCreateTender;
		
		// actor Ids will come from POST RequestBody
		return new EiCreateTender(tempTender, new ActorIdType(), new ActorIdType());		
	}
	
	/*
	 * GET - /CreateTransaction responds with a new EiCreateTransaction
	 * 		ResponseBody is EiCreateTransaction
	 * 	DEBUG version - uses CreateRandomTender, sequential actorId, response tenderId not correlated to any RequestBody
	 */
	@GetMapping("/CreateTransaction")
	public EiCreateTransaction eiCreateTransaction(@RequestParam(name = "number", defaultValue = "tid not assigned") String tid) {
		EiTender tempTender = new CreateRandomTender().randomTender();
		EiCreateTransaction tempEiCreateTransaction;
		EiTransaction tempTransaction;

		tempTransaction = new EiTransaction(tempTender);
		
		// create a new EiCreateTransaction body using random tender held in tempTender and sequential new ActorIds
		tempEiCreateTransaction = new EiCreateTransaction(
				tempTransaction,
				new ActorIdType(), 
				new ActorIdType());
		
		return tempEiCreateTransaction;
	}
	
	/*
	 * GET - /CancelTender responds with a new EiCancelTender
	 * 		ResponseBody is EiCancelTender
	 */
	@GetMapping("/CancelTender")
	public EiCancelTender eiCancelTender(@RequestParam(name = "number", defaultValue = "tid not assigned") String tid) {
		EiTender tempTender = new CreateRandomTender().randomTender();
		EiCancelTender tempEiCancelTender;
		EiTransaction tempTransaction;

		tempTransaction = new EiTransaction(tempTender);
		
		// create a new EiCreateTransaction body using random tender held in tempTender and sequential new ActorIds
		tempEiCancelTender = new EiCancelTender(
				new TenderIdType(),
				new ActorIdType(), 
				new ActorIdType());
		
		/*
		 * 	public EiCancelTender(TenderId tenderId, ActorId party, ActorId counterParty) {
		 */
		
		return tempEiCancelTender;
	}
	
	
}
