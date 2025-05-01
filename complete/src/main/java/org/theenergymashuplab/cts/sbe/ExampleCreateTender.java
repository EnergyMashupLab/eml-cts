package org.theenergymashuplab.cts.sbe;

import org.agrona.concurrent.UnsafeBuffer;
import org.theenergymashuplab.cts.ActorIdType;
import org.theenergymashuplab.cts.EiResponseType;
import org.theenergymashuplab.cts.EiTenderType;
import org.theenergymashuplab.cts.Interval;
import org.theenergymashuplab.cts.MarketIdType;
import org.theenergymashuplab.cts.MarketOrderIdType;
import org.theenergymashuplab.cts.MarketTransactionIdType;
import org.theenergymashuplab.cts.RefIdType;
import org.theenergymashuplab.cts.ResourceDesignatorType;
import org.theenergymashuplab.cts.SideType;
import org.theenergymashuplab.cts.TenderIdType;
import org.theenergymashuplab.cts.TenderIntervalDetail;
import org.theenergymashuplab.cts.TransactionIdType;
import org.theenergymashuplab.cts.WarrantIdType;
import org.theenergymashuplab.cts.controller.payloads.EiCreateTenderPayload;
import org.theenergymashuplab.cts.controller.payloads.EiCreatedTenderPayload;
import org.theenergymashuplab.cts.controller.payloads.EiCreatedTransactionPayload;
import org.theenergymashuplab.cts.generated_files.EiCreateTenderPayloadDecoder;
import org.theenergymashuplab.cts.generated_files.EiCreateTenderPayloadEncoder;
import org.theenergymashuplab.cts.generated_files.EiCreatedTenderPayloadDecoder;
import org.theenergymashuplab.cts.generated_files.EiCreatedTenderPayloadEncoder;
import org.theenergymashuplab.cts.generated_files.EiCreatedTransactionPayloadDecoder;
import org.theenergymashuplab.cts.generated_files.EiCreatedTransactionPayloadEncoder;
import org.theenergymashuplab.cts.generated_files.MessageHeaderDecoder;
import org.theenergymashuplab.cts.generated_files.MessageHeaderEncoder;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.rmi.server.UID;
import java.time.Duration;
import java.time.Instant;

