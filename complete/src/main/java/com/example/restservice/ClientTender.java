package com.example.restservice;

public class ClientTender {
	private SideType side;
	private long quantity;
	private long price;	// with multipler for decimal fraction
	
	public ClientTender()	{
		side = SideType.BUY;
		quantity = 0;
		price = 0;
	}
	
	// new ClientTender(randSide, randQuantity, randPrice)
	
	public ClientTender(SideType side, long quantity, long price)	{
		this.side = side;
		this.quantity = quantity;
		this.price = price;
	}
	
	@Override
	public String toString()	{
		return ("ClientTender side " + side.toString() + " quantity " +
				Long.toString(quantity) + " price " +
				Long.toString((price/1000)));			
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
}
