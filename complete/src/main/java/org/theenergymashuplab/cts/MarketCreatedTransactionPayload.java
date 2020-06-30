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

/*
 * Sent by LME to Market in response to a MarketCreateTransactionPayload
 */

public class MarketCreatedTransactionPayload {
	private Boolean success = false;
	private String info = "MarketCreatedTransactionPayload";
	// return  created TransactionIdType to CtsBridge
	private TransactionIdType ctsTransactionId = new TransactionIdType();
	//return MarketCreateTransactionPayload matchNumber as correlation ID
	private long parityMatchNumber = 0; 
	
	MarketCreatedTransactionPayload()	{
		success = true;
	}
	
	@Override
	public String toString()	{
		return (info + " success is " + success.toString());
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
}
