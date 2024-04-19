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

/*
 * This initial version produces a random Tender with the following characterics:
 * 		BUY or SELL - probability 50% for each
 * 		Quantity uniform random variable between 50 and 100
 * 		party and counterParty are random (and monotonically increasing so UNIQUE)
 * 		dtStart is fixed at 2020-03-15T10:00:00.00Z
 * 
 * Next version will use a new constructor that takes
		base and random range for quantity
		dtStart and duration for interval (as strings)
		Explicit partyId and counterPartyId
		
	In effect, creating a tender with
		given party and counterParty
		randomized quantity in range
		random BUY SELL
 * 
 * William Cox 202003
 * 
 * Apache 2.0 license - will be included in NIST-CTS-Agents
 * 
 */

package org.theenergymashuplab.cts;

import java.time.Instant;
import java.time.Duration;
import java.util.Random;

import com.fasterxml.jackson.annotation.JsonIgnore;

/*
 * This has more limited functionality than the Parity sim application, which is
 * designed to create random bids and asks
 */
public class RandomEiTender {

		@JsonIgnore
		final static Random rand = new Random();
		
		// debug - effectively local constants pending richer constructor.
		public  Instant dtStart = Instant.parse("2020-03-15T10:00:00.00Z");
		public static Duration duration = Duration.ZERO;

		private EiTenderType randTender;
		Interval interval = new Interval(60, dtStart);	//	60 minute interval at dtStart
		
		RandomEiTender()	{
			// initialize random generator in class attributes
			// Local instance variable initializers and instance initializers are executed
			// after the constructor is invoked
		}
		
		public EiTenderType randomTender()	{
			int randQuantity = 50 + rand.nextInt(50); // random quantity from 50 to 100
			Interval interval = new Interval(60, dtStart);	//	60 minute interval at dtStart
			long price = 12; // cents
			
			price = rand.nextInt(29) + 1;	//random price from 1..30 cents
			Instant exp = dtStart.plusSeconds(60*60*11);	// DEBUG 11 hours after dtStart

			SideType side = SideType.BUY;
			if (rand.nextInt(100) > 50)	{
				side = SideType.SELL;
			}
			
			randTender = new EiTenderType(interval, randQuantity, price, exp, side);	
			return randTender;
		}
		
		public Instant getDtStart() {
			return dtStart;
		}


		public void setDtStart(Instant dtStart) {
			this.dtStart = dtStart;
		}


		public static Duration getDuration() {
			return duration;
		}


		public static void setDuration(Duration duration) {
			RandomEiTender.duration = duration;
		}


		public EiTenderType getRandTender() {
			return randTender;
		}


		public void setRandTender(EiTenderType randTender) {
			this.randTender = randTender;
		}


		public Interval getInterval() {
			return interval;
		}


		public void setInterval(Interval interval) {
			this.interval = interval;
		}

/*
		public static Random getRand() {
			return rand;
		}
*/


	}
