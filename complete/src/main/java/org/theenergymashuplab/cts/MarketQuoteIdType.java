package org.theenergymashuplab.cts;


/**
 * @author crossover
 * @version 1.0
 * @created 28-Sep-2024 8:41:41 PM
 */
public class MarketQuoteIdType {

	public String myUid = "";

	public MarketQuoteIdType() {

	}

	public MarketQuoteIdType(String myUid) {
		this.myUid = myUid;
	}

	public String getMyUid() {
		return myUid;
	}

	public void setMyUid(String myUid) {
		this.myUid = myUid;
	}

	@Override
	public String toString() {
		return "MarketQuoteIdType [myUid=" + myUid + "]";
	}
}
