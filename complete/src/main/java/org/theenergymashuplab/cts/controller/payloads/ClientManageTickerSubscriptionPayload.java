package org.theenergymashuplab.cts.controller.payloads;

import org.theenergymashuplab.cts.*;

public class ClientManageTickerSubscriptionPayload {
    String info = "ClientManageTickerSubscriptionPayload";
    public TickerType tickerType;
    public MarketIdType marketId;
    public int segmentId;
    public SubscriptionActionType subscriptionActionType;
//    public SubscriptionIdType subscriptionId;
    public RefIdType subscriptionRequestId;

    public ClientManageTickerSubscriptionPayload() {
    }

    public ClientManageTickerSubscriptionPayload(String info, TickerType tickerType, MarketIdType marketId, int segmentId, SubscriptionActionType subscriptionActionType, RefIdType subscriptionRequestId) {
        this.info = info;
        this.tickerType = tickerType;
        this.marketId = marketId;
        this.segmentId = segmentId;
        this.subscriptionActionType = subscriptionActionType;
        this.subscriptionRequestId = subscriptionRequestId;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public TickerType getTickerType() {
        return tickerType;
    }

    public void setTickerType(TickerType tickerType) {
        this.tickerType = tickerType;
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

    public SubscriptionActionType getSubscriptionActionType() {
        return subscriptionActionType;
    }

    public void setSubscriptionActionType(SubscriptionActionType subscriptionActionType) {
        this.subscriptionActionType = subscriptionActionType;
    }

    public RefIdType getSubscriptionRequestId() {
        return subscriptionRequestId;
    }

    public void setSubscriptionRequestId(RefIdType subscriptionRequestId) {
        this.subscriptionRequestId = subscriptionRequestId;
    }


}
