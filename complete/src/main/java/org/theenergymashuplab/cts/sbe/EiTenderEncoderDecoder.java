package org.theenergymashuplab.cts.sbe;

import java.awt.JobAttributes.SidesType;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;

import org.agrona.concurrent.UnsafeBuffer;
import org.hibernate.type.descriptor.java.BooleanJavaType;
import org.theenergymashuplab.cts.*;
import org.theenergymashuplab.cts.controller.payloads.EiCreateTenderPayload;
import org.theenergymashuplab.cts.controller.payloads.EiCreatedTenderPayload;
import org.theenergymashuplab.cts.generated_files.*;
import org.theenergymashuplab.cts.generated_files.ResourceDesignatorType;

//import baseline.*;

public class EiTenderEncoderDecoder {

	public static int eiCreateTenderEncode(EiCreateTenderPayloadEncoder eiCreateTenderPayloadEncoder,
			UnsafeBuffer directBuffer, MessageHeaderEncoder messageHeaderEncoder,
			EiCreateTenderPayload eiCreateTenderPayload) {
		eiCreateTenderPayloadEncoder.wrapAndApplyHeader(directBuffer, 0, messageHeaderEncoder);

		// At Most One field
		eiCreateTenderPayloadEncoder
				.atMostOne(eiCreateTenderPayload.isAtMostOne() ? BooleanType.TRUE : BooleanType.FALSE);

		eiCreateTenderPayloadEncoder.marketId(eiCreateTenderPayload.getMarketId().value());

		// Counter Party Id field
		eiCreateTenderPayloadEncoder.counterPartyId(eiCreateTenderPayload.getCounterPartyId().getMyUidId());

		// executionInstructions field

		ByteBuffer byteBuffer = ByteBuffer.allocate(128);
		UnsafeBuffer buffer = new UnsafeBuffer(byteBuffer);

		VarStringEncodingEncoder encoder = new VarStringEncodingEncoder();
		int offset = 0;
		encoder.wrap(buffer, offset);

		String executionInstructions = eiCreateTenderPayload.getExecutionInstructions();

		if (executionInstructions != null) {
			byte[] varData = executionInstructions.getBytes(StandardCharsets.UTF_8);

			// Encode length
			encoder.length(varData.length);

			// Encode the string data after the length
			int varDataOffset = offset + VarStringEncodingEncoder.varDataEncodingOffset();
			buffer.putBytes(varDataOffset, varData);
		} else {
			// Handle null by setting length to null value
			encoder.length(VarStringEncodingEncoder.lengthNullValue());
		}
		;

		// party Id Field
		eiCreateTenderPayloadEncoder.partyId(eiCreateTenderPayload.getPartyId().getMyUidId());

		// request Id field
		eiCreateTenderPayloadEncoder.requestId(eiCreateTenderPayload.getRequestId().getMyUidId());

		// segmentId field
		eiCreateTenderPayloadEncoder.segmentId(eiCreateTenderPayload.getSegmentId());

//-------------------------------------------------------------------------------------------
		// EiTenderType
		// MarketOrderID
		eiCreateTenderPayloadEncoder.tender()
				.marketOrderId(eiCreateTenderPayload.getTender().getMarketOrderId().value());
		// TenderID
		eiCreateTenderPayloadEncoder.tender().tenderId(eiCreateTenderPayload.getTender().getTenderId().value());

		// all or None
		eiCreateTenderPayloadEncoder.tender().tenderBase()
				.allOrNone(eiCreateTenderPayload.getTender().isAllOrNone() ? BooleanType.TRUE : BooleanType.FALSE);

		// expiration time
		eiCreateTenderPayloadEncoder.tender().tenderBase().expirationTime().seconds(10).nano(00);

		// market ID
		eiCreateTenderPayloadEncoder.tender().tenderBase()
				.marketId(eiCreateTenderPayload.getTender().getMarketId().value());

		// price Scale
		eiCreateTenderPayloadEncoder.tender().tenderBase()
				.priceScale(eiCreateTenderPayload.getTender().getPriceScale());
		// quantity Scale
		eiCreateTenderPayloadEncoder.tender().tenderBase()
				.quantityScale(eiCreateTenderPayload.getTender().getQuantityScale());
		
		// resource Designator
		org.theenergymashuplab.cts.generated_files.ResourceDesignatorType encodedResourceDesignator;

		switch (eiCreateTenderPayload.getTender().getResourceDesignator()) {
		case POWER:
			encodedResourceDesignator = org.theenergymashuplab.cts.generated_files.ResourceDesignatorType.POWER;
			break;
		case ENERGY:
			encodedResourceDesignator = org.theenergymashuplab.cts.generated_files.ResourceDesignatorType.ENERGY;
			break;
		case TRANSPORT:
			encodedResourceDesignator = org.theenergymashuplab.cts.generated_files.ResourceDesignatorType.TRANSPORT;
			break;
		case WATER_PRESSURE:
			encodedResourceDesignator = org.theenergymashuplab.cts.generated_files.ResourceDesignatorType.WATER_PRESSURE;
			break;
		case WATER_FLOW:
			encodedResourceDesignator = org.theenergymashuplab.cts.generated_files.ResourceDesignatorType.WATER_FLOW;
			break;
		case GAS_PRESSURE:
			encodedResourceDesignator = org.theenergymashuplab.cts.generated_files.ResourceDesignatorType.GAS_PRESSURE;
			break;
		case GAS_FLOW:
			encodedResourceDesignator = org.theenergymashuplab.cts.generated_files.ResourceDesignatorType.GAS_FLOW;
			break;
		case BANDWIDTH:
			encodedResourceDesignator = org.theenergymashuplab.cts.generated_files.ResourceDesignatorType.BANDWIDTH;
			break;
		default:
			throw new IllegalArgumentException(
					"Invalid ResourceDesignator: " + eiCreateTenderPayload.getTender().getResourceDesignator());
		}
		eiCreateTenderPayloadEncoder.tender().tenderBase().resourceDesignator(encodedResourceDesignator);

		// segmentId
		eiCreateTenderPayloadEncoder.tender().tenderBase().segmentId(eiCreateTenderPayload.getSegmentId());

		// sideType

		if (eiCreateTenderPayload.getTender().getSide() == org.theenergymashuplab.cts.SideType.BUY) {
			eiCreateTenderPayloadEncoder.tender().tenderBase()
					.side(org.theenergymashuplab.cts.generated_files.SideType.BUY);
		} else {
			eiCreateTenderPayloadEncoder.tender().tenderBase()
					.side(org.theenergymashuplab.cts.generated_files.SideType.SELL);
		}

		// TENDER DETAIL
		var tenderEncoder = eiCreateTenderPayloadEncoder.tender();
		TenderDetail tenderDetail = eiCreateTenderPayload.getTender().getTenderDetail();

		if (tenderDetail instanceof TenderIntervalDetail) {
			TenderIntervalDetail intervalDetail = (TenderIntervalDetail) tenderDetail;
			long price = intervalDetail.getPrice();
			long quantity = intervalDetail.getQuantity();
			Interval interval = intervalDetail.getInterval();

			// Price & Quantity & Interval
			//
			eiCreateTenderPayloadEncoder.tender().tenderBase().tenderDetail().price(price).quantity(quantity);

			eiCreateTenderPayloadEncoder.tender().tenderBase().tenderDetail().interval().dtStart().seconds(10).nano(00);

			eiCreateTenderPayloadEncoder.tender().tenderBase().tenderDetail().interval().duration().seconds(10)
					.nano(00);

		} else {
			throw new IllegalStateException("Unexpected TenderDetail type: " + tenderDetail.getClass().getName());
		}

		// warrants
		eiCreateTenderPayloadEncoder.tender().tenderBase()
				.warrants(eiCreateTenderPayload.getTender().getWarrants().value());

		// instantEncoder.expirationTime()

		System.out.println("");
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("EiCreateTenderPayload Encoded :-");
		System.out.println(eiCreateTenderPayloadEncoder.toString());

		return messageHeaderEncoder.ENCODED_LENGTH + eiCreateTenderPayloadEncoder.encodedLength();

	}

