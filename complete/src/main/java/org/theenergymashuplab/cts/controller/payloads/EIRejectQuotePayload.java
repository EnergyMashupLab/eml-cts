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

	public void finalize() throws Throwable {

	}

}
