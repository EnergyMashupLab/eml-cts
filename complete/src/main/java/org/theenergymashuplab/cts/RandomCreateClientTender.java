/*
 * Copyright 2019-2025 The Energy Mashup Lab
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

import java.time.Instant;
import java.util.Random;

import org.theenergymashuplab.cts.controller.TeuaRestController;
import org.theenergymashuplab.cts.controller.payloads.ClientCreateTenderPayload;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * 	Utility routine to generate random CreateClientTenderPayloads
 * 	TODO move to utilities package
 */

public class RandomCreateClientTender {
	
	@JsonIgnore
	final static Random rand = new Random();
	final boolean DEBUG_JSON = true;
	final int AUCTION_SEGMENT = 3;
	final int priceScale=100;	// one unit price is 1/100 of the base currency
	final int quantityScale=10;	// one unit quantity is 1/10h of the base unit
	
	// debug - effectively local constants pending richer constructor.
	/*
	 * Note that this used 2020-06-20; other code used 2020-05031
	 * New code uses 20250620
	 */
	public  Instant dtStart = Instant.parse("2025-06-20T00:00:00.00Z");
//	String[] instrumentNames;
//	String[] instrumentNamesFromInterval;
	Instant[] instants;
	String[] json;
	ClientCreateTenderPayload[] clientTenders;
	TeuaRestController teuaController = new TeuaRestController();
	final ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();

	RandomCreateClientTender()	{
		/*
		 *	Produces 50 ClientCreateTenderPayload objects and print to System.out.
		 *	(in original; adjusted to 20)
		 *	TODO should be an input or named constant.
		 *
		 *	initialize random generator in class attributes.
		 *	Local instance variable initializers and instance initializers are executed
		 *	after the constructor is invoked 
		 */
		instants = new Instant[24];	// array of time stamps for dtStart of 24 one hour intervals
		json = new String[50];
		clientTenders = new ClientCreateTenderPayload[50];
//		mapper.configure((SerializationConfig.Feature.valueOf("WRITE_DATES_AS_TIMESTAMPS")), false);		
		instants[0] = dtStart;
		for (int i = 1; i < 24; i++) {
			instants[i] = instants[i-1].plusSeconds(60*60);
		}
//		for (int i = 0; i < 24; i++)	{
////			instrumentNames[i] = new BridgeInstant(instants[i]).toInstrumentName();
//			instrumentNamesFromInterval[i] = new BridgeInterval(60, instants[i]).toInstrumentName();
//		}
		
		//	TODO BridgeInterval.toInstrumentNames() can address extended interval length encoding
		
		// print dtStart and instrument name from each bridgeInstant used BridgeInterval
//		for (int i = 0; i < 24; i++)	{
//			System.err.println("iteration " + i +
////				" name from BrInstant " + instrumentNames[i] +
//				" name from BrInterval " + instrumentNamesFromInterval[i] +
//				" " + instants[i].toString());
//		}

		//	print the json
		for (int i = 0; i < 20; i++)	{
			clientTenders[i] = randomTender(1,30, 50, 100);
			// Changed to not post the ClientCreateTenderPayloads
			// teuaController.postClientCreateTender("1", clientTenders[i]);
			try	{
				json[i] = mapper.writeValueAsString(clientTenders[i]);
			}	catch (JsonProcessingException e)	{
				System.err.println("CreateRandomClientTender: JsonProcessingException " + e);
				}
			// and print the json
			System.err.println("\n" + json[i] );
		}
		
	}
	
	RandomCreateClientTender(int hour, int howMany, int priceLower, int priceUpper,
				int quantityLower, int quantityUpper, int segment) {
		/*
		 *	Produces a specified ClientCreateTenderPayload objects and prints to System.out.
		 *	Hour is the hour of the day and shared by  all instruments.
		 *	Price and Quantity are uniform random variables in the stated ranges
		 */
//		instants = new Instant[24];
		json = new String[howMany];
		clientTenders = new ClientCreateTenderPayload[howMany];
		
		//	Compute start time for each tender
		Instant dtStart = Instant.parse("2025-06-20T00:00:00.00Z");	
		dtStart = dtStart.plusSeconds(hour * 60 * 60);	// the hour within the default day
		
		System.err.println("RandomCreateClientTender Constructor with Hour of Day " +  hour + "\n\n");
//		mapper.configure((SerializationConfig.Feature.valueOf("WRITE_DATES_AS_TIMESTAMPS")), false);

		//	generate howMany tenders and print json serialization
		for (int i = 0; i < howMany; i++) {
			/*
			 * randomTenderStart (a method in this class) creates the tenders with given start time
			 * Each generated tender is then serialized in JSON and printed.
			 */	
			clientTenders[i] = randomTenderStart(dtStart, priceLower, priceUpper, quantityLower, quantityUpper);
			//	teuaController.postClientCreateTender("1", clientTenders[i]);
			try {
				json[i] = mapper.writeValueAsString(clientTenders[i]);
			} catch (JsonProcessingException e) {
				System.err.println("CreateRandomClientTender: JsonProcessingException " + e);
			}
			// and print the json
			System.err.println(json[i]);
		}
	}

