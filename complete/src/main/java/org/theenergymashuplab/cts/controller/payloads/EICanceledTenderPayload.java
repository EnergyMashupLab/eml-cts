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

package org.theenergymashuplab.cts.controller.payloads;

import org.theenergymashuplab.cts.ActorIdType;
import org.theenergymashuplab.cts.EiResponse;
import org.theenergymashuplab.cts.dto.EiCanceledResponse;

public class EICanceledTenderPayload {
	private final ActorIdType partyId;
	private final ActorIdType counterPartyId;
	public EiResponse response;
	private EiCanceledResponse canceledResponse;
//	public ArrayofResponses responses; NOT USED
//	refId is in the EiResponse

	
	public EICanceledTenderPayload(ActorIdType partyId, ActorIdType counterPartyId, EiResponse response, EiCanceledResponse canceledResponse) {
		this.partyId = partyId;
		this.counterPartyId = counterPartyId;
		this.response = response;
		this.canceledResponse = canceledResponse;
	}

	//Default constructor for JSON serialization
	public EICanceledTenderPayload()	{
		this.partyId = new ActorIdType();
		this.counterPartyId = new ActorIdType();
		this.response = new EiResponse(200, "OK");
		this.canceledResponse = new EiCanceledResponse();
	}
	
	
	public void print() {
		String printStringFormat = 
				"EICanceledTenderPayload  partyId %d counterPartyId %d refId %d ";
		
		System.err.println(
				String.format(printStringFormat, 
				partyId.value(), 
				counterPartyId.value(), 
				response.getRefId()));
	}
	
	public String toString()	{
		return "EICanceledTenderPayload Soure RefId " +
				"PENDING" +
				" partyId " + partyId.toString() +
				" counterPartyId " + counterPartyId.toString() +
				" response " + response.toString() +
				" canceledResponse " + canceledResponse.toString();
	}

	public EiResponse getResponse() {
		return response;
	}

	public void setResponse(EiResponse response) {
		this.response = response;
	}

	public ActorIdType getPartyId() {
		return partyId;
	}

	public ActorIdType getCounterPartyId() {
		return counterPartyId;
	}
	
	

}
