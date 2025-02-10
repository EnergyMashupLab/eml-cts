package org.theenergymashuplab.cts.controller.payloads;

import org.theenergymashuplab.cts.MarketOrderIdType;
import org.theenergymashuplab.cts.RefIdType;

/**
 * @author crossover
 * @version 1.0
 * @created 28-Sep-2024 8:41:40 PM
 */
public class EIRejectQuotePayload {

	public MarketOrderIdType referencedQuoteId;
	public RefIdType requestId;

	public EIRejectQuotePayload(){

	}

	public EIRejectQuotePayload(MarketOrderIdType referencedQuoteId, RefIdType requestId) {
		this.referencedQuoteId = referencedQuoteId;
		this.requestId = requestId;
	}

	public MarketOrderIdType getReferencedQuoteId() {
		return referencedQuoteId;
	}

	public void setReferencedQuoteId(MarketOrderIdType referencedQuoteId) {
		this.referencedQuoteId = referencedQuoteId;
	}

	public RefIdType getRequestId() {
		return requestId;
	}

	public void setRequestId(RefIdType requestId) {
		this.requestId = requestId;
	}

	@Override
	public String toString() {
		return "EIRejectQuotePayload{" +
				"referencedQuoteId=" + referencedQuoteId +
				", requestId=" + requestId +
				'}';
	}
}
