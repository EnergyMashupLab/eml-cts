package org.theenergymashuplab.cts;


/**
 * @author crossover
 * @version 1.0
 * @created 28-Sep-2024 8:41:42 PM
 */
public class SessionDataType {

	public MmtLevelTwoPhase currentTradingPhase;
	public MarketIdType marketId;
	public InstantType messageTimeStamp;
	public int segmentId;
	public InstantType sessionClose;
	public InstantType sessionOpen;
	public InstantType sessionPreClose;
	public InstantType sessionStart;
	public SessionStatusType sessionStatus;
	public IntervalType tradeableInstrumentRange;

	public SessionDataType(){

	}

	public SessionDataType(MmtLevelTwoPhase currentTradingPhase, MarketIdType marketId, InstantType messageTimeStamp,
						   int segmentId, InstantType sessionClose, InstantType sessionOpen, InstantType sessionPreClose,
						   InstantType sessionStart, SessionStatusType sessionStatus, IntervalType tradeableInstrumentRange){
		this.currentTradingPhase = currentTradingPhase;
		this.marketId = marketId;
		this.messageTimeStamp = messageTimeStamp;
		this.segmentId = segmentId;
		this.sessionClose = sessionClose;
		this.sessionOpen = sessionOpen;
		this.sessionPreClose = sessionPreClose;
		this.sessionStart = sessionStart;
		this.sessionStatus = sessionStatus;
		this.tradeableInstrumentRange = tradeableInstrumentRange;
	}

	public MmtLevelTwoPhase getCurrentTradingPhase() { return currentTradingPhase; }

	public void setCurrentTradingPhase(MmtLevelTwoPhase currentTradingPhase) { this.currentTradingPhase = currentTradingPhase; }

	public MarketIdType getMarketId() { return marketId; }

	public void setMarketId(MarketIdType marketId) { this.marketId = marketId; }

	public InstantType getMessageTimeStamp() { return messageTimeStamp; }

	public void setMessageTimeStamp(InstantType messageTimeStamp) { this.messageTimeStamp = messageTimeStamp; }

	public int getSegmentId() { return segmentId; }

	public void setSegmentId(int segmentId) { this.segmentId = segmentId; }		// segmentID + marketId must be unique

	public InstantType getSessionClose() { return sessionClose; }

	public void setSessionClose(InstantType sessionClose) { this.sessionClose = sessionClose; }

	public InstantType getSessionOpen() { return sessionOpen; }

	public void setSessionOpen(InstantType sessionOpen) { this.sessionOpen = sessionOpen; }

	public InstantType getSessionPreClose() { return sessionPreClose; }

	public void setSessionPreClose(InstantType sessionPreClose) { this.sessionPreClose = sessionPreClose; }

	public InstantType getSessionStart() { return sessionStart; }

	public void setSessionStart(InstantType sessionStart) { this.sessionStart = sessionStart; }

	public SessionStatusType getSessionStatus() { return sessionStatus; }

	public void setSessionStatus(SessionStatusType sessionStatus) { this.sessionStatus = sessionStatus; }

	public IntervalType getTradeableInstrumentRange() { return tradeableInstrumentRange; }

	public void setTradeableInstrumentRange(IntervalType tradeableInstrumentRange) { this.tradeableInstrumentRange = tradeableInstrumentRange; }
}
