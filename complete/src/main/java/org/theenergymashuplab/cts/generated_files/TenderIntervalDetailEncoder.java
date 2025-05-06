/* Generated SBE (Simple Binary Encoding) message codec. */
package org.theenergymashuplab.cts.generated_files;

import org.agrona.MutableDirectBuffer;


/**
 * See TenderIntervalDetail.java
 */
@SuppressWarnings("all")
public final class TenderIntervalDetailEncoder
{
    public static final int SCHEMA_ID = 1;
    public static final int SCHEMA_VERSION = 2;
    public static final String SEMANTIC_VERSION = "2.1";
    public static final int ENCODED_LENGTH = 40;
    public static final java.nio.ByteOrder BYTE_ORDER = java.nio.ByteOrder.LITTLE_ENDIAN;

    private int offset;
    private MutableDirectBuffer buffer;

    public TenderIntervalDetailEncoder wrap(final MutableDirectBuffer buffer, final int offset)
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

    public static int intervalEncodingOffset()
    {
        return 0;
    }

    public static int intervalEncodingLength()
    {
        return 24;
    }

    private final IntervalEncoder interval = new IntervalEncoder();

    /**
     * See Interval.java
     *
     * @return IntervalEncoder : See Interval.java
     */
    public IntervalEncoder interval()
    {
        interval.wrap(buffer, offset + 0);
        return interval;
    }

    public static int priceEncodingOffset()
    {
        return 24;
    }

    public static int priceEncodingLength()
    {
        return 8;
    }

    public static long priceNullValue()
    {
        return -9223372036854775808L;
    }

    public static long priceMinValue()
    {
        return -9223372036854775807L;
    }

    public static long priceMaxValue()
    {
        return 9223372036854775807L;
    }

    public TenderIntervalDetailEncoder price(final long value)
    {
        buffer.putLong(offset + 24, value, BYTE_ORDER);
        return this;
    }


    public static int quantityEncodingOffset()
    {
        return 32;
    }

    public static int quantityEncodingLength()
    {
        return 8;
    }

    public static long quantityNullValue()
    {
        return -9223372036854775808L;
    }

    public static long quantityMinValue()
    {
        return -9223372036854775807L;
    }

    public static long quantityMaxValue()
    {
        return 9223372036854775807L;
    }

    public TenderIntervalDetailEncoder quantity(final long value)
    {
        buffer.putLong(offset + 32, value, BYTE_ORDER);
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

        final TenderIntervalDetailDecoder decoder = new TenderIntervalDetailDecoder();
        decoder.wrap(buffer, offset);

        return decoder.appendTo(builder);
    }
}
