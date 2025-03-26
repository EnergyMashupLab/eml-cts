/* Generated SBE (Simple Binary Encoding) message codec. */
package org.theenergymashuplab.cts.generated_files;

import org.agrona.DirectBuffer;


/**
 * See TenderBase.java
 */
@SuppressWarnings("all")
public class TenderBaseDecoder
{
    public static final int SCHEMA_ID = 1;
    public static final int SCHEMA_VERSION = 2;
    public static final int ENCODED_LENGTH = 91;
    public static final java.nio.ByteOrder BYTE_ORDER = java.nio.ByteOrder.LITTLE_ENDIAN;

    private int offset;
    private DirectBuffer buffer;

    public TenderBaseDecoder wrap(final DirectBuffer buffer, final int offset)
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

    public static int allOrNoneEncodingOffset()
    {
        return 0;
    }

    public static int allOrNoneEncodingLength()
    {
        return 1;
    }

    public static int allOrNoneSinceVersion()
    {
        return 0;
    }

    public short allOrNoneRaw()
    {
        return ((short)(buffer.getByte(offset + 0) & 0xFF));
    }

    public BooleanType allOrNone()
    {
        return BooleanType.get(((short)(buffer.getByte(offset + 0) & 0xFF)));
    }


    public static int executionInstructionsEncodingOffset()
    {
        return 1;
    }

    public static int executionInstructionsEncodingLength()
    {
        return 8;
    }

    public static int executionInstructionsSinceVersion()
    {
        return 0;
    }

    public static long executionInstructionsNullValue()
    {
        return 0xffffffffffffffffL;
    }

    public static long executionInstructionsMinValue()
    {
        return 0x0L;
    }

    public static long executionInstructionsMaxValue()
    {
        return 0xfffffffffffffffeL;
    }

    public long executionInstructions()
    {
        return buffer.getLong(offset + 1, java.nio.ByteOrder.LITTLE_ENDIAN);
    }


    public static int expirationTimeEncodingOffset()
    {
        return 9;
    }

    public static int expirationTimeEncodingLength()
    {
        return 12;
    }

    public static int expirationTimeSinceVersion()
    {
        return 0;
    }

    private final InstantTypeDecoder expirationTime = new InstantTypeDecoder();

    /**
     * See java.time.Instant. Seconds (signed) and nanoseconds (unsiqned)
     *
     * @return InstantTypeDecoder : See java.time.Instant. Seconds (signed) and nanoseconds (unsiqned)
     */
    public InstantTypeDecoder expirationTime()
    {
        expirationTime.wrap(buffer, offset + 9);
        return expirationTime;
    }

    public static int marketIdEncodingOffset()
    {
        return 21;
    }

    public static int marketIdEncodingLength()
    {
        return 8;
    }

    public static int marketIdSinceVersion()
    {
        return 0;
    }

    public static long marketIdNullValue()
    {
        return 0xffffffffffffffffL;
    }

    public static long marketIdMinValue()
    {
        return 0x0L;
    }

    public static long marketIdMaxValue()
    {
        return 0xfffffffffffffffeL;
    }

    public long marketId()
    {
        return buffer.getLong(offset + 21, java.nio.ByteOrder.LITTLE_ENDIAN);
    }


    public static int priceScaleEncodingOffset()
    {
        return 29;
    }

    public static int priceScaleEncodingLength()
    {
        return 4;
    }

    public static int priceScaleSinceVersion()
    {
        return 0;
    }

    public static long priceScaleNullValue()
    {
        return 4294967295L;
    }

    public static long priceScaleMinValue()
    {
        return 0L;
    }

    public static long priceScaleMaxValue()
    {
        return 4294967294L;
    }

    public long priceScale()
    {
        return (buffer.getInt(offset + 29, java.nio.ByteOrder.LITTLE_ENDIAN) & 0xFFFF_FFFFL);
    }


    public static int quantityScaleEncodingOffset()
    {
        return 33;
    }

    public static int quantityScaleEncodingLength()
    {
        return 4;
    }

    public static int quantityScaleSinceVersion()
    {
        return 0;
    }

    public static long quantityScaleNullValue()
    {
        return 4294967295L;
    }

    public static long quantityScaleMinValue()
    {
        return 0L;
    }

    public static long quantityScaleMaxValue()
    {
        return 4294967294L;
    }

