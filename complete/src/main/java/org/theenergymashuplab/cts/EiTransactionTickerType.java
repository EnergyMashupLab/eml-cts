package org.theenergymashuplab.cts;


/**
 * @author crossover
 * @version 1.0
 * @created 28-Sep-2024 8:41:40 PM
 */
public class EiTransactionTickerType {

	public IntervalType interval;
	public String marketContext;
	/**
	 * A long to which the Market or MarketPlace scale factors are applied. High
	 * performance open source market implementations often use Long rather than Fixed
	 * Decimal or Float,
	 */
	public long price;
	public long quantity;
	public ResourceDesignatorType resourceDesignator;
	public InstantType saleTime;
	public SideType side;
	public RefIdType tickerId;

	public EiTransactionTickerType(){

	}
}
