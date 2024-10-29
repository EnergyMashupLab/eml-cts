package org.theenergymashuplab.cts;

public class QuoteStreamDetail extends QuoteDetail {

    public CtsStreamType stream;
    private BridgeInstant streamStart;
    private long intervalDurationInMinutes;

    public QuoteStreamDetail(){
    }

    public QuoteStreamDetail(CtsStreamType stream) {
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
        return "QuoteStreamDetail{" +
                "stream=" + stream+
                //"streamStart=" + streamStart.toString() +
                "intervalDurationInMinutes=" + intervalDurationInMinutes +
                "}";
    }
}
