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

import org.theenergymashuplab.cts.EiResponseType;
import org.theenergymashuplab.cts.RefIdType;
import org.theenergymashuplab.cts.SubscriptionActionType;
import org.theenergymashuplab.cts.SubscriptionIdType;
import org.theenergymashuplab.cts.TickerType;

public class ClientManagedTickerSubscriptionPayload {
    public String info = "ClientManagedTickerSubscriptionPayload";
    public TickerType tickerType;
    public String multicastListenReference;
    public EiResponseType response;
    public SubscriptionIdType subscriptionId;
    public SubscriptionActionType subscriptionActionTaken;
    public RefIdType subscriptionRequestId;

    public ClientManagedTickerSubscriptionPayload() {}

    public ClientManagedTickerSubscriptionPayload(String info, TickerType tickerType, String multicastListenReference,
            EiResponseType response, SubscriptionActionType subscriptionActionTaken, RefIdType subscriptionRequestId,
            SubscriptionIdType subscriptionId) {
        this.info = info;
        this.tickerType = tickerType;
        this.multicastListenReference = multicastListenReference;
        this.response = response;
        this.subscriptionActionTaken = subscriptionActionTaken;
        this.subscriptionId = subscriptionId;
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

    public String getMulticastListenReference() {
        return multicastListenReference;
    }

    public void setMulticastListenReference(String multicastListenReference) {
        this.multicastListenReference = multicastListenReference;
    }

    public EiResponseType getResponse() {
        return response;
    }

    public void setResponse(EiResponseType response) {
        this.response = response;
    }

    public SubscriptionActionType getSubscriptionActionTaken() {
        return subscriptionActionTaken;
    }

    public void setSubscriptionActionTaken(SubscriptionActionType subscriptionActionTaken) {
        this.subscriptionActionTaken = subscriptionActionTaken;
    }

    public RefIdType getSubscriptionRequestId() {
        return subscriptionRequestId;
    }

    public void setSubscriptionRequestId(RefIdType subscriptionRequestId) {
        this.subscriptionRequestId = subscriptionRequestId;
    }

    public SubscriptionIdType getSubscriptionId() {
        return this.subscriptionId;
    }

    public void setSubscriptionId(SubscriptionIdType subscriptionId) {
        this.subscriptionId = subscriptionId;
    }
}
