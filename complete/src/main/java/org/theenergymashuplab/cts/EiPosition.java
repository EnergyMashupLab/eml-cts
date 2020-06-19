package org.theenergymashuplab.cts;

/*
 * ArrayList element for the EiReplyPosition payload -
 * 		a list of positions
 */
public class EiPosition {
	// the party whose position is described
	private ActorIdType positionParty;	
	private Interval interval;
	private long quantity;	// net position for interval
	// IMPLICIT the market, time granularity, and product
	
	EiPosition()	{
		// empty for JSON serialization
	}
	
	EiPosition(Interval interval, long quantity, ActorIdType positionParty)	{
		this.interval = interval;
		this.quantity = quantity;
		this.positionParty = positionParty;
	}

	public ActorIdType getPositionParty() {
		return positionParty;
	}

	public void setPositionParty(ActorIdType positionParty) {
		this.positionParty = positionParty;
	}

	public Interval getInterval() {
		return interval;
	}

	public void setInterval(Interval interval) {
		this.interval = interval;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	
	
}
