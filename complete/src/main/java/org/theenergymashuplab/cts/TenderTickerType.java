package org.theenergymashuplab.cts;

import org.theenergymashuplab.cts.controller.payloads.TickerPayloadBase;
/**
 * @author crossover
 * @version 1.0
 * @created 28-Sep-2024 8:41:42 PM
 */
public class TenderTickerType extends TickerPayloadBase {

	public EiTenderType tender;

	public TenderTickerType(){

	}

	public EiTenderType getTender() {
		return tender;
	}

	public void setTender(EiTenderType tender) {
		this.tender = tender;
	}

	public TenderTickerType(EiTenderType tender) {
		this.tender = tender;
	}


	@Override
	public String toString() {
		return "TenderTickerType{" +
				"counterParty=" + counterParty +
				", party=" + party +
				", subscriptionId=" + subscriptionId +
				", tickerType=" + tickerType +
				", tender=" + tender +
				'}';
	}
}
