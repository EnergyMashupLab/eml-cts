package org.theenergymashuplab.cts;


/**
 * WHEN -- Product available at a specific delivery time.
 * @author crossover
 * @version 1.0
 * @created 28-Sep-2024 8:41:41 PM
 */
public class InstrumentType extends ProductType {

	public InstantType instrumentStart;

	public InstrumentType(){

	}

	public InstrumentType(InstantType instrumentStart) {
		this.instrumentStart = instrumentStart;
	}

	public InstantType getInstrumentStart() {
		return instrumentStart;
	}

	public void setInstrumentStart(InstantType instrumentStart) {
		this.instrumentStart = instrumentStart;
	}

	@Override
	public String toString() {
		return "InstrumentType{" +
				"instrumentStart=" + instrumentStart +
				", duration=" + duration +
				", quantityScale=" + quantityScale +
				", warrantId=" + warrantId +
				", resourceAttributes='" + resourceAttributes + '\'' +
				", resourceDescription='" + resourceDescription + '\'' +
				", resourceDesignator=" + resourceDesignator +
				", resourceUnit='" + resourceUnit + '\'' +
				'}';
	}
}
