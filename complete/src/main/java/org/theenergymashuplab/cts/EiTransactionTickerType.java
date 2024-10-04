package org.theenergymashuplab.cts;


/**
 * @author crossover
 * @version 1.0
 * @created 28-Sep-2024 8:41:40 PM
 */
public class EiTransactionTickerType {

	public IntervalType interval;
	public String marketContext;
	/**
	 * A long to which the Market or MarketPlace scale factors are applied. High
	 * performance open source market implementations often use Long rather than Fixed
	 * Decimal or Float,
	 */
	public long price;
	public long quantity;
	public ResourceDesignatorType resourceDesignator;
	public InstantType saleTime;
	public SideType side;
	public RefIdType tickerId;

	public EiTransactionTickerType(){

	}

	public EiTransactionTickerType(IntervalType interval, String marketContext, long price, long quantity, ResourceDesignatorType resourceDesignator, InstantType saleTime, SideType side, RefIdType tickerId) {
		this.interval = interval;
		this.marketContext = marketContext;
		this.price = price;
		this.quantity = quantity;
		this.resourceDesignator = resourceDesignator;
		this.saleTime = saleTime;
		this.side = side;
		this.tickerId = tickerId;
	}

	public IntervalType getInterval() {
		return interval;
	}

	public void setInterval(IntervalType interval) {
		this.interval = interval;
	}

	public String getMarketContext() {
		return marketContext;
	}

	public void setMarketContext(String marketContext) {
		this.marketContext = marketContext;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public ResourceDesignatorType getResourceDesignator() {
		return resourceDesignator;
	}

	public void setResourceDesignator(ResourceDesignatorType resourceDesignator) {
		this.resourceDesignator = resourceDesignator;
	}

	public InstantType getSaleTime() {
		return saleTime;
	}

	public void setSaleTime(InstantType saleTime) {
		this.saleTime = saleTime;
	}

	public SideType getSide() {
		return side;
	}

	public void setSide(SideType side) {
		this.side = side;
	}

	public RefIdType getTickerId() {
		return tickerId;
	}

	public void setTickerId(RefIdType tickerId) {
		this.tickerId = tickerId;
	}

	@Override
	public String toString() {
		return "EiTransactionTickerType{" +
				"interval=" + interval +
				", marketContext='" + marketContext + '\'' +
				", price=" + price +
				", quantity=" + quantity +
				", resourceDesignator=" + resourceDesignator +
				", saleTime=" + saleTime +
				", side=" + side +
				", tickerId=" + tickerId +
				'}';
	}
}
