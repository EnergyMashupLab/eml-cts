/* Generated SBE (Simple Binary Encoding) message codec. */
package org.theenergymashuplab.cts.generated_files;

import org.agrona.DirectBuffer;


/**
 * See Interval.java
 */
@SuppressWarnings("all")
public final class IntervalDecoder
{
    public static final int SCHEMA_ID = 1;
    public static final int SCHEMA_VERSION = 2;
    public static final String SEMANTIC_VERSION = "2.1";
    public static final int ENCODED_LENGTH = 24;
    public static final java.nio.ByteOrder BYTE_ORDER = java.nio.ByteOrder.LITTLE_ENDIAN;

    private int offset;
    private DirectBuffer buffer;

    public IntervalDecoder wrap(final DirectBuffer buffer, final int offset)
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

    private final InstantDecoder dtStart = new InstantDecoder();

    /**
     * See java.time.Instant. Seconds (signed) and nanoseconds (unsiqned)
     *
     * @return InstantDecoder : See java.time.Instant. Seconds (signed) and nanoseconds (unsiqned)
     */
    public InstantDecoder dtStart()
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
        final DurationDecoder duration = this.duration();
        if (null != duration)
        {
            duration.appendTo(builder);
        }
        else
        {
            builder.append("null");
        }
        builder.append('|');
        builder.append("dtStart=");
        final InstantDecoder dtStart = this.dtStart();
        if (null != dtStart)
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