	RandomCreateClientTender(int howMany) {
		/*
		 *	Produces "howMany" ClientCreateTenderPayload objects and print to System.out.
		 *
		 *	initialize random generator in class attributes.
		 *	Local instance variable initializers and instance initializers are executed
		 *	after the constructor is invoked
		 */
		instants = new Instant[24];
		json = new String[howMany];
		clientTenders = new ClientCreateTenderPayload[howMany];
//		System.err.println("RandomCreateClientTender Constructor\n\n");
//		mapper.configure((SerializationConfig.Feature.valueOf("WRITE_DATES_AS_TIMESTAMPS")), false);
		instants[0] = dtStart;
		for (int i = 1; i < 24; i++) {
			instants[i] = instants[i - 1].plusSeconds(60 * 60);
		}

		//	TODO BridgeInterval.toInstrumentNames() can address extended interval length encoding

		//	print the json
		for (int i = 0; i < howMany; i++) {
			clientTenders[i] = randomTender(1,30, 50, 100);
			teuaController.postClientCreateTender("1", clientTenders[i]);
			try {
				json[i] = mapper.writeValueAsString(clientTenders[i]);
			} catch (JsonProcessingException e) {
				System.err.println("CreateRandomClientTender: JsonProcessingException " + e);
			}
			// and print the json
			System.err.println(json[i]);
		}
	}

	RandomCreateClientTender(int howMany, int priceLower, int priceUpper, int quantityLower, int quantityUpper) {
		/*
		 *	Produces a specified ClientCreateTenderPayload objects and print to System.out.
		 *
		 *	initialize random generator in class attributes.
		 *	Local instance variable initializers and instance initializers are executed
		 *	after the constructor is invoked
		 */
		instants = new Instant[24];
		json = new String[howMany];
		clientTenders = new ClientCreateTenderPayload[howMany];
		System.err.println("RandomCreateClientTender Constructor\n\n");
//		mapper.configure((SerializationConfig.Feature.valueOf("WRITE_DATES_AS_TIMESTAMPS")), false);
		instants[0] = dtStart;
		for (int i = 1; i < 24; i++) {
			instants[i] = instants[i - 1].plusSeconds(60 * 60);
		}

		//	TODO BridgeInterval.toInstrumentNames() can address extended interval length encoding

		//	print the json
		for (int i = 0; i < howMany; i++) {
			clientTenders[i] = randomTender(priceLower, priceUpper, quantityLower, quantityUpper);
//			teuaController.postClientCreateTender("1", clientTenders[i]);
			try {
				json[i] = mapper.writeValueAsString(clientTenders[i]);
			} catch (JsonProcessingException e) {
				System.err.println("CreateRandomClientTender: JsonProcessingException " + e);
			}
			// and print the json
			System.err.println(json[i]);
		}
	}

	/*
	 * Adjust random value generators in this method.
	 * Use values after priceScale and quantityScale have been applied.
	 */
	public ClientCreateTenderPayload randomTender(int priceLower, int priceUpper, int quantityLower, int quantityUpper)	{
//		int randQuantity = 50 + rand.nextInt(51); // random quantity from 50 to 100
		int randQuantity = quantityLower + rand.nextInt((quantityUpper-quantityLower) + 1);
//		Interval interval = new Interval(60, dtStart);	//	60 minute interval at dtStart
		long price; // 12 cents
		Instant startTime;
		ClientCreateTenderPayload randTender;
		
		startTime = instants[rand.nextInt(24)];
//		price = 10*(rand.nextInt(31) + 1);	//random price from 1..30 cents
		price = 10*(rand.nextInt((priceUpper-priceLower) + 1) + priceLower);

		SideType side = SideType.BUY;
		if (rand.nextInt(2) > 0)	{    // Why not `rand.nextInt(2) > 0` ?
			side = SideType.SELL;
		}

		/* original
		randTender = new ClientCreateTenderPayload(
				side, 
				randQuantity, 
				price, 
				startTime, 
				60*60);
		return randTender;
		*/
		
		randTender = new ClientCreateTenderPayload(
				13,
				side, 
				randQuantity, 
				price, 
				startTime, 
				60*60,
				AUCTION_SEGMENT);
		return randTender;
	}

	/*
	 * Adjust random value generators in this method.
	 * Use values after priceScale and quantityScale have been applied.
	 */
	public ClientCreateTenderPayload randomTenderStart(Instant start, int priceLower, int priceUpper,
				int quantityLower, int quantityUpper)	{
		long randQuantity = 1;
		long randPrice = 1;
		ClientCreateTenderPayload randTender;
		
		//	Initialize the parameters so each tender gets a random price and quantity
		randPrice = 	priceScale * (priceLower + rand.nextInt((priceUpper - priceLower) + 1));
		randQuantity = 	quantityScale * (quantityLower + rand.nextInt((quantityUpper - quantityLower) + 1));

		SideType side = SideType.BUY;
		if (rand.nextInt(2) > 0)	{
			side = SideType.SELL;
		}

		/* original
		randTender = new ClientCreateTenderPayload(
				side, 
				randQuantity, 
				price, 
				startTime, 
				60*60);
		return randTender;
		*/
		
		randTender = new ClientCreateTenderPayload(
				side, 
				randQuantity, 
				randPrice, 
				start, 
				60,
				AUCTION_SEGMENT);
		return randTender;
	}

}
