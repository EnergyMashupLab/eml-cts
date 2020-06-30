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
import java.util.Random;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EiCreateTransactionPayload {
	private ActorIdType counterPartyId;
	private ActorIdType partyId;
	private RefIdType requestId;
	private EiTransaction transaction;
	
	// Default initializer for JSON serialization
	public EiCreateTransactionPayload() {
	}

	public EiCreateTransactionPayload(EiTransaction eiTransaction)	{
		this.counterPartyId = new ActorIdType();
		this.partyId = new ActorIdType();
		this.requestId = new RefIdType();
		this.transaction = eiTransaction;
	}

	/* 
	 * Parallel for EiCreateTransactionPayload, EiCreateTender:
	 * 		pass in a completed Tender/Transaction which includes through its Tender interval, quantity, price,
	 * 		or for EiCancelTender only the TenderId.
	 * 
	 * Add party, counterParty, and requestId for the message payload.
	 */
	public EiCreateTransactionPayload(EiTransaction transaction, ActorIdType party,
				ActorIdType counterParty) {
		this.transaction = transaction;
		this.partyId = party;
		this.counterPartyId = counterParty;
		this.requestId = new RefIdType();
	}
	
	@Override
	public String toString() {
//		String printStringFormat = 
//			"EiCreateTransactionPayload transactionId %d partyId %d counterPartyId %d requestId %d  dtStart %s";
		
		return ("EiCreateTransactionPayload transactionId " + transaction.getTransactionId().value() +
				" partyid " + partyId.toString() +
				" counterPartyid " + counterPartyId.toString() +			
				" requestId " + requestId.value() + " TenderId " +
				transaction.getTender().getTenderId().value() +
				" quantity " + transaction.getTender().getQuantity() +
				" price " + transaction.getTender().getPrice());
	}

	public ActorIdType getCounterPartyId() {
		return counterPartyId;
	}

	public void setCounterPartyId(ActorIdType counterPartyId) {
		this.counterPartyId = counterPartyId;
	}

	public ActorIdType getPartyId() {
		return partyId;
	}

	public void setPartyId(ActorIdType partyId) {
		this.partyId = partyId;
	}

	public RefIdType getRequestId() {
		return requestId;
	}

	public void setRequestId(RefIdType requestId) {
		this.requestId = requestId;
	}

	public EiTransaction getTransaction() {
		return transaction;
	}

	public void setTransaction(EiTransaction transaction) {
		this.transaction = transaction;
	}
	
}
