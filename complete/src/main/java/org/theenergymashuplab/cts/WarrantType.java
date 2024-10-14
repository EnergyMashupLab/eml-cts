package org.theenergymashuplab.cts;


/**
 * Concrete Warrants inherit from this abstract base class.
 * @author crossover
 * @version 1.0
 * @created 28-Sep-2024 8:41:43 PM
 */
public abstract class WarrantType {

	public WarrantIdType warrantId;
	public String warrantSummary;

	public WarrantType(){

	}

	public WarrantIdType getWarrantId() {
		return warrantId;
	}

	public void setWarrantId(WarrantIdType warrantId) {
		this.warrantId = warrantId;
	}

	public String getWarrantSummary() {
		return warrantSummary;
	}

	public void setWarrantSummary(String warrantSummary) {
		this.warrantSummary = warrantSummary;
	}

	public WarrantType(WarrantIdType warrantId, String warrantSummary) {
		this.warrantId = warrantId;
		this.warrantSummary = warrantSummary;
	}

	@Override
	public String toString() {
		return "WarrantType{" +
				"warrantId=" + warrantId +
				", warrantSummary='" + warrantSummary + '\'' +
				'}';
	}
}
