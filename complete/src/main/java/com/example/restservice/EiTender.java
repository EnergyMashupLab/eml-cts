package com.example.restservice;

import java.time.*;

public class EiTender {
	private final TenderId tenderId = new TenderId();	// new tender id on construction
	private final Interval interval;
	private Instant expirationTime;
	private long quantity = 0;
	private long price = 0;	// cents initially. Parity uses long with number of fractional digits
	private final Side side;
	private final boolean integralOnly = false;
	private TransactiveState transactiveState = TransactiveState.TENDER;
	
	/* 
		Attributes OMITTED from the UML Model at 
			currency
			marketContext
			terms
			emixUid
			envelopeContents
			
		Attributes ADDED to the visible UML model at 
			price
			time interval (flattening of StreamPayloadBaseType from WS-Calendar)
	*/

	public EiTender(Interval interval, long quantity, long price, Instant expirationTime, Side side) {
		this.interval = interval;
		this.quantity = quantity;
		this.price = price;
		this.expirationTime = expirationTime;
		this.side = side;	// side.BUY or side.SELL
		// other attributes are set to defaults
		
//		System.err.println("EiTender Constructor Tender quantity " + quantity + " price " + price + " Side " + side);
//		this.print();
	}

	public void print()	{
		String printStringFormat = "EiTender.print() tenderId %d quantity %d price cents %d side %s integralOnly %s expirationTime %s dtStart %s duration %s";
		
		System.err.println(
				String.format(printStringFormat, tenderId.getTenderId(),quantity, price, side,integralOnly,
				expirationTime.toString(),interval.dtStart.toString(),interval.duration.toString()));
	}	
	
	public TenderId getTenderId() {
		return tenderId;
	}

	public Instant getExpireTime() {
		return expirationTime;
	}

	public void setExpireTime(Instant expirationTime) {
		this.expirationTime = expirationTime;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public TransactiveState getTransactiveState() {
		return transactiveState;
	}

	public void setTransactiveState(TransactiveState transactiveState) {
		this.transactiveState = transactiveState;
	}

	public Interval getInterval() {
		return interval;
	}

	public Side getSide() {
		return side;
	}

	public boolean isIntegralOnly() {
		return integralOnly;
	}
	
	public Instant getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(Instant expirationTime) {
		this.expirationTime = expirationTime;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}


}