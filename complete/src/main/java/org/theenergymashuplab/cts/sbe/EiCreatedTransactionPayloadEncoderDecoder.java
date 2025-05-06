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

import org.agrona.concurrent.UnsafeBuffer;
import org.theenergymashuplab.cts.ActorIdType;
import org.theenergymashuplab.cts.EiResponseType;
import org.theenergymashuplab.cts.MarketTransactionIdType;
import org.theenergymashuplab.cts.RefIdType;
import org.theenergymashuplab.cts.TransactionIdType;
import org.theenergymashuplab.cts.controller.payloads.EiCreatedTransactionPayload;
import org.theenergymashuplab.cts.generated_files.EiCreatedTransactionPayloadDecoder;
import org.theenergymashuplab.cts.generated_files.EiCreatedTransactionPayloadEncoder;
import org.theenergymashuplab.cts.generated_files.MessageHeaderEncoder;
import org.theenergymashuplab.cts.generated_files.ResponseDetailType;
import org.theenergymashuplab.cts.generated_files.VarStringEncodingDecoder;
import org.theenergymashuplab.cts.generated_files.VarStringEncodingEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Instant;

public class EiCreatedTransactionPayloadEncoderDecoder {

	public static int eiCreatedTransactionEncode(
			EiCreatedTransactionPayloadEncoder encoder,
			UnsafeBuffer directBuffer,
			MessageHeaderEncoder messageHeaderEncoder,
			EiCreatedTransactionPayload payload) {

		encoder.wrapAndApplyHeader(directBuffer, 0, messageHeaderEncoder);

		// Counter Party Id field
		encoder.counterPartyId(payload.getCounterPartyId().getMyUidId())
		.marketTransactionId(payload.getMarketTransactionId().getMyUidId())
		.partyId(payload.getPartyId().getMyUidId())
		.recipientTransactionId(payload.getRecipientTransactionId().getMyUidId())
		.refId(payload.getRefId().getMyUidId());

		// Response field ----
		Instant responseCreatedDateTime = payload.getResponse().getCreatedDateTime();
		encoder.response().createdDateTime()
				.seconds(responseCreatedDateTime.getEpochSecond())
				.nano(responseCreatedDateTime.getNano());
		
		
		// 1. Encode the description
//		String descString = payload.getResponse().getResponseDescription();
//		byte[] descBytes = descString.getBytes(StandardCharsets.UTF_8);
//
//		VarStringEncodingEncoder descEnc = encoder.response().responseDescription();
//		descEnc.length(descBytes.length);
//
//		// 2. Write string data after length field
//		int descDataOffset = descEnc.offset() + VarStringEncodingEncoder.lengthEncodingLength();
//		descEnc.buffer().putBytes(descDataOffset, descBytes);

		// ⚠️ DO NOT blindly set encoder.limit(...) here, let SBE handle this internally
		// Remove: encoder.limit(descDataOffset + descBytes.length);

		    
	    // Encode enum: ResponseDetailType
		encoder.response().responseDetail(org.theenergymashuplab.cts.generated_files.ResponseDetailType.valueOf(payload.getResponse().getResponseDetail().name())); 
		// Transaction Id field
		encoder.transactionId(payload.getTransactionId().getMyUidId());

		
		
		System.out.println("\n-------------------------------------------------------------------------");
		System.out.println("EiCreatedTransaction encoded :-");
		System.out.println(encoder.toString());
		
		return messageHeaderEncoder.ENCODED_LENGTH + encoder.encodedLength();

	}

	public static EiCreatedTransactionPayload eiCreatedTransactionDecode(
			EiCreatedTransactionPayloadDecoder eiCreatedTransactionDecoder, UnsafeBuffer directBuffer,
			int bufferOffset,
			int actingBlockLength, int actingVersion) throws Exception {

		eiCreatedTransactionDecoder.wrap(directBuffer, bufferOffset, actingBlockLength, actingVersion);

		System.out.println("\n-------------------------------------------------------------------------");
		System.out.println("EiCreatedTransactionDecode Decoded :-");
		System.out.println(eiCreatedTransactionDecoder.toString());

		// CounterPartyId
		ActorIdType counterPartyId = new ActorIdType();
		counterPartyId.setMyUidId(eiCreatedTransactionDecoder.counterPartyId());

		// MarketTransactionId
		MarketTransactionIdType marketTransactionId = new MarketTransactionIdType();
		marketTransactionId.setMyUidId(eiCreatedTransactionDecoder.marketTransactionId());

		// PartyId
		ActorIdType partyId = new ActorIdType();
		partyId.setMyUidId(eiCreatedTransactionDecoder.partyId());

		// RecipientTransactionId
		TransactionIdType recipientTransactionId = new TransactionIdType();
		recipientTransactionId.setMyUidId(eiCreatedTransactionDecoder.recipientTransactionId());

		// RefId
		RefIdType refId = new RefIdType();
		refId.setMyUidId(eiCreatedTransactionDecoder.refId());

		// Response
		//EiResponseType response = EiResponseTypeEncoderDecoder.Decode(eiCreatedTransactionDecoder.response());
		EiResponseType response = new EiResponseType();
		
		org.theenergymashuplab.cts.ResponseDetailType appEnum = org.theenergymashuplab.cts.ResponseDetailType.valueOf(eiCreatedTransactionDecoder.response().responseDetail().name());


			// Decode createdDateTime
			long seconds = eiCreatedTransactionDecoder.response().createdDateTime().seconds();
			int nanos = (int) eiCreatedTransactionDecoder.response().createdDateTime().nano();
			response.setCreatedDateTime(Instant.ofEpochSecond(seconds, nanos));
			
			
			
//		    // Decode responseDescription using VarStringEncodingDecoder
//		    VarStringEncodingDecoder descDec = eiCreatedTransactionDecoder.response().responseDescription();
//		    int descLength = (int) descDec.length();
//		    int descOffset = descDec.offset() + VarStringEncodingDecoder.lengthEncodingLength();
//		    byte[] descBytes = new byte[descLength];
//		    descDec.buffer().getBytes(descOffset, descBytes);
//		    String decoded = new String(descBytes, StandardCharsets.UTF_8);
//		    response.setResponseDescription(decoded);



			
		RefIdType inResponseTo= new RefIdType();
		inResponseTo.setMyUidId(eiCreatedTransactionDecoder.response().inResponseTo());
	
		response.setInResponseTo(inResponseTo);
		
		response.setResponseCode(eiCreatedTransactionDecoder.response().responseCode());
		


		response.setResponseDetail(appEnum);
		//response.setResponseDetail(responseDetail);
		
		// TransactionId
		TransactionIdType transactionId = new TransactionIdType();
		transactionId.setMyUidId(eiCreatedTransactionDecoder.transactionId());

		EiCreatedTransactionPayload eiCreatedTransactionPayload = new EiCreatedTransactionPayload();
		eiCreatedTransactionPayload.setCounterPartyId(counterPartyId);
		eiCreatedTransactionPayload.setMarketTransactionId(marketTransactionId);
		eiCreatedTransactionPayload.setPartyId(partyId);
		eiCreatedTransactionPayload.setRecipientTransactionId(recipientTransactionId);
		eiCreatedTransactionPayload.setRefId(refId);
		eiCreatedTransactionPayload.setResponse(response);
		eiCreatedTransactionPayload.setTransactionId(transactionId);

		return eiCreatedTransactionPayload;
	}

}