/*
 * Copyright 2019-2025 The Energy Mashup Lab
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */

package org.theenergymashuplab.cts.sbe;

import java.time.Instant;

import org.agrona.concurrent.UnsafeBuffer;
import org.theenergymashuplab.cts.EiResponseType;
import org.theenergymashuplab.cts.MarketIdType;
import org.theenergymashuplab.cts.RefIdType;
import org.theenergymashuplab.cts.SubscriptionActionType;
import org.theenergymashuplab.cts.TickerType;
import org.theenergymashuplab.cts.controller.payloads.EiManageTickerSubscriptionPayload;
import org.theenergymashuplab.cts.controller.payloads.EiManagedTickerSubscriptionPayload;
import org.theenergymashuplab.cts.generated_files.EiManageTickerSubscriptionPayloadDecoder;
import org.theenergymashuplab.cts.generated_files.EiManageTickerSubscriptionPayloadEncoder;
import org.theenergymashuplab.cts.generated_files.EiManagedTickerSubscriptionPayloadDecoder;
import org.theenergymashuplab.cts.generated_files.EiManagedTickerSubscriptionPayloadEncoder;
import org.theenergymashuplab.cts.generated_files.MessageHeaderDecoder;
import org.theenergymashuplab.cts.generated_files.MessageHeaderEncoder;

public class EiTickerSubscriptionPayloadEncoderDecoder {

	public static int EiManageTickerSubscriptionEncode(
			EiManageTickerSubscriptionPayloadEncoder eiManageTickerSubscriptionEncoder,
			UnsafeBuffer directBuffer,
			MessageHeaderEncoder messageHeaderEncoder,
			EiManageTickerSubscriptionPayload eiManageTickerSubscriptionPayload) {

		eiManageTickerSubscriptionEncoder.wrapAndApplyHeader(directBuffer, 0, messageHeaderEncoder);

		eiManageTickerSubscriptionEncoder.tickerType(org.theenergymashuplab.cts.generated_files.TickerType.get(eiManageTickerSubscriptionPayload.getTickerType().getValue()));

		eiManageTickerSubscriptionEncoder.marketId(eiManageTickerSubscriptionPayload.getMarketId().getMyUidId());
		// segment id should be short
		eiManageTickerSubscriptionEncoder.segmentId((short) eiManageTickerSubscriptionPayload.getSegmentId());

		eiManageTickerSubscriptionEncoder.subscriptionActionRequested(org.theenergymashuplab.cts.generated_files.SubscriptionActionType.get(eiManageTickerSubscriptionPayload.getSubscriptionActionRequested().getValue()));

		eiManageTickerSubscriptionEncoder.subscriptionRequestId(eiManageTickerSubscriptionPayload.getSubscriptionRequestId().getMyUidId());

//		System.out.println("\n-------------------------------------------------------------------------");
//		System.out.println("EiManageTickerSubscriptionEncode Encoded :-");
//		System.out.println(eiManageTickerSubscriptionEncoder.toString());

		return messageHeaderEncoder.ENCODED_LENGTH + eiManageTickerSubscriptionEncoder.encodedLength();

	}

	public static EiManageTickerSubscriptionPayload EiManageTickerSubscriptionPayloadDecode(
			EiManageTickerSubscriptionPayloadDecoder eiManageTickerSubscriptionPayloadDecoder,
			UnsafeBuffer directBuffer,
			int bufferOffset,
			int actingBlockLength, int actingVersion) throws Exception {

		eiManageTickerSubscriptionPayloadDecoder.wrap(directBuffer, bufferOffset, actingBlockLength, actingVersion);

//		System.out.println("\n-------------------------------------------------------------------------");
//		System.out.println("eiManageTickerSubscriptionPayloadDecode Decoded :-");
//		System.out.println(eiManageTickerSubscriptionPayloadDecoder.toString());
		TickerType tickerType = TickerType.fromSbe(eiManageTickerSubscriptionPayloadDecoder.tickerType().value());
		MarketIdType marketId = new MarketIdType();
		int segmentid = eiManageTickerSubscriptionPayloadDecoder.segmentId();
		SubscriptionActionType actionType = SubscriptionActionType.fromSbe(
				eiManageTickerSubscriptionPayloadDecoder.subscriptionActionRequested().value());
		RefIdType subscriptionRequestId = new RefIdType();

		subscriptionRequestId.setMyUidId(eiManageTickerSubscriptionPayloadDecoder.subscriptionRequestId());
		marketId.setMyUidId(eiManageTickerSubscriptionPayloadDecoder.marketId());

		EiManageTickerSubscriptionPayload eiManageTickerSubscriptionPayload = new EiManageTickerSubscriptionPayload(
				marketId,
				segmentid,
				actionType,
				subscriptionRequestId,
				tickerType,
				// the decoder does not have "partyId"
				null);

		return eiManageTickerSubscriptionPayload;

	}
	
