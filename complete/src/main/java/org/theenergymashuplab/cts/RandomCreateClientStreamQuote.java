package org.theenergymashuplab.cts;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.theenergymashuplab.cts.controller.TeuaRestController;
import org.theenergymashuplab.cts.controller.payloads.ClientCreateStreamQuotePayload;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RandomCreateClientStreamQuote {
    @JsonIgnore
    final static Random rand = new Random();
    final boolean DEBUG_JSON = true;

    // debug - effectively local constants pending richer constructor.
    public  Instant dtStart = Instant.parse("2020-06-20T00:00:00.00Z");
    Instant[] instants;
    String[] json;
    ClientCreateStreamQuotePayload[] clientQuotes;
    TeuaRestController teuaController = new TeuaRestController();
    final ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();

    RandomCreateClientStreamQuote()	{
        /*
         *	Produces 50 ClientCreateStreamTenderPayload objects and print to System.out.
         *
         *	initialize random generator in class attributes.
         *	Local instance variable initializers and instance initializers are executed
         *	after the constructor is invoked
         */
        instants = new Instant[24];
        json = new String[50];
        clientQuotes = new ClientCreateStreamQuotePayload[50];
//		mapper.configure((SerializationConfig.Feature.valueOf("WRITE_DATES_AS_TIMESTAMPS")), false);
        instants[0] = dtStart;
        for (int i = 1; i < 24; i++) {
            instants[i] = instants[i-1].plusSeconds(60*60);
        }

        //	TODO BridgeInterval.toInstrumentNames() can address extended interval length encoding

        //	print the json
        for (int i = 0; i < 50; i++)	{
            clientQuotes[i] = randomQuote(6,1,30, 50, 100);
            teuaController.postClientCreateStreamQuote("1", clientQuotes[i]);
            try	{
                json[i] = mapper.writeValueAsString(clientQuotes[i]);
            }	catch (JsonProcessingException e)	{
                System.err.println("CreateRandomClientQuote: JsonProcessingException " + e);
            }
            // and print the json
            System.err.println("\n" + json[i] + "***");
        }

    }

    RandomCreateClientStreamQuote(int tenderQuantity, int streamQuantity)	{
        /*
         *	Produces 50 ClientCreateStreamTenderPayload objects and print to System.out.
         *  User is able to specify the number of streamIntervals the ClientCreateStreamTenderPayload objects
         *  contain.
         *
         *	initialize random generator in class attributes.
         *	Local instance variable initializers and instance initializers are executed
         *	after the constructor is invoked
         */
        instants = new Instant[24];
        json = new String[tenderQuantity];
        clientQuotes = new ClientCreateStreamQuotePayload[tenderQuantity];
//		mapper.configure((SerializationConfig.Feature.valueOf("WRITE_DATES_AS_TIMESTAMPS")), false);
        instants[0] = dtStart;
        for (int i = 1; i < 24; i++) {
            instants[i] = instants[i-1].plusSeconds(60*60);
        }

        //	TODO BridgeInterval.toInstrumentNames() can address extended interval length encoding

        //	print the json
        for (int i = 0; i < tenderQuantity; i++)	{
            clientQuotes[i] = randomQuote(streamQuantity,1,30, 50, 100);
            teuaController.postClientCreateStreamQuote("1", clientQuotes[i]);
            try	{
                json[i] = mapper.writeValueAsString(clientQuotes[i]);
            }	catch (JsonProcessingException e)	{
                System.err.println("CreateRandomClientQuote: JsonProcessingException " + e);
            }
            // and print the json
            System.err.println("\n" + json[i] + "***");
        }

    }

    RandomCreateClientStreamQuote(int tenderQuantity, int streamQuantity, int priceLower, int priceUpper, int quantityLower, int quantityUpper)	{
        /*
         *	Produces a specified number of ClientCreateStreamTenderPayload objects and print to System.out.
         *  User is able to specify the number of streamIntervals the ClientCreateStreamTenderPayload objects
         *  contain, and specify a range of prices and quantity of orders.
         *
         *	initialize random generator in class attributes.
         *	Local instance variable initializers and instance initializers are executed
         *	after the constructor is invoked
         */
        instants = new Instant[24];
        json = new String[tenderQuantity];
        clientQuotes = new ClientCreateStreamQuotePayload[tenderQuantity];
//		mapper.configure((SerializationConfig.Feature.valueOf("WRITE_DATES_AS_TIMESTAMPS")), false);
        instants[0] = dtStart;
        for (int i = 1; i < 24; i++) {
            instants[i] = instants[i-1].plusSeconds(60*60);
        }

        //	TODO BridgeInterval.toInstrumentNames() can address extended interval length encoding

        //	print the json
        for (int i = 0; i < tenderQuantity; i++)	{
            clientQuotes[i] = randomQuote(streamQuantity,priceLower,priceUpper, quantityLower, quantityUpper);
            teuaController.postClientCreateStreamQuote("1", clientQuotes[i]);
            try	{
                json[i] = mapper.writeValueAsString(clientQuotes[i]);
            }	catch (JsonProcessingException e)	{
                System.err.println("CreateRandomClientQuote: JsonProcessingException " + e);
            }
            // and print the json
            System.err.println("\n" + json[i] + "***");
        }

    }

    public ClientCreateStreamQuotePayload randomQuote(int quantity, int priceLower, int priceUpper, int quantityLower, int quantityUpper)	{
        long price;
        Instant timestamp = instants[rand.nextInt(24)];
        BridgeInstant startTime = new BridgeInstant(timestamp);
        BridgeInstant endTime = new BridgeInstant(timestamp.plusSeconds((quantity + 3) * 60*60)); // Probably needs to be fixed?

        String info = "ClientCreateStreamTenderPayload";
        long ctsQuoteId = 0;

        List<CtsStreamIntervalType> randCtsList = new ArrayList<CtsStreamIntervalType>();
        ClientCreateStreamQuotePayload randTender;

        SideType side = SideType.BUY;
        if (rand.nextInt(2) > 0)	{    // Why not `rand.nextInt(2) > 0` ?
            side = SideType.SELL;
        }

        for (int i = 0; i < quantity; i++) {
            randCtsList.add(randomCts(priceLower, priceUpper, quantityLower, quantityUpper));
        }

        randTender = new ClientCreateStreamQuotePayload(
                side,
                ctsQuoteId,
                randCtsList,
                startTime,
                endTime,
                60);
        return randTender;
    }

    public CtsStreamIntervalType randomCts(int priceLower, int priceUpper, int quantityLower, int quantityUpper)	{
        int randQuantity = quantityLower + rand.nextInt((quantityUpper-quantityLower) + 1);
        long price;
        int streamUid = 1;
        CtsStreamIntervalType randTender;
        price = 10*(rand.nextInt((priceUpper-priceLower) + 1) + priceLower);

        randTender = new CtsStreamIntervalType(
                price,
                randQuantity,
                streamUid);
        return randTender;
    }
}

//public ClientCreateStreamQuotePayload(SideType side, long ctsQuoteId, List<CtsStreamIntervalType> streamIntervals, BridgeInstant streamStart, BridgeInstant bridgeExpireTime, long intervalDurationInMinutes) {
//    this.side = side;
//    this.ctsQuoteId = ctsQuoteId;
//    this.streamIntervals = streamIntervals;
//    this.streamStart = streamStart;
//    this.bridgeExpireTime = bridgeExpireTime;
//    this.intervalDurationInMinutes = intervalDurationInMinutes;
//}
//        }