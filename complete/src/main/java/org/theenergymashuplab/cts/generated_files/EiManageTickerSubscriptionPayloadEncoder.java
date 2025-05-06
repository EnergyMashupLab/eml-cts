/* Generated SBE (Simple Binary Encoding) message codec. */
package org.theenergymashuplab.cts.generated_files;

import org.agrona.MutableDirectBuffer;


/**
 * See EiManageTickerSubscriptionPayload
 */
@SuppressWarnings("all")
public final class EiManageTickerSubscriptionPayloadEncoder
{
    public static final int BLOCK_LENGTH = 20;
    public static final int TEMPLATE_ID = 9;
    public static final int SCHEMA_ID = 1;
    public static final int SCHEMA_VERSION = 2;
    public static final String SEMANTIC_VERSION = "2.1";
    public static final java.nio.ByteOrder BYTE_ORDER = java.nio.ByteOrder.LITTLE_ENDIAN;

    private final EiManageTickerSubscriptionPayloadEncoder parentMessage = this;
    private MutableDirectBuffer buffer;
    private int offset;
    private int limit;

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

    public MutableDirectBuffer buffer()
    {
        return buffer;
    }

    public int offset()
    {
        return offset;
    }

    public EiManageTickerSubscriptionPayloadEncoder wrap(final MutableDirectBuffer buffer, final int offset)
    {
        if (buffer != this.buffer)
        {
            this.buffer = buffer;
        }
        this.offset = offset;
        limit(offset + BLOCK_LENGTH);

        return this;
    }

    public EiManageTickerSubscriptionPayloadEncoder wrapAndApplyHeader(
        final MutableDirectBuffer buffer, final int offset, final MessageHeaderEncoder headerEncoder)
    {
        headerEncoder
            .wrap(buffer, offset)
            .blockLength(BLOCK_LENGTH)
            .templateId(TEMPLATE_ID)
            .schemaId(SCHEMA_ID)
            .version(SCHEMA_VERSION);

        return wrap(buffer, offset + MessageHeaderEncoder.ENCODED_LENGTH);
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

    public EiManageTickerSubscriptionPayloadEncoder tickerType(final TickerType value)
    {
        buffer.putByte(offset + 0, (byte)value.value());
        return this;
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

    public EiManageTickerSubscriptionPayloadEncoder marketId(final long value)
    {
        buffer.putLong(offset + 1, value, BYTE_ORDER);
        return this;
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

    public EiManageTickerSubscriptionPayloadEncoder segmentId(final short value)
    {
        buffer.putShort(offset + 9, value, BYTE_ORDER);
        return this;
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

    public EiManageTickerSubscriptionPayloadEncoder subscriptionActionRequested(final SubscriptionActionType value)
    {
        buffer.putByte(offset + 11, (byte)value.value());
        return this;
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

    public EiManageTickerSubscriptionPayloadEncoder subscriptionRequestId(final long value)
    {
        buffer.putLong(offset + 12, value, BYTE_ORDER);
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

        final EiManageTickerSubscriptionPayloadDecoder decoder = new EiManageTickerSubscriptionPayloadDecoder();
        decoder.wrap(buffer, offset, BLOCK_LENGTH, SCHEMA_VERSION);

        return decoder.appendTo(builder);
    }
}
