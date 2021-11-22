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

import java.io.Serializable;

import org.theenergymashuplab.cts.ActorIdType;
import org.theenergymashuplab.cts.EiTenderRabbit;
import org.theenergymashuplab.cts.RefIdType;

public class EiCreateTenderPayloadRabbit implements Serializable{
	private ActorIdType counterPartyId;
	private ActorIdType partyId;
	private RefIdType requestId;
	private EiTenderRabbit tender;
	
	/*
	@JsonIgnore
	private final Random rand = new Random();
	 */
	
	/*
	 * Default constructor for JSON deserialization.
	 * TO DO change to zero Id values in ActorId and RefId constructors
	 */
	public EiCreateTenderPayloadRabbit()	{		
		this.counterPartyId = new ActorIdType();
		this.partyId = new ActorIdType();
		this.requestId = new RefIdType();
	}
	

	/* 
	 * Parallel for EiCreateTransaction, EiCreateTender:
	 * 		pass in a completed Tender/Transaction which includes through its Tender interval, quantity, price,
	 * 		or for EiCancelTender only the TenderId.
	 * 
	 * Add party, counterParty, and requestId for the message payload.
	 */

	public EiCreateTenderPayloadRabbit(EiTenderRabbit tender, ActorIdType party, ActorIdType counterParty, RefIdType rid) {

		this.tender = tender;
		this.partyId = party;
		this.counterPartyId = counterParty;
		this.requestId = rid;
		
//		System.err.println("EiCreateTender Constructor before this.print()");
//		this.print();
	}

	@Override
	public String toString() {
		return ("EiCreateTenderPayload party " +
				partyId.value() +
				" counterParty " +
				counterPartyId.value() +
				" requestId " +
				requestId.toString() +
				" " +
				tender.toString());
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

	public EiTenderRabbit getTender() {
		return tender;
	}

	public void setTender(EiTenderRabbit tender) {
		this.tender = tender;
	}
}
