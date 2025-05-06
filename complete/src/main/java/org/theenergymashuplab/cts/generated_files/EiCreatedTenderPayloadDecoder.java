/* Generated SBE (Simple Binary Encoding) message codec. */
package org.theenergymashuplab.cts.generated_files;

import org.agrona.MutableDirectBuffer;
import org.agrona.DirectBuffer;


/**
 * See EiCreatedTenderPayload.java
 */
@SuppressWarnings("all")
public final class EiCreatedTenderPayloadDecoder
{
    public static final int BLOCK_LENGTH = 69;
    public static final int TEMPLATE_ID = 6;
    public static final int SCHEMA_ID = 1;
    public static final int SCHEMA_VERSION = 2;
    public static final String SEMANTIC_VERSION = "2.1";
    public static final java.nio.ByteOrder BYTE_ORDER = java.nio.ByteOrder.LITTLE_ENDIAN;

    private final EiCreatedTenderPayloadDecoder parentMessage = this;
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

    public EiCreatedTenderPayloadDecoder wrap(
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

    public EiCreatedTenderPayloadDecoder wrapAndApplyHeader(
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

    public EiCreatedTenderPayloadDecoder sbeRewind()
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


    public static int inResponseToId()
    {
        return 2;
    }

    public static int inResponseToSinceVersion()
    {
        return 0;
    }

    public static int inResponseToEncodingOffset()
    {
        return 8;
    }

    public static int inResponseToEncodingLength()
    {
        return 8;
    }

    public static String inResponseToMetaAttribute(final MetaAttribute metaAttribute)
    {
        if (MetaAttribute.PRESENCE == metaAttribute)
        {
            return "required";
        }

        return "";
    }

    public static long inResponseToNullValue()
    {
        return 0xffffffffffffffffL;
    }

    public static long inResponseToMinValue()
    {
        return 0x0L;
    }

    public static long inResponseToMaxValue()
    {
        return 0xfffffffffffffffeL;
    }

    public long inResponseTo()
    {
        return buffer.getLong(offset + 8, BYTE_ORDER);
    }


    public static int marketOrderIdId()
    {
        return 3;
    }

    public static int marketOrderIdSinceVersion()
    {
        return 0;
    }

    public static int marketOrderIdEncodingOffset()
    {
        return 16;
    }

    public static int marketOrderIdEncodingLength()
    {
        return 8;
    }

    public static String marketOrderIdMetaAttribute(final MetaAttribute metaAttribute)
    {
        if (MetaAttribute.PRESENCE == metaAttribute)
        {
            return "required";
        }

        return "";
    }

    public static long marketOrderIdNullValue()
    {
        return 0xffffffffffffffffL;
    }

    public static long marketOrderIdMinValue()
    {
        return 0x0L;
    }

    public static long marketOrderIdMaxValue()
    {
        return 0xfffffffffffffffeL;
    }

    public long marketOrderId()
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


    public static int responseId()
    {
        return 5;
    }

    public static int responseSinceVersion()
    {
        return 0;
    }

    public static int responseEncodingOffset()
    {
        return 32;
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
        response.wrap(buffer, offset + 32);
        return response;
    }

    public static int tenderIdId()
    {
        return 6;
    }

    public static int tenderIdSinceVersion()
    {
        return 0;
    }

    public static int tenderIdEncodingOffset()
    {
        return 61;
    }

    public static int tenderIdEncodingLength()
    {
        return 8;
    }

    public static String tenderIdMetaAttribute(final MetaAttribute metaAttribute)
    {
        if (MetaAttribute.PRESENCE == metaAttribute)
        {
            return "required";
        }

        return "";
    }

    public static long tenderIdNullValue()
    {
        return 0xffffffffffffffffL;
    }

    public static long tenderIdMinValue()
    {
        return 0x0L;
    }

    public static long tenderIdMaxValue()
    {
        return 0xfffffffffffffffeL;
    }

    public long tenderId()
    {
        return buffer.getLong(offset + 61, BYTE_ORDER);
    }


    public static int responseDescriptionId()
    {
        return 7;
    }

    public static int responseDescriptionSinceVersion()
    {
        return 0;
    }

    public static String responseDescriptionCharacterEncoding()
    {
        return java.nio.charset.StandardCharsets.UTF_8.name();
    }

    public static String responseDescriptionMetaAttribute(final MetaAttribute metaAttribute)
    {
        if (MetaAttribute.PRESENCE == metaAttribute)
        {
            return "required";
        }

        return "";
    }

    public static int responseDescriptionHeaderLength()
    {
        return 4;
    }

    public int responseDescriptionLength()
    {
        final int limit = parentMessage.limit();
        return (int)(buffer.getInt(limit, BYTE_ORDER) & 0xFFFF_FFFFL);
    }

    public int skipResponseDescription()
    {
        final int headerLength = 4;
        final int limit = parentMessage.limit();
        final int dataLength = (int)(buffer.getInt(limit, BYTE_ORDER) & 0xFFFF_FFFFL);
        final int dataOffset = limit + headerLength;
        parentMessage.limit(dataOffset + dataLength);

        return dataLength;
    }

    public int getResponseDescription(final MutableDirectBuffer dst, final int dstOffset, final int length)
    {
        final int headerLength = 4;
        final int limit = parentMessage.limit();
        final int dataLength = (int)(buffer.getInt(limit, BYTE_ORDER) & 0xFFFF_FFFFL);
        final int bytesCopied = Math.min(length, dataLength);
        parentMessage.limit(limit + headerLength + dataLength);
        buffer.getBytes(limit + headerLength, dst, dstOffset, bytesCopied);

        return bytesCopied;
    }

    public int getResponseDescription(final byte[] dst, final int dstOffset, final int length)
    {
        final int headerLength = 4;
        final int limit = parentMessage.limit();
        final int dataLength = (int)(buffer.getInt(limit, BYTE_ORDER) & 0xFFFF_FFFFL);
        final int bytesCopied = Math.min(length, dataLength);
        parentMessage.limit(limit + headerLength + dataLength);
        buffer.getBytes(limit + headerLength, dst, dstOffset, bytesCopied);

        return bytesCopied;
    }

    public void wrapResponseDescription(final DirectBuffer wrapBuffer)
    {
        final int headerLength = 4;
        final int limit = parentMessage.limit();
        final int dataLength = (int)(buffer.getInt(limit, BYTE_ORDER) & 0xFFFF_FFFFL);
        parentMessage.limit(limit + headerLength + dataLength);
        wrapBuffer.wrap(buffer, limit + headerLength, dataLength);
    }

    public String responseDescription()
    {
        final int headerLength = 4;
        final int limit = parentMessage.limit();
        final int dataLength = (int)(buffer.getInt(limit, BYTE_ORDER) & 0xFFFF_FFFFL);
        parentMessage.limit(limit + headerLength + dataLength);

        if (0 == dataLength)
        {
            return "";
        }

        final byte[] tmp = new byte[dataLength];
        buffer.getBytes(limit + headerLength, tmp, 0, dataLength);

        return new String(tmp, java.nio.charset.StandardCharsets.UTF_8);
    }

    public String toString()
    {
        if (null == buffer)
        {
            return "";
        }

        final EiCreatedTenderPayloadDecoder decoder = new EiCreatedTenderPayloadDecoder();
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
        builder.append("[EiCreatedTenderPayload](sbeTemplateId=");
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
        builder.append("inResponseTo=");
        builder.append(this.inResponseTo());
        builder.append('|');
        builder.append("marketOrderId=");
        builder.append(this.marketOrderId());
        builder.append('|');
        builder.append("partyId=");
        builder.append(this.partyId());
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
        builder.append("tenderId=");
        builder.append(this.tenderId());
        builder.append('|');
        builder.append("responseDescription=");
        builder.append('\'').append(responseDescription()).append('\'');

        limit(originalLimit);

        return builder;
    }
    
    public EiCreatedTenderPayloadDecoder sbeSkip()
    {
        sbeRewind();
        skipResponseDescription();

        return this;
    }
}
