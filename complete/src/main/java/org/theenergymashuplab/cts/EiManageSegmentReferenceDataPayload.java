package org.theenergymashuplab.cts;

/**
 * @author crossover
 * @version 1.0
 * @created 26-Feb-2025 11:30:00 AM
 */

public class EiManageSegmentReferenceDataPayload extends EiSubscriptionRequestType {

    public MarketIdType marketId;
    public int segmentId;
    public SubscriptionActionType subscriptionActionRequested;
    public RefIdType subscriptionRequestied;
}
