package org.theenergymashuplab.cts.sbe;

import org.agrona.concurrent.UnsafeBuffer;
import org.theenergymashuplab.cts.controller.payloads.EiCreatedTransactionPayload;
import org.theenergymashuplab.cts.controller.payloads.EiManageTickerSubscriptionPayload;
import org.theenergymashuplab.cts.generated_files.EiCreatedTransactionPayloadEncoder;
import org.theenergymashuplab.cts.generated_files.MessageHeaderEncoder;

public class EiManageTickerSubscriptionPayloadEncoderDecoder {


	public static int eiManageTickerSubscriptionEncode(
			EiManageTickerSubscriptionPayloadEncoder eiManageTickerSubscriptionPayloadEncoder,
			UnsafeBuffer directBuffer,
			MessageHeaderEncoder messageHeaderEncoder,
			EiManageTickerSubscriptionPayload eiManageTickerSubscriptionPayload) {}

}
