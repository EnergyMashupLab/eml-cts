package org.theenergymashuplab.cts;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.theenergymashuplab.cts.TradingDataDTO;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataExtractorService {

    private static final Logger logger = LoggerFactory.getLogger(DataExtractorService.class);

    public List<TradingDataDTO> extractDataFromFile(String filePath) {
        // made this a arraylist to store the data but not sure if we will want to use a different data structure
        List<TradingDataDTO> dataList = new ArrayList<>();

        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setDelimiter(',')
                .setTrim(true)
                .setSkipHeaderRecord(false)
                .setIgnoreEmptyLines(true)
                .build();

        try (Reader reader = new FileReader(filePath);
             CSVParser csvParser = new CSVParser(reader, csvFormat)) {

            for (CSVRecord csvRecord : csvParser) {
                if (csvRecord.size() == 0 || !"D".equals(csvRecord.get(0))) {
                    continue;
                }

                // we wil check if the record has the expected number of columns 36
                // might need to edit this if we change the number of columns otherwise it will skip the record
                 if (csvRecord.size() < 36) {
                     logger.warn("Skipping data record. expected at least 36 columns");
                     continue;
                 }

                TradingDataDTO dto = new TradingDataDTO();
                boolean recordParsedSuccessfully = true;
                try {
                    // indexing starts at 1, if you want to change the index you canchange the index in the safeGet method by adding or subtracting. or manually change it below
                    dto.sethDayTrading(safeGet(csvRecord, 1));
                    dto.setIntervalMasked(safeParseInt(csvRecord, 2));
                    dto.setLeadParticipantIDMasked(safeGet(csvRecord, 3));
                    dto.setAssetIDMasked(safeGet(csvRecord, 4));        
                    dto.setMustTakeEnergy(safeParseDouble(csvRecord, 5));
                    dto.setMaximumDailyEnergyAvailable(safeParseDouble(csvRecord, 6));
                    dto.setEconomicMaximum(safeParseDouble(csvRecord, 7));
                    dto.setEconomicMinimum(safeParseDouble(csvRecord, 8));
                    dto.setColdStartupPrice(safeParseDouble(csvRecord, 9));
                    dto.setIntermediateStartupPrice(safeParseDouble(csvRecord, 10));
                    dto.setHotStartupPrice(safeParseDouble(csvRecord, 11));
                    dto.setNoLoadPrice(safeParseDouble(csvRecord, 12));
                    dto.setSegment1Price(safeParseDouble(csvRecord, 13));
                    dto.setSegment1MW(safeParseDouble(csvRecord, 14));
                    dto.setSegment2Price(safeParseDouble(csvRecord, 15));
                    dto.setSegment2MW(safeParseDouble(csvRecord, 16));
                    dto.setSegment3Price(safeParseDouble(csvRecord, 17));
                    dto.setSegment3MW(safeParseDouble(csvRecord, 18));
                    dto.setSegment4Price(safeParseDouble(csvRecord, 19));
                    dto.setSegment4MW(safeParseDouble(csvRecord, 20));
                    dto.setSegment5Price(safeParseDouble(csvRecord, 21));
                    dto.setSegment5MW(safeParseDouble(csvRecord, 22));
                    dto.setSegment6Price(safeParseDouble(csvRecord, 23));
                    dto.setSegment6MW(safeParseDouble(csvRecord, 24));
                    dto.setSegment7Price(safeParseDouble(csvRecord, 25));
                    dto.setSegment7MW(safeParseDouble(csvRecord, 26));
                    dto.setSegment8Price(safeParseDouble(csvRecord, 27));
                    dto.setSegment8MW(safeParseDouble(csvRecord, 28));
                    dto.setSegment9Price(safeParseDouble(csvRecord, 29));
                    dto.setSegment9MW(safeParseDouble(csvRecord, 30));
                    dto.setSegment10Price(safeParseDouble(csvRecord, 31));
                    dto.setSegment10MW(safeParseDouble(csvRecord, 32));
                    dto.setClaim10(safeParseDouble(csvRecord, 33));
                    dto.setClaim30(safeParseDouble(csvRecord, 34));
                    dto.setUnitStatus(safeGet(csvRecord, 35));

                } catch (NumberFormatException e) {
                    logger.error("Error parsing number in record at line {}: {}. Record: {}", csvParser.getCurrentLineNumber(), e.getMessage(), csvRecord.toString());
                    recordParsedSuccessfully = false;
                } catch (ArrayIndexOutOfBoundsException e) {
                     logger.error("Error accessing column index in record at line {}: {}. Record: {}", csvParser.getCurrentLineNumber(), e.getMessage(), csvRecord.toString());
                     recordParsedSuccessfully = false;
                }

                // add if our parsing was successful
                if (recordParsedSuccessfully) {
                    dataList.add(dto);
                }
            }

        } catch (IOException e) {
            logger.error("Errorvparsing CSV file '{}': {}", filePath, e.getMessage(), e);
        }

        logger.info("Success");
        return dataList;
    }



    // below are helper methods to parse the data

    // using to get the string value. retiens empty string or returns the string value
    private String safeGet(CSVRecord record, int index) {
        if (index >= record.size()) {
             logger.warn("Column index {} out of bounds (size {}) for record at line {}. Returning empty string.", index, record.size(), record.getRecordNumber());
             return "";
        }
        return record.get(index);
    }

    // using to parse double return 0.0 if empty otherwise return the double
    private double safeParseDouble(CSVRecord record, int index) throws NumberFormatException {
        String value = safeGet(record, index);
        if (value == null || value.trim().isEmpty()) {
             logger.debug("Empty value found for double at index {} for record at line {}. Returning 0.0.", index, record.getRecordNumber());
             return 0.0;
        }
        return Double.parseDouble(value.trim());
    }

    // using to parse integer returns 0 if empty else return the integer
    private int safeParseInt(CSVRecord record, int index) throws NumberFormatException {
        String value = safeGet(record, index);
         if (value == null || value.trim().isEmpty()) {
             logger.debug("Empty value found at index {} at line {}", index, record.getRecordNumber());
             return 0;
         }
        return Integer.parseInt(value.trim());
    }
} 