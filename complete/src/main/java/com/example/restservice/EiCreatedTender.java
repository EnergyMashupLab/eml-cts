package com.example.restservice;

import java.time.*;

public class EiCreatedTender {
	private final TenderId tenderId;
	private final ActorId partyId;
	private final ActorId counterPartyId;
	public EiResponse response;
//	public ArrayofResponses responses; NOT USED
	private final RefId refId = new RefId();

	
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