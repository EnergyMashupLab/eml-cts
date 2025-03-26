/* Generated SBE (Simple Binary Encoding) message codec. */
package org.theenergymashuplab.cts.generated_files;

import org.agrona.MutableDirectBuffer;


/**
 * See EiTransactionType.java
 */
@SuppressWarnings("all")
public class EiTransactionTypeEncoder
{
    public static final int SCHEMA_ID = 1;
    public static final int SCHEMA_VERSION = 2;
    public static final int ENCODED_LENGTH = 115;
    public static final java.nio.ByteOrder BYTE_ORDER = java.nio.ByteOrder.LITTLE_ENDIAN;

    private int offset;
    private MutableDirectBuffer buffer;

    public EiTransactionTypeEncoder wrap(final MutableDirectBuffer buffer, final int offset)
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

    public static int marketTransactionIdEncodingOffset()
    {
        return 0;
    }

    public static int marketTransactionIdEncodingLength()
    {
        return 8;
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

    public EiTransactionTypeEncoder marketTransactionId(final long value)
    {
        buffer.putLong(offset + 0, value, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
    }


    public static int tenderEncodingOffset()
    {
        return 8;
    }

    public static int tenderEncodingLength()
    {
        return 107;
    }

    private final EiTenderTypeEncoder tender = new EiTenderTypeEncoder();

    /**
     * See EiTenderType.java
     *
     * @return EiTenderTypeEncoder : See EiTenderType.java
     */
    public EiTenderTypeEncoder tender()
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

        final EiTransactionTypeDecoder decoder = new EiTransactionTypeDecoder();
        decoder.wrap(buffer, offset);

        return decoder.appendTo(builder);
    }
}
