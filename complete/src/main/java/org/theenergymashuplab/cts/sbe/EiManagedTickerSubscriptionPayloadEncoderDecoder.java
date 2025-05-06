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

import java.nio.charset.StandardCharsets;
import java.time.Instant;

import org.agrona.concurrent.UnsafeBuffer;
import org.theenergymashuplab.cts.EiResponseType;
import org.theenergymashuplab.cts.RefIdType;
import org.theenergymashuplab.cts.SubscriptionActionType;
import org.theenergymashuplab.cts.TickerType;
import org.theenergymashuplab.cts.controller.payloads.EiManagedTickerSubscriptionPayload;
import org.theenergymashuplab.cts.generated_files.EiManagedTickerSubscriptionPayloadDecoder;
import org.theenergymashuplab.cts.generated_files.EiManagedTickerSubscriptionPayloadEncoder;
import org.theenergymashuplab.cts.generated_files.MessageHeaderDecoder;
import org.theenergymashuplab.cts.generated_files.MessageHeaderEncoder;
import org.theenergymashuplab.cts.generated_files.VarStringEncodingDecoder;
import org.theenergymashuplab.cts.generated_files.VarStringEncodingEncoder;

public class EiManagedTickerSubscriptionPayloadEncoderDecoder {

	public static int EiManagedTickerSubscriptionEncode(
			EiManagedTickerSubscriptionPayloadEncoder eiManagedTickerSubscriptionEncoder,
			UnsafeBuffer directBuffer,
			MessageHeaderEncoder messageHeaderEncoder,
			EiManagedTickerSubscriptionPayload eiManagedTickerSubscriptionPayload) {

		eiManagedTickerSubscriptionEncoder.wrapAndApplyHeader(directBuffer, 0, messageHeaderEncoder);

        eiManagedTickerSubscriptionEncoder.tickerType(org.theenergymashuplab.cts.generated_files.TickerType.get(eiManagedTickerSubscriptionPayload.getTickerType().getValue()));


//     // 1) Grab your Java string and UTF-8 bytes
//        String multicastRef = eiManagedTickerSubscriptionPayload.getMulticastListenReference();
//        byte[] refBytes = multicastRef.getBytes(StandardCharsets.UTF_8);
//
//        // 2) Wrap the VarString encoder at its offset
//        VarStringEncodingEncoder refEnc =
//            eiManagedTickerSubscriptionEncoder.multicastListenReference();
//
//        // 3) Write the 4-byte length prefix (little-endian)
//        refEnc.length(refBytes.length);
//
//        // 4) Compute where the actual data goes, then copy the bytes
//        int refDataOffset =
//            refEnc.offset() + VarStringEncodingEncoder.lengthEncodingLength();
//        refEnc.buffer().putBytes(refDataOffset, refBytes);
//
//        // 5) (Optional) track the end offset so you can bump your limit later
//        int refEnd = refDataOffset + refBytes.length;

        
		int length = eiManagedTickerSubscriptionPayload.getMulticastListenReference().length();
		
	
 		String multicastListenReference = eiManagedTickerSubscriptionPayload.getMulticastListenReference();
 		eiManagedTickerSubscriptionEncoder.multicastListenReference().buffer().putStringUtf8(0,
 				multicastListenReference, length);
        
        
        Instant responseCreatedDateTime = eiManagedTickerSubscriptionPayload.getResponse().getCreatedDateTime();
        eiManagedTickerSubscriptionEncoder.response().createdDateTime()
            .seconds(responseCreatedDateTime.getEpochSecond())
            .nano(responseCreatedDateTime.getNano());

        eiManagedTickerSubscriptionEncoder.response().inResponseTo(eiManagedTickerSubscriptionPayload.getResponse().getInResponseTo().getMyUidId());
        
        eiManagedTickerSubscriptionEncoder.response().responseCode(eiManagedTickerSubscriptionPayload.getResponse().getResponseCode());
        
//        
//        String desc = eiManagedTickerSubscriptionPayload
//                .getResponse()
//                .getResponseDescription();
//byte[] descBytes = desc.getBytes(StandardCharsets.UTF_8);
//VarStringEncodingEncoder descEnc = 
//  eiManagedTickerSubscriptionEncoder
//      .response()
//      .responseDescription();
//descEnc.length(descBytes.length);
//int descDataOffset = descEnc.offset() + VarStringEncodingEncoder.lengthEncodingLength();
//descEnc.buffer().putBytes(descDataOffset, descBytes);
//int descEnd = descDataOffset + descBytes.length;
//
//eiManagedTickerSubscriptionEncoder.limit(Math.max(refEnd, descEnd));

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
		
//		// 1️⃣ Get the VarString decoder for multicastListenReference
//		VarStringEncodingDecoder mDec =
//		    decoder.multicastListenReference();
//
//		// 2️⃣ Read the 4-byte length prefix
//		int mLen = (int)mDec.length();
//
//		// 3️⃣ Allocate a buffer and copy exactly those bytes
//		byte[] mBuf = new byte[mLen];
//		directBuffer.getBytes(
//		    mDec.offset() + VarStringEncodingDecoder.lengthEncodingLength(),
//		    mBuf, 0, mLen
//		);
//
//		// 4️⃣ Build the Java String
//		String multicastListenReference =
//		    new String(mBuf, StandardCharsets.UTF_8);

	    
		eiManagedTickerSubscriptionPayload
			.setMulticastListenReference(
					decoder
							.multicastListenReference()
							.buffer()
							.getStringUtf8(decoder
									.multicastListenReference()
									.offset()));
		
		
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
        

//	    // 5c) responseDescription (var-string)
//	    VarStringEncodingDecoder dDec = responseDecoder.responseDescription();
//	    int    dLen    = (int)dDec.length();
//	    byte[] dBytes  = new byte[dLen];
//	    directBuffer.getBytes(
//	        dDec.offset() + VarStringEncodingDecoder.varDataEncodingOffset(),
//	        dBytes, 0, dLen
//	    );
//	    String description = new String(dBytes, StandardCharsets.UTF_8);

 
    	var responseDetail = org.theenergymashuplab.cts.ResponseDetailType.fromSbe(
    			responseDecoder.responseDetail().value());
    	
    	//EiResponseType response = new EiResponseType((int) responseCode, description, responseDetail);
    	response.setCreatedDateTime(createdDateTime);
    	response.setInResponseTo(inResponseTo);
    	response.setResponseCode(responseCode);
    	//response.setResponseDescription(description);
    	response.setResponseDetail(responseDetail);
        

        eiManagedTickerSubscriptionPayload.setTickerType(tickerType);
		//eiManagedTickerSubscriptionPayload.setMulticastListenReference(multicastListenReference);
		eiManagedTickerSubscriptionPayload.setSubscriptionRequestId(subscriptionRequestId);
        eiManagedTickerSubscriptionPayload.setSubscriptionActionTaken(actionTaken);
		eiManagedTickerSubscriptionPayload.setResponse(response);



		return eiManagedTickerSubscriptionPayload;

	}

}
