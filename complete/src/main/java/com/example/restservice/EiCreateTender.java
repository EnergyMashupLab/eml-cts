package com.example.restservice;

import java.time.*;
import java.util.Random;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EiCreateTender {
	private ActorIdType counterPartyId;
	private ActorIdType partyId;
	private RefIdType requestId;
	private EiTender tender;
	
	/*
	@JsonIgnore
	private final Random rand = new Random();
	 */
	
	/*
	 * Default constructor for JSON deserialization.
	 * TO DO change to zero Id values in ActorId and RefId constructors
	 */
	public EiCreateTender()	{		
		this.counterPartyId = new ActorIdType();
		this.partyId = new ActorIdType();
		this.requestId = new RefIdType();
	}

	/* 
	 * Parallel for EiCreateTransaction, EiCreateTender:
	 * 		pass in a completed Tender/Transaction which includes through its Tender interval, quantity, price,
	 * 		or for EiCancelTender only the TenderId.
	 * 
	 * Add party, counterParty, and requestId for the message payload.
	 */

	public EiCreateTender(EiTender tender, ActorIdType party, ActorIdType counterParty) {

		this.tender = tender;
		this.partyId = party;
		this.counterPartyId = counterParty;
		this.requestId = new RefIdType();
		
//		System.err.println("EiCreateTender Constructor before this.print()");
//		this.print();
	}

	public void print() {
		String printStringFormat = "EiCreateTender.print() tenderId %d partyId %d counterPartyId %d requestId %d  dtStart %s";
			
		System.err.println(String.format(printStringFormat,
				tender.getTenderId().value(),
				partyId.value(), 
				counterPartyId.value(),
				requestId.value(),
				tender.getInterval().dtStart.toString()));
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

	public EiTender getTender() {
		return tender;
	}

	public void setTender(EiTender tender) {
		this.tender = tender;
	}
	
	
	
}