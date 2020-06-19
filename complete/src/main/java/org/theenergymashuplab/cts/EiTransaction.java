package org.theenergymashuplab.cts;

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
