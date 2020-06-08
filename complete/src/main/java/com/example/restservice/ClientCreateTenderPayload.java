package com.example.restservice;

import java.time.*;
//	import ...Interval; 

public class ClientCreateTenderPayload {
	private String info = "ClientCreateTenderPayload";
	private SideType side;
	private long quantity;
	private long price;
	private long ctsTenderId;

	private BridgeInterval bridgeInterval;
	private BridgeInstant bridgeExpireTime;	

	// Uses BridgeInterval to avoid serialization issues
	
	ClientCreateTenderPayload()	{	// json
	}
	
	ClientCreateTenderPayload(SideType side, long quantity, long price)	{
		// DEBUG start time and expiration time for test payloads
		Instant expire = null;
		Instant dtStart = Instant.parse("2020-05-31T10:00:00.00Z");	
		expire = dtStart.plusSeconds(60*60*11);	// DEBUG 11 hours after dtStart
		// System.err.println("ClientCreateTenderPayload: expire " +
		//		expire.toString());
		
		this.side = side;
		this.quantity = quantity;
		this.price = price;
		// DEBUG this.expireTime = expire;
		this.bridgeInterval = new BridgeInterval(60, dtStart);
		this.bridgeExpireTime = new BridgeInstant(expire);
	}
	
	@Override
	public String toString()	{
		SideType tempSide = this.side;
		String tempString;

		tempString = (tempSide == SideType.BUY)? "B" : "S";	
		return (info + " side " + tempString + " quantity " +
				quantity + " price " + price);
	}
	
	public Interval getInterval()	{
		// converts internal representation to a CTS Interval
		Interval tempInterval = 
				new Interval(this.bridgeInterval.getDurationInMinutes(),
				this.bridgeInterval.getDtStart().asInstant());
		return tempInterval;
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

	public BridgeInterval getBridgeInterval() {
		return bridgeInterval;
	}

	public void setBridgeInterval(BridgeInterval bridgeInterval) {
		this.bridgeInterval = bridgeInterval;
	}

	public BridgeInstant getBridgeExpireTime() {
		return bridgeExpireTime;
	}

	public void setBridgeExpireTime(BridgeInstant bridgeExpireTime) {
		this.bridgeExpireTime = bridgeExpireTime;
	}


	
}
