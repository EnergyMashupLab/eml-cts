/* Generated SBE (Simple Binary Encoding) message codec. */
package org.theenergymashuplab.cts.generated_files;

import org.agrona.DirectBuffer;


/**
 * See EiAcceptedQuotePayload
 */
@SuppressWarnings("all")
public final class EiAcceptedQuotePayloadDecoder
{
    public static final int BLOCK_LENGTH = 77;
    public static final int TEMPLATE_ID = 12;
    public static final int SCHEMA_ID = 1;
    public static final int SCHEMA_VERSION = 2;
    public static final String SEMANTIC_VERSION = "2.1";
    public static final java.nio.ByteOrder BYTE_ORDER = java.nio.ByteOrder.LITTLE_ENDIAN;

    private final EiAcceptedQuotePayloadDecoder parentMessage = this;
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

    public EiAcceptedQuotePayloadDecoder wrap(
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

    public EiAcceptedQuotePayloadDecoder wrapAndApplyHeader(
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

    public EiAcceptedQuotePayloadDecoder sbeRewind()
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

    public static int counterPartyIdId()
    {
        return 1;
    }

    public static int counterPartyIdSinceVersion()
    {
        return 0;
    }

    public static int counterPartyIdEncodingOffset()
    {
        return 0;
    }

    public static int counterPartyIdEncodingLength()
    {
        return 8;
    }

    public static String counterPartyIdMetaAttribute(final MetaAttribute metaAttribute)
    {
        if (MetaAttribute.PRESENCE == metaAttribute)
        {
            return "required";
        }

        return "";
    }

    public static long counterPartyIdNullValue()
    {
        return 0xffffffffffffffffL;
    }

    public static long counterPartyIdMinValue()
    {
        return 0x0L;
    }

    public static long counterPartyIdMaxValue()
    {
        return 0xfffffffffffffffeL;
    }

    public long counterPartyId()
    {
        return buffer.getLong(offset + 0, BYTE_ORDER);
    }


    public static int marketTransactionIdId()
    {
        return 2;
    }

    public static int marketTransactionIdSinceVersion()
    {
        return 0;
    }

    public static int marketTransactionIdEncodingOffset()
    {
        return 8;
    }

    public static int marketTransactionIdEncodingLength()
    {
        return 8;
    }

    public static String marketTransactionIdMetaAttribute(final MetaAttribute metaAttribute)
    {
        if (MetaAttribute.PRESENCE == metaAttribute)
        {
            return "required";
        }

        return "";
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

    public long marketTransactionId()
    {
        return buffer.getLong(offset + 8, BYTE_ORDER);
    }


    public static int partyIdId()
    {
        return 3;
    }

    public static int partyIdSinceVersion()
    {
        return 0;
    }

    public static int partyIdEncodingOffset()
    {
        return 16;
    }

    public static int partyIdEncodingLength()
    {
        return 8;
    }

    public static String partyIdMetaAttribute(final MetaAttribute metaAttribute)
    {
        if (MetaAttribute.PRESENCE == metaAttribute)
        {
            return "required";
        }

        return "";
    }

    public static long partyIdNullValue()
    {
        return 0xffffffffffffffffL;
    }

    public static long partyIdMinValue()
    {
        return 0x0L;
    }

    public static long partyIdMaxValue()
    {
        return 0xfffffffffffffffeL;
    }

    public long partyId()
    {
        return buffer.getLong(offset + 16, BYTE_ORDER);
    }


    public static int recipientTransactionIdId()
    {
        return 4;
    }

    public static int recipientTransactionIdSinceVersion()
    {
        return 0;
    }

    public static int recipientTransactionIdEncodingOffset()
    {
        return 24;
    }

    public static int recipientTransactionIdEncodingLength()
    {
        return 8;
    }

    public static String recipientTransactionIdMetaAttribute(final MetaAttribute metaAttribute)
    {
        if (MetaAttribute.PRESENCE == metaAttribute)
        {
            return "required";
        }

        return "";
    }

    public static long recipientTransactionIdNullValue()
    {
        return 0xffffffffffffffffL;
    }

    public static long recipientTransactionIdMinValue()
    {
        return 0x0L;
    }

    public static long recipientTransactionIdMaxValue()
    {
        return 0xfffffffffffffffeL;
    }

    public long recipientTransactionId()
    {
        return buffer.getLong(offset + 24, BYTE_ORDER);
    }


    public static int refIdId()
    {
        return 5;
    }

    public static int refIdSinceVersion()
    {
        return 0;
    }

    public static int refIdEncodingOffset()
    {
        return 32;
    }

    public static int refIdEncodingLength()
    {
        return 8;
    }

    public static String refIdMetaAttribute(final MetaAttribute metaAttribute)
    {
        if (MetaAttribute.PRESENCE == metaAttribute)
        {
            return "required";
        }

        return "";
    }

    public static long refIdNullValue()
    {
        return 0xffffffffffffffffL;
    }

    public static long refIdMinValue()
    {
        return 0x0L;
    }

    public static long refIdMaxValue()
    {
        return 0xfffffffffffffffeL;
    }

    public long refId()
    {
        return buffer.getLong(offset + 32, BYTE_ORDER);
    }


    public static int responseId()
    {
        return 6;
    }

    public static int responseSinceVersion()
    {
        return 0;
    }

    public static int responseEncodingOffset()
    {
        return 40;
    }

    public static int responseEncodingLength()
    {
        return 29;
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
        response.wrap(buffer, offset + 40);
        return response;
    }

    public static int transactionIdId()
    {
        return 7;
    }

    public static int transactionIdSinceVersion()
    {
        return 0;
    }

    public static int transactionIdEncodingOffset()
    {
        return 69;
    }

    public static int transactionIdEncodingLength()
    {
        return 8;
    }

    public static String transactionIdMetaAttribute(final MetaAttribute metaAttribute)
    {
        if (MetaAttribute.PRESENCE == metaAttribute)
        {
            return "required";
        }

        return "";
    }

    public static long transactionIdNullValue()
    {
        return 0xffffffffffffffffL;
    }

    public static long transactionIdMinValue()
    {
        return 0x0L;
    }

    public static long transactionIdMaxValue()
    {
        return 0xfffffffffffffffeL;
    }

    public long transactionId()
    {
        return buffer.getLong(offset + 69, BYTE_ORDER);
    }


    public String toString()
    {
        if (null == buffer)
        {
            return "";
        }

        final EiAcceptedQuotePayloadDecoder decoder = new EiAcceptedQuotePayloadDecoder();
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
        builder.append("[EiAcceptedQuotePayload](sbeTemplateId=");
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
        builder.append("counterPartyId=");
        builder.append(this.counterPartyId());
        builder.append('|');
        builder.append("marketTransactionId=");
        builder.append(this.marketTransactionId());
        builder.append('|');
        builder.append("partyId=");
        builder.append(this.partyId());
        builder.append('|');
        builder.append("recipientTransactionId=");
        builder.append(this.recipientTransactionId());
        builder.append('|');
        builder.append("refId=");
        builder.append(this.refId());
        builder.append('|');
        builder.append("response=");
        final EiResponseTypeDecoder response = this.response();
        if (null != response)
        {
            response.appendTo(builder);
        }
        else
        {
            builder.append("null");
        }
        builder.append('|');
        builder.append("transactionId=");
        builder.append(this.transactionId());

        limit(originalLimit);

        return builder;
    }
    
    public EiAcceptedQuotePayloadDecoder sbeSkip()
    {
        sbeRewind();

        return this;
    }
}
