package com.example.restservice;

import java.time.*;
import java.util.Random;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EiCreateTransactionPayload {
	private ActorIdType counterPartyId;
	private ActorIdType partyId;
	private RefIdType requestId;
	private EiTransaction transaction;
	
	/*
	@JsonIgnore
	private final Random rand = new Random();
	 */
	
	// Default initializer for JSON serialization
	public EiCreateTransactionPayload() {
	}

	public EiCreateTransactionPayload(EiTransaction eiTransaction)	{
		this.counterPartyId = new ActorIdType();
		this.partyId = new ActorIdType();
		this.requestId = new RefIdType();
		this.transaction = eiTransaction;
	}

	/* 
	 * Parallel for EiCreateTransactionPayload, EiCreateTender:
	 * 		pass in a completed Tender/Transaction which includes through its Tender interval, quantity, price,
	 * 		or for EiCancelTender only the TenderId.
	 * 
	 * Add party, counterParty, and requestId for the message payload.
	 */
	public EiCreateTransactionPayload(EiTransaction transaction, ActorIdType party, ActorIdType counterParty) {

		this.transaction = transaction;
		this.partyId = party;
		this.counterPartyId = counterParty;
		this.requestId = new RefIdType();
		
//		System.err.println("EiCreateTransactionPayload Constructor before print()");
//		this.print();
	}

	public void print() {
		String printStringFormat = "EiCreateTransactionPayload transactionId %d partyId %d counterPartyId %d requestId %d  dtStart %s";
		
		System.err.println(String.format(printStringFormat,
				transaction.getTransactionId().value(),
				partyId.value(), 
				counterPartyId.value(),
				requestId.value(),
				transaction.getTender().
					getInterval().dtStart.toString()));
	}
	
	public String toString() {
		String printStringFormat = "EiCreateTransactionPayload transactionId %d partyId %d counterPartyId %d requestId %d  dtStart %s";
		
		return ("EiCreateTransactionPayload transactionId " + transaction.getTransactionId().toString() +
				" partyid " + partyId.toString() +
				" counterPartyid " + counterPartyId.toString() +			
				" requestId " + requestId.toString() +
				transaction.getTender().getTenderId().value());
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

	public EiTransaction getTransaction() {
		return transaction;
	}

	public void setTransaction(EiTransaction transaction) {
		this.transaction = transaction;
	}
}