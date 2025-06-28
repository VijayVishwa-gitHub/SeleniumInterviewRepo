package SeleniumFramework;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.*;

public class excelUtilities {
    static Sheet sheet;
    static Workbook workbook;
    static Cell cell;
    static Row row;

    private static final Logger logger = LogManager.getLogger(excelUtilities.class);

    public static void getCellValue(Row row, String str) {

        Map<String, Integer> headerMap = new HashMap<>();
        for (Cell k : row) {
            headerMap.put(row.getCell(k.getColumnIndex()).toString(), k.getColumnIndex());
        }
        int z = headerMap.get(str);
        if (sheet == null) {
            logger.error("Sheet is null. Cannot retrieve cell value.");
            return;
        } else {
//            for (int i = 0; i < row.getLastCellNum(); i++) {
//                if(row.getCell(i).toString().equalsIgnoreCase(str)) {
            int RowsCount = sheet.getLastRowNum();  //returns the last row number in the sheet
            for (int j = 0; j < sheet.getLastRowNum(); j++) {
                String result = sheet.getRow(j + 1).getCell(z).toString();  //getRow>getCell>toString()
                //String result = sheet.getRow(rowIndex + 1).getCell(cellIndex).toString();
                logger.info("Value of " + row.getCell(z).toString() + ": " + result);

            }
        }
//            }
//        }
        logger.info("Reading Completed");
    }

    public static void URLManipulation(Map<String, String> data, String URL) {


        String regex = "~([^/&]+)";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(URL);
        StringBuffer result = new StringBuffer();

        while (matcher.find()) {
            String key = matcher.group(1);
            String replacement = data.getOrDefault(key, matcher.group(0));
            //String  replacement = matcher.group(1).equals(key) ? data.get(matcher.group(1)) : matcher.group(0);
            matcher.appendReplacement(result, replacement);
        }

        matcher.appendTail(result);
        logger.info(STR."Updated URL: \{result}");
        logger.info("Created Actual URL");

    }


//        String resource = "svijayvishwa";
//        String ID = "1234";
//        StringBuilder demo = new StringBuilder("https://automationexercise.com/~ID/~Resource");
//
//        String regex = "~([^/&]+)";
//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(demo.toString());
//
//        StringBuffer result = new StringBuffer();  // Use this to build final string safely
//
//        while (matcher.find()) {
//            String replacement;
//            switch (matcher.group(1)) {
//                case "ID":
//                    replacement = ID;
//                    break;
//                case "Resource":
//                    replacement = resource;
//                    break;
//                default:
//                    replacement = matcher.group(0);  // fallback: no replacement
//            }
//
//            matcher.appendReplacement(result, replacement);
//        }
//
//        matcher.appendTail(result);
//
//        // Update the original StringBuilder
//        demo.setLength(0);
//        demo.append(result);
//
//        System.out.println(demo.toString());
//    }


    /**
     * This class is used to read data from an Excel file.
     * It uses Apache POI library to handle Excel files.
     */
    public static void main(String[] args) {
        String filePath = "C:/Users/VijayBala/IdeaProject/src/main/java/DataSheets/Testsheet.xlsx";
        logger.info("File Fetched & Reading Started");
        HashMap<String, String> data = new HashMap<>();
        try (FileInputStream fis = new FileInputStream(filePath)) {
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheetAt(0);
            Row row = sheet.getRow(0);

            HashMap<String, String> Urldata = new HashMap<>();
            Urldata.put("ID", "1234");
            Urldata.put("Resource", "svijayvishwa");

            URLManipulation(Urldata, "https://automationexercise.com/~ID/~Resource");
            // getCellValue2(row,"Domain", "Robin");

        } catch (IOException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }
}