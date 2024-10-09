package org.theenergymashuplab.cts;


import org.theenergymashuplab.cts.controller.payloads.TickerPayloadBase;
/**
 * @author crossover
 * @version 1.0
 * @created 28-Sep-2024 8:41:42 PM
 */
public class RfqTickerType extends TickerPayloadBase {

	public EiRfqType rfq;

	public RfqTickerType(){
	}

	public RfqTickerType(EiRfqType rfq){
		this.rfq = rfq;
	}

	public void setRfq(EiRfqType rfq) { this.rfq = rfq; }

	public EiRfqType getRfq() { return rfq; }

	@Override
	public String toString() {
		return "RfqTickerType {" +
				" rfq = " + rfq + " }";
	}
}
