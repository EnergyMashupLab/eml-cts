/* Generated SBE (Simple Binary Encoding) message codec. */
package org.theenergymashuplab.cts.generated_files;

import org.agrona.DirectBuffer;


/**
 * See EiTransactionType.java
 */
@SuppressWarnings("all")
public class EiTransactionTypeDecoder
{
    public static final int SCHEMA_ID = 1;
    public static final int SCHEMA_VERSION = 2;
    public static final int ENCODED_LENGTH = 111;
    public static final java.nio.ByteOrder BYTE_ORDER = java.nio.ByteOrder.LITTLE_ENDIAN;

    private int offset;
    private DirectBuffer buffer;

    public EiTransactionTypeDecoder wrap(final DirectBuffer buffer, final int offset)
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

    public static int marketTransactionIdEncodingOffset()
    {
        return 0;
    }

    public static int marketTransactionIdEncodingLength()
    {
        return 8;
    }

    public static int marketTransactionIdSinceVersion()
    {
        return 0;
    }

    public static long marketTransactionIdNullValue()
    {
        return 0xffffffffffffffffL;
    }

    public static long marketTransactionIdMinValue()
    {
        return 0x0L;
    }

    public static long marketTransactionIdMaxValue()
    {
        return 0xfffffffffffffffeL;
    }

    public long marketTransactionId()
    {
        return buffer.getLong(offset + 0, java.nio.ByteOrder.LITTLE_ENDIAN);
    }


    public static int tenderEncodingOffset()
    {
        return 8;
    }

    public static int tenderEncodingLength()
    {
        return 103;
    }

    public static int tenderSinceVersion()
    {
        return 0;
    }

    private final EiTenderTypeDecoder tender = new EiTenderTypeDecoder();

    /**
     * See EiTenderType.java
     *
     * @return EiTenderTypeDecoder : See EiTenderType.java
     */
    public EiTenderTypeDecoder tender()
    {
        tender.wrap(buffer, offset + 8);
        return tender;
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
        builder.append("marketTransactionId=");
        builder.append(marketTransactionId());
        builder.append('|');
        builder.append("tender=");
        final EiTenderTypeDecoder tender = tender();
        if (tender != null)
        {
            tender.appendTo(builder);
        }
        else
        {
            builder.append("null");
        }
        builder.append(')');

        return builder;
    }
}
