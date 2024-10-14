package org.theenergymashuplab.cts;


/**
 * @author crossover
 * @version 1.0
 * @created 28-Sep-2024 8:41:42 PM
 */
public class SegmentReferenceDataType {

	public boolean auctionAtClose;
	public boolean auctionAtOpen;
	public String executionInstructionsAccepted;
	public int marketDepth;
	public MarketIdType marketId;
	public boolean marketInstrumentSummaryAvailable;
	public MarketMechanismType marketMechanism;
	public int maxSummaryInstruments;
	public int maxTenderQuantity;
	public InstantType messageTimeStamp;
	public int minTenderQuantity;
	public boolean negotiationsPermitted;
	public NegotiationType negotiationsType;
	public int priceScale;
	public boolean privateNegotiationViaMarket;
	public ProductType product;
	public int quantityScale;
	public int roundLot;
	public InstantType scheduledSessionCloseTime;
	public InstantType scheduledSessionOpenTime;
	public InstantType scheduledSessionStartTime;
	public String segmentDesc;
	public Integer segmentId;
	public SegmentStatusType segmentStatus;
	public StreamTradingType streamTradingOk;
	public String subscriptionEndpoint;
	public boolean tickerQuotes;
	public boolean tickerRfqs;
	public boolean tickerTenders;
	public boolean tickerTransactions;
	public IntervalType tradeableInstrumentRange;
	public String tradeEndpoint;

	public SegmentReferenceDataType(){

	}

	public SegmentReferenceDataType(boolean auctionAtClose, boolean auctionAtOpen, String executionInstructionsAccepted,
									int marketDepth, MarketIdType marketID, boolean marketInstrumentSummaryAvailable,
									MarketMechanismType marketMechanism, int maxSummaryInstruments, int maxTenderQuantity,
									InstantType messageTimeStamp, int minTenderQuantity, boolean negotiationsPermitted,
									NegotiationType negotiationsType, int priceScale, boolean privateNegotiationViaMarket,
									ProductType product, int quantityScale, int roundLot, InstantType scheduledSessionCloseTime,
									InstantType scheduledSessionOpenTime, InstantType scheduledSessionStartTime,
									String segmentDesc, Integer segmentId, SegmentStatusType segmentStatus,
									StreamTradingType streamTradingOk, String subscriptionEndpoint, boolean tickerQuotes,
									boolean tickerRfqs, boolean tickerTenders, boolean tickerTransactions,
									IntervalType tradeableInstrumentRange, String tradeEndpoint) {
		this.auctionAtClose = auctionAtClose;
		this.auctionAtOpen = auctionAtOpen;
		this.executionInstructionsAccepted = executionInstructionsAccepted;
		this.marketDepth = marketDepth;
		this.marketId = marketID;
		this.marketInstrumentSummaryAvailable = marketInstrumentSummaryAvailable;
		this.marketMechanism = marketMechanism;
		this.maxSummaryInstruments = maxSummaryInstruments;
		this.maxTenderQuantity = maxTenderQuantity;
		this.messageTimeStamp = messageTimeStamp;
		this.minTenderQuantity = minTenderQuantity;
		this.negotiationsPermitted = negotiationsPermitted;
		this.negotiationsType = negotiationsType;
		this.priceScale = priceScale;
		this.privateNegotiationViaMarket = privateNegotiationViaMarket;
		this.product = product;
		this.quantityScale = quantityScale;
		this.roundLot = roundLot;
		this.scheduledSessionCloseTime = scheduledSessionCloseTime;
		this.scheduledSessionOpenTime = scheduledSessionOpenTime;
		this.scheduledSessionStartTime = scheduledSessionStartTime;
		this.segmentDesc = segmentDesc;
		this.segmentId = segmentId;
		this.segmentStatus = segmentStatus;
		this.streamTradingOk = streamTradingOk;
		this.subscriptionEndpoint = subscriptionEndpoint;
		this.tickerQuotes = tickerQuotes;
		this.tickerRfqs = tickerRfqs;
		this.tickerTenders = tickerTenders;
		this.tickerTransactions = tickerTransactions;
		this.tradeableInstrumentRange = tradeableInstrumentRange;
		this.tradeEndpoint = tradeEndpoint;
	}