	public static EiCreateTenderPayload eiCreateTenderPayloadDecode(
			EiCreateTenderPayloadDecoder eiCreateTenderPayloadDecoder, UnsafeBuffer directBuffer, int bufferOffset,
			int actingBlockLength, int actingVersion) throws Exception {

		// Wrap the decoder to start reading from the provided buffer offset
		eiCreateTenderPayloadDecoder.wrap(directBuffer, bufferOffset, actingBlockLength, actingVersion);

		// Print the decoded message for debugging purposes
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

		// Execution Instructions Field
		VarStringEncodingDecoder executionInstructionsDecoder = eiCreateTenderPayloadDecoder.executionInstructions();
		String executionInstructions = null;

		long length = executionInstructionsDecoder.length();
		if (length != VarStringEncodingDecoder.lengthNullValue()) {
			int dataOffset = executionInstructionsDecoder.offset() + VarStringEncodingDecoder.varDataEncodingOffset();
			byte[] varData = new byte[(int) length];
			executionInstructionsDecoder.buffer().getBytes(dataOffset, varData);
			executionInstructions = new String(varData, StandardCharsets.UTF_8);
		}

		eiCreateTenderPayload.setExecutionInstructions(executionInstructions);

		// Decode marketId
		MarketIdType marketIdType = new MarketIdType();
		marketIdType.setMyUidId(eiCreateTenderPayloadDecoder.marketId());
		eiCreateTenderPayload.getTender().setMarketId(marketIdType);

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

		// Decode the 'tender' (EiTenderType)

		EiTenderType tender = new EiTenderType();
		// Get the 'tenderDetail' decoder
		TenderIntervalDetailDecoder tenderDetailDecoder = eiCreateTenderPayloadDecoder.tender().tenderBase()
				.tenderDetail();
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
		}
		
