/* Generated SBE (Simple Binary Encoding) message codec. */
package org.theenergymashuplab.cts.generated_files;

import org.agrona.MutableDirectBuffer;


/**
 * See EiCreateTenderPayload.java
 */
@SuppressWarnings("all")
public class EiCreateTenderPayloadEncoder
{
    public static final int BLOCK_LENGTH = 160;
    public static final int TEMPLATE_ID = 5;
    public static final int SCHEMA_ID = 1;
    public static final int SCHEMA_VERSION = 2;
    public static final java.nio.ByteOrder BYTE_ORDER = java.nio.ByteOrder.LITTLE_ENDIAN;

    private final EiCreateTenderPayloadEncoder parentMessage = this;
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

    public EiCreateTenderPayloadEncoder wrap(final MutableDirectBuffer buffer, final int offset)
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

    public EiCreateTenderPayloadEncoder wrapAndApplyHeader(
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

    public static int atMostOneId()
    {
        return 1;
    }

    public static int atMostOneSinceVersion()
    {
        return 0;
    }

    public static int atMostOneEncodingOffset()
    {
        return 0;
    }

    public static int atMostOneEncodingLength()
    {
        return 1;
    }

    public static String atMostOneMetaAttribute(final MetaAttribute metaAttribute)
    {
        if (MetaAttribute.PRESENCE == metaAttribute)
        {
            return "required";
        }

        return "";
    }

    public EiCreateTenderPayloadEncoder atMostOne(final BooleanType value)
    {
        buffer.putByte(offset + 0, (byte)value.value());
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
        return 1;
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

    public EiCreateTenderPayloadEncoder counterPartyId(final long value)
    {
        buffer.putLong(offset + 1, value, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
    }


    public static int executionInstructionsId()
    {
        return 3;
    }

    public static int executionInstructionsSinceVersion()
    {
        return 0;
    }

    public static int executionInstructionsEncodingOffset()
    {
        return 9;
    }

    public static int executionInstructionsEncodingLength()
    {
        return 8;
    }

    public static String executionInstructionsMetaAttribute(final MetaAttribute metaAttribute)
    {
        if (MetaAttribute.PRESENCE == metaAttribute)
        {
            return "required";
        }

        return "";
    }

    public static long executionInstructionsNullValue()
    {
        return 0xffffffffffffffffL;
    }

    public static long executionInstructionsMinValue()
    {
        return 0x0L;
    }

    public static long executionInstructionsMaxValue()
    {
        return 0xfffffffffffffffeL;
    }

    public EiCreateTenderPayloadEncoder executionInstructions(final long value)
    {
        buffer.putLong(offset + 9, value, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
    }


    public static int marketIdId()
    {
        return 4;
    }

    public static int marketIdSinceVersion()
    {
        return 0;
    }

    public static int marketIdEncodingOffset()
    {
        return 17;
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

    public EiCreateTenderPayloadEncoder marketId(final long value)
    {
        buffer.putLong(offset + 17, value, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
    }


    public static int partyIdId()
    {
        return 5;
    }

    public static int partyIdSinceVersion()
    {
        return 0;
    }

    public static int partyIdEncodingOffset()
    {
        return 25;
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

    public EiCreateTenderPayloadEncoder partyId(final long value)
    {
        buffer.putLong(offset + 25, value, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
    }


    public static int requestIdId()
    {
        return 6;
    }

    public static int requestIdSinceVersion()
    {
        return 0;
    }

    public static int requestIdEncodingOffset()
    {
        return 33;
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

    public EiCreateTenderPayloadEncoder requestId(final long value)
    {
        buffer.putLong(offset + 33, value, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
    }


    public static int segmentIdId()
    {
        return 7;
    }

    public static int segmentIdSinceVersion()
    {
        return 0;
    }

    public static int segmentIdEncodingOffset()
    {
        return 41;
    }

    public static int segmentIdEncodingLength()
    {
        return 4;
    }

    public static String segmentIdMetaAttribute(final MetaAttribute metaAttribute)
    {
        if (MetaAttribute.PRESENCE == metaAttribute)
        {
            return "required";
        }

        return "";
    }

    public static long segmentIdNullValue()
    {
        return 4294967295L;
    }

    public static long segmentIdMinValue()
    {
        return 0L;
    }

    public static long segmentIdMaxValue()
    {
        return 4294967294L;
    }

    public EiCreateTenderPayloadEncoder segmentId(final long value)
    {
        buffer.putInt(offset + 41, (int)value, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
    }


    public static int tenderId()
    {
        return 8;
    }

    public static int tenderSinceVersion()
    {
        return 0;
    }

    public static int tenderEncodingOffset()
    {
        return 45;
    }

    public static int tenderEncodingLength()
    {
        return 115;
    }

    public static String tenderMetaAttribute(final MetaAttribute metaAttribute)
    {
        if (MetaAttribute.PRESENCE == metaAttribute)
        {
            return "required";
        }

        return "";
    }

    private final EiTenderTypeEncoder tender = new EiTenderTypeEncoder();

    public EiTenderTypeEncoder tender()
    {
        tender.wrap(buffer, offset + 45);
        return tender;
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

        final EiCreateTenderPayloadDecoder decoder = new EiCreateTenderPayloadDecoder();
        decoder.wrap(buffer, initialOffset, BLOCK_LENGTH, SCHEMA_VERSION);

        return decoder.appendTo(builder);
    }
}
