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

package org.theenergymashuplab.cts;

import java.time.Instant;

public class EiQuoteType extends TenderBase {

	public MarketOrderIdType marketQuoteId;
	public boolean privateQuote;
	public TenderIdType quoteId = new TenderIdType();
	public RfqIdType rfqId;
	public boolean tradeable;

	public EiQuoteType() {

	}

	public EiQuoteType(MarketOrderIdType marketQuoteId, boolean privateQuote, TenderIdType quoteId, RfqIdType rfqId,
			boolean tradeable) {
		this.marketQuoteId = marketQuoteId;
		this.privateQuote = privateQuote;
		this.quoteId = quoteId;
		this.rfqId = rfqId;
		this.tradeable = tradeable;
	}

	public EiQuoteType(Instant instant, SideType side, TenderDetail quoteDetail) {
		super(instant, side, quoteDetail);
	}

	public MarketOrderIdType getMarketQuoteId() {
		return marketQuoteId;
	}

	public void setMarketQuoteId(MarketOrderIdType marketQuoteId) {
		this.marketQuoteId = marketQuoteId;
	}

	public boolean getPrivateQuote() {
		return this.privateQuote;
	}

	public void setPrivateQuote(boolean privateQuote) {
		this.privateQuote = privateQuote;
	}

	public TenderIdType getQuoteId() {
		return this.quoteId;
	}

	public void setQuoteId(TenderIdType quoteId) {
		this.quoteId = quoteId;
	}

	public RfqIdType getRfqId() {
		return this.rfqId;
	}

	public void setRfqId(RfqIdType rfqId) {
		this.rfqId = rfqId;
	}

	public boolean getTradeable() {
		return this.tradeable;
	}

	public void setTradeable(boolean tradeable) {
		this.tradeable = tradeable;
	}

	@Override
	public String toString() {
		return "EiQuoteType{" +
				"marketQuoteId=" + marketQuoteId +
				", privateQuote=" + privateQuote +
				", quoteId=" + quoteId +
				", rfqId=" + rfqId +
				", tradeable=" + tradeable +
				", tenderDetail: " + this.getTenderDetail().toString() +
				'}';
	}

	/**
	 * We are guaranteed to have a unique market order ID, and the AcceptQuote will
	 * reference
	 * a quote via the marketQuoteId. As such, it makes sense to index by it in a
	 * hashset
	 */
	@Override
	public int hashCode() {
		return (int) this.marketQuoteId.getMyUidId();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		// Check class equality
		if (obj.getClass() != EiQuoteType.class) {
			return false;
		}

		// If their market order IDs equal they are the same for us
		return ((EiQuoteType) obj).getMarketQuoteId().equals(this.getMarketQuoteId());
	}
}
