package org.theenergymashuplab.cts;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.theenergymashuplab.cts.controller.TeuaRestController;
import org.theenergymashuplab.cts.controller.payloads.ClientCreateStreamTenderPayload;
import org.theenergymashuplab.cts.controller.payloads.ClientCreateTenderPayload;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RandomCreateClientStreamTender {
    @JsonIgnore
    final static Random rand = new Random();
    final boolean DEBUG_JSON = true;

    // debug - effectively local constants pending richer constructor.
    public  Instant dtStart = Instant.parse("2020-06-20T00:00:00.00Z");
    Instant[] instants;
    String[] json;
    ClientCreateStreamTenderPayload[] clientTenders;
    TeuaRestController teuaController = new TeuaRestController();
    final ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();

    RandomCreateClientStreamTender()	{
        /*
         *	Produces 50 ClientCreateStreamTenderPayload objects and print to System.out.
         *
         *	initialize random generator in class attributes.
         *	Local instance variable initializers and instance initializers are executed
         *	after the constructor is invoked
         */
        instants = new Instant[24];
        json = new String[50];
        clientTenders = new ClientCreateStreamTenderPayload[50];
//		mapper.configure((SerializationConfig.Feature.valueOf("WRITE_DATES_AS_TIMESTAMPS")), false);
        instants[0] = dtStart;
        for (int i = 1; i < 24; i++) {
            instants[i] = instants[i-1].plusSeconds(60*60);
        }

        //	TODO BridgeInterval.toInstrumentNames() can address extended interval length encoding

        //	print the json
        for (int i = 0; i < 50; i++)	{
            clientTenders[i] = randomTender(6,1,30, 50, 100);
            teuaController.postClientCreateStreamTender("1", clientTenders[i]);
            try	{
                json[i] = mapper.writeValueAsString(clientTenders[i]);
            }	catch (JsonProcessingException e)	{
                System.err.println("CreateRandomClientTender: JsonProcessingException " + e);
            }
            // and print the json
            System.err.println("\n" + json[i] + "***");
        }

    }

    public ClientCreateStreamTenderPayload randomTender(int quantity, int priceLower, int priceUpper, int quantityLower, int quantityUpper)	{
//        int randQuantity = quantityLower + rand.nextInt((quantityUpper-quantityLower) + 1);
        long price;
        BridgeInstant startTime = new BridgeInstant(dtStart);
        BridgeInstant endTime = new BridgeInstant(instants[rand.nextInt(24)]);

        String info = "ClientCreateStreamTenderPayload";
        long ctsTenderId = 0;

        List<CtsStreamIntervalType> randCtsList = new ArrayList<CtsStreamIntervalType>();
        ClientCreateStreamTenderPayload randTender;

        SideType side = SideType.BUY;
        if (rand.nextInt(2) > 0)	{    // Why not `rand.nextInt(2) > 0` ?
            side = SideType.SELL;
        }

        for (int i = 0; i < quantity; i++) {
            randCtsList.add(randomCts(priceLower, priceUpper, quantityLower, quantityUpper));
        }

        randTender = new ClientCreateStreamTenderPayload(
                info,
                side,
                ctsTenderId,
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
//ClientCreateStreamTenderPayload(
//        info,
//        SideType side,
//        long ctsTenderId,
//        List<CtsStreamIntervalType> streamIntervals,
//        BridgeInstant streamStart,
//        BridgeInstant bridgeExpireTime,
//        long intervalDurationInMinutes)

//public CtsStreamIntervalType(
//        long streamIntervalPrice,
//        long streamIntervalQuantity,
//        int streamUid)

//{
//        "info": "ClientCreateStreamTenderPayload",
//        "side": "BUY",
//        "ctsTenderId": 0,
//        "streamIntervals": [
//        {"streamIntervalPrice":190, "streamIntervalQuantity":50, "streamUid": 1},
//        {"streamIntervalPrice":110, "streamIntervalQuantity":20, "streamUid": 1},
//        {"streamIntervalPrice":123, "streamIntervalQuantity":23, "streamUid": 1},
//        {"streamIntervalPrice":123, "streamIntervalQuantity":23, "streamUid": 1},
//        {"streamIntervalPrice":112, "streamIntervalQuantity":33, "streamUid": 1},
//        {"streamIntervalPrice":200, "streamIntervalQuantity":53, "streamUid": 1}
//        ],
//        "streamStart":{"instantString": "2020-06-20T08:00:00Z"},
//        "intervalDurationInMinutes": 60,
//        "bridgeExpireTime": {
//        "instantString": "2020-06-20T19:00:00Z"
//        }
//        }