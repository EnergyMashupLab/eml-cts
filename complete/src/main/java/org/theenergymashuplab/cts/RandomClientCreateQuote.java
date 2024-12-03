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

        //Create the payload that hold the quotes
        ClientCreateQuotePayload tempClientPayload;
        ClientCreatedQuotePayload resultPayload;

    }

    //This method will generate each quote and we will assign the value to the

    public ClientCreateQuotePayload randomQuote(){

        ClientCreateQuotePayload tempQuote;

        return
    }




}

