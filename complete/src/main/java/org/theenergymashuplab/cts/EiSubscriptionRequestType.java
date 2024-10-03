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

	public void finalize() throws Throwable {

	}

}