    public long quantityScale()
    {
        return (buffer.getInt(offset + 33, java.nio.ByteOrder.LITTLE_ENDIAN) & 0xFFFF_FFFFL);
    }


    public static int resourceDesignatorEncodingOffset()
    {
        return 37;
    }

    public static int resourceDesignatorEncodingLength()
    {
        return 1;
    }

    public static int resourceDesignatorSinceVersion()
    {
        return 0;
    }

    public short resourceDesignatorRaw()
    {
        return ((short)(buffer.getByte(offset + 37) & 0xFF));
    }

    public ResourceDesignatorType resourceDesignator()
    {
        return ResourceDesignatorType.get(((short)(buffer.getByte(offset + 37) & 0xFF)));
    }


    public static int segmentIdEncodingOffset()
    {
        return 38;
    }

    public static int segmentIdEncodingLength()
    {
        return 4;
    }

    public static int segmentIdSinceVersion()
    {
        return 0;
    }

    public static long segmentIdNullValue()
    {
        return 4294967295L;
    }

    public static long segmentIdMinValue()
    {
        return 0L;
    }

    public static long segmentIdMaxValue()
    {
        return 4294967294L;
    }

    public long segmentId()
    {
        return (buffer.getInt(offset + 38, java.nio.ByteOrder.LITTLE_ENDIAN) & 0xFFFF_FFFFL);
    }


    public static int sideEncodingOffset()
    {
        return 42;
    }

    public static int sideEncodingLength()
    {
        return 1;
    }

    public static int sideSinceVersion()
    {
        return 0;
    }

    public byte sideRaw()
    {
        return buffer.getByte(offset + 42);
    }

    public SideType side()
    {
        return SideType.get(buffer.getByte(offset + 42));
    }


    public static int tenderDetailEncodingOffset()
    {
        return 43;
    }

    public static int tenderDetailEncodingLength()
    {
        return 40;
    }

    public static int tenderDetailSinceVersion()
    {
        return 0;
    }

    private final TenderIntervalDetailDecoder tenderDetail = new TenderIntervalDetailDecoder();

    /**
     * See TenderIntervalDetail.java
     *
     * @return TenderIntervalDetailDecoder : See TenderIntervalDetail.java
     */
    public TenderIntervalDetailDecoder tenderDetail()
    {
        tenderDetail.wrap(buffer, offset + 43);
        return tenderDetail;
    }

    public static int warrantsEncodingOffset()
    {
        return 83;
    }

    public static int warrantsEncodingLength()
    {
        return 8;
    }

    public static int warrantsSinceVersion()
    {
        return 0;
    }

    public static long warrantsNullValue()
    {
        return 0xffffffffffffffffL;
    }

    public static long warrantsMinValue()
    {
        return 0x0L;
    }

    public static long warrantsMaxValue()
    {
        return 0xfffffffffffffffeL;
    }

    public long warrants()
    {
        return buffer.getLong(offset + 83, java.nio.ByteOrder.LITTLE_ENDIAN);
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
        builder.append("allOrNone=");
        builder.append(allOrNone());
        builder.append('|');
        builder.append("executionInstructions=");
        builder.append(executionInstructions());
        builder.append('|');
        builder.append("expirationTime=");
        final InstantTypeDecoder expirationTime = expirationTime();
        if (expirationTime != null)
        {
            expirationTime.appendTo(builder);
        }
        else
        {
            builder.append("null");
        }
        builder.append('|');
        builder.append("marketId=");
        builder.append(marketId());
        builder.append('|');
        builder.append("priceScale=");
        builder.append(priceScale());
        builder.append('|');
        builder.append("quantityScale=");
        builder.append(quantityScale());
        builder.append('|');
        builder.append("resourceDesignator=");
        builder.append(resourceDesignator());
        builder.append('|');
        builder.append("segmentId=");
        builder.append(segmentId());
        builder.append('|');
        builder.append("side=");
        builder.append(side());
        builder.append('|');
        builder.append("tenderDetail=");
        final TenderIntervalDetailDecoder tenderDetail = tenderDetail();
        if (tenderDetail != null)
        {
            tenderDetail.appendTo(builder);
        }
        else
        {
            builder.append("null");
        }
        builder.append('|');
        builder.append("warrants=");
        builder.append(warrants());
        builder.append(')');

        return builder;
    }
}
