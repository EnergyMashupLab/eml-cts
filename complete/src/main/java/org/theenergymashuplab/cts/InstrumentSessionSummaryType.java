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

package org.theenergymashuplab.cts;

/**
 * @author crossover
 * @version 1.0
 * @created 28-Sep-2024 8:41:41 PM
 */
public class InstrumentSessionSummaryType {

	public long firstPrice;
	public long highPrice;
	public long lastPrice;
	public long lowPrice;
	public long volume;

	public InstrumentSessionSummaryType() {

	}

	public InstrumentSessionSummaryType(long firstPrice, long highPrice, long lastPrice, long lowPrice, long volume) {
		this.firstPrice = firstPrice;
		this.highPrice = highPrice;
		this.lastPrice = lastPrice;
		this.lowPrice = lowPrice;
		this.volume = volume;
	}

	public long getFirstPrice() {
		return firstPrice;
	}

	public void setFirstPrice(long firstPrice) {
		this.firstPrice = firstPrice;
	}

	public long getVolume() {
		return volume;
	}

	public void setVolume(long volume) {
		this.volume = volume;
	}

	public long getLowPrice() {
		return lowPrice;
	}

	public void setLowPrice(long lowPrice) {
		this.lowPrice = lowPrice;
	}

	public long getLastPrice() {
		return lastPrice;
	}

	public void setLastPrice(long lastPrice) {
		this.lastPrice = lastPrice;
	}

	public long getHighPrice() {
		return highPrice;
	}

	public void setHighPrice(long highPrice) {
		this.highPrice = highPrice;
	}

	@Override
	public String toString() {
		return "InstrumentSessionSummaryType{" + "firstPrice=" + firstPrice + ", highPrice=" + highPrice
				+ ", lastPrice=" + lastPrice + ", lowPrice=" + lowPrice + ", volume=" + volume + '}';
	}
}
