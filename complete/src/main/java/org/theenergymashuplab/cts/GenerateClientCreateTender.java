/*
 * Copyright 2019-2020 The Energy Mashup Lab
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

import java.util.Random;

import org.theenergymashuplab.cts.controller.payloads.ClientCreateTenderPayload;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * Carryover from previous investigatory code. 
 * TODO Candidate for deletion in future release
 * 
 * Implemented to generate ClientCreateTender payloads in JSON
 * TODO candidate for rationalization with other generators e,g,
 * Random*
 */
public class GenerateClientCreateTender {

	public static void main(String[] args) {	
		final ObjectMapper mapper = new ObjectMapper();
		final Random rand = new Random();
		ClientCreateTenderPayload payload = null;
		SideType side;
		long quantity, price;
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
