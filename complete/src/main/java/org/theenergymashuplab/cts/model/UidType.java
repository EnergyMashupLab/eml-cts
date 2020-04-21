package org.theenergymashuplab.cts.model;

import java.util.concurrent.atomic.AtomicLong;

public class UidType {
	private long myUidId = 0;
	private static final AtomicLong uidIdCounter = new AtomicLong();

		UidType(long uidId){
		myUidId = uidId;
		// zero value is for json demarshalling - verify
		if (myUidId != 0) {
		uidIdCounter.set(uidId + 1); 	//to avoid future collisions
	 }

	UidType(){
		myUidId = uidIdCounter.incrementAndGet();
	    }

	public long value() {
		return this.myUidId;
	}

	public toString()	{
				return String.valueOf(this.myUidId);
	}
}
