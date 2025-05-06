/* Generated SBE (Simple Binary Encoding) message codec. */
package org.theenergymashuplab.cts.generated_files;

import org.agrona.DirectBuffer;


/**
 * See EiAcceptQuotePayload
 */
@SuppressWarnings("all")
public final class EiAcceptQuotePayloadDecoder
{
    public static final int BLOCK_LENGTH = 163;
    public static final int TEMPLATE_ID = 11;
    public static final int SCHEMA_ID = 1;
    public static final int SCHEMA_VERSION = 2;
    public static final String SEMANTIC_VERSION = "2.1";
    public static final java.nio.ByteOrder BYTE_ORDER = java.nio.ByteOrder.LITTLE_ENDIAN;

    private final EiAcceptQuotePayloadDecoder parentMessage = this;
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

    public EiAcceptQuotePayloadDecoder wrap(
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

    public EiAcceptQuotePayloadDecoder wrapAndApplyHeader(
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

    public EiAcceptQuotePayloadDecoder sbeRewind()
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

    public static int referencedQuoteIdId()
    {
        return 1;
    }

    public static int referencedQuoteIdSinceVersion()
    {
        return 0;
    }

    public static int referencedQuoteIdEncodingOffset()
    {
        return 0;
    }

    public static int referencedQuoteIdEncodingLength()
    {
        return 8;
    }

    public static String referencedQuoteIdMetaAttribute(final MetaAttribute metaAttribute)
    {
        if (MetaAttribute.PRESENCE == metaAttribute)
        {
            return "required";
        }

        return "";
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
        return buffer.getLong(offset + 0, BYTE_ORDER);
    }


    public static int counterPartyIdId()
    {
        return 2;
    }

    public static int counterPartyIdSinceVersion()
    {
        return 0;
    }

    public static int counterPartyIdEncodingOffset()
    {
        return 8;
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
        return buffer.getLong(offset + 8, BYTE_ORDER);
    }


    public static int marketTransactionIdId()
    {
        return 3;
    }

    public static int marketTransactionIdSinceVersion()
    {
        return 0;
    }

    public static int marketTransactionIdEncodingOffset()
    {
        return 16;
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
        return buffer.getLong(offset + 16, BYTE_ORDER);
    }


    public static int partyIdId()
    {
        return 4;
    }

    public static int partyIdSinceVersion()
    {
        return 0;
    }

    public static int partyIdEncodingOffset()
    {
        return 24;
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
        return buffer.getLong(offset + 24, BYTE_ORDER);
    }


    public static int requestIdId()
    {
        return 5;
    }

    public static int requestIdSinceVersion()
    {
        return 0;
    }

    public static int requestIdEncodingOffset()
    {
        return 32;
    }

    public static int requestIdEncodingLength()
    {
        return 8;
    }

    public static String requestIdMetaAttribute(final MetaAttribute metaAttribute)
    {
        if (MetaAttribute.PRESENCE == metaAttribute)
        {
            return "required";
        }

        return "";
    }

    public static long requestIdNullValue()
    {
        return 0xffffffffffffffffL;
    }

    public static long requestIdMinValue()
    {
        return 0x0L;
    }

    public static long requestIdMaxValue()
    {
        return 0xfffffffffffffffeL;
    }

    public long requestId()
    {
        return buffer.getLong(offset + 32, BYTE_ORDER);
    }


    public static int transactionId()
    {
        return 6;
    }

    public static int transactionSinceVersion()
    {
        return 0;
    }

    public static int transactionEncodingOffset()
    {
        return 40;
    }

    public static int transactionEncodingLength()
    {
        return 123;
    }

    public static String transactionMetaAttribute(final MetaAttribute metaAttribute)
    {
        if (MetaAttribute.PRESENCE == metaAttribute)
        {
            return "required";
        }

        return "";
    }

    private final EiTransactionTypeDecoder transaction = new EiTransactionTypeDecoder();

    public EiTransactionTypeDecoder transaction()
    {
        transaction.wrap(buffer, offset + 40);
        return transaction;
    }

    public String toString()
    {
        if (null == buffer)
        {
            return "";
        }

        final EiAcceptQuotePayloadDecoder decoder = new EiAcceptQuotePayloadDecoder();
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
        builder.append("[EiAcceptQuotePayload](sbeTemplateId=");
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
        builder.append("referencedQuoteId=");
        builder.append(this.referencedQuoteId());
        builder.append('|');
        builder.append("counterPartyId=");
        builder.append(this.counterPartyId());
        builder.append('|');
        builder.append("marketTransactionId=");
        builder.append(this.marketTransactionId());
        builder.append('|');
        builder.append("partyId=");
        builder.append(this.partyId());
        builder.append('|');
        builder.append("requestId=");
        builder.append(this.requestId());
        builder.append('|');
        builder.append("transaction=");
        final EiTransactionTypeDecoder transaction = this.transaction();
        if (null != transaction)
        {
            transaction.appendTo(builder);
        }
        else
        {
            builder.append("null");
        }

        limit(originalLimit);

        return builder;
    }
    
    public EiAcceptQuotePayloadDecoder sbeSkip()
    {
        sbeRewind();

        return this;
    }
}
