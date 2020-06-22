package org.theenergymashuplab.cts;

// TYPES updated not used - see dao/EiTenderType.java

import java.time.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EiTender {
	private final TenderIdType tenderId = new TenderIdType();	// new tender id on construction
	private final Interval interval;
	private Instant expirationTime;
	private long quantity = 0;
	private long price = 0;	// cents initially. Parity uses long with number of fractional digits
	private SideType side;
	private final boolean integralOnly = false;	// TODO not set, not serialized but comes through as false. Verify behavior.
	private TransactiveState transactiveState = TransactiveState.TENDER;
	
	private static final Logger logger = LogManager.getLogger(
			EiTender.class);
	
	/* 
		Attributes OMITTED from the UML Model at 
			currency
			marketContext
			terms
			emixUid
			envelopeContents
			
		Attributes ADDED to the visible UML model (from StreamPayloadBaseType) 
			price
			time interval
	*/

	public EiTender(Interval interval, long quantity, long price, Instant expirationTime, SideType side) {
		this.interval = interval;
		this.quantity = quantity;
		this.price = price;
		this.expirationTime = expirationTime;
		this.side = side;	// side.BUY or side.SELL
		/*
		 * other attributes are set to defaults
		 * 
		 * TODO reorder constructor parameters to mimic parity terminal client -
		 * 		side, quantity, instrument, price
		 */
		
	}
	
	@Override
	public String toString()	{
		// TODO replace with concatenated strings and toStrings
		String printStringFormat = 
				"EiTender tenderId %d quantity %d price cents %d side %s integralOnly %s expirationTime %s dtStart %s duration %s";

		return (String.format(printStringFormat, tenderId.value(),quantity, price, side,integralOnly,
				expirationTime.toString(),interval.dtStart.toString(),interval.duration.toString()));
	}	
	
	public TenderIdType getTenderId() {
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

	public SideType getSide() {
		return side;
	}

	public void setSide(SideType side) {
		this.side = side;
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
