/* Generated SBE (Simple Binary Encoding) message codec. */
package org.theenergymashuplab.cts.generated_files;

import org.agrona.MutableDirectBuffer;


/**
 * See EiCreatedTenderPayload.java
 */
@SuppressWarnings("all")
public class EiCreatedTenderPayloadEncoder
{
    public static final int BLOCK_LENGTH = 73;
    public static final int TEMPLATE_ID = 6;
    public static final int SCHEMA_ID = 1;
    public static final int SCHEMA_VERSION = 2;
    public static final java.nio.ByteOrder BYTE_ORDER = java.nio.ByteOrder.LITTLE_ENDIAN;

    private final EiCreatedTenderPayloadEncoder parentMessage = this;
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

    public EiCreatedTenderPayloadEncoder wrap(final MutableDirectBuffer buffer, final int offset)
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

    public EiCreatedTenderPayloadEncoder wrapAndApplyHeader(
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

    public EiCreatedTenderPayloadEncoder counterPartyId(final long value)
    {
        buffer.putLong(offset + 0, value, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
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

    public EiCreatedTenderPayloadEncoder inResponseTo(final long value)
    {
        buffer.putLong(offset + 8, value, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
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

    public EiCreatedTenderPayloadEncoder marketOrderId(final long value)
    {
        buffer.putLong(offset + 16, value, java.nio.ByteOrder.LITTLE_ENDIAN);
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

    public EiCreatedTenderPayloadEncoder partyId(final long value)
    {
        buffer.putLong(offset + 24, value, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
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
        return 65;
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

    public EiCreatedTenderPayloadEncoder tenderId(final long value)
    {
        buffer.putLong(offset + 65, value, java.nio.ByteOrder.LITTLE_ENDIAN);
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

        final EiCreatedTenderPayloadDecoder decoder = new EiCreatedTenderPayloadDecoder();
        decoder.wrap(buffer, initialOffset, BLOCK_LENGTH, SCHEMA_VERSION);

        return decoder.appendTo(builder);
    }
}
