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
import org.theenergymashuplab.cts.EiCanceledResponseType;
import org.theenergymashuplab.cts.EiResponse;
import org.theenergymashuplab.cts.RefIdType;

public class EICanceledTenderPayload {
	private final ActorIdType partyId;
	private final ActorIdType counterPartyId;
	private EiResponse response;
	private EiCanceledResponseType eiCanceledResponse;
	private RefIdType inResponseTo;
//	public ArrayofResponses responses; NOT USED
//	refId is in the EiResponse

	public EICanceledTenderPayload(ActorIdType partyId, ActorIdType counterPartyId, EiResponse response,
			EiCanceledResponseType eiCanceledResponse, RefIdType inResponseTo) {
		this.partyId = partyId;
		this.counterPartyId = counterPartyId;
		this.response = response;
		this.eiCanceledResponse = eiCanceledResponse;
		this.inResponseTo = inResponseTo;
	}

	//Default constructor for JSON serialization
	public EICanceledTenderPayload()	{
		this.partyId = new ActorIdType();
		this.counterPartyId = new ActorIdType();
		this.response = new EiResponse(200, "OK");
	}

	public void print() {
		System.err.println(this);
	}
	
	@Override
	public String toString() {
		return "EICanceledTenderPayload [partyId=" + partyId + ", counterPartyId=" + counterPartyId + ", response="
				+ response + ", eiCanceledResponse=" + eiCanceledResponse + ", inResponseTo=" + inResponseTo + "]";
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

	public EiCanceledResponseType getEiCanceledResponse() {
		return eiCanceledResponse;
	}

	public void setEiCanceledResponse(EiCanceledResponseType eiCanceledResponse) {
		this.eiCanceledResponse = eiCanceledResponse;
	}

	public RefIdType getInResponseTo() {
		return inResponseTo;
	}

	public void setInResponseTo(RefIdType inResponseTo) {
		this.inResponseTo = inResponseTo;
	}

}
