package org.theenergymashuplab.cts;

import java.util.concurrent.atomic.AtomicLong;

public class UidType {

	public long myUidId = 0;
	private static final AtomicLong uidIdCounter = new AtomicLong();

	UidType(long uidId){
		myUidId = uidIdCounter.addAndGet(uidId);
	    }

	UidType(){
		myUidId = uidIdCounter.incrementAndGet();
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
