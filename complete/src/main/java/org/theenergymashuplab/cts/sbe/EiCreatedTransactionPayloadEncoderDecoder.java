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

public class EiCreatedTransactionPayloadEncoderDecoder {

	public static int eiCreatedTransactionEncode(
			EiCreatedTransactionPayloadEncoder eiCreatedTransactionPayloadEncoder,
			UnsafeBuffer directBuffer,
			MessageHeaderEncoder messageHeaderEncoder,
			EiCreatedTransactionPayload eiCreatedTransactionPayload) {

		eiCreatedTransactionPayloadEncoder.wrapAndApplyHeader(directBuffer, 0, messageHeaderEncoder);

		// Counter Party Id field
		eiCreatedTransactionPayloadEncoder
				.counterPartyId(eiCreatedTransactionPayload.getCounterPartyId().getMyUidId());

		// Market Transaction Id field
		eiCreatedTransactionPayloadEncoder
				.marketTransactionId(eiCreatedTransactionPayload.getMarketTransactionId().getMyUidId());

		// Party Id field
		eiCreatedTransactionPayloadEncoder.partyId(eiCreatedTransactionPayload.getPartyId().getMyUidId());

		// Recipient Transaction Id field
		eiCreatedTransactionPayloadEncoder
				.recipientTransactionId(
						eiCreatedTransactionPayload.getRecipientTransactionId().getMyUidId());

		// RefId field
		eiCreatedTransactionPayloadEncoder.refId(eiCreatedTransactionPayload.getRefId().getMyUidId());

		// Response field ----
		EiResponseTypeEncoderDecoder.Encode(eiCreatedTransactionPayloadEncoder.response(),
				eiCreatedTransactionPayload.getResponse());

		// Transaction Id field
		eiCreatedTransactionPayloadEncoder
				.transactionId(eiCreatedTransactionPayload.getTransactionId().getMyUidId());

		System.out.println("\n-------------------------------------------------------------------------");
		System.out.println("EiCreatedTransactionEncode Encoded :-");
		System.out.println(eiCreatedTransactionPayloadEncoder.toString());

		return messageHeaderEncoder.ENCODED_LENGTH + eiCreatedTransactionPayloadEncoder.encodedLength();

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
