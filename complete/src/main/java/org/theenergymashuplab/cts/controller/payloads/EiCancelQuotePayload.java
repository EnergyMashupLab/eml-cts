package org.theenergymashuplab.cts;


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
	public RefId requestId;

	public EiCancelQuotePayload(){

	}

	public void finalize() throws Throwable {

	}

}