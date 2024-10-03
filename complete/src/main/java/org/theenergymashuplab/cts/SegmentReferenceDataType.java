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

	public void finalize() throws Throwable {

	}

}