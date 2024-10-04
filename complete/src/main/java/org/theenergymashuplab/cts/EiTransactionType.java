package org.theenergymashuplab.cts;


/**
 * While EiTransaction extends EiTender in the base standards, here we use the
 * "has a" construction because the EiTender exists before the EiTransaction, so a
 * constructor cannot create a new EiTender
 * @author crossover
 * @version 1.0
 * @created 28-Sep-2024 8:41:40 PM
 */
public class EiTransactionType {

	public MarketTransactionIdType marketTransactionId;
	public EiTenderType tender;

	public EiTransactionType(){

	}

	public EiTransactionType(MarketTransactionIdType marketTransactionId, EiTenderType tender) {
		this.marketTransactionId = marketTransactionId;
		this.tender = tender;
	}

	public MarketTransactionIdType getMarketTransactionId() {
		return marketTransactionId;
	}

	public void setMarketTransactionId(MarketTransactionIdType marketTransactionId) {
		this.marketTransactionId = marketTransactionId;
	}

	public EiTenderType getTender() {
		return tender;
	}

	public void setTender(EiTenderType tender) {
		this.tender = tender;
	}

	@Override
	public String toString() {
		return "EiTransactionType{" +
				"marketTransactionId=" + marketTransactionId +
				", tender=" + tender +
				'}';
	}
}
