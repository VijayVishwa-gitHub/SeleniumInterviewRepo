package RestAssuredAutomation;

import SeleniumFramework.excelUtilities;
import groovy.lang.GString;
import io.cucumber.core.internal.com.fasterxml.jackson.core.type.TypeReference;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.net.URI;
import java.time.Instant;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.testng.annotations.Test;

import static org.apache.poi.ss.usermodel.IndexedColors.LIGHT_GREEN;

public class testDataHandler {
    public static final Logger logger = LogManager.getLogger(testDataHandler.class);
    public static int count;
    static Sheet sheet;
    static Workbook workbook;
    static Cell cell;
    static Row row;
    static String filepath = "./src/main/java/DataSheets/Testsheet.xlsx";
    static FileInputStream fis;
    static FileOutputStream fos;




    static {
        try {
            fis = new FileInputStream(filepath);
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheetAt(0);
            count = sheet.getLastRowNum();
            row = sheet.getRow(0);
            fis.close(); // ✅ Safe to close after loading workbook
        } catch (IOException e) {
            throw new RuntimeException("Failed to load Excel file: " + e.getMessage(), e);
        }
    }

    public static int HeaderIndex(String str) {
        HashMap<String, Integer> headerValue = new HashMap<>();
        row = sheet.getRow(0);
        for (Cell k : row) {
            headerValue.put(row.getCell(k.getColumnIndex()).toString(), k.getColumnIndex());
        }
        int z = 0;
        try {
            z = headerValue.get(str);
        } catch (NullPointerException e) {
            logger.info("header not available in sheet");
        }
        return z;
    }

    public static String getCellValue(String str, int i) throws IOException {

        int z = HeaderIndex(str);
        String JSONPayload = null;


            JSONPayload = sheet.getRow(i+1).getCell(z).toString();
            logger.info("Payload Fetched");


        return JSONPayload;
    }

    public static String updatedPayload(Integer row) throws IOException {
        logger.info("Preparing the requestPayload ");

        String payload = getCellValue("REQUESTPAYLOAD", row);

             // Assume header 'Payload' contains the full template JSON

            // Regex to find all ~placeholders in the payload
            Pattern pattern = Pattern.compile("~(\\w+)");
            Matcher matcher = pattern.matcher(payload);

            StringBuffer result = new StringBuffer();

            while (matcher.find()) {
                String placeholder = matcher.group();     // e.g., ~place_id
                String header = matcher.group(1);         // e.g., place_id (without ~)

                int colIndex = HeaderIndex(header);       // Get column index
                if (colIndex == -1) {
                    throw new IllegalArgumentException("Header not found in Excel: " + header);
                }

                String cellValue = sheet.getRow(row+1).getCell(colIndex).toString();
                if (cellValue == null || cellValue.isEmpty()) {
                    throw new IllegalArgumentException("No value found in Excel for header: " + header);
                }

                matcher.appendReplacement(result, Matcher.quoteReplacement(cellValue));
            }
        logger.info("Updated the requestPayload ");
            matcher.appendTail(result);
            return result.toString();

    }

