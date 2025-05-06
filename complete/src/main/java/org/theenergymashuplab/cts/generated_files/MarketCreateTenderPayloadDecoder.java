/* Generated SBE (Simple Binary Encoding) message codec. */
package org.theenergymashuplab.cts.generated_files;

import org.agrona.MutableDirectBuffer;
import org.agrona.DirectBuffer;


/**
 * see MarketCreateTenderPayload.java
 */
@SuppressWarnings("all")
public final class MarketCreateTenderPayloadDecoder
{
    public static final int BLOCK_LENGTH = 25;
    public static final int TEMPLATE_ID = 1;
    public static final int SCHEMA_ID = 1;
    public static final int SCHEMA_VERSION = 2;
    public static final String SEMANTIC_VERSION = "2.1";
    public static final java.nio.ByteOrder BYTE_ORDER = java.nio.ByteOrder.LITTLE_ENDIAN;

    private final MarketCreateTenderPayloadDecoder parentMessage = this;
    private DirectBuffer buffer;
    private int offset;
    private int limit;
    int actingBlockLength;
    int actingVersion;

    public int sbeBlockLength()
    {
        return BLOCK_LENGTH;
    }

    public int sbeTemplateId()
    {
        return TEMPLATE_ID;
    }

    public int sbeSchemaId()
    {
        return SCHEMA_ID;
    }

    public int sbeSchemaVersion()
    {
        return SCHEMA_VERSION;
    }

    public String sbeSemanticType()
    {
        return "";
    }

    public DirectBuffer buffer()
    {
        return buffer;
    }

    public int offset()
    {
        return offset;
    }

    public MarketCreateTenderPayloadDecoder wrap(
        final DirectBuffer buffer,
        final int offset,
        final int actingBlockLength,
        final int actingVersion)
    {
        if (buffer != this.buffer)
        {
            this.buffer = buffer;
        }
        this.offset = offset;
        this.actingBlockLength = actingBlockLength;
        this.actingVersion = actingVersion;
        limit(offset + actingBlockLength);

        return this;
    }

    public MarketCreateTenderPayloadDecoder wrapAndApplyHeader(
        final DirectBuffer buffer,
        final int offset,
        final MessageHeaderDecoder headerDecoder)
    {
        headerDecoder.wrap(buffer, offset);

        final int templateId = headerDecoder.templateId();
        if (TEMPLATE_ID != templateId)
        {
            throw new IllegalStateException("Invalid TEMPLATE_ID: " + templateId);
        }

        return wrap(
            buffer,
            offset + MessageHeaderDecoder.ENCODED_LENGTH,
            headerDecoder.blockLength(),
            headerDecoder.version());
    }

    public MarketCreateTenderPayloadDecoder sbeRewind()
    {
        return wrap(buffer, offset, actingBlockLength, actingVersion);
    }

    public int sbeDecodedLength()
    {
        final int currentLimit = limit();
        sbeSkip();
        final int decodedLength = encodedLength();
        limit(currentLimit);

        return decodedLength;
    }

    public int actingVersion()
    {
        return actingVersion;
    }

    public int encodedLength()
    {
        return limit - offset;
    }

    public int limit()
    {
        return limit;
    }

    public void limit(final int limit)
    {
        this.limit = limit;
    }

    public static int sideId()
    {
        return 2;
    }

    public static int sideSinceVersion()
    {
        return 0;
    }

    public static int sideEncodingOffset()
    {
        return 0;
    }

    public static int sideEncodingLength()
    {
        return 1;
    }

    public static String sideMetaAttribute(final MetaAttribute metaAttribute)
    {
        if (MetaAttribute.PRESENCE == metaAttribute)
        {
            return "required";
        }

        return "";
    }

    public byte sideRaw()
    {
        return buffer.getByte(offset + 0);
    }

    public SideType side()
    {
        return SideType.get(buffer.getByte(offset + 0));
    }


    public static int quantityId()
    {
        return 3;
    }

    public static int quantitySinceVersion()
    {
        return 0;
    }

    public static int quantityEncodingOffset()
    {
        return 1;
    }

    public static int quantityEncodingLength()
    {
        return 4;
    }

    public static String quantityMetaAttribute(final MetaAttribute metaAttribute)
    {
        if (MetaAttribute.PRESENCE == metaAttribute)
        {
            return "required";
        }

        return "";
    }

    public static long quantityNullValue()
    {
        return 4294967295L;
    }

    public static long quantityMinValue()
    {
        return 0L;
    }

