package org.theenergymashuplab.cts.sbe;

import java.nio.charset.StandardCharsets;
import java.time.Instant;

import org.agrona.MutableDirectBuffer;
import org.agrona.concurrent.UnsafeBuffer;
import org.theenergymashuplab.cts.*;
import org.theenergymashuplab.cts.controller.payloads.EiCreatedTenderPayload;
import org.theenergymashuplab.cts.generated_files.*;

public class EiCreatedTenderPayloadEncoderDecoder {

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
    EiResponseTypeEncoder responseEncoder = encoder.response();

    Instant createdDateTime = payload.getResponse().getCreatedDateTime();
    responseEncoder.createdDateTime()
        .seconds(createdDateTime.getEpochSecond())
        .nano(createdDateTime.getNano());

    responseEncoder.inResponseTo(payload.getResponse().getInResponseTo().getMyUidId());
    //	inResponseTo of type RefID in WD32 UML
    
    responseEncoder.responseCode(payload.getResponse().getResponseCode());

    String description = payload.getResponse().getResponseDescription();
    byte[] descBytes = description.getBytes(StandardCharsets.UTF_8);
    MutableDirectBuffer descBuffer = new UnsafeBuffer(descBytes);

 // First write the length
    responseEncoder.responseDescription().length(descBytes.length);
 // Write the UTF-8 bytes directly
    responseEncoder.responseDescription().buffer().putBytes(
        responseEncoder.responseDescription().offset() + 4, 
        descBuffer, 
        0, 
        descBytes.length
    );
    
