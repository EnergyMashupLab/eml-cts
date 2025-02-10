package org.theenergymashuplab.cts;


/**
 * @author crossover
 * @version 1.0
 * @created 28-Sep-2024 8:41:42 PM
 */
public class SubscriptionIdType extends UidType {

	public SubscriptionIdType(){

	}

	public SubscriptionIdType(long uidId) {
		super(uidId);
	}

	@Override
	public String toString() {
		return "SubscriptionIdType{" +
				"myUidId=" + myUidId +
				'}';
	}

	@Override
	public int hashCode(){
		return (int)super.getMyUidId();
	}

	@Override
	public boolean equals(Object other){
		if(other == null){
			return false;
		}

		if(other.getClass() != SubscriptionIdType.class){
			return false;
		}

		return ((SubscriptionIdType)other).hashCode() == this.hashCode();
	}
}
