package org.theenergymashuplab.cts.sbe;

import java.time.Instant;

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
		// Response -> Created Date Time ---
		Instant responseCreatedDateTime = eiCreatedTransactionPayload.getResponse()
				.getCreatedDateTime();
		eiCreatedTransactionPayloadEncoder.response().createdDateTime()
				.seconds(responseCreatedDateTime.getEpochSecond());
		eiCreatedTransactionPayloadEncoder.response().createdDateTime().nano(responseCreatedDateTime.getNano());

		// ---

		// Response-> inResponseTo
		eiCreatedTransactionPayloadEncoder.response().inResponseTo(
				eiCreatedTransactionPayload.getResponse().getInResponseTo().getMyUidId());

		// Response -> Response Code
		eiCreatedTransactionPayloadEncoder.response()
				.responseCode(eiCreatedTransactionPayload.getResponse().getResponseCode());

		// Response -> Description
		// eiCreatedTransactionPayloadEncoder.response().responseDescription().wrap(directBuffer,
		// 0);

		org.theenergymashuplab.cts.ResponseDetailType appEnum =
			    eiCreatedTransactionPayload.getResponse().getResponseDetail();

			org.theenergymashuplab.cts.generated_files.ResponseDetailType encodedEnum =
			    org.theenergymashuplab.cts.generated_files.ResponseDetailType.valueOf(appEnum.name());

			eiCreatedTransactionPayloadEncoder.response().responseDetail(encodedEnum);

		// ----

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

		// Response---------------------------------------
		EiResponseType response = new EiResponseType();
		RefIdType inResponseTo = new RefIdType();
		inResponseTo.setMyUidId(eiCreatedTransactionDecoder.response().inResponseTo());

		org.theenergymashuplab.cts.generated_files.ResponseDetailType generatedDetail =
			    eiCreatedTransactionDecoder.response().responseDetail();

			org.theenergymashuplab.cts.ResponseDetailType responseDetail =
			    org.theenergymashuplab.cts.ResponseDetailType.valueOf(generatedDetail.name());

		response.setCreatedDateTime(
				Instant.ofEpochSecond(eiCreatedTransactionDecoder.response().createdDateTime().seconds(),
						eiCreatedTransactionDecoder.response().createdDateTime().nano()));
		response.setInResponseTo(inResponseTo);
		response.setResponseCode(eiCreatedTransactionDecoder.response().responseCode());
		response.setResponseDescription(String.valueOf(eiCreatedTransactionDecoder.response().responseDescription()));
		response.setResponseDetail(responseDetail);
		// --------------------------------------------
		
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
