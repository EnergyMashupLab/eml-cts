package org.theenergymashuplab.cts.sbe;

import java.time.Duration;
import java.time.Instant;

import org.agrona.concurrent.UnsafeBuffer;
import org.theenergymashuplab.cts.*;
import org.theenergymashuplab.cts.ResourceDesignatorType;
import org.theenergymashuplab.cts.SideType;
import org.theenergymashuplab.cts.controller.payloads.EiCreateTenderPayload;
import org.theenergymashuplab.cts.controller.payloads.EiCreatedTenderPayload;
import org.theenergymashuplab.cts.generated_files.*;

public class EiTenderPayloadEncoderDecoder {

	// ENCODER
	public static int eiCreateTenderEncode(EiCreateTenderPayloadEncoder encoder, UnsafeBuffer buffer,
			MessageHeaderEncoder header, EiCreateTenderPayload payload) {

		// HEADER
		encoder.wrapAndApplyHeader(buffer, 0, header);

		EiTenderType payloadTender = payload.getTender();
		Instant expirationTime = payloadTender.getExpirationTime();
		TenderIntervalDetail intervalDetail = (TenderIntervalDetail) payloadTender.getTenderDetail();
		Interval interval = intervalDetail.getInterval();
		    
		EiTenderTypeEncoder tenderEncoder = encoder.tender();
		TenderBaseEncoder base = tenderEncoder.tenderBase();
		TenderIntervalDetailEncoder detail = base.tenderDetail();
		IntervalEncoder intervalEncoder = detail.interval();

	    encoder
        .atMostOne(payload.isAtMostOne() ? BooleanType.TRUE : BooleanType.FALSE)
        .marketId(payload.getMarketId().getMyUidId())
        .counterPartyId(payload.getCounterPartyId().getMyUidId())
        .executionInstructions(0L) // unused
        .partyId(payload.getPartyId().getMyUidId())
        .requestId(payload.getRequestId().getMyUidId())
        .segmentId(payload.getSegmentId());

		// EiTenderType
	    tenderEncoder
        .marketOrderId(payloadTender.getMarketOrderId().getMyUidId())
        .tenderId(payloadTender.getTenderId().getMyUidId())
        .tenderBase()
            .allOrNone(payloadTender.isAllOrNone() ? BooleanType.TRUE : BooleanType.FALSE)
            .expirationTime()
                .seconds(expirationTime.getEpochSecond())
                .nano(expirationTime.getNano());
	    
	    base
        .marketId(0) // workaround (null in current payloads)
        .priceScale(payloadTender.getPriceScale())
        .quantityScale(payloadTender.getQuantityScale())
        .resourceDesignator(org.theenergymashuplab.cts.generated_files.ResourceDesignatorType.get(payloadTender.getResourceDesignator().getValue()))
        .segmentId(payload.getSegmentId())
        .side(org.theenergymashuplab.cts.generated_files.SideType.get(payloadTender.getSide().getValue()))
        .warrants(0); // workaround (null in current payloads)

		// ENCODE TENDER DETAIL
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

//		System.out.println("\nEiCreateTenderPayload Encoded :-");
//		System.out.println(encoder.toString() + "\n");
	    return header.encodedLength() + encoder.encodedLength();

	}

	// DECODER
	public static EiCreateTenderPayload eiCreateTenderDecode(EiCreateTenderPayloadDecoder decoder,
			UnsafeBuffer buffer, int offset, int actingBlockLength, int actingVersion) throws Exception {

		// Wrap the decoder to start reading from the provided buffer offset
		decoder.wrap(buffer, offset, actingBlockLength, actingVersion);

//		System.out.println("\nEiCreateTenderPayload Decoded :-");
//		System.out.println(eiCreateTenderPayloadDecoder.toString() + "\n");
		EiTenderTypeDecoder tender = decoder.tender();
		TenderBaseDecoder base = tender.tenderBase();
		TenderIntervalDetailDecoder detail = base.tenderDetail();
		IntervalDecoder interval = detail.interval();

		EiCreateTenderPayload eiCreateTenderPayload = new EiCreateTenderPayload();

		BooleanType atMostOne = decoder.atMostOne();
		eiCreateTenderPayload.setAtMostOne(atMostOne == BooleanType.TRUE);

		ActorIdType counterPartyId = new ActorIdType();
		counterPartyId.setMyUidId(decoder.counterPartyId());
		eiCreateTenderPayload.setCounterPartyId(counterPartyId);

		//not currently used, set to empty
		eiCreateTenderPayload.setExecutionInstructions("");

		MarketIdType marketIdType = new MarketIdType();
		marketIdType.setMyUidId(decoder.marketId());
		eiCreateTenderPayload.setMarketId(marketIdType);

		ActorIdType partyId = new ActorIdType();
		partyId.setMyUidId(decoder.partyId());
		eiCreateTenderPayload.setPartyId(partyId);

		RefIdType requestId = new RefIdType();
		requestId.setMyUidId(decoder.requestId());
		eiCreateTenderPayload.setRequestId(requestId);

		int segmentId = (int) decoder.segmentId();
		eiCreateTenderPayload.setSegmentId(segmentId);
//TENDER----------------------------------------------------------------------
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

		// Set the decoded fields back in the payload object
		eiCreateTenderPayload.setTender(eiTenderType);

		return eiCreateTenderPayload;
	}
	
