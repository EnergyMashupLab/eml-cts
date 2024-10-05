package org.theenergymashuplab.cts.controller.payloads;

import java.sql.Ref;

import org.theenergymashuplab.cts.ActorIdType;
import org.theenergymashuplab.cts.EiResponseType;
import org.theenergymashuplab.cts.MarketOrderIdType;
import org.theenergymashuplab.cts.RefIdType;
import org.theenergymashuplab.cts.TenderIdType;

/**
 * @author crossover
 * @version 1.0
 * @created 28-Sep-2024 8:41:39 PM
 */
public class EiCreatedQuotePayload {

	public ActorIdType counterPartyId;
	public RefIdType inResponseTo;
	public MarketOrderIdType marketQuoteId;
	public ActorIdType partyId;
	public TenderIdType quoteId;
	public EiResponseType response;

	public EiCreatedQuotePayload(){

	}

	public EiCreatedQuotePayload(ActorIdType counterPartyId, RefIdType inResponseTo, MarketOrderIdType marketQuoteId, ActorIdType partyId, TenderIdType quoteId, EiResponseType response){
		this.counterPartyId = counterPartyId;
		this.inResponseTo = inResponseTo;
		this.marketQuoteId = marketQuoteId;
		this.partyId = partyId;
		this.quoteId = quoteId;
		this.response = response;
	}

	public ActorIdType getCounterPartyId(){
		return this.counterPartyId;
	}

	public void setCounterPartyId(ActorIdType counterPartyId){
		this.counterPartyId = counterPartyId;
	}

	public RefIdType getInResponseTo(){
		return this.inResponseTo;
	}

	public void setInResponseTo(RefIdType inResponseTo){
		this.inResponseTo = inResponseTo;
	}

	public MarketOrderIdType getMarketQuoteId(){
		return this.marketQuoteId;
	}

	public void setMarketQuoteId(MarketOrderIdType marketQuoteId){
		this.marketQuoteId = marketQuoteId;
	}

	public ActorIdType getPartyId(){
		return this.partyId;
	}

	public void setPartyId(ActorIdType partyId){
		this.partyId = partyId;
	}

	public TenderIdType getQuoteId(){
		return this.quoteId;
	}

	public void setQuoteId(TenderIdType quoteId){
		this.quoteId = quoteId;
	}

	public EiResponseType getResponse(){
		return this.response;
	}

	public void setResponse(EiResponseType response){
		this.response = response;
	}

	@Override
	public String toString(){
		return "EiCreatedQuotePayload [" +
				"counterPartyId=" + this.counterPartyId.toString() +
				", inResonseTo=" + this.inResponseTo.toString() +
				", marketQuoteId=" + this.marketQuoteId.toString() +
				", partyId=" + this.partyId.toString() +
				", quoteId=" + this.quoteId.toString() +
				", response=" + this.response.toString() + "]";
	}

}
