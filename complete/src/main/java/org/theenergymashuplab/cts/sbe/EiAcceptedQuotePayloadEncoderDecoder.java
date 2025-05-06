package org.theenergymashuplab.cts.sbe;

import org.agrona.concurrent.UnsafeBuffer;
import org.theenergymashuplab.cts.*;
import org.theenergymashuplab.cts.controller.payloads.EiAcceptedQuotePayload;
import org.theenergymashuplab.cts.generated_files.*;

public class EiAcceptedQuotePayloadEncoderDecoder {
    public static int encode(EiAcceptedQuotePayloadEncoder encoder,
                             UnsafeBuffer unsafeBuffer,
                             MessageHeaderEncoder messageHeaderEncoder,
                             EiAcceptedQuotePayload payload) {

        encoder.wrapAndApplyHeader(unsafeBuffer, 0, messageHeaderEncoder);
        encodeIdFields(encoder, payload);
        encodeResponseField(encoder, payload);
        return MessageHeaderEncoder.ENCODED_LENGTH + encoder.encodedLength();
    }

    private static void encodeIdFields(EiAcceptedQuotePayloadEncoder encoder, EiAcceptedQuotePayload payload) {
        long counterPartyId = payload.getCounterPartyId().getMyUidId();
        long marketTransactionId = payload.getMarketTransactionId().getMyUidId();
        long partyId = payload.getPartyId().getMyUidId();
        long recipientTransactionId = payload.getRecipientTransactionId().getMyUidId();
        long refId = payload.getRefId().getMyUidId();
        long transactionId = payload.getTransactionId().getMyUidId();

        encoder.counterPartyId(counterPartyId);
        encoder.marketTransactionId(marketTransactionId);
        encoder.partyId(partyId);
        encoder.recipientTransactionId(recipientTransactionId);
        encoder.refId(refId);
        encoder.transactionId(transactionId);
    }

    private static void encodeResponseField(EiAcceptedQuotePayloadEncoder encoder, EiAcceptedQuotePayload payload) {
        EiResponseType response = payload.getResponse();
        EiResponseTypeEncoder responseEncoder = encoder.response();

        EiResponseTypeEncoderDecoder.Encode(responseEncoder, response);
    }

    public static EiAcceptedQuotePayload decode(EiAcceptedQuotePayloadDecoder decoder,
                                                UnsafeBuffer unsafeBuffer,
                                                int bufferOffset,
                                                int actingBlockLength,
                                                int actingVersion) {

        decoder.wrap(unsafeBuffer, bufferOffset, actingBlockLength, actingVersion);
        EiAcceptedQuotePayload payload = new EiAcceptedQuotePayload();

        // id fields
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

        TransactionIdType transactionId = new TransactionIdType();
        transactionId.setMyUidId(decoder.transactionId());

        payload.setCounterPartyId(counterPartyId);
        payload.setMarketTransactionId(marketTransactionId);
        payload.setPartyId(partyId);
        payload.setRecipientTransactionId(recipientTransactionId);
        payload.setRefId(refId);
        payload.setTransactionId(transactionId);

        // response field
        EiResponseTypeDecoder responseDecoder = decoder.response();
        EiResponseType response = EiResponseTypeEncoderDecoder.Decode(responseDecoder);
        payload.setResponse(response);

        return payload;
    }
}
