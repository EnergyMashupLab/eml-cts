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
	}
	
	public long getRefId()	{
		return this.value;
	}
	
	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}

	public static AtomicLong getRefidcounter() {
		return refIdCounter;
	}

	public static String getTemplate() {
		return template;
	}

	public String print() {
		return String.format(template, value);
	}
	
	public String toString()	{
		return String.valueOf(this.value);
	}
}
