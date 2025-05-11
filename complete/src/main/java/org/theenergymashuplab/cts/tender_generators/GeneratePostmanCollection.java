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
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.theenergymashuplab.cts.tender_generators;

import java.time.Instant;
import java.util.Random;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.theenergymashuplab.cts.SideType;
import org.theenergymashuplab.cts.controller.payloads.ClientCreateTenderPayload;

/*
 * This class' main function is run to generate a postman collection at path /complete/src/main/resources
 * 
 * The number of create tender requests to generate is defined by the numberOfTenders variable in the main function
 */

public class GeneratePostmanCollection {
    static Random rand = new Random();

    public static void main(String[] args) {
        int numberOfTenders = 100;
        // Header for postman collection
        String jsonOutput = "{\"info\":{\"_postman_id\":\"5ea37b54-c932-4a9f-bd39-0558328a3e5c\",\"name\":\"Auction Market Testing\",\"schema\":\"https://schema.getpostman.com/json/collection/v2.1.0/collection.json\",\"_exporter_id\":\"43509160\"},\"item\":[";
        // This makes the first request in the collection the auction clear request
        jsonOutput += "{\"name\": \"Clear Auction Market\",\"request\": {\"auth\": {"
                + "\"type\": \"noauth\"},\"method\": \"GET\",\"header\": [],\"url\": {"
                + "\"raw\": \"http://localhost:8080/lme/clear\",\"protocol\": \"http\",\"host\": [\"localhost\""
                + "],\"port\": \"8080\",\"path\": [\"lme\",\"clear\"]}},\"response\": []},";
        String item;
        String raw;
        for (int i = 0; i < numberOfTenders; i++) {
            ClientCreateTenderPayload tender = randomTender();
            item = "{\"name\":\"" + tender.getSide() + " " + tender.getQuantity() + " at " + tender.getPrice()
                    + "\",\"request\":{\"method\":\"POST\",\"header\":[],\"body\":{\"mode\":\"raw\",\"raw\":";

            raw = "\"{\\\"info\\\":\\\"" + tender.getInfo() + "\\\",\\\"side\\\":\\\"" + tender.getSide()
                    + "\\\",\\\"quantity\\\":" + tender.getQuantity() + ",\\\"price\\\":" + tender.getPrice()
                    + ",\\\"ctsTenderId\\\":0,\\\"bridgeInterval\\\":{" + tender.getBridgeInterval().toString() + "},"
                    + "\\\"bridgeExpireTime\\\":{" + "\\\"instantString\\\":\\\""
                    + tender.getBridgeExpireTime().getInstantString() + "\\\"}," + "\\\"segmentId\\\":"
                    + tender.getSegmentId() + "}\",";

            item += raw;

            item += "\"options\":{\"raw\":{\"language\":\"json\"}}},\"url\":{\"raw\":\"http://localhost:8080/teua/1/clientCreateTender\",\"protocol\":\"http\",\"host\":[\"localhost\"],\"port\":\"8080\",\"path\":[\"teua\",\"1\",\"clientCreateTender\"]}},\"response\":[]}";

            // No trailing comma
            if (i != numberOfTenders - 1) {
                item += ",";
            }

            jsonOutput += item;
        }

        // Close item array in json file
        jsonOutput += "]}";

        try {
            String path = "complete/src/main/resources/Generated_Postman_Collection.json";
            File file = new File(path);
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(path);
            fileWriter.write(jsonOutput);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error creating postman collection json file");
        }

        System.out.println(jsonOutput);
    }

    public static ClientCreateTenderPayload randomTender() {
        SideType side = SideType.BUY;
        int quantity = 1 + rand.nextInt(100);
        int price = 1 + rand.nextInt(100);
        Instant startTime = Instant.parse("2020-06-20T00:00:00.00Z");
        // Segment 2 is for auction market
        int segmentId = 2;

        // 50-50 chance of being buy or sell side
        if (rand.nextInt(2) == 1) {
            side = SideType.SELL;
            // If sell, 50-50 chance of being negative bid
            if (rand.nextInt(2) == 1)
                price *= -1;
        }

        return new ClientCreateTenderPayload(side, quantity, price, startTime, 60 * 60, segmentId);

    }
}
