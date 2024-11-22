package org.theenergymashuplab.cts.controller.payloads;

import org.theenergymashuplab.cts.MarketIdType;
import org.theenergymashuplab.cts.RefIdType;
import org.theenergymashuplab.cts.SubscriptionActionType;

public class ClientSubscriptionRequestType {
    public String info = "ClientSubscriptionRequestType";
    public MarketIdType marketId;
    public int segmentId;
    public SubscriptionActionType subscriptionActionRequested;
    public RefIdType subscriptionRequestId;

    public ClientSubscriptionRequestType() {
    }

    public ClientSubscriptionRequestType(String info, MarketIdType marketId, int segmentId, SubscriptionActionType subscriptionActionRequested, RefIdType subscriptionRequestId) {
        this.info = info;
        this.marketId = marketId;
        this.segmentId = segmentId;
        this.subscriptionActionRequested = subscriptionActionRequested;
        this.subscriptionRequestId = subscriptionRequestId;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
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
}
