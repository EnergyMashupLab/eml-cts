package org.theenergymashuplab.cts.sbe;

import java.time.Instant;

import org.agrona.concurrent.UnsafeBuffer;
import org.theenergymashuplab.cts.ActorIdType;
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
        eiCreatedTransactionPayloadEncoder.counterPartyId(eiCreatedTransactionPayload.getCounterPartyId().getMyUidId());

        // Market Transaction Id field (there is no getMarketTransactionId method)
        eiCreatedTransactionPayloadEncoder
                .marketTransactionId(eiCreatedTransactionPayload.marketTransactionId.getMyUidId());

        // Party Id field
        eiCreatedTransactionPayloadEncoder.partyId(eiCreatedTransactionPayload.getPartyId().getMyUidId());

        // Recipient Transaction Id field
        eiCreatedTransactionPayloadEncoder
                .recipientTransactionId(eiCreatedTransactionPayload.getRecipientTransactionId().getMyUidId());

        // RefId field
        eiCreatedTransactionPayloadEncoder.refId(eiCreatedTransactionPayload.getRefId().getMyUidId());
        
        {
            // Response field ----
            // Response -> Created Date Time ---
            Instant responseCreatedDateTime = eiCreatedTransactionPayload.response.getCreatedDateTime();
            eiCreatedTransactionPayloadEncoder.response()
                    .createdDateTime()
                    .seconds(responseCreatedDateTime.getEpochSecond())
                    .nano(responseCreatedDateTime.getNano());

            // Response-> inResponseTo (there is no inResponseTo method)
            // eiCreatedTransactionPayloadEncoder.response().inResponseTo(eiCreatedTransactionPayload.getResponse().getInResponseTo().getMyUidId());

            // Response -> Response Code
            eiCreatedTransactionPayloadEncoder.response()
                    .responseCode(eiCreatedTransactionPayload.getResponse().getResponseCode());

            // Response -> Description ---
            // Response -> Description -> Length
            eiCreatedTransactionPayloadEncoder.response().responseDescription()
                    .length(eiCreatedTransactionPayload.getResponse().getResponseDescription().length());

            // Response -> Description -> varData (there is no varData attribute)
            // eiCreatedTransactionPayloadEncoder.response().responseDescription().varData(eiCreatedTransactionPayload.getResponse().getResponseDescription());

            // Response -> Response Detail (there is no response detail attribute in
            // EiResponse)
            // eiCreatedTransactionPayloadEncoder.response().responseDetail(eiCreatedTransactionPayload.getResponse());

            // ----
        }

        // Transaction Id field
        eiCreatedTransactionPayloadEncoder.transactionId(eiCreatedTransactionPayload.getTransactionId().getMyUidId());

        System.out.println("\n-------------------------------------------------------------------------");
        System.out.println("EiCreatedTransactionEncode Encoded :-");
        System.out.println(eiCreatedTransactionPayloadEncoder.toString());

        return messageHeaderEncoder.ENCODED_LENGTH + eiCreatedTransactionPayloadEncoder.encodedLength();

    }

    public static EiCreatedTransactionPayload eiCreatedTransactionDecode(
            EiCreatedTransactionPayloadDecoder eiCreatedTransactionDecoder, UnsafeBuffer directBuffer, int bufferOffset,
            int actingBlockLength, int actingVersion) throws Exception {

        eiCreatedTransactionDecoder.wrap(directBuffer, bufferOffset, actingBlockLength, actingVersion);

        System.out.println("\n-------------------------------------------------------------------------");
        System.out.println("EiCreatedTransactionEncode Decoded :-");
        System.out.println(eiCreatedTransactionDecoder.toString());

        ActorIdType partyId = new ActorIdType(), counterPartyId = new ActorIdType();
        MarketTransactionIdType marketTransactionId = new MarketTransactionIdType();

        // RecipientTransactionIdType recipientTransactionId = new
        // RecipientTransactionIdType();

        TransactionIdType recipientTransactionId = new TransactionIdType(), transactionId = new TransactionIdType();
        RefIdType refId = new RefIdType();

        {/*
          * long createdDateTimeSeconds =
          * eiCreatedTransactionDecoder.response().createdDateTime().seconds();
          * int createdDateTimeNano = (int)
          * eiCreatedTransactionDecoder.response().createdDateTime().nano();
          * Instant responseCreatedDateTime =
          * Instant.ofEpochSecond(createdDateTimeSeconds,
          * createdDateTimeNano);
          * 
          * RefIdType inResponseTo = new RefIdType();
          * inResponseTo.setMyUidId(eiCreatedTransactionDecoder.response().inResponseTo()
          * );
          * 
          * long responseCode = eiCreatedTransactionDecoder.response().responseCode();
          * 
          * String responseDescription = String.valueOf(
          * eiCreatedTransactionDecoder.response().responseDescription());
          * 
          * ResponseDetailType responseDetail =
          * eiCreatedTransactionDecoder.response().responseDetail();
          * 
          * EiResponseType response = new
          * EiResponseType(responseCode,responseDescription,);
          * response.setCreatedDateTime(null);
          */
        }
        counterPartyId.setMyUidId(eiCreatedTransactionDecoder.counterPartyId());
        marketTransactionId.setMyUidId(eiCreatedTransactionDecoder.marketTransactionId());
        partyId.setMyUidId(eiCreatedTransactionDecoder.partyId());
        recipientTransactionId.setMyUidId(eiCreatedTransactionDecoder.recipientTransactionId());
        // response
        refId.setMyUidId(eiCreatedTransactionDecoder.refId());
        transactionId.setMyUidId(eiCreatedTransactionDecoder.transactionId());

        EiCreatedTransactionPayload eiCreatedTransactionPayload = new EiCreatedTransactionPayload();
        eiCreatedTransactionPayload.setCounterPartyId(counterPartyId);
        // eiCreatedTransactionPayload.setMarketTransactionId(marketTransactionId);
        eiCreatedTransactionPayload.setPartyId(partyId);

        // this is final, is it supposed to be set?
        // eiCreatedTransactionPayload.refId;

        // response

        eiCreatedTransactionPayload.setTransactionId(transactionId);

        return eiCreatedTransactionPayload;
    }

}
