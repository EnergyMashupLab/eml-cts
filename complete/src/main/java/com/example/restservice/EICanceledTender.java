package com.example.restservice;

import java.time.*;

public class EICanceledTender {
	private final ActorId partyId;
	private final ActorId counterPartyId;
	public EiResponse response;
//	public ArrayofResponses responses; NOT USED
//	refId is in the EiResponse

	
	public EICanceledTender(ActorId partyId, ActorId counterPartyId, EiResponse response) {
		this.partyId = partyId;
		this.counterPartyId = counterPartyId;
		this.response = response;
	}

	//Default constructor for JSON serialization
	public EICanceledTender()	{
		this.partyId = new ActorId();
		this.counterPartyId = new ActorId();
		this.response = new EiResponse(200, "OK");
	}
	
	
	public void print() {
		String printStringFormat = 
				"EiCanceledTender  partyId %d counterPartyId %d refId %d ";
		
		System.err.println(
				String.format(printStringFormat, 
				partyId.getActorId(), 
				counterPartyId.getActorId(), 
				response.getRefId()));
	}
	
	public String toString()	{
		return "EiCanceledTender Soure RefId " +
				"PENDING" +
				" partyId " + partyId.toString() +
				" counterPartyId " + counterPartyId.toString() +
				" response " + response.toString();
	}

	public EiResponse getResponse() {
		return response;
	}

	public void setResponse(EiResponse response) {
		this.response = response;
	}

	public ActorId getPartyId() {
		return partyId;
	}

	public ActorId getCounterPartyId() {
		return counterPartyId;
	}
	
	

}