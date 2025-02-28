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

import org.theenergymashuplab.cts.*;

public class EiManageTickerSubscriptionPayload extends EiSubscriptionRequestType {

	public TickerType tickerType;

	public EiManageTickerSubscriptionPayload() {

	}

	public EiManageTickerSubscriptionPayload(TickerType tickerType) {
		this.tickerType = tickerType;
	}

	public EiManageTickerSubscriptionPayload(MarketIdType marketId, int segmentId,
			SubscriptionActionType subscriptionActionRequested, RefIdType subscriptionRequestId, TickerType tickerType,
			ActorIdType partyId) {
		super(marketId, segmentId, subscriptionActionRequested, subscriptionRequestId, partyId);
		this.tickerType = tickerType;
	}

	public TickerType getTickerType() {
		return tickerType;
	}

	public void setTickerType(TickerType tickerType) {
		this.tickerType = tickerType;
	}

	@Override
	public String toString() {
		return "EiManageTickerSubscriptionPayload{" +
				"tickerType=" + tickerType +
				'}';
	}
}
