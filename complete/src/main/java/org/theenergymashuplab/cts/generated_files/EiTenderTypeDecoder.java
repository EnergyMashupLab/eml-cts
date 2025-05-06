/* Generated SBE (Simple Binary Encoding) message codec. */
package org.theenergymashuplab.cts.generated_files;

import org.agrona.DirectBuffer;


/**
 * See EiTenderType.java
 */
@SuppressWarnings("all")
public final class EiTenderTypeDecoder
{
    public static final int SCHEMA_ID = 1;
    public static final int SCHEMA_VERSION = 2;
    public static final String SEMANTIC_VERSION = "2.1";
    public static final int ENCODED_LENGTH = 115;
    public static final java.nio.ByteOrder BYTE_ORDER = java.nio.ByteOrder.LITTLE_ENDIAN;

    private int offset;
    private DirectBuffer buffer;

    public EiTenderTypeDecoder wrap(final DirectBuffer buffer, final int offset)
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

    public static int marketOrderIdEncodingOffset()
    {
        return 0;
    }

    public static int marketOrderIdEncodingLength()
    {
        return 8;
    }

    public static int marketOrderIdSinceVersion()
    {
        return 0;
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

    public long marketOrderId()
    {
        return buffer.getLong(offset + 0, BYTE_ORDER);
    }


    public static int tenderIdEncodingOffset()
    {
        return 8;
    }

    public static int tenderIdEncodingLength()
    {
        return 8;
    }

    public static int tenderIdSinceVersion()
    {
        return 0;
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

    public long tenderId()
    {
        return buffer.getLong(offset + 8, BYTE_ORDER);
    }


    public static int referencedQuoteIdEncodingOffset()
    {
        return 16;
    }

    public static int referencedQuoteIdEncodingLength()
    {
        return 8;
    }

    public static int referencedQuoteIdSinceVersion()
    {
        return 0;
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

    public long referencedQuoteId()
    {
        return buffer.getLong(offset + 16, BYTE_ORDER);
    }


    public static int tenderBaseEncodingOffset()
    {
        return 24;
    }

    public static int tenderBaseEncodingLength()
    {
        return 91;
    }

    public static int tenderBaseSinceVersion()
    {
        return 0;
    }

    private final TenderBaseDecoder tenderBase = new TenderBaseDecoder();

    /**
     * See TenderBase.java
     *
     * @return TenderBaseDecoder : See TenderBase.java
     */
    public TenderBaseDecoder tenderBase()
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

        builder.append('(');
        builder.append("marketOrderId=");
        builder.append(this.marketOrderId());
        builder.append('|');
        builder.append("tenderId=");
        builder.append(this.tenderId());
        builder.append('|');
        builder.append("referencedQuoteId=");
        builder.append(this.referencedQuoteId());
        builder.append('|');
        builder.append("tenderBase=");
        final TenderBaseDecoder tenderBase = this.tenderBase();
        if (null != tenderBase)
        {
            tenderBase.appendTo(builder);
        }
        else
        {
            builder.append("null");
        }
        builder.append(')');

        return builder;
    }
}
