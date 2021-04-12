/* Generated SBE (Simple Binary Encoding) message codec. */
package baseline;

import org.agrona.MutableDirectBuffer;


/**
 * see MarketCreateTenderPayload.java
 */
@SuppressWarnings("all")
public class MarketCreateTenderPayloadEncoder
{
    public static final int BLOCK_LENGTH = 29;
    public static final int TEMPLATE_ID = 1;
    public static final int SCHEMA_ID = 1;
    public static final int SCHEMA_VERSION = 0;
    public static final java.nio.ByteOrder BYTE_ORDER = java.nio.ByteOrder.LITTLE_ENDIAN;

    private final MarketCreateTenderPayloadEncoder parentMessage = this;
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

    public MarketCreateTenderPayloadEncoder wrap(final MutableDirectBuffer buffer, final int offset)
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

    public MarketCreateTenderPayloadEncoder wrapAndApplyHeader(
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
        return 4;
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

    public MarketCreateTenderPayloadEncoder side(final SideType value)
    {
        buffer.putByte(offset + 4, value.value());
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
        return 5;
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

    public MarketCreateTenderPayloadEncoder quantity(final long value)
    {
        buffer.putInt(offset + 5, (int)value, java.nio.ByteOrder.LITTLE_ENDIAN);
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
        return 9;
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

    public MarketCreateTenderPayloadEncoder price(final long value)
    {
        buffer.putInt(offset + 9, (int)value, java.nio.ByteOrder.LITTLE_ENDIAN);
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
        return 13;
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

    public MarketCreateTenderPayloadEncoder ctsTenderId(final long value)
    {
        buffer.putInt(offset + 13, (int)value, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
    }


    public static int expireTimeId()
    {
        return 6;
    }

    public static int expireTimeSinceVersion()
    {
        return 0;
    }

    public static int expireTimeEncodingOffset()
    {
        return 17;
    }

    public static int expireTimeEncodingLength()
    {
        return 4;
    }

    public static String expireTimeMetaAttribute(final MetaAttribute metaAttribute)
    {
        if (MetaAttribute.PRESENCE == metaAttribute)
        {
            return "required";
        }

        return "";
    }

    private final BridgeInstantEncoder expireTime = new BridgeInstantEncoder();

    public BridgeInstantEncoder expireTime()
    {
        expireTime.wrap(buffer, offset + 17);
        return expireTime;
    }

    public static int bridgeIntervalId()
    {
        return 7;
    }

    public static int bridgeIntervalSinceVersion()
    {
        return 0;
    }

    public static int bridgeIntervalEncodingOffset()
    {
        return 21;
    }

    public static int bridgeIntervalEncodingLength()
    {
        return 8;
    }

    public static String bridgeIntervalMetaAttribute(final MetaAttribute metaAttribute)
    {
        if (MetaAttribute.PRESENCE == metaAttribute)
        {
            return "required";
        }

        return "";
    }

    private final BridgeIntervalEncoder bridgeInterval = new BridgeIntervalEncoder();

    public BridgeIntervalEncoder bridgeInterval()
    {
        bridgeInterval.wrap(buffer, offset + 21);
        return bridgeInterval;
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

        final MarketCreateTenderPayloadDecoder decoder = new MarketCreateTenderPayloadDecoder();
        decoder.wrap(buffer, initialOffset, BLOCK_LENGTH, SCHEMA_VERSION);

        return decoder.appendTo(builder);
    }
}
