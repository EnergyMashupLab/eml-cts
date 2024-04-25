package org.theenergymashuplab.cts;

public class MarketIdType extends UidType {
	@Override
	public long value() {
		/* TODO Currently, we only use a single market and order book
		 * so we assume its ID to be 1 */
		return 1;
	}
}
