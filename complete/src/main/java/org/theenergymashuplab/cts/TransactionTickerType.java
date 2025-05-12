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

import org.theenergymashuplab.cts.controller.payloads.TickerPayloadBase;

/**
 * @author crossover
 * @version 1.0
 * @created 28-Sep-2024 8:41:43 PM
 */
public class TransactionTickerType extends TickerPayloadBase {

	public EiTransactionType transaction;

	public TransactionTickerType() {

	}

	public EiTransactionType getTransaction() {
		return transaction;
	}

	public void setTransaction(EiTransactionType transaction) {
		this.transaction = transaction;
	}

	@Override
	public String toString() {
		return "TransactionTickerType{" + "transaction=" + transaction + ", counterParty=" + counterParty + ", party="
				+ party + ", subscriptionId=" + subscriptionId + ", tickerType=" + tickerType + '}';
	}

	public TransactionTickerType(EiTransactionType transaction) {
		this.transaction = transaction;
	}
}
