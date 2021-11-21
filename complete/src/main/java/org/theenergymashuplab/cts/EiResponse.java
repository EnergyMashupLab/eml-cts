/*
 * Copyright 2019-2020 The Energy Mashup Lab
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.theenergymashuplab.cts;

import java.io.Serializable;
import java.time.*;

public class EiResponse implements Serializable{
	
	public long responseCode;
	public String responseDescription;
	private RefIdType refId = new RefIdType();
	private Instant createdDateTime = Instant.now();
	
	/*
	 * PROBABLY NO LONGER USED due to id inheritance
	 * Three parameters - response code, description string
	 *	 refId
	 */
	public EiResponse (long code, String description, long rid)	{
		responseCode = code;
		responseDescription = description;
		refId = new RefIdType();
	}
	
	/*
	 * Two parameters - response code and description e.g. 200 "OK"
	 */
	 public EiResponse (long code, String description)	{
		responseCode = code;
		responseDescription = description;
	}
	 
	 /*
	  * No parameters - for JSON serialization
	  */
	 
	 public EiResponse ()	{
		responseCode = 0;
		responseDescription = "";
	}
	 
	 public String toString()	{		
			return ("EIResponse responseCode " + responseCode + 
					" responseDescription " + responseDescription +
					" refId " + refId.toString() +
					" createdDateTime " + createdDateTime.toString());
}

	public long getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(long responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseDescription() {
		return responseDescription;
	}

	public void setResponseDescription(String responseDescription) {
		this.responseDescription = responseDescription;
	}

	public RefIdType getRefId() {
		return refId;
	}

	public void setRefId(RefIdType refId) {
		this.refId = refId;
	}

	public Instant getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(Instant createdDateTime) {
		this.createdDateTime = createdDateTime;
	}
}
