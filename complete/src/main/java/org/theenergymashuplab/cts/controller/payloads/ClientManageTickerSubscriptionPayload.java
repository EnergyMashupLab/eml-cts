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

package org.theenergymashuplab.cts.controller.payloads;

import org.theenergymashuplab.cts.*;

public class ClientManageTickerSubscriptionPayload {
    String info = "ClientManageTickerSubscriptionPayload";
    public TickerType tickerType;
    public MarketIdType marketId;
    public int segmentId;
    public SubscriptionActionType subscriptionActionType;
    public long subscriptionId;

    public ClientManageTickerSubscriptionPayload() {}

    public ClientManageTickerSubscriptionPayload(String info, TickerType tickerType, MarketIdType marketId,
            int segmentId, SubscriptionActionType subscriptionActionType, long subscriptionId) {
        this.info = info;
        this.tickerType = tickerType;
        this.marketId = marketId;
        this.segmentId = segmentId;
        this.subscriptionActionType = subscriptionActionType;
        this.subscriptionId = subscriptionId;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public long getSubscriptionId() {
        return this.subscriptionId;
    }

    public void setSubscriptionId(long subscriptionId) {
        this.subscriptionId = subscriptionId;
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

}
