package com.xpman.spreadsheet;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.*;
import com.xpman.constants.EntitySpreadSheet;
import com.xpman.model.BaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.xpman.utils.GoogleCredentialsManager.*;

public class GoogleSheetOperation {

    private static final Logger logger = LoggerFactory.getLogger(GoogleSheetOperation.class);

    private static String getSheetId(){
        return "1QESuBMB_PcDQy0b8bbBPJ77a0b8DLxSgbbez8YGz3-E";
    }

    public static Sheets getService() throws IOException, GeneralSecurityException {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
         // "1BxiMVs0XRA5nFMdKvBdBZjgmUUqptlbs74OgvE2upms";
        return new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    public static List<List<Object>> getAll(EntitySpreadSheet entitySpreadSheet) {
        logger.info("Reading from {}", entitySpreadSheet.getSheet());
        List<List<Object>> values = new ArrayList<>();
        try {
            Sheets service = getService();
            String spreadsheetId = getSheetId();
            String range = entitySpreadSheet.getSheet() + "!A2:D";
            ValueRange response = service.spreadsheets().values()
                    .get(spreadsheetId, range)
                    .execute();
            values = response.getValues();
            if (values == null || values.isEmpty()) {
                logger.info("No data found.");
            }
        }
        catch (Exception e){
            logger.error("Error while reading {}", entitySpreadSheet.getSheet());
            e.printStackTrace();
        }
        return values;
    }

    public static List<List<Object>> getByColumnNumber(EntitySpreadSheet entitySpreadSheet, Integer colNum) {
        return getAll(entitySpreadSheet);
    }

    public static List<Object> getByRowNumber(EntitySpreadSheet entitySpreadSheet, Integer rowNum) {
        logger.info("Reading from {} for rowNum {}", entitySpreadSheet.getSheet(), rowNum);
        List<Object> result = new ArrayList<>();
        try {
            Sheets service = getService();
            String spreadsheetId = getSheetId();
            String range = entitySpreadSheet.getSheet() + "!A" + rowNum+ ":" + entitySpreadSheet.getRange() + rowNum;
            logger.info("Range {}", range);
            ValueRange response = service.spreadsheets().values()
                    .get(spreadsheetId, range)
                    .execute();
            List<List<Object>> values = response.getValues();
            if (values == null || values.isEmpty()) {
                logger.info("No data found.");
            }
            else{
                result = values.get(0);
            }
        }
        catch (Exception e){
            logger.error("Error while reading {}", entitySpreadSheet.getSheet());
            e.printStackTrace();
        }
        return result;
    }

    public static void append(EntitySpreadSheet entitySpreadSheet, BaseEntity entity) throws IOException, GeneralSecurityException {
        logger.info("Writing to {}", entitySpreadSheet.getSheet());
        List<List<Object>> values = Arrays.asList(
                entity.toList()
        );
        String spreadsheetId = getSheetId();
        String valueInputOption = "RAW";
        Sheets service = getService();
        String range = entitySpreadSheet.getSheet() + "!A2:D";

        ValueRange body = new ValueRange()
                .setValues(values);
        AppendValuesResponse result =
                service.spreadsheets().values().append(spreadsheetId, range, body)
                        .setValueInputOption(valueInputOption)
                        .execute();
        logger.info("{} cells appended.", result.getUpdates().getUpdatedCells());
    }

    public static void updateByRowNumber(EntitySpreadSheet entitySpreadSheet, BaseEntity entity, Integer rowNum) throws IOException, GeneralSecurityException {
        logger.info("Writing to {} for row {}", entitySpreadSheet.getSheet(), rowNum);
        List<List<Object>> values = Arrays.asList(
                entity.toList()
        );
        String spreadsheetId = getSheetId();
        String valueInputOption = "RAW";
        Sheets service = getService();
        String range = entitySpreadSheet.getSheet() + "!A" + rowNum+ ":" + entitySpreadSheet.getRange() + rowNum;
        logger.info("Range {}", range);
        ValueRange body = new ValueRange()
                .setValues(values);
        UpdateValuesResponse result =
                service.spreadsheets().values()
                        .update(spreadsheetId, range, body)
                        .setValueInputOption(valueInputOption)
                        .execute();
        logger.info("{} cells appended.",  result.getUpdatedCells());
    }

    public static void deleteByRowNumber(EntitySpreadSheet entitySpreadSheet, Integer rowNum) throws IOException, GeneralSecurityException {
        logger.info("Deleting from {} for row {}", entitySpreadSheet.getSheet(), rowNum);
        String spreadsheetId = getSheetId();
        Sheets service = getService();
        BatchUpdateSpreadsheetRequest content = new BatchUpdateSpreadsheetRequest();
        Request request = new Request().setDeleteDimension(
                         new DeleteDimensionRequest()
                        .setRange(new DimensionRange()
                        .setDimension("ROWS")
                        .setStartIndex(rowNum-1)
                        .setEndIndex(rowNum))
                );
        List<Request> requests = new ArrayList<Request>();
        requests.add(request);
        content.setRequests(requests);
        service.spreadsheets().batchUpdate(spreadsheetId, content).execute();
    }
}
