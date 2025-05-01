package org.theenergymashuplab.cts.sbe;

import org.agrona.concurrent.UnsafeBuffer;
import org.theenergymashuplab.cts.*;
import org.theenergymashuplab.cts.ResourceDesignatorType;
import org.theenergymashuplab.cts.SideType;
import org.theenergymashuplab.cts.controller.payloads.EiCreateTransactionPayload;
import org.theenergymashuplab.cts.generated_files.*;

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

        encoder.transaction().marketTransactionId(marketTransactionId);
        encoder.transaction().tender().marketOrderId(marketOrderId);
        encoder.transaction().tender().tenderId(tenderId);
        encoder.transaction().tender().tenderBase().allOrNone(allOrNone);
        encoder.transaction().tender().tenderBase().expirationTime().seconds(expirationTime.getEpochSecond()).nano(expirationTime.getNano());
        encoder.transaction().tender().tenderBase().marketId(transaction.getTender().getMarketId().value());
        encoder.transaction().tender().tenderBase().priceScale(transaction.getTender().getPriceScale());
        encoder.transaction().tender().tenderBase().quantityScale(transaction.getTender().getQuantityScale());
        encoder.transaction().tender().tenderBase().resourceDesignator(org.theenergymashuplab.cts.generated_files.ResourceDesignatorType.get(
	            payload.getTransaction().getTender().getResourceDesignator().getValue()));
        encoder.transaction().tender().tenderBase().segmentId(transaction.getTender().getSegmentId());

		encoder.transaction().tender().tenderBase().side(org.theenergymashuplab.cts.generated_files.SideType.get(payload.getTransaction().getTender().getSide().getValue()));

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
    
    public static EiCreateTransactionPayload decode(EiCreateTransactionPayloadDecoder decoder,
                                                    UnsafeBuffer unsafeBuffer,
                                                    int bufferOffset,
                                                    int actingBlockLength,
                                                    int actingVersion) {

        decoder.wrap(unsafeBuffer, bufferOffset, actingBlockLength, actingVersion);

        EiCreateTransactionPayload payload = new EiCreateTransactionPayload();

        // id types
        ActorIdType counterPartyId = new ActorIdType();
        counterPartyId.setMyUidId(decoder.counterPartyId());

        TransactionIdType marketTransactionId = new TransactionIdType();
        marketTransactionId.setMyUidId(decoder.marketTransactionId());

        ActorIdType partyId = new ActorIdType();
        partyId.setMyUidId(decoder.marketTransactionId());

        RefIdType requestId = new RefIdType();
        requestId.setMyUidId(decoder.requestId());

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

		SideType side = SideType.fromSbe(decoder.transaction().tender().tenderBase().side());


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

		eiTenderType.setResourceDesignator(ResourceDesignatorType.fromSbe(decoder.transaction().tender().tenderBase().resourceDesignator().value()));

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
