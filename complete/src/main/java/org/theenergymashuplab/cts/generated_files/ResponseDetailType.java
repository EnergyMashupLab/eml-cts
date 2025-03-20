/* Generated SBE (Simple Binary Encoding) message codec. */
package org.theenergymashuplab.cts.generated_files;


/**
 * See ResponseDetailType.java
 */
public enum ResponseDetailType
{
    Unspecified((short)1),

    RulesViolation((short)2),

    InvalidReference((short)3),

    Duplicate((short)4),

    TradingClosed((short)5),

    PartyRestricted((short)6),

    InvalidInstrument((short)7),

    ForceMajeure((short)8),

    InvalidMarket((short)9),

    InvalidSegment((short)10),

    Success((short)11),

    NotAuthorized((short)12),

    InvalidArtifact((short)13),

    /**
     * To be used to represent not present or null.
     */
    NULL_VAL((short)255);

    private final short value;

    ResponseDetailType(final short value)
    {
        this.value = value;
    }

    public short value()
    {
        return value;
    }

    public static ResponseDetailType get(final short value)
    {
        switch (value)
        {
            case 1: return Unspecified;
            case 2: return RulesViolation;
            case 3: return InvalidReference;
            case 4: return Duplicate;
            case 5: return TradingClosed;
            case 6: return PartyRestricted;
            case 7: return InvalidInstrument;
            case 8: return ForceMajeure;
            case 9: return InvalidMarket;
            case 10: return InvalidSegment;
            case 11: return Success;
            case 12: return NotAuthorized;
            case 13: return InvalidArtifact;
            case 255: return NULL_VAL;
        }

        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
