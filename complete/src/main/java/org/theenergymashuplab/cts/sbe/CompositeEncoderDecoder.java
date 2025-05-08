package org.theenergymashuplab.cts.sbe;

import org.theenergymashuplab.cts.*;
import org.theenergymashuplab.cts.ResourceDesignatorType;
import org.theenergymashuplab.cts.SideType;
import org.theenergymashuplab.cts.generated_files.*;

import java.time.Duration;
import java.time.Instant;

public class CompositeEncoderDecoder {
    public static class EiTransactionEncoderDecoder {

        public static void encode(EiTransactionTypeEncoder transactionEncoder, EiTransaction transaction) {
            transactionEncoder.marketTransactionId(transaction.getTransactionId().getMyUidId());

            EiTenderTypeEncoder tenderEncoder = transactionEncoder.tender();
            CompositeEncoderDecoder.EiTenderTypeEncoderDecoder.encode(tenderEncoder, transaction.getTender());
        }

        public static EiTransaction decode(EiTransactionTypeDecoder transactionDecoder) {
            EiTenderTypeDecoder tender = transactionDecoder.tender();

            EiTenderType eiTenderType = CompositeEncoderDecoder.EiTenderTypeEncoderDecoder.decode(tender);

            TransactionIdType transactionId = new TransactionIdType();
            transactionId.setMyUidId(transactionDecoder.marketTransactionId());

            EiTransaction transaction = new EiTransaction();
            
            //transaction.setMarketTransactionId(transactionId);
            //doesn't have a setter, but is created see EiTransactionType.java
            
            transaction.setTender(eiTenderType);

            return transaction;
        }
    }
    
	public class EiTenderTypeEncoderDecoder {

	    public static void encode(EiTenderTypeEncoder tenderEncoder, EiTenderType payloadTender) {
	        Instant expirationTime = payloadTender.getExpirationTime();
	        TenderIntervalDetail intervalDetail = (TenderIntervalDetail) payloadTender.getTenderDetail();
	        Interval interval = intervalDetail.getInterval();

	        TenderBaseEncoder base = tenderEncoder.tenderBase();
	        TenderIntervalDetailEncoder detail = base.tenderDetail();
	        IntervalEncoder intervalEncoder = detail.interval();

	        tenderEncoder
	            .marketOrderId(payloadTender.getMarketOrderId().getMyUidId())
	            .tenderId(payloadTender.getTenderId().getMyUidId());

	        base
	            .allOrNone(payloadTender.isAllOrNone() ? BooleanType.TRUE : BooleanType.FALSE)
	            .expirationTime()
	                .seconds(expirationTime.getEpochSecond())
	                .nano(expirationTime.getNano());

	        base
	            .marketId(payloadTender.getMarketId() != null ? payloadTender.getMarketId().getMyUidId() : 0)
	            .priceScale(payloadTender.getPriceScale())
	            .quantityScale(payloadTender.getQuantityScale())
	            .resourceDesignator(org.theenergymashuplab.cts.generated_files.ResourceDesignatorType.get(payloadTender.getResourceDesignator().getValue()))
	            .segmentId(payloadTender.getSegmentId())
	            .side(org.theenergymashuplab.cts.generated_files.SideType.get(payloadTender.getSide().getValue()))
	            .warrants(payloadTender.getWarrants() != null ? payloadTender.getWarrants().getMyUidId() : 0);

	        detail
	            .price(intervalDetail.getPrice())
	            .quantity(intervalDetail.getQuantity());

	        intervalEncoder
	            .dtStart()
	                .seconds(interval.getDtStart().getEpochSecond())
	                .nano(interval.getDtStart().getNano());

	        intervalEncoder
	            .duration()
	                .seconds(interval.getDuration().getSeconds())
	                .nano(interval.getDuration().getNano());
	    }

	    public static EiTenderType decode(EiTenderTypeDecoder tender) {
	        TenderBaseDecoder base = tender.tenderBase();
	        TenderIntervalDetailDecoder detail = base.tenderDetail();
	        IntervalDecoder interval = detail.interval();

	        MarketOrderIdType marketOrderId = new MarketOrderIdType();
	        marketOrderId.setMyUidId(tender.marketOrderId());

	        TenderIdType tenderId = new TenderIdType();
	        tenderId.setMyUidId(tender.tenderId());

	        Instant expirationTime = Instant.ofEpochSecond(
	            base.expirationTime().seconds(),
	            base.expirationTime().nano()
	        );

	        TenderIntervalDetail tenderIntervalDetail = null;
	        if (detail != null) {
	            long price = detail.price();
	            long quantity = detail.quantity();
	            Interval intervalObj = new Interval();
	            intervalObj.setDtStart(Instant.ofEpochSecond(interval.dtStart().seconds(), interval.dtStart().nano()));
	            intervalObj.setDuration(Duration.ofSeconds(interval.duration().seconds(), interval.duration().nano()));

	            tenderIntervalDetail = new TenderIntervalDetail(intervalObj, price, quantity);
	        }

	        SideType side = SideType.fromSbe(base.side());

	        EiTenderType eiTenderType = new EiTenderType(expirationTime, side, tenderIntervalDetail, marketOrderId);
	        //eiTenderType.setTenderId(tenderId);

	        MarketIdType marketId = new MarketIdType();
	        marketId.setMyUidId(base.marketId());
	        eiTenderType.setMarketId(marketId);

	        eiTenderType.setAllOrNone(base.allOrNone() == BooleanType.TRUE);
	        eiTenderType.setPriceScale((int) base.priceScale());
	        eiTenderType.setQuantityScale((int) base.quantityScale());
	        eiTenderType.setResourceDesignator(ResourceDesignatorType.fromSbe(base.resourceDesignator().value()));
	        eiTenderType.setSegmentId((int) base.segmentId());

	        WarrantIdType warrants = new WarrantIdType(base.warrants());
	        eiTenderType.setWarrants(warrants);

	        return eiTenderType;
	    }
	}
	
	public static class EiResponseTypeEncoderDecoder {

		        public static void encode(EiResponseTypeEncoder responseEncoder,
		                                  EiResponseType response) {

		            Instant createdDateTime = response.getCreatedDateTime();
		            responseEncoder.createdDateTime()
		                    .seconds(createdDateTime.getEpochSecond())
		                    .nano(createdDateTime.getNano());

		            responseEncoder.inResponseTo(response.getInResponseTo().getMyUidId())
		                    .responseCode(response.getResponseCode());

		            short detailCode = response.getResponseDetail().getValue();
		            responseEncoder.responseDetail(
		                    org.theenergymashuplab.cts.generated_files.ResponseDetailType.get(detailCode)
		            );
		        }

		        public static EiResponseType decode(EiResponseTypeDecoder decoder) {
		            EiResponseType response = new EiResponseType();

		            long seconds = decoder.createdDateTime().seconds();
		            int nanos = (int) decoder.createdDateTime().nano();
		            response.setCreatedDateTime(Instant.ofEpochSecond(seconds, nanos));

		            RefIdType inResponseTo = new RefIdType();
		            inResponseTo.setMyUidId(decoder.inResponseTo());
		            response.setInResponseTo(inResponseTo);

		            response.setResponseCode(decoder.responseCode());

		            short sbeVal = decoder.responseDetail().value();
		            response.setResponseDetail(org.theenergymashuplab.cts.ResponseDetailType.fromSbe(sbeVal));

		            return response;
		        }
		    }
	
}
