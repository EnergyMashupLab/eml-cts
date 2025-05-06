/* Generated SBE (Simple Binary Encoding) message codec. */
package org.theenergymashuplab.cts.generated_files;

import org.agrona.MutableDirectBuffer;
import org.agrona.DirectBuffer;


/**
 * see MarketCreateTransactionPayload.java
 */
@SuppressWarnings("all")
public final class MarketCreateTransactionPayloadEncoder
{
    public static final int BLOCK_LENGTH = 17;
    public static final int TEMPLATE_ID = 3;
    public static final int SCHEMA_ID = 1;
    public static final int SCHEMA_VERSION = 2;
    public static final String SEMANTIC_VERSION = "2.1";
    public static final java.nio.ByteOrder BYTE_ORDER = java.nio.ByteOrder.LITTLE_ENDIAN;

    private final MarketCreateTransactionPayloadEncoder parentMessage = this;
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

    public MarketCreateTransactionPayloadEncoder wrap(final MutableDirectBuffer buffer, final int offset)
    {
        if (buffer != this.buffer)
        {
            this.buffer = buffer;
        }
        this.offset = offset;
        limit(offset + BLOCK_LENGTH);

        return this;
    }

    public MarketCreateTransactionPayloadEncoder wrapAndApplyHeader(
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

    public static int sideId()
    {
        return 2;
    }

    public static int sideSinceVersion()
    {
        return 0;
    }

    public static int sideEncodingOffset()
    {
        return 0;
    }

    public static int sideEncodingLength()
    {
        return 1;
    }

    public static String sideMetaAttribute(final MetaAttribute metaAttribute)
    {
        if (MetaAttribute.PRESENCE == metaAttribute)
        {
            return "required";
        }

        return "";
    }

    public MarketCreateTransactionPayloadEncoder side(final SideType value)
    {
        buffer.putByte(offset + 0, value.value());
        return this;
    }

    public static int quantityId()
    {
        return 3;
    }

    public static int quantitySinceVersion()
    {
        return 0;
    }

    public static int quantityEncodingOffset()
    {
        return 1;
    }

    public static int quantityEncodingLength()
    {
        return 4;
    }

    public static String quantityMetaAttribute(final MetaAttribute metaAttribute)
    {
        if (MetaAttribute.PRESENCE == metaAttribute)
        {
            return "required";
        }

        return "";
    }

    public static long quantityNullValue()
    {
        return 4294967295L;
    }

    public static long quantityMinValue()
    {
        return 0L;
    }

    public static long quantityMaxValue()
    {
        return 4294967294L;
    }

    public MarketCreateTransactionPayloadEncoder quantity(final long value)
    {
        buffer.putInt(offset + 1, (int)value, BYTE_ORDER);
        return this;
    }


    public static int priceId()
    {
        return 4;
    }

    public static int priceSinceVersion()
    {
        return 0;
    }

    public static int priceEncodingOffset()
    {
        return 5;
    }

    public static int priceEncodingLength()
    {
        return 4;
    }

    public static String priceMetaAttribute(final MetaAttribute metaAttribute)
    {
        if (MetaAttribute.PRESENCE == metaAttribute)
        {
            return "required";
        }

        return "";
    }

    public static long priceNullValue()
    {
        return 4294967295L;
    }

    public static long priceMinValue()
    {
        return 0L;
    }

    public static long priceMaxValue()
    {
        return 4294967294L;
    }

    public MarketCreateTransactionPayloadEncoder price(final long value)
    {
        buffer.putInt(offset + 5, (int)value, BYTE_ORDER);
        return this;
    }


    public static int ctsTenderIdId()
    {
        return 5;
    }

    public static int ctsTenderIdSinceVersion()
    {
        return 0;
    }

    public static int ctsTenderIdEncodingOffset()
    {
        return 9;
    }

    public static int ctsTenderIdEncodingLength()
    {
        return 4;
    }

    public static String ctsTenderIdMetaAttribute(final MetaAttribute metaAttribute)
    {
        if (MetaAttribute.PRESENCE == metaAttribute)
        {
            return "required";
        }

        return "";
    }

    public static long ctsTenderIdNullValue()
    {
        return 4294967295L;
    }

    public static long ctsTenderIdMinValue()
    {
        return 0L;
    }

    public static long ctsTenderIdMaxValue()
    {
        return 4294967294L;
    }

    public MarketCreateTransactionPayloadEncoder ctsTenderId(final long value)
    {
        buffer.putInt(offset + 9, (int)value, BYTE_ORDER);
        return this;
    }


    public static int matchNumberId()
    {
        return 7;
    }

    public static int matchNumberSinceVersion()
    {
        return 0;
    }

    public static int matchNumberEncodingOffset()
    {
        return 13;
    }

    public static int matchNumberEncodingLength()
    {
        return 4;
    }

    public static String matchNumberMetaAttribute(final MetaAttribute metaAttribute)
    {
        if (MetaAttribute.PRESENCE == metaAttribute)
        {
            return "required";
        }

        return "";
    }

    public static long matchNumberNullValue()
    {
        return 4294967295L;
    }

    public static long matchNumberMinValue()
    {
        return 0L;
    }

    public static long matchNumberMaxValue()
    {
        return 4294967294L;
    }

    public MarketCreateTransactionPayloadEncoder matchNumber(final long value)
    {
        buffer.putInt(offset + 13, (int)value, BYTE_ORDER);
        return this;
    }


    public static int infoId()
    {
        return 1;
    }

    public static String infoCharacterEncoding()
    {
        return java.nio.charset.StandardCharsets.UTF_8.name();
    }

    public static String infoMetaAttribute(final MetaAttribute metaAttribute)
    {
        if (MetaAttribute.PRESENCE == metaAttribute)
        {
            return "required";
        }

        return "";
    }

    public static int infoHeaderLength()
    {
        return 4;
    }

    public MarketCreateTransactionPayloadEncoder putInfo(final DirectBuffer src, final int srcOffset, final int length)
    {
        if (length > 1073741824)
        {
            throw new IllegalStateException("length > maxValue for type: " + length);
        }

        final int headerLength = 4;
        final int limit = parentMessage.limit();
        parentMessage.limit(limit + headerLength + length);
        buffer.putInt(limit, length, BYTE_ORDER);
        buffer.putBytes(limit + headerLength, src, srcOffset, length);

        return this;
    }

    public MarketCreateTransactionPayloadEncoder putInfo(final byte[] src, final int srcOffset, final int length)
    {
        if (length > 1073741824)
        {
            throw new IllegalStateException("length > maxValue for type: " + length);
        }

        final int headerLength = 4;
        final int limit = parentMessage.limit();
        parentMessage.limit(limit + headerLength + length);
        buffer.putInt(limit, length, BYTE_ORDER);
        buffer.putBytes(limit + headerLength, src, srcOffset, length);

        return this;
    }

    public MarketCreateTransactionPayloadEncoder info(final String value)
    {
        final byte[] bytes = (null == value || value.isEmpty()) ? org.agrona.collections.ArrayUtil.EMPTY_BYTE_ARRAY : value.getBytes(java.nio.charset.StandardCharsets.UTF_8);

        final int length = bytes.length;
        if (length > 1073741824)
        {
            throw new IllegalStateException("length > maxValue for type: " + length);
        }

        final int headerLength = 4;
        final int limit = parentMessage.limit();
        parentMessage.limit(limit + headerLength + length);
        buffer.putInt(limit, length, BYTE_ORDER);
        buffer.putBytes(limit + headerLength, bytes, 0, length);

        return this;
    }

    public static int parityOrderIdId()
    {
        return 6;
    }

    public static String parityOrderIdCharacterEncoding()
    {
        return java.nio.charset.StandardCharsets.UTF_8.name();
    }

    public static String parityOrderIdMetaAttribute(final MetaAttribute metaAttribute)
    {
        if (MetaAttribute.PRESENCE == metaAttribute)
        {
            return "required";
        }

        return "";
    }

    public static int parityOrderIdHeaderLength()
    {
        return 4;
    }

    public MarketCreateTransactionPayloadEncoder putParityOrderId(final DirectBuffer src, final int srcOffset, final int length)
    {
        if (length > 1073741824)
        {
            throw new IllegalStateException("length > maxValue for type: " + length);
        }

        final int headerLength = 4;
        final int limit = parentMessage.limit();
        parentMessage.limit(limit + headerLength + length);
        buffer.putInt(limit, length, BYTE_ORDER);
        buffer.putBytes(limit + headerLength, src, srcOffset, length);

        return this;
    }

    public MarketCreateTransactionPayloadEncoder putParityOrderId(final byte[] src, final int srcOffset, final int length)
    {
        if (length > 1073741824)
        {
            throw new IllegalStateException("length > maxValue for type: " + length);
        }

        final int headerLength = 4;
        final int limit = parentMessage.limit();
        parentMessage.limit(limit + headerLength + length);
        buffer.putInt(limit, length, BYTE_ORDER);
        buffer.putBytes(limit + headerLength, src, srcOffset, length);

        return this;
    }

    public MarketCreateTransactionPayloadEncoder parityOrderId(final String value)
    {
        final byte[] bytes = (null == value || value.isEmpty()) ? org.agrona.collections.ArrayUtil.EMPTY_BYTE_ARRAY : value.getBytes(java.nio.charset.StandardCharsets.UTF_8);

        final int length = bytes.length;
        if (length > 1073741824)
        {
            throw new IllegalStateException("length > maxValue for type: " + length);
        }

        final int headerLength = 4;
        final int limit = parentMessage.limit();
        parentMessage.limit(limit + headerLength + length);
        buffer.putInt(limit, length, BYTE_ORDER);
        buffer.putBytes(limit + headerLength, bytes, 0, length);

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

        final MarketCreateTransactionPayloadDecoder decoder = new MarketCreateTransactionPayloadDecoder();
        decoder.wrap(buffer, offset, BLOCK_LENGTH, SCHEMA_VERSION);

        return decoder.appendTo(builder);
    }
}
