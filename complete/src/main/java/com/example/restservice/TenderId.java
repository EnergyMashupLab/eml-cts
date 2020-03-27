package com.example.restservice;

import java.util.concurrent.atomic.AtomicLong;

public class TenderId {
	private static final String template = "tenderId %d";
	private static final AtomicLong tenderIdCounter = new AtomicLong();
	private long tenderId;
	
	TenderId(long tenderId){
		/*
		 * If parameter is zero, create a TenderId with value zero.
		 * For creating zero values in initilizers in EiTransaction, EiCancelTender, etc
		 */
		this.tenderId = tenderId;
		if (tenderId != 0) {
			tenderIdCounter.set(tenderId + 1); 	//to avoid future collisions
		}
	}
	
	TenderId(){
		tenderId = tenderIdCounter.incrementAndGet();
		
//		System.err.format("Creating TenderId = %d", this.tenderId);
//		System.err.println();
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
	
	public long getTenderId()	{
		return this.tenderId;
	}
	
	public String print() {
		return String.format(template, tenderId);
	}
}
