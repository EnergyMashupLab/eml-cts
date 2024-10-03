package org.theenergymashuplab.cts;


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

	public void finalize() throws Throwable {

	}

}