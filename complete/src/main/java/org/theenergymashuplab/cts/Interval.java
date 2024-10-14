/*
 * Copyright 2019-2020 The Energy Mashup Lab
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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

	public Interval(Duration duration, Instant dtStart) {
		this.duration = duration;
		this.dtStart = dtStart;
	}

	public Interval(long durationInMinutes, Instant dtStart){
		this.duration = Duration.ofSeconds(60*durationInMinutes);
		this.dtStart = dtStart;
	}
	
	@Override
	public String toString()	{
		return "Interval dtStart" + dtStart.toString() + " duration " + duration.toString();
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
