package com.example.restservice;

import java.time.*;

/*
 * While EiTransaction extends EiTender in the base standards, in Java
 * we use the "has a" construction because the EiTender exists before the
 * EiTransaction does, so the constructor cannot create a new EiTender
 */

public class EiTransaction {
	private final TransactionId transactionId = new TransactionId();	// will be passed to constructor
	private EiTender tender;
	private TransactiveState transactiveState = TransactiveState.TRANSACTION;
	
	/*
	 * Default constructor for JSON deserialization.
	 */
	public EiTransaction()	{		
	}

	public EiTransaction(EiTender tender) {
//		System.err.println("in EiTransaction Constructor tenderId " + tender.getTenderId().getTenderId());
		this.tender = tender;
//		this.print();
	}

	public TransactionId getTransactionId() {
		return this.transactionId;
	}
	
	public EiTender getTender()	{
		return tender;
	}

	public void print() {
		String printStringFormat = "EiTransaction transactionId %d tenderfId %d";
		
		System.err.println("head of EiTransaction.print()");
		System.err.println("transactionId is " + this.transactionId.getTransactionId());		
		System.err.println("tender's Id is " + this.tender.getTenderId().getTenderId());		
		System.err.println(String.format(printStringFormat, 
				this.transactionId.getTransactionId(),
				this.tender.getTenderId().getTenderId() ));
	}
	
	public String toString() {
		return ("EiTransaction transactionId " + this.transactionId.toString() +
				 " tenderId " + this.tender.getTenderId().toString());
	}

	public TransactiveState getTransactiveState() {
		return transactiveState;
	}

	public void setTransactiveState(TransactiveState transactiveState) {
		this.transactiveState = transactiveState;
	}	
	
	
}