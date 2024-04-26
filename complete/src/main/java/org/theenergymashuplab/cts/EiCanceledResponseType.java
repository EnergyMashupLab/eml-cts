package org.theenergymashuplab.cts;

public class EiCanceledResponseType {
	private CancelReasonType cancelReason;
	private MarketOrderIdType marketOrderId;
	public int remainingQuantity = 0;
	public boolean success = false;
	
	public EiCanceledResponseType() {
		
	}

	public EiCanceledResponseType(CancelReasonType cancelReason, MarketOrderIdType marketOrderId, int remainingQuantity,
			boolean success) {
		this.cancelReason = cancelReason;
		this.marketOrderId = marketOrderId;
		this.remainingQuantity = remainingQuantity;
		this.success = success;
	}

	@Override
	public String toString() {
		return "EiCanceledResponseType [cancelReason=" + cancelReason + ", marketOrderId=" + marketOrderId
				+ ", remainingQuantity=" + remainingQuantity + ", success=" + success + "]";
	}

	public CancelReasonType getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(CancelReasonType cancelReason) {
		this.cancelReason = cancelReason;
	}

	public MarketOrderIdType getMarketOrderId() {
		return marketOrderId;
	}

	public void setMarketOrderId(MarketOrderIdType marketOrderId) {
		this.marketOrderId = marketOrderId;
	}

	public int getRemainingQuantity() {
		return remainingQuantity;
	}

	public void setRemainingQuantity(int remainingQuantity) {
		this.remainingQuantity = remainingQuantity;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}
