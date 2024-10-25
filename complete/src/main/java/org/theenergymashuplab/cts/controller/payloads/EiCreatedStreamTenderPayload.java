package org.theenergymashuplab.cts.controller.payloads;

import java.util.List;

import org.theenergymashuplab.cts.ActorIdType;
import org.theenergymashuplab.cts.EiResponse;
import org.theenergymashuplab.cts.MarketOrderIdType;
import org.theenergymashuplab.cts.RefIdType;
import org.theenergymashuplab.cts.TenderIdType;

public class EiCreatedStreamTenderPayload{
	private MarketOrderIdType marketOrderId = new MarketOrderIdType();
	private ActorIdType partyId;
	private ActorIdType counterPartyId;
	public EiResponse response;
	private List<Long> createdTenders;
	
	// Need clarification as to what this attribute refers to before changing or deleting
	private final RefIdType refId = new RefIdType();
	private RefIdType inResponseTo;  // May be more prudent to rename and use refID instead of this new attribute 

	/*
	 * Default constructor for JSON deserialization.
	 * TO DO change to zero Id values in ActorId and RefId constructors
	 */
	public EiCreatedStreamTenderPayload()	{		
	}
	
	public EiCreatedStreamTenderPayload(
			TenderIdType tenderId,
			ActorIdType partyId,
			ActorIdType counterPartyId,
			EiResponse response,
			RefIdType inResponseTo) {

		this.partyId = partyId;
		this.counterPartyId = counterPartyId;
		this.response = response;
		this.inResponseTo = inResponseTo;
	}

	public void print() {		
		System.err.println(this);
	}
	
	public List<Long> getCreatedTenders(){
		return this.createdTenders;
	}

	public void setCreatedTenders(List<Long> createdTenders){
		this.createdTenders = createdTenders;
	}


	@Override
	public String toString() {
		return "EiCreatedTenderPayload [marketOrderId=" + marketOrderId +  ", partyId="
				+ partyId + ", counterPartyId=" + counterPartyId + ", response=" + response + ", refId=" + refId
				+ ", inResponseTo=" + inResponseTo +  ", createdTenders=" + createdTenders + "]";
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

	public RefIdType getRefId() {
		return refId;
	}

	public MarketOrderIdType getMarketOrderId() {
		return marketOrderId;
	}

	public void setMarketOrderId(MarketOrderIdType marketOrderId) {
		this.marketOrderId = marketOrderId;
	}

	public RefIdType getInResponseTo() {
		return inResponseTo;
	}

	public void setInResponseTo(RefIdType inResponseTo) {
		this.inResponseTo = inResponseTo;
	}


	public void setPartyId(ActorIdType partyId) {
		this.partyId = partyId;
	}

	public void setCounterPartyId(ActorIdType counterPartyId) {
		this.counterPartyId = counterPartyId;
	}
}
