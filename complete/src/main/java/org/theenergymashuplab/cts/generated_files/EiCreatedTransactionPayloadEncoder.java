/* Generated SBE (Simple Binary Encoding) message codec. */
package org.theenergymashuplab.cts.generated_files;

import org.agrona.MutableDirectBuffer;


/**
 * See EiCreatedTransactionPayload
 */
@SuppressWarnings("all")
public class EiCreatedTransactionPayloadEncoder
{
    public static final int BLOCK_LENGTH = 81;
    public static final int TEMPLATE_ID = 8;
    public static final int SCHEMA_ID = 1;
    public static final int SCHEMA_VERSION = 2;
    public static final java.nio.ByteOrder BYTE_ORDER = java.nio.ByteOrder.LITTLE_ENDIAN;

    private final EiCreatedTransactionPayloadEncoder parentMessage = this;
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

    public EiCreatedTransactionPayloadEncoder wrap(final MutableDirectBuffer buffer, final int offset)
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

    public EiCreatedTransactionPayloadEncoder wrapAndApplyHeader(
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

    public EiCreatedTransactionPayloadEncoder counterPartyId(final long value)
    {
        buffer.putLong(offset + 0, value, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
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

    public EiCreatedTransactionPayloadEncoder marketTransactionId(final long value)
    {
        buffer.putLong(offset + 8, value, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
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

    public EiCreatedTransactionPayloadEncoder partyId(final long value)
    {
        buffer.putLong(offset + 16, value, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
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

    public EiCreatedTransactionPayloadEncoder recipientTransactionId(final long value)
    {
        buffer.putLong(offset + 24, value, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
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

    public EiCreatedTransactionPayloadEncoder refId(final long value)
    {
        buffer.putLong(offset + 32, value, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
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
        return 73;
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

    public EiCreatedTransactionPayloadEncoder transactionId(final long value)
    {
        buffer.putLong(offset + 73, value, java.nio.ByteOrder.LITTLE_ENDIAN);
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

        final EiCreatedTransactionPayloadDecoder decoder = new EiCreatedTransactionPayloadDecoder();
        decoder.wrap(buffer, initialOffset, BLOCK_LENGTH, SCHEMA_VERSION);

        return decoder.appendTo(builder);
    }
}
