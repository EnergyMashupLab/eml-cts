package org.theenergymashuplab.cts.controller.payloads;

import org.theenergymashuplab.cts.EiResponseType;
import org.theenergymashuplab.cts.RefIdType;
import org.theenergymashuplab.cts.SubscriptionActionType;

public class ClientSubscriptionResponseType {
    public String multicastListenReference;
    public EiResponseType response;
    public SubscriptionActionType subscriptionActionTaken;
    public RefIdType subscriptionRequestId;

    public ClientSubscriptionResponseType() {
    }

    public ClientSubscriptionResponseType(String multicastListenReference, EiResponseType response, SubscriptionActionType subscriptionActionTaken, RefIdType subscriptionRequestId) {
        this.multicastListenReference = multicastListenReference;
        this.response = response;
        this.subscriptionActionTaken = subscriptionActionTaken;
        this.subscriptionRequestId = subscriptionRequestId;
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
}