	public static int EiManagedTickerSubscriptionEncode(
			EiManagedTickerSubscriptionPayloadEncoder eiManagedTickerSubscriptionEncoder,
			UnsafeBuffer directBuffer,
			MessageHeaderEncoder messageHeaderEncoder,
			EiManagedTickerSubscriptionPayload eiManagedTickerSubscriptionPayload) {

		eiManagedTickerSubscriptionEncoder.wrapAndApplyHeader(directBuffer, 0, messageHeaderEncoder);

        eiManagedTickerSubscriptionEncoder.tickerType(org.theenergymashuplab.cts.generated_files.TickerType.get(eiManagedTickerSubscriptionPayload.getTickerType().getValue()));
		eiManagedTickerSubscriptionEncoder.multicastListenReference(eiManagedTickerSubscriptionPayload.multicastListenReference);

        Instant responseCreatedDateTime = eiManagedTickerSubscriptionPayload.getResponse().getCreatedDateTime();
        eiManagedTickerSubscriptionEncoder.response().createdDateTime()
            .seconds(responseCreatedDateTime.getEpochSecond())
            .nano(responseCreatedDateTime.getNano());

        eiManagedTickerSubscriptionEncoder.response().inResponseTo(eiManagedTickerSubscriptionPayload.getResponse().getInResponseTo().getMyUidId());
        
        eiManagedTickerSubscriptionEncoder.response().responseCode(eiManagedTickerSubscriptionPayload.getResponse().getResponseCode());
        
        eiManagedTickerSubscriptionEncoder.responseDescription(eiManagedTickerSubscriptionPayload.response.responseDescription);
        
        short detailShort = eiManagedTickerSubscriptionPayload.getResponse().getResponseDetail().getValue();
        
        System.out.println(">>> Encoding ResponseDetailType short: " + detailShort);
        eiManagedTickerSubscriptionEncoder.response().responseDetail(
            org.theenergymashuplab.cts.generated_files.ResponseDetailType.get(detailShort)
        );

        
        eiManagedTickerSubscriptionEncoder.subscriptionActionTaken(org.theenergymashuplab.cts.generated_files.SubscriptionActionType.get(eiManagedTickerSubscriptionPayload.getSubscriptionActionTaken().getValue()));

		eiManagedTickerSubscriptionEncoder
				.subscriptionRequestId(eiManagedTickerSubscriptionPayload.getSubscriptionRequestId().getMyUidId());

		System.out.println("\n-------------------------------------------------------------------------");
		System.out.println("EiManagedTickerSubscriptionEncode Encoded :-");
		System.out.println(eiManagedTickerSubscriptionEncoder.toString());

		return messageHeaderEncoder.ENCODED_LENGTH + eiManagedTickerSubscriptionEncoder.encodedLength();

	}
	
	

	public static EiManagedTickerSubscriptionPayload EiManagedTickerSubscriptionPayloadDecode(
			EiManagedTickerSubscriptionPayloadDecoder decoder,
			UnsafeBuffer directBuffer,
			int bufferOffset,
			int actingBlockLength, 
			int actingVersion) throws Exception {

		
	    MessageHeaderDecoder hdr = new MessageHeaderDecoder();
	    hdr.wrap(directBuffer, 0);
	    
	    decoder.wrap(directBuffer, bufferOffset, actingBlockLength, actingVersion);
		EiManagedTickerSubscriptionPayload eiManagedTickerSubscriptionPayload = new EiManagedTickerSubscriptionPayload();

//	        
//		System.out.println("\n-------------------------------------------------------------------------");
//		System.out.println("eiManagedTickerSubscriptionPayloadDecode Decoded :-");
//		System.out.println(eiManagedTickerSubscriptionPayloadDecoder.toString());
//		

		TickerType tickerType = TickerType.fromSbe(decoder.tickerType().value());
		
		
		SubscriptionActionType actionTaken = SubscriptionActionType.fromSbe(
				decoder.subscriptionActionTaken().value());
		
		RefIdType subscriptionRequestId = new RefIdType();
		subscriptionRequestId.setMyUidId(decoder.subscriptionRequestId());
		
		// Response
		//EiResponseType response = EiResponseTypeEncoderDecoder.Decode(decoder.response());
		
		   var responseDecoder = decoder.response();
		    EiResponseType response = new EiResponseType();
		
        org.theenergymashuplab.cts.ResponseDetailType appEnum = org.theenergymashuplab.cts.ResponseDetailType.valueOf(decoder.response().responseDetail().name());

        // Decode createdDateTime
		// Decode response section
		//var responseDecoder = decoder.response();

		long seconds = responseDecoder.createdDateTime().seconds();
		int nanos = (int) responseDecoder.createdDateTime().nano();
		Instant createdDateTime = Instant.ofEpochSecond(seconds, nanos);

		RefIdType inResponseTo = new RefIdType();
		inResponseTo.setMyUidId(responseDecoder.inResponseTo());

		long responseCode = responseDecoder.responseCode();
        
		String description = decoder.responseDescription();
 
    	var responseDetail = org.theenergymashuplab.cts.ResponseDetailType.fromSbe(
    			responseDecoder.responseDetail().value());
    	
    	String multicastListenReference = decoder.multicastListenReference();

    	response.setCreatedDateTime(createdDateTime);
    	response.setInResponseTo(inResponseTo);
    	response.setResponseCode(responseCode);
    	response.setResponseDescription(description);
    	response.setResponseDetail(responseDetail);
        

        eiManagedTickerSubscriptionPayload.setTickerType(tickerType);
		eiManagedTickerSubscriptionPayload.setMulticastListenReference(multicastListenReference);
		eiManagedTickerSubscriptionPayload.setSubscriptionRequestId(subscriptionRequestId);
        eiManagedTickerSubscriptionPayload.setSubscriptionActionTaken(actionTaken);
		eiManagedTickerSubscriptionPayload.setResponse(response);



		return eiManagedTickerSubscriptionPayload;

	}

}
