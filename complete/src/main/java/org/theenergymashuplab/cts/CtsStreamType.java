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
import java.util.*;
import java.time.Instant;

public class CtsStreamType {
    /**
	 * The duration of each StreamInterval
	 */
	private Interval streamIntervalDuration;
	/**
	 * Array of StreamIntervals.
	 */
	private ArrayList<CtsStreamIntervalType> streamIntervals;
	private Instant streamStart;

	public CtsStreamType(){
	}

	public CtsStreamType(Interval streamIntervalDuration, ArrayList<CtsStreamIntervalType> streamIntervals, Instant streamStart) {
		this.streamIntervalDuration = streamIntervalDuration;
		this.streamIntervals = streamIntervals;
		this.streamStart = streamStart;
	}

	public Interval getStreamIntervalDuration() {
		return streamIntervalDuration;
	}
	
	public void setStreamIntervalDuration(Interval streamIntervalDuration) {
		this.streamIntervalDuration = streamIntervalDuration;
	}

	public ArrayList<CtsStreamIntervalType> getStreamIntervals() {
		return streamIntervals;
	}

	public void addStreamInterval(CtsStreamIntervalType streamInterval) {
		this.streamIntervals.add(streamInterval);
	}

	public void setStreamIntervals(ArrayList<CtsStreamIntervalType> streamIntervals) {
		this.streamIntervals = streamIntervals;
	}

	public Instant getStreamStart() {
		return streamStart;
	}

	public void setStreamStart(Instant streamStart) {
		this.streamStart = streamStart;
	}
}
