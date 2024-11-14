package org.theenergymashuplab.cts;


import java.time.Instant;

/**
 * @author crossover
 * @version 1.0
 * @created 28-Sep-2024 8:41:40 PM
 */
public class EiQuoteType extends TenderBase {

	public MarketOrderIdType marketOrderId;
	public boolean privateQuote;
	public TenderIdType quoteId = new TenderIdType();
	public RfqIdType rfqId;
	public boolean tradeable;

	public EiQuoteType(){

	}

	public EiQuoteType(MarketOrderIdType marketOrderId, boolean privateQuote, TenderIdType quoteId, RfqIdType rfqId, boolean tradeable){
		this.marketOrderId = marketOrderId;
		this.privateQuote = privateQuote;
		this.quoteId = quoteId;
		this.rfqId = rfqId;
		this.tradeable = tradeable;
	}

	public EiQuoteType(Instant instant, SideType side, TenderDetail quoteDetail) {
		super(instant, side, quoteDetail);
	}

	public MarketOrderIdType getMarketOrderId(){
		return this.marketOrderId;
	}

	public void setMarketOrderId(MarketOrderIdType marketOrderId){
		this.marketOrderId = marketOrderId;
	}

	public boolean getPrivateQuote(){
		return this.privateQuote;
	}

	public void setPrivateQuote(boolean privateQuote){
		this.privateQuote = privateQuote;
	}

	public TenderIdType getQuoteId(){
		return this.quoteId;
	}

	public void setQuoteId(TenderIdType quoteId){
		this.quoteId = quoteId;
	}

	public RfqIdType getRfqId(){
		return this.rfqId;
	}

	public void setRfqId(RfqIdType rfqId){
		this.rfqId = rfqId;
	}

	public boolean getTradeable(){
		return this.tradeable;
	}

	public void setTradeable(boolean tradeable){
		this.tradeable = tradeable;
	}

	@Override
	public String toString() {
		return "EiQuoteType{" +
				"marketQuoteId=" + marketOrderId +
				", privateQuote=" + privateQuote +
				", quoteId=" + quoteId +
				", rfqId=" + rfqId +
				", tradeable=" + tradeable +
				'}';
	}

	/**
	 * We are guaranteed to have a unique market order ID, and the AcceptQuote will reference
	 * a quote via the marketOrderId. As such, it makes sense to index by it in a hashset
	 */
	@Override
	public int hashCode(){
		return (int)this.marketOrderId.getMyUidId();

	}
}
