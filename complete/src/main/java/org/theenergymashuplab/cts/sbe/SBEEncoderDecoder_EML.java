package org.theenergymashuplab.cts.sbe;

import org.agrona.concurrent.UnsafeBuffer;
import org.theenergymashuplab.cts.controller.payloads.MarketCreateTenderPayload;

import baseline.*;


public class SBEEncoderDecoder_EML {
   
    public static int encode(MarketCreateTenderPayloadEncoder marketCreateTenderPayloadEncoder, UnsafeBuffer directBuffer, MessageHeaderEncoder messageHeaderEncoder, MarketCreateTenderPayload marketCreateTenderPayload)
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
    	return messageHeaderEncoder.ENCODED_LENGTH + marketCreateTenderPayloadEncoder.encodedLength();

    }

    public static long decode(final MarketCreateTransactionPayloadDecoder marketCreateTransactionPayloadDecoder, final UnsafeBuffer directBuffer, final int bufferOffset, final int actingBlockLength, final int actingVersion) throws Exception{
       // final byte[] buffer = new byte[128];
        final StringBuilder sb = new StringBuilder();
        marketCreateTransactionPayloadDecoder.wrap(directBuffer, bufferOffset, actingBlockLength, actingVersion);
        sb.append("\nmarketCreateTenderPayload.info=").append(marketCreateTransactionPayloadDecoder.info());
        sb.append("\nmarketCreateTenderPayload.quantity=").append(marketCreateTransactionPayloadDecoder.quantity());
        sb.append("\nmarketCreateTenderPayload.price=").append(marketCreateTransactionPayloadDecoder.price());
        sb.append("\nmarketCreateTenderPayload.ctsTenderId=").append(marketCreateTransactionPayloadDecoder.ctsTenderId());
        sb.append("\nmarketCreateTenderPayload.encodedLength=").append(marketCreateTransactionPayloadDecoder.encodedLength());
        sb.append("\nmarketCreateTenderPayload.parityOrderId=").append(marketCreateTransactionPayloadDecoder.parityOrderId());
        sb.append("\nmarketCreateTenderPayload.matchNumber=").append(marketCreateTransactionPayloadDecoder.matchNumber());
        
        System.out.println(sb);
        
       return marketCreateTransactionPayloadDecoder.matchNumber();
    }

}
