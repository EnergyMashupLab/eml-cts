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

package org.theenergymashuplab.cts;

public class ClientTender {
	private SideType side;
	private long quantity;
	private long price;	// with multipler for decimal fraction
	
	public ClientTender()	{
		side = SideType.BUY;
		quantity = 0;
		price = 0;
	}
	
	// new ClientTender(randSide, randQuantity, randPrice)
	
	public ClientTender(SideType side, long quantity, long price)	{
		this.side = side;
		this.quantity = quantity;
		this.price = price;
	}
	
	@Override
	public String toString()	{
		return ("ClientTender side " + side.toString() + " quantity " +
				Long.toString(quantity) + " price " +
				Long.toString((price/1000)));			
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
}
