package org.theenergymashuplab.cts;


/**
 * @author crossover
 * @version 1.0
 * @created 28-Sep-2024 8:41:42 PM
 */
public class TenderStreamDetail extends TenderDetail {

	public CtsStreamType stream;
	private BridgeInstant streamStart;
	private long intervalDurationInMinutes;

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

	public BridgeInstant getStreamStart(){
		return this.streamStart;
	}

	public void setStreamStart(BridgeInstant streamStart){
		this.streamStart = streamStart;
	}

	public long getIntervalDurationInMinutes(){
		return this.intervalDurationInMinutes;
	}

	public void setIntervalDurationInMinutes(long intervalDurationInMinutes){
		this.intervalDurationInMinutes = intervalDurationInMinutes;
	}

	@Override
	public String toString() {
		return "TenderStreamDetail{" +
				"stream=" + stream.toString() +
				//"streamStart=" + streamStart.toString() +
				"intervalDurationInMinutes=" + intervalDurationInMinutes +
				"}";
	}
}
