package org.theenergymashuplab.cts.sbe;

import org.agrona.concurrent.UnsafeBuffer;
import org.theenergymashuplab.cts.*;
import org.theenergymashuplab.cts.ResourceDesignatorType;
import org.theenergymashuplab.cts.controller.payloads.EiAcceptQuotePayload;
import org.theenergymashuplab.cts.generated_files.*;

import java.time.Duration;
import java.time.Instant;

public class EiAcceptQuotePayloadEncoderDecoder {

    public static int encode(EiAcceptQuotePayloadEncoder encoder,
                             UnsafeBuffer unsafeBuffer,
                             MessageHeaderEncoder messageHeaderEncoder,
                             EiAcceptQuotePayload payload) {

        encoder.wrapAndApplyHeader(unsafeBuffer, 0, messageHeaderEncoder);
        encodeIdFields(encoder, payload);
        encodeTransactionField(encoder, payload);
        return MessageHeaderEncoder.ENCODED_LENGTH + encoder.encodedLength();
    }

    private static void encodeIdFields(EiAcceptQuotePayloadEncoder encoder, EiAcceptQuotePayload payload) {
        long referencedQuoteId = payload.getReferencedQuoteId().getMyUidId();
        long counterPartyId = payload.getCounterPartyId().getMyUidId();
        long marketTransactionId = payload.getMarketTransactionId().getMyUidId();
        long partyId = payload.getPartyId().getMyUidId();
        long requestId = payload.getRequestId().getMyUidId();

        encoder.referencedQuoteId(referencedQuoteId);
        encoder.counterPartyId(counterPartyId);
        encoder.marketTransactionId(marketTransactionId);
        encoder.partyId(partyId);
        encoder.requestId(requestId);
    }

