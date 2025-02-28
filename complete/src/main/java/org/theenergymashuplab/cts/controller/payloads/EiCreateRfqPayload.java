package org.theenergymashuplab.cts.controller.payloads;

import org.theenergymashuplab.cts.ActorIdType;
import org.theenergymashuplab.cts.EiRfqType;
import org.theenergymashuplab.cts.RefIdType;

/**
 * @author crossover
 * @version 1.0
 * @created 28-Sep-2024 8:41:39 PM
 */
public class EiCreateRfqPayload {

	public ActorIdType counterPartyId;
	public ActorIdType partyId;
	public RefIdType requestId;
	public boolean requestPrivate;
	public boolean requestPublication;
	public EiRfqType rfq;

	public EiCreateRfqPayload(){

	}

	public EiCreateRfqPayload(ActorIdType counterPartyId, ActorIdType partyId, RefIdType requestId,
			boolean requestPrivate, boolean requestPublication, EiRfqType rfq){
		this.counterPartyId = counterPartyId;
		this.partyId = partyId;
		this.requestId = requestId;
		this.requestPrivate = requestPrivate;
		this.requestPublication = requestPublication;
		this.rfq = rfq;
	}

	public ActorIdType getCounterPartyId(){
		return this.counterPartyId;
	}

	public void setCounterPartyId(ActorIdType counterPartyId){
		this.counterPartyId = counterPartyId;
	}

	public ActorIdType getPartyId(){
		return this.partyId;
	}

	public void setPartyId(ActorIdType partyId){
		this.partyId = partyId;
	}

	public RefIdType getRequestId(){
		return this.requestId;
	}

	public void setRequestId(RefIdType requestId){
		this.requestId = requestId;
	}

	public boolean getRequestPrivate(){
		return this.requestPrivate;
	}

	public void setRequestPrivate(boolean requestPrivate){
		this.requestPrivate = requestPrivate;
	}

	public boolean getRequestPublication(){
		return this.requestPublication;
	}

	public void setRequestPublication(boolean requestPublication){
		this.requestPublication = requestPublication;
	}

	public EiRfqType getRfq(){
		return this.rfq;
	}

	public void setRfq(EiRfqType rfq){
		this.rfq = rfq;
	}

	@Override
	public String toString(){
		return "EiCreateRfqPayload: [" +
				"counterPartyId=" + this.counterPartyId.toString() +
				"partyId=" + this.partyId.toString() +
				"requestId=" + this.requestId.toString() +
				"requestPrivate=" + this.requestPrivate +
				"requestPublication=" + this.requestPublication +
				"rfq=" + this.rfq + "]";
	}
}
