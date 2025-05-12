/*
 * Copyright 2019-2025 The Energy Mashup Lab
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