	public boolean isAuctionAtClose() { return auctionAtClose; }

	public void setAuctionAtClose(boolean auctionAtClose) { this.auctionAtClose = auctionAtClose; }

	public boolean isAuctionAtOpen() { return auctionAtOpen; }

	public void setAuctionAtOpen(boolean auctionAtOpen) { this.auctionAtOpen = auctionAtOpen; }

	public String getExecutionInstructionsAccepted() { return executionInstructionsAccepted; }

	public void setExecutionInstructionsAccepted(String executionInstructionsAccepted) { this.executionInstructionsAccepted = executionInstructionsAccepted; }

	public int getMarketDepth() { return marketDepth; }

	public void setMarketDepth(int marketDepth) { this.marketDepth = marketDepth; }

	public MarketIdType getMarketId() { return marketId; }

	public void setMarketId(MarketIdType marketId) { this.marketId = marketId; }

	public boolean isMarketInstrumentSummaryAvailable() { return marketInstrumentSummaryAvailable; }

	public void setMarketInstrumentSummaryAvailable(boolean marketInstrumentSummaryAvailable) { this.marketInstrumentSummaryAvailable = marketInstrumentSummaryAvailable; }

	public MarketMechanismType getMarketMechanism() { return marketMechanism; }

	public void setMarketMechanism(MarketMechanismType marketMechanism) { this.marketMechanism = marketMechanism; }

	public int getMaxSummaryInstruments() { return maxSummaryInstruments; }

	public void setMaxSummaryInstruments(int maxSummaryInstruments) { this.maxSummaryInstruments = maxSummaryInstruments; }

	public int getMaxTenderQuantity() { return maxTenderQuantity; }

	public void setMaxTenderQuantity(int maxTenderQuantity) { this.maxTenderQuantity = maxTenderQuantity; }

	public InstantType getMessageTimeStamp() { return messageTimeStamp; }

	public void setMessageTimeStamp(InstantType messageTimeStamp) { this.messageTimeStamp = messageTimeStamp; }

	public int getMinTenderQuantity() { return minTenderQuantity; }

	public void setMinTenderQuantity(int minTenderQuantity) { this.minTenderQuantity = minTenderQuantity; }

	public boolean isNegotiationsPermitted() { return negotiationsPermitted; }

	public void setNegotiationsPermitted(boolean negotiationsPermitted) { this.negotiationsPermitted = negotiationsPermitted; }

	public int getPriceScale() { return priceScale; }

	public void setPriceScale(int priceScale) { this.priceScale = priceScale; }

	public boolean isPrivateNegotiationViaMarket() { return privateNegotiationViaMarket; }

	public void setPrivateNegotiationViaMarket(boolean privateNegotiationViaMarket) { this.privateNegotiationViaMarket = privateNegotiationViaMarket; }

	public ProductType getProduct() { return product; }

	public void setProduct(ProductType product) { this.product = product; }

	public int getQuantityScale() { return quantityScale; }

	public void setQuantityScale(int quantityScale) { this.quantityScale = quantityScale; }

	public int getRoundLot() { return roundLot; }

	public void setRoundLot(int roundLot) { this.roundLot = roundLot; }

	public InstantType getScheduledSessionCloseTime() { return scheduledSessionCloseTime; }

	public void setScheduledSessionCloseTime(InstantType scheduledSessionCloseTime) { this.scheduledSessionCloseTime = scheduledSessionCloseTime; }

	public InstantType getScheduledSessionOpenTime() { return scheduledSessionOpenTime; }

	public void setScheduledSessionOpenTime(InstantType scheduledSessionOpenTime) { this.scheduledSessionOpenTime = scheduledSessionOpenTime; }

	public InstantType getScheduledSessionStartTime() { return scheduledSessionStartTime; }

