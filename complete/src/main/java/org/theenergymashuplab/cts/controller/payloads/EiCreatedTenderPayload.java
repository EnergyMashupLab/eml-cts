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

import org.theenergymashuplab.cts.ActorIdType;
import org.theenergymashuplab.cts.EiResponse;
import org.theenergymashuplab.cts.MarketOrderIdType;
import org.theenergymashuplab.cts.RefIdType;
import org.theenergymashuplab.cts.TenderIdType;

public class EiCreatedTenderPayload {
	private MarketOrderIdType marketOrderId = new MarketOrderIdType();
	
	private TenderIdType tenderId;
	private ActorIdType partyId;
	private ActorIdType counterPartyId;
	public EiResponse response;
//	public ArrayofResponses responses; NOT USED
	
	// Need clarification as to what this attribute refers to before changing or deleting
	private final RefIdType refId = new RefIdType();
	private RefIdType inResponseTo;  // May be more prudent to rename and use refID instead of this new attribute 

	/*
	 * Default constructor for JSON deserialization.
	 * TO DO change to zero Id values in ActorId and RefId constructors
	 */
	public EiCreatedTenderPayload()	{		
	}
	
	public EiCreatedTenderPayload(
			TenderIdType tenderId,
			ActorIdType partyId,
			ActorIdType counterPartyId,
			EiResponse response,
			RefIdType inResponseTo) {

		this.tenderId = tenderId;
		this.partyId = partyId;
		this.counterPartyId = counterPartyId;
		this.response = response;
	}

	public long getId() {
		return tenderId.value();
	}

	public void print() {		
		System.err.println(this);
	}
	
	@Override
	public String toString() {
		return "EiCreatedTenderPayload [marketOrderId=" + marketOrderId + ", tenderId=" + tenderId + ", partyId="
				+ partyId + ", counterPartyId=" + counterPartyId + ", response=" + response + ", refId=" + refId
				+ ", inResponseTo=" + inResponseTo + "]";
	}
	
	public EiResponse getResponse() {
		return response;
	}

	public void setResponse(EiResponse response) {
		this.response = response;
	}

	public TenderIdType getTenderId() {
		return tenderId;
	}

	public ActorIdType getPartyId() {
		return partyId;
	}

	public ActorIdType getCounterPartyId() {
		return counterPartyId;
	}

	public RefIdType getRefId() {
		return refId;
	}

	public MarketOrderIdType getMarketOrderId() {
		return marketOrderId;
	}

	public void setMarketOrderId(MarketOrderIdType marketOrderId) {
		this.marketOrderId = marketOrderId;
	}

	public RefIdType getInResponseTo() {
		return inResponseTo;
	}

	public void setInResponseTo(RefIdType inResponseTo) {
		this.inResponseTo = inResponseTo;
	}

	public void setTenderId(TenderIdType tenderId) {
		this.tenderId = tenderId;
	}

	public void setPartyId(ActorIdType partyId) {
		this.partyId = partyId;
	}

	public void setCounterPartyId(ActorIdType counterPartyId) {
		this.counterPartyId = counterPartyId;
	}
}
