package org.theenergymashuplab.cts;

import java.time.*;

import java.time.Instant;
import java.time.Duration;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;

/*
 * 	Utility routine to generate random CreateClientTenderPayloads
 * 	TODO move to utilities package
 */

public class RandomCreateClientTender {
	
	@JsonIgnore
	final static Random rand = new Random();
	final boolean DEBUG_JSON = true;
	
	// debug - effectively local constants pending richer constructor.
	public  Instant dtStart = Instant.parse("2020-06-20T00:00:00.00Z");
	String[] instrumentNames = new String[24];
	String[] instrumentNamesFromInterval = new String[24];
	Instant[] instants = new Instant[24];
	String[] json = new String[50];
	ClientCreateTenderPayload[] clientTenders = new ClientCreateTenderPayload[50];
	final ObjectMapper mapper = new ObjectMapper();
	
	RandomCreateClientTender()	{
		/*
		 *	Produces 50 ClientCreateTenderPayload objects and print to System.out.
		 *
		 *	initialize random generator in class attributes.
		 *	Local instance variable initializers and instance initializers are executed
		 *	after the constructor is invoked 
		 */
		
		System.err.println("RandomCreateClientTender Constructor\n\n");
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
		for (int i = 0; i < 24; i++)	{	
			System.err.println("iteration " + i +
//				" name from BrInstant " + instrumentNames[i] +
				" name from BrInterval " + instrumentNamesFromInterval[i] + 
				" " + instants[i].toString());
			}

		//	print the json
		for (int i = 0; i < 50; i++)	{
			clientTenders[i] = randomTender();
			try	{
				json[i] = mapper.writeValueAsString(randomTender());		
			}	catch (JsonProcessingException e)	{
				System.err.println("CreateRandomClientTender: JsonProcessingException " + e);
				}				
			// and print the json
			System.err.println("\n" + json[i] + "***");
		}


		
	}
	
	public ClientCreateTenderPayload randomTender()	{
		int randQuantity = 50 + rand.nextInt(50); // random quantity from 50 to 100
//		Interval interval = new Interval(60, dtStart);	//	60 minute interval at dtStart
		long price = 120; // 12 cents
		Instant startTime;
		ClientCreateTenderPayload randTender;
		
		startTime = instants[rand.nextInt(24)];
		price = 10*(rand.nextInt(29) + 1);	//random price from 1..30 cents
		Instant exp = dtStart.plusSeconds(60*60*11);	// DEBUG 11 hours after dtStart

		SideType side = SideType.BUY;
		if (rand.nextInt(100) > 50)	{
			side = SideType.SELL;
		}
		
		randTender = new ClientCreateTenderPayload(
				side, 
				randQuantity, 
				price, 
				startTime, 
				60*60);
		
		return randTender;
	}

	

}
