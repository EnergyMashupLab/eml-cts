package org.theenergymashuplab.cts.sbe;

import org.agrona.concurrent.UnsafeBuffer;
//import org.theenergymashuplab.cts.SideType;
import org.theenergymashuplab.cts.SideType;
import org.theenergymashuplab.cts.controller.payloads.MarketCreateTenderPayload;
import org.theenergymashuplab.cts.controller.payloads.MarketCreateTransactionPayload;
import org.theenergymashuplab.cts.generated_files.MarketCreateTenderPayloadEncoder;
import org.theenergymashuplab.cts.generated_files.MarketCreateTransactionPayloadDecoder;
import org.theenergymashuplab.cts.generated_files.MessageHeaderEncoder;
//import org.theenergymashuplab.cts.generated_files.SideType;

//import baseline.*;


public class SBEEncoderDecoder_EML {
   

	public static int encode(
			MarketCreateTenderPayloadEncoder marketCreateTenderPayloadEncoder, 
			UnsafeBuffer directBuffer, 
			MessageHeaderEncoder messageHeaderEncoder, 
			MarketCreateTenderPayload marketCreateTenderPayload)
    {
    	
    	org.theenergymashuplab.cts.SideType sideT = marketCreateTenderPayload.getSide();
    	
        org.theenergymashuplab.cts.generated_files.SideType generatedSideType = convertToGeneratedSideType(sideT);

		marketCreateTenderPayloadEncoder.wrapAndApplyHeader(directBuffer, 0, messageHeaderEncoder)
                .quantity(marketCreateTenderPayload.getQuantity())
                .price(marketCreateTenderPayload.getPrice())
                .ctsTenderId(marketCreateTenderPayload.getCtsTenderId())
                .side(generatedSideType);
    	marketCreateTenderPayloadEncoder.expireTime()
                .length(5)
                .varDataMaxValue();
    	marketCreateTenderPayloadEncoder.bridgeInterval()
                .durationInMinutes(30)
                .length(5)
                .varDataMaxValue();
    	

    	System.out.println("");
    	System.out.println("-------------------------------------------------------------------------");
    	System.out.println("MarketCreateTenderPayload Encoded  :-");
    	System.out.println(marketCreateTenderPayloadEncoder.toString());
    	
    	return messageHeaderEncoder.ENCODED_LENGTH + marketCreateTenderPayloadEncoder.encodedLength();

    }

	private static org.theenergymashuplab.cts.generated_files.SideType convertToGeneratedSideType(org.theenergymashuplab.cts.SideType sideT) {
	    if (sideT == org.theenergymashuplab.cts.SideType.BUY) {
	        return org.theenergymashuplab.cts.generated_files.SideType.BUY;
	    } else {
	        return org.theenergymashuplab.cts.generated_files.SideType.SELL;
	    }
	}
	
	
    public static MarketCreateTransactionPayload decode( MarketCreateTransactionPayloadDecoder marketCreateTransactionPayloadDecoder, 
    		 UnsafeBuffer directBuffer, int bufferOffset, int actingBlockLength, int actingVersion) throws Exception{

    	final StringBuilder sb = new StringBuilder();
        marketCreateTransactionPayloadDecoder.wrap(directBuffer, 0, actingBlockLength, actingVersion);
        
       
        sb.append("\nmarketCreateTransactionPayload.info=").append(marketCreateTransactionPayloadDecoder.info());
        sb.append("\nmarketCreateTransactionPayload.quantity=").append(marketCreateTransactionPayloadDecoder.quantity());
        sb.append("\nmarketCreateTransactionPayload.price=").append(marketCreateTransactionPayloadDecoder.price());
        sb.append("\nmarketCreateTransactionPayload.ctsTenderId=").append(marketCreateTransactionPayloadDecoder.ctsTenderId());
        sb.append("\nmarketCreateTransactionPayload.encodedLength=").append(marketCreateTransactionPayloadDecoder.encodedLength());
        sb.append("\nmarketCreateTransactionPayload.parityOrderId=").append(marketCreateTransactionPayloadDecoder.parityOrderId());
        sb.append("\nmarketCreateTransactionPayload.matchNumber=").append(marketCreateTransactionPayloadDecoder.matchNumber());
        
        
        System.out.println("");
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("MarketCreateTransactionPayload Decoded :-");
        System.out.println(sb);
        
        MarketCreateTransactionPayload marketCreateTransactionPayload = new MarketCreateTransactionPayload();
        marketCreateTransactionPayload.setQuantity(marketCreateTransactionPayloadDecoder.quantity());
        marketCreateTransactionPayload.setPrice(marketCreateTransactionPayloadDecoder.price());
        marketCreateTransactionPayload.setCtsTenderId(marketCreateTransactionPayloadDecoder.ctsTenderId());
        marketCreateTransactionPayload.setMatchNumber(marketCreateTransactionPayloadDecoder.matchNumber());
        
        if (marketCreateTransactionPayloadDecoder.side() == org.theenergymashuplab.cts.generated_files.SideType.BUY) {
            marketCreateTransactionPayload.setSide(org.theenergymashuplab.cts.SideType.BUY);
        } else {
            marketCreateTransactionPayload.setSide(org.theenergymashuplab.cts.SideType.SELL);
        }
        return marketCreateTransactionPayload;
       //return marketCreateTransactionPayloadDecoder.matchNumber();
    }

}