/* Generated SBE (Simple Binary Encoding) message codec. */
package org.theenergymashuplab.cts.generated_files;


/**
 * See ResponseDetailType.java
 */
@SuppressWarnings("all")
public enum ResponseDetailType
{
    UNSPECIFIED((short)1),

    RULES_VIOLATION((short)2),

    INVALID_REFERENCE((short)3),

    DUPLICATE((short)4),

    TRADING_CLOSED((short)5),

    PARTY_RESTRICTED((short)6),

    INVALID_INSTRUMENT((short)7),

    FORCE_MAJEURE((short)8),

    INVALID_MARKET((short)9),

    INVALID_SEGMENT((short)10),

    SUCCESS((short)11),

    NOT_AUTHORIZED((short)12),

    INVALID_ARTIFACT((short)13),

    /**
     * To be used to represent not present or null.
     */
    NULL_VAL((short)255);

    private final short value;

    ResponseDetailType(final short value)
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
    public static ResponseDetailType get(final short value)
    {
        switch (value)
        {
            case 1: return UNSPECIFIED;
            case 2: return RULES_VIOLATION;
            case 3: return INVALID_REFERENCE;
            case 4: return DUPLICATE;
            case 5: return TRADING_CLOSED;
            case 6: return PARTY_RESTRICTED;
            case 7: return INVALID_INSTRUMENT;
            case 8: return FORCE_MAJEURE;
            case 9: return INVALID_MARKET;
            case 10: return INVALID_SEGMENT;
            case 11: return SUCCESS;
            case 12: return NOT_AUTHORIZED;
            case 13: return INVALID_ARTIFACT;
            case 255: return NULL_VAL;
        }

        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
