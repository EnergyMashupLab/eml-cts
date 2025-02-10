package org.theenergymashuplab.cts.controller.payloads;

import org.theenergymashuplab.cts.ActorIdType;
import org.theenergymashuplab.cts.EiQuoteType;
import org.theenergymashuplab.cts.MarketIdType;
import org.theenergymashuplab.cts.RefIdType;
import org.theenergymashuplab.cts.ResourceDesignatorType;

/**
 * @author crossover
 * @version 1.0
 * @created 28-Sep-2024 8:41:39 PM
 */
public class EiCreateQuotePayload {

	public boolean atMostOne;
	public ActorIdType counterPartyId;
	public String executionInstructions;
	public MarketIdType marketId;
	public ActorIdType partyId;
	public EiQuoteType quote;
	public RefIdType requestId;
	public boolean requestPrivate;
	public boolean requestPublication;
	public ResourceDesignatorType resourceDesignator;
	public int segmentId;

	public EiCreateQuotePayload(){

	}

	public EiCreateQuotePayload(boolean atMostOne, ActorIdType counterPartyId, String executionInstructions, MarketIdType marketId, ActorIdType partyId, EiQuoteType quote,
			RefIdType requestId, boolean requestPrivate, boolean requestPublication, ResourceDesignatorType resourceDesignator, int segmentId){

		this.atMostOne = atMostOne;
		this.counterPartyId = counterPartyId;
		this.executionInstructions = executionInstructions;
		this.marketId = marketId;
		this.partyId = partyId;
		this.quote = quote;
		this.requestId = requestId;
		this.requestPrivate = requestPrivate;
		this.requestPublication = requestPublication;
		this.resourceDesignator = resourceDesignator;
		this.segmentId = segmentId;
	}

	public EiCreateQuotePayload(EiQuoteType quote, ActorIdType actorId, ActorIdType lmePartyId) {
		this.quote = quote;
		this.partyId = actorId;
		this.counterPartyId = lmePartyId;
	}

	public boolean getAtMostOne(){
		return this.atMostOne;
	}

	public void setAtMostOne(boolean atMostOne){
		this.atMostOne = atMostOne;
	}

	public ActorIdType getCounterPartyId(){
		return this.counterPartyId;
	}

	public void setCounterPartyId(ActorIdType counterPartyId){
		this.counterPartyId = counterPartyId;
	}

	public String getExecutionInstructions(){
		return this.executionInstructions;
	}

	public void setExecutionInstructions(String executionInstructions){
		this.executionInstructions = executionInstructions;
	}

	public MarketIdType getMarketId(){
		return this.marketId;
	}

	public void setMarketId(MarketIdType marketId){
		this.marketId = marketId;
	}

	public ActorIdType getPartyId(){
		return this.partyId;
	}

	public void setPartyId(ActorIdType partyId){
		this.partyId = partyId;
	}

	public EiQuoteType getQuote(){
		return this.quote;
	}

	public void setQuote(EiQuoteType quote){
		this.quote = quote;
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

	public ResourceDesignatorType getResourceDesginator(){
		return this.resourceDesignator;
	}

	public void setResourceDesignator(ResourceDesignatorType resourceDesignator){
		this.resourceDesignator = resourceDesignator;
	}

	public int getSegmentId(){
		return this.segmentId;
	}

	public void setSegmentId(int segmentId){
		this.segmentId = segmentId;
	}

	@Override
	public String toString() {
		return "EiCreateQuotePayload{" +
				"atMostOne=" + atMostOne +
				", counterPartyId=" + counterPartyId +
				", executionInstructions='" + executionInstructions + '\'' +
				", marketId=" + marketId +
				", partyId=" + partyId +
				", quote=" + quote +
				", requestId=" + requestId +
				", requestPrivate=" + requestPrivate +
				", requestPublication=" + requestPublication +
				", resourceDesignator=" + resourceDesignator +
				", segmentId=" + segmentId +
				'}';
	}
}
