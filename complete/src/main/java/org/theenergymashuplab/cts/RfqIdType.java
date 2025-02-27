package org.theenergymashuplab.cts;


/**
 * @author crossover
 * @version 1.0
 * @created 18-Feb-2025 11:30:37 AM
 */
public class RfqIdType extends UidType {

    public RfqIdType() {

    }

    public long value() {
        return this.myUidId;
    }

    @Override
    public String toString() {
        return "RfqIdType{" +
                "myUidId=" + myUidId +
                '}';
    }
}
