package org.theenergymashuplab.cts;


/**
 * @author crossover
 * @version 1.0
 * @created 28-Sep-2024 8:41:42 PM
 */
public class TenderStreamDetail extends TenderDetail {

	public CtsStreamType stream;

	public TenderStreamDetail(){

	}

	public TenderStreamDetail(CtsStreamType stream) {
		this.stream = stream;
	}

	public CtsStreamType getStream() {
		return stream;
	}

	public void setStream(CtsStreamType stream) {
		this.stream = stream;
	}

	@Override
	public String toString() {
		return "TenderStreamDetail{" +
				"stream=" + stream +
				"}";
	}
}
