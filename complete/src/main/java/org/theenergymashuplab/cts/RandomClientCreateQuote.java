package org.theenergymashuplab.cts;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.theenergymashuplab.cts.controller.payloads.ClientCreateQuotePayload;
import org.theenergymashuplab.cts.controller.payloads.ClientCreatedQuotePayload;

import java.util.Random;

public class RandomClientCreateQuote {

    public RandomClientCreateQuote(){
        @JsonIgnore
        final static Random rand = new Random();
        final boolean DEBUG_JSON = true;

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

    // Create a EiquoteType method






}

