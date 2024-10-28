package org.theenergymashuplab.cts.controller.payloads;

import org.theenergymashuplab.cts.*;

public class EiCreateStreamQuotePayload{
    private String executionInstructions = "";
    private MarketIdType marketId = new MarketIdType();  // Should be provided externally
    private int segmentId = 1;  // Assumed to always be one segment, so it's always one at the moment

    private ActorIdType counterPartyId;
    private ActorIdType partyId;
    private RefIdType requestId;
    private EiQuoteType quote;

	/*
	@JsonIgnore
	private final Random rand = new Random();
	 */

    /*
     * Default constructor for JSON deserialization.
     * TO DO change to zero Id values in ActorId and RefId constructors
     */

    public EiCreateStreamQuotePayload(){
//        this.counterPartyId = new ActorIdType();
//        this.partyId = new ActorIdType();
//        this.requestId = new RefIdType();
    }

    /*
     * Parallel for EiCreateTransaction, EiCreateTender, EiCreateStreamTender:
     * 		pass in a completed Tender, stream tender or Transaction which includes through its Tender interval, quantity, price,
     * 		or for EiCancelTender only the TenderId.
     *
     * Add party, counterParty, and requestId for the message payload.
     */
    public EiCreateStreamQuotePayload(EiQuoteType quote, ActorIdType party, ActorIdType counterParty) {
        this.quote = quote;
        this.partyId = party;
        this.counterPartyId = counterParty;
        this.requestId = new RefIdType();
    }

    @Override
    public String toString() {
        return "EiCreateStreamQuotePayload [executionInstructions=" + executionInstructions
                + ", marketId=" + marketId + ", segmentId=" + segmentId + ", counterPartyId=" + counterPartyId
                + ", partyId=" + partyId + ", requestId=" + requestId + ", quote=" + quote + "]";
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

    public EiQuoteType getQuote() {
        return quote;
    }

    public void setQuote(EiQuoteType quote) {
        this.quote = quote;
    }

    public String getExecutionInstructions() {
        return executionInstructions;
    }

    public void setExecutionInstructions(String executionInstructions) {
        this.executionInstructions = executionInstructions;
    }

    public MarketIdType getMarketId() {
        return marketId;
    }

    public void setMarketId(MarketIdType marketId) {
        this.marketId = marketId;
    }

    public int getSegmentId() {
        return segmentId;
    }

    public void setSegmentId(int segmentId) {
        this.segmentId = segmentId;
    }
}
