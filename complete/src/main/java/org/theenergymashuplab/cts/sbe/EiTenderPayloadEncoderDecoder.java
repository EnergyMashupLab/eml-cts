package org.theenergymashuplab.cts.sbe;

import org.agrona.concurrent.UnsafeBuffer;
import org.theenergymashuplab.cts.*;
import org.theenergymashuplab.cts.controller.payloads.EiCreateTenderPayload;
import org.theenergymashuplab.cts.controller.payloads.EiCreatedTenderPayload;
import org.theenergymashuplab.cts.generated_files.*;
import org.theenergymashuplab.cts.sbe.CompositeEncoderDecoder.EiTenderTypeEncoderDecoder;

public class EiTenderPayloadEncoderDecoder {

	// ENCODER
	public static int eiCreateTenderEncode(EiCreateTenderPayloadEncoder encoder, UnsafeBuffer buffer,
			MessageHeaderEncoder header, EiCreateTenderPayload payload) {

		encoder.wrapAndApplyHeader(buffer, 0, header);

		encoder.atMostOne(payload.isAtMostOne() ? BooleanType.TRUE : BooleanType.FALSE)
				.marketId(payload.getMarketId().getMyUidId()).counterPartyId(payload.getCounterPartyId().getMyUidId())
				.executionInstructions(0L) // unused
				.partyId(payload.getPartyId().getMyUidId()).requestId(payload.getRequestId().getMyUidId())
				.segmentId(payload.getSegmentId());

		EiTenderTypeEncoderDecoder.encode(encoder.tender(), payload.getTender());

//		System.out.println("\nEiCreateTenderPayload Encoded :-");
//		System.out.println(encoder.toString() + "\n");

		// SBE DEMO
//	    inspectEncodedBuffer(buffer, header.encodedLength() + encoder.encodedLength(), payload);
		return header.encodedLength() + encoder.encodedLength();

	}

	// DECODER
	public static EiCreateTenderPayload eiCreateTenderDecode(EiCreateTenderPayloadDecoder decoder, UnsafeBuffer buffer,
			int offset, int actingBlockLength, int actingVersion) throws Exception {

		decoder.wrap(buffer, offset, actingBlockLength, actingVersion);

//		System.out.println("\nEiCreateTenderPayload Decoded :-");
//		System.out.println(eiCreateTenderPayloadDecoder.toString() + "\n");
		EiTenderTypeDecoder tender = decoder.tender();
		TenderBaseDecoder base = tender.tenderBase();

		EiCreateTenderPayload eiCreateTenderPayload = new EiCreateTenderPayload();

		BooleanType atMostOne = decoder.atMostOne();
		eiCreateTenderPayload.setAtMostOne(atMostOne == BooleanType.TRUE);

		ActorIdType counterPartyId = new ActorIdType();
		counterPartyId.setMyUidId(decoder.counterPartyId());
		eiCreateTenderPayload.setCounterPartyId(counterPartyId);

		// not currently used, set to empty
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

		EiTenderType eiTenderType = EiTenderTypeEncoderDecoder.decode(decoder.tender());
		eiCreateTenderPayload.setTender(eiTenderType);

		WarrantIdType warrants = new WarrantIdType(base.warrants());
		eiTenderType.setWarrants(warrants);

		// Set the decoded fields back in the payload object
		eiCreateTenderPayload.setTender(eiTenderType);

		return eiCreateTenderPayload;
	}

	public static int eiCreatedTenderEncode(EiCreatedTenderPayloadEncoder encoder, UnsafeBuffer buffer,
	        MessageHeaderEncoder header, EiCreatedTenderPayload payload) {

	    encoder.wrapAndApplyHeader(buffer, 0, header);

	    encoder.counterPartyId(payload.getCounterPartyId().getMyUidId());
	    encoder.inResponseTo(payload.getInResponseTo().getMyUidId());
	    encoder.marketOrderId(payload.getMarketOrderId().getMyUidId());
	    encoder.partyId(payload.getPartyId().getMyUidId());
	    encoder.tenderId(payload.getTenderId().getMyUidId());

	    CompositeEncoderDecoder.EiResponseTypeEncoderDecoder.encode(encoder.response(), payload.getResponse());

	    encoder.responseDescription(payload.getResponse().getResponseDescription());
//	    System.out.println("\nEiCreatedTenderPayload Encoded:");
//	    System.out.println(encoder.toString() + "\n");

	    return header.encodedLength() + encoder.encodedLength();
	}