	public static int eiCreatedTenderEncode(
	        EiCreatedTenderPayloadEncoder encoder,
	        UnsafeBuffer buffer,
	        MessageHeaderEncoder header,
	        EiCreatedTenderPayload payload) {

	    encoder.wrapAndApplyHeader(buffer, 0, header);
	    

		EiResponseType response = payload.getResponse();
	    Instant createdDateTime = response.getCreatedDateTime();
	    String description = response.getResponseDescription();

	    EiResponseTypeEncoder responseEncoder = encoder.response();


	    encoder.counterPartyId(payload.getCounterPartyId().getMyUidId())
	    .inResponseTo(payload.getInResponseTo().getMyUidId())
	    .marketOrderId(payload.getMarketOrderId().getMyUidId())
	    .partyId(payload.getPartyId().getMyUidId());


	    responseEncoder.createdDateTime()
	        .seconds(createdDateTime.getEpochSecond())
	        .nano(createdDateTime.getNano());

	    responseEncoder.inResponseTo(response.getInResponseTo().getMyUidId())
	    .responseCode(response.getResponseCode());

	    encoder.responseDescription(description);

	    short code = payload.getResponse().getResponseDetail().getValue();
	    org.theenergymashuplab.cts.generated_files.ResponseDetailType encodedEnum = org.theenergymashuplab.cts.generated_files.ResponseDetailType.get(code);
	    responseEncoder.responseDetail(encodedEnum);

	    encoder.tenderId(payload.getTenderId().getMyUidId());

//	    System.out.println("\nEiCreatedTenderPayload Encoded:");
//	    System.out.println(encoder.toString() + "\n");

	    return header.encodedLength() + encoder.encodedLength();
	}

		public static EiCreatedTenderPayload eiCreatedTenderPayloadDecode(
				EiCreatedTenderPayloadDecoder decoder,
				UnsafeBuffer buffer,
				int offset,
				int actingBlockLength,
				int actingVersion) throws Exception {

			// Wrap decoder to start reading from the provided buffer
			decoder.wrap(buffer, offset, actingBlockLength, actingVersion);

//			System.out.println("\nEiCreatedTenderPayload Decoded :-");
//			System.out.println(eiCreatedTenderPayloadDecoder.toString() + "\n");

			EiResponseTypeDecoder responseDecoder = decoder.response();

			
			EiCreatedTenderPayload eiCreatedTenderPayload = new EiCreatedTenderPayload();

			ActorIdType counterPartyId = new ActorIdType();
			counterPartyId.setMyUidId(decoder.counterPartyId());
			eiCreatedTenderPayload.setCounterPartyId(counterPartyId);

			RefIdType inResponseTo = new RefIdType();
			inResponseTo.setMyUidId(decoder.inResponseTo());
			eiCreatedTenderPayload.setInResponseTo(inResponseTo);

			MarketOrderIdType marketOrderId = new MarketOrderIdType();
			marketOrderId.setMyUidId(decoder.marketOrderId());
			eiCreatedTenderPayload.setMarketOrderId(marketOrderId);

			ActorIdType partyId = new ActorIdType();
			partyId.setMyUidId(decoder.partyId());
			eiCreatedTenderPayload.setPartyId(partyId);

			TenderIdType tenderId = new TenderIdType();
			tenderId.setMyUidId(decoder.tenderId());
			eiCreatedTenderPayload.setTenderId(tenderId);

			EiResponseType eiResponse = new EiResponseType();

			long seconds = responseDecoder.createdDateTime().seconds();
			int nanos = (int) responseDecoder.createdDateTime().nano();
			eiResponse.setCreatedDateTime(Instant.ofEpochSecond(seconds, nanos));

			RefIdType responseInResponseTo = new RefIdType();
			responseInResponseTo.setMyUidId(responseDecoder.inResponseTo());
			eiResponse.setInResponseTo(responseInResponseTo);

			eiResponse.setResponseCode(responseDecoder.responseCode());
			eiResponse.setResponseDescription(decoder.responseDescription());

			short sbeValue = responseDecoder.responseDetail().value();
			org.theenergymashuplab.cts.ResponseDetailType responseDetail = org.theenergymashuplab.cts.ResponseDetailType.fromSbe(sbeValue);

			eiResponse.setResponseDetail(responseDetail);
			eiCreatedTenderPayload.setResponse(eiResponse);

			// Return the fully decoded payload
			return eiCreatedTenderPayload;
		}

}
