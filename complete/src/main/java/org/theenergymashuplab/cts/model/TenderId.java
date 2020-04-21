package org.theenergymashuplab.cts.model;

import java.util.concurrent.atomic.AtomicLong;

public class TenderId {
	private static final String template = "tenderId %d";
	private static final AtomicLong tenderIdCounter = new AtomicLong();
	private long myTenderId;
	
	TenderId(long tenderId){
		/*
		 * If parameter is zero, create a TenderId with value zero.
		 * For creating zero values in initilizers in EiTransaction, EiCancelTender, etc
		 */
		this.myTenderId = tenderId;
		if (tenderId != 0) {
			tenderIdCounter.set(tenderId + 1); 	//to avoid future collisions
		}
	}
	
	TenderId(){
		myTenderId = tenderIdCounter.incrementAndGet();
	}
	
	public static String getTemplate() {
		return template;
	}

	public static AtomicLong getTenderidcounter() {
		return tenderIdCounter;
	}

	public void setTenderId(long tenderId) {
		this.tenderId = tenderId;
	}
	
	/* synonym for getTenderId() for integration of types - TODO*/
	public long value()    {
		return this.myTenderId;
	}

	public long getTenderId()	{
		return this.tenderId;
	}
	
	public String print() {
		return String.format(template, tenderId);
	}
	
	public String toString()	{
		return String.valueOf(this.tenderId);
	}
	
}
