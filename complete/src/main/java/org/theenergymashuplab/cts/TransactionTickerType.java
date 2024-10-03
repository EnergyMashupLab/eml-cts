package org.theenergymashuplab.cts;


/**
 * @author crossover
 * @version 1.0
 * @created 28-Sep-2024 8:41:43 PM
 */
public class TransactionTickerType extends TickerPayloadBase {

	public EiTransactionType transaction;

	public TransactionTickerType(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

}