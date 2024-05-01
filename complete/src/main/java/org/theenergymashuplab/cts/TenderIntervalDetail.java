package org.theenergymashuplab.cts;

public class TenderIntervalDetail extends TenderDetail {
	private Interval interval;
	private long price;
	private long quantity;
	
	public TenderIntervalDetail(Interval interval, long price, long quantity) {
		this.interval = interval;
		this.price = price;
		this.quantity = quantity;
	}

	public Interval getInterval() {
		return interval;
	}

	public void setInterval(Interval interval) {
		this.interval = interval;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "TenderIntervalDetail [interval=" + interval + ", price=" + price + ", quantity=" + quantity + "]";
	}
}
