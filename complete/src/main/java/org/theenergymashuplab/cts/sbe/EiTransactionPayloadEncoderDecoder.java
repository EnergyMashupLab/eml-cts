package org.theenergymashuplab.cts.sbe;

import org.agrona.concurrent.UnsafeBuffer;
import org.theenergymashuplab.cts.*;
import org.theenergymashuplab.cts.ResourceDesignatorType;
import org.theenergymashuplab.cts.SideType;
import org.theenergymashuplab.cts.controller.payloads.EiCreateTransactionPayload;
import org.theenergymashuplab.cts.controller.payloads.EiCreatedTransactionPayload;
import org.theenergymashuplab.cts.generated_files.*;

import java.time.Duration;
import java.time.Instant;

public class EiTransactionPayloadEncoderDecoder {

    public static int eiCreateTransactionEncode(EiCreateTransactionPayloadEncoder encoder,
                             UnsafeBuffer buffer,
                             MessageHeaderEncoder header,
                             EiCreateTransactionPayload payload) {

        encoder.wrapAndApplyHeader(buffer, 0, header);
        encodeIdFields(encoder, payload);
        encodeTransactionField(encoder, payload);
        return MessageHeaderEncoder.ENCODED_LENGTH + encoder.encodedLength();
    }

    private static void encodeIdFields(EiCreateTransactionPayloadEncoder encoder,
                                       EiCreateTransactionPayload payload) {

        encoder.counterPartyId(payload.getCounterPartyId().getMyUidId());
        encoder.marketTransactionId(payload.getMarketTransactionId().getMyUidId());
        encoder.partyId(payload.getPartyId().getMyUidId());
        encoder.requestId(payload.getRequestId().getMyUidId());
    }

    private static void encodeTransactionField(EiCreateTransactionPayloadEncoder encoder,
                                               EiCreateTransactionPayload payload) {

        EiTransaction transaction = payload.getTransaction();
        EiTenderType tender = transaction.getTender();
		TenderIntervalDetail intervalDetail = (TenderIntervalDetail) tender.getTenderDetail();
		Interval interval = intervalDetail.getInterval();


        EiTransactionTypeEncoder transactionEncoder = encoder.transaction();
        EiTenderTypeEncoder tenderEncoder = transactionEncoder.tender();
        TenderBaseEncoder baseEncoder = tenderEncoder.tenderBase();
		TenderIntervalDetailEncoder detail = baseEncoder.tenderDetail();
		IntervalEncoder intervalEncoder = detail.interval();

        Instant expirationTime = tender.getExpirationTime();

        transactionEncoder.marketTransactionId(transaction.getTransactionId().getMyUidId());
        tenderEncoder.marketOrderId(tender.getMarketOrderId().value())
        .tenderId(tender.getTenderId().value());
        //currently null in payloads
       // .referencedQuoteId(tender.getReferencedQuoteId().value());
        baseEncoder.allOrNone(tender.isAllOrNone() ? BooleanType.TRUE : BooleanType.FALSE)
        .executionInstructions(0)
        .expirationTime().seconds(expirationTime.getEpochSecond()).nano(expirationTime.getNano());
        baseEncoder.marketId(transaction.getTender().getMarketId().value())
        .priceScale(transaction.getTender().getPriceScale())
        .quantityScale(transaction.getTender().getQuantityScale())
        .resourceDesignator(org.theenergymashuplab.cts.generated_files.ResourceDesignatorType.get(
        		tender.getResourceDesignator().getValue()));
        baseEncoder.segmentId(transaction.getTender().getSegmentId())
        .side(org.theenergymashuplab.cts.generated_files.SideType.get(payload.getTransaction().getTender().getSide().getValue()));

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

        encoder.transaction().tender().tenderBase()
                .warrants(transaction.getTender().getWarrants().value());
    }
    
