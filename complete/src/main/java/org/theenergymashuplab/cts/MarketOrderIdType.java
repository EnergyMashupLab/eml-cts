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

public class MarketOrderIdType extends UidType {

	public MarketOrderIdType(long uidId) {
		super(uidId);
	}

	public MarketOrderIdType() {
	}

	/**
	 * This will be used in the explicit case that we want a duplicate
	 */
	public void setMarketOrderId(long uidId) {
		super.setMyUidId(uidId);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (obj.getClass() != MarketOrderIdType.class) {
			return false;
		}

		return ((MarketOrderIdType) obj).value() == this.value();
	}

	@Override
	public String toString() {
		return "MarketOrderIdType{" +
				"myUidId=" + myUidId +
				'}';
	}
}
