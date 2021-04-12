/* Generated SBE (Simple Binary Encoding) message codec. */
package baseline;

import org.agrona.MutableDirectBuffer;


/**
 * see MarketCreatedTenderPayload.java
 */
@SuppressWarnings("all")
public class MarketCreatedTenderPayloadEncoder
{
    public static final int BLOCK_LENGTH = 13;
    public static final int TEMPLATE_ID = 2;
    public static final int SCHEMA_ID = 1;
    public static final int SCHEMA_VERSION = 0;
    public static final java.nio.ByteOrder BYTE_ORDER = java.nio.ByteOrder.LITTLE_ENDIAN;

    private final MarketCreatedTenderPayloadEncoder parentMessage = this;
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

    public MarketCreatedTenderPayloadEncoder wrap(final MutableDirectBuffer buffer, final int offset)
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

    public MarketCreatedTenderPayloadEncoder wrapAndApplyHeader(
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

    public static int infoId()
    {
        return 1;
    }

    public static int infoSinceVersion()
    {
        return 0;
    }

    public static int infoEncodingOffset()
    {
        return 0;
    }

    public static int infoEncodingLength()
    {
        return 4;
    }

    public static String infoMetaAttribute(final MetaAttribute metaAttribute)
    {
        if (MetaAttribute.PRESENCE == metaAttribute)
        {
            return "required";
        }

        return "";
    }

    private final VarStringEncodingEncoder info = new VarStringEncodingEncoder();

    public VarStringEncodingEncoder info()
    {
        info.wrap(buffer, offset + 0);
        return info;
    }

    public static int successId()
    {
        return 2;
    }

    public static int successSinceVersion()
    {
        return 0;
    }

    public static int successEncodingOffset()
    {
        return 4;
    }

    public static int successEncodingLength()
    {
        return 1;
    }

    public static String successMetaAttribute(final MetaAttribute metaAttribute)
    {
        if (MetaAttribute.PRESENCE == metaAttribute)
        {
            return "required";
        }

        return "";
    }

    public MarketCreatedTenderPayloadEncoder success(final BooleanType value)
    {
        buffer.putByte(offset + 4, (byte)value.value());
        return this;
    }

    public static int ctsTenderIdId()
    {
        return 3;
    }

    public static int ctsTenderIdSinceVersion()
    {
        return 0;
    }

    public static int ctsTenderIdEncodingOffset()
    {
        return 5;
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

    public MarketCreatedTenderPayloadEncoder ctsTenderId(final long value)
    {
        buffer.putInt(offset + 5, (int)value, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
    }


    public static int parityOrderIdId()
    {
        return 4;
    }

    public static int parityOrderIdSinceVersion()
    {
        return 0;
    }

    public static int parityOrderIdEncodingOffset()
    {
        return 9;
    }

    public static int parityOrderIdEncodingLength()
    {
        return 4;
    }

    public static String parityOrderIdMetaAttribute(final MetaAttribute metaAttribute)
    {
        if (MetaAttribute.PRESENCE == metaAttribute)
        {
            return "required";
        }

        return "";
    }

    private final VarStringEncodingEncoder parityOrderId = new VarStringEncodingEncoder();

    public VarStringEncodingEncoder parityOrderId()
    {
        parityOrderId.wrap(buffer, offset + 9);
        return parityOrderId;
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

        final MarketCreatedTenderPayloadDecoder decoder = new MarketCreatedTenderPayloadDecoder();
        decoder.wrap(buffer, initialOffset, BLOCK_LENGTH, SCHEMA_VERSION);

        return decoder.appendTo(builder);
    }
}
