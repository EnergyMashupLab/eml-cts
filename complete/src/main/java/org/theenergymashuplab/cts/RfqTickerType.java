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
 * @created 28-Sep-2024 8:41:42 PM
 */
public class RfqTickerType extends TickerPayloadBase {

	public EiRfqType rfq;

	public RfqTickerType() {}

	public RfqTickerType(EiRfqType rfq) {
		this.rfq = rfq;
	}

	public void setRfq(EiRfqType rfq) {
		this.rfq = rfq;
	}

	public EiRfqType getRfq() {
		return rfq;
	}

	@Override
	public String toString() {
		return "RfqTickerType {" + " rfq = " + rfq + " }";
	}
}
