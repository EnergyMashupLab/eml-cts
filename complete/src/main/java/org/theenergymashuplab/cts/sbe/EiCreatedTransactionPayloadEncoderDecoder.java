package org.theenergymashuplab.cts.sbe;

import java.time.Instant;

import org.agrona.concurrent.UnsafeBuffer;
import org.theenergymashuplab.cts.ActorIdType;
import org.theenergymashuplab.cts.EiResponseType;
import org.theenergymashuplab.cts.InstantType;
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

		{
			// Response field ----
			// Response -> Created Date Time ---
			// How should this be handled?
			InstantType responseCreatedDateTime = eiCreatedTransactionPayload.getResponse()
					.getCreatedDateTime();
			responseCreatedDateTime.getTime();
			eiCreatedTransactionPayloadEncoder.response().createdDateTime().seconds(0).nano(0);

			// Response-> inResponseTo (there is no inResponseTo method)
			eiCreatedTransactionPayloadEncoder.response().inResponseTo(
					eiCreatedTransactionPayload.getResponse().getInResponseTo().getMyUidId());

			// Response -> Response Code
			eiCreatedTransactionPayloadEncoder.response()
					.responseCode(eiCreatedTransactionPayload.getResponse().getResponseCode());

			// Response -> Description ---
			// Response -> Description -> Length
			eiCreatedTransactionPayloadEncoder.response().responseDescription()
					.length(eiCreatedTransactionPayload.getResponse().getResponseDescription()
							.length());

			// Response -> Description -> varData (there is no varData attribute)
			// eiCreatedTransactionPayloadEncoder.response().responseDescription().varData(eiCreatedTransactionPayload.getResponse().getResponseDescription());

			// Response -> Response Detail (there is no response detail attribute in
			// EiResponse)
			// eiCreatedTransactionPayloadEncoder.response().responseDetail(eiCreatedTransactionPayload.getResponse().getResponseDetail());

			// ----
		}

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

		ActorIdType counterPartyId = new ActorIdType();
		counterPartyId.setMyUidId(eiCreatedTransactionDecoder.counterPartyId());

		MarketTransactionIdType marketTransactionId = new MarketTransactionIdType();
		marketTransactionId.setMyUidId(eiCreatedTransactionDecoder.marketTransactionId());

		ActorIdType partyId = new ActorIdType();
		partyId.setMyUidId(eiCreatedTransactionDecoder.partyId());

		// RecipientTransactionIdType
		TransactionIdType recipientTransactionId = new TransactionIdType();
		recipientTransactionId.setMyUidId(eiCreatedTransactionDecoder.recipientTransactionId());

		RefIdType refId = new RefIdType();
		refId.setMyUidId(eiCreatedTransactionDecoder.refId());

		// response---------------------------------------
		EiResponseType response = new EiResponseType();
		// response created date time

		RefIdType inResponseTo = new RefIdType();
		inResponseTo.setMyUidId(eiCreatedTransactionDecoder.response().inResponseTo());

		ResponseDetailType responseDetail = eiCreatedTransactionDecoder.response().responseDetail();

		response.setCreatedDateTime(null);
		response.setInResponseTo(inResponseTo);
		response.setResponseCode(eiCreatedTransactionDecoder.response().responseCode());
		response.setResponseDescription(String.valueOf(eiCreatedTransactionDecoder.response().responseDescription()));
		// response.setResponseDetails(responseDetail);
		// --------------------------------------------

		TransactionIdType transactionId = new TransactionIdType();
		transactionId.setMyUidId(eiCreatedTransactionDecoder.transactionId());

		EiCreatedTransactionPayload eiCreatedTransactionPayload = new EiCreatedTransactionPayload();
		eiCreatedTransactionPayload.setCounterPartyId(counterPartyId);
		eiCreatedTransactionPayload.setMarketTransactionId(marketTransactionId);
		eiCreatedTransactionPayload.setPartyId(partyId);
		eiCreatedTransactionPayload.setRecipientTransactionId(recipientTransactionId);
		// this is final, is it supposed to be set?
		// eiCreatedTransactionPayload.setRefId(refId);
		eiCreatedTransactionPayload.setResponse(response);
		eiCreatedTransactionPayload.setTransactionId(transactionId);

		return eiCreatedTransactionPayload;
	}

}
