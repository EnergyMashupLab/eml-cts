/* Generated SBE (Simple Binary Encoding) message codec. */
package org.theenergymashuplab.cts.generated_files;


/**
 * See SubscriptionActionType.java
 */
@SuppressWarnings("all")
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

    /**
     * The raw encoded value in the Java type representation.
     *
     * @return the raw value encoded.
     */
    public short value()
    {
        return value;
    }

    /**
     * Lookup the enum value representing the value.
     *
     * @param value encoded to be looked up.
     * @return the enum value representing the value.
     */
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
