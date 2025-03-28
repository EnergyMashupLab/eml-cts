package org.theenergymashuplab.cts;


/**
 * @author crossover
 * @version 1.0
 * @created 18-Feb-2025 11:30:37 AM
 */
public class SubscriptionIdType extends UidType {

    public SubscriptionIdType() {

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
    public int hashCode() {
        return (int) super.getMyUidId();
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (other.getClass() != SubscriptionIdType.class) {
            return false;
        }

        return ((SubscriptionIdType) other).hashCode() == this.hashCode();
    }
}
