/* Generated SBE (Simple Binary Encoding) message codec. */
package org.theenergymashuplab.cts.generated_files;

import org.agrona.MutableDirectBuffer;


/**
 * See java.time.Duration. In seconds (signed) and nanoseconds (unsiqned)
 */
@SuppressWarnings("all")
public final class DurationEncoder
{
    public static final int SCHEMA_ID = 1;
    public static final int SCHEMA_VERSION = 2;
    public static final String SEMANTIC_VERSION = "2.1";
    public static final int ENCODED_LENGTH = 12;
    public static final java.nio.ByteOrder BYTE_ORDER = java.nio.ByteOrder.LITTLE_ENDIAN;

    private int offset;
    private MutableDirectBuffer buffer;

    public DurationEncoder wrap(final MutableDirectBuffer buffer, final int offset)
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

    public static int secondsEncodingOffset()
    {
        return 0;
    }

    public static int secondsEncodingLength()
    {
        return 8;
    }

    public static long secondsNullValue()
    {
        return -9223372036854775808L;
    }

    public static long secondsMinValue()
    {
        return -9223372036854775807L;
    }

    public static long secondsMaxValue()
    {
        return 9223372036854775807L;
    }

    public DurationEncoder seconds(final long value)
    {
        buffer.putLong(offset + 0, value, BYTE_ORDER);
        return this;
    }


    public static int nanoEncodingOffset()
    {
        return 8;
    }

    public static int nanoEncodingLength()
    {
        return 4;
    }

    public static long nanoNullValue()
    {
        return 4294967295L;
    }

    public static long nanoMinValue()
    {
        return 0L;
    }

    public static long nanoMaxValue()
    {
        return 4294967294L;
    }

    public DurationEncoder nano(final long value)
    {
        buffer.putInt(offset + 8, (int)value, BYTE_ORDER);
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

        final DurationDecoder decoder = new DurationDecoder();
        decoder.wrap(buffer, offset);

        return decoder.appendTo(builder);
    }
}
