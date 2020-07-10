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

/*
 * MANUAL MIRROR from package com.paritytrading.parity.client;
 * 
 * TODO use single package and import both places
 */
/*
 * Sent by the Market to the LME with information from a market trade, including
 * 		ctsTenderId (CTS TenderId for the original tender in EiCreateTenderPayload)
 * 		quantity Traded (usually less than tendered)
 * 		price Traded (usually different from tendered)
 * 		side (always the same as tendered)
 * 		parityOrderId (to track on Parity Terminal Client, ticker, and reporter)
 * 		matchNumber (from  parity engine; may not be useful)
 * 
 * Information from the MarketCreateTransactionPayload will be used to define an EiCreateTransactionPayload.
 * 
 * MANUAL SYNCHRONIZATION BETWEEN SEPARATE PARITY and CTS Applications
 * Identical as of 20200526:1400
 */
public class MarketCreateTransactionPayload {
	private String info = "MarketCreateTransactionPayload";
	private SideType side;
	long quantity = 0;	// quantity transacted. At least two Transactions created for a match
	long price = 0;		//price may not be that tendered
	long ctsTenderId = 0;	// set before sending -- Map maintained by CtsBridge	
	String parityOrderId = null;	// parity order id for this match from engine
	long matchNumber = 0;	// parity matchNumber for this match from engine
		
	MarketCreateTransactionPayload()	{	// json
	}
	
	// Not used on LME end
	MarketCreateTransactionPayload(String parityOrderId,
			long ctsTenderId,
			long quantity,
			long price,
			long matchNumber,
			SideType side)	{
		this.parityOrderId = parityOrderId;
		this.ctsTenderId = ctsTenderId;
		this.quantity = quantity;
		this.price = price;
		this.matchNumber = matchNumber;
		this.side = side;
	}
	
	@Override
	public String toString()	{
		SideType tempSide = this.side;
		String tempString;	

		tempString = (tempSide == SideType.BUY)? "B" : "S";		
		return (info + " parityOrderId " + parityOrderId +
				" ctsTenderId " + ctsTenderId +
				" side " + tempString +
				" quantity " + quantity 
				+ " price " + price +
				" matchNumber " + matchNumber);
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

	public String getParityOrderId() {
		return parityOrderId;
	}

	public void setParityOrderId(String parityOrderId) {
		this.parityOrderId = parityOrderId;
	}

	public long getMatchNumber() {
		return matchNumber;
	}

	public void setMatchNumber(long matchNumber) {
		this.matchNumber = matchNumber;
	}

}
