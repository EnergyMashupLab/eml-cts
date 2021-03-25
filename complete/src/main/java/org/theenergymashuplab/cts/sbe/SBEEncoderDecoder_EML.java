package org.theenergymashuplab.cts.sbe;

import org.agrona.concurrent.UnsafeBuffer;

import baseline.*;

import org.theenergymashuplab.cts.controller.payloads.MarketCreateTenderPayload;

public class SBEEncoderDecoder_EML {
   
    private static final MessageHeaderDecoder MESSAGE_HEADER_DECODER = new MessageHeaderDecoder();
    private static final MessageHeaderEncoder messageHeaderEncoder = new MessageHeaderEncoder();
    private static final  MarketCreateTenderPayloadDecoder Market_Create_Tender_Payload_Decoder = new MarketCreateTenderPayloadDecoder();
    private static final MarketCreateTenderPayloadEncoder market_Create_Tender_Payload_Encoder = new MarketCreateTenderPayloadEncoder();
    
   //private static final  MarketCreatedTenderPayloadDecoder Market_Created_Tender_Payload_Decoder = new MarketCreatedTenderPayloadDecoder();
    //private static final MarketCreatedTenderPayloadEncoder Market_Created_Tender_Payload_ENCODER = new MarketCreatedTenderPayloadEncoder();
   

   
  /*  public static int encode(final UnsafeBuffer directBuffer, MarketCreateTenderPayload marketCreateTenderPayload, MarketCreateTenderPayloadEncoder marketCreateTenderPayloadEncoder)
    {
    	marketCreateTenderPayloadEncoder.wrapAndApplyHeader(directBuffer, 0, messageHeaderEncoder)
                .quantity(marketCreateTenderPayload.getQuantity())
                .price(marketCreateTenderPayload.getPrice())
                .ctsTenderId(marketCreateTenderPayload.getCtsTenderId())
                .side(SideType.S);
    	marketCreateTenderPayloadEncoder.expireTime()
                .length(5)
                .varDataMaxValue();
    	marketCreateTenderPayloadEncoder.bridgeInterval()
                .durationInMinutes(30)
                .length(5)
                .varDataMaxValue();
    	return MessageHeaderEncoder.ENCODED_LENGTH + marketCreateTenderPayloadEncoder.encodedLength();
        //return marketCreateTenderPayloadEncoder;

    } */

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
