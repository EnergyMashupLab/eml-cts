package com.example.restservice;

/*
 * Sent by Market responding to the LME. Includes CtsTenderId
 * as a correlation ID for possible asynchronous service calls
 * (future)
 */

public class MarketCreatedTenderPayload {
	long ctsTenderId;
	public Boolean success = false;
	protected String info = "ClientCreatedTenderPayload";
	
	MarketCreatedTenderPayload(){
		success = true;
	}
	
	MarketCreatedTenderPayload(long id){
		ctsTenderId = id;
		this.success = true;
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
