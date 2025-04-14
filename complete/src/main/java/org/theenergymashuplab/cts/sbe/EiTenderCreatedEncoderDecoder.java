package org.theenergymashuplab.cts.sbe;

import java.nio.charset.StandardCharsets;
import java.time.Instant;

import org.agrona.MutableDirectBuffer;
import org.agrona.concurrent.UnsafeBuffer;
import org.theenergymashuplab.cts.*;
import org.theenergymashuplab.cts.controller.payloads.EiCreatedTenderPayload;
import org.theenergymashuplab.cts.generated_files.*;

public class EiTenderCreatedEncoderDecoder {

	/*****************************************************************************************************
	 * TO-DO: ENCODER FUNCTION
	 * 
	 * Function Purpose: 
	 * Takes in an EiCreatedTenderPayload (Java Object) that has all the data populated 
	 * and writes it into a ByteBuffer using the SBE Encoder generated classes.
	 * 
	 * Field-by-field encoding breakdown:
	 *
	 * Top-Level Fields (Flat Encoding):
	 * ----------------------------------
	 * counterPartyId (ActorIdType -> long)
	 *   - Unique ID of the counterparty (the other party in the transaction)
	 *   - Encoder Function: eiCreatedTenderPayloadEncoder.counterPartyId(eiCreatedTenderPayload.getCounterPartyId().getMyUidId());
	 * 
	 * inResponseTo (RefIdType -> long)
	 *   - The ID of the request this message is responding to.
	 *   - Encoder Function: eiCreatedTenderPayloadEncoder.inResponseTo(eiCreatedTenderPayload.getInResponseTo().getMyUidId());
	 * 
	 * marketOrderId (MarketOrderIdType -> long)
	 *   - Market Order ID associated with this tender.
	 *   - Encoder Function: eiCreatedTenderPayloadEncoder.marketOrderId(eiCreatedTenderPayload.getMarketOrderId().getMyUidId());
	 * 
	 * partyId (ActorIdType -> long)
	 *   - The party sending this payload.
	 *   - Encoder Function: eiCreatedTenderPayloadEncoder.partyId(eiCreatedTenderPayload.getPartyId().getMyUidId());
	 * 
	 * tenderId (TenderIdType -> long)
	 *   - The ID of the tender being created.
	 *   - Encoder Function: eiCreatedTenderPayloadEncoder.tenderId(eiCreatedTenderPayload.getTenderId().getMyUidId());
	 * 
	 * Nested Field Encoding:
	 * -----------------------
	 * response (EiResponseType - Composite)
	 *   - Encodes another set of fields nested within 'response'.
	 * 
	 * Response Fields to Encode:
	 *   - createdDateTime (InstantType -> seconds + nanos)
	 *   - inResponseTo (RefIdType -> long)
	 *   - responseCode (long)
	 *   - responseDescription (String - varStringEncoding)
	 *   - responseDetail (Enum ResponseDetailType)
	 ******************************************************************************************************/

	public static int eiCreatedTenderEncode(
        EiCreatedTenderPayloadEncoder encoder,
        UnsafeBuffer directBuffer,
        MessageHeaderEncoder headerEncoder,
        EiCreatedTenderPayload payload) {

    encoder.wrapAndApplyHeader(directBuffer, 0, headerEncoder);

    // Encode primitive long fields
    encoder.counterPartyId(payload.getCounterPartyId().getMyUidId());
    encoder.inResponseTo(payload.getInResponseTo().getMyUidId());
    encoder.marketOrderId(payload.getMarketOrderId().getMyUidId());
    encoder.partyId(payload.getPartyId().getMyUidId());

    // Encode composite: EiResponseType
    EiCreatedTenderPayloadEncoder.ResponseEncoder responseEncoder = encoder.response();

    Instant createdDateTime = payload.getResponse().getCreatedDateTime();
    responseEncoder.createdDateTime()
        .seconds(createdDateTime.getEpochSecond())
        .nano(createdDateTime.getNano());

    responseEncoder.inResponseTo(payload.getResponse().getInResponseTo().getMyUidId());
    responseEncoder.responseCode(payload.getResponse().getResponseCode());

    String description = payload.getResponse().getResponseDescription();
    MutableDirectBuffer descBuffer = new UnsafeBuffer(description.getBytes(StandardCharsets.UTF_8));
    responseEncoder.putResponseDescription(descBuffer, 0, descBuffer.capacity());

    // Encode enum: ResponseDetailType
    org.theenergymashuplab.cts.generated_files.ResponseDetailType encodedEnum;
    switch (payload.getResponse().getResponseDetail()) {
        case Success:
            encodedEnum = org.theenergymashuplab.cts.generated_files.ResponseDetailType.Success;
            break;
        case NotAuthorized:
            encodedEnum = org.theenergymashuplab.cts.generated_files.ResponseDetailType.NotAuthorized;
            break;
        // add all other cases...
        default:
            throw new IllegalArgumentException("Unhandled ResponseDetail: " + payload.getResponse().getResponseDetail());
    }

    responseEncoder.responseDetail(encodedEnum);

    // Encode tenderId
    encoder.tenderId(payload.getTenderId().getMyUidId());

    // Debug print
    System.out.println("-------------------------------------------------------------------------");
    System.out.println("EiCreatedTenderPayload Encoded:");
    System.out.println(encoder.toString());

    return headerEncoder.encodedLength() + encoder.encodedLength();
}

