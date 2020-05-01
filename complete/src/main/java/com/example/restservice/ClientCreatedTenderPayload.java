package com.example.restservice;

/*
 * Sent by TEUA to the requesting Client/SC. Includes CtsTenderId
 */

public class ClientCreatedTenderPayload	{
	long ctsTenderId;
	public Boolean success = false;
	protected String info = "ClientCreatedTenderPayload";
	
	ClientCreatedTenderPayload(){
		success = true;
	}
	
	ClientCreatedTenderPayload(long id){
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
}
