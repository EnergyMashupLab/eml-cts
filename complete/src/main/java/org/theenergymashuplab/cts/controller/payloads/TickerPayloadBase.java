package org.theenergymashuplab.cts.controller.payloads;

import org.theenergymashuplab.cts.ActorIdType;
import org.theenergymashuplab.cts.RefIdType;
import org.theenergymashuplab.cts.SideType;
import org.theenergymashuplab.cts.TickerType;

/**
 * @author crossover
 * @version 1.0
 * @created 28-Sep-2024 8:41:42 PM
 */
public abstract class TickerPayloadBase {

	public ActorIdType counterParty;
	public ActorIdType party;
	public SideType side;
	public RefIdType subscriptionId;
	public TickerType tickerType;

	public TickerPayloadBase(){

	}

	public TickerPayloadBase(ActorIdType counterParty, ActorIdType party, SideType side, RefIdType subscriptionId, TickerType tickerType) {
		this.counterParty = counterParty;
		this.party = party;
		this.side = side;
		this.subscriptionId = subscriptionId;
		this.tickerType = tickerType;
	}

	public ActorIdType getCounterParty() {
		return counterParty;
	}

	public void setCounterParty(ActorIdType counterParty) {
		this.counterParty = counterParty;
	}

	public ActorIdType getParty() {
		return party;
	}

	public void setParty(ActorIdType party) {
		this.party = party;
	}

	public SideType getSide() {
		return side;
	}

	public void setSide(SideType side) {
		this.side = side;
	}

	public RefIdType getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(RefIdType subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public TickerType getTickerType() {
		return tickerType;
	}

	public void setTickerType(TickerType tickerType) {
		this.tickerType = tickerType;
	}

	@Override
	public String toString() {
		return "TickerPayloadBase{" +
				"counterParty=" + counterParty +
				", party=" + party +
				", side=" + side +
				", subscriptionId=" + subscriptionId +
				", tickerType=" + tickerType +
				'}';
	}
}
