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

import org.theenergymashuplab.cts.MarketOrderIdType;

public class ClientAcceptedQuotePayload {
	private String info = "ClientAcceptedQuotePayload";
	private long price;
	private long quantity;
	private MarketOrderIdType referencedQuoteId;

	// JSON
	public ClientAcceptedQuotePayload() {

	}

	public MarketOrderIdType getReferencedQuoteId() {
		return this.referencedQuoteId;
	}

	public void setReferencedQuoteId(MarketOrderIdType referencedQuoteId) {
		this.referencedQuoteId = referencedQuoteId;
	}

	public long getQuantity() {
		return this.quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public long getPrice() {
		return this.price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public String getInfo() {
		return this.info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
}
