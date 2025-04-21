/* Generated SBE (Simple Binary Encoding) message codec. */
package org.theenergymashuplab.cts.generated_files;

import org.agrona.DirectBuffer;


/**
 * See EiManageTickerSubscriptionPayload
 */
@SuppressWarnings("all")
public class EiManageTickerSubscriptionPayloadDecoder
{
    public static final int BLOCK_LENGTH = 20;
    public static final int TEMPLATE_ID = 9;
    public static final int SCHEMA_ID = 1;
    public static final int SCHEMA_VERSION = 2;
    public static final java.nio.ByteOrder BYTE_ORDER = java.nio.ByteOrder.LITTLE_ENDIAN;

    private final EiManageTickerSubscriptionPayloadDecoder parentMessage = this;
    private DirectBuffer buffer;
    private int initialOffset;
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

    public int initialOffset()
    {
        return initialOffset;
    }

    public int offset()
    {
        return offset;
    }

    public EiManageTickerSubscriptionPayloadDecoder wrap(
        final DirectBuffer buffer,
        final int offset,
        final int actingBlockLength,
        final int actingVersion)
    {
        if (buffer != this.buffer)
        {
            this.buffer = buffer;
        }
        this.initialOffset = offset;
        this.offset = offset;
        this.actingBlockLength = actingBlockLength;
        this.actingVersion = actingVersion;
        limit(offset + actingBlockLength);

        return this;
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

    public static int tickerTypeId()
    {
        return 1;
    }

    public static int tickerTypeSinceVersion()
    {
        return 0;
    }

    public static int tickerTypeEncodingOffset()
    {
        return 0;
    }

    public static int tickerTypeEncodingLength()
    {
        return 1;
    }

    public static String tickerTypeMetaAttribute(final MetaAttribute metaAttribute)
    {
        if (MetaAttribute.PRESENCE == metaAttribute)
        {
            return "required";
        }

        return "";
    }

    public short tickerTypeRaw()
    {
        return ((short)(buffer.getByte(offset + 0) & 0xFF));
    }

    public TickerType tickerType()
    {
        return TickerType.get(((short)(buffer.getByte(offset + 0) & 0xFF)));
    }


    public static int marketIdId()
    {
        return 2;
    }

    public static int marketIdSinceVersion()
    {
        return 0;
    }

    public static int marketIdEncodingOffset()
    {
        return 1;
    }

    public static int marketIdEncodingLength()
    {
        return 8;
    }

    public static String marketIdMetaAttribute(final MetaAttribute metaAttribute)
    {
        if (MetaAttribute.PRESENCE == metaAttribute)
        {
            return "required";
        }

        return "";
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
        return buffer.getLong(offset + 1, java.nio.ByteOrder.LITTLE_ENDIAN);
    }


    public static int segmentIdId()
    {
        return 3;
    }

    public static int segmentIdSinceVersion()
    {
        return 0;
    }

    public static int segmentIdEncodingOffset()
    {
        return 9;
    }

    public static int segmentIdEncodingLength()
    {
        return 2;
    }

    public static String segmentIdMetaAttribute(final MetaAttribute metaAttribute)
    {
        if (MetaAttribute.PRESENCE == metaAttribute)
        {
            return "required";
        }

        return "";
    }

    public static short segmentIdNullValue()
    {
        return (short)-32768;
    }

    public static short segmentIdMinValue()
    {
        return (short)-32767;
    }

    public static short segmentIdMaxValue()
    {
        return (short)32767;
    }

    public short segmentId()
    {
        return buffer.getShort(offset + 9, java.nio.ByteOrder.LITTLE_ENDIAN);
    }


    public static int subscriptionActionRequestedId()
    {
        return 4;
    }

    public static int subscriptionActionRequestedSinceVersion()
    {
        return 0;
    }

    public static int subscriptionActionRequestedEncodingOffset()
    {
        return 11;
    }

    public static int subscriptionActionRequestedEncodingLength()
    {
        return 1;
    }

    public static String subscriptionActionRequestedMetaAttribute(final MetaAttribute metaAttribute)
    {
        if (MetaAttribute.PRESENCE == metaAttribute)
        {
            return "required";
        }

        return "";
    }

    public short subscriptionActionRequestedRaw()
    {
        return ((short)(buffer.getByte(offset + 11) & 0xFF));
    }

    public SubscriptionActionType subscriptionActionRequested()
    {
        return SubscriptionActionType.get(((short)(buffer.getByte(offset + 11) & 0xFF)));
    }


    public static int subscriptionRequestIdId()
    {
        return 5;
    }

    public static int subscriptionRequestIdSinceVersion()
    {
        return 0;
    }

    public static int subscriptionRequestIdEncodingOffset()
    {
        return 12;
    }

    public static int subscriptionRequestIdEncodingLength()
    {
        return 8;
    }

    public static String subscriptionRequestIdMetaAttribute(final MetaAttribute metaAttribute)
    {
        if (MetaAttribute.PRESENCE == metaAttribute)
        {
            return "required";
        }

        return "";
    }

    public static long subscriptionRequestIdNullValue()
    {
        return 0xffffffffffffffffL;
    }

    public static long subscriptionRequestIdMinValue()
    {
        return 0x0L;
    }

    public static long subscriptionRequestIdMaxValue()
    {
        return 0xfffffffffffffffeL;
    }

    public long subscriptionRequestId()
    {
        return buffer.getLong(offset + 12, java.nio.ByteOrder.LITTLE_ENDIAN);
    }


    public String toString()
    {
        if (null == buffer)
        {
            return "";
        }

        final EiManageTickerSubscriptionPayloadDecoder decoder = new EiManageTickerSubscriptionPayloadDecoder();
        decoder.wrap(buffer, initialOffset, actingBlockLength, actingVersion);

        return decoder.appendTo(new StringBuilder()).toString();
    }

    public StringBuilder appendTo(final StringBuilder builder)
    {
        if (null == buffer)
        {
            return builder;
        }

        final int originalLimit = limit();
        limit(initialOffset + actingBlockLength);
        builder.append("[EiManageTickerSubscriptionPayload](sbeTemplateId=");
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
        builder.append("tickerType=");
        builder.append(tickerType());
        builder.append('|');
        builder.append("marketId=");
        builder.append(marketId());
        builder.append('|');
        builder.append("segmentId=");
        builder.append(segmentId());
        builder.append('|');
        builder.append("subscriptionActionRequested=");
        builder.append(subscriptionActionRequested());
        builder.append('|');
        builder.append("subscriptionRequestId=");
        builder.append(subscriptionRequestId());

        limit(originalLimit);

        return builder;
    }
}
