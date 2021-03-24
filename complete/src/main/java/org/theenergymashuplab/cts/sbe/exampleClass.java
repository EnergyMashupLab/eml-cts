package org.theenergymashuplab.cts.sbe;

import org.agrona.concurrent.UnsafeBuffer;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.READ;
import static java.nio.file.StandardOpenOption.WRITE;

import baseline.*;

/**
 * Example encode and decode of a complex message using generated stub codecs.
 */

public class exampleClass {
    private static final String ENCODING_FILENAME = "sbe.encoding.filename";
    //private static final byte[] INFO;
    //private static final byte[] QUANTITY;


    private static final MessageHeaderDecoder MESSAGE_HEADER_DECODER = new MessageHeaderDecoder();
    private static final MessageHeaderEncoder MESSAGE_HEADER_ENCODER = new MessageHeaderEncoder();
    //private static final  MarketCreatedTenderPayloadDecoder Market_Created_Tender_Payload_Decoder = new MarketCreatedTenderPayloadDecoder();
    //private static final MarketCreatedTenderPayloadEncoder Market_Created_Tender_Payload_ENCODER = new MarketCreatedTenderPayloadEncoder();
    private static final  MarketCreateTenderPayloadDecoder Market_Create_Tender_Payload_Decoder = new MarketCreateTenderPayloadDecoder();
    private static final MarketCreateTenderPayloadEncoder Market_Create_Tender_Payload_ENCODER = new MarketCreateTenderPayloadEncoder();

    /*static
    {
        try
        {
            //INFO = "testingInfo".getBytes(MarketCreateTenderPayloadEncoder.infoCharacterEncoding());
            QUANTITY = "100".getBytes(MarketCreateTenderPayloadEncoder.quantit);
        } catch (final UnsupportedEncodingException ex)
        {
            throw new RuntimeException(ex);
        }
    }*/

    public static void main(final String[] args) throws Exception {
        //System.out.println("\n*** Basic Stub Example ***");

        final ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4096);
        final UnsafeBuffer directBuffer = new UnsafeBuffer(byteBuffer);


        final int encodingLengthPlusHeader = encode(Market_Create_Tender_Payload_ENCODER, directBuffer, MESSAGE_HEADER_ENCODER);
        /*System.out.println("CarEncoder toString:");
        System.out.println(Market_Create_Tender_Payload_ENCODER.toString());
        System.out.println(directBuffer);
        System.out.println(directBuffer.byteBuffer());
        System.out.println(Market_Create_Tender_Payload_ENCODER.buffer());*/
        System.out.println("Encoder toString:");
        System.out.println(Market_Create_Tender_Payload_ENCODER.toString());


        FileChannel fc = new FileOutputStream("data1.txt").getChannel();
        //fc.write(directBuffer.byteBuffer());
        //fc.write(Market_Create_Tender_Payload_ENCODER.buffer().byteBuffer());
        byteBuffer.limit(encodingLengthPlusHeader);
        fc.write(Market_Create_Tender_Payload_ENCODER.buffer().byteBuffer());
        fc.write(ByteBuffer.wrap(Market_Create_Tender_Payload_ENCODER.toString().getBytes()));
        fc.write(byteBuffer);
        fc.write(directBuffer.byteBuffer());
        fc.close();

        // Optionally write the encoded buffer to a file for decoding by the On-The-Fly decoder

        final String encodingFilename = System.getProperty(ENCODING_FILENAME);
        if (encodingFilename != null) {
            try (FileChannel channel = FileChannel.open(Paths.get(encodingFilename), READ, WRITE, CREATE)) {
                byteBuffer.limit(encodingLengthPlusHeader);
                channel.write(byteBuffer);
            }
        }

        // Decode the encoded message
        int bufferOffset = 0;
        MESSAGE_HEADER_DECODER.wrap(directBuffer, bufferOffset);

        // Lookup the applicable flyweight to decode this type of message based on templateId and version.
        final int templateId = MESSAGE_HEADER_DECODER.templateId();
        if (templateId != MarketCreateTenderPayloadEncoder.TEMPLATE_ID) {
            throw new IllegalStateException("Template ids do not match");
        }

        final int actingBlockLength = MESSAGE_HEADER_DECODER.blockLength();
        final int actingVersion = MESSAGE_HEADER_DECODER.version();

        bufferOffset += MESSAGE_HEADER_DECODER.encodedLength();
        decode(Market_Create_Tender_Payload_Decoder, directBuffer, bufferOffset, actingBlockLength, actingVersion);
        System.out.println(Market_Create_Tender_Payload_Decoder);

    }

    public static int encode(
            final MarketCreateTenderPayloadEncoder MarketCreateTenderPayload, final UnsafeBuffer directBuffer, final MessageHeaderEncoder messageHeaderEncoder
    )
    {
        MarketCreateTenderPayload.wrapAndApplyHeader(directBuffer, 0, messageHeaderEncoder)
                .quantity(99)
                .price(50)
                .ctsTenderId(111)
                .side(SideType.S);
        MarketCreateTenderPayload.expireTime()
                .length(5)
                .varDataMaxValue();
        MarketCreateTenderPayload.bridgeInterval()
                .durationInMinutes(30)
                .length(5)
                .varDataMaxValue();
        return MessageHeaderEncoder.ENCODED_LENGTH + MarketCreateTenderPayload.encodedLength();

    }

    public static void decode(final MarketCreateTenderPayloadDecoder MarketCreateTenderPayload, final UnsafeBuffer directBuffer, final int bufferOffset, final int actingBlockLength, final int actingVersion) throws Exception{
        final byte[] buffer = new byte[128];
        final StringBuilder sb = new StringBuilder();
        MarketCreateTenderPayload.wrap(directBuffer, bufferOffset, actingBlockLength, actingVersion);
        sb.append("\nmarketCreateTenderPayload.info=").append(MarketCreateTenderPayload.info());
        sb.append("\nmarketCreateTenderPayload.quantity=").append(MarketCreateTenderPayload.quantity());
        sb.append("\nmarketCreateTenderPayload.price=").append(MarketCreateTenderPayload.price());
        sb.append("\nmarketCreateTenderPayload.ctsTenderId=").append(MarketCreateTenderPayload.ctsTenderId());
        sb.append("\nmarketCreateTenderPayload.encodedLength=").append(MarketCreateTenderPayload.encodedLength());
        final BridgeInstantDecoder ep = MarketCreateTenderPayload.expireTime();
        sb.append("\nmarketCreateTenderPayload.expireTime.length=").append(ep.length());
        sb.append("\nmarketCreateTenderPayload.expireTime.varData=").append(ep.varDataMaxValue());
        final BridgeIntervalDecoder bid = MarketCreateTenderPayload.bridgeInterval();
        sb.append("\nmarketCreateTenderPayload.bridgeInterval.durationInMinutes=").append(bid.durationInMinutes());
        sb.append("\nmarketCreateTenderPayload.bridgeInterval.length=").append(bid.length());
        sb.append("\nmarketCreateTenderPayload.bridgeInterval.varData=").append(bid.varDataMaxValue());


        System.out.println(sb);
    }
}
