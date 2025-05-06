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
import org.theenergymashuplab.cts.SubscriptionActionType;
import org.theenergymashuplab.cts.TickerType;
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

		eiManageTickerSubscriptionEncoder.tickerType(org.theenergymashuplab.cts.generated_files.TickerType.get(eiManageTickerSubscriptionPayload.getTickerType().getValue()));

		eiManageTickerSubscriptionEncoder.marketId(eiManageTickerSubscriptionPayload.getMarketId().getMyUidId());
		// segment id should be short
		eiManageTickerSubscriptionEncoder.segmentId((short) eiManageTickerSubscriptionPayload.getSegmentId());

		eiManageTickerSubscriptionEncoder.subscriptionActionRequested(org.theenergymashuplab.cts.generated_files.SubscriptionActionType.get(eiManageTickerSubscriptionPayload.getSubscriptionActionRequested().getValue()));

		eiManageTickerSubscriptionEncoder.subscriptionRequestId(eiManageTickerSubscriptionPayload.getSubscriptionRequestId().getMyUidId());

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
				SubscriptionActionType.fromSbe(
						eiManageTickerSubscriptionPayloadDecoder.subscriptionActionRequested().value()),
				subscriptionRequestId,
				TickerType.fromSbe(eiManageTickerSubscriptionPayloadDecoder.tickerType().value()),
				// the decoder does not have partyId
				null);

		return eiManageTickerSubscriptionPayload;

	}

}
