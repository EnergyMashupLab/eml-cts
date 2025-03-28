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
 * 
 * @author crossover
 * @version 1.0
 * @created 18-Feb-2025 11:30:37 AM
 */

public class InstrumentSessionReportType {

	public InstrumentSummaryType instrumentSummary;
	public MarketIdType marketId;
	public int priceScale;
	public int quantityScale;
	public ResourceDesignatorType resourceDesignator;
	public int segmentId;
	public SegmentStatusType segmentStatus;

	public InstrumentSessionReportType() {

	}

	public InstrumentSessionReportType(InstrumentSummaryType instrumentSummary, MarketIdType marketId, int priceScale,
			int quantityScale, ResourceDesignatorType resourceDesignator, int segmentId,
			SegmentStatusType segmentStatus) {
		this.instrumentSummary = instrumentSummary;
		this.marketId = marketId;
		this.priceScale = priceScale;
		this.quantityScale = quantityScale;
		this.resourceDesignator = resourceDesignator;
		this.segmentId = segmentId;
		this.segmentStatus = segmentStatus;
	}

	public InstrumentSummaryType getInstrumentSummary() {
		return instrumentSummary;
	}

	public void setInstrumentSummary(InstrumentSummaryType instrumentSummary) {
		this.instrumentSummary = instrumentSummary;
	}

	public MarketIdType getMarketId() {
		return marketId;
	}

	public void setMarketId(MarketIdType marketId) {
		this.marketId = marketId;
	}

	public int getPriceScale() {
		return priceScale;
	}

	public void setPriceScale(int priceScale) {
		this.priceScale = priceScale;
	}

	public int getQuantityScale() {
		return quantityScale;
	}

	public void setQuantityScale(int quantityScale) {
		this.quantityScale = quantityScale;
	}

	public ResourceDesignatorType getResourceDesignator() {
		return resourceDesignator;
	}

	public void setResourceDesignator(ResourceDesignatorType resourceDesignator) {
		this.resourceDesignator = resourceDesignator;
	}

	public int getSegmentId() {
		return segmentId;
	}

	public void setSegmentId(int segmentId) {
		this.segmentId = segmentId;
	}

	public SegmentStatusType getSegmentStatus() {
		return segmentStatus;
	}

	public void setSegmentStatus(SegmentStatusType segmentStatus) {
		this.segmentStatus = segmentStatus;
	}

	@Override
	public String toString() {
		return "InstrumentSessionReportType {" + "instrumentSummary=" + instrumentSummary + ", marketId=" + marketId
				+ ", priceScale=" + priceScale + ", quantityScale=" + quantityScale + ", resourceDesignator="
				+ resourceDesignator + ", segmentId=" + segmentId + ", segmentStatus=" + segmentStatus + '}';
	}
}
