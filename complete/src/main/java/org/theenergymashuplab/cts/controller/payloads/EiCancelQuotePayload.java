package org.theenergymashuplab.cts.controller.payloads;

import java.util.ArrayList;

import org.theenergymashuplab.cts.ActorIdType;
import org.theenergymashuplab.cts.MarketOrderIdType;
import org.theenergymashuplab.cts.RefIdType;
import org.theenergymashuplab.cts.TenderIdType;

/**
 * @author crossover
 * @version 1.0
 * @created 28-Sep-2024 8:41:39 PM
 */
public class EiCancelQuotePayload {

	public ActorIdType counterPartyId;
	//A list of marketQuoteIds to cancel 
	public ArrayList<MarketOrderIdType> marketQuoteIds;
	public ActorIdType partyId;
	private TenderIdType quoteIds;
	public RefIdType requestId;

	public EiCancelQuotePayload(){

	}

	public EiCancelQuotePayload(ActorIdType counterPartyId, ArrayList<MarketOrderIdType> marketQuoteIds, ActorIdType partyId, TenderIdType quoteIds, RefIdType requestId){
		this.counterPartyId = counterPartyId;
		this.marketQuoteIds = marketQuoteIds;
		this.partyId = partyId;
		this.quoteIds = quoteIds;
		this.requestId = requestId;
	}

	public ActorIdType getCounterPartyId(){
		return this.counterPartyId;
	}

	public void setCounterPartyId(ActorIdType counterPartyId){
		this.counterPartyId = counterPartyId;
	}

	public ArrayList<MarketOrderIdType> getMarketQuoteIds(){
		return this.marketQuoteIds;
	}

	public void setMarketQuoteIds(ArrayList<MarketOrderIdType> marketQuoteIds){
		this.marketQuoteIds = marketQuoteIds;
	}

	public ActorIdType getPartyId(){
		return this.partyId;
	}

	public void setPartyId(ActorIdType partyId){
		this.partyId = partyId;
	}

	public TenderIdType getQuoteIds(){
		return this.quoteIds;
	}

	public void setQuoteIds(TenderIdType quoteIds){
		this.quoteIds = quoteIds;
	}

	public RefIdType getRequestId(){
		return this.requestId;
	}

	public void setRequestId(RefIdType requestId){
		this.requestId = requestId;
	}
	
	@Override
	public String toString(){
		return "EiCancelQuotePayload [" +
				"counterPartyId=" + this.counterPartyId.toString() +
				", marketQuoteIds=" + this.marketQuoteIds.toString() +
				", partyId=" + this.partyId.toString() +
				", quoteIds=" + this.quoteIds.toString() +
				", requestId=" + this.requestId.toString() + "]";
	}
}
