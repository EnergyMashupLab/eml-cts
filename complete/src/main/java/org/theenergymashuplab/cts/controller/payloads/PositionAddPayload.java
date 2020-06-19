/*
 * 
 * @Author: Dhruvin Desai
 * 
 */

package org.theenergymashuplab.cts.controller.payloads;



public class PositionAddPayload {

	Interval interval;
	long quantity;
	
	// Class constructor.
	public PositionAddPayload(Interval tinterval, long tquantity){
		this.interval = tinterval;
		this.quantity = tquantity;
	}
	
	// Default Constructor.
	public PositionAddPayload() {
		this.interval = null;
		this.quantity = 0;
	}
	/**
	 * @return the interval
	 */
	public Interval getInterval() {
		return interval;
	}
	/**
	 * @param interval the interval to set
	 */
	public void setInterval(Interval interval) {
		this.interval = interval;
	}
	/**
	 * @return the quantity
	 */
	public long getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	
	
	@Override
	public String toString() {
		return "PositionAddPayload [interval=" + interval + ", quantity=" + quantity + "]";
	}
	
}
