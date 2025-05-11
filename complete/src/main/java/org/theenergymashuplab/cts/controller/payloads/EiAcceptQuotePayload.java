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

	public EiAcceptQuotePayload() {

	}

	public EiAcceptQuotePayload(MarketOrderIdType referencedQuoteId, long quantity, long price) {
		// Instantiate a new transaction
		this.setTransaction(new EiTransaction());
		// Instantiate
		this.getTransaction().setTender(new EiTenderType());
		// Create a new tender interval detail
		this.getTransaction().getTender().setTenderDetail(new TenderIntervalDetail(null, price, quantity));
		;
		// Set the referenced quote ID
		this.referencedQuoteId = referencedQuoteId;
		// Set the market transaction
		this.setMarketTransactionId(new TransactionIdType());

	}

	public MarketOrderIdType getReferencedQuoteId() {
		return this.referencedQuoteId;
	}

	public void setReferencedQuoteID(MarketOrderIdType referencedQuoteId) {
		this.referencedQuoteId = referencedQuoteId;
	}

	@Override
	public String toString() {
		return "EiAcceptQuotePayload [" + "referencedQuoteId=" + referencedQuoteId.toString() + "]";
	}
}
