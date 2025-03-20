/* Generated SBE (Simple Binary Encoding) message codec. */
package org.theenergymashuplab.cts.generated_files;

import org.agrona.MutableDirectBuffer;


/**
 * See TenderBase.java
 */
@SuppressWarnings("all")
public class TenderBaseEncoder
{
    public static final int SCHEMA_ID = 1;
    public static final int SCHEMA_VERSION = 2;
    public static final int ENCODED_LENGTH = 87;
    public static final java.nio.ByteOrder BYTE_ORDER = java.nio.ByteOrder.LITTLE_ENDIAN;

    private int offset;
    private MutableDirectBuffer buffer;

    public TenderBaseEncoder wrap(final MutableDirectBuffer buffer, final int offset)
    {
        if (buffer != this.buffer)
        {
            this.buffer = buffer;
        }
        this.offset = offset;

        return this;
    }

    public MutableDirectBuffer buffer()
    {
        return buffer;
    }

    public int offset()
    {
        return offset;
    }

    public int encodedLength()
    {
        return ENCODED_LENGTH;
    }

    public int sbeSchemaId()
    {
        return SCHEMA_ID;
    }

    public int sbeSchemaVersion()
    {
        return SCHEMA_VERSION;
    }

    public static int allOrNoneEncodingOffset()
    {
        return 0;
    }

    public static int allOrNoneEncodingLength()
    {
        return 1;
    }

    public TenderBaseEncoder allOrNone(final BooleanType value)
    {
        buffer.putByte(offset + 0, (byte)value.value());
        return this;
    }

    public static int executionInstructionsEncodingOffset()
    {
        return 1;
    }

    public static int executionInstructionsEncodingLength()
    {
        return 4;
    }

    private final VarStringEncodingEncoder executionInstructions = new VarStringEncodingEncoder();

    /**
     * Variable length UTF-8 String.
     *
     * @return VarStringEncodingEncoder : Variable length UTF-8 String.
     */
    public VarStringEncodingEncoder executionInstructions()
    {
        executionInstructions.wrap(buffer, offset + 1);
        return executionInstructions;
    }

    public static int expirationTimeEncodingOffset()
    {
        return 5;
    }

    public static int expirationTimeEncodingLength()
    {
        return 12;
    }

    private final InstantTypeEncoder expirationTime = new InstantTypeEncoder();

    /**
     * See java.time.Instant. Seconds (signed) and nanoseconds (unsiqned)
     *
     * @return InstantTypeEncoder : See java.time.Instant. Seconds (signed) and nanoseconds (unsiqned)
     */
    public InstantTypeEncoder expirationTime()
    {
        expirationTime.wrap(buffer, offset + 5);
        return expirationTime;
    }

    public static int marketIdEncodingOffset()
    {
        return 17;
    }

    public static int marketIdEncodingLength()
    {
        return 8;
    }

    public static long marketIdNullValue()
    {
        return 0xffffffffffffffffL;
    }

    public static long marketIdMinValue()
    {
        return 0x0L;
    }

    public static long marketIdMaxValue()
    {
        return 0xfffffffffffffffeL;
    }

    public TenderBaseEncoder marketId(final long value)
    {
        buffer.putLong(offset + 17, value, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
    }


    public static int priceScaleEncodingOffset()
    {
        return 25;
    }

    public static int priceScaleEncodingLength()
    {
        return 4;
    }

    public static long priceScaleNullValue()
    {
        return 4294967295L;
    }

    public static long priceScaleMinValue()
    {
        return 0L;
    }

    public static long priceScaleMaxValue()
    {
        return 4294967294L;
    }

    public TenderBaseEncoder priceScale(final long value)
    {
        buffer.putInt(offset + 25, (int)value, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
    }


    public static int quantityScaleEncodingOffset()
    {
        return 29;
    }

    public static int quantityScaleEncodingLength()
    {
        return 4;
    }

    public static long quantityScaleNullValue()
    {
        return 4294967295L;
    }

    public static long quantityScaleMinValue()
    {
        return 0L;
    }

    public static long quantityScaleMaxValue()
    {
        return 4294967294L;
    }

    public TenderBaseEncoder quantityScale(final long value)
    {
        buffer.putInt(offset + 29, (int)value, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
    }


    public static int resourceDesignatorEncodingOffset()
    {
        return 33;
    }

    public static int resourceDesignatorEncodingLength()
    {
        return 1;
    }

    public TenderBaseEncoder resourceDesignator(final ResourceDesignatorType value)
    {
        buffer.putByte(offset + 33, (byte)value.value());
        return this;
    }

    public static int segmentIdEncodingOffset()
    {
        return 34;
    }

    public static int segmentIdEncodingLength()
    {
        return 4;
    }

    public static long segmentIdNullValue()
    {
        return 4294967295L;
    }

    public static long segmentIdMinValue()
    {
        return 0L;
    }

    public static long segmentIdMaxValue()
    {
        return 4294967294L;
    }

    public TenderBaseEncoder segmentId(final long value)
    {
        buffer.putInt(offset + 34, (int)value, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
    }


    public static int sideEncodingOffset()
    {
        return 38;
    }

    public static int sideEncodingLength()
    {
        return 1;
    }

    public TenderBaseEncoder side(final SideType value)
    {
        buffer.putByte(offset + 38, value.value());
        return this;
    }

    public static int tenderDetailEncodingOffset()
    {
        return 39;
    }

    public static int tenderDetailEncodingLength()
    {
        return 40;
    }

    private final TenderIntervalDetailEncoder tenderDetail = new TenderIntervalDetailEncoder();

    /**
     * See TenderIntervalDetail.java
     *
     * @return TenderIntervalDetailEncoder : See TenderIntervalDetail.java
     */
    public TenderIntervalDetailEncoder tenderDetail()
    {
        tenderDetail.wrap(buffer, offset + 39);
        return tenderDetail;
    }

    public static int warrantsEncodingOffset()
    {
        return 79;
    }

    public static int warrantsEncodingLength()
    {
        return 8;
    }

    public static long warrantsNullValue()
    {
        return 0xffffffffffffffffL;
    }

    public static long warrantsMinValue()
    {
        return 0x0L;
    }

    public static long warrantsMaxValue()
    {
        return 0xfffffffffffffffeL;
    }

    public TenderBaseEncoder warrants(final long value)
    {
        buffer.putLong(offset + 79, value, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
    }


    public String toString()
    {
        if (null == buffer)
        {
            return "";
        }

        return appendTo(new StringBuilder()).toString();
    }

    public StringBuilder appendTo(final StringBuilder builder)
    {
        if (null == buffer)
        {
            return builder;
        }

        final TenderBaseDecoder decoder = new TenderBaseDecoder();
        decoder.wrap(buffer, offset);

        return decoder.appendTo(builder);
    }
}
