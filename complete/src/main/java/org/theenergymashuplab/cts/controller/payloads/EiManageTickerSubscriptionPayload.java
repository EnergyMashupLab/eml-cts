package org.theenergymashuplab.cts.controller.payloads;

import org.theenergymashuplab.cts.*;

/**
 * @author crossover
 * @version 1.0
 * @created 28-Sep-2024 8:41:40 PM
 */
public class EiManageTickerSubscriptionPayload extends EiSubscriptionRequestType {

	public TickerType tickerType;

	public EiManageTickerSubscriptionPayload(){

	}

	public EiManageTickerSubscriptionPayload(TickerType tickerType) {
		this.tickerType = tickerType;
	}

	public TickerType getTickerType() {
		return tickerType;
	}

	public void setTickerType(TickerType tickerType) {
		this.tickerType = tickerType;
	}

	@Override
	public String toString() {
		return "EiManageTickerSubscriptionPayload{" +
				"tickerType=" + tickerType +
				'}';
	}
}
