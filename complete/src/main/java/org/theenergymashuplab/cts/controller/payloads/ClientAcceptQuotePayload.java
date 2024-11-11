package org.theenergymashuplab.cts.controller.payloads;

import org.theenergymashuplab.cts.MarketOrderIdType;

public class ClientAcceptQuotePayload{
	private String info = "ClientAcceptQuotePayload";
	private long quantity;
	private long price;
	private MarketOrderIdType referencedQuoteId;


	//JSON
	public ClientAcceptQuotePayload(){

	}

	public MarketOrderIdType getReferencedQuoteId(){
		return this.referencedQuoteId;
	}

	public void setReferencedQuoteId(MarketOrderIdType referencedQuoteId){
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
	
	public String getInfo(){
		return this.info;
	}

	public void setInfo(String info){
		this.info = info;
	}
}
