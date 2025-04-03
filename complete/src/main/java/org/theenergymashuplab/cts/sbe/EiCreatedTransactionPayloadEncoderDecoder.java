package org.theenergymashuplab.cts.sbe;

import java.time.Instant;

import org.agrona.concurrent.UnsafeBuffer;
import org.theenergymashuplab.cts.controller.payloads.EiCreatedTransactionPayload;
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

        // Transaction Id field
        eiCreatedTransactionPayloadEncoder.transactionId(eiCreatedTransactionPayload.getTransactionId().getMyUidId());

        return 0;
    }

}
