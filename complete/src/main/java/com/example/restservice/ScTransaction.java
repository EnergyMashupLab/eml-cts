package com.example.restservice;

public class ScTransaction {
	String info = "ScTransaction";
	SideType side;
	long quantity;
	long price;
	// tenderId is the CTS ID of the tender made by this SC that cleared
	long tenderId;
	
	ScTransaction(SideType side, long quantity, long price, long tenderId)	{
		// values from EiTransaction that are not implicit (e.g. market, product)
		this.side = side;
		this.quantity = quantity;
		this.price = price;
		this.tenderId= tenderId;
	}
	
	public String toString()	{
		return ("ScTransaction side " + side.toString() + 
				" quantity " + Long.toString(quantity) + 
				" price " + Long.toString(price));
	}
}
