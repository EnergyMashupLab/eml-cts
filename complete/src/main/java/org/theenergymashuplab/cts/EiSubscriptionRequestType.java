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
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.theenergymashuplab.cts;

/**
 * @author crossover
 * @version 1.0
 * @created 18-Feb-2025 11:30:37 AM
 */
public abstract class EiSubscriptionRequestType {

	public MarketIdType marketId;
	public int segmentId;
	public SubscriptionActionType subscriptionActionRequested;
	public RefIdType subscriptionRequestId;

	public EiSubscriptionRequestType(){

	}

	public EiSubscriptionRequestType(MarketIdType marketId, int segmentId, SubscriptionActionType subscriptionActionRequested,
									 RefIdType subscriptionRequestId) {
		this.marketId = marketId;
		this.segmentId = segmentId;
		this.subscriptionActionRequested = subscriptionActionRequested;
		this.subscriptionRequestId = subscriptionRequestId;
	}

	public void setSubscriptionActionRequested(SubscriptionActionType subscriptionActionRequested) {
		this.subscriptionActionRequested = subscriptionActionRequested;
	}

	public void setSubscriptionRequestId(RefIdType subscriptionRequestId) {
		this.subscriptionRequestId = subscriptionRequestId;
	}

	public void setSegmentId(int segmentId) {
		this.segmentId = segmentId;
	}

	public void setMarketId(MarketIdType marketId) {
		this.marketId = marketId;
	}

	public RefIdType getSubscriptionRequestId() {
		return subscriptionRequestId;
	}

	public SubscriptionActionType getSubscriptionActionRequested() {
		return subscriptionActionRequested;
	}

	public int getSegmentId() {
		return segmentId;
	}

	public MarketIdType getMarketId() {
		return marketId;
	}

	@Override
	public String toString() {
		return "EiSubscriptionRequestType{" +
				"marketId=" + marketId +
				", segmentId=" + segmentId +
				", subscriptionActionRequested=" + subscriptionActionRequested +
				", subscriptionRequestId=" + subscriptionRequestId +
				'}';
	}

	//	public void finalize() throws Throwable {
//
//	}

}