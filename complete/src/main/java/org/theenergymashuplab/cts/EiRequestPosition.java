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
 * Not used - superceded by revised PositionManager - may merge
 */

public class EiRequestPosition {
	private ActorIdType requestor;
	private ActorIdType positionParty;	// the party whose position is described
	private UidType request;
	private Interval boundingInterval;
	private UidType requestId = new UidType();

	/*
	 * Default constructor for JSON deserialization.
	 */
	public EiRequestPosition()	{
		
		this.requestor = new ActorIdType();
		this.positionParty = new ActorIdType();
		this.request = new UidType();
	}

	/* 
	 * Parallel to EiCreateTransaction, EiCreateTender, etc:
	 * 		pass an Interval
	 * 
	 * Add positionParty, requestorParty, and requestId for the message payload.
	 */

	public EiRequestPosition(ActorIdType positionParty, ActorIdType requestorParty, Interval boundingInterval) {

		this.boundingInterval = boundingInterval;
		this.positionParty = positionParty;
		this.requestor = requestorParty;
		this.request = new UidType();
		
		System.err.println("EiRequestPosition Constructor before this.print()" + this.toString());	
	}
	
	public String toString() {
		String printStringFormat = 
				"EiRequestPosition.print() partyId %d requestorPartyId %d requestId %d  dtStart %s duration %s";
			
		String.format(printStringFormat,
				positionParty.value(), 
				requestor.value(),
				request.value(),
				boundingInterval.dtStart.toString(),
				boundingInterval.duration.toString());
		
		return printStringFormat;
	}

	public ActorIdType getRequestor() {
		return requestor;
	}

	public void setRequestor(ActorIdType requestor) {
		this.requestor = requestor;
	}

	public ActorIdType getPositionParty() {
		return positionParty;
	}

	public void setPositionParty(ActorIdType positionParty) {
		this.positionParty = positionParty;
	}

	public UidType getRequest() {
		return request;
	}

	public void setRequest(UidType request) {
		this.request = request;
	}

	public Interval getBoundingInterval() {
		return boundingInterval;
	}

	public void setBoundingInterval(Interval boundingInterval) {
		this.boundingInterval = boundingInterval;
	}

	public UidType getRequestId() {
		return requestId;
	}

	public void setRequestId(UidType requestId) {
		this.requestId = requestId;
	}
	
	
}
