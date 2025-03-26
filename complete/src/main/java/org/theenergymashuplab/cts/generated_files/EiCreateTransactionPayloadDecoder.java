/* Generated SBE (Simple Binary Encoding) message codec. */
package org.theenergymashuplab.cts.generated_files;

import org.agrona.DirectBuffer;


/**
 * See EiCreateTransactionPayload
 */
@SuppressWarnings("all")
public class EiCreateTransactionPayloadDecoder
{
    public static final int BLOCK_LENGTH = 147;
    public static final int TEMPLATE_ID = 7;
    public static final int SCHEMA_ID = 1;
    public static final int SCHEMA_VERSION = 2;
    public static final java.nio.ByteOrder BYTE_ORDER = java.nio.ByteOrder.LITTLE_ENDIAN;

    private final EiCreateTransactionPayloadDecoder parentMessage = this;
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

    public EiCreateTransactionPayloadDecoder wrap(
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
        return buffer.getLong(offset + 0, java.nio.ByteOrder.LITTLE_ENDIAN);
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
        return buffer.getLong(offset + 8, java.nio.ByteOrder.LITTLE_ENDIAN);
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
        return buffer.getLong(offset + 16, java.nio.ByteOrder.LITTLE_ENDIAN);
    }


    public static int requestIdId()
    {
        return 4;
    }

    public static int requestIdSinceVersion()
    {
        return 0;
    }

    public static int requestIdEncodingOffset()
    {
        return 24;
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
        return buffer.getLong(offset + 24, java.nio.ByteOrder.LITTLE_ENDIAN);
    }


    public static int transactionId()
    {
        return 5;
    }

    public static int transactionSinceVersion()
    {
        return 0;
    }

    public static int transactionEncodingOffset()
    {
        return 32;
    }

    public static int transactionEncodingLength()
    {
        return 115;
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
        transaction.wrap(buffer, offset + 32);
        return transaction;
    }

    public String toString()
    {
        if (null == buffer)
        {
            return "";
        }

        final EiCreateTransactionPayloadDecoder decoder = new EiCreateTransactionPayloadDecoder();
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
        builder.append("[EiCreateTransactionPayload](sbeTemplateId=");
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
        builder.append(counterPartyId());
        builder.append('|');
        builder.append("marketTransactionId=");
        builder.append(marketTransactionId());
        builder.append('|');
        builder.append("partyId=");
        builder.append(partyId());
        builder.append('|');
        builder.append("requestId=");
        builder.append(requestId());
        builder.append('|');
        builder.append("transaction=");
        final EiTransactionTypeDecoder transaction = transaction();
        if (transaction != null)
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
}
