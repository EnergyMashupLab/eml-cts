package org.theenergymashuplab.cts.sbe;

import java.time.Duration;
import java.time.Instant;

import org.agrona.concurrent.UnsafeBuffer;
import org.theenergymashuplab.cts.ActorIdType;
import org.theenergymashuplab.cts.EiResponse;
import org.theenergymashuplab.cts.EiTender;
import org.theenergymashuplab.cts.Interval;
import org.theenergymashuplab.cts.RefIdType;
import org.theenergymashuplab.cts.TenderIdType;
import org.theenergymashuplab.cts.TransactiveState;
import org.theenergymashuplab.cts.controller.payloads.EiCreateTenderPayload;
import org.theenergymashuplab.cts.controller.payloads.EiCreatedTenderPayload;

import baseline.*;


public class EiTenderEncoderDEcoder {

	public static int eiCreateTenderEncode(EiCreateTenderPayloadEncoder eiCreateTenderPayloadEncoder, 
			UnsafeBuffer directBuffer, MessageHeaderEncoder messageHeaderEncoder, 
			EiCreateTenderPayload eiCreateTenderPayload)
    {
		eiCreateTenderPayloadEncoder.wrapAndApplyHeader(directBuffer, 0, messageHeaderEncoder);
		
		eiCreateTenderPayloadEncoder.counterPartyId()
		.value(eiCreateTenderPayload.getCounterPartyId().getMyUidId());
		
		
		eiCreateTenderPayloadEncoder.partyId()
		.value(eiCreateTenderPayload.getPartyId().getMyUidId());
		
		
		eiCreateTenderPayloadEncoder.requestId()
		.value(eiCreateTenderPayload.getRequestId().getMyUidId());
		
		
		
		//instantEncoder.expirationTime()
		
		eiCreateTenderPayloadEncoder.tender()
		.price(eiCreateTenderPayload.getTender().getPrice())
		.quantity(eiCreateTenderPayload.getTender().getQuantity());
		
		if(eiCreateTenderPayload.getTender().getSide()==org.theenergymashuplab.cts.SideType.BUY)
		{
			eiCreateTenderPayloadEncoder.tender().side(baseline.SideType.B);
		}else {
			eiCreateTenderPayloadEncoder.tender().side(baseline.SideType.S);
		}
		//.transactiveState(eiCreateTenderPayload.getTender().getTransactiveState());
		
		eiCreateTenderPayloadEncoder.tender().tenderId()
		.value(eiCreateTenderPayload.getTender().getTenderId().getMyUidId());
		
		eiCreateTenderPayloadEncoder.tender().expirationTime()
		.seconds(10)
		.nano(00);
		
		
		eiCreateTenderPayloadEncoder.tender().interval().duration()
		.seconds(10)
		.nano(00);
		
		eiCreateTenderPayloadEncoder.tender().interval().dtStart()
		.seconds(10)
		.nano(00);
		
		System.out.println("");
		System.out.println("-------------------------------------------------------------------------");
    	System.out.println("EiCreateTenderPayload Encoded :-");
    	System.out.println(eiCreateTenderPayloadEncoder.toString());
    	
    	return messageHeaderEncoder.ENCODED_LENGTH + eiCreateTenderPayloadEncoder.encodedLength();

    }
	
