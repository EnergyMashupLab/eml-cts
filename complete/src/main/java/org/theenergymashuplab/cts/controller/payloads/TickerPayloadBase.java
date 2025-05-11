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

package org.theenergymashuplab.cts.controller.payloads;

import org.theenergymashuplab.cts.ActorIdType;
import org.theenergymashuplab.cts.RefIdType;
import org.theenergymashuplab.cts.SideType;
import org.theenergymashuplab.cts.SubscriptionIdType;
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
	public SubscriptionIdType subscriptionId;
	public TickerType tickerType;

	public TickerPayloadBase() {

	}

	public TickerPayloadBase(ActorIdType counterParty, ActorIdType party, SideType side,
			SubscriptionIdType subscriptionId, TickerType tickerType) {
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

	public SubscriptionIdType getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(SubscriptionIdType subscriptionId) {
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
		return "TickerPayloadBase{" + "counterParty=" + counterParty + ", party=" + party + ", side=" + side
				+ ", subscriptionId=" + subscriptionId + ", tickerType=" + tickerType + '}';
	}
}