    public static long quantityMaxValue()
    {
        return 4294967294L;
    }

    public long quantity()
    {
        return (buffer.getInt(offset + 1, BYTE_ORDER) & 0xFFFF_FFFFL);
    }


    public static int priceId()
    {
        return 4;
    }

    public static int priceSinceVersion()
    {
        return 0;
    }

    public static int priceEncodingOffset()
    {
        return 5;
    }

    public static int priceEncodingLength()
    {
        return 4;
    }

    public static String priceMetaAttribute(final MetaAttribute metaAttribute)
    {
        if (MetaAttribute.PRESENCE == metaAttribute)
        {
            return "required";
        }

        return "";
    }

    public static long priceNullValue()
    {
        return 4294967295L;
    }

    public static long priceMinValue()
    {
        return 0L;
    }

    public static long priceMaxValue()
    {
        return 4294967294L;
    }

    public long price()
    {
        return (buffer.getInt(offset + 5, BYTE_ORDER) & 0xFFFF_FFFFL);
    }


    public static int ctsTenderIdId()
    {
        return 5;
    }

    public static int ctsTenderIdSinceVersion()
    {
        return 0;
    }

    public static int ctsTenderIdEncodingOffset()
    {
        return 9;
    }

    public static int ctsTenderIdEncodingLength()
    {
        return 4;
    }

    public static String ctsTenderIdMetaAttribute(final MetaAttribute metaAttribute)
    {
        if (MetaAttribute.PRESENCE == metaAttribute)
        {
            return "required";
        }

        return "";
    }

    public static long ctsTenderIdNullValue()
    {
        return 4294967295L;
    }

    public static long ctsTenderIdMinValue()
    {
        return 0L;
    }

    public static long ctsTenderIdMaxValue()
    {
        return 4294967294L;
    }

    public long ctsTenderId()
    {
        return (buffer.getInt(offset + 9, BYTE_ORDER) & 0xFFFF_FFFFL);
    }


    public static int expireTimeId()
    {
        return 6;
    }

    public static int expireTimeSinceVersion()
    {
        return 0;
    }

    public static int expireTimeEncodingOffset()
    {
        return 13;
    }

    public static int expireTimeEncodingLength()
    {
        return 4;
    }

    public static String expireTimeMetaAttribute(final MetaAttribute metaAttribute)
    {
        if (MetaAttribute.PRESENCE == metaAttribute)
        {
            return "required";
        }

        return "";
    }

    private final BridgeInstantDecoder expireTime = new BridgeInstantDecoder();

    public BridgeInstantDecoder expireTime()
    {
        expireTime.wrap(buffer, offset + 13);
        return expireTime;
    }

    public static int bridgeIntervalId()
    {
        return 7;
    }

    public static int bridgeIntervalSinceVersion()
    {
        return 0;
    }

    public static int bridgeIntervalEncodingOffset()
    {
        return 17;
    }

    public static int bridgeIntervalEncodingLength()
    {
        return 8;
    }

    public static String bridgeIntervalMetaAttribute(final MetaAttribute metaAttribute)
    {
        if (MetaAttribute.PRESENCE == metaAttribute)
        {
            return "required";
        }

        return "";
    }

    private final BridgeIntervalDecoder bridgeInterval = new BridgeIntervalDecoder();

    public BridgeIntervalDecoder bridgeInterval()
    {
        bridgeInterval.wrap(buffer, offset + 17);
        return bridgeInterval;
    }

    public static int infoId()
    {
        return 1;
    }

    public static int infoSinceVersion()
    {
        return 0;
    }

    public static String infoCharacterEncoding()
    {
        return java.nio.charset.StandardCharsets.UTF_8.name();
    }

    public static String infoMetaAttribute(final MetaAttribute metaAttribute)
    {
        if (MetaAttribute.PRESENCE == metaAttribute)
        {
            return "required";
        }

        return "";
    }

    public static int infoHeaderLength()
    {
        return 4;
    }

    public int infoLength()
    {
        final int limit = parentMessage.limit();
        return (int)(buffer.getInt(limit, BYTE_ORDER) & 0xFFFF_FFFFL);
    }

    public int skipInfo()
    {
        final int headerLength = 4;
        final int limit = parentMessage.limit();
        final int dataLength = (int)(buffer.getInt(limit, BYTE_ORDER) & 0xFFFF_FFFFL);
        final int dataOffset = limit + headerLength;
        parentMessage.limit(dataOffset + dataLength);

        return dataLength;
    }

