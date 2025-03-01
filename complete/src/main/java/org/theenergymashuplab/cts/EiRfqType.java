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
public class EiRfqType extends TenderBase {

	public IntervalType boundingInterval;
	public DurationType duration;
	public MarketOrderIdType marketRfqId;
	public boolean privateRfq;
	public RfqIdType rfqId;
	public boolean tradeableOnlyResponse;

	public EiRfqType() {

	}

	public EiRfqType (IntervalType boundingInterval, DurationType duration, MarketOrderIdType marketRfqId,
					  boolean privateRfq, RfqIdType rfqId, boolean tradeableOnlyResponse)
	{
		this.boundingInterval = boundingInterval;
		this.duration = duration;
		this.marketRfqId = marketRfqId;
		this.privateRfq = privateRfq;
		this.rfqId = rfqId;
		this.tradeableOnlyResponse = tradeableOnlyResponse;
	}

	public void setTradeableOnlyResponse(boolean tradeableOnlyResponse) {
		this.tradeableOnlyResponse = tradeableOnlyResponse;
	}

	public void setRfqId(RfqIdType rfqId) {
		this.rfqId = rfqId;
	}

	public void setPrivateRfq(boolean privateRfq) {
		this.privateRfq = privateRfq;
	}

	public void setMarketRfqId(MarketOrderIdType marketRfqId) {
		this.marketRfqId = marketRfqId;
	}

	public void setDuration(DurationType duration) {
		this.duration = duration;
	}

	public void setBoundingInterval(IntervalType boundingInterval) {
		this.boundingInterval = boundingInterval;
	}

	public IntervalType getBoundingInterval() {
		return boundingInterval;
	}

	public DurationType getDuration() {
		return duration;
	}

	public MarketOrderIdType getMarketRfqId() {
		return marketRfqId;
	}

	public boolean isPrivateRfq() {
		return privateRfq;
	}

	public RfqIdType getRfqId() {
		return rfqId;
	}

	public boolean isTradeableOnlyResponse() {
		return tradeableOnlyResponse;
	}

	@Override
	public String toString() {
		return "EiRfqType{" +
				"boundingInterval=" + boundingInterval +
				", duration=" + duration +
				", marketRfqId=" + marketRfqId +
				", privateRfq=" + privateRfq +
				", rfqId=" + rfqId +
				", tradeableOnlyResponse=" + tradeableOnlyResponse +
				'}';
	}

	//	public void finalize() throws Throwable {
//		super.finalize();
//	}
}