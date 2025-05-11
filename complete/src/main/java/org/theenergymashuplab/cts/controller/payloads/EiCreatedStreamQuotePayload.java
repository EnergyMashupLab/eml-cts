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

import org.theenergymashuplab.cts.*;

import java.util.List;

public class EiCreatedStreamQuotePayload {
    private MarketOrderIdType marketOrderId = new MarketOrderIdType();
    private ActorIdType partyId;
    private ActorIdType counterPartyId;
    public EiResponseType response;
    private List<EiQuoteType> createdQuotes;

    // Need clarification as to what this attribute refers to before changing or deleting
    private final RefIdType refId = new RefIdType();
    private RefIdType inResponseTo; // May be more prudent to rename and use refID instead of this new attribute

    /*
     * Default constructor for JSON deserialization. TO DO change to zero Id values in ActorId and RefId constructors
     */
    public EiCreatedStreamQuotePayload() {}

    public EiCreatedStreamQuotePayload(MarketOrderIdType marketOrderId, ActorIdType partyId, ActorIdType counterPartyId,
            EiResponseType response, List<EiQuoteType> createdQuotes, RefIdType inResponseTo) {
        this.marketOrderId = marketOrderId;
        this.partyId = partyId;
        this.counterPartyId = counterPartyId;
        this.response = response;
        this.createdQuotes = createdQuotes;
        this.inResponseTo = inResponseTo;
    }

    public void print() {
        System.err.println(this);
    }

    public List<EiQuoteType> getCreatedQuotes() {
        return this.createdQuotes;
    }

    public void setCreatedQuotes(List<EiQuoteType> createdQuotes) {
        this.createdQuotes = createdQuotes;
    }

    @Override
    public String toString() {
        return "EiCreatedQuotePayload [marketOrderId=" + marketOrderId + ", partyId=" + partyId + ", counterPartyId="
                + counterPartyId + ", response=" + response + ", refId=" + refId + ", inResponseTo=" + inResponseTo
                + ", createdTenders=" + createdQuotes + "]";
    }

    public EiResponseType getResponse() {
        return response;
    }

    public void setResponse(EiResponseType response) {
        this.response = response;
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

    public void setPartyId(ActorIdType partyId) {
        this.partyId = partyId;
    }

    public void setCounterPartyId(ActorIdType counterPartyId) {
        this.counterPartyId = counterPartyId;
    }

}
