package org.theenergymashuplab.cts.controller.payloads;

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
	public MarketOrderIdType marketQuoteIds;
	public ActorIdType partyId;
	private TenderIdType quoteIds;
	public RefIdType requestId;

	public EiCancelQuotePayload(){

	}
}
