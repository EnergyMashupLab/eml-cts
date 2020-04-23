package com.example.restservice;

import java.time.*;

public class EiCreatedTransaction {
	private TransactionIdType transactionId;
	private ActorIdType partyId;
	private ActorIdType counterPartyId;
	public EiResponse response;
//	public ArrayofResponses responses; NOT USED YET
	private final RefIdType refId = new RefIdType();

	// Default initializer for JSON serialization
	public EiCreatedTransaction() {
	}
	
	public TransactionIdType getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(TransactionIdType transactionId) {
		this.transactionId = transactionId;
	}

	public void setPartyId(ActorIdType partyId) {
		this.partyId = partyId;
	}

	public void setCounterPartyId(ActorIdType counterPartyId) {
		this.counterPartyId = counterPartyId;
	}

	public EiCreatedTransaction(
			TransactionIdType transactionId,
			ActorIdType partyId,
			ActorIdType counterPartyId,
			EiResponse response) {

		this.transactionId = transactionId;
		this.partyId = partyId;
		this.counterPartyId = counterPartyId;
		this.response = response;
	}

	public long getId() {
		return transactionId.value();
	}

	public void print() {		
		System.err.println(
				"EiCreatedTransaction transactionId " +
				transactionId.toString() +
				" partyId " + partyId.value() +
				" counterPartyId " + counterPartyId.value() +
				" refId " + refId.toString());
	}
	
	public String toString()	{
		return ("EiCreatedTransaction transactionId " +
				transactionId.toString() +
				" partyId " + partyId.toString() +
				" counterPartyId " + counterPartyId.toString() +
				" refId " + refId.toString());
	}
	
	
	public EiResponse getResponse() {
		return response;
	}

	public void setResponse(EiResponse response) {
		this.response = response;
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