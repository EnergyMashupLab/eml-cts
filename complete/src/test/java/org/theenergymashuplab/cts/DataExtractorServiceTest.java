package org.theenergymashuplab.cts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataExtractorServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(DataExtractorServiceTest.class);
    private DataExtractorService dataExtractorService;
    private String testFilePath;

    @BeforeEach
    void setUp() throws IOException {
        dataExtractorService = new DataExtractorService();
        // Use ClassLoader.getResource()
        URL resourceUrl = getClass().getClassLoader().getResource("test-trading-data.csv");
        if (resourceUrl == null) {
            throw new IOException("Could not find test resource file: test-trading-data.csv");
        }
        try {
            testFilePath = Paths.get(resourceUrl.toURI()).toFile().getAbsolutePath();
            logger.info("Using test file path: {}", testFilePath);
        } catch (URISyntaxException e) {
            throw new IOException("Could not convert resource URL to URI", e);
        }
    }

    @Test
    void extractDataFromFile_shouldParseCorrectly() {
        // This test uses the 54-line test-trading-data.csv file from src/test/resources
        List<TradingDataDTO> results = dataExtractorService.extractDataFromFile(testFilePath);

        // --- Print the parsed data ---
        System.out.println("--- Parsed Data Start ---");
        if (results.isEmpty()) {
            System.out.println("(List is empty)");
        } else {
            for (TradingDataDTO dto : results) {
                 // Ensure TradingDataDTO has a useful toString() or modify print statement
                 System.out.println(dto);
            }
        }
        System.out.println("--- Parsed Data End ---");
        // ---------------------------

        // Assertions (should match the 48 'D' records in test-trading-data.csv)
        assertNotNull(results, "Result list should not be null");
        // Expect 48 records based on test-trading-data.csv
        assertEquals(48, results.size(), "Should extract 48 data records from test-trading-data.csv");

        // --- Check data from the first record (line 7 in test-trading-data.csv) ---
        TradingDataDTO firstRecord = results.get(0);
        assertEquals("6/20/2024", firstRecord.gethDayTrading());
        assertEquals(1, firstRecord.getIntervalMasked());
        assertEquals("20720", firstRecord.getLeadParticipantIDMasked());
        assertEquals("40320", firstRecord.getAssetIDMasked());
        assertEquals(0.0, firstRecord.getMustTakeEnergy());
        assertEquals(0.0, firstRecord.getMaximumDailyEnergyAvailable());
        assertEquals(74.1, firstRecord.getEconomicMaximum());
        assertEquals(47.5, firstRecord.getEconomicMinimum());
        assertEquals(9082.72, firstRecord.getColdStartupPrice());
        assertEquals(9082.72, firstRecord.getIntermediateStartupPrice());
        assertEquals(9082.72, firstRecord.getHotStartupPrice());
        assertEquals(2933.86, firstRecord.getNoLoadPrice());
        assertEquals(129.01, firstRecord.getSegment1Price());
        assertEquals(74.1, firstRecord.getSegment1MW());
        assertEquals(0.0, firstRecord.getSegment2Price());
        assertEquals(0.0, firstRecord.getSegment2MW());
        // ... other empty segments ...
        assertEquals(0.0, firstRecord.getSegment10Price());
        assertEquals(0.0, firstRecord.getSegment10MW());
        assertEquals(50.0, firstRecord.getClaim10());
        assertEquals(72.0, firstRecord.getClaim30());
        assertEquals("ECONOMIC", firstRecord.getUnitStatus());

        // --- Check data from the last record (line 54 in test-trading-data.csv) ---
        TradingDataDTO lastRecord = results.get(47); // Index 47 for the 48th record
        assertEquals("6/20/2024", lastRecord.gethDayTrading());
        assertEquals(24, lastRecord.getIntervalMasked());
        assertEquals("20721", lastRecord.getLeadParticipantIDMasked());
        assertEquals("88115", lastRecord.getAssetIDMasked());
        assertEquals(0.0, lastRecord.getMustTakeEnergy());
        assertEquals(0.0, lastRecord.getMaximumDailyEnergyAvailable());
        assertEquals(5.0, lastRecord.getEconomicMaximum());
        assertEquals(0.1, lastRecord.getEconomicMinimum());
        assertEquals(3.1, lastRecord.getColdStartupPrice());
        assertEquals(3.1, lastRecord.getIntermediateStartupPrice());
        assertEquals(3.1, lastRecord.getHotStartupPrice());
        assertEquals(3.1, lastRecord.getNoLoadPrice());
        assertEquals(0.0, lastRecord.getSegment1Price());
        assertEquals(0.1, lastRecord.getSegment1MW());
        assertEquals(0.01, lastRecord.getSegment2Price());
        assertEquals(4.9, lastRecord.getSegment2MW());
        assertEquals(0.0, lastRecord.getSegment3Price());
        assertEquals(0.0, lastRecord.getSegment3MW());
        // ... other empty segments ...
        assertEquals(0.0, lastRecord.getSegment10Price());
        assertEquals(0.0, lastRecord.getSegment10MW());
        assertEquals(0.0, lastRecord.getClaim10());
        assertEquals(0.0, lastRecord.getClaim30());
        assertEquals("ECONOMIC", lastRecord.getUnitStatus());
    }

    @Test
    void extractDataFromFile_nonExistentFile_shouldReturnEmptyList() {
        List<TradingDataDTO> results = dataExtractorService.extractDataFromFile("/path/to/non/existent/file.csv");
        assertNotNull(results, "Result list should not be null even if file doesn't exist");
        assertTrue(results.isEmpty(), "Result list should be empty for a non-existent file");
    }

    @Test
    void extractDataFromFile_malformedRow_shouldSkipRow() throws IOException {
        // Create a temporary file with a malformed row (non-numeric interval)
        File tempFile = File.createTempFile("malformed_data_", ".csv");
        tempFile.deleteOnExit();
        List<String> lines = List.of(
            "C,Header,,,,,,,,,,,,,,,,,,,,,,,,,,,",
            "H,Header,,,,,,,,,,,,,,,,,,,,,,,,,,,",
            "D,6/20/2024,1,20720,40320,0,0,74.1,47.5,9082.72,9082.72,9082.72,2933.86,129.01,74.1,,,,,,,,,,,,,,,,,,,50,72,OK", // Valid
            "D,6/20/2024,NOT_A_NUMBER,20721,40321,0,0,74.1,47.5,9082.72,9082.72,9082.72,2933.86,129.01,74.1,,,,,,,,,,,,,,,,,,,50,72,BAD", // Malformed
            "D,6/20/2024,3,20722,40322,0,0,74.1,47.5,9082.72,9082.72,9082.72,2933.86,129.01,74.1,,,,,,,,,,,,,,,,,,,50,72,OK"  // Valid
        );
        Files.write(tempFile.toPath(), lines);

        List<TradingDataDTO> results = dataExtractorService.extractDataFromFile(tempFile.getAbsolutePath());

        // Now expect only 2 records because the malformed one should be skipped entirely
        assertEquals(2, results.size(), "Should parse 2 valid 'D' records, skipping C, H, and the malformed D record");
        assertEquals(1, results.get(0).getIntervalMasked()); // First valid D record
        assertEquals(3, results.get(1).getIntervalMasked()); // Second valid D record
    }
} 