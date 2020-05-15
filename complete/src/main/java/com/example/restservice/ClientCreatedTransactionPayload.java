package com.example.restservice;

/*
 * Response from Client/SC to its TEUA
 */
public class ClientCreatedTransactionPayload {
	private Boolean success = false;
	
	ClientCreatedTransactionPayload()	{
		success = true;
	// class may be extended further
	}
	
	@Override
	public String toString()	{
		return ("ClientCreatedTransactionPayload success is " + success.toString());
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}	
}
