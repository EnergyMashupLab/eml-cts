package org.theenergymashuplab.cts.sbe;

import org.agrona.concurrent.UnsafeBuffer;
import org.theenergymashuplab.cts.*;
import org.theenergymashuplab.cts.controller.payloads.EiCreateTransactionPayload;
import org.theenergymashuplab.cts.controller.payloads.EiCreatedTransactionPayload;
import org.theenergymashuplab.cts.generated_files.*;

public class EiTransactionPayloadEncoderDecoder {

	public static int eiCreateTransactionEncode(EiCreateTransactionPayloadEncoder encoder, UnsafeBuffer buffer,
			MessageHeaderEncoder header, EiCreateTransactionPayload payload) {

		encoder.wrapAndApplyHeader(buffer, 0, header);

		encoder.counterPartyId(payload.getCounterPartyId().getMyUidId());
		encoder.marketTransactionId(payload.getMarketTransactionId().getMyUidId());
		encoder.partyId(payload.getPartyId().getMyUidId());
		encoder.requestId(payload.getRequestId().getMyUidId());

		EiTransactionTypeEncoder transactionEncoder = encoder.transaction();
		CompositeEncoderDecoder.EiTransactionEncoderDecoder.encode(transactionEncoder, payload.getTransaction());

		return MessageHeaderEncoder.ENCODED_LENGTH + encoder.encodedLength();
	}

	public static EiCreateTransactionPayload eiCreateTransactionDecode(EiCreateTransactionPayloadDecoder decoder,
			UnsafeBuffer unsafeBuffer, int bufferOffset, int actingBlockLength, int actingVersion) {

		decoder.wrap(unsafeBuffer, bufferOffset, actingBlockLength, actingVersion);

		EiCreateTransactionPayload payload = new EiCreateTransactionPayload();

		ActorIdType counterPartyId = new ActorIdType();
		counterPartyId.setMyUidId(decoder.counterPartyId());
		payload.setCounterPartyId(counterPartyId);

		TransactionIdType marketTransactionId = new TransactionIdType();
		marketTransactionId.setMyUidId(decoder.marketTransactionId());
		payload.setMarketTransactionId(marketTransactionId);

		ActorIdType partyId = new ActorIdType();
		partyId.setMyUidId(decoder.partyId());
		payload.setPartyId(partyId);

		RefIdType requestId = new RefIdType();
		requestId.setMyUidId(decoder.requestId());
		payload.setRequestId(requestId);

		EiTransactionTypeDecoder transactionDecoder = decoder.transaction();
		EiTransaction transaction = new EiTransaction();
		EiTenderType tender = CompositeEncoderDecoder.EiTenderTypeEncoderDecoder.decode(transactionDecoder.tender());
		transaction.setTender(tender);
		payload.setTransaction(transaction);

		return payload;
	}

	public static int eiCreatedTransactionEncode(EiCreatedTransactionPayloadEncoder encoder, UnsafeBuffer buffer,
			MessageHeaderEncoder header, EiCreatedTransactionPayload payload) {

		encoder.wrapAndApplyHeader(buffer, 0, header);

		encoder.counterPartyId(payload.getCounterPartyId().getMyUidId())
				.marketTransactionId(payload.getMarketTransactionId().getMyUidId())
				.partyId(payload.getPartyId().getMyUidId())
				.recipientTransactionId(payload.getRecipientTransactionId().getMyUidId())
				.refId(payload.getRefId().getMyUidId());

		CompositeEncoderDecoder.EiResponseTypeEncoderDecoder.encode(encoder.response(), payload.getResponse());

		encoder.responseDescription(payload.getResponse().getResponseDescription());
		encoder.transactionId(payload.getTransactionId().getMyUidId());

		System.out.println("\n-------------------------------------------------------------------------");
		System.out.println("EiCreatedTransaction encoded :-");
		System.out.println(encoder.toString());

		return MessageHeaderEncoder.ENCODED_LENGTH + encoder.encodedLength();
	}

	public static EiCreatedTransactionPayload eiCreatedTransactionDecode(EiCreatedTransactionPayloadDecoder decoder,
			UnsafeBuffer buffer, int offset, int actingBlockLength, int actingVersion) throws Exception {

		decoder.wrap(buffer, offset, actingBlockLength, actingVersion);

		System.out.println("\n-------------------------------------------------------------------------");
		System.out.println("EiCreatedTransactionDecode Decoded :-");
		System.out.println(decoder.toString());

		ActorIdType counterPartyId = new ActorIdType();
		counterPartyId.setMyUidId(decoder.counterPartyId());

		MarketTransactionIdType marketTransactionId = new MarketTransactionIdType();
		marketTransactionId.setMyUidId(decoder.marketTransactionId());

		ActorIdType partyId = new ActorIdType();
		partyId.setMyUidId(decoder.partyId());

		TransactionIdType recipientTransactionId = new TransactionIdType();
		recipientTransactionId.setMyUidId(decoder.recipientTransactionId());

		RefIdType refId = new RefIdType();
		refId.setMyUidId(decoder.refId());

		EiResponseType response = CompositeEncoderDecoder.EiResponseTypeEncoderDecoder.decode(decoder.response());
		response.setResponseDescription(decoder.responseDescription());

		TransactionIdType transactionId = new TransactionIdType();
		transactionId.setMyUidId(decoder.transactionId());

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
