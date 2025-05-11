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
 * WHAT -- is traded
 * 
 * @author crossover
 * @version 1.0
 * @created 28-Sep-2024 8:41:41 PM
 */
public class ResourceType {

	public String resourceAttributes;
	public String resourceDescription;
	public ResourceDesignatorType resourceDesignator;
	public String resourceUnit;

	public ResourceType() {

	}

	public ResourceType(String resourceAttributes, String resourceDescription,
			ResourceDesignatorType resourceDesignator, String resourceUnit) {
		this.resourceAttributes = resourceAttributes;
		this.resourceDescription = resourceDescription;
		this.resourceDesignator = resourceDesignator;
		this.resourceUnit = resourceUnit;
	}

	public String getResourceAttributes() {
		return resourceAttributes;
	}

	public void setResourceAttributes(String resourceAttributes) {
		this.resourceAttributes = resourceAttributes;
	}

	public String getResourceDescription() {
		return resourceDescription;
	}

	public void setResourceDescription(String resourceDescription) {
		this.resourceDescription = resourceDescription;
	}

	public ResourceDesignatorType getResourceDesignator() {
		return resourceDesignator;
	}

	public void ResourceDesignator(ResourceDesignatorType resourceDesignator) {
		this.resourceDesignator = resourceDesignator;
	}

	public String getResourceUnit() {
		return resourceUnit;
	}

	public void setResourceUnit(String resourceUnit) {
		this.resourceUnit = resourceUnit;
	}

	@Override
	public String toString() {
		return "ResourceType {" + " resourceAttributes = " + resourceAttributes + ", resourceDescription = "
				+ resourceDescription + ", resourceDesignator = " + resourceDesignator + ", resourceUnit = "
				+ resourceUnit + " }";
	}
}
