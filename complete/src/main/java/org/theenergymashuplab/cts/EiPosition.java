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

/*
 * ArrayList element for the EiReplyPosition payload -
 * 		a list of positions
 */
public class EiPosition {
	// the party whose position is described
	private ActorIdType positionParty;	
	private Interval interval;
	private long quantity;	// net position for interval
	// IMPLICIT the market, time granularity, and product
	
	EiPosition()	{
		// empty for JSON serialization
	}
	
	EiPosition(Interval interval, long quantity, ActorIdType positionParty)	{
		this.interval = interval;
		this.quantity = quantity;
		this.positionParty = positionParty;
	}

	public ActorIdType getPositionParty() {
		return positionParty;
	}

	public void setPositionParty(ActorIdType positionParty) {
		this.positionParty = positionParty;
	}

	public Interval getInterval() {
		return interval;
	}

	public void setInterval(Interval interval) {
		this.interval = interval;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	
	
}
