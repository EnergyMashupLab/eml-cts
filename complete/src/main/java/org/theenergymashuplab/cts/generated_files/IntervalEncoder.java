/* Generated SBE (Simple Binary Encoding) message codec. */
package org.theenergymashuplab.cts.generated_files;

import org.agrona.MutableDirectBuffer;


/**
 * See Interval.java
 */
@SuppressWarnings("all")
public final class IntervalEncoder
{
    public static final int SCHEMA_ID = 1;
    public static final int SCHEMA_VERSION = 2;
    public static final String SEMANTIC_VERSION = "2.1";
    public static final int ENCODED_LENGTH = 24;
    public static final java.nio.ByteOrder BYTE_ORDER = java.nio.ByteOrder.LITTLE_ENDIAN;

    private int offset;
    private MutableDirectBuffer buffer;

    public IntervalEncoder wrap(final MutableDirectBuffer buffer, final int offset)
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

    public static int durationEncodingOffset()
    {
        return 0;
    }

    public static int durationEncodingLength()
    {
        return 12;
    }

    private final DurationEncoder duration = new DurationEncoder();

    /**
     * See java.time.Duration. In seconds (signed) and nanoseconds (unsiqned)
     *
     * @return DurationEncoder : See java.time.Duration. In seconds (signed) and nanoseconds (unsiqned)
     */
    public DurationEncoder duration()
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

    private final InstantEncoder dtStart = new InstantEncoder();

    /**
     * See java.time.Instant. Seconds (signed) and nanoseconds (unsiqned)
     *
     * @return InstantEncoder : See java.time.Instant. Seconds (signed) and nanoseconds (unsiqned)
     */
    public InstantEncoder dtStart()
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

        final IntervalDecoder decoder = new IntervalDecoder();
        decoder.wrap(buffer, offset);

        return decoder.appendTo(builder);
    }
}
