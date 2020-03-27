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
		this.print();
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
//		this.print();
	}

	public long getId() {
		return transactionId.getTransactionId();
	}

	public void print() {
		String printStringFormat = "EiCreatedTransaction tenderId %d partyId %d counterPartyId %d refId %d";
		
		System.err.println(
				String.format(printStringFormat, 
				transactionId.getTransactionId(),
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