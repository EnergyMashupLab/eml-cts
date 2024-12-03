package org.theenergymashuplab.cts.controller.payloads;

import org.theenergymashuplab.cts.ActorIdType;
import org.theenergymashuplab.cts.MarketOrderIdType;

public class ClientAcceptQuotePayload{
	private String info = "ClientAcceptQuotePayload";
	private long quantity;
	private long price;

	//This is essential, it is the only way that we are able to grab quotes and act upon them
	//in the LME
	private MarketOrderIdType referencedQuoteId;
	private long tempReferencedQuoteId;


	//JSON
	public ClientAcceptQuotePayload(){

	}

	public ClientAcceptQuotePayload(long tempReferencedQuoteId,long quantity, long price){
		this.tempReferencedQuoteId = tempReferencedQuoteId;
		this.quantity = quantity;
		this.price = price;
	}

	public MarketOrderIdType getReferencedQuoteId(){
		return this.referencedQuoteId;
	}

	public void setReferencedQuoteId(MarketOrderIdType referencedQuoteId){
		this.referencedQuoteId = referencedQuoteId;
	}

	public long getTempReferencedQuoteId(){
		return this.tempReferencedQuoteId;
	}

	public void setTempReferencedQuoteId(long tempReferencedQuoteId){
		this.tempReferencedQuoteId = tempReferencedQuoteId;
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
