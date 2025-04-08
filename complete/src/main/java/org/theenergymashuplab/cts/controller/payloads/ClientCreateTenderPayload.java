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

package org.theenergymashuplab.cts.controller.payloads;

import java.time.*;
//	import ...Interval; 

import org.theenergymashuplab.cts.BridgeInstant;
import org.theenergymashuplab.cts.BridgeInterval;
import org.theenergymashuplab.cts.Interval;
import org.theenergymashuplab.cts.SideType;

public class ClientCreateTenderPayload {
	private String info = "ClientCreateTenderPayload";
	private SideType side;
	private long quantity;
	private long price;
	private long ctsTenderId;
	private int segmentId = 1; // Default 1 for order  book for backwards compatability
	/*
	 * We can see either interval tenders or stream tenders with the latest September 2024 standard. To support this, 
	 * we'll set these attributes initially to be null and allow the JSON serialization to populate them if they appear 
	 * in the payload. For example, in the POST request, if we see a ctsStream object in the JSON, bridgeInterval and
	 * bridgeExpireTime will remain null whilst ctsStream is nonNull(if all goes well). This should allow us to take
	 * action based on what kind of tender we have.
	 */
	private BridgeInterval bridgeInterval;
	private BridgeInstant bridgeExpireTime;	
//	private boolean ignorePosition; TODO 1.01	

	// Uses BridgeInterval to avoid serialization issues
	
	ClientCreateTenderPayload()	{	// json
	}
	
	public ClientCreateTenderPayload(SideType side, long quantity, long price)	{
		// DEBUG start time and expiration time for test payloads
		Instant expire = null;
		// Earlier generated tenders used 2020-05-31T10:00:00.00Z; updated for Spring 2025 to 2025-06-20
		Instant dtStart = Instant.parse("2025-06-20T00:00:00.00Z");	
		expire = dtStart.plusSeconds(60*60*11);	// DEBUG 11 hours after dtStart
		// System.err.println("ClientCreateTenderPayload: expire " +
		//		expire.toString());
		
		this.side = side;
		this.quantity = quantity;
		this.price = price;
//		this.ignorePosition = true;	// TODO hook and use in TeuaRestController
		// DEBUG this.expireTime = expire;
		this.bridgeInterval = new BridgeInterval(60, dtStart);
		this.bridgeExpireTime = new BridgeInstant(expire);
	}
	
	/*
	 * TODO add segment attribute to constructor
	 */
	
	public ClientCreateTenderPayload(int hour, SideType side, long quantity, long price, int segmentId)	{
		// DEBUG start time and expiration time for test payloads
		// This constructor uses a fixed date plus the hour parameter as dtStart
		Instant expire = null;
		// Earlier generated tenders used 2020-05-31T10:00:00.00Z; updated for Spring 2025 to 2025-06-20
		Instant dtStart = Instant.parse("2025-06-20T00:00:00.00Z");	
		dtStart = dtStart.plusSeconds(hour * 60 * 60);	// adjust to the hour within the canonical day
		
		expire = dtStart.plusSeconds(60*60*11);	// DEBUG 11 hours after dtStart
		
		this.side = side;
		this.quantity = quantity;
		this.price = price;
//		this.ignorePosition = true;	// TODO hook and use in TeuaRestController
		// DEBUG this.expireTime = expire;
		this.bridgeInterval = new BridgeInterval(60, dtStart);
		this.bridgeExpireTime = new BridgeInstant(expire);
	}
	
	// Constructor takes interval description
	public ClientCreateTenderPayload(SideType side, long quantity, long price,
			Instant dtStart, long minutes, int segmentId)	{
		// DEBUG start time and expiration time for test payloads
		Instant expire;

		expire = dtStart.plusSeconds(60*60*11);	// DEBUG 11 hours after dtStart
		
		this.side = side;
		this.quantity = quantity;
		this.price = price;
		this.bridgeInterval = new BridgeInterval(60, dtStart);
		this.bridgeExpireTime = new BridgeInstant(expire);
		this.segmentId = segmentId;
	}
	
	// Constructor takes interval description
		public ClientCreateTenderPayload(int hour, SideType side, long quantity, long price,
				Instant dtStart, long minutes,int segmentId)	{
			// DEBUG start time and expiration time for test payloads
			Instant expire;

			dtStart = dtStart.plusSeconds(hour * 60 * 60);
			expire = dtStart.plusSeconds(60*60*11);	// DEBUG 11 hours after dtStart
			
			this.side = side;
			this.quantity = quantity;
			this.price = price;
			this.bridgeInterval = new BridgeInterval(60, dtStart);
			this.bridgeExpireTime = new BridgeInstant(expire);
			this.segmentId = segmentId;
		}

	// Constructor takes stream description
	//May need new constructor
	
	@Override
	public String toString()	{
		SideType tempSide = this.side;
		String tempString;

		tempString = (tempSide == SideType.BUY)? "B" : "S";	
		return (info + " side " + tempString + " quantity " +
				quantity + " price " + price);
	}
	
	public int getSegmentId() {
		return segmentId;
	}

	public void setSegmentId(int segmentId) {
		this.segmentId = segmentId;
	}

	public Interval getInterval()	{
		// converts internal representation to a CTS Interval
		Interval tempInterval = 
				new Interval(this.bridgeInterval.getDurationInMinutes(),
				this.bridgeInterval.getDtStart().asInstant());
		return tempInterval;
	}

	public String getInfo() { 
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public SideType getSide() {
		return side;
	}


	public void setSide(SideType side) {
		this.side = side;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public long getCtsTenderId() {
		return ctsTenderId;
	}

	public void setCtsTenderId(long ctsTenderId) {
		this.ctsTenderId = ctsTenderId;
	}

	public BridgeInterval getBridgeInterval() {
		return bridgeInterval;
	}

	public void setBridgeInterval(BridgeInterval bridgeInterval) {
		this.bridgeInterval = bridgeInterval;
	}

	public BridgeInstant getBridgeExpireTime() {
		return bridgeExpireTime;
	}

	public void setBridgeExpireTime(BridgeInstant bridgeExpireTime) {
		this.bridgeExpireTime = bridgeExpireTime;
	}

//	public boolean isIgnorePosition() {
//		return ignorePosition;
//	}
//
//	public void setIgnorePosition(boolean ignorePosition) {
//		this.ignorePosition = ignorePosition;
//	}
//	
}
