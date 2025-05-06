/* Generated SBE (Simple Binary Encoding) message codec. */
package org.theenergymashuplab.cts.generated_files;

import org.agrona.DirectBuffer;


/**
 * See EiCreateTenderPayload.java
 */
@SuppressWarnings("all")
public final class EiCreateTenderPayloadDecoder
{
    public static final int BLOCK_LENGTH = 160;
    public static final int TEMPLATE_ID = 5;
    public static final int SCHEMA_ID = 1;
    public static final int SCHEMA_VERSION = 2;
    public static final String SEMANTIC_VERSION = "2.1";
    public static final java.nio.ByteOrder BYTE_ORDER = java.nio.ByteOrder.LITTLE_ENDIAN;

    private final EiCreateTenderPayloadDecoder parentMessage = this;
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

    public EiCreateTenderPayloadDecoder wrap(
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

    public EiCreateTenderPayloadDecoder wrapAndApplyHeader(
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

    public EiCreateTenderPayloadDecoder sbeRewind()
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

    public short atMostOneRaw()
    {
        return ((short)(buffer.getByte(offset + 0) & 0xFF));
    }

    public BooleanType atMostOne()
    {
        return BooleanType.get(((short)(buffer.getByte(offset + 0) & 0xFF)));
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

    public long counterPartyId()
    {
        return buffer.getLong(offset + 1, BYTE_ORDER);
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

    public long executionInstructions()
    {
        return buffer.getLong(offset + 9, BYTE_ORDER);
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

    public long marketId()
    {
        return buffer.getLong(offset + 17, BYTE_ORDER);
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

    public long partyId()
    {
        return buffer.getLong(offset + 25, BYTE_ORDER);
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

    public long requestId()
    {
        return buffer.getLong(offset + 33, BYTE_ORDER);
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

    public long segmentId()
    {
        return (buffer.getInt(offset + 41, BYTE_ORDER) & 0xFFFF_FFFFL);
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

    private final EiTenderTypeDecoder tender = new EiTenderTypeDecoder();

    public EiTenderTypeDecoder tender()
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

        final EiCreateTenderPayloadDecoder decoder = new EiCreateTenderPayloadDecoder();
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
        builder.append("[EiCreateTenderPayload](sbeTemplateId=");
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
        builder.append("atMostOne=");
        builder.append(this.atMostOne());
        builder.append('|');
        builder.append("counterPartyId=");
        builder.append(this.counterPartyId());
        builder.append('|');
        builder.append("executionInstructions=");
        builder.append(this.executionInstructions());
        builder.append('|');
        builder.append("marketId=");
        builder.append(this.marketId());
        builder.append('|');
        builder.append("partyId=");
        builder.append(this.partyId());
        builder.append('|');
        builder.append("requestId=");
        builder.append(this.requestId());
        builder.append('|');
        builder.append("segmentId=");
        builder.append(this.segmentId());
        builder.append('|');
        builder.append("tender=");
        final EiTenderTypeDecoder tender = this.tender();
        if (null != tender)
        {
            tender.appendTo(builder);
        }
        else
        {
            builder.append("null");
        }

        limit(originalLimit);

        return builder;
    }
    
    public EiCreateTenderPayloadDecoder sbeSkip()
    {
        sbeRewind();

        return this;
    }
}
