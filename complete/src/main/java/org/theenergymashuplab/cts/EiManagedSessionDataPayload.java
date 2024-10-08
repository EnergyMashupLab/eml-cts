package org.theenergymashuplab.cts;

public class EiManagedSessionDataPayload {
    private String sessionId;
    private String sessionType;

    // Constructor
    public EiManagedSessionDataPayload(String sessionId, String sessionType) {
        this.sessionId = sessionId;
        this.sessionType = sessionType;
    }

    // Getter(s)
    public String getSessionId() {
        return sessionId;
    } 
    
    public String getSessionType() {
        return sessionType;
    } 

    // Setter(s)
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    } 
    public void setSessionType(String sessionType) {
        this.sessionType = sessionType;
    } 
}