    // Encode enum: ResponseDetailType
    org.theenergymashuplab.cts.generated_files.ResponseDetailType encodedEnum;
    switch (payload.getResponse().getResponseDetail()) {
    case UNSPECIFIED:
        encodedEnum = org.theenergymashuplab.cts.generated_files.ResponseDetailType.UNSPECIFIED;
        break;
    case RULES_VIOLATION:
        encodedEnum = org.theenergymashuplab.cts.generated_files.ResponseDetailType.RULES_VIOLATION;
        break;
    case INVALID_REFERENCE:
        encodedEnum = org.theenergymashuplab.cts.generated_files.ResponseDetailType.INVALID_REFERENCE;
        break;
    case DUPLICATE:
        encodedEnum = org.theenergymashuplab.cts.generated_files.ResponseDetailType.DUPLICATE;
        break;
    case TRADING_CLOSED:
        encodedEnum = org.theenergymashuplab.cts.generated_files.ResponseDetailType.TRADING_CLOSED;
        break;
    case PARTY_RESTRICTED:
        encodedEnum = org.theenergymashuplab.cts.generated_files.ResponseDetailType.PARTY_RESTRICTED;
        break;
    case INVALID_INSTRUMENT:
        encodedEnum = org.theenergymashuplab.cts.generated_files.ResponseDetailType.INVALID_INSTRUMENT;
        break;
    case FORCE_MAJEURE:
        encodedEnum = org.theenergymashuplab.cts.generated_files.ResponseDetailType.FORCE_MAJEURE;
        break;
    case INVALID_MARKET:
        encodedEnum = org.theenergymashuplab.cts.generated_files.ResponseDetailType.INVALID_MARKET;
        break;
    case INVALID_SEGMENT:
        encodedEnum = org.theenergymashuplab.cts.generated_files.ResponseDetailType.INVALID_SEGMENT;
        break;
    case SUCCESS:
        encodedEnum = org.theenergymashuplab.cts.generated_files.ResponseDetailType.SUCCESS;
        break;
    case NOT_AUTHORIZED:
        encodedEnum = org.theenergymashuplab.cts.generated_files.ResponseDetailType.NOT_AUTHORIZED;
        break;
    case INVALID_ARTIFACT:
        encodedEnum = org.theenergymashuplab.cts.generated_files.ResponseDetailType.INVALID_ARTIFACT;
        break;
    default: 
        encodedEnum = org.theenergymashuplab.cts.generated_files.ResponseDetailType.UNSPECIFIED; 
        break;
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
		//System.out.println(eiCreatedTenderPayloadDecoder.toString());

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
		EiResponseType eiResponse = new EiResponseType();
		EiResponseTypeDecoder responseDecoder = eiCreatedTenderPayloadDecoder.response();

		// Decode createdDateTime
		long seconds = responseDecoder.createdDateTime().seconds();
		int nanos = (int) responseDecoder.createdDateTime().nano();
		eiResponse.setCreatedDateTime(Instant.ofEpochSecond(seconds, nanos));

		// Decode inResponseTo
		RefIdType responseInResponseTo = new RefIdType();
		responseInResponseTo.setMyUidId(responseDecoder.inResponseTo());
		eiResponse.setInResponseTo(responseInResponseTo);

		// Decode responseCode
		eiResponse.setResponseCode(responseDecoder.responseCode());

		// Decode responseDescription
		// Assuming responseDescription is unused and set as empty string ""
		eiResponse.setResponseDescription("");
//		// Decode responseDescription
//		String responseDescription = responseDecoder.responseDescription();
//		eiResponse.setResponseDescription(responseDescription);

		// Decode responseDetail Enum using a switch-case
		org.theenergymashuplab.cts.generated_files.ResponseDetailType decodedEnum = responseDecoder.responseDetail();
		org.theenergymashuplab.cts.ResponseDetailType responseDetail;

		switch (decodedEnum) {
		    case UNSPECIFIED:
		        responseDetail = org.theenergymashuplab.cts.ResponseDetailType.UNSPECIFIED;
		        break;
		    case RULES_VIOLATION:
		        responseDetail = org.theenergymashuplab.cts.ResponseDetailType.RULES_VIOLATION;
		        break;
		    case INVALID_REFERENCE:
		        responseDetail = org.theenergymashuplab.cts.ResponseDetailType.INVALID_REFERENCE;
		        break;
		    case DUPLICATE:
		        responseDetail = org.theenergymashuplab.cts.ResponseDetailType.DUPLICATE;
		        break;
		    case TRADING_CLOSED:
		        responseDetail = org.theenergymashuplab.cts.ResponseDetailType.TRADING_CLOSED;
		        break;
		    case PARTY_RESTRICTED:
		        responseDetail = org.theenergymashuplab.cts.ResponseDetailType.PARTY_RESTRICTED;
		        break;
		    case INVALID_INSTRUMENT:
		        responseDetail = org.theenergymashuplab.cts.ResponseDetailType.INVALID_INSTRUMENT;
		        break;
		    case FORCE_MAJEURE:
		        responseDetail = org.theenergymashuplab.cts.ResponseDetailType.FORCE_MAJEURE;
		        break;
		    case INVALID_MARKET:
		        responseDetail = org.theenergymashuplab.cts.ResponseDetailType.INVALID_MARKET;
		        break;
		    case INVALID_SEGMENT:
		        responseDetail = org.theenergymashuplab.cts.ResponseDetailType.INVALID_SEGMENT;
		        break;
		    case SUCCESS:
		        responseDetail = org.theenergymashuplab.cts.ResponseDetailType.SUCCESS;
		        break;
		    case NOT_AUTHORIZED:
		        responseDetail = org.theenergymashuplab.cts.ResponseDetailType.NOT_AUTHORIZED;
		        break;
		    case INVALID_ARTIFACT:
		        responseDetail = org.theenergymashuplab.cts.ResponseDetailType.INVALID_ARTIFACT;
		        break;
		    default:
		        responseDetail = org.theenergymashuplab.cts.ResponseDetailType.UNSPECIFIED;
		        break;
		}

		// Set it
		eiResponse.setResponseDetail(responseDetail);

		// Set the decoded response
		eiCreatedTenderPayload.setResponse(eiResponse);

		// Return the fully decoded payload
		return eiCreatedTenderPayload;
	}
}
