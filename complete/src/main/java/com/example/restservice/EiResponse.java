package com.example.restservice;

import java.time.*;

public class EiResponse {
	
	public long responseCode;
	public String responseDescription;
	private RefId refId = new RefId();
	private Instant createdDateTime = Instant.now();
	
	/*
	 * Three parameters - response code, description string, what will become a refId number
	 */
	public EiResponse (long code, String description, long rid)	{
		responseCode = code;
		responseDescription = description;
		refId = new RefId(rid);
	}
	
	/*
	 * Two parameters - response code and description e.g. 200 "OK"
	 */
	 public EiResponse (long code, String description)	{
		responseCode = code;
		responseDescription = description;
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

	public RefId getRefId() {
		return refId;
	}

	public void setRefId(RefId refId) {
		this.refId = refId;
	}

	public Instant getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(Instant createdDateTime) {
		this.createdDateTime = createdDateTime;
	}
}