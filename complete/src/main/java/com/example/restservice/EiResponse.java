package com.example.restservice;

import java.time.*;

public class EiResponse {
	
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