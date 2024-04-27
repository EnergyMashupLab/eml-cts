package org.theenergymashuplab.cts;

import java.time.Instant;
import java.util.List;

public abstract class TenderBase {
	private boolean allOrNone = false;  // TODO Added due to being a part of the March 2024 spec. All or none behavior has not be implemented yet
	private String executionInstructions = "";  // TODO Added due to being a part of the March 2024 spec. execution instruction behavior has not be implemented yet
	private Instant expirationTime = null;
	private MarketIdType marketId = new MarketIdType();  // TODO Added due to being a part of the March 2024 spec. eml-cts can only handle one market at the moment
	private int priceScale = 1;  // TODO Added due to being a part of the March 2024 spec. Variable price scales have not been implemented, so its always assumed to be 1 at the moment
	private int quantityScale = 1;  // TODO Added due to being a part of the March 2024 spec. Variable quantity scales have not been implemented, so its always assumed to be 1 at the moment
	private ResourceDesignatorType resourceDesignator = ResourceDesignatorType.ENERGY;
	private SideType side;
	private TenderDetail tenderDetail;
	private List<Integer> warrants = null;  // TODO Added due to being a part of the March 2024 spec. warrant behavior has not be implemented yet
	
	public TenderBase(Instant expirationTime, SideType side, TenderDetail tenderDetail) {
		this.expirationTime = expirationTime;
		this.side = side;
		this.tenderDetail = tenderDetail;
	}

	public TenderBase(boolean allOrNone, String executionInstructions, Instant expirationTime, MarketIdType marketId,
			int priceScale, int quantityScale, ResourceDesignatorType resourceDesignator, SideType side,
			TenderDetail tenderDetail, List<Integer> warrants) {
		this.allOrNone = allOrNone;
		this.executionInstructions = executionInstructions;
		this.expirationTime = expirationTime;
		this.marketId = marketId;
		this.priceScale = priceScale;
		this.quantityScale = quantityScale;
		this.resourceDesignator = resourceDesignator;
		this.side = side;
		this.tenderDetail = tenderDetail;
		this.warrants = warrants;
	}

	public boolean isAllOrNone() {
		return allOrNone;
	}

	public void setAllOrNone(boolean allOrNone) {
		this.allOrNone = allOrNone;
	}

	public String getExecutionInstructions() {
		return executionInstructions;
	}

	public void setExecutionInstructions(String executionInstructions) {
		this.executionInstructions = executionInstructions;
	}

	public Instant getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(Instant expirationTime) {
		this.expirationTime = expirationTime;
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

	public SideType getSide() {
		return side;
	}

	public void setSide(SideType side) {
		this.side = side;
	}

	public TenderDetail getTenderDetail() {
		return tenderDetail;
	}

	public void setTenderDetail(TenderDetail tenderDetail) {
		this.tenderDetail = tenderDetail;
	}

	public List<Integer> getWarrants() {
		return warrants;
	}

	public void setWarrants(List<Integer> warrants) {
		this.warrants = warrants;
	}
}
