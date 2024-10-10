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
}
