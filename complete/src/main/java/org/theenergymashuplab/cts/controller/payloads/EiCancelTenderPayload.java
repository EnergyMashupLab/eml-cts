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
import org.theenergymashuplab.cts.MarketOrderIdType;
import org.theenergymashuplab.cts.RefIdType;
import org.theenergymashuplab.cts.TenderIdType;

public class EiCancelTenderPayload {
	private ActorIdType counterPartyId;
	private ActorIdType partyId;
	private RefIdType requestId;
	// Standard has 1..* TenderIdTypes TODO
	private TenderIdType tenderId;
	private MarketOrderIdType marketOrderId;
	
	/*
	 * Default constructor for JSON deserialization.
	 * TODO change to zero Id values in ActorId and RefId constructors
	 */
	public EiCancelTenderPayload()	{
		this.counterPartyId = new ActorIdType();
		this.partyId = new ActorIdType();
		this.requestId = new RefIdType();
		this.tenderId = new TenderIdType();
	}

	/* 
	 * Parallel for EiCreateTransaction, EiCreateTender:
	 * 		pass in a completed Tender/Transaction which includes through its Tender
	 * 		interval, quantity, price, or for EiCancelTender only the TenderIdType.
	 * Add party, counterParty, and requestId for the message payload.
	 */
	public EiCancelTenderPayload(TenderIdType tenderId, ActorIdType party, ActorIdType counterParty, MarketOrderIdType marketOrderId) {
		this.tenderId = tenderId;
		this.partyId = party;
		this.counterPartyId = counterParty;
		this.requestId = new RefIdType();
		this.marketOrderId = marketOrderId;
	}

	public void print() {
		System.err.println(this);
	}
	
	@Override
	public String toString() {
		return "EiCancelTenderPayload [counterPartyId=" + counterPartyId + ", partyId=" + partyId + ", requestId="
				+ requestId + ", tenderId=" + tenderId + ", marketOrderId=" + marketOrderId + "]";
	}

	public ActorIdType getCounterPartyId() {
		return counterPartyId;
	}

	public void setCounterPartyId(ActorIdType counterPartyId) {
		this.counterPartyId = counterPartyId;
	}

	public ActorIdType getPartyId() {
		return partyId;
	}

	public void setPartyId(ActorIdType partyId) {
		this.partyId = partyId;
	}

	public RefIdType getRequestId() {
		return requestId;
	}

	public void setRequestId(RefIdType requestId) {
		this.requestId = requestId;
	}

	public TenderIdType getTenderId() {
		return tenderId;
	}

	public void setTenderIdType(TenderIdType tenderId) {
		this.tenderId = tenderId;
	}

	public MarketOrderIdType getMarketOrderId() {
		return marketOrderId;
	}

	public void setMarketOrderId(MarketOrderIdType marketOrderId) {
		this.marketOrderId = marketOrderId;
	}

	public void setTenderId(TenderIdType tenderId) {
		this.tenderId = tenderId;
	}
	
}
