package org.theenergymashuplab.cts;


/**
 * @author crossover
 * @version 1.0
 * @created 28-Sep-2024 8:41:41 PM
 */
public class InstrumentSessionSummaryType {

	public long firstPrice;
	public long highPrice;
	public long lastPrice;
	public long lowPrice;
	public long volume;

	public InstrumentSessionSummaryType(){

	}

	public InstrumentSessionSummaryType(long firstPrice, long highPrice, long lastPrice, long lowPrice, long volume) {
		this.firstPrice = firstPrice;
		this.highPrice = highPrice;
		this.lastPrice = lastPrice;
		this.lowPrice = lowPrice;
		this.volume = volume;
	}

	public long getFirstPrice() {
		return firstPrice;
	}

	public void setFirstPrice(long firstPrice) {
		this.firstPrice = firstPrice;
	}

	public long getVolume() {
		return volume;
	}

	public void setVolume(long volume) {
		this.volume = volume;
	}

	public long getLowPrice() {
		return lowPrice;
	}

	public void setLowPrice(long lowPrice) {
		this.lowPrice = lowPrice;
	}

	public long getLastPrice() {
		return lastPrice;
	}

	public void setLastPrice(long lastPrice) {
		this.lastPrice = lastPrice;
	}

	public long getHighPrice() {
		return highPrice;
	}

	public void setHighPrice(long highPrice) {
		this.highPrice = highPrice;
	}

	@Override
	public String toString() {
		return "InstrumentSessionSummaryType{" +
				"firstPrice=" + firstPrice +
				", highPrice=" + highPrice +
				", lastPrice=" + lastPrice +
				", lowPrice=" + lowPrice +
				", volume=" + volume +
				'}';
	}
}
