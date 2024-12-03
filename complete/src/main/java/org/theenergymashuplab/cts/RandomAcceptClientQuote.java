package org.theenergymashuplab.cts;

import java.time.Instant;
import java.util.Random;

import org.theenergymashuplab.cts.controller.TeuaRestController;
import org.theenergymashuplab.cts.controller.payloads.ClientAcceptQuotePayload;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RandomAcceptClientQuote {
    @JsonIgnore
    final static Random rand = new Random();
    final boolean DEBUG_JSON = true;

    // debug - effectively local constants pending richer constructor.
    public  Instant dtStart = Instant.parse("2020-06-20T00:00:00.00Z");
    //	String[] instrumentNames;
//	String[] instrumentNamesFromInterval;
    Instant[] instants;
    String[] json;
    ClientAcceptQuotePayload[] clientQuotes;
    TeuaRestController teuaController = new TeuaRestController();
    final ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();

    RandomAcceptClientQuote()	{
        /*
         *	Produces 50 ClientCreateTenderPayload objects and print to System.out.
         *
         *	initialize random generator in class attributes.
         *	Local instance variable initializers and instance initializers are executed
         *	after the constructor is invoked
         */
        // QuoteId gets too large, so it can't just be guessed for this. A list of possible values is
        // needed for this to be useful.
        instants = new Instant[24];
        json = new String[50];
        clientQuotes = new ClientAcceptQuotePayload[50];
//		mapper.configure((SerializationConfig.Feature.valueOf("WRITE_DATES_AS_TIMESTAMPS")), false);
        instants[0] = dtStart;
        for (int i = 1; i < 24; i++) {
            instants[i] = instants[i-1].plusSeconds(60*60);
        }

        //	TODO BridgeInterval.toInstrumentNames() can address extended interval length encoding

        //	print the json
        for (int i = 0; i < 50; i++)	{
            clientQuotes[i] = randomQuote(1,30, 50, 100);
            teuaController.postClientAcceptQuote("1", clientQuotes[i]);
            try	{
                json[i] = mapper.writeValueAsString(clientQuotes[i]);
            }	catch (JsonProcessingException e)	{
                System.err.println("AcceptRandomClientQuote: JsonProcessingException " + e);
            }
            // and print the json
            System.err.println("\n" + json[i] + "***");
        }

    }

    public ClientAcceptQuotePayload randomQuote(int priceLower, int priceUpper, int quantityLower, int quantityUpper)	{
//		int randQuantity = 50 + rand.nextInt(51); // random quantity from 50 to 100
        int randQuantity = quantityLower + rand.nextInt((quantityUpper-quantityLower) + 1);
//		Interval interval = new Interval(60, dtStart);	//	60 minute interval at dtStart
        long price;
        long tempReferencedQuoteId = 0;
        Instant startTime;
        ClientAcceptQuotePayload randQuote;

        startTime = instants[rand.nextInt(24)];
        price = 10*(rand.nextInt((priceUpper-priceLower) + 1) + priceLower);

        SideType side = SideType.BUY;
        if (rand.nextInt(2) > 0)	{    // Why not `rand.nextInt(2) > 0` ?
            side = SideType.SELL;
        }

        randQuote = new ClientAcceptQuotePayload(
                tempReferencedQuoteId,
                randQuantity,
                price);
        return randQuote;
    }

}