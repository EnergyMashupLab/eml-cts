/* Generated SBE (Simple Binary Encoding) message codec. */
package org.theenergymashuplab.cts.generated_files;

import org.agrona.DirectBuffer;


/**
 * See EiCreateTenderPayload.java
 */
@SuppressWarnings("all")
public class EiCreateTenderPayloadDecoder
{
    public static final int BLOCK_LENGTH = 144;
    public static final int TEMPLATE_ID = 5;
    public static final int SCHEMA_ID = 1;
    public static final int SCHEMA_VERSION = 2;
    public static final java.nio.ByteOrder BYTE_ORDER = java.nio.ByteOrder.LITTLE_ENDIAN;

    private final EiCreateTenderPayloadDecoder parentMessage = this;
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
        return buffer.getLong(offset + 1, java.nio.ByteOrder.LITTLE_ENDIAN);
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
        return 4;
    }

    public static String executionInstructionsMetaAttribute(final MetaAttribute metaAttribute)
    {
        if (MetaAttribute.PRESENCE == metaAttribute)
        {
            return "required";
        }

        return "";
    }

    private final VarStringEncodingDecoder executionInstructions = new VarStringEncodingDecoder();

    public VarStringEncodingDecoder executionInstructions()
    {
        executionInstructions.wrap(buffer, offset + 9);
        return executionInstructions;
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
        return 13;
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
        return buffer.getLong(offset + 13, java.nio.ByteOrder.LITTLE_ENDIAN);
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
        return 21;
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
        return buffer.getLong(offset + 21, java.nio.ByteOrder.LITTLE_ENDIAN);
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
        return 29;
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
        return buffer.getLong(offset + 29, java.nio.ByteOrder.LITTLE_ENDIAN);
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
        return 37;
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
        return (buffer.getInt(offset + 37, java.nio.ByteOrder.LITTLE_ENDIAN) & 0xFFFF_FFFFL);
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
        return 41;
    }

    public static int tenderEncodingLength()
    {
        return 103;
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
        tender.wrap(buffer, offset + 41);
        return tender;
    }

    public String toString()
    {
        if (null == buffer)
        {
            return "";
        }

        final EiCreateTenderPayloadDecoder decoder = new EiCreateTenderPayloadDecoder();
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
        builder.append(atMostOne());
        builder.append('|');
        builder.append("counterPartyId=");
        builder.append(counterPartyId());
        builder.append('|');
        builder.append("executionInstructions=");
        final VarStringEncodingDecoder executionInstructions = executionInstructions();
        if (executionInstructions != null)
        {
            executionInstructions.appendTo(builder);
        }
        else
        {
            builder.append("null");
        }
        builder.append('|');
        builder.append("marketId=");
        builder.append(marketId());
        builder.append('|');
        builder.append("partyId=");
        builder.append(partyId());
        builder.append('|');
        builder.append("requestId=");
        builder.append(requestId());
        builder.append('|');
        builder.append("segmentId=");
        builder.append(segmentId());
        builder.append('|');
        builder.append("tender=");
        final EiTenderTypeDecoder tender = tender();
        if (tender != null)
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
}
