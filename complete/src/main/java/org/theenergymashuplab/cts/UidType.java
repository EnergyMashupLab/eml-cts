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

import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class UidType {

	public long myUidId = 0;
//	private static final AtomicLong uidIdCounter = new AtomicLong();

//	UidType(long uidId){
//		myUidId = uidIdCounter.addAndGet(uidId);
//	    }

	UidType(){
		myUidId = UUID.randomUUID().getMostSignificantBits();
//		myUidId = uidIdCounter.incrementAndGet();
	    }

	public long value() {
		return this.myUidId;
	}
	
	public String toString()	{
		return ("Uid: " + String.valueOf(this.myUidId));
	}

	public long getMyUidId() {
		return myUidId;
	}

	public void setMyUidId(long myUidId) {
		this.myUidId = myUidId;
	}
	
}
