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

	public MarketReferenceDataType(){

	}
}
