package org.theenergymashuplab.cts.controller.payloads;

import org.theenergymashuplab.cts.ActorIdType;
import org.theenergymashuplab.cts.RefIdType;
import org.theenergymashuplab.cts.TickerType;

/**
 * @author crossover
 * @version 1.0
 * @created 28-Sep-2024 8:41:42 PM
 */
public abstract class TickerPayloadBase {

	public ActorIdType counterParty;
	public ActorIdType party;
	public RefIdType subscriptionId;
	public TickerType tickerType;

	public TickerPayloadBase(){

	}
}
