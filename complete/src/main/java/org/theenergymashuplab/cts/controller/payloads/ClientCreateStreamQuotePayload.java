package org.theenergymashuplab.cts.controller.payloads;

import org.theenergymashuplab.cts.BridgeInstant;
import org.theenergymashuplab.cts.CtsStreamIntervalType;
import org.theenergymashuplab.cts.SideType;

import java.util.List;

public class ClientCreateStreamQuotePayload {
    private String info = "ClientCreateStreamQuotePayload";
    // Side that we are on
    private SideType side;
    private long ctsQuoteId;
    //A list of our streamIntervals
    private List<CtsStreamIntervalType> streamIntervals;
    private BridgeInstant streamStart;
    private BridgeInstant bridgeExpireTime;
    private long intervalDurationInMinutes;



    public ClientCreateStreamQuotePayload() {
    }

    public ClientCreateStreamQuotePayload(SideType side, long ctsQuoteId, List<CtsStreamIntervalType> streamIntervals, BridgeInstant streamStart, BridgeInstant bridgeExpireTime, long intervalDurationInMinutes) {
        this.side = side;
        this.ctsQuoteId = ctsQuoteId;
        this.streamIntervals = streamIntervals;
        this.streamStart = streamStart;
        this.bridgeExpireTime = bridgeExpireTime;
        this.intervalDurationInMinutes = intervalDurationInMinutes;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public SideType getSide() {
        return side;
    }

    public void setSide(SideType side) {
        this.side = side;
    }

    public long getCtsQuoteId() {
        return ctsQuoteId;
    }

    public void setCtsQuoteId(long ctsQuoteId) {
        this.ctsQuoteId = ctsQuoteId;
    }

    public List<CtsStreamIntervalType> getStreamIntervals() {
        return streamIntervals;
    }

    public void setStreamIntervals(List<CtsStreamIntervalType> streamIntervals) {
        this.streamIntervals = streamIntervals;
    }

    public BridgeInstant getStreamStart() {
        return streamStart;
    }

    public void setStreamStart(BridgeInstant streamStart) {
        this.streamStart = streamStart;
    }

    public BridgeInstant getBridgeExpireTime() {
        return bridgeExpireTime;
    }

    public void setBridgeExpireTime(BridgeInstant bridgeExpireTime) {
        this.bridgeExpireTime = bridgeExpireTime;
    }

    public long getIntervalDurationInMinutes() {
        return intervalDurationInMinutes;
    }

    public void setIntervalDurationInMinutes(long intervalDurationInMinutes) {
        this.intervalDurationInMinutes = intervalDurationInMinutes;
    }

    @Override
    public String toString() {
        return "ClientCreateStreamQuotePayload{" +
                "info='" + info + '\'' +
                ", side=" + side +
                ", ctsQuoteId=" + ctsQuoteId +
                ", streamIntervals=" + streamIntervals +
                ", streamStart=" + streamStart +
                ", bridgeExpireTime=" + bridgeExpireTime +
                ", intervalDurationInMinutes=" + intervalDurationInMinutes +
                '}';
    }
}