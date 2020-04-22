package com.example.restservice;

import java.time.*;

/*
 * While EiTransaction extends EiTender in the base standards, in Java
 * we use the "has a" construction because the EiTender exists before the
 * EiTransaction does, so the constructor cannot create a new EiTender
 */

public class EiTransaction {
	private final TransactionIdType transactionId = new TransactionIdType();	// will be passed to constructor
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

	public TransactionIdType getTransactionId() {
		return this.transactionId;
	}
	
	public EiTender getTender()	{
		return tender;
	}

	public void print() {
		String printStringFormat = "EiTransaction transactionId %d tenderfId %d";
		
		System.err.println("head of EiTransaction.print()");
		System.err.println("transactionId is " + this.transactionId.value());		
		System.err.println("tender's Id is " + this.tender.getTenderId().value());		
		System.err.println(String.format(printStringFormat, 
				this.transactionId.value(),
				this.tender.getTenderId().value() ));
	}
	
	public String toString() {
		return ("EiTransaction transactionId " + this.transactionId.toString() +
				 " tenderId " + this.tender.getTenderId().value());
	}

	public TransactiveState getTransactiveState() {
		return transactiveState;
	}

	public void setTransactiveState(TransactiveState transactiveState) {
		this.transactiveState = transactiveState;
	}	
	
	
}