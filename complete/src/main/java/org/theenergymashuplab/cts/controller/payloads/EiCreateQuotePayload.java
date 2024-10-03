package org.theenergymashuplab.cts.controller.payloads;

import org.theenergymashuplab.cts.ActorIdType;
import org.theenergymashuplab.cts.EiQuoteType;
import org.theenergymashuplab.cts.MarketIdType;
import org.theenergymashuplab.cts.RefIdType;
import org.theenergymashuplab.cts.ResourceDesignatorType;

/**
 * @author crossover
 * @version 1.0
 * @created 28-Sep-2024 8:41:39 PM
 */
public class EiCreateQuotePayload {

	public boolean atMostOne;
	public ActorIdType counterPartyId;
	public String executionInstructions;
	public MarketIdType marketId;
	public ActorIdType partyId;
	public EiQuoteType quote;
	public RefIdType requestId;
	public boolean requestPrivate;
	public boolean requestPublication;
	public ResourceDesignatorType resourceDesignator;
	public int segmentId;

	public EiCreateQuotePayload(){

	}
}
