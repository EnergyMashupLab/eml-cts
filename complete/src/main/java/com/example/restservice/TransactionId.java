package com.example.restservice;

import java.util.concurrent.atomic.AtomicLong;

public class TransactionId {
	private long transactionId;
	private static final String template = "transactionId %d";
	private static final AtomicLong transactionIdCounter = new AtomicLong();
	
	TransactionId(long transactionId){
		/*
		 * If parameter is zero, create a TenderId with value zero.
		 * For creating zero values in initilizers in EiTransaction, EiCancelTender, etc
		 */
		this.transactionId = transactionId;
		if (transactionId != 0) {
			transactionIdCounter.set(transactionId + 1); 	//to avoid future collisions
		}
	}
	
	TransactionId(){
		transactionId = transactionIdCounter.incrementAndGet();
//		System.err.format("Creating TransactionId = %d ", this.transactionId);
//		System.err.println();
	}
	
	public long getTransactionId()	{
		return this.transactionId;
	}
	
	public String print() {
		return String.format(template, transactionId);
	}
	
	public String toString()	{
		return String.valueOf(this.transactionId);
	}

	public static String getTemplate() {
		return template;
	}

	public static AtomicLong getTransactionidcounter() {
		return transactionIdCounter;
	}

	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}
	
	
}
