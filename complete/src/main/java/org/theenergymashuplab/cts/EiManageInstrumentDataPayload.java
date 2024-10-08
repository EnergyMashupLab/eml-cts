package org.theenergymashuplab.cts;

public class EiManageInstrumentDataPayload {

    private String instrumentId;
    private String instrumentType;
    private double instrumentValue;

    // Constructor
    public EiManageInstrumentDataPayload(String instrumentId, String instrumentType, double instrumentValue) {
        this.instrumentId       = instrumentId;
        this.instrumentType     = instrumentType;
        this.instrumentValue    = instrumentValue;
    }

    // Getter(s)
    public String getInstrumentId() {
        return instrumentId;
    }
    public String getInstrumentType() {
        return instrumentType;
    }
    public double getInstrumentValue() {
        return instrumentValue;
    }

    // Setter(s)
    public void setInstrumentID(String instrumentId) {
        this.instrumentId = instrumentId;
    } 
    public void setInstrumentType(String instrumentType) {
        this.instrumentType = instrumentType;
    } 
    public void setInstrumentValue(double instrumentValue) {
        this.instrumentValue = instrumentValue;
    } 
}
