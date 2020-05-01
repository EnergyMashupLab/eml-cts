package com.example.restservice;

public class ClientCreateTenderPayload {
	protected SideType side;
	protected long quantity;
	protected long price;
	protected String info = "ClientCreateTenderPayload";
	
	ClientCreateTenderPayload()	{
	}

	ClientCreateTenderPayload(SideType side, long quantity, long price)	{
		this.side = side;
		this.quantity = quantity;
		this.price = price;
//		System.err.println(this.toString());
	}
	
	@Override
	public String toString()	{
		SideType tempSide = this.side;
		String tempString;
/*		
		tempString = new String ("S");
		if (tempSide == SideType.BUY)	{
			tempString = new String ("B");
		}
*/
		tempString = (tempSide == SideType.BUY)? "B" : "S";
		
		return (info + " side " + tempString + " quantity " +
				quantity + " price " + price);
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

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
}
