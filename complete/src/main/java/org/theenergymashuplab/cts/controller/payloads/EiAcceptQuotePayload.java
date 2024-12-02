package org.theenergymashuplab.cts.controller.payloads;

import org.theenergymashuplab.cts.EiTenderType;
import org.theenergymashuplab.cts.EiTransaction;
import org.theenergymashuplab.cts.MarketOrderIdType;
import org.theenergymashuplab.cts.TenderIntervalDetail;
import org.theenergymashuplab.cts.TransactionIdType;

/**
 * @author crossover
 * @version 1.0
 * @created 28-Sep-2024 8:41:39 PM
 */
public class EiAcceptQuotePayload extends EiCreateTransactionPayload {

	public MarketOrderIdType referencedQuoteId;

	public EiAcceptQuotePayload(){

	}

	public EiAcceptQuotePayload(MarketOrderIdType referencedQuoteId, long quantity, long price){
		//Instantiate a new transaction
		this.setTransaction(new EiTransaction());
		//Instantiate
		this.getTransaction().setTender(new EiTenderType());
		//Create a new tender interval detail
		this.getTransaction().getTender().setTenderDetail(new TenderIntervalDetail(null, price, quantity));;
		//Set the referenced quote ID
		this.referencedQuoteId = referencedQuoteId;
		//Set the market transaction
		this.setMarketTransactionId(new TransactionIdType());

	}

	public MarketOrderIdType getReferencedQuoteId(){
		return this.referencedQuoteId;
	}

	public void setReferencedQuoteID(MarketOrderIdType referencedQuoteId){
		this.referencedQuoteId = referencedQuoteId;
	}
	
	@Override
	public String toString(){
		return 	"EiAcceptQuotePayload [" +
				"referencedQuoteId=" + referencedQuoteId.toString() +
			                        "]";
	}
}
