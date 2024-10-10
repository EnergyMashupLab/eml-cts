package org.theenergymashuplab.cts;


/**
 * @author crossover
 * @version 1.0
 * @created 28-Sep-2024 8:41:41 PM
 */
public class MarketReferenceDataType {

	public String currency;
	public String currencyCodeSource;
	public MarketIdType marketId;
	private int marketMechanismLevelTwo;
	public String marketName;
	public ActorIdType marketPartyId;
	public Integer maximumResourceAmount;
	public Integer maximumResourceDuration;
	public Integer maximumResourceUnit;
	public Integer maximumTrade;
	public Integer priceScale;
	public ResourceDesignatorType resourceDesignator;
	public SegmentReferenceDataType segments;
	public Long tickSize;
	public DurationType timeOffset;

	public MarketReferenceDataType(String currency, String currencyCodeSource, MarketIdType marketId, Integer marketMechanismLevelTwo,
								   String marketName, ActorIdType marketPartyI, Integer maximumResourceAmount, Integer maximumResourceDuration,
								   Integer maximumResourceUnit, Integer maximumTrade, Integer priceScale, ResourceDesignatorType resourceDesignator,
								   SegmentReferenceDataType segments, Long tickSize, DurationType timeOffset) {
		this.currency = currency;
		this.currencyCodeSource = currencyCodeSource;
		this.marketId = marketId;
		this.marketMechanismLevelTwo = marketMechanismLevelTwo;
		this.marketName = marketName;
		this.marketPartyId = marketPartyI;
		this.maximumResourceAmount = maximumResourceAmount;
		this.maximumResourceDuration = maximumResourceDuration;
		this.maximumResourceUnit = maximumResourceUnit;
		this.maximumTrade = maximumTrade;
		this.priceScale = priceScale;
		this.resourceDesignator = resourceDesignator;
		this.segments = segments;
		this.tickSize = tickSize;
		this.timeOffset = timeOffset;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getCurrencyCodeSource() {
		return currencyCodeSource;
	}

	public void setCurrencyCodeSource(String currencyCodeSource) {
		this.currencyCodeSource = currencyCodeSource;
	}

	public MarketIdType getMarketId() {
		return marketId;
	}

	public void setMarketId(MarketIdType marketId) {
		this.marketId = marketId;
	}

	public int getMarketMechanismLevelTwo() {
		return marketMechanismLevelTwo;
	}

	public void setMarketMechanismLevelTwo(int marketMechanismLevelTwo) {
		this.marketMechanismLevelTwo = marketMechanismLevelTwo;
	}

	public String getMarketName() {
		return marketName;
	}

	public void setMarketName(String marketName) {
		this.marketName = marketName;
	}

	public ActorIdType getMarketPartyId() {
		return marketPartyId;
	}

	public void setMarketPartyId(ActorIdType marketPartyId) {
		this.marketPartyId = marketPartyId;
	}

	public Integer getMaximumResourceAmount() {
		return maximumResourceAmount;
	}

	public void setMaximumResourceAmount(Integer maximumResourceAmount) {
		this.maximumResourceAmount = maximumResourceAmount;
	}

	public Integer getMaximumResourceDuration() {
		return maximumResourceDuration;
	}

	public void setMaximumResourceDuration(Integer maximumResourceDuration) {
		this.maximumResourceDuration = maximumResourceDuration;
	}

	public Integer getMaximumResourceUnit() {
		return maximumResourceUnit;
	}

	public void setMaximumResourceUnit(Integer maximumResourceUnit) {
		this.maximumResourceUnit = maximumResourceUnit;
	}

	public Integer getMaximumTrade() {
		return maximumTrade;
	}

	public void setMaximumTrade(Integer maximumTrade) {
		this.maximumTrade = maximumTrade;
	}

	public Integer getPriceScale() {
		return priceScale;
	}

	public void setPriceScale(Integer priceScale) {
		this.priceScale = priceScale;
	}

	public ResourceDesignatorType getResourceDesignator() {
		return resourceDesignator;
	}

	public void setResourceDesignator(ResourceDesignatorType resourceDesignator) {
		this.resourceDesignator = resourceDesignator;
	}

	public SegmentReferenceDataType getSegments() {
		return segments;
	}

	public void setSegments(SegmentReferenceDataType segments) {
		this.segments = segments;
	}

	public Long getTickSize() {
		return tickSize;
	}

	public void setTickSize(Long tickSize) {
		this.tickSize = tickSize;
	}

	public DurationType getTimeOffset() {
		return timeOffset;
	}

	public void setTimeOffset(DurationType timeOffset) {
		this.timeOffset = timeOffset;
	}

	@Override
	public String toString() {
		return "MarketReferenceDataType [currency=" + currency + ", currencyCodeSource=" + currencyCodeSource +
				", marketId=" + marketId + ", marketMechanismLevelTwo=" + marketMechanismLevelTwo +
				", marketName=" + marketName + ", marketPartyId=" + marketPartyId + ", maximumResourceAmount="
				+ maximumResourceAmount + ", maximumResourceDuration=" + maximumResourceDuration +
				", maximumResourceUnit=" + maximumResourceUnit + ", maximumTrade=" + maximumTrade +
				", priceScale=" + priceScale + ", resourceDesignator=" + resourceDesignator +
				", segments=" + segments + ", timeOffset=" + timeOffset + "]";
	}
}
