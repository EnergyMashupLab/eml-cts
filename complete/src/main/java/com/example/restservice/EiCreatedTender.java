package com.example.restservice;

import java.time.*;

public class EiCreatedTender {
	private TenderIdType tenderId;
	private ActorIdType partyId;
	private ActorIdType counterPartyId;
	public EiResponse response;
//	public ArrayofResponses responses; NOT USED
	private final RefIdType refId = new RefIdType();

	/*
	 * Default constructor for JSON deserialization.
	 * TO DO change to zero Id values in ActorId and RefId constructors
	 */
	public EiCreatedTender()	{		
	}
	
	public EiCreatedTender(
			TenderIdType tenderId,
			ActorIdType partyId,
			ActorIdType counterPartyId,
			EiResponse response) {

		this.tenderId = tenderId;
		this.partyId = partyId;
		this.counterPartyId = counterPartyId;
		this.response = response;
	}

	public long getId() {
		return tenderId.value();
	}

	public void print() {		
		System.err.println(
				"EiCreatedTender tenderId " +
				tenderId.toString() +
				" partyId " + partyId.toString() +
				" counterPartyId " + counterPartyId.toString() +
				" refId " + refId.toString() +
				" response " + response.toString());
	}
	
	public String toString() {
		return "EiCreatedTender tenderId " +
				tenderId.toString() +
				" partyId " + partyId.toString() +
				" counterPartyId " + counterPartyId.toString() +
				" refId " + refId.toString() +
				" response " + response.toString();
	}
	
	public EiResponse getResponse() {
		return response;
	}

	public void setResponse(EiResponse response) {
		this.response = response;
	}

	public TenderIdType getTenderId() {
		return tenderId;
	}

	public ActorIdType getPartyId() {
		return partyId;
	}

	public ActorIdType getCounterPartyId() {
		return counterPartyId;
	}

	public RefIdType getRefId() {
		return refId;
	}
}
