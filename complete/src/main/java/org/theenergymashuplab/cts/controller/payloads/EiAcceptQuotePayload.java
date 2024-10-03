package org.theenergymashuplab.cts;


/**
 * @author crossover
 * @version 1.0
 * @created 28-Sep-2024 8:41:39 PM
 */
public class EiAcceptQuotePayload extends EiCreateTransactionPayload {

	public MarketOrderIdType referencedQuoteId;

	public EiAcceptQuotePayload(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

}