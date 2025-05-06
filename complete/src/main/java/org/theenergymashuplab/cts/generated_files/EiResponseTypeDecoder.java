/* Generated SBE (Simple Binary Encoding) message codec. */
package org.theenergymashuplab.cts.generated_files;

import org.agrona.DirectBuffer;


/**
 * See EiResponseType.java
 */
@SuppressWarnings("all")
public final class EiResponseTypeDecoder
{
    public static final int SCHEMA_ID = 1;
    public static final int SCHEMA_VERSION = 2;
    public static final String SEMANTIC_VERSION = "2.1";
    public static final int ENCODED_LENGTH = 29;
    public static final java.nio.ByteOrder BYTE_ORDER = java.nio.ByteOrder.LITTLE_ENDIAN;

    private int offset;
    private DirectBuffer buffer;

    public EiResponseTypeDecoder wrap(final DirectBuffer buffer, final int offset)
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

    public static int createdDateTimeEncodingOffset()
    {
        return 0;
    }

    public static int createdDateTimeEncodingLength()
    {
        return 12;
    }

    public static int createdDateTimeSinceVersion()
    {
        return 0;
    }

    private final InstantDecoder createdDateTime = new InstantDecoder();

    /**
     * See java.time.Instant. Seconds (signed) and nanoseconds (unsiqned)
     *
     * @return InstantDecoder : See java.time.Instant. Seconds (signed) and nanoseconds (unsiqned)
     */
    public InstantDecoder createdDateTime()
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

    public static int inResponseToSinceVersion()
    {
        return 0;
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

    public long inResponseTo()
    {
        return buffer.getLong(offset + 12, BYTE_ORDER);
    }


    public static int responseCodeEncodingOffset()
    {
        return 20;
    }

    public static int responseCodeEncodingLength()
    {
        return 8;
    }

    public static int responseCodeSinceVersion()
    {
        return 0;
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

    public long responseCode()
    {
        return buffer.getLong(offset + 20, BYTE_ORDER);
    }


    public static int responseDetailEncodingOffset()
    {
        return 28;
    }

    public static int responseDetailEncodingLength()
    {
        return 1;
    }

    public static int responseDetailSinceVersion()
    {
        return 0;
    }

    public short responseDetailRaw()
    {
        return ((short)(buffer.getByte(offset + 28) & 0xFF));
    }

    public ResponseDetailType responseDetail()
    {
        return ResponseDetailType.get(((short)(buffer.getByte(offset + 28) & 0xFF)));
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
        builder.append("createdDateTime=");
        final InstantDecoder createdDateTime = this.createdDateTime();
        if (null != createdDateTime)
        {
            createdDateTime.appendTo(builder);
        }
        else
        {
            builder.append("null");
        }
        builder.append('|');
        builder.append("inResponseTo=");
        builder.append(this.inResponseTo());
        builder.append('|');
        builder.append("responseCode=");
        builder.append(this.responseCode());
        builder.append('|');
        builder.append("responseDetail=");
        builder.append(this.responseDetail());
        builder.append(')');

        return builder;
    }
}
