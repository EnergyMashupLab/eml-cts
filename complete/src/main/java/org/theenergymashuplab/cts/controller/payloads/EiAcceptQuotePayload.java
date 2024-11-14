package org.theenergymashuplab.cts.controller.payloads;

import org.theenergymashuplab.cts.MarketOrderIdType;

/**
 * @author crossover
 * @version 1.0
 * @created 28-Sep-2024 8:41:39 PM
 */
public class EiAcceptQuotePayload extends EiCreateTransactionPayload {

	public MarketOrderIdType referencedQuoteId;
	public long quantity;
	public long price;

	public EiAcceptQuotePayload(){

	}

	public EiAcceptQuotePayload(MarketOrderIdType referencedQuoteId, long quantity, long price){
		this.referencedQuoteId = referencedQuoteId;
		this.quantity = quantity;
		this.price = price;
	}

	public MarketOrderIdType getReferencedQuoteId(){
		return this.referencedQuoteId;
	}

	public void setReferencedQuoteID(MarketOrderIdType referencedQuoteId){
		this.referencedQuoteId = referencedQuoteId;
	}

	public long getQuantity(){
		return this.quantity;
	}

	public void setQuantity(long quantity){
		this.quantity = quantity;
	}

	public long getPrice(){
		return this.price;
	}

	public void setPrice(long price){
		this.price = price;
	}
	
	@Override
	public String toString(){
		return 	"EiAcceptQuotePayload [" +
				"referencedQuoteId=" + referencedQuoteId.toString() +
				"price=" + price + 
				"quantity=" + quantity +
			                         "]";
	}
}
