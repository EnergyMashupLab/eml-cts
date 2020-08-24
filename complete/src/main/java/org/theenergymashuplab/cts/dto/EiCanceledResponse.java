package org.theenergymashuplab.cts.dto;

import org.theenergymashuplab.cts.TenderIdType;
import org.theenergymashuplab.cts.enums.CancelReason;

public class EiCanceledResponse {

	private TenderIdType tenderID;
	private boolean successful;
	private int canceledCount;
	private CancelReason cancelReason;

	public TenderIdType getTenderID() {
		return tenderID;
	}

	public void setTenderID(TenderIdType tenderID) {
		this.tenderID = tenderID;
	}

	public boolean isSuccessful() {
		return successful;
	}

	public void setSuccessful(boolean successful) {
		this.successful = successful;
	}

	public int getCanceledCount() {
		return canceledCount;
	}

	public void setCanceledCount(int canceledCount) {
		this.canceledCount = canceledCount;
	}

	public CancelReason getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(CancelReason cancelReason) {
		this.cancelReason = cancelReason;
	}
}