	public void setScheduledSessionStartTime(InstantType scheduledSessionStartTime) { this.scheduledSessionStartTime = scheduledSessionStartTime; }

	public String getSegmentDesc() { return segmentDesc; }

	public void setSegmentDesc(String segmentDesc) { this.segmentDesc = segmentDesc; }

	public int getSegmentId() { return segmentId; }

	public void setSegmentId(int segmentId) { this.segmentId = segmentId; }

	public SegmentStatusType getSegmentStatus() { return segmentStatus; }

	public void setSegmentStatus(SegmentStatusType segmentStatus) { this.segmentStatus = segmentStatus; }

	public StreamTradingType getStreamTradingOk() { return streamTradingOk; }

	public void setStreamTradingOk(StreamTradingType streamTradingOk) { this.streamTradingOk = streamTradingOk; }

	public String getSubscriptionEndpoint() { return subscriptionEndpoint; }

	public void setSubscriptionEndpoint(String subscriptionEndpoint) { this.subscriptionEndpoint = subscriptionEndpoint; }

	public boolean isTickerQuotes() { return tickerQuotes; }

	public void setTickerQuotes(boolean tickerQuotes) { this.tickerQuotes = tickerQuotes; }

	public boolean isTickerRfqs() { return tickerRfqs; }

	public void setTickerRfqs(boolean tickerRfqs) { this.tickerRfqs = tickerRfqs; }

	public boolean isTickerTenders() { return tickerTenders; }

	public void setTickerTenders(boolean tickerTenders) { this.tickerTenders = tickerTenders; }

	public boolean isTickerTransactions() { return tickerTransactions; }

	public void setTickerTransactions(boolean tickerTransactions) { this.tickerTransactions = tickerTransactions; }

	public IntervalType getTradeableInstrumentRange() { return tradeableInstrumentRange; }

	public void setTradeableInstrumentRange(IntervalType tradeableInstrumentRange) { this.tradeableInstrumentRange = tradeableInstrumentRange; }

	public String getTradeEndpoint() { return tradeEndpoint; }

	public void setTradeEndpoint(String tradeEndpoint) { this.tradeEndpoint = tradeEndpoint; }

	@Override
	public String toString() {
		return "SegmentReferenceDataType {" +
				" auctionAtClose = " + auctionAtClose +
				", auctionAtOpen = " + auctionAtOpen +
				", executionInstructionsAccepted = " + executionInstructionsAccepted +
				", marketDepth = " + marketDepth +
				", marketId = " + marketId +
				", marketInstrumentSummaryAvailable = " + marketInstrumentSummaryAvailable +
				", marketMechanism = " + marketMechanism +
				", maxSummaryInstruments = " + maxSummaryInstruments +
				", maxTenderQuantity = " + maxTenderQuantity +
				", minTenderQuantity = " + minTenderQuantity +
				", negotiationsPermitted = " + negotiationsPermitted +
				", priceScale = " + priceScale +
				", privateNegotiationViaMarket = " + privateNegotiationViaMarket +
				", product = " + product +
				", quantityScale = " + quantityScale +
				", roundLot = " + roundLot +
				", scheduledSessionCloseTime = " + scheduledSessionCloseTime +
				", scheduledSessionOpenTime = " + scheduledSessionOpenTime +
				", scheduledSessionStartTime = " + scheduledSessionStartTime +
				", segmentDesc = " + segmentDesc +
				", segmentId = " + segmentId +
				", segmentStatus = " + segmentStatus +
				", streamTradingOk = " + streamTradingOk +
				", subscriptionEndpoint = " + subscriptionEndpoint +
				", tickerQuotes = " + tickerQuotes +
				", tickerRfqs = " + tickerRfqs +
				", tickerTenders = " + tickerTenders +
				", tickerTransactions = " + tickerTransactions +
				", tradeableInstrumentRange = " + tradeableInstrumentRange +
				", tradeEndpoint = " + tradeEndpoint +
				"}";
	}

}
