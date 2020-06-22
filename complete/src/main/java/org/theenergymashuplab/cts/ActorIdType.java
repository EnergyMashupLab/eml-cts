package org.theenergymashuplab.cts;

public class ActorIdType extends UidType {
	
	public long value() {
		return this.myUidId;
	}
	
	@Override
	public String toString()	{
		return (String.valueOf(myUidId));
	}
}
