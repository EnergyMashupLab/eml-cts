package com.example.restservice;

public class ClientCreateTransactionPayload {
	String info = "ClientCreateTransactionPayload";
	SideType side;
	private long quantity;
	private long price;	// multiplied by 10**decimal fraction - 3 by convention
	// ctsTenderId is the CTS ID of the tender made by this SC that cleared
	long ctsTenderId; // matched in Parity
	
	ClientCreateTransactionPayload(SideType side, long quantity, long price, long tenderId)	{
		// values from EiTransaction that are not implicit (e.g. market, product)
		this.side = side;
		this.quantity = quantity;
		this.price = price;
		this.ctsTenderId= tenderId;
	}
	
	public String toString()	{
		return (info + " side " + side.toString() + " quantity " + Long.toString(quantity) + 
				" price " + Long.toString(price));
	}
}
