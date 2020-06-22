package org.theenergymashuplab.cts;

/*
 * Sent by TEUA to the requesting Client/SC. Includes CtsTenderId
 */

public class ClientCreatedTenderPayload	{
	long ctsTenderId;
	private Boolean success = false;
	private String info = "ClientCreatedTenderPayload";
	
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
				" CtsTenderId " + ctsTenderId);
	}

	public long getCtsTenderId() {
		return ctsTenderId;
	}

	public void setCtsTenderId(long ctsTenderId) {
		this.ctsTenderId = ctsTenderId;
	}
}
