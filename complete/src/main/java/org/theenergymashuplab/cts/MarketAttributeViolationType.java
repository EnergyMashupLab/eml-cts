package org.theenergymashuplab.cts;


/**
 * @author crossover
 * @version 1.0
 * @created 28-Sep-2024 8:41:41 PM
 */
public class MarketAttributeViolationType {

	public String attributeViolated;
	public String responseDescription;

	public MarketAttributeViolationType(){

	}

	public MarketAttributeViolationType(String attributeViolated, String responseDescription) {
		this.attributeViolated = attributeViolated;
		this.responseDescription = responseDescription;
	}

	public String getAttributeViolated() {
		return attributeViolated;
	}

	public void setAttributeViolated(String attributeViolated) {
		this.attributeViolated = attributeViolated;
	}

	public String getResponseDescription() {
		return responseDescription;
	}

	public void setResponseDescription(String responseDescription) {
		this.responseDescription = responseDescription;
	}

	@Override
	public String toString() {
		return "MarketAttributeViolationType{" +
				"attributeViolated='" + attributeViolated + '\'' +
				", responseDescription='" + responseDescription + '\'' +
				'}';
	}
}
