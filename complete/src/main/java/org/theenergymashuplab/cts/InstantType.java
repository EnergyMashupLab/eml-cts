package org.theenergymashuplab.cts;


/**
 * Polymorphic representation of an Instant in time.
 * 
 * Representations incude
 * (1) ISO8601 string
 * (2) Java-style <Seconds from the epoch, nanoseconds as natural number>
 * @author crossover
 * @version 1.0
 * @created 28-Sep-2024 8:41:41 PM
 */
public class InstantType {

	public String time;

	public InstantType(){

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
		return "InstantType{" +
				"time='" + time + '\'' +
				'}';
	}
}
