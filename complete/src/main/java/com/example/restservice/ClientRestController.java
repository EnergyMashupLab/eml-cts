package com.example.restservice;

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
import org.springframework.web.client.RestTemplate;
import java.util.Random;

@RestController
@RequestMapping("/client")	// will be dynamic URI with /sc/{id}/
public class ClientRestController {
	
	private static final Logger logger = LogManager.getLogger(
			LmaRestController.class);
	
	// for randomized quantity and price for testing
	final static Random rand = new Random();	
	
	/*
	 * POST - To /client/clientCreateTransaction - by TEUA to SC/client
	 *			(simplified forward of EiCreateTransaction from TEUA)
	 * 		RequestBody is ClientCreateTransactionPayload
	 * 		ResponseBody is ClientCreatedTransactionPayload
	 */

	@PostMapping("/clientCreateTransaction")
	public ClientCreatedTransactionPayload 	postTransaction(
		@RequestBody ClientCreateTransactionPayload clientCreateTransactionPayload)	{
		
		ClientCreateTransactionPayload tempClientCreateTransaction;
		tempClientCreateTransaction = clientCreateTransactionPayload;
		
		// Build response
		ClientCreatedTransactionPayload response = 
					new ClientCreatedTransactionPayload();
		
		/*
		 * HOOK HERE - call client/SC code to update table of tenders sent and the
		 * results.
		 * 
		 * This SC previously posted a tender (time interval, expiration time, quantity,
		 * price, side). The transaction tells the SC which of its tenders have cleared,
		 * partially or fully, one message per executed tender or part.
		 * 
		 * By saving the CtsTendewrId and the ClientCreateTender request, the time interval,
		 * parties and other information can be retrieved.
		 * 
		 * Possible pseudocode for SC
		 * 
		 * 		Create a table or Map with fields for quantity offered,
		 * 		time interval, price offered, and ctsTenderId as returned
		 * 
		 * 		When a tender is send to the TEUA, the response ClientCreatedTenderPayload
		 * 		contains the CtsTenderId that was created.
		 * 
		 * 		Enter the tender with its CtsTenderId and its initial/offered
		 * 		quantity in the table
		 * 
		 * 		When an ClientCreateTransactionPayload is posted to this SC,
		 * 		update the relevant table entry.
		 * 
		 * 	We just log the ClientCreateTransaction payload.
		 */

		logger.info("clientCreateTransaction post received " +
				tempClientCreateTransaction.toString());
		
		/*
		 * INSERT SC/Client code to track tenders and transactions against then
		 */
		
		
		
		return response;
		}
	
	/*
	 * TODO verify price and quantity transform
	 */
	
	long PostTenderToCTS(SideType side, long quantity, long price)	{
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

		// To send to my TEUA
		ClientCreatedTenderPayload tempPostResponse;
		
		final RestTemplateBuilder builder = new RestTemplateBuilder();
		RestTemplate restTemplate;
		ClientCreateTenderPayload createTender;	// what is sent
		createTender = new ClientCreateTenderPayload(side, quantity, price);

		restTemplate = builder.build();
		
		tempPostResponse = restTemplate.postForObject(
				"http://localhost:8080/teua/clientCreateTender", 
				createTender,
				ClientCreatedTenderPayload.class);
		logger.info("after response received. TenderId " +
				tempPostResponse.ctsTenderId);

		return ( tempPostResponse.ctsTenderId);
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
	 * POST - /clientCreateTender - a simple pass through to TEUA
	 * 
	 * Simplifies use of Postman for testing
	 */
	
	@PostMapping("/clientCreateTender")
	public ClientCreatedTenderPayload postEiCreateTender(
				@RequestBody ClientCreateTenderPayload clientCreateTender)	{
		ClientCreateTenderPayload tempCreate;	
		ClientCreatedTenderPayload tempCreated, tempReturn;
		
		final RestTemplateBuilder builder = new RestTemplateBuilder();
		// scope is function postEiCreateTender
		RestTemplate restTemplate = builder.build();	
		tempCreate = clientCreateTender;	// as received

		/*
		 * Forward the @RequestBody as received,
		 * Wait for and return the @ReponseBody as received
		 */
		
		logger.info("before forwarding CLientCreateTender to TEUA " +
				tempCreate.toString());
		
		//	And forward to the TEUA
		restTemplate = builder.build();
		ClientCreatedTenderPayload result = restTemplate.postForObject
			("http://localhost:8080/teua/clientCreateTender", tempCreate,
					ClientCreatedTenderPayload.class);		
		
		return result;
	}
}