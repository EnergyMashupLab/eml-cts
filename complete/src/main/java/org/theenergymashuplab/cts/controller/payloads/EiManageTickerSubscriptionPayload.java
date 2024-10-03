package org.theenergymashuplab.cts.controller.payloads;

import org.theenergymashuplab.cts.EiSubscriptionRequestType;
import org.theenergymashuplab.cts.TickerType;

/**
 * @author crossover
 * @version 1.0
 * @created 28-Sep-2024 8:41:40 PM
 */
public class EiManageTickerSubscriptionPayload extends EiSubscriptionRequestType {

	public TickerType tickerType;

	public EiManageTickerSubscriptionPayload(){

	}
}
