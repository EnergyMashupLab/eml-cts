package org.theenergymashuplab.cts;

public class MarketOrderIdType extends UidType {

    public MarketOrderIdType(long uidId) {
        super(uidId);
    }

    public MarketOrderIdType() {
    }

	/**
	 * This will be used in the explicit case that we want a duplicate
	 */
	public void setMarketOrderId(long uidId){
		super.setMyUidId(uidId);
	}



    @Override
    public String toString() {
        return "MarketOrderIdType{" +
                "myUidId=" + myUidId +
                '}';
    }
}
