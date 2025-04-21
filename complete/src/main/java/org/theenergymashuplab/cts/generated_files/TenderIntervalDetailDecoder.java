/* Generated SBE (Simple Binary Encoding) message codec. */
package org.theenergymashuplab.cts.generated_files;

import org.agrona.DirectBuffer;


/**
 * See TenderIntervalDetail.java
 */
@SuppressWarnings("all")
public class TenderIntervalDetailDecoder
{
    public static final int SCHEMA_ID = 1;
    public static final int SCHEMA_VERSION = 2;
    public static final int ENCODED_LENGTH = 40;
    public static final java.nio.ByteOrder BYTE_ORDER = java.nio.ByteOrder.LITTLE_ENDIAN;

    private int offset;
    private DirectBuffer buffer;

    public TenderIntervalDetailDecoder wrap(final DirectBuffer buffer, final int offset)
    {
        if (buffer != this.buffer)
        {
            this.buffer = buffer;
        }
        this.offset = offset;

        return this;
    }

    public DirectBuffer buffer()
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

    public static int intervalSinceVersion()
    {
        return 0;
    }

    private final IntervalDecoder interval = new IntervalDecoder();

    /**
     * See Interval.java
     *
     * @return IntervalDecoder : See Interval.java
     */
    public IntervalDecoder interval()
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

    public static int priceSinceVersion()
    {
        return 0;
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

    public long price()
    {
        return buffer.getLong(offset + 24, java.nio.ByteOrder.LITTLE_ENDIAN);
    }


    public static int quantityEncodingOffset()
    {
        return 32;
    }

    public static int quantityEncodingLength()
    {
        return 8;
    }

    public static int quantitySinceVersion()
    {
        return 0;
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

    public long quantity()
    {
        return buffer.getLong(offset + 32, java.nio.ByteOrder.LITTLE_ENDIAN);
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

        builder.append('(');
        builder.append("interval=");
        final IntervalDecoder interval = interval();
        if (interval != null)
        {
            interval.appendTo(builder);
        }
        else
        {
            builder.append("null");
        }
        builder.append('|');
        builder.append("price=");
        builder.append(price());
        builder.append('|');
        builder.append("quantity=");
        builder.append(quantity());
        builder.append(')');

        return builder;
    }
}
