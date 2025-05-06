/* Generated SBE (Simple Binary Encoding) message codec. */
package org.theenergymashuplab.cts.generated_files;


/**
 * See ResourceDesignatorType.java
 */
@SuppressWarnings("all")
public enum ResourceDesignatorType
{
    POWER((short)1),

    ENERGY((short)2),

    TRANSPORT((short)3),

    WATER_PRESSURE((short)4),

    WATER_FLOW((short)5),

    GAS_PRESSURE((short)6),

    GAS_FLOW((short)7),

    BANDWIDTH((short)8),

    /**
     * To be used to represent not present or null.
     */
    NULL_VAL((short)255);

    private final short value;

    ResourceDesignatorType(final short value)
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
    public static ResourceDesignatorType get(final short value)
    {
        switch (value)
        {
            case 1: return POWER;
            case 2: return ENERGY;
            case 3: return TRANSPORT;
            case 4: return WATER_PRESSURE;
            case 5: return WATER_FLOW;
            case 6: return GAS_PRESSURE;
            case 7: return GAS_FLOW;
            case 8: return BANDWIDTH;
            case 255: return NULL_VAL;
        }

        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
