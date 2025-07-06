package RestAssuredAutomation;

import SeleniumFramework.excelUtilities;
import groovy.lang.GString;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class testDataHandler {
    private static final Logger logger = LogManager.getLogger(testDataHandler.class);
    static Sheet sheet;
    static Workbook workbook;
    static Cell cell;
    static Row row;
    static String filepath = "C:/Users/VijayBala/IdeaProject/src/main/java/DataSheets/Testsheet.xlsx";
    static FileInputStream fis;
    static FileOutputStream fos;

    static {
        try {
            fis = new FileInputStream(filepath);
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheetAt(0);
            row = sheet.getRow(0);
            fis.close(); // ✅ Safe to close after loading workbook
        } catch (IOException e) {
            throw new RuntimeException("Failed to load Excel file: " + e.getMessage(), e);
        }
    }

    public testDataHandler() throws FileNotFoundException {}

    public static int HeaderIndex(String str){
        HashMap<String, Integer> headerValue = new HashMap<>();
        row  = sheet.getRow(0);
        for (Cell k : row) {
            headerValue.put(row.getCell(k.getColumnIndex()).toString(), k.getColumnIndex());
        }
        int z = headerValue.get(str);
        return z;
    }

    public static String getCellValue(String str) throws IOException {

        logger.info("File Fetched and started Reading");

        int z = HeaderIndex(str);
        String JSONPayload = null;

        for (int j = 0; j < sheet.getLastRowNum(); j++) {
            JSONPayload = sheet.getRow(j + 1).getCell(z).toString();
            logger.info("Payload Fetched");

        }
        return JSONPayload;
    }

    public static String updatedPayload(Integer row) throws IOException {
        // ❌ Remove these lines
        // fis = new FileInputStream(filepath);
        // workbook = new XSSFWorkbook();
        // sheet = workbook.getSheetAt(0);
        // row = sheet.getRow(0);

        String payload = getCellValue("REQUESTPAYLOAD");

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

                String cellValue = sheet.getRow(1).getCell(colIndex).toString();
                if (cellValue == null || cellValue.isEmpty()) {
                    throw new IllegalArgumentException("No value found in Excel for header: " + header);
                }

                matcher.appendReplacement(result, Matcher.quoteReplacement(cellValue));
            }

            matcher.appendTail(result);
            return result.toString();

    }


    public static void updateResponses(String Header, String value) throws IOException {
        int z = HeaderIndex(Header); // Column index based on header

        for (int j = 0; j < sheet.getLastRowNum(); j++) {
            Row row = sheet.getRow(j + 1);
            if (row == null) {
                row = sheet.createRow(j + 1); // Create row if it doesn’t exist
            }

            Cell cell = row.getCell(z);
            if (cell == null) {
                cell = row.createCell(z); // Create cell if it doesn’t exist
            }

            cell.setCellValue(value); // Write value
        }

        fos = new FileOutputStream(filepath); // Open file for writing
        workbook.write(fos);
        fos.close(); // Close file
        //workbook.close(); // Close workbook
    }

}

