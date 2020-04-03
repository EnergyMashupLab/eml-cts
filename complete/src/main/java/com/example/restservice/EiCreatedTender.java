package com.example.restservice;

import java.time.*;

public class EiCreatedTender {
	private TenderId tenderId;
	private ActorId partyId;
	private ActorId counterPartyId;
	public EiResponse response;
//	public ArrayofResponses responses; NOT USED
	private final RefId refId = new RefId();

	/*
	 * Default constructor for JSON deserialization.
	 * TO DO change to zero Id values in ActorId and RefId constructors
	 */
	public EiCreatedTender()	{		
	}
	
	public EiCreatedTender(
			TenderId tenderId,
			ActorId partyId,
			ActorId counterPartyId,
			EiResponse response) {

		this.tenderId = tenderId;
		this.partyId = partyId;
		this.counterPartyId = counterPartyId;
		this.response = response;
//		this.print();
	}

	public long getId() {
		return tenderId.getTenderId();
	}

	public void print() {
		String printStringFormat = "EiCreatedTender tenderId %d partyId %d counterPartyId %d refId %d";
		
		System.err.println(
				String.format(printStringFormat, 
				tenderId.getTenderId(),
				partyId.getActorId(), 
				counterPartyId.getActorId(), 
				refId.getRefId()));
	}
	
	public String toString() {
		String printStringFormat = "EiCreatedTender tenderId %d partyId %d counterPartyId %d refId %d";
		
			String.format(printStringFormat, 
				tenderId.getTenderId(),
				partyId.getActorId(), 
				counterPartyId.getActorId(), 
				refId.getRefId());
			return printStringFormat;
	}
	
	public EiResponse getResponse() {
		return response;
	}

	public void setResponse(EiResponse response) {
		this.response = response;
	}

	public TenderId getTenderId() {
		return tenderId;
	}

	public ActorId getPartyId() {
		return partyId;
	}

	public ActorId getCounterPartyId() {
		return counterPartyId;
	}

	public RefId getRefId() {
		return refId;
	}
}