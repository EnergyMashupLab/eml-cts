package com.example.restservice;

// Actor for SC used for integration with CTS via POST to TEUA

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.Random;

@RestController
@RequestMapping("/sc")	// will be dynamic URI with /sc/{id}/
public class ScRestController {

//	private static ScTender prepareTender;
	
	private static final Logger logger = LogManager.getLogger(
			LmaRestController.class);
	
	// for randomized quantity and price
	final static Random rand = new Random();	
	
	/*
	 * POST - To /sc/transaction - by TEUA to SC
	 *			(simplified forward of EiCreateTransaction)
	 * 		RequestBody is ScTransaction
	 * 		ResponseBody is ScTransactionResponse
	 */

	@PostMapping("/transaction")
	public ScTransactionResponse 	postTransaction(
			@RequestBody ScTransaction transactionRequest)	{
		
		ScTransactionResponse response = new ScTransactionResponse();
		
		/*
		 * HOOK HERE - call SC code to update table of tenders sent and the results.
		 * 
		 * This SC sent a tender (time, quantity, price) before; the transaction
		 * tells the SC which of its tenders have cleared, partially of full.
		 * 
		 * Suggested pseudocode for SC
		 * 
		 * 		Create a table for CTS TenderIds with columns for quantity offered,
		 * 		time interval, price offered
		 * 
		 * 		When a tender (offer to buy or sell) is made by calling SendTenderToUserAgent, 
		 * 		the return value is the CTS ID of the CTS tender.
		 * 
		 * 		Enter the tender with its CTS ID and its initial/offered values in the table
		 * 
		 * 		When a transaction is POSTED to this SC, call back to SC code to update the 
		 * 		relevant table entry, identified with //HOOK in this RestController
		 *
		 */

		return response;
		}
	
	/*
	 * TODO verify price and quantity transform
	 */
	
	long PostTenderToCTS(Side side, long quantity, long price)	{
		/*
		 * package core tender information
		 * 		Side
		 * 		Time Interval
		 * 		Price
		 * into an ScTender object which will be sent
		 * to the TEUA with the same Id as this SC
		 * 
		 * Called by other SC code as
		 * 		<<table entry tender id> = PostTenderToCTS(side, quantity, price);
		 */

		ScTenderResponse tempPostResponse;	// what is received. Does it need to be an object?
		
		final RestTemplateBuilder builder = new RestTemplateBuilder();
		RestTemplate restTemplate;
		ScTender scTender;	// what is sent
		scTender = new ScTender(side, quantity, price);

		restTemplate = builder.build();
		
		tempPostResponse = restTemplate.postForObject("http://localhost:8080/teua/tender", 
				scTender,
				ScTenderResponse.class);
		logger.info("ScController after POST response received from teua. returned TenderId " +
				tempPostResponse.ctsTenderId);

		return ( tempPostResponse.ctsTenderId);
	}
	
	/*
	 * GET - /sc/scTender responds with a randomized tender, serialized to JSON
	 * for use with Postman for functional testing
	 */
	@GetMapping("/scTender")
	public ScTender getScTender() {
		Side randSide;
		long randQuantity;
		long randPrice;
		ScTender tempTender;
		
		randQuantity = 20 + rand.nextInt(80);
		randPrice = 75 + rand.nextInt(50);	
		randSide =Side.BUY;
		if (rand.nextInt(100) > 50)	{
				randSide = Side.SELL;
			}
		
		tempTender = new ScTender(randSide, randQuantity, randPrice);
//		System.err.println(tempTender.toString());
		
		// ScTender(Side side, long quantity, long price)	{
		return  tempTender;
	}
	
	
		
}