package org.theenergymashuplab.cts.controller.payloads;

import org.theenergymashuplab.cts.ActorIdType;
import org.theenergymashuplab.cts.EiCanceledResponseType;
import org.theenergymashuplab.cts.EiResponseType;
import org.theenergymashuplab.cts.RefIdType;

/**
 * @author crossover
 * @version 1.0
 * @created 28-Sep-2024 8:41:39 PM
 */
public class EICanceledQuotePayload {

	public ActorIdType counterPartyId;
	public EiCanceledResponseType eiCanceledResponse;
	public EiResponseType eiResponse;
	public RefIdType inResponseTo;
	public ActorIdType partyId;

	public EICanceledQuotePayload(ActorIdType counterPartyId, EiCanceledResponseType eiCanceledResponse, EiResponseType eiResponse, RefIdType inResponseTo, ActorIdType partyId){
		this.counterPartyId = counterPartyId;
		this.eiCanceledResponse = eiCanceledResponse;
		this.eiResponse = eiResponse;
		this.inResponseTo = inResponseTo;
		this.partyId = partyId;
	}

	
	public ActorIdType getCounterPartyId(){
		return this.counterPartyId;
	}

	public void setCounterPartyId(ActorIdType counterPartyId){
		this.counterPartyId = counterPartyId;
	}

	public EiCanceledResponseType getEiCanceledResponse(){
		return this.eiCanceledResponse;
	}

	public void setEiCanceledResponse(EiCanceledResponseType eiCanceledResponse){
		this.eiCanceledResponse = eiCanceledResponse;
	}
	
	public EiResponseType getEiResponse(){
		return this.eiResponse;
	}

	public void setEiResponse(EiResponseType eiResponse){
		this.eiResponse = eiResponse;
	}

	public RefIdType getInResponseTo(){
		return this.inResponseTo;
	}

	public void setInResponseTo(RefIdType inResponseTo){
		this.inResponseTo = inResponseTo;
	}

	public ActorIdType getPartyId(){
		return this.partyId;
	}

	public void setPartyId(ActorIdType partyId){
		this.partyId = partyId;
	}

	@Override
	public String toString(){
		return "EICanceledQuotePayload [" +
				"counterPartyId=" + this.counterPartyId.toString() +
				", eiCanceledResponse=" + this.eiCanceledResponse.toString() +
				", eiResponse=" + this.eiResponse.toString() +
				", inResponseTo=" + this.inResponseTo.toString() +
				", partyId=" + this.partyId.toString() + "]";
	}
}
