package org.theenergymashuplab.cts.controller.payloads;

import org.theenergymashuplab.cts.EiResponseType;
import org.theenergymashuplab.cts.EiSubscriptionResponseType;
import org.theenergymashuplab.cts.RefIdType;
import org.theenergymashuplab.cts.SubscriptionActionType;
import org.theenergymashuplab.cts.SubscriptionIdType;
import org.theenergymashuplab.cts.TickerType;

/**
 * @author crossover
 * @version 1.0
 * @created 28-Sep-2024 8:41:40 PM
 */
public class EiManagedTickerSubscriptionPayload extends EiSubscriptionResponseType {

	public TickerType tickerType;
	public SubscriptionIdType subscriptionId;

	public EiManagedTickerSubscriptionPayload(){

	}

	public EiManagedTickerSubscriptionPayload(String multicastListenReference, SubscriptionActionType subscriptionActionTaken, EiResponseType response, RefIdType subscriptionRequestId, TickerType tickerType) {
		super(multicastListenReference, subscriptionActionTaken, response, subscriptionRequestId);
		this.tickerType = tickerType;
	}

	public EiManagedTickerSubscriptionPayload(TickerType tickerType){
		this.tickerType = tickerType;
	}

	public TickerType getTickerType(){
		return this.tickerType;
	}

	public void setTickerType(TickerType tickerType){
		this.tickerType = tickerType;
	}

	public SubscriptionIdType getSubscriptionId(){
		return this.subscriptionId;
	}

	public void setSubscriptionId(SubscriptionIdType subscriptionId){
		this.subscriptionId = subscriptionId;
	}

	@Override
	public String toString(){
		return "EiManagedTickerSubscriptionPayload: [" +
				"tickerType=" + this.tickerType.toString() + "]";
	}

}
