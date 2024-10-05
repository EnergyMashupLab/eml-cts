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

	public EiRfqType(IntervalType boundingInterval, DurationType duration, MarketOrderIdType marketRfqId, boolean privateRfq, RfqIdType rfqId, boolean tradeableOnlyResponse){
		this.boundingInterval = boundingInterval;
		this.duration = duration;
		this.marketRfqId = marketRfqId;
		this.privateRfq = privateRfq;
		this.rfqId = rfqId;
		this.tradeableOnlyResponse = tradeableOnlyResponse;
	}
	
	public IntervalType getBoundingInterval(){
		return this.boundingInterval;
	}

	public void setBoundingInterval(IntervalType boundingInterval){
		this.boundingInterval = boundingInterval;
	}

	public DurationType getDuration(){
		return this.duration;
	}

	public void setDuratoin(DurationType duration){
		this.duration = duration;
	}

	public MarketOrderIdType getMarketRfqId(){
		return this.marketRfqId;
	}

	public void setMarketRfqId(MarketOrderIdType marketRfqId){
		this.marketRfqId = marketRfqId;
	}

	public boolean getPrivateRfq(){
		return this.privateRfq;
	}

	public void setPrivateRfq(boolean privateRfq){
		this.privateRfq = privateRfq;
	}

	public RfqIdType getRfqId(){
		return this.rfqId;
	}

	public void setRfqId(RfqIdType rfqId){
		this.rfqId = rfqId;
	}

	public boolean getTradeableOnlyResponse(){
		return this.tradeableOnlyResponse;
	}

	public void setTradeableOnlyResponse(boolean tradeableOnlyResponse){
		this.tradeableOnlyResponse = tradeableOnlyResponse;
	}

	@Override
	public String toString(){
		return "EiRfqType [" +
			"boundingInterval=" + this.boundingInterval.toString() +
			"duration=" + this.duration.toString() +
			"marketRfqId=" + this.marketRfqId.toString() +
			"privateRfq=" + this.privateRfq +
			"rfqId=" + this.rfqId.toString() +
			"tradeableOnlyResponse=" + this.tradeableOnlyResponse + "]";
	}
}
