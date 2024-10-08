package org.theenergymashuplab.cts;

public class EiManagedMarketReferenceDataPayload {
    private String marketId;
    private String marketName;
    private String marketType;

    // Constructor
    public EiManagedMarketReferenceDataPayload(String marketId, String marketName, String marketType) {
        this.marketId = marketId;
        this.marketName = marketName;
        this.marketType = marketType;
    }
    
    // Getter(s)
    public String getMarketId() {
        return marketId;
    }
    public String getMarketName() {
        return marketName;
    }
    public String getMarketType() {
        return marketType;
    }
    
    // Setter(s)
    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }
    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }
    public void setMarketType(String marketType) {
        this.marketType = marketType;
    }
}