public class ExampleCreateTender {
    public static void main(String[] args) throws Exception {
        try {

    	EiCreateTenderPayloadEncoder eiCreateTenderPayloadEncoder = new EiCreateTenderPayloadEncoder();
    	MessageHeaderEncoder messageHeaderEncoder = new MessageHeaderEncoder();
    	UnsafeBuffer directBuffer = new UnsafeBuffer(ByteBuffer.allocate(512));
    	
    	EiCreateTenderPayload payload = new EiCreateTenderPayload();
    	
    	ActorIdType counterPartyId = new ActorIdType();
    	counterPartyId.setMyUidId(3001); //test cp id
    	
    	MarketIdType marketId = new MarketIdType();
    	marketId.setMyUidId(5001); // test market id
    	
    	ActorIdType partyId = new ActorIdType();
    	partyId.setMyUidId(7001); //test party id
    	
    	RefIdType requestId = new RefIdType();
    	requestId.setMyUidId(9001); // test request id
    	
    	payload.setAtMostOne(true);
    	payload.setCounterPartyId(counterPartyId);
    	payload.setExecutionInstructions("");
    	payload.setMarketId(marketId);
    	payload.setPartyId(partyId);
    	payload.setRequestId(requestId);
    	payload.setSegmentId(7777);
    	
    	//Payload is made, so now we need to make the EiTenderType
    	
        EiTenderType tender = new EiTenderType();
        
        MarketOrderIdType marketOrderId = new MarketOrderIdType();
        marketOrderId.setMarketOrderId(1001);
        
        TenderIdType tenderId = new TenderIdType();
        tenderId.setMyUidId(2001);
        
        Instant expirationTime = Instant.now().plusSeconds(3600);

        WarrantIdType warrant = new WarrantIdType();
        warrant.setMyUidId(1234);

        tender.setMarketOrderId(marketOrderId);
        tender.setReferencedQuoteId(marketOrderId);
        
        //ReferenceQuoteId is missing, it is of type MarketOrderIdType, but we
        //already have a MarketOrderIdType, and thus compiler throws a duplicate
        //field error. How to handle?
        
        tender.setAllOrNone(true);
        tender.setExecutionInstructions("");
        tender.setExpirationTime(expirationTime);
        tender.setMarketId(marketId);
        tender.setPriceScale(2);
        tender.setQuantityScale(3);
        tender.setResourceDesignator(ResourceDesignatorType.ENERGY);
        tender.setSegmentId(7777);
        tender.setSide(SideType.BUY);
        tender.setWarrants(warrant);
        
        Interval interval = new Interval();
        interval.setDtStart(Instant.now());
        interval.setDuration(Duration.ofDays(1));

        // Create TenderDetail with Interval
        TenderIntervalDetail tenderDetail = new TenderIntervalDetail(interval, 500, 100);
        tender.setTenderDetail(tenderDetail);
        payload.setTender(tender);
        
        int encodedLength = EiCreateTenderPayloadEncoderDecoder.eiCreateTenderEncode(eiCreateTenderPayloadEncoder, directBuffer, messageHeaderEncoder, payload);

        byte[] encodedBytes = new byte[encodedLength];
        directBuffer.getBytes(0, encodedBytes);
        System.out.println("Encoded Hex Dump:");
        printHexDump(directBuffer, encodedLength);
        //System.out.println(payload.toString());
        
        
        MessageHeaderDecoder messageHeaderDecoder = new MessageHeaderDecoder();
        EiCreateTenderPayloadDecoder eiCreateTenderPayloadDecoder = new EiCreateTenderPayloadDecoder();

        // Wrap the header first to determine the block length and version
        messageHeaderDecoder.wrap(directBuffer, 0);

        // Now decode the actual payload, starting after the header
        int headerLength = messageHeaderDecoder.encodedLength();
        int actingBlockLength = messageHeaderDecoder.blockLength();
        int actingVersion = messageHeaderDecoder.version();

        EiCreateTenderPayload decoded = EiCreateTenderPayloadEncoderDecoder.eiCreateTenderPayloadDecode(
            eiCreateTenderPayloadDecoder,
            directBuffer,
            headerLength,
            actingBlockLength,
            actingVersion
        );

        // Print the decoded payload for verification
        
        System.out.println("Decoded Payload:");
        System.out.println(decoded.toString());
    } catch (Exception e) {
        e.printStackTrace();
    }
        
        System.out.println("\n======================= START CREATED TENDER TEST =======================");

     // Set up buffer and encoders
     EiCreatedTenderPayloadEncoder eiCreatedTenderPayloadEncoder = new EiCreatedTenderPayloadEncoder();
     MessageHeaderEncoder tenderHeaderEncoder = new MessageHeaderEncoder();
     UnsafeBuffer tenderBuffer = new UnsafeBuffer(ByteBuffer.allocate(512));

     // Create the EiCreatedTenderPayload object manually
     EiCreatedTenderPayload tenderPayload = new EiCreatedTenderPayload();

     // Set simple fields
     ActorIdType counterPartyIdTender = new ActorIdType();
     counterPartyIdTender.setMyUidId(1001);
     tenderPayload.setCounterPartyId(counterPartyIdTender);

     RefIdType inResponseTo = new RefIdType();
     inResponseTo.setMyUidId(2002);
     tenderPayload.setInResponseTo(inResponseTo);

     MarketOrderIdType marketOrderId = new MarketOrderIdType();
     marketOrderId.setMyUidId(3003);
     tenderPayload.setMarketOrderId(marketOrderId);

     ActorIdType partyIdTender = new ActorIdType();
     partyIdTender.setMyUidId(4004);
     tenderPayload.setPartyId(partyIdTender);

     TenderIdType tenderId = new TenderIdType();
     tenderId.setMyUidId(5005);
     tenderPayload.setTenderId(tenderId);

     // Build nested EiResponseType
     EiResponseType tenderResponse = new EiResponseType();
     tenderResponse.setCreatedDateTime(Instant.now());

     RefIdType tenderResponseInResponseTo = new RefIdType();
     tenderResponseInResponseTo.setMyUidId(6006);
     tenderResponse.setInResponseTo(tenderResponseInResponseTo);

     tenderResponse.setResponseCode(200);
     tenderResponse.setResponseDescription("Tender created successfully");
     tenderResponse.setResponseDetail(org.theenergymashuplab.cts.ResponseDetailType.SUCCESS);

     tenderPayload.setResponse(tenderResponse);

	// Encode
     int encodedTenderLength = EiCreatedTenderPayloadEncoderDecoder.eiCreatedTenderEncode(
         eiCreatedTenderPayloadEncoder,
         tenderBuffer,
         tenderHeaderEncoder,
         tenderPayload
     );

     // Print encoded hex
     System.out.println("Encoded CreatedTender Hex Dump:");
     printHexDump(tenderBuffer, encodedTenderLength);

     // Decode
     MessageHeaderDecoder tenderHeaderDecoder = new MessageHeaderDecoder();
     EiCreatedTenderPayloadDecoder tenderPayloadDecoder = new EiCreatedTenderPayloadDecoder();

     tenderHeaderDecoder.wrap(tenderBuffer, 0);
     int tenderHeaderLength = tenderHeaderDecoder.encodedLength();
     int tenderActingBlockLength = tenderHeaderDecoder.blockLength();
     int tenderActingVersion = tenderHeaderDecoder.version();

     EiCreatedTenderPayload decodedTenderPayload = EiCreatedTenderPayloadEncoderDecoder.eiCreatedTenderPayloadDecode(
         tenderPayloadDecoder,
         tenderBuffer,
         tenderHeaderLength,
         tenderActingBlockLength,
         tenderActingVersion
     );
     

     // Print decoded payload
     System.out.println("\nDecoded CreatedTender Payload:");
     System.out.println(decodedTenderPayload.toString());
     System.out.println("======================= END CREATED TENDER TEST =======================");

        
     // Add this AFTER your Tender test inside ExampleCreateTender.java

        System.out.println("\n======================= START CREATE TRANSACTION TEST =======================");

        // Create encoders and buffer
        EiCreatedTransactionPayloadEncoder eiCreatedTransactionPayloadEncoder = new EiCreatedTransactionPayloadEncoder();
        MessageHeaderEncoder transactionHeaderEncoder = new MessageHeaderEncoder();
        UnsafeBuffer transactionBuffer = new UnsafeBuffer(ByteBuffer.allocate(512));

        // Build CreateTransaction payload manually
        EiCreatedTransactionPayload transactionPayload = new EiCreatedTransactionPayload();

        ActorIdType counterPartyIdTx = new ActorIdType();
        counterPartyIdTx.setMyUidId(1001);
        transactionPayload.setCounterPartyId(counterPartyIdTx);

        MarketTransactionIdType marketTransactionId = new MarketTransactionIdType();
        marketTransactionId.setMyUidId(2001);
        transactionPayload.setMarketTransactionId(marketTransactionId);

        ActorIdType partyIdTx = new ActorIdType();
        partyIdTx.setMyUidId(3001);
        transactionPayload.setPartyId(partyIdTx);

        TransactionIdType recipientTransactionId = new TransactionIdType();
        recipientTransactionId.setMyUidId(4001);
        transactionPayload.setRecipientTransactionId(recipientTransactionId);

        RefIdType refIdTx = new RefIdType();
        refIdTx.setMyUidId(5001);
        transactionPayload.setRefId(refIdTx);

        EiResponseType responseTx = new EiResponseType();
        responseTx.setCreatedDateTime(Instant.now());
        RefIdType inResponseToTx = new RefIdType();
        inResponseToTx.setMyUidId(6001);
        responseTx.setInResponseTo(inResponseToTx);
        responseTx.setResponseCode(200);
        responseTx.setResponseDescription("1234567890");
        responseTx.setResponseDetail(org.theenergymashuplab.cts.ResponseDetailType.SUCCESS);
        transactionPayload.setResponse(responseTx);

        TransactionIdType transactionIdTx = new TransactionIdType();
        transactionIdTx.setMyUidId(7001);
        transactionPayload.setTransactionId(transactionIdTx);

        // Encode CreateTransaction payload
        int transactionEncodedLength = EiCreatedTransactionPayloadEncoderDecoder.eiCreatedTransactionEncode(
            eiCreatedTransactionPayloadEncoder,
            transactionBuffer,
            transactionHeaderEncoder,
            transactionPayload
        );
        System.out.println(transactionPayload.toString());

        // Print encoded buffer
        System.out.println("Encoded CreateTransaction Hex Dump:");
        printHexDump(transactionBuffer, transactionEncodedLength);

        // Decode CreateTransaction payload
        MessageHeaderDecoder transactionHeaderDecoder = new MessageHeaderDecoder();
        EiCreatedTransactionPayloadDecoder transactionPayloadDecoder = new EiCreatedTransactionPayloadDecoder();

        transactionHeaderDecoder.wrap(transactionBuffer, 0);
        int transactionHeaderLength = transactionHeaderDecoder.encodedLength();
        int transactionActingBlockLength = transactionHeaderDecoder.blockLength();
        int transactionActingVersion = transactionHeaderDecoder.version();

        EiCreatedTransactionPayload decodedTransaction = EiCreatedTransactionPayloadEncoderDecoder.eiCreatedTransactionDecode(
            transactionPayloadDecoder,
            transactionBuffer,
            transactionHeaderLength,
            transactionActingBlockLength,
            transactionActingVersion
        );

        // Print decoded CreateTransaction
        System.out.println("\nDecoded CreateTransaction Payload:");
        System.out.println(decodedTransaction.toString());

        System.out.println("======================= END CREATE TRANSACTION TEST =======================");

    	
    }
    
    public static void printHexDump(UnsafeBuffer buffer, int length) {
        StringBuilder hexDump = new StringBuilder();
        for (int i = 0; i < length; i++) {
            if (i % 16 == 0) hexDump.append(String.format("%04x: ", i));
            hexDump.append(String.format("%02x ", buffer.getByte(i)));
            if ((i + 1) % 16 == 0) hexDump.append("\n");
        }
        System.out.println(hexDump);
    }
    
}