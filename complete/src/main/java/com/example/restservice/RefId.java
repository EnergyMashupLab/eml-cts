package com.example.restservice;

import java.util.concurrent.atomic.AtomicLong;

public class RefId {
	private long value;
	private static final AtomicLong refIdCounter = new AtomicLong();
	
	private static final String template = "RefId %d";
	
	/* 
	 * refIdCounter not used
	 */
	RefId(long refId){
		this.value = refId;
		refIdCounter.set(refId + 1); 	//to avoid inadvertent future collisions
	}
	
	/*
	 * No parameters - uses refIdCounter to determine the value
	 */
	RefId () {
		value = refIdCounter.incrementAndGet();
//		System.err.format("Creating RefId = %d", this.value); System.err.println();
	}
	
	public long getRefId()	{
		return this.value;
	}
	
	public String print() {
		return String.format(template, value);
	}
	
	public String toString()	{
		return String.valueOf(this.value);
	}
}