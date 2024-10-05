package org.theenergymashuplab.cts;


/**
 * @author crossover
 * @version 1.0
 * @created 28-Sep-2024 8:41:40 PM
 */
public class EiQuoteType extends TenderBase {

	public MarketQuoteIdType marketQuoteId;
	public boolean privateQuote;
	public TenderIdType quoteId;
	public RfqIdType rfqId;
	public boolean tradeable;


	public EiQuoteType(){

	}

	public EiQuoteType(MarketQuoteIdType marketQuoteId, boolean privateQuote, TenderIdType quoteId, RfqIdType rfqId, boolean tradeable){
		this.marketQuoteId = marketQuoteId;
		this.privateQuote = privateQuote;
		this.quoteId = quoteId;
		this.rfqId = rfqId;
		this.tradeable = tradeable;
	}

	public MarketQuoteIdType getMarketQuoteId(){
		return this.marketQuoteId;
	}

	public void setMarketQuoteId(MarketQuoteIdType marketQuoteId){
		this.marketQuoteId = marketQuoteId;
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
	public String toString(){
		return "EiQuoteType [" +
				"marketQuoteId=" + this.marketQuoteId.toString() +
				"privateQuote=" + this.privateQuote +
				"quoteId=" + this.quoteId.toString() +
				"rfqId=" + this.rfqId.toString() +
				"tradeable=" + this.tradeable + "]";
	}
}
