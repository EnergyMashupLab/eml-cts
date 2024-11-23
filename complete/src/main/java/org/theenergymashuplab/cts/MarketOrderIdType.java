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
	public boolean equals(Object obj){
		if(obj == null){
			return false;
		}

		if(obj.getClass() != MarketOrderIdType.class){
			return false;
		}

		return ((MarketOrderIdType)obj).value() == this.value();
	}

    @Override
    public String toString() {
        return "MarketOrderIdType{" +
                "myUidId=" + myUidId +
                '}';
    }
}
