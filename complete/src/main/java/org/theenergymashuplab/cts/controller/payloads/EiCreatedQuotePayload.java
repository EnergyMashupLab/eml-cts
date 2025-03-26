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

import java.sql.Ref;

import org.theenergymashuplab.cts.ActorIdType;
import org.theenergymashuplab.cts.EiResponse;
import org.theenergymashuplab.cts.EiResponseType;
import org.theenergymashuplab.cts.MarketOrderIdType;
import org.theenergymashuplab.cts.RefIdType;
import org.theenergymashuplab.cts.TenderIdType;

/**
 * @author crossover
 * @version 1.0
 * @created 28-Sep-2024 8:41:39 PM
 */
public class EiCreatedQuotePayload {

	public ActorIdType counterPartyId;
	public RefIdType inResponseTo;
	public MarketOrderIdType marketOrderId;
	public ActorIdType partyId;
	public TenderIdType quoteId;
	public EiResponse response;

	public EiCreatedQuotePayload() {

	}

	public EiCreatedQuotePayload(ActorIdType counterPartyId, RefIdType inResponseTo, MarketOrderIdType marketOrderId,
			ActorIdType partyId, TenderIdType quoteId, EiResponse response) {
		this.counterPartyId = counterPartyId;
		this.inResponseTo = inResponseTo;
		this.marketOrderId = marketOrderId;
		this.partyId = partyId;
		this.quoteId = quoteId;
		this.response = response;
	}

	public EiCreatedQuotePayload(ActorIdType counterPartyId, MarketOrderIdType marketOrderId, ActorIdType partyId,
			TenderIdType quoteId, EiResponse response) {
		this.counterPartyId = counterPartyId;
		this.marketOrderId = marketOrderId;
		this.partyId = partyId;
		this.quoteId = quoteId;
		this.response = response;
	}

	public ActorIdType getCounterPartyId() {
		return this.counterPartyId;
	}

	public void setCounterPartyId(ActorIdType counterPartyId) {
		this.counterPartyId = counterPartyId;
	}

	public RefIdType getInResponseTo() {
		return this.inResponseTo;
	}

	public void setInResponseTo(RefIdType inResponseTo) {
		this.inResponseTo = inResponseTo;
	}

	public MarketOrderIdType getMarketOrderId() {
		return this.marketOrderId;
	}

	public void setMarketQuoteId(MarketOrderIdType marketOrderId) {
		this.marketOrderId = marketOrderId;
	}

	public ActorIdType getPartyId() {
		return this.partyId;
	}

	public void setPartyId(ActorIdType partyId) {
		this.partyId = partyId;
	}

	public TenderIdType getQuoteId() {
		return this.quoteId;
	}

	public void setQuoteId(TenderIdType quoteId) {
		this.quoteId = quoteId;
	}

	public EiResponse getResponse() {
		return this.response;
	}

	public void setResponse(EiResponse response) {
		this.response = response;
	}

	@Override
	public String toString() {
		return "EiCreatedQuotePayload [" +
				"counterPartyId=" + this.counterPartyId.toString() +
				", inResonseTo=" + this.inResponseTo.toString() +
				", marketQuoteId=" + this.marketOrderId.toString() +
				", partyId=" + this.partyId.toString() +
				", quoteId=" + this.quoteId.toString() +
				", response=" + this.response.toString() + "]";
	}

}