	public static EiCreateTenderPayload eiCreateTenderPayloadDecode( EiCreateTenderPayloadDecoder eiCreateTenderPayloadDecoder , 
   		 UnsafeBuffer directBuffer, int bufferOffset, int actingBlockLength, int actingVersion) throws Exception{

		eiCreateTenderPayloadDecoder.wrap(directBuffer, 0, actingBlockLength, actingVersion);
		
	   System.out.println("");
       System.out.println("-------------------------------------------------------------------------");
       System.out.println("EiCreateTenderPayload Decoded :-");
       System.out.println(eiCreateTenderPayloadDecoder.toString());
       
       EiCreateTenderPayload eiCreateTenderPayload = new EiCreateTenderPayload();
       
       ActorIdType counterPartyId = new ActorIdType();
       counterPartyId.setMyUidId(eiCreateTenderPayloadDecoder.counterPartyId().value());
       
       ActorIdType partyId = new ActorIdType();
       partyId.setMyUidId(eiCreateTenderPayloadDecoder.partyId().value());
       
       RefIdType requestId = new RefIdType();
       requestId.setMyUidId(eiCreateTenderPayloadDecoder.requestId().value());
       
       EiTender tender = new EiTender();
       tender.setPrice(eiCreateTenderPayloadDecoder.tender().price());
       tender.setQuantity(eiCreateTenderPayloadDecoder.tender().quantity());
       if(eiCreateTenderPayloadDecoder.tender().side() == baseline.SideType.B) {
    	   tender.setSide(org.theenergymashuplab.cts.SideType.BUY);
       }else {
    	   tender.setSide(org.theenergymashuplab.cts.SideType.SELL);
       }
       
       //tender.setTransactiveState(transactiveState);
       
       Instant instant = Instant.parse("2020-06-20T05:00:00Z");
      tender.setExpireTime(instant);
      
      tender.setExpireTime(instant);
      
      Interval interval = new Interval(30,instant);
      tender.setInterval(interval);
       
       eiCreateTenderPayload.setCounterPartyId(counterPartyId);
       eiCreateTenderPayload.setPartyId(partyId);
       //eiCreateTenderPayload.setRequestId(requestId);
       eiCreateTenderPayload.setTender(tender);
      
      return eiCreateTenderPayload;
   }
	
	public static int eiCreatedTenderEncode(EiCreatedTenderPayloadEncoder eiCreatedTenderPayloadEncoder, 
			UnsafeBuffer directBuffer, MessageHeaderEncoder messageHeaderEncoder, EiCreatedTenderPayload eiCreatedTenderPayload)
	{

		eiCreatedTenderPayloadEncoder.wrapAndApplyHeader(directBuffer, 0, messageHeaderEncoder);
		
		eiCreatedTenderPayloadEncoder.counterPartyId()
		.value(eiCreatedTenderPayload.getCounterPartyId().getMyUidId());
		
		
		eiCreatedTenderPayloadEncoder.partyId()
		.value(eiCreatedTenderPayload.getPartyId().getMyUidId());
		
		
		eiCreatedTenderPayloadEncoder.refId()
		.value(eiCreatedTenderPayload.getRefId().getMyUidId());
		
		eiCreatedTenderPayloadEncoder.tenderId()
		.value(eiCreatedTenderPayload.getTenderId().getMyUidId());
		
		eiCreatedTenderPayloadEncoder.response()
		.responseCode(200)
		.responseDescription();
		
		System.out.println("");
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("EiCreatedTenderPayload Encoded :-");
		System.out.println(eiCreatedTenderPayloadEncoder.toString());

		return messageHeaderEncoder.ENCODED_LENGTH + eiCreatedTenderPayloadEncoder.encodedLength();
		
	}

	
	public static EiCreatedTenderPayload eiCreatedTenderPayloadDecoder( EiCreatedTenderPayloadDecoder eiCreatedTenderPayloadDecoder , 
	   		 UnsafeBuffer directBuffer, int bufferOffset, int actingBlockLength, int actingVersion) throws Exception{

		   eiCreatedTenderPayloadDecoder.wrap(directBuffer, 0, actingBlockLength, actingVersion);
		
		   System.out.println("");
	       System.out.println("-------------------------------------------------------------------------");
	       System.out.println("EiCreatedTenderPayload Decoded :-");
	       System.out.println(eiCreatedTenderPayloadDecoder.toString());
	       
	       EiCreatedTenderPayload eiCreatedTenderPayload = new EiCreatedTenderPayload();
	       
	       ActorIdType counterPartyId = new ActorIdType();
	       counterPartyId.setMyUidId(eiCreatedTenderPayloadDecoder.counterPartyId().value());
	       eiCreatedTenderPayload.setCounterPartyId(counterPartyId);
	       
	       ActorIdType partyId = new ActorIdType();
	       partyId.setMyUidId(eiCreatedTenderPayloadDecoder.partyId().value());
	       eiCreatedTenderPayload.setPartyId(partyId);
	       
	       EiResponse response = new EiResponse();
	       response.setResponseCode(eiCreatedTenderPayloadDecoder.response().responseCode());
	       response.setResponseDescription("Successfull");
	       eiCreatedTenderPayload.setResponse(response);
	       
	       TenderIdType tenderId = new TenderIdType();
	       tenderId.setMyUidId(eiCreatedTenderPayloadDecoder.tenderId().value());
	       eiCreatedTenderPayload.setTenderId(tenderId);
	       
	       return eiCreatedTenderPayload;
	      
	   }
	   
}




