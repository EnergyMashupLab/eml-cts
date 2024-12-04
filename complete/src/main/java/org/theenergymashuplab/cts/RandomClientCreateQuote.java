package org.theenergymashuplab.cts;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.theenergymashuplab.cts.controller.TeuaRestController;
import org.theenergymashuplab.cts.controller.payloads.ClientCreateQuotePayload;
import org.theenergymashuplab.cts.controller.payloads.ClientCreatedQuotePayload;
import org.theenergymashuplab.cts.controller.payloads.EiCreateQuotePayload;
import org.theenergymashuplab.cts.controller.payloads.EiCreatedQuotePayload;
import org.springframework.web.client.RestTemplate;

import java.util.Random;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class RandomClientCreateQuote {

    private ActorIdType lmePartyId = null;
    private ActorIdType[] actorIds;			// ActorIdType values for the created actors
    /*
    private static final Logger logger = LogManager.getLogManager().getLogger(String.valueOf(TeuaRestController.class));
*/
    public RandomClientCreateQuote(){

        final Random rand = new Random();
        final boolean DEBUG_JSON = true;

        Integer numericTeuaId = -1;
        String positionUri;

        actorIds = new ActorIdType[10];

        //Make a http request to the lme party fo the actor ID?
        // Assignt he actorId t the EiQuotePayload?
        // scope is function postEiCreateTender
        RestTemplate restTemplate = new RestTemplate();

        if (lmePartyId == null)	{
            // builder = new RestTemplateBuilder();
            restTemplate = new RestTemplate();
            lmePartyId = restTemplate.getForObject(
                    "http://localhost:8080/lme/party",
                    ActorIdType.class);
        }

        numericTeuaId = 1;


        //convert to URI for position manager
        positionUri = "/position/"
                + actorIds[numericTeuaId] +
                "/getPosition";
        /*
        logger.debug("positionUri is " + positionUri);

        logger.debug("numericTeuaId is " + numericTeuaId +" String is " + "1");
        logger.debug("postEiCreateTender teuaId " +
                "1" +
                " actorNumericIds[teuaId] " +
                actorIds[numericTeuaId].toString());
*/
        //Intialize all the field that need to create for each Quote Object



        //1 Create the payload that hold the quotes
        ClientCreateQuotePayload tempClientPayload;
        ClientCreatedQuotePayload resultPayload;
        tempClientPayload = randomQuote();

        // 2 Create TenderDetail
        TenderDetail tenderDetail;
        tenderDetail = new TenderIntervalDetail(
                tempClientPayload.getInterval(),
                tempClientPayload.getPrice(),
                tempClientPayload.getQuantity()
        );

        // 3 Create EiQuoteType: creating a quote
        EiQuoteType quote;
        quote = new EiQuoteType(
                //The expire time is given to us by the client
                tempClientPayload.getBridgeExpireTime().asInstant(),
                //This should ALWAYS be sell
                tempClientPayload.getSide(),
                //Stuff in the interval tender
                tenderDetail
        );

        //Assign configuration settings
        //No execution instructions
        quote.setExecutionInstructions(null);
        //Not private -- we want to publish
        quote.setPrivateQuote(false);
        //Always energy for right now
        quote.setResourceDesignator(ResourceDesignatorType.ENERGY);
        //The quote is tradeable
        quote.setTradeable(true);

        // 4 Create a EiquotePayload that will be sent to the LMA

        EiCreateQuotePayload eiQuote;

        eiQuote = new EiCreateQuotePayload(quote, actorIds[numericTeuaId], this.lmePartyId);

        //Debug log here
        /*
        logger.trace("TEUA sending EiCreateTender to LMA " +
                eiQuote.toString());
                */


        //	And forward to the LMA
        restTemplate = new RestTemplate();
        EiCreatedQuotePayload result = restTemplate.postForObject
                ("http://localhost:8080/lma/createQuote", eiQuote,
                        EiCreatedQuotePayload.class);

        // and put CtsTenderId in ClientCreatedTenderPayload
        resultPayload = new ClientCreatedQuotePayload(result.getQuoteId().value());
        /*
        logger.trace("TEUA before return ClientCreatedTender to Client/SC " +
                resultPayload.toString());
*/

    }



    //This method will generate each quote and we will assign the value to the Quote Payload

    public ClientCreateQuotePayload randomQuote(){
        Random ran  = new Random();
        ClientCreateQuotePayload tempQuote;

        //Assign random quantity
        int quantity = 50 + ran.nextInt(50);

        //Assign Price
        long price = 120; // 12 cents
        price = 10*(ran.nextInt(29) + 1);

        // Assign side
        SideType side = SideType.SELL;
        if(ran.nextInt(100) < 50 ){
            side =  SideType.BUY;
        }

        tempQuote = new ClientCreateQuotePayload(side, price, quantity);

        return tempQuote;
    }




}

