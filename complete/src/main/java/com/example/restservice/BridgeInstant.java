package com.example.restservice;
/*
 * DIFFERS from java.time.Instant due to JSON serialization issues
 * 
 * Used in Parity Bridge and in Client payloads
 */

import java.time.Duration;
import java.time.Instant;
import java.time.*;
import java.time.Duration;

public class BridgeInstant {
	private String instantString = new String("");
	
	BridgeInstant()	{
	}
	
	BridgeInstant(Instant javaInstant)	{
		instantString = javaInstant.toString();
	}
	
	Instant asInstant()	{
		return Instant.parse(instantString);
	}

	public String getInstantString() {
		return instantString;
	}

	public void setInstantString(String instantString) {
		this.instantString = instantString;
	}

}