    public int getInfo(final MutableDirectBuffer dst, final int dstOffset, final int length)
    {
        final int headerLength = 4;
        final int limit = parentMessage.limit();
        final int dataLength = (int)(buffer.getInt(limit, BYTE_ORDER) & 0xFFFF_FFFFL);
        final int bytesCopied = Math.min(length, dataLength);
        parentMessage.limit(limit + headerLength + dataLength);
        buffer.getBytes(limit + headerLength, dst, dstOffset, bytesCopied);

        return bytesCopied;
    }

    public int getInfo(final byte[] dst, final int dstOffset, final int length)
    {
        final int headerLength = 4;
        final int limit = parentMessage.limit();
        final int dataLength = (int)(buffer.getInt(limit, BYTE_ORDER) & 0xFFFF_FFFFL);
        final int bytesCopied = Math.min(length, dataLength);
        parentMessage.limit(limit + headerLength + dataLength);
        buffer.getBytes(limit + headerLength, dst, dstOffset, bytesCopied);

        return bytesCopied;
    }

    public void wrapInfo(final DirectBuffer wrapBuffer)
    {
        final int headerLength = 4;
        final int limit = parentMessage.limit();
        final int dataLength = (int)(buffer.getInt(limit, BYTE_ORDER) & 0xFFFF_FFFFL);
        parentMessage.limit(limit + headerLength + dataLength);
        wrapBuffer.wrap(buffer, limit + headerLength, dataLength);
    }

    public String info()
    {
        final int headerLength = 4;
        final int limit = parentMessage.limit();
        final int dataLength = (int)(buffer.getInt(limit, BYTE_ORDER) & 0xFFFF_FFFFL);
        parentMessage.limit(limit + headerLength + dataLength);

        if (0 == dataLength)
        {
            return "";
        }

        final byte[] tmp = new byte[dataLength];
        buffer.getBytes(limit + headerLength, tmp, 0, dataLength);

        return new String(tmp, java.nio.charset.StandardCharsets.UTF_8);
    }

    public String toString()
    {
        if (null == buffer)
        {
            return "";
        }

        final MarketCreateTenderPayloadDecoder decoder = new MarketCreateTenderPayloadDecoder();
        decoder.wrap(buffer, offset, actingBlockLength, actingVersion);

        return decoder.appendTo(new StringBuilder()).toString();
    }

    public StringBuilder appendTo(final StringBuilder builder)
    {
        if (null == buffer)
        {
            return builder;
        }

        final int originalLimit = limit();
        limit(offset + actingBlockLength);
        builder.append("[MarketCreateTenderPayload](sbeTemplateId=");
        builder.append(TEMPLATE_ID);
        builder.append("|sbeSchemaId=");
        builder.append(SCHEMA_ID);
        builder.append("|sbeSchemaVersion=");
        if (parentMessage.actingVersion != SCHEMA_VERSION)
        {
            builder.append(parentMessage.actingVersion);
            builder.append('/');
        }
        builder.append(SCHEMA_VERSION);
        builder.append("|sbeBlockLength=");
        if (actingBlockLength != BLOCK_LENGTH)
        {
            builder.append(actingBlockLength);
            builder.append('/');
        }
        builder.append(BLOCK_LENGTH);
        builder.append("):");
        builder.append("side=");
        builder.append(this.side());
        builder.append('|');
        builder.append("quantity=");
        builder.append(this.quantity());
        builder.append('|');
        builder.append("price=");
        builder.append(this.price());
        builder.append('|');
        builder.append("ctsTenderId=");
        builder.append(this.ctsTenderId());
        builder.append('|');
        builder.append("expireTime=");
        final BridgeInstantDecoder expireTime = this.expireTime();
        if (null != expireTime)
        {
            expireTime.appendTo(builder);
        }
        else
        {
            builder.append("null");
        }
        builder.append('|');
        builder.append("bridgeInterval=");
        final BridgeIntervalDecoder bridgeInterval = this.bridgeInterval();
        if (null != bridgeInterval)
        {
            bridgeInterval.appendTo(builder);
        }
        else
        {
            builder.append("null");
        }
        builder.append('|');
        builder.append("info=");
        builder.append('\'').append(info()).append('\'');

        limit(originalLimit);

        return builder;
    }
    
    public MarketCreateTenderPayloadDecoder sbeSkip()
    {
        sbeRewind();
        skipInfo();

        return this;
    }
}
