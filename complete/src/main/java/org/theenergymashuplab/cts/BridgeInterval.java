package org.theenergymashuplab.cts;

//package com.paritytrading.parity.client;

/*
 * DIFFERS from CTS Interval due to JSON serialization issues
 * 
 * Instant works fine Duration does not
 * 
 * duration attribute is integral minutes, rather than JDK8 java.time.Duration
 */

/*
 * This class provides a workaround Jackson serialization problem.
 * 
 * Investigate SpringBoot - it doesn't have this issue, unravel imports
 *
 * Inside CtsBridge/Client use this specialized interval that maintains string instant and duration
 * in minutes.
 *
 * Setters and Getters create java.time Instant and Duration for other integration.
 */

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

//import com.paritytrading.foundation.ASCII;

public class BridgeInterval {
	// only for JSON serialization over sockets between CTS and Parity
	// - see note on Spring Boot

	private long durationInMinutes = 0; // integral minutes
	private BridgeInstant dtStart;
	private static final DateTimeFormatter INSTANT_INSTRUMENT_FORMATTER =
			DateTimeFormatter.ofPattern("MMddHHmm").withZone(ZoneId.systemDefault());

	// TODO compare with CTS implementation; Jackson can't serialize java.time.Duration. First add
	// explicit import java.time.Duration

	/*
	 * Construct Interval from java.time.Duration and Instant instant should be constructed and
	 * passed in after application of ZonedDateTime.toInstant() elsewhere duration is number of
	 * minutes, converted in the constructor
	 */

	BridgeInterval() {
		dtStart = new BridgeInstant(Instant.now()); // a reasonable default
	}

	BridgeInterval(long durationInMinutes, Instant dtStart) {
		this.durationInMinutes = durationInMinutes;
		this.dtStart = new BridgeInstant(dtStart);
	}

	BridgeInterval(Interval ctsInterval) {
		this.durationInMinutes = ctsInterval.getDuration().toMinutes();
		this.dtStart = new BridgeInstant(ctsInterval.dtStart);
	}

	Interval asInterval() {
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

//	/**
//	 * Converts dtStart to a packed long using {@link ASCII}.
//	 * 
//	 * @return packed long
//	 */
//	public long toPackedLong() {
//		return ASCII.packLong(INSTANT_INSTRUMENT_FORMATTER.format(this.dtStart.asInstant()));
//	}
//
	/**
	 * Converts {@link #dtStart} to an instrument name.
	 * 
	 * @return string matching the form {@code MMddHHmm}
	 */
	public String toInstrumentName() {
		return INSTANT_INSTRUMENT_FORMATTER.format(this.dtStart.asInstant());
	}
}




//	/*
//	 * DIFFERS from CTS Interval due to JSON serialization issues
//	 * 
//	 * 	Uses BridgeInstant and duraction in minutes, rather than JDK8 java.time.Duration
//	 */
//
//	/*
//	 * This class provides a workaround to a Jackson serialization problem.
//	 * 
//	 *
//	 *		Setters and Getters create java.time Instant and Duration for integration.
//	 */
//
//import java.time.Duration;
//import java.time.Instant;
//import java.time.*;
//import java.time.Duration;
//
//public class BridgeInterval {
//	// only for JSON serialization over sockets and to/from Client
//	private long durationInMinutes = 0;	//integral minutes
//	private BridgeInstant dtStart;
//	
//	// TODO compare with CTS implementation; Jackson can't serialize java.time.Duration.
//	
//	/*
//	 *  Construct Interval from java.time.Duration and Instant
//	 * 	instant should be constructed and passed in after application of ZonedDateTime.toInstant() elsewhere
//	 *  duration is number of minutes, converted in the constructor
//	 */
//	
//	BridgeInterval()	{
//		dtStart = new BridgeInstant(Instant.now());	// a reasonable default
//	}
//	
//	BridgeInterval(long durationInMinutes, Instant dtStart){
//		this.durationInMinutes = durationInMinutes;
//		this.dtStart = new BridgeInstant(dtStart);
//	}
//	
//	BridgeInterval(Interval ctsInterval)	{
//		// takes Interval and constructs a BridgeInterval for serialization
//		this.durationInMinutes = ctsInterval.getDuration().toMinutes();
//		this.dtStart = new BridgeInstant(ctsInterval.dtStart);
//	}
//	
//	Interval asInterval()	{
//		return new Interval(durationInMinutes, dtStart.asInstant());
//	}
//
//	public long getDurationInMinutes() {
//		return durationInMinutes;
//	}
//
//	public void setDurationInMinutes(long durationInMinutes) {
//		this.durationInMinutes = durationInMinutes;
//	}
//
//	public BridgeInstant getDtStart() {
//		return dtStart;
//	}
//
//	public void setDtStart(BridgeInstant dtStart) {
//		this.dtStart = dtStart;
//	}
//
//	
//}
