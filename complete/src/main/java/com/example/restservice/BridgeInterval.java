package com.example.restservice;

	/*
	 * DIFFERS from CTS Interval due to JSON serialization issues
	 * 
	 * 	Uses BridgeInstant and duraction in minutes, rather than JDK8 java.time.Duration
	 */

	/*
	 * This class provides a workaround to a Jackson serialization problem.
	 * 
	 *
	 *		Setters and Getters create java.time Instant and Duration for integration.
	 */

import java.time.Duration;
import java.time.Instant;
import java.time.*;
import java.time.Duration;

public class BridgeInterval {
	// only for JSON serialization over sockets and to/from Client
	private long durationInMinutes = 0;	//integral minutes
	private BridgeInstant dtStart;
	
	// TODO compare with CTS implementation; Jackson can't serialize java.time.Duration. First add
	// explicit import java.time.Duration
	
	/*
	 *  Construct Interval from java.time.Duration and Instant
	 * 	instant should be constructed and passed in after application of ZonedDateTime.toInstant() elsewhere
	 *  duration is number of minutes, converted in the constructor
	 */
	
	BridgeInterval()	{
		dtStart = new BridgeInstant(Instant.now());	// a reasonable default
	}
	
	BridgeInterval(long durationInMinutes, Instant dtStart){
		this.durationInMinutes = durationInMinutes;
		this.dtStart = new BridgeInstant(dtStart);
	}
	
	BridgeInterval(Interval ctsInterval)	{
		// takes Interval and constructs a BridgeInterval for serialization
		this.durationInMinutes = ctsInterval.getDuration().toMinutes();
		this.dtStart = new BridgeInstant(ctsInterval.dtStart);
	}
	
	Interval asInterval()	{
		return new Interval(durationInMinutes, dtStart.asInstant());
	}

	public long getDurationInMinutes() {
		return durationInMinutes;
	}

	public void setDurationInMinutes(long durationInMinutes) {
		this.durationInMinutes = durationInMinutes;
	}

	public BridgeInstant getDtStart() {
		return dtStart;
	}

	public void setDtStart(BridgeInstant dtStart) {
		this.dtStart = dtStart;
	}

	
}
