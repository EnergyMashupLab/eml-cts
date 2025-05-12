/*
 * Copyright 2019-2025 The Energy Mashup Lab
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.theenergymashuplab.cts.controller.payloads;

import org.theenergymashuplab.cts.ActorIdType;
import org.theenergymashuplab.cts.BridgeInstant;
import org.theenergymashuplab.cts.EiTenderType;
import org.theenergymashuplab.cts.MarketIdType;
import org.theenergymashuplab.cts.RefIdType;

public class EiCreateStreamTenderPayload {
	private String executionInstructions = "";
	private MarketIdType marketId = new MarketIdType(); // Should be provided externally
	private int segmentId = 1; // Assumed to always be one segment, so it's always one at the moment

	private ActorIdType counterPartyId;
	private ActorIdType partyId;
	private RefIdType requestId;
	private EiTenderType tender;

	/*
	 * @JsonIgnore private final Random rand = new Random();
	 */

	/*
	 * Default constructor for JSON deserialization. TO DO change to zero Id values in ActorId and RefId constructors
	 */
	public EiCreateStreamTenderPayload() {
		this.counterPartyId = new ActorIdType();
		this.partyId = new ActorIdType();
		this.requestId = new RefIdType();
	}

	/*
	 * Parallel for EiCreateTransaction, EiCreateTender, EiCreateStreamTender: pass in a completed Tender, stream tender
	 * or Transaction which includes through its Tender interval, quantity, price, or for EiCancelTender only the
	 * TenderId.
	 * 
	 * Add party, counterParty, and requestId for the message payload.
	 */
	public EiCreateStreamTenderPayload(EiTenderType tender, ActorIdType party, ActorIdType counterParty) {
		this.tender = tender;
		this.partyId = party;
		this.counterPartyId = counterParty;
		this.requestId = new RefIdType();
	}

	@Override
	public String toString() {
		return "EiCreateTenderPayload [executionInstructions=" + executionInstructions + ", marketId=" + marketId
				+ ", segmentId=" + segmentId + ", counterPartyId=" + counterPartyId + ", partyId=" + partyId
				+ ", requestId=" + requestId + ", tender=" + tender + "]";
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

	public EiTenderType getTender() {
		return tender;
	}

	public void setTender(EiTenderType tender) {
		this.tender = tender;
	}

	public String getExecutionInstructions() {
		return executionInstructions;
	}

	public void setExecutionInstructions(String executionInstructions) {
		this.executionInstructions = executionInstructions;
	}

	public MarketIdType getMarketId() {
		return marketId;
	}

	public void setMarketId(MarketIdType marketId) {
		this.marketId = marketId;
	}

	public int getSegmentId() {
		return segmentId;
	}

	public void setSegmentId(int segmentId) {
		this.segmentId = segmentId;
	}
}
