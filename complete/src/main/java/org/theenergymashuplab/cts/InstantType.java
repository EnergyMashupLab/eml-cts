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
 * Polymorphic representation of an Instant in time.
 * 
 * Representations incude (1) ISO8601 string (2) Java-style <Seconds from the epoch, nanoseconds as natural number>
 * 
 * @author crossover
 * @version 1.0
 * @created 28-Sep-2024 8:41:41 PM
 */
public class InstantType {

	public String time;

	public InstantType() {

	}

	public InstantType(String time) {
		this.time = time;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "InstantType{" + "time='" + time + '\'' + '}';
	}
}