    private static void encodeTransactionField(EiAcceptQuotePayloadEncoder encoder,
                                               EiAcceptQuotePayload payload) {

        EiTransaction transaction = payload.getTransaction();

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

    public static EiAcceptQuotePayload decode(EiAcceptQuotePayloadDecoder decoder,
                                              UnsafeBuffer unsafeBuffer,
                                              int bufferOffset,
                                              int actingBlockLength,
                                              int actingVersion) {

        decoder.wrap(unsafeBuffer, bufferOffset, actingBlockLength, actingVersion);
        EiAcceptQuotePayload payload = new EiAcceptQuotePayload();

        // id fields
        MarketOrderIdType referencedQuoteId = new MarketOrderIdType();
        referencedQuoteId.setMyUidId(decoder.marketTransactionId());

        ActorIdType counterPartyId = new ActorIdType();
        counterPartyId.setMyUidId(decoder.counterPartyId());

        TransactionIdType marketTransactionId = new TransactionIdType();
        marketTransactionId.setMyUidId(decoder.marketTransactionId());

        ActorIdType partyId = new ActorIdType();
        partyId.setMyUidId(decoder.marketTransactionId());

        RefIdType requestId = new RefIdType();
        requestId.setMyUidId(decoder.requestId());

        payload.setReferencedQuoteID(referencedQuoteId);
        payload.setCounterPartyId(counterPartyId);
        payload.setMarketTransactionId(marketTransactionId);
        payload.setPartyId(partyId);
        payload.setRequestId(requestId);

        // transaction
        TransactionIdType marketTransactionId2 = new TransactionIdType();
        marketTransactionId2.setMyUidId(decoder.marketTransactionId());

        long expirationTimeSeconds = decoder.transaction().tender().tenderBase().expirationTime().seconds();
        int expirationTimeNano = (int) decoder.transaction().tender().tenderBase().expirationTime().nano();

        Instant expirationTime = Instant.ofEpochSecond(expirationTimeSeconds, expirationTimeNano);

        TenderIntervalDetailDecoder tenderDetailDecoder = decoder.transaction().tender().tenderBase().tenderDetail();

        TenderDetail tenderDetail = null;

        if (tenderDetailDecoder != null) {
            long price = tenderDetailDecoder.price();
            long quantity = tenderDetailDecoder.quantity();

            Interval interval = new Interval();
            interval.setDtStart(Instant.ofEpochSecond(tenderDetailDecoder.interval().dtStart().seconds(),
                    tenderDetailDecoder.interval().dtStart().nano()));
            interval.setDuration(Duration.ofSeconds(tenderDetailDecoder.interval().duration().seconds(),
                    tenderDetailDecoder.interval().duration().nano()));

            TenderIntervalDetail tenderIntervalDetail = new TenderIntervalDetail(interval, price, quantity);
            tenderDetail = tenderIntervalDetail;
        }

        org.theenergymashuplab.cts.SideType side;
        if (decoder.transaction().tender().tenderBase()
                .side() == org.theenergymashuplab.cts.generated_files.SideType.BUY) {
            side = org.theenergymashuplab.cts.SideType.BUY;
        } else if (decoder.transaction().tender().tenderBase()
                .side() == org.theenergymashuplab.cts.generated_files.SideType.SELL) {
            side = org.theenergymashuplab.cts.SideType.SELL;
        } else {
            throw new IllegalArgumentException(
                    "Unknown side type: " + decoder.transaction().tender().tenderBase().side());
        }

        MarketOrderIdType marketOrderId = new MarketOrderIdType();
        marketOrderId.setMyUidId(decoder.transaction().tender().marketOrderId());
        EiTenderType eiTenderType = new EiTenderType(expirationTime, side, tenderDetail, marketOrderId);

        MarketIdType marketId = new MarketIdType();
        marketId.setMyUidId(decoder.transaction().tender().tenderBase().marketId());

        eiTenderType.setMarketId(marketId);

        BooleanType allOrNone = decoder.transaction().tender().tenderBase().allOrNone();
        eiTenderType.setAllOrNone(allOrNone == BooleanType.TRUE);

        int priceScale = (int) decoder.transaction().tender().tenderBase().priceScale();

        eiTenderType.setPriceScale(priceScale);

        int quantityScale = (int) decoder.transaction().tender().tenderBase().quantityScale();

        eiTenderType.setQuantityScale(quantityScale);

        org.theenergymashuplab.cts.generated_files.ResourceDesignatorType generatedResourceDesignator = decoder.transaction().tender().tenderBase().resourceDesignator();
        org.theenergymashuplab.cts.ResourceDesignatorType resourceDesignator;

        switch (generatedResourceDesignator) {
            case POWER:
                resourceDesignator = org.theenergymashuplab.cts.ResourceDesignatorType.POWER;
                break;
            case ENERGY:
                resourceDesignator = org.theenergymashuplab.cts.ResourceDesignatorType.ENERGY;
                break;
            case TRANSPORT:
                resourceDesignator = org.theenergymashuplab.cts.ResourceDesignatorType.TRANSPORT;
                break;
            case WATER_PRESSURE:
                resourceDesignator = org.theenergymashuplab.cts.ResourceDesignatorType.WATER_PRESSURE;
                break;
            case WATER_FLOW:
                resourceDesignator = org.theenergymashuplab.cts.ResourceDesignatorType.WATER_FLOW;
                break;
            case GAS_PRESSURE:
                resourceDesignator = org.theenergymashuplab.cts.ResourceDesignatorType.GAS_PRESSURE;
                break;
            case GAS_FLOW:
                resourceDesignator = org.theenergymashuplab.cts.ResourceDesignatorType.GAS_FLOW;
                break;
            case BANDWIDTH:
                resourceDesignator = org.theenergymashuplab.cts.ResourceDesignatorType.BANDWIDTH;
                break;
            default:
                throw new IllegalArgumentException("Invalid ResourceDesignator: " + generatedResourceDesignator);
        }

        eiTenderType.setResourceDesignator(resourceDesignator);

        int segmentId = (int) decoder.transaction().tender().tenderBase().segmentId();
        eiTenderType.setSegmentId(segmentId);

        long decodedWarrantsValue = decoder.transaction().tender().tenderBase().warrants();
        WarrantIdType warrants = new WarrantIdType(decodedWarrantsValue);
        eiTenderType.setWarrants(warrants);

        EiTransaction eiTransaction = new EiTransaction();
        payload.setTransaction(eiTransaction);

        return payload;
    }
}