		// Decode (SideType)
		org.theenergymashuplab.cts.SideType side;
		if (eiCreateTenderPayloadDecoder.tender().tenderBase()
				.side() == org.theenergymashuplab.cts.generated_files.SideType.BUY) {
			side = org.theenergymashuplab.cts.SideType.BUY;
		} else if (eiCreateTenderPayloadDecoder.tender().tenderBase()
				.side() == org.theenergymashuplab.cts.generated_files.SideType.SELL) {
			side = org.theenergymashuplab.cts.SideType.SELL;
		} else {
			throw new IllegalArgumentException(
					"Unknown side type: " + eiCreateTenderPayloadDecoder.tender().tenderBase().side());
		}

		// Decode the expiration time (Instant) and set the tender expire time
		long expirationTimeSeconds = eiCreateTenderPayloadDecoder.tender().tenderBase().expirationTime().seconds();
		int expirationTimeNano = (int) eiCreateTenderPayloadDecoder.tender().tenderBase().expirationTime().nano();

		Instant expirationTime = Instant.ofEpochSecond(expirationTimeSeconds, expirationTimeNano);

		EiTenderType eiTenderType = new EiTenderType(expirationTime, side, tenderDetail, marketOrderId

		);

		// Decode 'AllOrNone' (Boolean)
		BooleanType allOrNone = eiCreateTenderPayloadDecoder.tender().tenderBase().allOrNone();
		eiTenderType.setAllOrNone(allOrNone == BooleanType.TRUE); // Set it as true if it is BooleanType.TRUE

		// Decode execution instructions
		VarStringEncodingDecoder executionInstructionsDecoderTender = eiCreateTenderPayloadDecoder
				.executionInstructions();
		String executionInstructionsTender = null;

		length = executionInstructionsDecoderTender.length();
		if (length != VarStringEncodingDecoder.lengthNullValue()) {
			int dataOffset = executionInstructionsDecoderTender.offset()
					+ VarStringEncodingDecoder.varDataEncodingOffset();
			byte[] varData = new byte[(int) length];
			executionInstructionsDecoderTender.buffer().getBytes(dataOffset, varData);
			executionInstructionsTender = new String(varData, StandardCharsets.UTF_8);
		}

		eiTenderType.setExecutionInstructions(executionInstructions); // Set the decoded execution instructions

		// Decode price scale (e.g., price scale could be a numeric value like an
		// integer or long)
		int priceScale = (int) eiCreateTenderPayloadDecoder.tender().tenderBase().priceScale();
		eiTenderType.setPriceScale(priceScale);

		// Decode quantity scale (e.g., quantity scale could be a numeric value)
		int quantityScale = (int) eiCreateTenderPayloadDecoder.tender().tenderBase().quantityScale();
		eiTenderType.setQuantityScale(quantityScale); // Set the decoded quantity scale
		
		org.theenergymashuplab.cts.generated_files.ResourceDesignatorType generatedResourceDesignator = eiCreateTenderPayloadDecoder.tender().tenderBase().resourceDesignator();
		org.theenergymashuplab.cts.ResourceDesignatorType resourceDesignator = null;

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

		// Now you can set the resourceDesignator of the tender
		eiTenderType.setResourceDesignator(resourceDesignator);

		// Decode the segment ID
		segmentId = (int) eiCreateTenderPayloadDecoder.tender().tenderBase().segmentId();
		eiTenderType.setSegmentId(segmentId); // Set the decoded segment ID

