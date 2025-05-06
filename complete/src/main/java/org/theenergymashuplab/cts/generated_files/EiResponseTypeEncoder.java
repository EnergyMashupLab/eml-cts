/* Generated SBE (Simple Binary Encoding) message codec. */
package org.theenergymashuplab.cts.generated_files;

import org.agrona.MutableDirectBuffer;


/**
 * See EiResponseType.java
 */
@SuppressWarnings("all")
public final class EiResponseTypeEncoder
{
    public static final int SCHEMA_ID = 1;
    public static final int SCHEMA_VERSION = 2;
    public static final String SEMANTIC_VERSION = "2.1";
    public static final int ENCODED_LENGTH = 29;
    public static final java.nio.ByteOrder BYTE_ORDER = java.nio.ByteOrder.LITTLE_ENDIAN;

    private int offset;
    private MutableDirectBuffer buffer;

    public EiResponseTypeEncoder wrap(final MutableDirectBuffer buffer, final int offset)
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

    public static int createdDateTimeEncodingOffset()
    {
        return 0;
    }

    public static int createdDateTimeEncodingLength()
    {
        return 12;
    }

    private final InstantEncoder createdDateTime = new InstantEncoder();

    /**
     * See java.time.Instant. Seconds (signed) and nanoseconds (unsiqned)
     *
     * @return InstantEncoder : See java.time.Instant. Seconds (signed) and nanoseconds (unsiqned)
     */
    public InstantEncoder createdDateTime()
    {
        createdDateTime.wrap(buffer, offset + 0);
        return createdDateTime;
    }

    public static int inResponseToEncodingOffset()
    {
        return 12;
    }

    public static int inResponseToEncodingLength()
    {
        return 8;
    }

    public static long inResponseToNullValue()
    {
        return 0xffffffffffffffffL;
    }

    public static long inResponseToMinValue()
    {
        return 0x0L;
    }

    public static long inResponseToMaxValue()
    {
        return 0xfffffffffffffffeL;
    }

    public EiResponseTypeEncoder inResponseTo(final long value)
    {
        buffer.putLong(offset + 12, value, BYTE_ORDER);
        return this;
    }


    public static int responseCodeEncodingOffset()
    {
        return 20;
    }

    public static int responseCodeEncodingLength()
    {
        return 8;
    }

    public static long responseCodeNullValue()
    {
        return 0xffffffffffffffffL;
    }

    public static long responseCodeMinValue()
    {
        return 0x0L;
    }

    public static long responseCodeMaxValue()
    {
        return 0xfffffffffffffffeL;
    }

    public EiResponseTypeEncoder responseCode(final long value)
    {
        buffer.putLong(offset + 20, value, BYTE_ORDER);
        return this;
    }


    public static int responseDetailEncodingOffset()
    {
        return 28;
    }

    public static int responseDetailEncodingLength()
    {
        return 1;
    }

    public EiResponseTypeEncoder responseDetail(final ResponseDetailType value)
    {
        buffer.putByte(offset + 28, (byte)value.value());
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

        final EiResponseTypeDecoder decoder = new EiResponseTypeDecoder();
        decoder.wrap(buffer, offset);

        return decoder.appendTo(builder);
    }
}
