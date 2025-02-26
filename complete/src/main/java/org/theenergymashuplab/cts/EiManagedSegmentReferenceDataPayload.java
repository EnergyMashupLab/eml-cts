package org.theenergymashuplab.cts;

/**
 * @author crossover
 * @version 1.0
 * @created 26-Feb-2025 11:30:00 AM
 */

public class EiManagedSegmentReferenceDataPayload extends EiSubscriptionResponseType{
    public String multicasetListenReference;
    public EiResponseType response;
    public SubscriptionActionType subscribtionActionTaken;
    public RefIdType subscriptionRequestedId;
}
