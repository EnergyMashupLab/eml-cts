package org.theenergymashuplab.cts.sbe;

import java.time.Duration;
import java.time.Instant;

import org.agrona.concurrent.UnsafeBuffer;
import org.theenergymashuplab.cts.*;
import org.theenergymashuplab.cts.ResourceDesignatorType;
import org.theenergymashuplab.cts.SideType;
import org.theenergymashuplab.cts.controller.payloads.EiCreateTenderPayload;
import org.theenergymashuplab.cts.generated_files.*;

public class EiCreateTenderPayloadEncoderDecoder {
	//ENCODER
	public static int eiCreateTenderEncode(
			EiCreateTenderPayloadEncoder eiCreateTenderPayloadEncoder,
			UnsafeBuffer directBuffer, 
			MessageHeaderEncoder messageHeaderEncoder,
			EiCreateTenderPayload eiCreateTenderPayload) {
		
		eiCreateTenderPayloadEncoder.wrapAndApplyHeader(directBuffer, 0, messageHeaderEncoder);
		//encode At Most One field
		eiCreateTenderPayloadEncoder.atMostOne(eiCreateTenderPayload.isAtMostOne() ? BooleanType.TRUE : BooleanType.FALSE);
		
		// Encode Market Id field
		if (eiCreateTenderPayload.getMarketId() != null) {
		    eiCreateTenderPayloadEncoder.marketId(eiCreateTenderPayload.getMarketId().value());
		} else {
		    // Optional: Set a default MarketId if missing
		    eiCreateTenderPayloadEncoder.marketId(0L); // or any "null" placeholder you want
		}

		// Encode: counterPartyId, executionInstructions (0), partyId, requestId, and segmentId
		eiCreateTenderPayloadEncoder.counterPartyId(eiCreateTenderPayload.getCounterPartyId().getMyUidId());
		eiCreateTenderPayloadEncoder.executionInstructions(0L); // executionInstructions is unused; pass 0
		eiCreateTenderPayloadEncoder.partyId(eiCreateTenderPayload.getPartyId().getMyUidId());
		eiCreateTenderPayloadEncoder.requestId(eiCreateTenderPayload.getRequestId().getMyUidId());
		eiCreateTenderPayloadEncoder.segmentId(eiCreateTenderPayload.getSegmentId());

//-------------------------------------------------------------------------------------------
		// EiTenderType
		
		// Encode, MarketOrderID, Tender ID, All or None
		eiCreateTenderPayloadEncoder.tender().marketOrderId(eiCreateTenderPayload.getTender().getMarketOrderId().value());
		eiCreateTenderPayloadEncoder.tender().tenderId(eiCreateTenderPayload.getTender().getTenderId().value());
		eiCreateTenderPayloadEncoder.tender().tenderBase().allOrNone(eiCreateTenderPayload.getTender().isAllOrNone() ? BooleanType.TRUE : BooleanType.FALSE);

		// ENCODE expiration time
		Instant expirationTime = eiCreateTenderPayload.getTender().getExpirationTime();
		eiCreateTenderPayloadEncoder.tender().tenderBase()
		    .expirationTime()
		    .seconds(expirationTime.getEpochSecond())
		    .nano(expirationTime.getNano());


		// ENCODE market ID
		if (eiCreateTenderPayload.getTender() != null && eiCreateTenderPayload.getTender().getMarketId() != null) {
		    eiCreateTenderPayloadEncoder.tender().tenderBase()
		        .marketId(eiCreateTenderPayload.getTender().getMarketId().value());
		} else {
		    eiCreateTenderPayloadEncoder.tender().tenderBase()
		        .marketId(0L);
		}

		// Encode: price Scale and quantity Scale
		eiCreateTenderPayloadEncoder.tender().tenderBase().priceScale(eiCreateTenderPayload.getTender().getPriceScale());
		eiCreateTenderPayloadEncoder.tender().tenderBase().quantityScale(eiCreateTenderPayload.getTender().getQuantityScale());
		
		eiCreateTenderPayloadEncoder.tender().tenderBase().resourceDesignator(org.theenergymashuplab.cts.generated_files.ResourceDesignatorType.get(
	            eiCreateTenderPayload.getTender().getResourceDesignator().getValue()));

		// encode segmentId
		eiCreateTenderPayloadEncoder.tender().tenderBase().segmentId(eiCreateTenderPayload.getSegmentId());

		// encode sideType
		eiCreateTenderPayloadEncoder.tender().tenderBase().side(org.theenergymashuplab.cts.generated_files.SideType.get(eiCreateTenderPayload.getTender().getSide().getValue()));

		//ENCODE TENDER DETAIL
		TenderDetail tenderDetail = eiCreateTenderPayload.getTender().getTenderDetail();

		if (tenderDetail instanceof TenderIntervalDetail) {
			TenderIntervalDetail intervalDetail = (TenderIntervalDetail) tenderDetail;
			long price = intervalDetail.getPrice();
			long quantity = intervalDetail.getQuantity();
			Interval interval = intervalDetail.getInterval();

			// Price & Quantity & Interval
			eiCreateTenderPayloadEncoder.tender().tenderBase().tenderDetail().price(price).quantity(quantity);

			Instant dtStart = interval.getDtStart();
			Duration duration = interval.getDuration();
			
			//extract and decode DtStart
			eiCreateTenderPayloadEncoder.tender().tenderBase().tenderDetail()
			    .interval().dtStart()
			        .seconds(dtStart.getEpochSecond())
			        .nano(dtStart.getNano());
			//extract and decode Duration
			eiCreateTenderPayloadEncoder.tender().tenderBase().tenderDetail()
			    .interval().duration()
			        .seconds(duration.getSeconds())
			        .nano(duration.getNano());
		} else {
			throw new IllegalStateException("Unexpected TenderDetail type: " + tenderDetail.getClass().getName());
		}

		// warrants
		if (eiCreateTenderPayload.getTender() != null && eiCreateTenderPayload.getTender().getWarrants() != null) {
		    eiCreateTenderPayloadEncoder.tender().tenderBase()
		        .warrants(eiCreateTenderPayload.getTender().getWarrants().value());
		} else {
		    eiCreateTenderPayloadEncoder.tender().tenderBase()
		        .warrants(0L); // default if missing
		}
		// instantEncoder.expirationTime()
		System.out.println("");
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("EiCreateTenderPayload Encoded :-");
		System.out.println(eiCreateTenderPayloadEncoder.toString());

		return messageHeaderEncoder.ENCODED_LENGTH + eiCreateTenderPayloadEncoder.encodedLength();

	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	//DECODER
	public static EiCreateTenderPayload eiCreateTenderPayloadDecode(
			EiCreateTenderPayloadDecoder eiCreateTenderPayloadDecoder, 
			UnsafeBuffer directBuffer, int bufferOffset,
			int actingBlockLength, 
			int actingVersion) throws Exception {

		// Wrap the decoder to start reading from the provided buffer offset
		eiCreateTenderPayloadDecoder.wrap(directBuffer, bufferOffset, actingBlockLength, actingVersion);

		System.out.println("");
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("EiCreateTenderPayload Decoded :-");
		System.out.println(eiCreateTenderPayloadDecoder.toString());

		EiCreateTenderPayload eiCreateTenderPayload = new EiCreateTenderPayload();

		// decode the atMostOne
		BooleanType atMostOne = eiCreateTenderPayloadDecoder.atMostOne();
		eiCreateTenderPayload.setAtMostOne(atMostOne == BooleanType.TRUE);

		// Decode the 'counterPartyId' (ActorIdType)
		ActorIdType counterPartyId = new ActorIdType();
		counterPartyId.setMyUidId(eiCreateTenderPayloadDecoder.counterPartyId());
		
		// Decode the executionInstructions, check if 0
		long execInstr = eiCreateTenderPayloadDecoder.executionInstructions();
		String executionInstructions = (execInstr == 0) ? "" : String.valueOf(execInstr);
		eiCreateTenderPayload.setExecutionInstructions(executionInstructions);

		// Decode marketId
		MarketIdType marketIdType = new MarketIdType();
		marketIdType.setMyUidId(eiCreateTenderPayloadDecoder.marketId());
		eiCreateTenderPayload.setMarketId(marketIdType);

		// Decode the 'partyId' (ActorIdType)
		ActorIdType partyId = new ActorIdType();
		partyId.setMyUidId(eiCreateTenderPayloadDecoder.partyId());

		// Decode the 'requestId' (RefIdType)
		RefIdType requestId = new RefIdType();
		requestId.setMyUidId(eiCreateTenderPayloadDecoder.requestId());

		// Decode the 'segmentID'
		int segmentId = (int) eiCreateTenderPayloadDecoder.segmentId();
		eiCreateTenderPayload.setSegmentId(segmentId);
//TENDER----------------------------------------------------------------------
		// MarketOrderIdType
		MarketOrderIdType marketOrderId = new MarketOrderIdType();
		marketOrderId.setMyUidId(eiCreateTenderPayloadDecoder.tender().marketOrderId());

		// Decode TenderId
		TenderIdType tenderId = new TenderIdType();
		tenderId.setMyUidId(eiCreateTenderPayloadDecoder.tender().tenderId());

		// Decode the expiration timeand set the tender expire time
		long expirationTimeSeconds = eiCreateTenderPayloadDecoder.tender().tenderBase().expirationTime().seconds();
		int expirationTimeNano = (int) eiCreateTenderPayloadDecoder.tender().tenderBase().expirationTime().nano();

		Instant expirationTime = Instant.ofEpochSecond(expirationTimeSeconds, expirationTimeNano);

		// Get the 'tenderDetail' decoder
		TenderIntervalDetailDecoder tenderDetailDecoder = eiCreateTenderPayloadDecoder.tender().tenderBase().tenderDetail();
		
		TenderDetail tenderDetail = null;

		// Extract details from the TenderIntervalDetailDecoder
		if (tenderDetailDecoder != null) {
			long price = tenderDetailDecoder.price();
			long quantity = tenderDetailDecoder.quantity();

			// Interval details

			// Set the start time and duration from the interval
			Interval interval = new Interval();
			interval.setDtStart(Instant.ofEpochSecond(tenderDetailDecoder.interval().dtStart().seconds(),
					tenderDetailDecoder.interval().dtStart().nano()));
			interval.setDuration(Duration.ofSeconds(tenderDetailDecoder.interval().duration().seconds(),
					tenderDetailDecoder.interval().duration().nano()));

			TenderIntervalDetail tenderIntervalDetail = new TenderIntervalDetail(interval, price, quantity);
			tenderDetail = tenderIntervalDetail;

		}
		
		SideType side = SideType.fromSbe(eiCreateTenderPayloadDecoder.tender().tenderBase().side());

		EiTenderType eiTenderType = new EiTenderType(expirationTime, side, tenderDetail, marketOrderId);
		
		// Decode the 'marketId' (MarketIdType)
		MarketIdType marketId = new MarketIdType();
		marketId.setMyUidId(eiCreateTenderPayloadDecoder.tender().tenderBase().marketId());
		eiTenderType.setMarketId(marketId);

		// Decode 'AllOrNone' (Boolean)
		BooleanType allOrNone = eiCreateTenderPayloadDecoder.tender().tenderBase().allOrNone();
		eiTenderType.setAllOrNone(allOrNone == BooleanType.TRUE); // Set it as true if it is BooleanType.TRUE

		// Decode price scale (e.g., price scale could be a numeric value like an
		// integer or long)
		int priceScale = (int) eiCreateTenderPayloadDecoder.tender().tenderBase().priceScale();
		eiTenderType.setPriceScale(priceScale);

		//Decode: quantity scale, ResourceDesignator, Segment ID, Warrants
		int quantityScale = (int) eiCreateTenderPayloadDecoder.tender().tenderBase().quantityScale();
		eiTenderType.setQuantityScale(quantityScale); // Set the decoded quantity scale
				eiTenderType.setResourceDesignator(ResourceDesignatorType.fromSbe(eiCreateTenderPayloadDecoder.tender().tenderBase().resourceDesignator().value()));

		segmentId = (int) eiCreateTenderPayloadDecoder.tender().tenderBase().segmentId();
		eiTenderType.setSegmentId(segmentId); // Set the decoded segment ID

		long decodedWarrantsValue = eiCreateTenderPayloadDecoder.tender().tenderBase().warrants();
		WarrantIdType warrants = new WarrantIdType(decodedWarrantsValue);
		eiTenderType.setWarrants(warrants);

		// Set the decoded fields in the payload object
		eiCreateTenderPayload.setCounterPartyId(counterPartyId);
		eiCreateTenderPayload.setPartyId(partyId);
		eiCreateTenderPayload.setRequestId(requestId);
		eiCreateTenderPayload.setTender(eiTenderType);

		return eiCreateTenderPayload;
	}
}
