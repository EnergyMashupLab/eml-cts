package org.theenergymashuplab.cts;


import org.theenergymashuplab.cts.controller.payloads.TickerPayloadBase;

/**
 * @author crossover
 * @version 1.0
 * @created 28-Sep-2024 8:41:43 PM
 */
public class TransactionTickerType extends TickerPayloadBase {

	public EiTransactionType transaction;

	public TransactionTickerType(){

	}

	public EiTransactionType getTransaction() {
		return transaction;
	}

	public void setTransaction(EiTransactionType transaction) {
		this.transaction = transaction;
	}

	@Override
	public String toString() {
		return "TransactionTickerType{" +
				"transaction=" + transaction +
				", counterParty=" + counterParty +
				", party=" + party +
				", subscriptionId=" + subscriptionId +
				", tickerType=" + tickerType +
				'}';
	}

	public TransactionTickerType(EiTransactionType transaction) {
		this.transaction = transaction;
	}
}
