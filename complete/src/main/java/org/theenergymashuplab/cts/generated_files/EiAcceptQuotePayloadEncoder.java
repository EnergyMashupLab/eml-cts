/* Generated SBE (Simple Binary Encoding) message codec. */
package org.theenergymashuplab.cts.generated_files;

import org.agrona.MutableDirectBuffer;


/**
 * See EiAcceptQuotePayload
 */
@SuppressWarnings("all")
public final class EiAcceptQuotePayloadEncoder
{
    public static final int BLOCK_LENGTH = 163;
    public static final int TEMPLATE_ID = 11;
    public static final int SCHEMA_ID = 1;
    public static final int SCHEMA_VERSION = 2;
    public static final String SEMANTIC_VERSION = "2.1";
    public static final java.nio.ByteOrder BYTE_ORDER = java.nio.ByteOrder.LITTLE_ENDIAN;

    private final EiAcceptQuotePayloadEncoder parentMessage = this;
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

    public EiAcceptQuotePayloadEncoder wrap(final MutableDirectBuffer buffer, final int offset)
    {
        if (buffer != this.buffer)
        {
            this.buffer = buffer;
        }
        this.offset = offset;
        limit(offset + BLOCK_LENGTH);

        return this;
    }

    public EiAcceptQuotePayloadEncoder wrapAndApplyHeader(
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

    public EiAcceptQuotePayloadEncoder referencedQuoteId(final long value)
    {
        buffer.putLong(offset + 0, value, BYTE_ORDER);
        return this;
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

    public EiAcceptQuotePayloadEncoder counterPartyId(final long value)
    {
        buffer.putLong(offset + 8, value, BYTE_ORDER);
        return this;
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

    public EiAcceptQuotePayloadEncoder marketTransactionId(final long value)
    {
        buffer.putLong(offset + 16, value, BYTE_ORDER);
        return this;
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

    public EiAcceptQuotePayloadEncoder partyId(final long value)
    {
        buffer.putLong(offset + 24, value, BYTE_ORDER);
        return this;
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

    public EiAcceptQuotePayloadEncoder requestId(final long value)
    {
        buffer.putLong(offset + 32, value, BYTE_ORDER);
        return this;
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

    private final EiTransactionTypeEncoder transaction = new EiTransactionTypeEncoder();

    public EiTransactionTypeEncoder transaction()
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

        return appendTo(new StringBuilder()).toString();
    }

    public StringBuilder appendTo(final StringBuilder builder)
    {
        if (null == buffer)
        {
            return builder;
        }

        final EiAcceptQuotePayloadDecoder decoder = new EiAcceptQuotePayloadDecoder();
        decoder.wrap(buffer, offset, BLOCK_LENGTH, SCHEMA_VERSION);

        return decoder.appendTo(builder);
    }
}
