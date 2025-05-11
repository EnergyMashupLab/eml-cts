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

/**
 * While EiTransaction extends EiTender in the base standards, here we use the "has a" construction because the EiTender
 * exists before the EiTransaction, so a constructor cannot create a new EiTender
 * 
 * @author crossover
 * @version 1.0
 * @created 28-Sep-2024 8:41:40 PM
 */
public class EiTransactionType {

	public MarketTransactionIdType marketTransactionId;
	public EiTenderType tender;

	public EiTransactionType() {

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
		return "EiTransactionType{" + "marketTransactionId=" + marketTransactionId + ", tender=" + tender + '}';
	}
}
