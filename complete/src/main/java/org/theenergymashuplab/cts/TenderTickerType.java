package org.theenergymashuplab.cts;

import org.theenergymashuplab.cts.controller.payloads.TickerPayloadBase;

/**
 * @author crossover
 * @version 1.0
 * @created 18-Feb-2025 11:30:37 AM
 */
public class TenderTickerType extends TickerPayloadBase {

    public EiTenderType tender;

    public TenderTickerType() {

    }

    public EiTenderType getTender() {
        return tender;
    }

    public void setTender(EiTenderType tender) {
        this.tender = tender;
    }

    @Override
    public String toString() {
        return "TenderTickerType{" +
                "tender=" + tender +
                ", counterParty=" + counterParty +
                ", party=" + party +
                ", side=" + side +
                ", subscriptionId=" + subscriptionId +
                ", tickerType=" + tickerType +
                '}';
    }
}
