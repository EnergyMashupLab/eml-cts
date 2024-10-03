package org.theenergymashuplab.cts;


/**
 * @author crossover
 * @version 1.0
 * @created 28-Sep-2024 8:41:40 PM
 */
public class EiRfqType extends TenderBase {

	public IntervalType boundingInterval;
	public DurationType duration;
	public MarketOrderIdType marketRfqId;
	public boolean privateRfq;
	public RfqIdType rfqId;
	public boolean tradeableOnlyResponse;

	public EiRfqType(){

	}
}
