package org.theenergymashuplab.cts;

public class MarketOrderIdType extends UidType {

    public MarketOrderIdType(long uidId) {
        super(uidId);
    }

    public MarketOrderIdType() {
    }

    @Override
    public String toString() {
        return "MarketOrderIdType{" +
                "myUidId=" + myUidId +
                '}';
    }
}
