package com.example.restservice;

import java.time.*;
import java.util.Random;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EiRequestPosition {
	private ActorIdType requestor;
	private ActorIdType party;
	private RefIdType request;
	private Interval boundingInterval;

	/*
	 * Default constructor for JSON deserialization.
	 * TO DO change to zero Id values in ActorId and RefId constructors
	 */
	public EiRequestPosition()	{
		
		this.requestor = new ActorIdType();
		this.party = new ActorIdType();
		this.request = new RefIdType();
	}

	/* 
	 * Parallel for EiCreateTransaction, EiCreateTender, etc:
	 * 		pass in an Interval
	 * 
	 * Add party, requestorParty, and requestId for the message payload.
	 */

	public EiRequestPosition(ActorIdType party, ActorIdType requestorParty, Interval boundingInterval) {

		this.boundingInterval = boundingInterval;
		this.party = party;
		this.requestor = requestorParty;
		this.request = new RefIdType();
		
		System.err.println("EiRequestPosition Constructor before this.print()");	
		this.print();
	}
	
	public void print() {
		String printStringFormat = "EiRequestPosition.print() partyId %d requestorPartyId %d requestId %d  dtStart %s duration %s";
			
		System.err.println(String.format(printStringFormat,
				party.value(), 
				requestor.value(),
				request.value(),
				boundingInterval.dtStart.toString(),
				boundingInterval.duration.toString()));
	}
}