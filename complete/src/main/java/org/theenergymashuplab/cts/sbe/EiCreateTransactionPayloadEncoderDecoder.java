package org.theenergymashuplab.cts.sbe;

import org.agrona.concurrent.UnsafeBuffer;
import org.theenergymashuplab.cts.*;
import org.theenergymashuplab.cts.controller.payloads.EiCreateTransactionPayload;
import org.theenergymashuplab.cts.generated_files.BooleanType;
import org.theenergymashuplab.cts.generated_files.EiCreateTransactionPayloadDecoder;
import org.theenergymashuplab.cts.generated_files.EiCreateTransactionPayloadEncoder;
import org.theenergymashuplab.cts.generated_files.MessageHeaderEncoder;

import java.time.Duration;
import java.time.Instant;

public class EiCreateTransactionPayloadEncoderDecoder {

    public static int encode(EiCreateTransactionPayloadEncoder encoder,
                             UnsafeBuffer unsafeBuffer,
                             MessageHeaderEncoder messageHeaderEncoder,
                             EiCreateTransactionPayload payload) {

        encoder.wrapAndApplyHeader(unsafeBuffer, 0, messageHeaderEncoder);
        encodeIdFields(encoder, payload);
        encodeTransactionField(encoder, payload);
        return MessageHeaderEncoder.ENCODED_LENGTH + encoder.encodedLength();
    }

    private static void encodeIdFields(EiCreateTransactionPayloadEncoder encoder,
                                       EiCreateTransactionPayload payload) {

        long counterPartyId = payload.getCounterPartyId().getMyUidId();
        long marketTransactionId = payload.getMarketTransactionId().getMyUidId();
        long partyId = payload.getPartyId().getMyUidId();
        long requestId = payload.getRequestId().getMyUidId();

        encoder.counterPartyId(counterPartyId);
        encoder.marketTransactionId(marketTransactionId);
        encoder.partyId(partyId);
        encoder.requestId(requestId);
    }

    private static void encodeTransactionField(EiCreateTransactionPayloadEncoder encoder,
                                               EiCreateTransactionPayload payload) {

        EiTransaction transaction = payload.getTransaction();

        // market transaction id??
        long marketTransactionId = transaction.getTransactionId().getMyUidId();
        long marketOrderId = transaction.getTender().getMarketOrderId().value();
        long tenderId = transaction.getTender().getTenderId().value();
        BooleanType allOrNone = transaction.getTender().isAllOrNone() ? BooleanType.TRUE : BooleanType.FALSE;
        Instant expirationTime = transaction.getTender().getExpirationTime();
        org.theenergymashuplab.cts.generated_files.ResourceDesignatorType encodedResourceDesignator =
                getResourceDesignatorType(transaction.getTender().getResourceDesignator());

        encoder.transaction().marketTransactionId(marketTransactionId);
        encoder.transaction().tender().marketOrderId(marketOrderId);
        encoder.transaction().tender().tenderId(tenderId);
        encoder.transaction().tender().tenderBase().allOrNone(allOrNone);
        encoder.transaction().tender().tenderBase().expirationTime().seconds(expirationTime.getEpochSecond()).nano(expirationTime.getNano());
        encoder.transaction().tender().tenderBase().marketId(transaction.getTender().getMarketId().value());
        encoder.transaction().tender().tenderBase().priceScale(transaction.getTender().getPriceScale());
        encoder.transaction().tender().tenderBase().quantityScale(transaction.getTender().getQuantityScale());
        encoder.transaction().tender().tenderBase().resourceDesignator(encodedResourceDesignator);
        encoder.transaction().tender().tenderBase().segmentId(transaction.getTender().getSegmentId());

        if (transaction.getTender().getSide() == org.theenergymashuplab.cts.SideType.BUY) {
            encoder.transaction().tender().tenderBase()
                    .side(org.theenergymashuplab.cts.generated_files.SideType.BUY);
        } else {
            encoder.transaction().tender().tenderBase()
                    .side(org.theenergymashuplab.cts.generated_files.SideType.SELL);
        }

        TenderDetail tenderDetail = transaction.getTender().getTenderDetail();

        if (tenderDetail instanceof TenderIntervalDetail) {
            TenderIntervalDetail intervalDetail = (TenderIntervalDetail) tenderDetail;
            long price = intervalDetail.getPrice();
            long quantity = intervalDetail.getQuantity();
            Interval interval = intervalDetail.getInterval();

            encoder.transaction().tender().tenderBase().tenderDetail().price(price).quantity(quantity);

            Instant dtStart = interval.getDtStart();
            Duration duration = interval.getDuration();

            encoder.transaction().tender().tenderBase().tenderDetail()
                    .interval().dtStart()
                    .seconds(dtStart.getEpochSecond())
                    .nano(dtStart.getNano());

            encoder.transaction().tender().tenderBase().tenderDetail()
                    .interval().duration()
                    .seconds(duration.getSeconds())
                    .nano(duration.getNano());
        } else {
            throw new IllegalStateException("Unexpected TenderDetail type: " + tenderDetail.getClass().getName());
        }

        encoder.transaction().tender().tenderBase()
                .warrants(transaction.getTender().getWarrants().value());
    }

    private static org.theenergymashuplab.cts.generated_files.ResourceDesignatorType getResourceDesignatorType(ResourceDesignatorType type) {
        switch (type) {
            case POWER:
                return org.theenergymashuplab.cts.generated_files.ResourceDesignatorType.POWER;
            case ENERGY:
                return org.theenergymashuplab.cts.generated_files.ResourceDesignatorType.ENERGY;

            case TRANSPORT:
                return org.theenergymashuplab.cts.generated_files.ResourceDesignatorType.TRANSPORT;

            case WATER_PRESSURE:
                return org.theenergymashuplab.cts.generated_files.ResourceDesignatorType.WATER_PRESSURE;

            case WATER_FLOW:
                return org.theenergymashuplab.cts.generated_files.ResourceDesignatorType.WATER_FLOW;

            case GAS_PRESSURE:
                return org.theenergymashuplab.cts.generated_files.ResourceDesignatorType.GAS_PRESSURE;

            case GAS_FLOW:
                return org.theenergymashuplab.cts.generated_files.ResourceDesignatorType.GAS_FLOW;

            case BANDWIDTH:
                return org.theenergymashuplab.cts.generated_files.ResourceDesignatorType.BANDWIDTH;

            default:
                throw new IllegalArgumentException("Invalid ResourceDesignator: " + type);
        }
    }

    public static int decode(EiCreateTransactionPayloadDecoder decoder,
                             UnsafeBuffer unsafeBuffer,
                             int bufferOffset,
                             int actingBlockLength,
                             int actingVersion) {

        return -1;
    }
}
