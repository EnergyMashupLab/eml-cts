package com.example.restservice;

import java.time.*;
import java.util.Random;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EiCreateTender {
	private ActorId counterPartyId;
	private ActorId partyId;
	private RefId requestId;
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
		this.counterPartyId = new ActorId();
		this.partyId = new ActorId();
		this.requestId = new RefId();
	}

	/* 
	 * Parallel for EiCreateTransaction, EiCreateTender:
	 * 		pass in a completed Tender/Transaction which includes through its Tender interval, quantity, price,
	 * 		or for EiCancelTender only the TenderId.
	 * 
	 * Add party, counterParty, and requestId for the message payload.
	 */

	public EiCreateTender(EiTender tender, ActorId party, ActorId counterParty) {

		this.tender = tender;
		this.partyId = party;
		this.counterPartyId = counterParty;
		this.requestId = new RefId();
		
//		System.err.println("EiCreateTender Constructor before this.print()");
//		this.print();
	}

	public void print() {
		String printStringFormat = "EiCreateTender.print() tenderId %d partyId %d counterPartyId %d requestId %d  dtStart %s";
			
		System.err.println(String.format(printStringFormat,
				tender.getTenderId().getTenderId(),
				partyId.getActorId(), 
				counterPartyId.getActorId(),
				requestId.getRefId(),
				tender.getInterval().dtStart.toString()));
	}

	public ActorId getCounterPartyId() {
		return counterPartyId;
	}

	public void setCounterPartyId(ActorId counterPartyId) {
		this.counterPartyId = counterPartyId;
	}

	public ActorId getPartyId() {
		return partyId;
	}

	public void setPartyId(ActorId partyId) {
		this.partyId = partyId;
	}

	public RefId getRequestId() {
		return requestId;
	}

	public void setRequestId(RefId requestId) {
		this.requestId = requestId;
	}

	public EiTender getTender() {
		return tender;
	}

	public void setTender(EiTender tender) {
		this.tender = tender;
	}
	
	
	
}