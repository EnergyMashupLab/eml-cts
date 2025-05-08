package org.theenergymashuplab.cts.sbe;

import org.agrona.concurrent.UnsafeBuffer;
import org.theenergymashuplab.cts.*;
import org.theenergymashuplab.cts.ResourceDesignatorType;
import org.theenergymashuplab.cts.SideType;
import org.theenergymashuplab.cts.controller.payloads.EiAcceptQuotePayload;
import org.theenergymashuplab.cts.controller.payloads.EiAcceptedQuotePayload;
import org.theenergymashuplab.cts.generated_files.*;

import java.time.Duration;
import java.time.Instant;

public class EiQuotePayloadEncoderDecoder {

	public static int encode(EiAcceptQuotePayloadEncoder encoder, UnsafeBuffer unsafeBuffer,
			MessageHeaderEncoder messageHeaderEncoder, EiAcceptQuotePayload payload) {

		encoder.wrapAndApplyHeader(unsafeBuffer, 0, messageHeaderEncoder);

		encoder.referencedQuoteId(payload.getReferencedQuoteId().getMyUidId());
		encoder.counterPartyId(payload.getCounterPartyId().getMyUidId());
		encoder.marketTransactionId(payload.getMarketTransactionId().getMyUidId());
		encoder.partyId(payload.getPartyId().getMyUidId());
		encoder.requestId(payload.getRequestId().getMyUidId());

		EiTransactionTypeEncoder transactionEncoder = encoder.transaction();
		CompositeEncoderDecoder.EiTransactionEncoderDecoder.encode(transactionEncoder, payload.getTransaction());

		return MessageHeaderEncoder.ENCODED_LENGTH + encoder.encodedLength();
	}

	public static EiAcceptQuotePayload decode(EiAcceptQuotePayloadDecoder decoder, UnsafeBuffer unsafeBuffer,
			int bufferOffset, int actingBlockLength, int actingVersion) {

		decoder.wrap(unsafeBuffer, bufferOffset, actingBlockLength, actingVersion);
		EiAcceptQuotePayload payload = new EiAcceptQuotePayload();

		MarketOrderIdType referencedQuoteId = new MarketOrderIdType();
		referencedQuoteId.setMyUidId(decoder.referencedQuoteId());

		ActorIdType counterPartyId = new ActorIdType();
		counterPartyId.setMyUidId(decoder.counterPartyId());

		TransactionIdType marketTransactionId = new TransactionIdType();
		marketTransactionId.setMyUidId(decoder.marketTransactionId());

		ActorIdType partyId = new ActorIdType();
		partyId.setMyUidId(decoder.partyId());

		RefIdType requestId = new RefIdType();
		requestId.setMyUidId(decoder.requestId());

		payload.setReferencedQuoteID(referencedQuoteId);
		payload.setCounterPartyId(counterPartyId);
		payload.setMarketTransactionId(marketTransactionId);
		payload.setPartyId(partyId);
		payload.setRequestId(requestId);

// Decode transaction using composite decoder
		EiTransaction transaction = CompositeEncoderDecoder.EiTransactionEncoderDecoder.decode(decoder.transaction());
		payload.setTransaction(transaction);

		return payload;
	}

	public static int encode(EiAcceptedQuotePayloadEncoder encoder, UnsafeBuffer unsafeBuffer,
			MessageHeaderEncoder messageHeaderEncoder, EiAcceptedQuotePayload payload) {

		encoder.wrapAndApplyHeader(unsafeBuffer, 0, messageHeaderEncoder);
		encoder.counterPartyId(payload.getCounterPartyId().getMyUidId());
		encoder.marketTransactionId(payload.getMarketTransactionId().getMyUidId());
		encoder.partyId(payload.getPartyId().getMyUidId());
		encoder.recipientTransactionId(payload.getRecipientTransactionId().getMyUidId());
		encoder.refId(payload.getRefId().getMyUidId());
		encoder.transactionId(payload.getTransactionId().getMyUidId());

		EiResponseType response = payload.getResponse();
		Instant createdDateTime = response.getCreatedDateTime();
		String description = response.getResponseDescription();

		EiResponseTypeEncoder responseEncoder = encoder.response();

		responseEncoder.createdDateTime().seconds(createdDateTime.getEpochSecond()).nano(createdDateTime.getNano());

		responseEncoder.inResponseTo(response.getInResponseTo().getMyUidId()).responseCode(response.getResponseCode());

		encoder.responseDescription(description);

		short code = payload.getResponse().getResponseDetail().getValue();
		org.theenergymashuplab.cts.generated_files.ResponseDetailType encodedEnum = org.theenergymashuplab.cts.generated_files.ResponseDetailType
				.get(code);
		responseEncoder.responseDetail(encodedEnum);

		return MessageHeaderEncoder.ENCODED_LENGTH + encoder.encodedLength();
	}

	public static EiAcceptedQuotePayload decode(EiAcceptedQuotePayloadDecoder decoder, UnsafeBuffer unsafeBuffer,
			int bufferOffset, int actingBlockLength, int actingVersion) {

		decoder.wrap(unsafeBuffer, bufferOffset, actingBlockLength, actingVersion);
		EiAcceptedQuotePayload payload = new EiAcceptedQuotePayload();

		ActorIdType counterPartyId = new ActorIdType();
		counterPartyId.setMyUidId(decoder.marketTransactionId());

		MarketTransactionIdType marketTransactionId = new MarketTransactionIdType();
		marketTransactionId.setMyUidId(decoder.marketTransactionId());

		ActorIdType partyId = new ActorIdType();
		partyId.setMyUidId(decoder.partyId());

		TransactionIdType recipientTransactionId = new TransactionIdType();
		recipientTransactionId.setMyUidId(decoder.recipientTransactionId());

		RefIdType refId = new RefIdType();
		refId.setMyUidId(decoder.refId());

		EiResponseType eiResponse = new EiResponseType();
		EiResponseTypeDecoder responseDecoder = decoder.response();

		RefIdType responseInResponseTo = new RefIdType();
		responseInResponseTo.setMyUidId(responseDecoder.inResponseTo());
		eiResponse.setInResponseTo(responseInResponseTo);

		eiResponse.setResponseCode(responseDecoder.responseCode());
		eiResponse.setResponseDescription(decoder.responseDescription());

		short sbeValue = responseDecoder.responseDetail().value();
		org.theenergymashuplab.cts.ResponseDetailType responseDetail = org.theenergymashuplab.cts.ResponseDetailType
				.fromSbe(sbeValue);
		eiResponse.setResponseDetail(responseDetail);

		TransactionIdType transactionId = new TransactionIdType();
		transactionId.setMyUidId(decoder.transactionId());

		payload.setResponse(eiResponse);
		payload.response.setResponseDescription(decoder.responseDescription());
		payload.setCounterPartyId(counterPartyId);
		payload.setMarketTransactionId(marketTransactionId);
		payload.setPartyId(partyId);
		payload.setRecipientTransactionId(recipientTransactionId);
		payload.setRefId(refId);
		payload.setTransactionId(transactionId);

		return payload;
	}
}
