/* Generated SBE (Simple Binary Encoding) message codec. */
package org.theenergymashuplab.cts.generated_files;

import org.agrona.DirectBuffer;


/**
 * See EiManagedTickerSubscriptionPayload
 */
@SuppressWarnings("all")
public class EiManagedTickerSubscriptionPayloadDecoder
{
    public static final int BLOCK_LENGTH = 47;
    public static final int TEMPLATE_ID = 10;
    public static final int SCHEMA_ID = 1;
    public static final int SCHEMA_VERSION = 2;
    public static final java.nio.ByteOrder BYTE_ORDER = java.nio.ByteOrder.LITTLE_ENDIAN;

    private final EiManagedTickerSubscriptionPayloadDecoder parentMessage = this;
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

    public EiManagedTickerSubscriptionPayloadDecoder wrap(
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

    private final VarStringEncodingDecoder multicastListenReference = new VarStringEncodingDecoder();

    public VarStringEncodingDecoder multicastListenReference()
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

    private final EiResponseTypeDecoder response = new EiResponseTypeDecoder();

    public EiResponseTypeDecoder response()
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

    public short subscriptionActionTakenRaw()
    {
        return ((short)(buffer.getByte(offset + 38) & 0xFF));
    }

    public SubscriptionActionType subscriptionActionTaken()
    {
        return SubscriptionActionType.get(((short)(buffer.getByte(offset + 38) & 0xFF)));
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

    public long subscriptionRequestId()
    {
        return buffer.getLong(offset + 39, java.nio.ByteOrder.LITTLE_ENDIAN);
    }


    public String toString()
    {
        if (null == buffer)
        {
            return "";
        }

        final EiManagedTickerSubscriptionPayloadDecoder decoder = new EiManagedTickerSubscriptionPayloadDecoder();
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
        builder.append("[EiManagedTickerSubscriptionPayload](sbeTemplateId=");
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
        builder.append("multicastListenReference=");
        final VarStringEncodingDecoder multicastListenReference = multicastListenReference();
        if (multicastListenReference != null)
        {
            multicastListenReference.appendTo(builder);
        }
        else
        {
            builder.append("null");
        }
        builder.append('|');
        builder.append("response=");
        final EiResponseTypeDecoder response = response();
        if (response != null)
        {
            response.appendTo(builder);
        }
        else
        {
            builder.append("null");
        }
        builder.append('|');
        builder.append("subscriptionActionTaken=");
        builder.append(subscriptionActionTaken());
        builder.append('|');
        builder.append("subscriptionRequestId=");
        builder.append(subscriptionRequestId());

        limit(originalLimit);

        return builder;
    }
}
