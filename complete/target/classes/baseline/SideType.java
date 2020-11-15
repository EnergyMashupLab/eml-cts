/* Generated SBE (Simple Binary Encoding) message codec. */
package baseline;


/**
 * see SideType.java
 */
public enum SideType
{

    /**
     * Buy side
     */
    B((byte)66),


    /**
     * Sell side
     */
    S((byte)83),

    /**
     * To be used to represent not present or null.
     */
    NULL_VAL((byte)0);

    private final byte value;

    SideType(final byte value)
    {
        this.value = value;
    }

    public byte value()
    {
        return value;
    }

    public static SideType get(final byte value)
    {
        switch (value)
        {
            case 66: return B;
            case 83: return S;
            case 0: return NULL_VAL;
        }

        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
