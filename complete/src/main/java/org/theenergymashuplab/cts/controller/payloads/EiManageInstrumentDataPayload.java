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

	public EiManageInstrumentDataPayload(IntervalType boundingInterval, InstrumentSummaryType instrumentSummaryType, 
			int marketDepth, UpdateType updateType){
		this.boundingInterval = boundingInterval;
		this.instrumentSummaryType = instrumentSummaryType;
		this.marketDepth = marketDepth;
		this.updateType = updateType;
	}


	public IntervalType getBoundingInterval(){
		return this.boundingInterval;
	}

	public void setBoundingInterval(IntervalType boundingInterval){
		this.boundingInterval = boundingInterval;
	}

	public InstrumentSummaryType getInstrumentSummaryType(){
		return this.instrumentSummaryType;
	}

	public void setInstrumentSummaryType(InstrumentSummaryType instrumentSummaryType){
		this.instrumentSummaryType = instrumentSummaryType;
	}

	public int getMarketDepth(){
		return this.marketDepth;
	}

	public void setMarketDepth(int marketDepth){
		this.marketDepth = marketDepth;
	}

	public UpdateType getUpdateType(){
		return this.updateType;
	}

	public void setUpdateType(UpdateType updateType){
		this.updateType = updateType;
	}

	@Override
	public String toString(){
		return "EiManageInstrumentDataPayload: [boundingInterval=" + this.boundingInterval.toString() +
				"instrumentSummaryType=" + this.instrumentSummaryType.toString() +
				"marketDepth=" + this.marketDepth +
				"updateType=" + this.updateType.toString() + "]";
	 }
}
