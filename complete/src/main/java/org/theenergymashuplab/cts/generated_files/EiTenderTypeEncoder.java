/* Generated SBE (Simple Binary Encoding) message codec. */
package org.theenergymashuplab.cts.generated_files;

import org.agrona.MutableDirectBuffer;


/**
 * See EiTenderType.java
 */
@SuppressWarnings("all")
public final class EiTenderTypeEncoder
{
    public static final int SCHEMA_ID = 1;
    public static final int SCHEMA_VERSION = 2;
    public static final String SEMANTIC_VERSION = "2.1";
    public static final int ENCODED_LENGTH = 115;
    public static final java.nio.ByteOrder BYTE_ORDER = java.nio.ByteOrder.LITTLE_ENDIAN;

    private int offset;
    private MutableDirectBuffer buffer;

    public EiTenderTypeEncoder wrap(final MutableDirectBuffer buffer, final int offset)
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

    public static int marketOrderIdEncodingOffset()
    {
        return 0;
    }

    public static int marketOrderIdEncodingLength()
    {
        return 8;
    }

    public static long marketOrderIdNullValue()
    {
        return 0xffffffffffffffffL;
    }

    public static long marketOrderIdMinValue()
    {
        return 0x0L;
    }

    public static long marketOrderIdMaxValue()
    {
        return 0xfffffffffffffffeL;
    }

    public EiTenderTypeEncoder marketOrderId(final long value)
    {
        buffer.putLong(offset + 0, value, BYTE_ORDER);
        return this;
    }


    public static int tenderIdEncodingOffset()
    {
        return 8;
    }

    public static int tenderIdEncodingLength()
    {
        return 8;
    }

    public static long tenderIdNullValue()
    {
        return 0xffffffffffffffffL;
    }

    public static long tenderIdMinValue()
    {
        return 0x0L;
    }

    public static long tenderIdMaxValue()
    {
        return 0xfffffffffffffffeL;
    }

    public EiTenderTypeEncoder tenderId(final long value)
    {
        buffer.putLong(offset + 8, value, BYTE_ORDER);
        return this;
    }


    public static int referencedQuoteIdEncodingOffset()
    {
        return 16;
    }

    public static int referencedQuoteIdEncodingLength()
    {
        return 8;
    }

    public static long referencedQuoteIdNullValue()
    {
        return 0xffffffffffffffffL;
    }

    public static long referencedQuoteIdMinValue()
    {
        return 0x0L;
    }

    public static long referencedQuoteIdMaxValue()
    {
        return 0xfffffffffffffffeL;
    }

    public EiTenderTypeEncoder referencedQuoteId(final long value)
    {
        buffer.putLong(offset + 16, value, BYTE_ORDER);
        return this;
    }


    public static int tenderBaseEncodingOffset()
    {
        return 24;
    }

    public static int tenderBaseEncodingLength()
    {
        return 91;
    }

    private final TenderBaseEncoder tenderBase = new TenderBaseEncoder();

    /**
     * See TenderBase.java
     *
     * @return TenderBaseEncoder : See TenderBase.java
     */
    public TenderBaseEncoder tenderBase()
    {
        tenderBase.wrap(buffer, offset + 24);
        return tenderBase;
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

        final EiTenderTypeDecoder decoder = new EiTenderTypeDecoder();
        decoder.wrap(buffer, offset);

        return decoder.appendTo(builder);
    }
}
