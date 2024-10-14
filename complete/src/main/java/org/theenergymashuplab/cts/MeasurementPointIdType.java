package org.theenergymashuplab.cts;


/**
 * @author crossover
 * @version 1.0
 * @created 28-Sep-2024 8:41:41 PM
 */
public class MeasurementPointIdType extends UidType {

	public MeasurementPointIdType(){

	}

	public long value() {
		return this.myUidId;
	}

	@Override
	public String toString() {
		return String.valueOf(this.myUidId);
	}
}