    public static void updateResponses(String Header, String value, int rowNum) throws IOException {

        logger.info("Writing: " +Header);
        int z = HeaderIndex(Header); // Column index based on header
        int statusCell = HeaderIndex("STATUS");

            Row row = sheet.getRow(rowNum+1);
            if (row == null) {
                row = sheet.createRow(rowNum+1); // Create row if it doesn’t exist
            }

            Cell cell = row.getCell(z);
            if (cell == null) {
                cell = row.createCell(z); // Create cell if it doesn’t exist
            }

            cell.setCellValue(value); // Write value


            Cell cell2 = row.createCell(statusCell);
            cell2.setCellValue("PASSED");
            CellStyle style = workbook.createCellStyle();
            style.setFillForegroundColor(IndexedColors.DARK_GREEN.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cell2.setCellStyle(style);




        fos = new FileOutputStream(filepath); // Open file for writing
        workbook.write(fos);
        fos.close();

        logger.info("Updated Response");
    }

    /**
     * Validates the response and appends a row to an Excel file with columns:
     * Timestamp, Request Payload, Response Payload, Status Code, Response Time (ms),
     * Cookies, Request URI, Method, Request Content-Type, Response Content-Type
     */
    public static void validateAndSaveResponse(String requestPayload, String responseBody, HttpResponse response, long responseTimeMs, URI uri, HttpUriRequest request) throws Exception {

        int statusCode = response.getStatusLine() != null ? response.getStatusLine().getStatusCode() : -1;

        Header[] cookieHeaders = (Header[]) response.getHeaders("Set-Cookie");
        String cookies = cookieHeaders != null && cookieHeaders.length > 0
                ? String.join("; ", Arrays.stream(cookieHeaders).map(Header::getValue).toArray(String[]::new))
                : "";

        String requestContentType = request.getFirstHeader("Content-Type") != null
                ? request.getFirstHeader("Content-Type").getValue()
                : "";

        Header respContentTypeHeader = response.getFirstHeader("Content-Type");
        String responseContentType = respContentTypeHeader != null ? respContentTypeHeader.getValue() : "";

        String timestamp = Instant.now().toString();

        File outFile = new File("./src/main/java/DataSheets/response_results.xlsx");
        XSSFWorkbook workbook;
        XSSFSheet sheet;

        if (outFile.exists()) {
            try (FileInputStream fis = new FileInputStream(outFile)) {
                workbook = new XSSFWorkbook(fis);
            }
            sheet = workbook.getNumberOfSheets() > 0 ? workbook.getSheetAt(0) : workbook.createSheet("API Results");
        } else {
            workbook = new XSSFWorkbook();
            sheet = workbook.createSheet("API Results");
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("Timestamp");
            header.createCell(1).setCellValue("Request Payload");
            header.createCell(2).setCellValue("Response Payload");
            header.createCell(3).setCellValue("Status Code");
            header.createCell(4).setCellValue("Response Time (ms)");
            header.createCell(5).setCellValue("Cookies");
            header.createCell(6).setCellValue("Request URI");
            header.createCell(7).setCellValue("Method");
            header.createCell(8).setCellValue("Request Content-Type");
            header.createCell(9).setCellValue("Response Content-Type");
        }

        int rowNum = sheet.getLastRowNum() + 1;
        Row row = sheet.createRow(rowNum);
        row.createCell(0).setCellValue(timestamp);
        row.createCell(1).setCellValue(requestPayload != null ? requestPayload : "");
        row.createCell(2).setCellValue(responseBody != null ? responseBody : "");
        row.createCell(3).setCellValue(statusCode);
        row.createCell(4).setCellValue(responseTimeMs);
        row.createCell(5).setCellValue(cookies);
        row.createCell(6).setCellValue(uri != null ? uri.toString() : "");
        row.createCell(7).setCellValue(request != null ? request.getMethod() : "");
        row.createCell(8).setCellValue(requestContentType);
        row.createCell(9).setCellValue(responseContentType);

        // autosize a few columns (optional)
        for (int i = 0; i <= 9; i++) {
            sheet.autoSizeColumn(i);
        }

        // ensure target directory exists
        outFile.getParentFile().mkdirs();

        try (FileOutputStream fos = new FileOutputStream(outFile)) {
            workbook.write(fos);
        }
        workbook.close();
    }

    public static void responseValidation(String expectedResult, String actualResult, int cell) throws IOException {
        if(expectedResult!=null) {
            getCellValue(expectedResult, cell);

            ObjectMapper mapper = new ObjectMapper();

            Map<String, Object> responseMap = mapper.readValue(expectedResult, new TypeReference<Map<String, Object>>() {
            });

            for (Map.Entry<String, Object> entrySet : responseMap.entrySet()) {
                System.out.println(STR."\{entrySet.getValue()} \{entrySet.getKey()}");

            }
        }
        else{
            logger.info("empty payload");
        }
    }


}

