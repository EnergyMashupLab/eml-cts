package org.theenergymashuplab.cts;


/**
 * @author crossover
 * @version 1.0
 * @created 28-Sep-2024 8:41:41 PM
 */
public class NameValueString {

	public String attributeName;
	public String attributeValueString;

	public NameValueString(){

	}

	public NameValueString(String attributeName, String attributeValueString){
		this.attributeName = attributeName;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public String getAttributeValueString() {
		return attributeValueString;
	}

	public void setAttributeValueString(String attributeValueString) {
		this.attributeValueString = attributeValueString;
	}

	@Override
	public String toString() {
		return "NameValueString [attributeName=" + attributeName + ", attributeValueString=" + attributeValueString + "]";
	}
}