	/*******************************************************************************************************
	 * TO-DO: DECODER FUNCTION
	 * 
	 * Function Purpose: 
	 * Takes a ByteBuffer that contains an SBE-encoded EiCreatedTenderPayload 
	 * and reads each field from it to rebuild the Java object.
	 * 
	 * Decoding Procedure:
	 * - Wrap the decoder around the buffer
	 * - Read all top-level fields
	 * - Read the nested response composite (Instant, inResponseTo, responseCode, description, responseDetail)
	 ********************************************************************************************************/

	public static EiCreatedTenderPayload eiCreatedTenderPayloadDecode(
			EiCreatedTenderPayloadDecoder eiCreatedTenderPayloadDecoder,
			UnsafeBuffer directBuffer,
			int bufferOffset,
			int actingBlockLength,
			int actingVersion) throws Exception {

		// Wrap decoder to start reading from the provided buffer
		eiCreatedTenderPayloadDecoder.wrap(directBuffer, bufferOffset, actingBlockLength, actingVersion);

		System.out.println("");
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("EiCreatedTenderPayload Decoded :-");
		System.out.println(eiCreatedTenderPayloadDecoder.toString());

		// Instantiate the payload to return
		EiCreatedTenderPayload eiCreatedTenderPayload = new EiCreatedTenderPayload();

		// DECODE FLAT FIELDS
		// Counter Party ID
		ActorIdType counterPartyId = new ActorIdType();
		counterPartyId.setMyUidId(eiCreatedTenderPayloadDecoder.counterPartyId());
		eiCreatedTenderPayload.setCounterPartyId(counterPartyId);

		// In Response To
		RefIdType inResponseTo = new RefIdType();
		inResponseTo.setMyUidId(eiCreatedTenderPayloadDecoder.inResponseTo());
		eiCreatedTenderPayload.setInResponseTo(inResponseTo);

		// Market Order ID
		MarketOrderIdType marketOrderId = new MarketOrderIdType();
		marketOrderId.setMyUidId(eiCreatedTenderPayloadDecoder.marketOrderId());
		eiCreatedTenderPayload.setMarketOrderId(marketOrderId);

		// Party ID
		ActorIdType partyId = new ActorIdType();
		partyId.setMyUidId(eiCreatedTenderPayloadDecoder.partyId());
		eiCreatedTenderPayload.setPartyId(partyId);

		// Tender ID
		TenderIdType tenderId = new TenderIdType();
		tenderId.setMyUidId(eiCreatedTenderPayloadDecoder.tenderId());
		eiCreatedTenderPayload.setTenderId(tenderId);

		// DECODE NESTED RESPONSE COMPOSITE
		EiResponse eiResponse = new EiResponse();
		EiResponseTypeDecoder responseDecoder = eiCreatedTenderPayloadDecoder.response();

		// Decode createdDateTime
		long seconds = responseDecoder.createdDateTime().seconds();
		int nanos = (int) responseDecoder.createdDateTime().nano();
		eiResponse.setCreatedDateTime(Instant.ofEpochSecond(seconds, nanos));

		// Decode inResponseTo
		RefIdType responseInResponseTo = new RefIdType();
		responseInResponseTo.setMyUidId(responseDecoder.inResponseTo());
		eiResponse.setRefId(responseInResponseTo);

		// Decode responseCode
		eiResponse.setResponseCode(responseDecoder.responseCode());

		// Decode responseDescription
		// Currently not implementing varStringEncoding decoding logic
		// Assuming responseDescription is unused and set as empty string ""
		eiResponse.setResponseDescription("");
//		// Decode responseDescription
//		String responseDescription = responseDecoder.responseDescription();
//		eiResponse.setResponseDescription(responseDescription);

		// Decode responseDetail Enum
		eiResponse.setResponseDetail(org.theenergymashuplab.cts.ResponseDetailType.valueOf(responseDecoder.responseDetail().name()));

		// Set the decoded response
		eiCreatedTenderPayload.setResponse(eiResponse);

		// Return the fully decoded payload
		return eiCreatedTenderPayload;
	}
}
