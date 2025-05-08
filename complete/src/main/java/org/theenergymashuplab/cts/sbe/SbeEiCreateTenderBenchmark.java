/*
* Copyright 2019-2025 The Energy Mashup Lab
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.

*/
package org.theenergymashuplab.cts.sbe;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.agrona.concurrent.UnsafeBuffer;
import org.theenergymashuplab.cts.*;
import org.theenergymashuplab.cts.ResourceDesignatorType;
import org.theenergymashuplab.cts.SideType;
import org.theenergymashuplab.cts.controller.payloads.EiCreateTenderPayload;
import org.theenergymashuplab.cts.generated_files.*;

import java.nio.ByteBuffer;
import java.time.Duration;
import java.time.Instant;

public class SbeEiCreateTenderBenchmark {

    public static void main(String[] args) throws Exception {
        final int iterations = 1_000_000;

        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
        UnsafeBuffer buffer = new UnsafeBuffer(byteBuffer);

        MessageHeaderEncoder headerEncoder = new MessageHeaderEncoder();
        MessageHeaderDecoder headerDecoder = new MessageHeaderDecoder();

        EiCreateTenderPayloadEncoder encoder = new EiCreateTenderPayloadEncoder();
        EiCreateTenderPayloadDecoder decoder = new EiCreateTenderPayloadDecoder();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        // --- Payload Construction ---
        EiCreateTenderPayload payload = new EiCreateTenderPayload();
        payload.setAtMostOne(true);

        ActorIdType counterPartyId = new ActorIdType();
        counterPartyId.setMyUidId(5);
        payload.setCounterPartyId(counterPartyId);

        MarketIdType marketId = new MarketIdType();
        marketId.setMyUidId(6);
        payload.setMarketId(marketId);

        ActorIdType partyId = new ActorIdType();
        partyId.setMyUidId(7);
        payload.setPartyId(partyId);

        RefIdType requestId = new RefIdType();
        requestId.setMyUidId(8);
        payload.setRequestId(requestId);

        payload.setExecutionInstructions("");
        payload.setSegmentId(7777);

        EiTenderType tender = new EiTenderType();
        MarketOrderIdType marketOrderId = new MarketOrderIdType();
        marketOrderId.setMyUidId(1001);
        tender.setMarketOrderId(marketOrderId);
        tender.setReferencedQuoteId(marketOrderId);

        TenderIdType tenderId = new TenderIdType();
        tenderId.setMyUidId(2001);
        tender.setAllOrNone(true);
        tender.setExpirationTime(Instant.now().plusSeconds(3600));
        tender.setMarketId(marketId);
        tender.setPriceScale(2);
        tender.setQuantityScale(3);
        tender.setResourceDesignator(ResourceDesignatorType.ENERGY);
        tender.setSegmentId(7777);
        tender.setSide(SideType.BUY);
        tender.setWarrants(new WarrantIdType(1234));

        Interval interval = new Interval();
        interval.setDtStart(Instant.now());
        interval.setDuration(Duration.ofDays(1));
        TenderIntervalDetail tenderDetail = new TenderIntervalDetail(interval, 500, 100);
        tender.setTenderDetail(tenderDetail);

        payload.setTender(tender);

        // --- Warm-up ---
        for (int i = 0; i < 10_000; i++) {
            EiTenderPayloadEncoderDecoder.eiCreateTenderEncode(encoder, buffer, headerEncoder, payload);
            EiTenderPayloadEncoderDecoder.eiCreateTenderDecode(decoder, buffer, headerEncoder.encodedLength(), decoder.sbeBlockLength(), decoder.sbeSchemaVersion());
            byte[] jsonBytes = objectMapper.writeValueAsBytes(payload);
            objectMapper.readValue(jsonBytes, EiCreateTenderPayload.class);
        }

        // --- JSON Benchmark ---
        long jsonStart = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            byte[] jsonBytes = objectMapper.writeValueAsBytes(payload);
            objectMapper.readValue(jsonBytes, EiCreateTenderPayload.class);
        }
        long jsonEnd = System.nanoTime();

        // --- SBE Benchmark ---
        long sbeStart = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            EiTenderPayloadEncoderDecoder.eiCreateTenderEncode(encoder, buffer, headerEncoder, payload);
            EiTenderPayloadEncoderDecoder.eiCreateTenderDecode(decoder, buffer, headerEncoder.encodedLength(), decoder.sbeBlockLength(), decoder.sbeSchemaVersion());
        }
        long sbeEnd = System.nanoTime();

        double jsonTimeMs = (jsonEnd - jsonStart) / 1_000_000.0;
        double sbeTimeMs = (sbeEnd - sbeStart) / 1_000_000.0;

        System.out.printf("JSON: %.2f ms (%.2f µs per op)%n", jsonTimeMs, (jsonTimeMs * 1000) / iterations);
        System.out.printf("SBE : %.2f ms (%.2f µs per op)%n", sbeTimeMs, (sbeTimeMs * 1000) / iterations);
    }
}
