package org.theenergymashuplab.cts;

/*
 * Sent by LME to Market in response to a MarketCreateTransactionPayload
 */

public class MarketCreatedTransactionPayload {
	private Boolean success = false;
	private String info = "MarketCreatedTransactionPayload";
	// return  created TransactionIdType to CtsBridge
	private TransactionIdType ctsTransactionId = new TransactionIdType();
	//return MarketCreateTransactionPayload matchNumber as correlation ID
	private long parityMatchNumber = 0; 
	
	MarketCreatedTransactionPayload()	{
		success = true;
	}
	
	@Override
	public String toString()	{
		return (info + " success is " + success.toString());
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
}
