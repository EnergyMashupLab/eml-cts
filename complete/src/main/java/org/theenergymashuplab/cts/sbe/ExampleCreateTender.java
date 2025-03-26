package org.theenergymashuplab.cts.sbe;

import org.agrona.concurrent.UnsafeBuffer;
import org.theenergymashuplab.cts.ActorIdType;
import org.theenergymashuplab.cts.EiTenderType;
import org.theenergymashuplab.cts.Interval;
import org.theenergymashuplab.cts.MarketIdType;
import org.theenergymashuplab.cts.MarketOrderIdType;
import org.theenergymashuplab.cts.RefIdType;
import org.theenergymashuplab.cts.ResourceDesignatorType;
import org.theenergymashuplab.cts.SideType;
import org.theenergymashuplab.cts.TenderIdType;
import org.theenergymashuplab.cts.TenderIntervalDetail;
import org.theenergymashuplab.cts.WarrantIdType;
import org.theenergymashuplab.cts.controller.payloads.EiCreateTenderPayload;
import org.theenergymashuplab.cts.controller.payloads.EiCreatedTenderPayload;
import org.theenergymashuplab.cts.generated_files.EiCreateTenderPayloadDecoder;
import org.theenergymashuplab.cts.generated_files.EiCreateTenderPayloadEncoder;
import org.theenergymashuplab.cts.generated_files.MessageHeaderDecoder;
import org.theenergymashuplab.cts.generated_files.MessageHeaderEncoder;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.rmi.server.UID;
import java.time.Duration;
import java.time.Instant;

public class ExampleCreateTender {
    public static void main(String[] args) {
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
        
        int encodedLength = EiTenderEncoderDecoder.eiCreateTenderEncode(eiCreateTenderPayloadEncoder, directBuffer, messageHeaderEncoder, payload);

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

        EiCreateTenderPayload decoded = EiTenderEncoderDecoder.eiCreateTenderPayloadDecode(
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