	public static EiCreatedTenderPayload eiCreatedTenderPayloadDecode(EiCreatedTenderPayloadDecoder decoder,
			UnsafeBuffer buffer, int offset, int actingBlockLength, int actingVersion) throws Exception {
		decoder.wrap(buffer, offset, actingBlockLength, actingVersion);

//			System.out.println("\nEiCreatedTenderPayload Decoded :-");
//			System.out.println(eiCreatedTenderPayloadDecoder.toString() + "\n");

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

		EiResponseType response = CompositeEncoderDecoder.EiResponseTypeEncoderDecoder.decode(decoder.response());
		response.setResponseDescription(decoder.responseDescription());

		eiCreatedTenderPayload.setResponse(response);

		return eiCreatedTenderPayload;
	}

	public static void inspectEncodedBuffer(UnsafeBuffer buffer, int totalLength, EiCreateTenderPayload payload) {
		int bytes = 0;

		System.out.println("\n-------------------------------------------------------------------------");
		System.out.println("Payload Object:");
		System.out.println(payload);

		System.out.printf("\n%-32s | %-17s | %-55s | %-25s%n", "Field", "Bytes Range", "Bytes", "Value");
		System.out.println("-".repeat(140));

		String[] fieldLabels = new String[] { "Message Header", "atMostOne", "counterPartyId", "executionInstructions",
				"marketId", "partyId", "requestId", "segmentId", "tender.marketOrderId", "tender.tenderId",
				"tender.referencedQuoteId", "tenderBase.allOrNone", "tenderBase.executionInstructions",
				"tenderBase.expirationTime", "tenderBase.marketId", "tenderBase.priceScale", "tenderBase.quantityScale",
				"tenderBase.resourceDesignator", "tenderBase.segmentId", "tenderBase.side", "interval.duration",
				"interval.dtStart", "tenderDetail.price", "tenderDetail.quantity", "tenderBase.warrants" };

		int[] fieldSizes = new int[] { 8, 1, 8, 8, 8, 8, 8, 4, 8, 8, 8, 1, 8, 12, 8, 4, 4, 1, 4, 1, 12, 12, 8, 8, 8 };

		for (int i = 0; i < fieldLabels.length && bytes < totalLength; i++) {
			int size = fieldSizes[i];
			int end = bytes + size;

			if (end > totalLength) {
				System.out.printf("%-32s | Bytes %3d - ???     | [field exceeds buffer length]%n", fieldLabels[i],
						bytes);
				break;
			}

			StringBuilder hexBytes = new StringBuilder();
			long value = 0;

			for (int j = bytes; j < end; j++) {
				byte b = buffer.getByte(j);
				hexBytes.append(String.format("%02X ", b));
				value |= ((long) (b & 0xFF)) << ((j - bytes) * 8);
			}

			String display;
			if (size == 1) {
				if (fieldLabels[i].equals("tenderBase.side")) {
					char c = (char) value;
					display = "'" + c + "' (" + switch (c) {
					case 'B' -> "BUY";
					case 'S' -> "SELL";
					default -> "UNKNOWN";
					} + ")";
				} else {
					display = switch ((int) value) {
					case 0 -> "FALSE";
					case 1 -> "TRUE";
					default -> String.valueOf(value);
					};
				}
			} else if (size == 4) {
				display = String.valueOf((int) value);
			} else if (size == 8) {
				display = String.valueOf(value);
			} else if (size == 12) {
				long seconds = 0;
				int nanos = 0;
				for (int j = 0; j < 8; j++) {
					seconds |= ((long) buffer.getByte(bytes + j) & 0xFF) << (j * 8);
				}
				for (int j = 0; j < 4; j++) {
					nanos |= (buffer.getByte(bytes + 8 + j) & 0xFF) << (j * 8);
				}
				display = seconds + "s " + nanos + "ns";
			} else {
				display = String.valueOf(value);
			}

			System.out.printf("%-32s | Bytes %3d - %3d | %-55s | %-25s%n", fieldLabels[i], bytes, end - 1,
					hexBytes.toString().trim(), display);

			bytes = end;
		}

		if (bytes < totalLength) {
			System.out.printf("%-32s | Bytes %3d - %3d | Bytes: ", "Remaining Bytes", bytes, totalLength - 1);
			for (int j = bytes; j < totalLength; j++) {
				System.out.printf("%02X ", buffer.getByte(j));
			}
			System.out.println();
		}

		System.out.println("\n-------------------------------------------------------------------------");
		System.out.println("Full Raw Buffer:");
		for (int i = 0; i < totalLength; i++) {
			System.out.printf("%02X", buffer.getByte(i));
		}
		System.out.println("\n-------------------------------------------------------------------------");
	}

}
