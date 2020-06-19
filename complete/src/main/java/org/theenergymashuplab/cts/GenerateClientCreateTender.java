package org.theenergymashuplab.cts;

import java.time.*;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;


public class GenerateClientCreateTender {

	public static void main(String[] args) {	
		final ObjectMapper mapper = new ObjectMapper();
		final Random rand = new Random();
		ClientCreateTenderPayload payload = null;
		SideType side;
		long quantity, price;
		long randQuantity;
		String jsonOut = null;
				
		for (int i = 0; i<20 ; i++) {
			
			side = SideType.BUY;
			if (rand.nextInt(100) > 50)	{
				side = SideType.SELL;
			}
			quantity = 50 + rand.nextInt(50); // random quantity from 50 to 100
			price = 100 * (1 + rand.nextInt(20)); // random price from 1 cent to 20 cents
			// System.err.println("GenerateClientCreateTender: Side " + side.toString() + " Quantity " + quantity + " price " + price/100);
			
			// Interval and expireTime are set in the constructor for ClientCreateTenderPayload
			// They will be inserted by the Client/SC prior to POSTing to the TEUA
			payload = new ClientCreateTenderPayload(side, quantity, price);
		
			try {
				jsonOut = null;
				jsonOut = mapper.writeValueAsString(payload);
				System.err.println(jsonOut);
				} catch (JsonProcessingException e) {
				System.err.println("GenerateClientCreateTender: Json Exception: Side " + side.toString() + 
							" Quantity " + quantity + " price " + price/100);
				e.printStackTrace();
			}
		}		
	}
}
