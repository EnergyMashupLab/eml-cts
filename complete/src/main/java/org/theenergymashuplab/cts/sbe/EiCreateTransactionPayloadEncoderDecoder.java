package org.theenergymashuplab.cts.sbe;

import org.agrona.concurrent.UnsafeBuffer;
import org.theenergymashuplab.cts.controller.payloads.EiCreateTransactionPayload;
import org.theenergymashuplab.cts.generated_files.EiCreateTransactionPayloadDecoder;
import org.theenergymashuplab.cts.generated_files.EiCreateTransactionPayloadEncoder;
import org.theenergymashuplab.cts.generated_files.MessageHeaderEncoder;

public class EiCreateTransactionPayloadEncoderDecoder {

    public static int encode(EiCreateTransactionPayloadEncoder encoder,
                             UnsafeBuffer unsafeBuffer,
                             MessageHeaderEncoder messageHeaderEncoder,
                             EiCreateTransactionPayload payload) {

        encoder.wrapAndApplyHeader(unsafeBuffer, 0, messageHeaderEncoder);

        // id fields
        long counterPartyId = payload.getCounterPartyId().getMyUidId();
        long marketTransactionId = payload.getMarketTransactionId().getMyUidId();
        long partyId = payload.getPartyId().getMyUidId();
        long requestId = payload.getRequestId().getMyUidId();

        encoder.counterPartyId(counterPartyId);
        encoder.marketTransactionId(marketTransactionId);
        encoder.partyId(partyId);
        encoder.requestId(requestId);

        // transaction ???
        //
        //

        return MessageHeaderEncoder.ENCODED_LENGTH + encoder.encodedLength();
    }

    public static int decode(EiCreateTransactionPayloadDecoder decoder,
                             UnsafeBuffer unsafeBuffer,
                             int bufferOffset,
                             int actingBlockLength,
                             int actingVersion) {

        return -1;
    }
}
