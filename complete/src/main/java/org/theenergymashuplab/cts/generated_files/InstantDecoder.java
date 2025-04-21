/* Generated SBE (Simple Binary Encoding) message codec. */
package org.theenergymashuplab.cts.generated_files;

import org.agrona.DirectBuffer;


/**
 * See java.time.Instant. Seconds (signed) and nanoseconds (unsiqned)
 */
@SuppressWarnings("all")
public class InstantDecoder
{
    public static final int SCHEMA_ID = 1;
    public static final int SCHEMA_VERSION = 2;
    public static final int ENCODED_LENGTH = 12;
    public static final java.nio.ByteOrder BYTE_ORDER = java.nio.ByteOrder.LITTLE_ENDIAN;

    private int offset;
    private DirectBuffer buffer;

    public InstantDecoder wrap(final DirectBuffer buffer, final int offset)
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

    public static int secondsEncodingOffset()
    {
        return 0;
    }

    public static int secondsEncodingLength()
    {
        return 8;
    }

    public static int secondsSinceVersion()
    {
        return 0;
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

    public long seconds()
    {
        return buffer.getLong(offset + 0, java.nio.ByteOrder.LITTLE_ENDIAN);
    }


    public static int nanoEncodingOffset()
    {
        return 8;
    }

    public static int nanoEncodingLength()
    {
        return 4;
    }

    public static int nanoSinceVersion()
    {
        return 0;
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

    public long nano()
    {
        return (buffer.getInt(offset + 8, java.nio.ByteOrder.LITTLE_ENDIAN) & 0xFFFF_FFFFL);
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
        builder.append("seconds=");
        builder.append(seconds());
        builder.append('|');
        builder.append("nano=");
        builder.append(nano());
        builder.append(')');

        return builder;
    }
}
