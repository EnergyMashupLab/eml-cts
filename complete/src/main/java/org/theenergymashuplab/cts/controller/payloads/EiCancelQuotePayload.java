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

import java.util.ArrayList;

import org.theenergymashuplab.cts.ActorIdType;
import org.theenergymashuplab.cts.MarketOrderIdType;
import org.theenergymashuplab.cts.RefIdType;
import org.theenergymashuplab.cts.TenderIdType;

/**
 * @author crossover
 * @version 1.0
 * @created 28-Sep-2024 8:41:39 PM
 */
public class EiCancelQuotePayload {

	public ActorIdType counterPartyId;
	// A list of marketQuoteIds to cancel
	public ArrayList<MarketOrderIdType> marketQuoteIds;
	public ActorIdType partyId;
	private ArrayList<TenderIdType> quoteIds;
	public RefIdType requestId;

	public EiCancelQuotePayload() {

	}

	public EiCancelQuotePayload(ActorIdType counterPartyId, ArrayList<MarketOrderIdType> marketQuoteIds,
			ActorIdType partyId, ArrayList<TenderIdType> quoteIds, RefIdType requestId) {
		this.counterPartyId = counterPartyId;
		this.marketQuoteIds = marketQuoteIds;
		this.partyId = partyId;
		this.quoteIds = quoteIds;
		this.requestId = requestId;
	}

	public ActorIdType getCounterPartyId() {
		return this.counterPartyId;
	}

	public void setCounterPartyId(ActorIdType counterPartyId) {
		this.counterPartyId = counterPartyId;
	}

	public ArrayList<MarketOrderIdType> getMarketQuoteIds() {
		return this.marketQuoteIds;
	}

	public void setMarketQuoteIds(ArrayList<MarketOrderIdType> marketQuoteIds) {
		this.marketQuoteIds = marketQuoteIds;
	}

	public ActorIdType getPartyId() {
		return this.partyId;
	}

	public void setPartyId(ActorIdType partyId) {
		this.partyId = partyId;
	}

	public ArrayList<TenderIdType> getQuoteIds() {
		return this.quoteIds;
	}

	public void setQuoteIds(ArrayList<TenderIdType> quoteIds) {
		this.quoteIds = quoteIds;
	}

	public RefIdType getRequestId() {
		return this.requestId;
	}

	public void setRequestId(RefIdType requestId) {
		this.requestId = requestId;
	}

	@Override
	public String toString() {
		return "EiCancelQuotePayload [" +
				"counterPartyId=" + this.counterPartyId.toString() +
				", marketQuoteIds=" + this.marketQuoteIds.toString() +
				", partyId=" + this.partyId.toString() +
				", quoteIds=" + this.quoteIds.toString() +
				", requestId=" + this.requestId.toString() + "]";
	}
}
