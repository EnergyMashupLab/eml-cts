package com.example.restservice;

import java.time.*;
import java.util.Random;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EiRequestPosition {
	private ActorId requestorPartyId;
	private ActorId partyId;
	private RefId requestId;
	private Interval boundingInterval;

	/*
	 * Default constructor for JSON deserialization.
	 * TO DO change to zero Id values in ActorId and RefId constructors
	 */
	public EiRequestPosition()	{
		
		this.requestorPartyId = new ActorId(0);
		this.partyId = new ActorId(0);
		this.requestId = new RefId();
	}

	/* 
	 * Parallel for EiCreateTransaction, EiCreateTender, etc:
	 * 		pass in an Interval
	 * 
	 * Add party, requestorParty, and requestId for the message payload.
	 */

	public EiRequestPosition(ActorId party, ActorId requestorParty, Interval boundingInterval) {

		this.boundingInterval = boundingInterval;
		this.partyId = party;
		this.requestorPartyId = requestorParty;
		this.requestId = new RefId();
		
		System.err.println("EiRequestPosition Constructor before this.print()");	
		this.print();
	}
	
	public void print() {
		String printStringFormat = "EiRequestPosition.print() partyId %d requestorPartyId %d requestId %d  dtStart %s duration %s";
			
		System.err.println(String.format(printStringFormat,
				partyId.getActorId(), 
				requestorPartyId.getActorId(),
				requestId.getRefId(),
				boundingInterval.dtStart.toString(),
				boundingInterval.duration.toString()));
	}
}