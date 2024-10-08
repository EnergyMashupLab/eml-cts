package org.theenergymashuplab.cts;


/**
 * @author crossover
 * @version 1.0
 * @created 28-Sep-2024 8:41:42 PM
 */
public class RfqIdType extends UidType {

	public long value() {
		return this.myUidId;
	}

	@Override
	public String toString()	{
		return (String.valueOf(myUidId));
	}
}
