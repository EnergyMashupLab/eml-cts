package com.example.restservice;

/*
 * Sent by Market responding to MarketCreateTender from LME.
 * 
 * Includes CtsTenderId as a correlation ID
 */

public class MarketCreatedTenderPayload {
	private long ctsTenderId = 0;
	public Boolean success = false;
	private String info = "ClientCreatedTenderPayload";
	private String parityOrderId = null;
	
	MarketCreatedTenderPayload(){
		success = true;
	}
	
	MarketCreatedTenderPayload(long id){
		ctsTenderId = id;
		// parityOrderId is known after Order is entered
		this.success = true;
	}
	
	MarketCreatedTenderPayload(long ctsTenderId, String parityOrderId)	{
		this.ctsTenderId = ctsTenderId;
		this.parityOrderId = parityOrderId;
		this.success = true;
	}
	
	public String getParityOrderId() {
		return parityOrderId;
	}

	public void setParityOrderId(String parityOrderId) {
		this.parityOrderId = parityOrderId;
	}

	@Override
	public String toString()	{
		return (info + " success is " + success.toString() +
				" CtsTenderId " + Long.toString(ctsTenderId));
	}

	public long getCtsTenderId() {
		return ctsTenderId;
	}

	public void setCtsTenderId(long ctsTenderId) {
		this.ctsTenderId = ctsTenderId;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
}
