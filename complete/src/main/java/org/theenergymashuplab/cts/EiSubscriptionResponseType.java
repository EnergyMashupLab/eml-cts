package org.theenergymashuplab.cts;


/**
 * @author crossover
 * @version 1.0
 * @created 28-Sep-2024 8:41:40 PM
 */
public abstract class EiSubscriptionResponseType {

	public String multicastListenReference;
	public EiResponseType response;
	public SubscriptionActionType subscriptionActionTaken;
	public RefIdType subscriptionRequestId;

	public EiSubscriptionResponseType(){

	}

	public EiSubscriptionResponseType(String multicastListenReference, SubscriptionActionType subscriptionActionTaken, EiResponseType response, RefIdType subscriptionRequestId) {
		this.multicastListenReference = multicastListenReference;
		this.subscriptionActionTaken = subscriptionActionTaken;
		this.response = response;
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

	@Override
	public String toString() {
		return "EiSubscriptionResponseType{" +
				"multicastListenReference='" + multicastListenReference + '\'' +
				", response=" + response +
				", subscriptionActionTaken=" + subscriptionActionTaken +
				", subscriptionRequestId=" + subscriptionRequestId +
				'}';
	}
}
