package com.example.restservice;

import java.time.*;
import java.util.Random;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EiCancelTender {
	private ActorIdType counterPartyId;
	private ActorIdType partyId;
	private RefIdType requestId;
	// Standard has 1..* TenderIdTypes TODO
	private TenderIdType tenderId;
	
	/*
	 * Default constructor for JSON deserialization.
	 * TO DO change to zero Id values in ActorId and RefId constructors
	 */
	public EiCancelTender()	{
		this.counterPartyId = new ActorIdType();
		this.partyId = new ActorIdType();
		this.requestId = new RefIdType();
		this.tenderId = new TenderIdType();
	}

	/* 
	 * Parallel for EiCreateTransaction, EiCreateTender:
	 * 		pass in a completed Tender/Transaction which includes through its Tender
	 * 		interval, quantity, price, or for EiCancelTender only the TenderIdType.
	 * Add party, counterParty, and requestId for the message payload.
	 */
	public EiCancelTender(TenderIdType tenderId, ActorIdType party, ActorIdType counterParty) {
		this.tenderId = tenderId;
		this.partyId = party;
		this.counterPartyId = counterParty;
		this.requestId = new RefIdType();
	}

	public void print() {
		String printStringFormat = "EiCancelTender.print() tenderId %d partyId %d counterPartyId %d requestId %d ";
			
		System.err.println(String.format(printStringFormat,
				tenderId.toString(),
				partyId.value(), 
				counterPartyId.value(),
				requestId.value()));
	}
	
	public String toString() {
		String printStringFormat = "EiCancelTender.print() tenderId %d partyId %d counterPartyId %d requestId %d ";
			
		System.err.println(String.format(printStringFormat,
				tenderId.toString(),
				partyId.value(), 
				counterPartyId.value(),
				requestId.value()));
		return ("EiCancelTender tenderId " + tenderId.toString() +
				" party " + partyId.toString() +
				" counterParty " + counterPartyId.toString() +
				"requestId " + requestId.toString());
	}

	public ActorIdType getCounterPartyId() {
		return counterPartyId;
	}

	public void setCounterPartyId(ActorIdType counterPartyId) {
		this.counterPartyId = counterPartyId;
	}

	public ActorIdType getPartyId() {
		return partyId;
	}

	public void setPartyId(ActorIdType partyId) {
		this.partyId = partyId;
	}

	public RefIdType getRequestId() {
		return requestId;
	}

	public void setRequestId(RefIdType requestId) {
		this.requestId = requestId;
	}

	public TenderIdType getTenderId() {
		return tenderId;
	}

	public void setTenderIdType(TenderIdType tenderId) {
		this.tenderId = tenderId;
	}
	
	
	
}