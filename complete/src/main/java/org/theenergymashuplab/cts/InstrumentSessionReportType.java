package org.theenergymashuplab.cts;

/**
 * The common parts of EiTenderType and EiQuoteType
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
