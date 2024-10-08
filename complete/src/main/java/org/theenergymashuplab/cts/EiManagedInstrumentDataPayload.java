package org.theenergymashuplab.cts;

public class EiManagedInstrumentDataPayload {
    private String instrumentId;
    private String instrumentName;
    private String instrumentType;

    // Constructor
    public EiManagedInstrumentDataPayload(String instrumentId, String instrumentName, String instrumentType) {
        this.instrumentId = instrumentId;
        this.instrumentName = instrumentName;
        this.instrumentType = instrumentType;
    }

    // Getter(s)
    public String instrumentId() {
        return instrumentId;
    }
    public String instrumentName() {
        return instrumentName;
    }
    public String instrumentType() {
        return instrumentType;
    }
    
    // Setter(s)
    public void setInstrumentId(String instrumentId) {
        this.instrumentId = instrumentId;
    }
    public void setInstrumentName(String instrumentName) {
        this.instrumentName = instrumentName;
    }
    public void setInstrumentType(String instrumentType) {
        this.instrumentType = instrumentType;
    }
}

