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
 * written by: Dhruvin Desai 202005
 */
package org.theenergymashuplab.cts.controller.payloads;

import org.theenergymashuplab.cts.Interval;

public class PositionAddPayload {

	Interval interval;
	long quantity;
	
	// Class constructor.
	public PositionAddPayload(Interval tinterval, long tquantity){
		this.interval = tinterval;
		this.quantity = tquantity;
	}
	
	// Default Constructor.
	public PositionAddPayload() {
		this.interval = null;
		this.quantity = 0;
	}
	/**
	 * @return the interval
	 */
	public Interval getInterval() {
		return interval;
	}
	/**
	 * @param interval the interval to set
	 */
	public void setInterval(Interval interval) {
		this.interval = interval;
	}
	/**
	 * @return the quantity
	 */
	public long getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	
	
	@Override
	public String toString() {
		return "PositionAddPayload [interval=" + interval + ", quantity=" + quantity + "]";
	}
	
}
