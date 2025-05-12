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
 * @created 28-Sep-2024 8:41:41 PM
 */
public class QuoteTickerType extends TickerPayloadBase {

	public EiQuoteType quote;

	public QuoteTickerType() {

	}

	public QuoteTickerType(EiQuoteType quote) {
		this.quote = quote;
	}

	public void setQuote(EiQuoteType quote) {
		this.quote = quote;
	}

	public EiQuoteType getQuote() {
		return quote;
	}

	@Override
	public String toString() {
		return "QuoteTickerType {" + " quote = " + quote + " }";
	}

}
