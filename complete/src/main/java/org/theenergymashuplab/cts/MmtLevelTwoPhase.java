package org.theenergymashuplab.cts;


/**
 * @author crossover
 * @version 1.0
 * @created 28-Sep-2024 8:41:41 PM
 */
public enum MmtLevelTwoPhase {
	PreTrading,
	OpeningOrOpeningAuction,
	Continuous,
	ClosingOrClosingAuction,
	PostTrading,
	ScheduleIntradayAuction,
	Quiescent,
	AnyAuction,
	UnscheduleddIntradayAuction
}