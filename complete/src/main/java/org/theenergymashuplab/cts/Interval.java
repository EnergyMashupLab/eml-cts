package org.theenergymashuplab.cts;

import java.time.*;

public class Interval {
	public Duration duration = Duration.ZERO;
	public Instant dtStart;
	
	/*
	 *  Construct Interval from java.time.Duration and Instant
	 * 	instant should be constructed and passed in after application of 
	 * 	ZonedDateTime.toInstant()
	 * 
	 *  Elsewhere duration is number of minutes, converted in the constructor
	 */
	
	public Interval(long durationInMinutes, Instant dtStart){
		this.duration = Duration.ofSeconds(60*durationInMinutes);
		this.dtStart = dtStart;
	}

	public Duration getDuration() {
		return duration;
	}

	public void setDuration(Duration duration) {
		this.duration = duration;
	}

	public Instant getDtStart() {
		return dtStart;
	}

	public void setDtStart(Instant dtStart) {
		this.dtStart = dtStart;
	}
	
}
