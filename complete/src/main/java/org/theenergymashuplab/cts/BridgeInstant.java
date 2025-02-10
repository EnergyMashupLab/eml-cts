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

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class BridgeInstant {
	private String instantString = new String("");
	private Instant instant;
	private static final DateTimeFormatter INSTANT_INSTRUMENT_FORMATTER =
		DateTimeFormatter.ofPattern("MMddHHmm").withZone(ZoneId.of("Z"));

	public BridgeInstant() {
	}

	public BridgeInstant(Instant javaInstant) {
		instantString = javaInstant.toString();
//		this.instant = javaInstant;
	}

	public Instant asInstant() {
		return Instant.parse(instantString);
	}

	@Override
	public String toString()	{
		return "BridgeInstant" + asInstant().toString();
	}
	
	public String getInstantString() {
		return instantString;
	}

	public void setInstantString(String instantString) {
		this.instantString = instantString;
	}

	public void setInstant(Instant instant){
		this.instant = instant;
		this.instantString = instant.toString();
	}

	//	Code from Parity CtsBridge uses mehods not available here
	
//	/**
//	 * Converts dtStart to a packed long using {@link ASCII}.
//	 * 
//	 * @return packed long
//	 */
//	public long toPackedLong() {
////		return ASCII.packLong(INSTANT_INSTRUMENT_FORMATTER.format(this.instant));
//		return ASCII.packLong(INSTANT_INSTRUMENT_FORMATTER.format(this.asInstant()));
//	}

	/**
	 * Converts {@link #dtStart} to an instrument name.
	 * 
	 * @return string matching the form {@code MMddHHmm}
	 */
	public String toInstrumentName() {
		return INSTANT_INSTRUMENT_FORMATTER.format(this.asInstant());
	}

}
