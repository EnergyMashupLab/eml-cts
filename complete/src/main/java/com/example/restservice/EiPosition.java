package com.example.restservice;

/*
 * ArrayList element for the EiR
 * 
 * eplyPosition payload - a list of positions
 */
public class EiPosition {
	private Interval interval;
	private long quantity;
	
	EiPosition()	{
		// empty for JSON serialization
	}
	
	EiPosition(Interval interval, long quantity)	{
		this.interval = interval;
		this.quantity = quantity;
	}
}