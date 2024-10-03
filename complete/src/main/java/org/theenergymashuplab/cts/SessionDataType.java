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
}
