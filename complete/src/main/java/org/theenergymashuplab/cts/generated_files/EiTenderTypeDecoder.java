/* Generated SBE (Simple Binary Encoding) message codec. */
package org.theenergymashuplab.cts.generated_files;

import org.agrona.DirectBuffer;


/**
 * See EiTenderType.java
 */
@SuppressWarnings("all")
public class EiTenderTypeDecoder
{
    public static final int SCHEMA_ID = 1;
    public static final int SCHEMA_VERSION = 2;
    public static final int ENCODED_LENGTH = 103;
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
        return buffer.getLong(offset + 0, java.nio.ByteOrder.LITTLE_ENDIAN);
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
        return buffer.getLong(offset + 8, java.nio.ByteOrder.LITTLE_ENDIAN);
    }


    public static int tenderBaseEncodingOffset()
    {
        return 16;
    }

    public static int tenderBaseEncodingLength()
    {
        return 87;
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
        tenderBase.wrap(buffer, offset + 16);
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
        builder.append(marketOrderId());
        builder.append('|');
        builder.append("tenderId=");
        builder.append(tenderId());
        builder.append('|');
        builder.append("tenderBase=");
        final TenderBaseDecoder tenderBase = tenderBase();
        if (tenderBase != null)
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
