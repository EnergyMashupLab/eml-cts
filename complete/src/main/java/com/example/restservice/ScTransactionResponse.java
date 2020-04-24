package com.example.restservice;

public class ScTransactionResponse {
	public Boolean success = false;
	
	ScTransactionResponse()	{
		success = true;
	// class may be extended further
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}
	
	
}
