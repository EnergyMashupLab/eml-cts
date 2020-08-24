/*
 * Copyright 2019-2020 The Energy Mashup Lab
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.theenergymashuplab.cts.controller.payloads;

import org.theenergymashuplab.cts.SideType;

public class ClientCreateTransactionPayload {
	String info = "ClientCreateTransactionPayload";
	SideType side;
	private long quantity;
	private long price;
	// external price is multiplied by 10**decimal fraction digits - 3 by convention
	// ctsTenderId is the CTS ID of the tender made by this SC that cleared
	
	long ctsTenderId; // matched in Parity
	
	public ClientCreateTransactionPayload(SideType side, long quantity,
										long price, long tenderId)	{
		// values from EiTransaction that are not implicit (e.g. market, product)
		this.side = side;
		this.quantity = quantity;
		this.price = price;
		this.ctsTenderId= tenderId;
	}
	
	public String toString()	{
		return (info + " side " + side.toString() + " quantity " +
				Long.toString(quantity) + " price " +
				Long.toString(price) + " ctsTenderId " + ctsTenderId);
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public SideType getSide() {
		return side;
	}

	public void setSide(SideType side) {
		this.side = side;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public long getCtsTenderId() {
		return ctsTenderId;
	}

	public void setCtsTenderId(long ctsTenderId) {
		this.ctsTenderId = ctsTenderId;
	}
	
}
