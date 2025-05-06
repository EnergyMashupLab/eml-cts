package org.theenergymashuplab.cts.sbe;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.theenergymashuplab.cts.SideType;
import org.theenergymashuplab.cts.controller.payloads.ClientCreateTenderPayload;

import java.time.Instant;
import java.util.Random;

public class BenchmarkClientCreateTender {
    public static void main(String[] args) {
        Random rand = new Random();
        Instant baseTime = Instant.now();

        int quantity = 50 + rand.nextInt(51);             // 50-100
        long price = 10L * (rand.nextInt(30) + 1);        // 10-300 cents
        Instant start = baseTime.plusSeconds(rand.nextInt(24 * 3600)); // within 24h
        int intervalSec = 3600;                           // 1 hour
        SideType side = rand.nextBoolean() ? SideType.BUY : SideType.SELL;

        ClientCreateTenderPayload payload = new ClientCreateTenderPayload(side, quantity, price, start, intervalSec);

        // Register JavaTimeModule to support Instant and Duration
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        try {
            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(payload);
            System.out.println("Copy this JSON into Postman:");
            System.out.println(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
