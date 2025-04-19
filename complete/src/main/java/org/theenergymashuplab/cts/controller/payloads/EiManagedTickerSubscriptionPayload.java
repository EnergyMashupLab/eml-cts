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

import org.theenergymashuplab.cts.EiResponseType;
import org.theenergymashuplab.cts.EiSubscriptionResponseType;
import org.theenergymashuplab.cts.RefIdType;
import org.theenergymashuplab.cts.SubscriptionActionType;
import org.theenergymashuplab.cts.SubscriptionIdType;
import org.theenergymashuplab.cts.TickerType;

public class EiManagedTickerSubscriptionPayload extends EiSubscriptionResponseType {

	public TickerType tickerType;
	public SubscriptionIdType subscriptionId;

	public EiManagedTickerSubscriptionPayload() {

	}

	public EiManagedTickerSubscriptionPayload(String multicastListenReference,
			SubscriptionActionType subscriptionActionTaken, EiResponseType response, RefIdType subscriptionRequestId,
			TickerType tickerType) {
		super(multicastListenReference, subscriptionActionTaken, response, subscriptionRequestId);
		this.tickerType = tickerType;
	}

	public EiManagedTickerSubscriptionPayload(TickerType tickerType) {
		this.tickerType = tickerType;
	}

	public TickerType getTickerType() {
		return this.tickerType;
	}

	public void setTickerType(TickerType tickerType) {
		this.tickerType = tickerType;
	}

	public SubscriptionIdType getSubscriptionId() {
		return this.subscriptionId;
	}

	public void setSubscriptionId(SubscriptionIdType subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	@Override
	public String toString() {
		return "EiManagedTickerSubscriptionPayload: [" + "tickerType=" + this.tickerType.toString() + "]";
	}

}
