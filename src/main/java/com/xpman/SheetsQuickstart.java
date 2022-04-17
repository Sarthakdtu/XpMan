//package com.xpman;
//
//import com.google.api.client.auth.oauth2.Credential;
//import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
//import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
//import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
//import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
//import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
//import com.google.api.client.http.javanet.NetHttpTransport;
//import com.google.api.client.json.JsonFactory;
//import com.google.api.client.json.gson.GsonFactory;
//import com.google.api.client.util.store.FileDataStoreFactory;
//import com.google.api.services.sheets.v4.Sheets;
//import com.google.api.services.sheets.v4.SheetsScopes;
//import com.google.api.services.sheets.v4.model.AppendValuesResponse;
//import com.google.api.services.sheets.v4.model.ValueRange;
//import com.xpman.model.Account;
//
//import java.io.*;
//import java.security.GeneralSecurityException;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//
//public class SheetsQuickstart {
//    private static final String APPLICATION_NAME = "Google Sheets API Java Quickstart";
//    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
//    private static final String TOKENS_DIRECTORY_PATH = "tokens";
//
//    /**
//     * Global instance of the scopes required by this quickstart.
//     * If modifying these scopes, delete your previously saved tokens/ folder.
//     */
//    private static final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS);
//    private static final String CREDENTIALS_FILE_PATH = "C:\\Users\\Sarthak\\Documents\\ExpenseManager\\src\\main\\java\\com\\xpman\\credentials.json";
//
//    /**
//     * Creates an authorized Credential object.
//     * @param HTTP_TRANSPORT The network HTTP Transport.
//     * @return An authorized Credential object.
//     * @throws IOException If the credentials.json file cannot be found.
//     */
//    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
//        // Load client secrets.
//        InputStream in = new FileInputStream(CREDENTIALS_FILE_PATH);
//        //SheetsQuickstart.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
//        if (in == null) {
//            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
//        }
//        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
//
//        // Build flow and trigger user authorization request.
//        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
//                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
//                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
//                .setAccessType("offline")
//                .build();
//        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
//        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user1");
//    }
//
//    /**
//     * Prints the names and majors of students in a sample spreadsheet:
//     * https://docs.google.com/spreadsheets/d/1BxiMVs0XRA5nFMdKvBdBZjgmUUqptlbs74OgvE2upms/edit
//     */
//    public static void main(String... args) throws IOException, GeneralSecurityException {
//        // Build a new authorized API client service.
//        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
//        final String spreadsheetId = "1QESuBMB_PcDQy0b8bbBPJ77a0b8DLxSgbbez8YGz3-E"; // "1BxiMVs0XRA5nFMdKvBdBZjgmUUqptlbs74OgvE2upms";
//        String range = "Account!A2:D";
//        Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
//                .setApplicationName(APPLICATION_NAME)
//                .build();
//        ValueRange response = service.spreadsheets().values()
//                .get(spreadsheetId, range)
//                .execute();
//        List<List<Object>> values = response.getValues();
//        if (values == null || values.isEmpty()) {
//            System.out.println("No data found.");
//        } else {
//            System.out.println("Account, Color");
////            values.remove(0);
//            for (List row : values) {
//                // Print columns A and B, which correspond to indices 0 and 1.
////                System.out.printf("%s, %s\n", row.get(0), row.get(1));
//                String name = (String) row.get(0);
//                String color = (String) row.get(1);
//
//                Account account = Account.builder().build();
//                account.setColor(color);
//                account.setName(name);
//                System.out.println(account.toString());
//            }
//
//        }
//        List<List<Object>> valuesInsert = Arrays.asList(
//                Arrays.asList(
//                        "State Bank", "Yellow"
//                        // Cell values ...
//                )
//                // Additional rows ...
//        );
////        int num = values.size();
////        range = "Category!A" + num + ":B";
//        range = "Account!A2:D";
//        String valueInputOption = "RAW";
//        ValueRange body = new ValueRange()
//                .setValues(valuesInsert);
//        AppendValuesResponse result =
//                service.spreadsheets().values().append(spreadsheetId, range, body)
//                        .setValueInputOption(valueInputOption)
//                        .execute();
//        System.out.printf("%d cells appended.", result.getUpdates().getUpdatedCells());
//    }
//}