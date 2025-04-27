package org.theenergymashuplab.cts.sbe;

import org.agrona.concurrent.UnsafeBuffer;
import org.theenergymashuplab.cts.RefIdType;
import org.theenergymashuplab.cts.controller.payloads.EiManagedTickerSubscriptionPayload;
import org.theenergymashuplab.cts.generated_files.EiManagedTickerSubscriptionPayloadDecoder;
import org.theenergymashuplab.cts.generated_files.EiManagedTickerSubscriptionPayloadEncoder;
import org.theenergymashuplab.cts.generated_files.MessageHeaderEncoder;

public class EiManagedTickerSubscriptionPayloadEncoderDecoder {

	public static int EiManagedTickerSubscriptionEncode(
			EiManagedTickerSubscriptionPayloadEncoder eiManagedTickerSubscriptionEncoder,
			UnsafeBuffer directBuffer,
			MessageHeaderEncoder messageHeaderEncoder,
			EiManagedTickerSubscriptionPayload eiManagedTickerSubscriptionPayload) {

		eiManagedTickerSubscriptionEncoder.wrapAndApplyHeader(directBuffer, 0, messageHeaderEncoder);

		eiManagedTickerSubscriptionEncoder
				.tickerType(ConvertTickerType(eiManagedTickerSubscriptionPayload.getTickerType()));

		// TODO: fix
		eiManagedTickerSubscriptionEncoder.multicastListenReference();

		// eiManagedTickerSubscriptionEncoder.response((short)

		eiManagedTickerSubscriptionEncoder.subscriptionActionTaken(
				ConvertSubscriptionActionType(eiManagedTickerSubscriptionPayload.getSubscriptionActionTaken()));

		eiManagedTickerSubscriptionEncoder
				.subscriptionRequestId(eiManagedTickerSubscriptionPayload.getSubscriptionRequestId().getMyUidId());

		System.out.println("\n-------------------------------------------------------------------------");
		System.out.println("EiManagedTickerSubscriptionEncode Encoded :-");
		System.out.println(eiManagedTickerSubscriptionEncoder.toString());

		return messageHeaderEncoder.ENCODED_LENGTH + eiManagedTickerSubscriptionEncoder.encodedLength();

	}

	public static EiManagedTickerSubscriptionPayload EiManagedTickerSubscriptionPayloadDecode(
			EiManagedTickerSubscriptionPayloadDecoder eiManagedTickerSubscriptionPayloadDecoder,
			UnsafeBuffer directBuffer,
			int bufferOffset,
			int actingBlockLength, int actingVersion) throws Exception {

		eiManagedTickerSubscriptionPayloadDecoder.wrap(directBuffer, bufferOffset, actingBlockLength, actingVersion);

		System.out.println("\n-------------------------------------------------------------------------");
		System.out.println("eiManagedTickerSubscriptionPayloadDecode Decoded :-");
		System.out.println(eiManagedTickerSubscriptionPayloadDecoder.toString());

		RefIdType subscriptionRequestId = new RefIdType();

		subscriptionRequestId.setMyUidId(eiManagedTickerSubscriptionPayloadDecoder.subscriptionRequestId());

		EiManagedTickerSubscriptionPayload eiManagedTickerSubscriptionPayload = new EiManagedTickerSubscriptionPayload();

		eiManagedTickerSubscriptionPayload
				.setTickerType(ConvertTickerType(eiManagedTickerSubscriptionPayloadDecoder.tickerType()));

		// TODO: fix
		eiManagedTickerSubscriptionPayload.setMulticastListenReference(null);

		// eiManagedTickerSubscriptionPayload.setResponse(null);
		eiManagedTickerSubscriptionPayload.setSubscriptionActionTaken(
				ConvertSubscriptionActionType(eiManagedTickerSubscriptionPayloadDecoder.subscriptionActionTaken()));

		eiManagedTickerSubscriptionPayload.setSubscriptionRequestId(subscriptionRequestId);

		return eiManagedTickerSubscriptionPayload;

	}

	private static org.theenergymashuplab.cts.generated_files.TickerType ConvertTickerType(
			org.theenergymashuplab.cts.TickerType tickerType) {
		switch (tickerType) {
			case QUOTES:
				return org.theenergymashuplab.cts.generated_files.TickerType.QUOTES;
			case RFQS:
				return org.theenergymashuplab.cts.generated_files.TickerType.RFQS;
			case TENDERS:
				return org.theenergymashuplab.cts.generated_files.TickerType.TENDERS;
			case TRANSACTIONS:
				return org.theenergymashuplab.cts.generated_files.TickerType.TRANSACTIONS;
			default:
				return org.theenergymashuplab.cts.generated_files.TickerType.NULL_VAL;
		}
	}

	private static org.theenergymashuplab.cts.TickerType ConvertTickerType(
			org.theenergymashuplab.cts.generated_files.TickerType tickerType) {
		switch (tickerType) {
			case QUOTES:
				return org.theenergymashuplab.cts.TickerType.QUOTES;
			case RFQS:
				return org.theenergymashuplab.cts.TickerType.RFQS;
			case TENDERS:
				return org.theenergymashuplab.cts.TickerType.TENDERS;
			case TRANSACTIONS:
				return org.theenergymashuplab.cts.TickerType.TRANSACTIONS;
			default:
				return null;
		}
	}

	private static org.theenergymashuplab.cts.generated_files.SubscriptionActionType ConvertSubscriptionActionType(
			org.theenergymashuplab.cts.SubscriptionActionType subscriptionActionType) {
		switch (subscriptionActionType) {
			case SNAPSHOT:
				return org.theenergymashuplab.cts.generated_files.SubscriptionActionType.SNAPSHOT;
			case SNAPSHOT_AND_UPDATES:
				return org.theenergymashuplab.cts.generated_files.SubscriptionActionType.SNAPSHOT_AND_UPDATES;
			case CANCEL:
				return org.theenergymashuplab.cts.generated_files.SubscriptionActionType.CANCEL;
			default:
				return org.theenergymashuplab.cts.generated_files.SubscriptionActionType.NULL_VAL;
		}
	}

	private static org.theenergymashuplab.cts.SubscriptionActionType ConvertSubscriptionActionType(
			org.theenergymashuplab.cts.generated_files.SubscriptionActionType subscriptionActionType) {
		switch (subscriptionActionType) {
			case SNAPSHOT:
				return org.theenergymashuplab.cts.SubscriptionActionType.SNAPSHOT;
			case SNAPSHOT_AND_UPDATES:
				return org.theenergymashuplab.cts.SubscriptionActionType.SNAPSHOT_AND_UPDATES;
			case CANCEL:
				return org.theenergymashuplab.cts.SubscriptionActionType.CANCEL;
			default:
				return null;
		}
	}

}
