/*
 * Copyright 2019-2025 The Energy Mashup Lab
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */

package org.theenergymashuplab.cts.sbe;

import org.agrona.concurrent.UnsafeBuffer;
import org.theenergymashuplab.cts.MarketIdType;
import org.theenergymashuplab.cts.RefIdType;
import org.theenergymashuplab.cts.controller.payloads.EiManageTickerSubscriptionPayload;
import org.theenergymashuplab.cts.generated_files.EiManageTickerSubscriptionPayloadDecoder;
import org.theenergymashuplab.cts.generated_files.EiManageTickerSubscriptionPayloadEncoder;
import org.theenergymashuplab.cts.generated_files.MessageHeaderEncoder;

public class EiManageTickerSubscriptionPayloadEncoderDecoder {

	public static int EiManageTickerSubscriptionEncode(
			EiManageTickerSubscriptionPayloadEncoder eiManageTickerSubscriptionEncoder,
			UnsafeBuffer directBuffer,
			MessageHeaderEncoder messageHeaderEncoder,
			EiManageTickerSubscriptionPayload eiManageTickerSubscriptionPayload) {

		eiManageTickerSubscriptionEncoder.wrapAndApplyHeader(directBuffer, 0, messageHeaderEncoder);

		eiManageTickerSubscriptionEncoder
				.tickerType(ConvertTickerType(eiManageTickerSubscriptionPayload.getTickerType()));

		eiManageTickerSubscriptionEncoder.marketId(eiManageTickerSubscriptionPayload.getMarketId().getMyUidId());
		// segment id should be short
		eiManageTickerSubscriptionEncoder.segmentId((short) eiManageTickerSubscriptionPayload.getSegmentId());

		eiManageTickerSubscriptionEncoder.subscriptionActionRequested(
				ConvertSubscriptionActionType(eiManageTickerSubscriptionPayload.getSubscriptionActionRequested()));
		eiManageTickerSubscriptionEncoder
				.subscriptionRequestId(eiManageTickerSubscriptionPayload.getSubscriptionRequestId().getMyUidId());

		System.out.println("\n-------------------------------------------------------------------------");
		System.out.println("EiManageTickerSubscriptionEncode Encoded :-");
		System.out.println(eiManageTickerSubscriptionEncoder.toString());

		return messageHeaderEncoder.ENCODED_LENGTH + eiManageTickerSubscriptionEncoder.encodedLength();

	}

	public static EiManageTickerSubscriptionPayload EiManageTickerSubscriptionPayloadDecode(
			EiManageTickerSubscriptionPayloadDecoder eiManageTickerSubscriptionPayloadDecoder,
			UnsafeBuffer directBuffer,
			int bufferOffset,
			int actingBlockLength, int actingVersion) throws Exception {

		eiManageTickerSubscriptionPayloadDecoder.wrap(directBuffer, bufferOffset, actingBlockLength, actingVersion);

		System.out.println("\n-------------------------------------------------------------------------");
		System.out.println("eiManageTickerSubscriptionPayloadDecode Decoded :-");
		System.out.println(eiManageTickerSubscriptionPayloadDecoder.toString());

		MarketIdType marketId = new MarketIdType();
		RefIdType subscriptionRequestId = new RefIdType();

		subscriptionRequestId.setMyUidId(eiManageTickerSubscriptionPayloadDecoder.subscriptionRequestId());
		marketId.setMyUidId(eiManageTickerSubscriptionPayloadDecoder.marketId());

		EiManageTickerSubscriptionPayload eiManageTickerSubscriptionPayload = new EiManageTickerSubscriptionPayload(
				marketId,
				eiManageTickerSubscriptionPayloadDecoder.segmentId(),
				ConvertSubscriptionActionType(eiManageTickerSubscriptionPayloadDecoder.subscriptionActionRequested()),
				subscriptionRequestId,
				ConvertTickerType(eiManageTickerSubscriptionPayloadDecoder.tickerType()),
				// the decoder does not have partyId
				null);

		return eiManageTickerSubscriptionPayload;

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
