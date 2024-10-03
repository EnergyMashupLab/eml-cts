package org.theenergymashuplab.cts.controller.payloads;

import org.theenergymashuplab.cts.ActorIdType;
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
	public MarketOrderIdType marketQuoteId;
	public ActorIdType partyId;
	public TenderIdType quoteId;
	public EiResponseType response;

	public EiCreatedQuotePayload(){

	}
}
