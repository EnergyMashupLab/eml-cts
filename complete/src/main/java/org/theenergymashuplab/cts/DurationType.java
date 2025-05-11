/*
 * Copyright 2019-2025 The Energy Mashup Lab
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.theenergymashuplab.cts;

/**
 * Polymorphic representation of a temporal duration
 * 
 * Representations include (1) ISO8601 string (2) Java-style duration (3) WS-Calendar/iCalendar duration representation
 * as string
 * 
 * @author crossover
 * @version 1.0
 * @created 28-Sep-2024 8:41:39 PM
 */
public class DurationType {

	public String duration;

	public DurationType() {

	}

	public DurationType(String duration) {
		this.duration = duration;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		return "DurationType{" + "duration='" + duration + '\'' + '}';
	}
}
