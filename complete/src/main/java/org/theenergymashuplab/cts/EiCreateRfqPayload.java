public class EiCreateRfqPayload {
    // Private fields
    private String rfqId;
    private String instrumentId;
    private int quantity;
    private double targetPrice;
    private String requestorId;
    private String expirationTime;

    // Constructor
    public EiCreateRfqPayload(String rfqId, String instrumentId, int quantity, double targetPrice, String requestorId, String expirationTime) {
        this.rfqId = rfqId;
        this.instrumentId = instrumentId;
        this.quantity = quantity;
        this.targetPrice = targetPrice;
        this.requestorId = requestorId;
        this.expirationTime = expirationTime;
    }

    // Getter(s)
    public String getRfqId() {
        return rfqId;
    }
    public String getInstrumentId() {
        return instrumentId;
    }
    public int getQuantity() {
        return quantity;
    }
    public double getTargetPrice() {
        return targetPrice;
    }
    public String getRequestorId() {
        return requestorId;
    }
    public String getExpirationTime() {
        return expirationTime;
    }

    // Setter(s)
    public void setRfqId(String rfqId) {
        this.rfqId = rfqId;
    }

    public void setInstrumentId(String instrumentId) {
        this.instrumentId = instrumentId;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setTargetPrice(double targetPrice) {
        this.targetPrice = targetPrice;
    }
    public void setRequestorId(String requestorId) {
        this.requestorId = requestorId;
    }
    public void setExpirationTime(String expirationTime) {
        this.expirationTime = expirationTime;
    }
}
