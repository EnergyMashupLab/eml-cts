package org.theenergymashuplab.cts.controller.payloads;

import org.theenergymashuplab.cts.EiSubscriptionRequestType;
import org.theenergymashuplab.cts.InstrumentSummaryType;
import org.theenergymashuplab.cts.IntervalType;
import org.theenergymashuplab.cts.UpdateType;

/**
 * @author crossover
 * @version 1.0
 * @created 28-Sep-2024 8:41:40 PM
 */
public class EiManageInstrumentDataPayload extends EiSubscriptionRequestType {

	public IntervalType boundingInterval;
	public InstrumentSummaryType instrumentSummaryType;
	public int marketDepth;
	public UpdateType updateType;

	public EiManageInstrumentDataPayload(){

	}
}
