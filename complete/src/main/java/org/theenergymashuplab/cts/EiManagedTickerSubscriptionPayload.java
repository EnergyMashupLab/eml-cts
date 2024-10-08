package org.theenergymashuplab.cts;

public class EiManagedTickerSubscriptionPayload {
    private String subscriptionType;
    private long subscriptionDuration;

    // Constructor
    public EiManagedTickerSubscriptionPayload(String subscriptionType, long subscriptionDuration) {
        this.subscriptionType = subscriptionType;
        this.subscriptionDuration = subscriptionDuration;
    }


    // Getter(s)
    public String getSubscriptionType() {
        return subscriptionType;
    }
    public long subscriptionDuration() {
        return subscriptionDuration;
    }

    // Setter(s)
    public void setSubscriptionType(String subscriptionType) {
        this.subscriptionType = subscriptionType;
    } 
    public void setSubscriptionDuration(long subscriptionDuration) {
        this.subscriptionDuration = subscriptionDuration;
    } 
}