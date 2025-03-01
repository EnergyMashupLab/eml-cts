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
public class EiTransactionTickerType {

	public IntervalType interval;
	public String marketContext;
	/**
	 * A long to which the Market or MarketPlace scale factors are applied. High
	 * performance open source market implementations often use Long rather than Fixed
	 * Decimal or Float,
	 */
	public long price;
	public long quantity;
	public ResourceDesignatorType resourceDesignator;
	public InstantType saleTime;
	public SideType side;
	public RefIdType tickerId;

	public EiTransactionTickerType() {

	}

	public EiTransactionTickerType(IntervalType interval, String marketContext, long price, long quantity,
								   ResourceDesignatorType resourceDesignator, InstantType saleTime, SideType side, RefIdType tickerId) {
		this.interval = interval;
		this.marketContext = marketContext;
		this.price = price;
		this.quantity = quantity;
		this.resourceDesignator = resourceDesignator;
		this.saleTime = saleTime;
		this.side = side;
		this.tickerId = tickerId;
	}

	public void setSide(SideType side) {
		this.side = side;
	}

	public void setSaleTime(InstantType saleTime) {
		this.saleTime = saleTime;
	}

	public void setTickerId(RefIdType tickerId) {
		this.tickerId = tickerId;
	}

	public void setResourceDesignator(ResourceDesignatorType resourceDesignator) {
		this.resourceDesignator = resourceDesignator;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public void setMarketContext(String marketContext) {
		this.marketContext = marketContext;
	}

	public void setInterval(IntervalType interval) {
		this.interval = interval;
	}

	public String getMarketContext() {
		return marketContext;
	}

	public ResourceDesignatorType getResourceDesignator() {
		return resourceDesignator;
	}

	public SideType getSide() {
		return side;
	}

	public long getQuantity() {
		return quantity;
	}

	public long getPrice() {
		return price;
	}

	public IntervalType getInterval() {
		return interval;
	}

	public InstantType getSaleTime() {
		return saleTime;
	}

	public RefIdType getTickerId() {
		return tickerId;
	}

	@Override
	public String toString() {
		return "EiTransactionTickerType{" +
				"interval=" + interval +
				", marketContext='" + marketContext + '\'' +
				", price=" + price +
				", quantity=" + quantity +
				", resourceDesignator=" + resourceDesignator +
				", saleTime=" + saleTime +
				", side=" + side +
				", tickerId=" + tickerId +
				'}';
	}

	//	public void finalize() throws Throwable {
//
//	}

}