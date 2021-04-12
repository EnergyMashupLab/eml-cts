/* Generated SBE (Simple Binary Encoding) message codec. */
package baseline;

import org.agrona.DirectBuffer;


/**
 * see MarketCreateTransactionPayload.java
 */
@SuppressWarnings("all")
public class MarketCreateTransactionPayloadDecoder
{
    public static final int BLOCK_LENGTH = 25;
    public static final int TEMPLATE_ID = 3;
    public static final int SCHEMA_ID = 1;
    public static final int SCHEMA_VERSION = 0;
    public static final java.nio.ByteOrder BYTE_ORDER = java.nio.ByteOrder.LITTLE_ENDIAN;

    private final MarketCreateTransactionPayloadDecoder parentMessage = this;
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

    public MarketCreateTransactionPayloadDecoder wrap(
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

    private final VarStringEncodingDecoder info = new VarStringEncodingDecoder();

    public VarStringEncodingDecoder info()
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

    public SideType side()
    {
        return SideType.get(buffer.getByte(offset + 4));
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

    public long quantity()
    {
        return (buffer.getInt(offset + 5, java.nio.ByteOrder.LITTLE_ENDIAN) & 0xFFFF_FFFFL);
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

    public long price()
    {
        return (buffer.getInt(offset + 9, java.nio.ByteOrder.LITTLE_ENDIAN) & 0xFFFF_FFFFL);
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

    public long ctsTenderId()
    {
        return (buffer.getInt(offset + 13, java.nio.ByteOrder.LITTLE_ENDIAN) & 0xFFFF_FFFFL);
    }


    public static int parityOrderIdId()
    {
        return 6;
    }

    public static int parityOrderIdSinceVersion()
    {
        return 0;
    }

    public static int parityOrderIdEncodingOffset()
    {
        return 17;
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

    private final VarStringEncodingDecoder parityOrderId = new VarStringEncodingDecoder();

    public VarStringEncodingDecoder parityOrderId()
    {
        parityOrderId.wrap(buffer, offset + 17);
        return parityOrderId;
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
        return 21;
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

    public long matchNumber()
    {
        return (buffer.getInt(offset + 21, java.nio.ByteOrder.LITTLE_ENDIAN) & 0xFFFF_FFFFL);
    }


    public String toString()
    {
        if (null == buffer)
        {
            return "";
        }

        final MarketCreateTransactionPayloadDecoder decoder = new MarketCreateTransactionPayloadDecoder();
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
        builder.append("[MarketCreateTransactionPayload](sbeTemplateId=");
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
        builder.append("info=");
        final VarStringEncodingDecoder info = info();
        if (info != null)
        {
            info.appendTo(builder);
        }
        else
        {
            builder.append("null");
        }
        builder.append('|');
        builder.append("side=");
        builder.append(side());
        builder.append('|');
        builder.append("quantity=");
        builder.append(quantity());
        builder.append('|');
        builder.append("price=");
        builder.append(price());
        builder.append('|');
        builder.append("ctsTenderId=");
        builder.append(ctsTenderId());
        builder.append('|');
        builder.append("parityOrderId=");
        final VarStringEncodingDecoder parityOrderId = parityOrderId();
        if (parityOrderId != null)
        {
            parityOrderId.appendTo(builder);
        }
        else
        {
            builder.append("null");
        }
        builder.append('|');
        builder.append("matchNumber=");
        builder.append(matchNumber());

        limit(originalLimit);

        return builder;
    }
}
