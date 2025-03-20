/* Generated SBE (Simple Binary Encoding) message codec. */
package org.theenergymashuplab.cts.generated_files;

import org.agrona.DirectBuffer;


/**
 * See IntervaTypel.java
 */
@SuppressWarnings("all")
public class IntervalTypeDecoder
{
    public static final int SCHEMA_ID = 1;
    public static final int SCHEMA_VERSION = 2;
    public static final int ENCODED_LENGTH = 24;
    public static final java.nio.ByteOrder BYTE_ORDER = java.nio.ByteOrder.LITTLE_ENDIAN;

    private int offset;
    private DirectBuffer buffer;

    public IntervalTypeDecoder wrap(final DirectBuffer buffer, final int offset)
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

    public static int durationEncodingOffset()
    {
        return 0;
    }

    public static int durationEncodingLength()
    {
        return 12;
    }

    public static int durationSinceVersion()
    {
        return 0;
    }

    private final DurationDecoder duration = new DurationDecoder();

    /**
     * See java.time.Duration. In seconds (signed) and nanoseconds (unsiqned)
     *
     * @return DurationDecoder : See java.time.Duration. In seconds (signed) and nanoseconds (unsiqned)
     */
    public DurationDecoder duration()
    {
        duration.wrap(buffer, offset + 0);
        return duration;
    }

    public static int dtStartEncodingOffset()
    {
        return 12;
    }

    public static int dtStartEncodingLength()
    {
        return 12;
    }

    public static int dtStartSinceVersion()
    {
        return 0;
    }

    private final InstantTypeDecoder dtStart = new InstantTypeDecoder();

    /**
     * See java.time.Instant. Seconds (signed) and nanoseconds (unsiqned)
     *
     * @return InstantTypeDecoder : See java.time.Instant. Seconds (signed) and nanoseconds (unsiqned)
     */
    public InstantTypeDecoder dtStart()
    {
        dtStart.wrap(buffer, offset + 12);
        return dtStart;
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
        builder.append("duration=");
        final DurationDecoder duration = duration();
        if (duration != null)
        {
            duration.appendTo(builder);
        }
        else
        {
            builder.append("null");
        }
        builder.append('|');
        builder.append("dtStart=");
        final InstantTypeDecoder dtStart = dtStart();
        if (dtStart != null)
        {
            dtStart.appendTo(builder);
        }
        else
        {
            builder.append("null");
        }
        builder.append(')');

        return builder;
    }
}
