/* Generated SBE (Simple Binary Encoding) message codec. */
package org.theenergymashuplab.cts.generated_files;


/**
 * Boolean Type
 */
public enum BooleanType
{

    /**
     * False value representation.
     */
    FALSE((short)0),


    /**
     * True value representation.
     */
    TRUE((short)1),

    /**
     * To be used to represent not present or null.
     */
    NULL_VAL((short)255);

    private final short value;

    BooleanType(final short value)
    {
        this.value = value;
    }

    public short value()
    {
        return value;
    }

    public static BooleanType get(final short value)
    {
        switch (value)
        {
            case 0: return FALSE;
            case 1: return TRUE;
            case 255: return NULL_VAL;
        }

        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
