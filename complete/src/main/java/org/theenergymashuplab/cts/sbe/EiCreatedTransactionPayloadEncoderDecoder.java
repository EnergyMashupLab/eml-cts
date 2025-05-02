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
		encoder.counterPartyId(payload.getCounterPartyId().getMyUidId());
		//Market Transaction Id
		encoder.marketTransactionId(payload.getMarketTransactionId().getMyUidId());
		// Party Id field
		encoder.partyId(payload.getPartyId().getMyUidId());
		// Recipient Transaction Id field
		encoder.recipientTransactionId(payload.getRecipientTransactionId().getMyUidId());
		// RefId field
		encoder.refId(payload.getRefId().getMyUidId());

		// Response field ----
		// Response -> Created Date Time ---
		Instant responseCreatedDateTime = payload.getResponse().getCreatedDateTime();
		
		encoder.response().createdDateTime()
				.seconds(responseCreatedDateTime.getEpochSecond())
				.nano(responseCreatedDateTime.getNano());
		
		// Response-> inResponseTo
		encoder.response().inResponseTo(payload.getResponse().getInResponseTo().getMyUidId());

		// Response -> Response Code
		encoder.response().responseCode(payload.getResponse().getResponseCode());

		// Response -> Description		
		// -- Safe Encoding --
		String description = payload.getResponse().getResponseDescription();
		byte[] descBytes = description.getBytes(StandardCharsets.UTF_8);
		UnsafeBuffer descBuffer = new UnsafeBuffer(descBytes);

		// Safe - capture encoder object
		var responseDescriptionEncoder = encoder.response().responseDescription();

		// Safe - calculate starting offset
		int offset = responseDescriptionEncoder.offset();

		// 🛠 1. Write length manually into buffer at offset
		responseDescriptionEncoder.buffer().putInt(offset, descBytes.length);

		// 🛠 2. Write UTF-8 bytes manually at offset+4
		responseDescriptionEncoder.buffer().putBytes(
		    offset + 4,
		    descBuffer,
		    0,
		    descBytes.length
		);



	    // Encode enum: ResponseDetailType
		encoder.response().responseDetail(org.theenergymashuplab.cts.generated_files.ResponseDetailType.valueOf(payload.getResponse().getResponseDetail().name()));

		// Transaction Id field
		encoder
				.transactionId(payload.getTransactionId().getMyUidId());

		System.out.println("\n-------------------------------------------------------------------------");
		System.out.println("EiCreatedTransactionEncode Encoded :-");
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
		EiResponseType response = EiResponseTypeEncoderDecoder.Decode(eiCreatedTransactionDecoder.response());

		
		org.theenergymashuplab.cts.ResponseDetailType appEnum = org.theenergymashuplab.cts.ResponseDetailType.valueOf(eiCreatedTransactionDecoder.response().responseDetail().name());


			// Decode createdDateTime
			long seconds = eiCreatedTransactionDecoder.response().createdDateTime().seconds();
			int nanos = (int) eiCreatedTransactionDecoder.response().createdDateTime().nano();
			response.setCreatedDateTime(Instant.ofEpochSecond(seconds, nanos));
			
		RefIdType inResponseTo= new RefIdType();
		inResponseTo.setMyUidId(eiCreatedTransactionDecoder.response().inResponseTo());
	
		response.setInResponseTo(inResponseTo);
		
		response.setResponseCode(eiCreatedTransactionDecoder.response().responseCode());
		
		int descriptionLength = (int) eiCreatedTransactionDecoder.response().responseDescription().length();
		byte[] descriptionBytes = new byte[descriptionLength];
		eiCreatedTransactionDecoder.response().responseDescription().buffer().getBytes(
		    eiCreatedTransactionDecoder.response().responseDescription().offset() + 4,
		    descriptionBytes,
		    0,
		    descriptionLength
		);
		String decodedDescription = new String(descriptionBytes, StandardCharsets.UTF_8);
		response.setResponseDescription(decodedDescription);

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