package org.theenergymashuplab.cts;

public class EiManagedSegmentReferenceDataPayload {
    private String segmentId;
    private String segmentName;
    private String segmentDescription;

    // Constructor
    public EiManagedSegmentReferenceDataPayload(String segmentId, String segmentName, String segmentDescription, String segmentType) {
        this.segmentId = segmentId;
        this.segmentName = segmentName;
        this.segmentDescription = segmentDescription;
    }

    // Getter(s)
    public String getSegmentId() {
        return segmentId;
    }
    public String getSegmentName() {
        return segmentName;
    }
    public String getSegmentDescription() {
        return segmentDescription;
    }
    
    // Setter(s)
    public void setSegmentId(String segmentId) {
        this.segmentId = segmentId;
    }
    public void setSegmentName(String segmentName) {
        this.segmentName = segmentName;
    }
    public void setSegmentDescription(String segmentDescription) {
        this.segmentDescription = segmentDescription;
    }
}

