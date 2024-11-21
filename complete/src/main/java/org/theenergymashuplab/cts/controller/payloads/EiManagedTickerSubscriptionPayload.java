package org.theenergymashuplab.cts.controller.payloads;

import org.theenergymashuplab.cts.*;

/**
 * @author crossover
 * @version 1.0
 * @created 28-Sep-2024 8:41:40 PM
 */
public class EiManagedTickerSubscriptionPayload extends EiSubscriptionResponseType {

	public TickerType tickerType;

	public EiManagedTickerSubscriptionPayload(){

	}

	public EiManagedTickerSubscriptionPayload(TickerType tickerType){
		this.tickerType = tickerType;
	}

	public EiManagedTickerSubscriptionPayload(String multicastListenReference, SubscriptionActionType subscriptionActionTaken, EiResponse response, RefIdType subscriptionRequestId, TickerType tickerType) {
		super(multicastListenReference, subscriptionActionTaken, response, subscriptionRequestId);
		this.tickerType = tickerType;
	}


	public TickerType getTickerType(){
		return this.tickerType;
	}

	public void setTickerType(TickerType tickerType){
		this.tickerType = tickerType;
	}

	@Override
	public String toString(){
		return "EiManagedTickerSubscriptionPayload: [" +
				"tickerType=" + this.tickerType.toString() + "]";
	}

}
