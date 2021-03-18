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
import org.theenergymashuplab.cts.UidType;
import org.theenergymashuplab.cts.TransactionIdType;

public class EiCreatedTransactionPayload {
	private TransactionIdType transactionId;
	private ActorIdType partyId;
	private ActorIdType counterPartyId;
	public EiResponse response;
//	public ArrayofResponses responses; NOT USED YET
	private final UidType uId = new UidType();

	// Default initializer for JSON serialization
	public EiCreatedTransactionPayload() {
	}
	
	public TransactionIdType getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(TransactionIdType transactionId) {
		this.transactionId = transactionId;
	}

	public void setPartyId(ActorIdType partyId) {
		this.partyId = partyId;
	}

	public void setCounterPartyId(ActorIdType counterPartyId) {
		this.counterPartyId = counterPartyId;
	}

	public EiCreatedTransactionPayload(
			TransactionIdType transactionId,
			ActorIdType partyId,
			ActorIdType counterPartyId,
			EiResponse response) {

		this.transactionId = transactionId;
		this.partyId = partyId;
		this.counterPartyId = counterPartyId;
		this.response = response;
	}

	public long getId() {
		return transactionId.value();
	}

	public void print() {		
		System.err.println(
				"EiCreatedTransactionPayload transactionId " +
				transactionId.toString() +
				" partyId " + partyId.value() +
				" counterPartyId " + counterPartyId.value() +
				" uId " + uId.toString());
	}
	
	public String toString()	{
		return ("EiCreatedTransactionPayload transactionId " +
				transactionId.toString() +
				" partyId " + partyId.toString() +
				" counterPartyId " + counterPartyId.toString() +
				" uId " + uId.toString());
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

	public UidType getRefId() {
		return uId;
	}
}
