package org.theenergymashuplab.cts;


/**
 * WHAT --  is traded
 * @author crossover
 * @version 1.0
 * @created 28-Sep-2024 8:41:41 PM
 */
public class ResourceType {

	public String resourceAttributes;
	public String resourceDescription;
	public ResourceDesignatorType resourceDesignator;
	public String resourceUnit;

	public ResourceType(){

	}

	public ResourceType(String resourceAttributes, String resourceDescription, ResourceDesignatorType resourceDesignator, String resourceUnit){
		this.resourceAttributes = resourceAttributes;
		this.resourceDescription = resourceDescription;
		this.resourceDesignator = resourceDesignator;
		this.resourceUnit = resourceUnit;
	}

	public String getResourceAttributes() { return resourceAttributes; }

	public void setResourceAttributes(String resourceAttributes) { this.resourceAttributes = resourceAttributes; }

	public String getResourceDescription() { return resourceDescription; }

	public void setResourceDescription(String resourceDescription) { this.resourceDescription = resourceDescription; }

	public ResourceDesignatorType getResourceDesignator() { return resourceDesignator; }

	public void ResourceDesignator(ResourceDesignatorType resourceDesignator) { this.resourceDesignator = resourceDesignator; }

	public String getResourceUnit() { return resourceUnit; }

	public void setResourceUnit(String resourceUnit) { this.resourceUnit = resourceUnit; }
}
