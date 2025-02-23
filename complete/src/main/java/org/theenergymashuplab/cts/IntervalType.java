package org.theenergymashuplab.cts;


/**
 * @author crossover
 * @version 1.0
 * @created 28-Sep-2024 8:41:41 PM
 */
public class IntervalType {

	public InstantType dtStart;
	// TODO uncomment this after implementation of DurationType
	//public DurationType duration = DurationType.ZERO;

	public IntervalType(){

	}

	public IntervalType(InstantType dtStart) {
		this.dtStart = dtStart;
	}

	public InstantType getDtStart() {
		return dtStart;
	}

	public void setDtStart(InstantType dtStart) {
		this.dtStart = dtStart;
	}

	@Override
	public String toString() {
		return "IntervalType{" +
				"dtStart=" + dtStart +
				'}';
	}
}
