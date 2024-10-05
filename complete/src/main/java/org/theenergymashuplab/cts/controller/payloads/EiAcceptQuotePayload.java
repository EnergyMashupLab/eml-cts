package org.theenergymashuplab.cts.controller.payloads;

import org.theenergymashuplab.cts.MarketOrderIdType;

/**
 * @author crossover
 * @version 1.0
 * @created 28-Sep-2024 8:41:39 PM
 */
public class EiAcceptQuotePayload extends EiCreateTransactionPayload {

	public MarketOrderIdType referencedQuoteId;

	public EiAcceptQuotePayload(){

	}

	public EiAcceptQuotePayload(MarketOrderIdType referencedQuoteId){
		this.referencedQuoteId = referencedQuoteId;
	}

	public MarketOrderIdType getReferencedQuoteId(){
		return this.referencedQuoteId;
	}

	public void setReferencedQuoteID(MarketOrderIdType referencedQuoteId){
		this.referencedQuoteId = referencedQuoteId;
	}

	@Override
	public String toString(){
		return 	"EiAcceptQuotePayload [" +
				"referencedQuoteId=" + referencedQuoteId.toString() +
			                         "]";
	}
}
