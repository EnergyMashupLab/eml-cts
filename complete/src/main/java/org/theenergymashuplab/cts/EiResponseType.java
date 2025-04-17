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

import java.time.*;

public class EiResponseType {
	public InstantType createdDateTime;
	public RefIdType inResponseTo;
	public long responseCode;
	public String responseDescription;
	public ResponseDetailType responseDetail;

	/*
	 * PROBABLY NO LONGER USED due to id inheritance Three parameters - response code, description string refId
	 */
	// public EiResponseType (long code, String description, long rid) {
	// responseCode = code;
	// responseDescription = description;
	// refId = new RefIdType();
	// }

	/*
	 * Two parameters - response code and description e.g. 200 "OK"
	 */
	public EiResponseType(long code, String description) {
		responseCode = code;
		responseDescription = description;
	}

	/*
	 * No parameters - for JSON serialization
	 */

	public EiResponseType() {
		responseCode = 0;
		responseDescription = "";
	}

	public String toString() {
		return ("EIResponse responseCode " + responseCode + " responseDescription " + responseDescription
				+ " createdDateTime " + createdDateTime.toString());
	}

	public InstantType getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(InstantType createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public RefIdType getInResponseTo() {
		return inResponseTo;
	}

	public void setInResponseTo(RefIdType inResponseTo) {
		this.inResponseTo = inResponseTo;
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

	public ResponseDetailType getResponseDetail() {
		return responseDetail;
	}

	public void setResponseDetail(ResponseDetailType responseDetail) {
		this.responseDetail = responseDetail;
	}
}
