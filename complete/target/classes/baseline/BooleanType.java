/* Generated SBE (Simple Binary Encoding) message codec. */
package baseline;


/**
 * Boolean Type
 */
public enum BooleanType
{

    /**
     * False value representation.
     */
    F((short)0),


    /**
     * True value representation.
     */
    T((short)1),

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
            case 0: return F;
            case 1: return T;
            case 255: return NULL_VAL;
        }

        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
