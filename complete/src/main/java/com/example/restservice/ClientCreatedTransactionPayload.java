package com.example.restservice;

public class ClientCreatedTransactionPayload {
	public Boolean success = false;
	
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
