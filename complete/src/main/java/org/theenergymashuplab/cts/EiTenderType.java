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

public class EiTenderType extends TenderBase {
	private final TenderIdType tenderId = new TenderIdType();	// new tender id on construction
	private MarketOrderIdType referencedQuoteId = null;  // Will be unused until development of Negotiation Facet
	/* TODO Not up to March 2024 spec. This ought to be provided by the market (parity)
	but parity does not provide its parity order id at this moment. Thus a new cts-generated id is used
	in the time being */ 
	private MarketOrderIdType marketOrderId = new MarketOrderIdType();
	public static final long EMPTY_MARKET_ORDER_ID = -1;  // Market orders ids need to be filled in by parity, so this will be the default value until it's filled by parity
	
	// Needed for JSON deserialization by Jackson
	public EiTenderType() {
		super(null, null, null);
		marketOrderId.setMyUidId(EMPTY_MARKET_ORDER_ID);
	}
	
	//Already have constructor that could be used with streams
	public EiTenderType(Instant expirationTime, SideType side, TenderDetail tenderDetail) {
		super(expirationTime, side, tenderDetail);
		marketOrderId.setMyUidId(EMPTY_MARKET_ORDER_ID);
	}

	public EiTenderType(Instant expirationTime, SideType side, TenderDetail tenderDetail,
			MarketOrderIdType marketOrderId) {
		super(expirationTime, side, tenderDetail);
		this.marketOrderId = marketOrderId;
	}
	
	public EiTenderType(Instant expirationTime, SideType side, TenderDetail tenderDetail,
			MarketOrderIdType referencedQuoteId, MarketOrderIdType marketOrderId) {
		super(expirationTime, side, tenderDetail);
		this.referencedQuoteId = referencedQuoteId;
		this.marketOrderId = marketOrderId;
	}

	@Override
	public String toString() {
		return "EiTenderType [tenderId=" + tenderId + ", referencedQuoteId=" + referencedQuoteId + ", marketOrderId="
				+ marketOrderId + ", allOrNone=" + isAllOrNone() + ", executionInstructions="
				+ getExecutionInstructions() + ", marketId=" + getMarketId() + ", priceScale="
				+ getPriceScale() + ", quantityScale=" + getQuantityScale() + ", resourceDesignator="
				+ getResourceDesignator() + ", tnderDetail=" + getTenderDetail() + ", warrants="
				+ getWarrants() + "]";
	}

	public TenderIdType getTenderId() {
		return tenderId;
	}

	public MarketOrderIdType getReferencedQuoteId() {
		return referencedQuoteId;
	}

	public void setReferencedQuoteId(MarketOrderIdType referencedQuoteId) {
		this.referencedQuoteId = referencedQuoteId;
	}

	public MarketOrderIdType getMarketOrderId() {
		return marketOrderId;
	}

	public void setMarketOrderId(MarketOrderIdType marketOrderId) {
		this.marketOrderId = marketOrderId;
	}
	
}
