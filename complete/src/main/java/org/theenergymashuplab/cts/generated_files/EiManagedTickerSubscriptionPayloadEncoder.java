/* Generated SBE (Simple Binary Encoding) message codec. */
package org.theenergymashuplab.cts.generated_files;

import org.agrona.MutableDirectBuffer;


/**
 * See EiManagedTickerSubscriptionPayload
 */
@SuppressWarnings("all")
public class EiManagedTickerSubscriptionPayloadEncoder
{
    public static final int BLOCK_LENGTH = 47;
    public static final int TEMPLATE_ID = 10;
    public static final int SCHEMA_ID = 1;
    public static final int SCHEMA_VERSION = 2;
    public static final java.nio.ByteOrder BYTE_ORDER = java.nio.ByteOrder.LITTLE_ENDIAN;

    private final EiManagedTickerSubscriptionPayloadEncoder parentMessage = this;
    private MutableDirectBuffer buffer;
    private int initialOffset;
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

    public int initialOffset()
    {
        return initialOffset;
    }

    public int offset()
    {
        return offset;
    }

    public EiManagedTickerSubscriptionPayloadEncoder wrap(final MutableDirectBuffer buffer, final int offset)
    {
        if (buffer != this.buffer)
        {
            this.buffer = buffer;
        }
        this.initialOffset = offset;
        this.offset = offset;
        limit(offset + BLOCK_LENGTH);

        return this;
    }

    public EiManagedTickerSubscriptionPayloadEncoder wrapAndApplyHeader(
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

    public EiManagedTickerSubscriptionPayloadEncoder tickerType(final TickerType value)
    {
        buffer.putByte(offset + 0, (byte)value.value());
        return this;
    }

    public static int multicastListenReferenceId()
    {
        return 2;
    }

    public static int multicastListenReferenceSinceVersion()
    {
        return 0;
    }

    public static int multicastListenReferenceEncodingOffset()
    {
        return 1;
    }

    public static int multicastListenReferenceEncodingLength()
    {
        return 4;
    }

    public static String multicastListenReferenceMetaAttribute(final MetaAttribute metaAttribute)
    {
        if (MetaAttribute.PRESENCE == metaAttribute)
        {
            return "required";
        }

        return "";
    }

    private final VarStringEncodingEncoder multicastListenReference = new VarStringEncodingEncoder();

    public VarStringEncodingEncoder multicastListenReference()
    {
        multicastListenReference.wrap(buffer, offset + 1);
        return multicastListenReference;
    }

    public static int responseId()
    {
        return 3;
    }

    public static int responseSinceVersion()
    {
        return 0;
    }

    public static int responseEncodingOffset()
    {
        return 5;
    }

    public static int responseEncodingLength()
    {
        return 33;
    }

    public static String responseMetaAttribute(final MetaAttribute metaAttribute)
    {
        if (MetaAttribute.PRESENCE == metaAttribute)
        {
            return "required";
        }

        return "";
    }

    private final EiResponseTypeEncoder response = new EiResponseTypeEncoder();

    public EiResponseTypeEncoder response()
    {
        response.wrap(buffer, offset + 5);
        return response;
    }

    public static int subscriptionActionTakenId()
    {
        return 4;
    }

    public static int subscriptionActionTakenSinceVersion()
    {
        return 0;
    }

    public static int subscriptionActionTakenEncodingOffset()
    {
        return 38;
    }

    public static int subscriptionActionTakenEncodingLength()
    {
        return 1;
    }

    public static String subscriptionActionTakenMetaAttribute(final MetaAttribute metaAttribute)
    {
        if (MetaAttribute.PRESENCE == metaAttribute)
        {
            return "required";
        }

        return "";
    }

    public EiManagedTickerSubscriptionPayloadEncoder subscriptionActionTaken(final SubscriptionActionType value)
    {
        buffer.putByte(offset + 38, (byte)value.value());
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
        return 39;
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

    public EiManagedTickerSubscriptionPayloadEncoder subscriptionRequestId(final long value)
    {
        buffer.putLong(offset + 39, value, java.nio.ByteOrder.LITTLE_ENDIAN);
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

        final EiManagedTickerSubscriptionPayloadDecoder decoder = new EiManagedTickerSubscriptionPayloadDecoder();
        decoder.wrap(buffer, initialOffset, BLOCK_LENGTH, SCHEMA_VERSION);

        return decoder.appendTo(builder);
    }
}
