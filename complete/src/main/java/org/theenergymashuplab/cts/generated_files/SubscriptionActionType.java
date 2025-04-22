/* Generated SBE (Simple Binary Encoding) message codec. */
package org.theenergymashuplab.cts.generated_files;


/**
 * See SubscriptionActionType.java
 */
public enum SubscriptionActionType
{
    SNAPSHOT((short)1),

    SNAPSHOT_AND_UPDATES((short)2),

    CANCEL((short)3),

    /**
     * To be used to represent not present or null.
     */
    NULL_VAL((short)255);

    private final short value;

    SubscriptionActionType(final short value)
    {
        this.value = value;
    }

    public short value()
    {
        return value;
    }

    public static SubscriptionActionType get(final short value)
    {
        switch (value)
        {
            case 1: return SNAPSHOT;
            case 2: return SNAPSHOT_AND_UPDATES;
            case 3: return CANCEL;
            case 255: return NULL_VAL;
        }

        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
