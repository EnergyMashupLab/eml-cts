package com.example.restservice;

/*
 * Sent by the Market to LME after a match is detected in the market engine
 */

public class MarketCreateTransactionPayload {
	private String info = "MarketCreateTransactionPayload";
	private SideType side;
	long quantity = 0;	// quantity transacted. At least two Transactions created for a match
	long price;	// in minimum price increment. May not equal tendered price
	// ctsTenderId is the CTS cleared tenderId
	long ctsTenderId = 0;	// set before sending tender -- Map maintained by CtsBridge	
	public long getMatchNumber() {
		return matchNumber;
	}

	public void setMatchNumber(long matchNumber) {
		this.matchNumber = matchNumber;
	}

	public String getParityOrderId() {
		return parityOrderId;
	}

	public void setParityOrderId(String parityOrderId) {
		this.parityOrderId = parityOrderId;
	}

	long matchNumber = 0;	// parity matchNumber for this match
	private String parityOrderId = null;
	
	
	// 	MarketCreateTransactionPayload(String parityOrderId, long quantity, long price, long matchNumber, SideType side)	
	
	
	MarketCreateTransactionPayload(String parityOrderId, long quantity, long price, long ctsTenderId, long matchNumber, SideType side)	{
		// values from EiTransaction that are not implicit (e.g. market, product)
		this.parityOrderId = parityOrderId;
		this.quantity = quantity;
		this.price = price;
		this.ctsTenderId= ctsTenderId;
		this.matchNumber = matchNumber;	// parity match number - may be useful
		this.side = side;
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
