package com.example.restservice;

import java.time.*;

public class EiCreatedTransaction {
	private TransactionId transactionId;
	private ActorId partyId;
	private ActorId counterPartyId;
	public EiResponse response;
//	public ArrayofResponses responses; NOT USED
	private final RefId refId = new RefId();

	// Default initializer for JSON serialization
	public EiCreatedTransaction() {
	}
	
	public TransactionId getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(TransactionId transactionId) {
		this.transactionId = transactionId;
	}

	public void setPartyId(ActorId partyId) {
		this.partyId = partyId;
	}

	public void setCounterPartyId(ActorId counterPartyId) {
		this.counterPartyId = counterPartyId;
	}

	public EiCreatedTransaction(
			TransactionId transactionId,
			ActorId partyId,
			ActorId counterPartyId,
			EiResponse response) {

		this.transactionId = transactionId;
		this.partyId = partyId;
		this.counterPartyId = counterPartyId;
		this.response = response;
	}

	public long getId() {
		return transactionId.getTransactionId();
	}

	public void print() {		
		System.err.println(
				"EiCreatedTransaction transactionId " +
				transactionId.toString() +
				" partyId " + partyId.toString() +
				" counterPartyId " + counterPartyId.toString() +
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