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

public class EiResponseType {
	public InstantType createdDateTime;
	public RefIdType inResponseTo;
	public long responseCode;
	public String responseDescription;
	public ResponseDetailType responseDetail;

	public EiResponseType() {
		this.createdDateTime = new InstantType();
		this.inResponseTo = new RefIdType();
		this.responseCode = 0;
		this.responseDescription = "";
		this.responseDetail = ResponseDetailType.UNSPECIFIED;
	}

	public EiResponseType(InstantType createdDateTime, RefIdType inResponseTo, long responseCode,
			String responseDescription, ResponseDetailType responseDetail) {
		this.createdDateTime = createdDateTime;
		this.inResponseTo = inResponseTo;
		this.responseCode = responseCode;
		this.responseDescription = responseDescription;
		this.responseDetail = responseDetail;
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

	@Override
	public String toString() {
		return "EiResponseType{" +
				"createdDateTime=" + createdDateTime +
				", inResponseTo=" + inResponseTo +
				", responseCode=" + responseCode +
				", responseDescription='" + responseDescription + '\'' +
				", responseDetail=" + responseDetail +
				'}';
	}

}