		// Decode the warrants value
		long decodedWarrantsValue = eiCreateTenderPayloadDecoder.tender().tenderBase().warrants();
		WarrantIdType warrants = new WarrantIdType(decodedWarrantsValue);
		eiTenderType.setWarrants(warrants);

		// Set the decoded fields in the payload object
		eiCreateTenderPayload.setCounterPartyId(counterPartyId);
		eiCreateTenderPayload.setPartyId(partyId);
		eiCreateTenderPayload.setRequestId(requestId); // Uncomment if requestId is needed
		eiCreateTenderPayload.setTender(eiTenderType);

		// Return the fully decoded payload
		return eiCreateTenderPayload;
	}

	/*
	 * public static int eiCreatedTenderEncode(EiCreatedTenderPayloadEncoder
	 * eiCreatedTenderPayloadEncoder, UnsafeBuffer directBuffer,
	 * MessageHeaderEncoder messageHeaderEncoder, EiCreatedTenderPayload
	 * eiCreatedTenderPayload) {
	 * 
	 * eiCreatedTenderPayloadEncoder.wrapAndApplyHeader(directBuffer, 0,
	 * messageHeaderEncoder);
	 * 
	 * eiCreatedTenderPayloadEncoder.counterPartyId()
	 * .value(eiCreatedTenderPayload.getCounterPartyId().getMyUidId());
	 * 
	 * 
	 * eiCreatedTenderPayloadEncoder.partyId()
	 * .value(eiCreatedTenderPayload.getPartyId().getMyUidId());
	 * 
	 * 
	 * eiCreatedTenderPayloadEncoder.refId()
	 * .value(eiCreatedTenderPayload.getRefId().getMyUidId());
	 * 
	 * eiCreatedTenderPayloadEncoder.tenderId()
	 * .value(eiCreatedTenderPayload.getTenderId().getMyUidId());
	 * 
	 * eiCreatedTenderPayloadEncoder.response() .responseCode(200)
	 * .responseDescription();
	 * 
	 * System.out.println(""); System.out.println(
	 * "-------------------------------------------------------------------------");
	 * System.out.println("EiCreatedTenderPayload Encoded :-");
	 * System.out.println(eiCreatedTenderPayloadEncoder.toString());
	 * 
	 * return messageHeaderEncoder.ENCODED_LENGTH +
	 * eiCreatedTenderPayloadEncoder.encodedLength();
	 * 
	 * }
	 * 
	 * 
	 * public static EiCreatedTenderPayload eiCreatedTenderPayloadDecoder(
	 * EiCreatedTenderPayloadDecoder eiCreatedTenderPayloadDecoder , UnsafeBuffer
	 * directBuffer, int bufferOffset, int actingBlockLength, int actingVersion)
	 * throws Exception{
	 * 
	 * eiCreatedTenderPayloadDecoder.wrap(directBuffer, 0, actingBlockLength,
	 * actingVersion);
	 * 
	 * System.out.println(""); System.out.println(
	 * "-------------------------------------------------------------------------");
	 * System.out.println("EiCreatedTenderPayload Decoded :-");
	 * System.out.println(eiCreatedTenderPayloadDecoder.toString());
	 * 
	 * EiCreatedTenderPayload eiCreatedTenderPayload = new EiCreatedTenderPayload();
	 * 
	 * ActorIdType counterPartyId = new ActorIdType();
	 * counterPartyId.setMyUidId(eiCreatedTenderPayloadDecoder.counterPartyId().
	 * value()); eiCreatedTenderPayload.setCounterPartyId(counterPartyId);
	 * 
	 * ActorIdType partyId = new ActorIdType();
	 * partyId.setMyUidId(eiCreatedTenderPayloadDecoder.partyId().value());
	 * eiCreatedTenderPayload.setPartyId(partyId);
	 * 
	 * EiResponse response = new EiResponse();
	 * response.setResponseCode(eiCreatedTenderPayloadDecoder.response().
	 * responseCode()); response.setResponseDescription("Successfull");
	 * eiCreatedTenderPayload.setResponse(response);
	 * 
	 * TenderIdType tenderId = new TenderIdType();
	 * tenderId.setMyUidId(eiCreatedTenderPayloadDecoder.tenderId().value());
	 * eiCreatedTenderPayload.setTenderId(tenderId);
	 * 
	 * return eiCreatedTenderPayload;
	 * 
	 * }
	 */
}
