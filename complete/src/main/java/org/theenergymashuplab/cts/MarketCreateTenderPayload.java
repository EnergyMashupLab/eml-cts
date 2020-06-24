package org.theenergymashuplab.cts;

import java.time.Instant;

/*
 * Sent by the LME to the Market with information to be
 * mapped and inserted in the market engine
 * 
 * Information is from an EiCreateTenderPayload received from the LMA
 * and used as constructor parameters by the LME Actor.
 */

/*
 * TODO needs to carry partyId, counterPartyId from EiTender
 */

public class MarketCreateTenderPayload {
	private String info = "MarketCreateTenderPayload";
	private SideType side;
	private long quantity;
	private long price;
	private long ctsTenderId;
	private BridgeInterval bridgeInterval;
	private BridgeInstant expireTime;

	MarketCreateTenderPayload(SideType side, long quantity, long price, long ctsTenderId, Interval interval, Instant expireTime)	{
		/*
		 * Ensure that the number of decimal fraction digits
		 * in price and quantity align with the global one which is presently 3
		 * 
		 * This converts from the external price, e.g. one dollar is 1000L
		 */
		this.side = side;
		this.quantity = quantity;
		this.price = price;
		this.ctsTenderId = ctsTenderId;
		this.bridgeInterval = new BridgeInterval(interval.getDuration().toMinutes(), interval.getDtStart());
		this.expireTime = new BridgeInstant(expireTime);
	}
	
	// Default constructor for JSON
	MarketCreateTenderPayload()	{
	}
	
	@Override
	public String toString()	{
		SideType tempSide = this.side;
		String tempString;

		tempString = (tempSide == SideType.BUY)? "B" : "S";
		
		return (info + " side " + tempString + " quantity " +
				quantity + " price " + price);
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

	public BridgeInstant getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(BridgeInstant expireTime) {
		this.expireTime = expireTime;
	}



}
