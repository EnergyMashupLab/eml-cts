package org.theenergymashuplab.cts;


/**
 * @author crossover
 * @version 1.0
 * @created 28-Sep-2024 8:41:43 PM
 */
public class WarrantIdType extends UidType {

	public WarrantIdType(){

	}

	public WarrantIdType(long uidId) {
		super(uidId);
	}

	@Override
	public String toString() {
		return "WarrantIdType{" +
				"myUidId=" + myUidId +
				'}';
	}
}
