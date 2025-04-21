/* Generated SBE (Simple Binary Encoding) message codec. */
package org.theenergymashuplab.cts.generated_files;


/**
 * See TickerType.java
 */
public enum TickerType
{
    QUOTES((short)1),

    RFQS((short)2),

    TENDERS((short)3),

    TRANSACTIONS((short)4),

    /**
     * To be used to represent not present or null.
     */
    NULL_VAL((short)255);

    private final short value;

    TickerType(final short value)
    {
        this.value = value;
    }

    public short value()
    {
        return value;
    }

    public static TickerType get(final short value)
    {
        switch (value)
        {
            case 1: return QUOTES;
            case 2: return RFQS;
            case 3: return TENDERS;
            case 4: return TRANSACTIONS;
            case 255: return NULL_VAL;
        }

        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