    public static EiCreateTransactionPayload eiCreateTransactionDecode(EiCreateTransactionPayloadDecoder decoder,
                                                    UnsafeBuffer unsafeBuffer,
                                                    int bufferOffset,
                                                    int actingBlockLength,
                                                    int actingVersion) {

        decoder.wrap(unsafeBuffer, bufferOffset, actingBlockLength, actingVersion);

		EiTenderTypeDecoder tender = decoder.transaction().tender();
		TenderBaseDecoder base = tender.tenderBase();
		TenderIntervalDetailDecoder detail = base.tenderDetail();
		IntervalDecoder interval = detail.interval();
		
        EiCreateTransactionPayload payload = new EiCreateTransactionPayload();

        // id types
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


        // transaction no setter in transactionType???
        TransactionIdType marketTransactionId2 = new TransactionIdType();
        marketTransactionId2.setMyUidId(decoder.marketTransactionId());
        
		MarketOrderIdType marketOrderId = new MarketOrderIdType();
		marketOrderId.setMyUidId(tender.marketOrderId());

		TenderIdType tenderId = new TenderIdType();
		tenderId.setMyUidId(tender.tenderId());
		
		//currently null
//		MarketOrderIdType referenceQuoteIdTypeId = new MarketOrderIdType();
//		referenceQuoteIdTypeId.setMyUidId(tender.referencedQuoteId());
//		

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

        EiTransaction eiTransaction = new EiTransaction();
        eiTransaction.setTender(eiTenderType);

        payload.setTransaction(eiTransaction);

        return payload;
    }

	public static int eiCreatedTransactionEncode(
			EiCreatedTransactionPayloadEncoder encoder,
			UnsafeBuffer buffer,
			MessageHeaderEncoder header,
			EiCreatedTransactionPayload payload) {

		encoder.wrapAndApplyHeader(buffer, 0, header);

		// Counter Party Id field
		encoder.counterPartyId(payload.getCounterPartyId().getMyUidId())
		.marketTransactionId(payload.getMarketTransactionId().getMyUidId())
		.partyId(payload.getPartyId().getMyUidId())
		.recipientTransactionId(payload.getRecipientTransactionId().getMyUidId())
		.refId(payload.getRefId().getMyUidId());

		// Response field ----
		Instant responseCreatedDateTime = payload.getResponse().getCreatedDateTime();
		encoder.response().createdDateTime()
				.seconds(responseCreatedDateTime.getEpochSecond())
				.nano(responseCreatedDateTime.getNano());
		
		encoder.response().inResponseTo(payload.getResponse().getInResponseTo().value());
		encoder.response().responseCode(payload.getResponse().responseCode);
		encoder.responseDescription(payload.getResponse().responseDescription);
		encoder.response().responseDetail(org.theenergymashuplab.cts.generated_files.ResponseDetailType.valueOf(payload.getResponse().getResponseDetail().name())); 
		encoder.transactionId(payload.getTransactionId().getMyUidId());

		
		
		System.out.println("\n-------------------------------------------------------------------------");
		System.out.println("EiCreatedTransaction encoded :-");
		System.out.println(encoder.toString());
		
		return header.ENCODED_LENGTH + encoder.encodedLength();

	}

	public static EiCreatedTransactionPayload eiCreatedTransactionDecode(
			EiCreatedTransactionPayloadDecoder decoder, UnsafeBuffer buffer,
			int offset,
			int actingBlockLength, int actingVersion) throws Exception {

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

		// Response
		//EiResponseType response = EiResponseTypeEncoderDecoder.Decode(eiCreatedTransactionDecoder.response());
		EiResponseType response = new EiResponseType();
		
		org.theenergymashuplab.cts.ResponseDetailType appEnum = org.theenergymashuplab.cts.ResponseDetailType.valueOf(decoder.response().responseDetail().name());

		long seconds = decoder.response().createdDateTime().seconds();
		int nanos = (int) decoder.response().createdDateTime().nano();
		response.setCreatedDateTime(Instant.ofEpochSecond(seconds, nanos));
			
		RefIdType inResponseTo= new RefIdType();
		inResponseTo.setMyUidId(decoder.response().inResponseTo());
		response.setInResponseTo(inResponseTo);
			
		response.setResponseDescription(decoder.responseDescription());
		
		response.setResponseCode(decoder.response().responseCode());
		
		response.setResponseDetail(appEnum);
		
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
