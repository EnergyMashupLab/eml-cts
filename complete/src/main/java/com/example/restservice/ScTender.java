package com.example.restservice;

public class ScTender {
	protected Side side;
	protected long quantity;
	protected long price;

	ScTender(Side side, long quantity, long price)	{
		// Ensure that the number of decimal points in price and
		/*
		 * Ensure that the number of decimal fraction digits
		 * in price and quantity align with the global one.
		 * 
		 * This converts from the same price, e.g. one dollar is 1000L
		 */
		this.side = side;
		this.quantity = quantity;
		this.price = price;
//		System.err.println(this.toString());
	}
	
	public String toString()	{
		Side tempSide = this.side;
		String tempString;
		
		tempString = new String ("S");
		if (tempSide == Side.BUY)	{
			tempString = new String ("B");
		}
		
		tempString = (tempSide == Side.BUY)? "B" : "S";
		
		return ("ScTender: side " + tempString + " quantity " + quantity + " price " + price);
	}

	public Side getSide() {
		return side;
	}

	public void setSide(Side side) {
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
	
	
}
