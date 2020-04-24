package com.example.restservice;

public class ScTenderResponse {
	long ctsTenderId;
	// class for extensibility - the CTS TenderId may evolve into a class
	
	ScTenderResponse(){
	}
	
	ScTenderResponse(long id){
		ctsTenderId = id;
	}

	public long getCtsTenderId() {
		return ctsTenderId;
	}

	public void setCtsTenderId(long ctsTenderId) {
		this.ctsTenderId = ctsTenderId;
	}
	
	
}
