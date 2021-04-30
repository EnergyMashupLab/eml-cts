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

// TYPES updated not used - see dao/EiTenderType.java

import java.time.*;

public class EiTender {
	private final TenderIdType tenderId = new TenderIdType();	// new tender id on construction
	private Interval interval;
	private Instant expirationTime;
	private long quantity = 0;
	private long price = 0;	// cents initially. Parity uses long with number of fractional digits
	private SideType side;
	private final boolean integralOnly = false;	// TODO not set, not serialized but comes through as false. Verify behavior.
	private TransactiveState transactiveState = TransactiveState.TENDER;
	
	
	public EiTender() {
		// TODO Auto-generated constructor stub
	}
	/* 
		Attributes OMITTED from the UML Model at 
			currency
			marketContext
			terms
			emixUid
			envelopeContents
			
		Attributes ADDED to the visible UML model (from StreamPayloadBaseType) 
			price
			time interval
	*/

	public EiTender(Interval interval, long quantity, long price, Instant expirationTime, SideType side) {
		this.interval = interval;
		this.quantity = quantity;
		this.price = price;
		this.expirationTime = expirationTime;
		this.side = side;	// side.BUY or side.SELL
		/*
		 * other attributes are set to defaults
		 * 
		 * TODO reorder constructor parameters to mimic parity terminal client -
		 * 		side, quantity, instrument, price
		 */
		
	}


	@Override
	public String toString()	{
		// TODO replace with concatenated strings and toStrings
		String printStringFormat = 
				"EiTender tenderId %d quantity %d price cents %d side %s integralOnly %s expirationTime %s dtStart %s duration %s";

		return (String.format(printStringFormat, tenderId.value(),quantity, price, side,integralOnly,
				expirationTime.toString(),interval.dtStart.toString(),interval.duration.toString()));
	}	
	
	public TenderIdType getTenderId() {
		return tenderId;
	}

	public Instant getExpireTime() {
		return expirationTime;
	}

	public void setExpireTime(Instant expirationTime) {
		this.expirationTime = expirationTime;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public TransactiveState getTransactiveState() {
		return transactiveState;
	}

	public void setTransactiveState(TransactiveState transactiveState) {
		this.transactiveState = transactiveState;
	}

	public void setInterval(Interval interval) {
		this.interval =  interval;
	}
	
	public Interval getInterval() {
		return interval;
	}

	public SideType getSide() {
		return side;
	}

	public void setSide(SideType side) {
		this.side = side;
	}

	public boolean isIntegralOnly() {
		return integralOnly;
	}
	
	public Instant getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(Instant expirationTime) {
		this.expirationTime = expirationTime;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}
}
