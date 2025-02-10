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
	public SubscriptionIdType subscriptionId;
	public ActorIdType partyId;
	public RefIdType subscriptionRequestId;

	public EiSubscriptionRequestType(){

	}

	public EiSubscriptionRequestType(MarketIdType marketId, int segmentId, SubscriptionActionType subscriptionActionRequested, RefIdType subscriptionRequestId, ActorIdType partyId) {
		this.marketId = marketId;
		this.segmentId = segmentId;
		this.subscriptionActionRequested = subscriptionActionRequested;
		this.partyId = partyId;
		this.subscriptionRequestId = subscriptionRequestId;
	}

	public MarketIdType getMarketId() {
		return marketId;
	}

	public void setMarketId(MarketIdType marketId) {
		this.marketId = marketId;
	}

	public ActorIdType getPartyId(){
		return this.partyId;
	}

	public void setPartyId(ActorIdType partyId){
		this.partyId = partyId;
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

	public void setSubscriptionId(SubscriptionIdType subscriptionId){
		this.subscriptionId = subscriptionId;
	}

	public SubscriptionIdType getSubscriptionId(){
		return this.subscriptionId;
	}

	@Override
	public int hashCode(){
		return this.getSubscriptionId().hashCode();
	}

	@Override
	public boolean equals(Object other){
		if(other == null){
			return false;
		}

		if(other.getClass() != EiSubscriptionRequestType.class){
			return false;
		}

		//Compare their IDs
		return ((EiSubscriptionRequestType)other).hashCode() == this.hashCode();
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
