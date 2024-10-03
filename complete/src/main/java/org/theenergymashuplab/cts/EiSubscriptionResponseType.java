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

	public void finalize() throws Throwable {

	}

}