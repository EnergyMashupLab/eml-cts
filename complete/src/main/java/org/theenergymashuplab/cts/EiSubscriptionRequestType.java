package org.theenergymashuplab.cts;


/**
 * @author crossover
 * @version 1.0
 * @created 28-Sep-2024 8:41:40 PM
 */
public abstract class EiSubscriptionRequestType {

	public MarketIdType marketId;
	public int segmentId;
	public SubscriptionActionType subscriptionActionRequested;
	public RefIdType subscriptionRequestId;

	public EiSubscriptionRequestType(){

	}

	public EiSubscriptionRequestType(MarketIdType marketId, int segmentId, SubscriptionActionType subscriptionActionRequested, RefIdType subscriptionRequestId) {
		this.marketId = marketId;
		this.segmentId = segmentId;
		this.subscriptionActionRequested = subscriptionActionRequested;
		this.subscriptionRequestId = subscriptionRequestId;
	}

	public MarketIdType getMarketId() {
		return marketId;
	}

	public void setMarketId(MarketIdType marketId) {
		this.marketId = marketId;
	}

	public int getSegmentId() {
		return segmentId;
	}

	public void setSegmentId(int segmentId) {
		this.segmentId = segmentId;
	}

	public SubscriptionActionType getSubscriptionActionRequested() {
		return subscriptionActionRequested;
	}

	public void setSubscriptionActionRequested(SubscriptionActionType subscriptionActionRequested) {
		this.subscriptionActionRequested = subscriptionActionRequested;
	}

	public RefIdType getSubscriptionRequestId() {
		return subscriptionRequestId;
	}

	public void setSubscriptionRequestId(RefIdType subscriptionRequestId) {
		this.subscriptionRequestId = subscriptionRequestId;
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
}
