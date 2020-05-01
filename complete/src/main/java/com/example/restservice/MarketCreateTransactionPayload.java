package com.example.restservice;

/*
 * Sent by the Market to LME after a match is detected in the market engine
 */

public class MarketCreateTransactionPayload {
	protected String info = "MarketCreateTransactionPayload";
	protected SideType side;
	long quantity;
	long price;	// multiplied by 10**decimal fraction - 3 by convention
	// tenderId is the CTS ID of the tender made by this SC that cleared
	long ctsTenderId;
	
	MarketCreateTransactionPayload(SideType side, long quantity, long price, long tenderId)	{
		// values from EiTransaction that are not implicit (e.g. market, product)
		this.side = side;
		this.quantity = quantity;
		this.price = price;
		this.ctsTenderId= tenderId;
	}
	
	public String toString()	{
		return (info + " side " + side.toString() + " quantity " + Long.toString(quantity) + 
				" price " + Long.toString(price) + " ctsTenderId " +
				Long.toString(ctsTenderId));
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public SideType getSide() {
		return side;
	}

	public void setSide(SideType side) {
		this.side = side;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public long getCtsTenderId() {
		return ctsTenderId;
	}

	public void setCtsTenderId(long ctsTenderId) {
		this.ctsTenderId = ctsTenderId;
	}
}
