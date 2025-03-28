package org.theenergymashuplab.cts;

import java.time.Instant;
import java.util.List;

/**
 * @author crossover
 * @version 1.0
 * @created 18-Feb-2025 11:30:37 AM
 */

public abstract class TenderBase {

    public boolean allOrNone = false;
    public String executionInstructions;
    public Instant expirationTime;
    public MarketIdType marketId;
    public int priceScale;
    public int quantityScale;
    public ResourceDesignatorType resourceDesignator;
    public int segmentId;
    public SideType side;
    public TenderDetail tenderDetail;
    public WarrantIdType warrants;

    public TenderBase() {

    }

    public TenderBase(Instant expirationTime, SideType side, TenderDetail tenderDetail) {
        this.expirationTime = expirationTime;
        this.side = side;
        this.tenderDetail = tenderDetail;
    }

    public boolean isAllOrNone() {
        return allOrNone;
    }

    public void setAllOrNone(boolean allOrNone) {
        this.allOrNone = allOrNone;
    }

    public String getExecutionInstructions() {
        return executionInstructions;
    }

    public void setExecutionInstructions(String executionInstructions) {
        this.executionInstructions = executionInstructions;
    }

    public Instant getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Instant expirationTime) {
        this.expirationTime = expirationTime;
    }

    public MarketIdType getMarketId() {
        return marketId;
    }

    public void setMarketId(MarketIdType marketId) {
        this.marketId = marketId;
    }

    public int getPriceScale() {
        return priceScale;
    }

    public void setPriceScale(int priceScale) {
        this.priceScale = priceScale;
    }

    public int getQuantityScale() {
        return quantityScale;
    }

    public void setQuantityScale(int quantityScale) {
        this.quantityScale = quantityScale;
    }

    public ResourceDesignatorType getResourceDesignator() {
        return resourceDesignator;
    }

    public void setResourceDesignator(ResourceDesignatorType resourceDesignator) {
        this.resourceDesignator = resourceDesignator;
    }

    public int getSegmentId() {
        return segmentId;
    }

    public void setSegmentId(int segmentId) {
        this.segmentId = segmentId;
    }

    public SideType getSide() {
        return side;
    }

    public void setSide(SideType side) {
        this.side = side;
    }

    public TenderDetail getTenderDetail() {
        return tenderDetail;
    }

    public void setTenderDetail(TenderDetail tenderDetail) {
        this.tenderDetail = tenderDetail;
    }

    public WarrantIdType getWarrants() {
        return warrants;
    }

    public void setWarrants(WarrantIdType warrants) {
        this.warrants = warrants;
    }

    @Override
    public String toString() {
        return "TenderBase{" +
                "allOrNone=" + allOrNone +
                ", executionInstructions='" + executionInstructions + '\'' +
                ", expirationTime=" + expirationTime +
                ", marketId=" + marketId +
                ", priceScale=" + priceScale +
                ", quantityScale=" + quantityScale +
                ", resourceDesignator=" + resourceDesignator +
                ", segmentId=" + segmentId +
                ", side=" + side +
                ", tenderDetail=" + tenderDetail +
                ", warrants=" + warrants +
                '}';
    }
}