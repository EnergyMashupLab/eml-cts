package org.theenergymashuplab.cts;

import java.time.Instant;
import java.util.Random;

import org.theenergymashuplab.cts.controller.TeuaRestController;
import org.theenergymashuplab.cts.controller.payloads.ClientCreateQuotePayload;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RandomCreateClientQuote {
    @JsonIgnore
    final static Random rand = new Random();
    final boolean DEBUG_JSON = true;

    // debug - effectively local constants pending richer constructor.
    public  Instant dtStart = Instant.parse("2020-06-20T00:00:00.00Z");
    //	String[] instrumentNames;
//	String[] instrumentNamesFromInterval;
    Instant[] instants;
    String[] json;
    ClientCreateQuotePayload[] clientQuotes;
    TeuaRestController teuaController = new TeuaRestController();
    final ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();

    RandomCreateClientQuote()	{
        /*
         *	Produces 50 ClientCreateTenderPayload objects and print to System.out.
         *
         *	initialize random generator in class attributes.
         *	Local instance variable initializers and instance initializers are executed
         *	after the constructor is invoked
         */
        instants = new Instant[24];
        json = new String[50];
        clientQuotes = new ClientCreateQuotePayload[50];
//		mapper.configure((SerializationConfig.Feature.valueOf("WRITE_DATES_AS_TIMESTAMPS")), false);
        instants[0] = dtStart;
        for (int i = 1; i < 24; i++) {
            instants[i] = instants[i-1].plusSeconds(60*60);
        }

        //	TODO BridgeInterval.toInstrumentNames() can address extended interval length encoding

        //	print the json
        for (int i = 0; i < 50; i++)	{
            clientQuotes[i] = randomQuote(1,30, 50, 100);
            teuaController.postClientCreateQuote("1", clientQuotes[i]);
            try	{
                json[i] = mapper.writeValueAsString(clientQuotes[i]);
            }	catch (JsonProcessingException e)	{
                System.err.println("CreateRandomClientQuote: JsonProcessingException " + e);
            }
            // and print the json
            System.err.println("\n" + json[i] + "***");
        }

    }


    RandomCreateClientQuote(int quantity) {
        /*
         *	Produces a specified ClientCreateTenderPayload objects and print to System.out.
         *
         *	initialize random generator in class attributes.
         *	Local instance variable initializers and instance initializers are executed
         *	after the constructor is invoked
         */
        instants = new Instant[24];
        json = new String[quantity];
        clientQuotes = new ClientCreateQuotePayload[quantity];
//		System.err.println("RandomCreateClientTender Constructor\n\n");
//		mapper.configure((SerializationConfig.Feature.valueOf("WRITE_DATES_AS_TIMESTAMPS")), false);
        instants[0] = dtStart;
        for (int i = 1; i < 24; i++) {
            instants[i] = instants[i - 1].plusSeconds(60 * 60);
        }

        //	TODO BridgeInterval.toInstrumentNames() can address extended interval length encoding

        //	print the json
        for (int i = 0; i < quantity; i++) {
            clientQuotes[i] = randomQuote(1,30, 50, 100);
            teuaController.postClientCreateQuote("1", clientQuotes[i]);
            try {
                json[i] = mapper.writeValueAsString(clientQuotes[i]);
            } catch (JsonProcessingException e) {
                System.err.println("CreateRandomClientTender: JsonProcessingException " + e);
            }
            // and print the json
            System.err.println("\n" + json[i] + "***");
        }
    }

    RandomCreateClientQuote(int quantity, int priceLower, int priceUpper, int quantityLower, int quantityUpper) {
        /*
         *	Produces a specified ClientCreateTenderPayload objects and print to System.out.
         *
         *	initialize random generator in class attributes.
         *	Local instance variable initializers and instance initializers are executed
         *	after the constructor is invoked
         */
        instants = new Instant[24];
        json = new String[quantity];
        clientQuotes = new ClientCreateQuotePayload[quantity];
        System.err.println("RandomCreateClientTender Constructor\n\n");
//		mapper.configure((SerializationConfig.Feature.valueOf("WRITE_DATES_AS_TIMESTAMPS")), false);
        instants[0] = dtStart;
        for (int i = 1; i < 24; i++) {
            instants[i] = instants[i - 1].plusSeconds(60 * 60);
        }

        //	TODO BridgeInterval.toInstrumentNames() can address extended interval length encoding

        //	print the json
        for (int i = 0; i < quantity; i++) {
            clientQuotes[i] = randomQuote(priceLower, priceUpper, quantityLower, quantityUpper);
            teuaController.postClientCreateQuote("1", clientQuotes[i]);
            try {
                json[i] = mapper.writeValueAsString(clientQuotes[i]);
            } catch (JsonProcessingException e) {
                System.err.println("CreateRandomClientTender: JsonProcessingException " + e);
            }
            // and print the json
            System.err.println("\n" + json[i] + "***");
        }
    }


    public ClientCreateQuotePayload randomQuote(int priceLower, int priceUpper, int quantityLower, int quantityUpper)	{
//		int randQuantity = 50 + rand.nextInt(51); // random quantity from 50 to 100
        int randQuantity = quantityLower + rand.nextInt((quantityUpper-quantityLower) + 1);
//		Interval interval = new Interval(60, dtStart);	//	60 minute interval at dtStart
        long price;
        Instant startTime;
        ClientCreateQuotePayload randQuote;

        startTime = instants[rand.nextInt(24)];
        price = 10*(rand.nextInt((priceUpper-priceLower) + 1) + priceLower);

        SideType side = SideType.BUY;
        if (rand.nextInt(2) > 0)	{    // Why not `rand.nextInt(2) > 0` ?
            side = SideType.SELL;
        }

        randQuote = new ClientCreateQuotePayload(
                side,
                randQuantity,
                price,
                startTime,
                60*60);
        return randQuote;
    }

}
//	public ClientCreateQuotePayload(SideType side, long quantity, long price,
//			Instant dtStart, long minutes)


//{"info":"ClientCreateQuotePayload",
//        "side":"BUY",
//        "quantity":54,
//        "price":190,
//        "ctsTenderId":0,
//        "bridgeInterval":{"durationInMinutes":60, "dtStart":{"instantString":"2020-06-20T05:00:00Z"}},
//        "bridgeExpireTime":{"instantString":"2020-06-20T16:00:00Z"}}