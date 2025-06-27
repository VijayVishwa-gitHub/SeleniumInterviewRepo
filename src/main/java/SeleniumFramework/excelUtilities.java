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

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.*;

public class excelUtilities {
    static Sheet sheet;
    static Workbook workbook;
    static Cell cell;
    static Row row;

    private static final Logger logger = LogManager.getLogger(excelUtilities.class);

    public static void getCellValue(Row row, String str) {

        Map <String, Integer> headerMap = new HashMap<>();
        for(Cell k : row){
            headerMap.put(row.getCell(k.getColumnIndex()).toString(), k.getColumnIndex());
            }
        int z = headerMap.get(str);
        if(sheet == null) {
            logger.error("Sheet is null. Cannot retrieve cell value.");
            return;
        }
        else{
//            for (int i = 0; i < row.getLastCellNum(); i++) {
//                if(row.getCell(i).toString().equalsIgnoreCase(str)) {
                    int RowsCount = sheet.getLastRowNum();  //returns the last row number in the sheet
                    for (int j = 0; j < sheet.getLastRowNum(); j++) {
                        String result = sheet.getRow(j + 1).getCell(z).toString();  //getRow>getCell>toString()
                        //String result = sheet.getRow(rowIndex + 1).getCell(cellIndex).toString();
                        logger.info("Value of "+row.getCell(z).toString()+": " + result);

                    }
                }
//            }
//        }
logger.info("Reading Completed");
    }
    /**
     * This method retrieves the value of a specific cell in an Excel sheet based on the header name.
     * It uses Apache POI to read the Excel file and logs the retrieved value.
     *
     * @param row The row from which to retrieve the cell value.
     * @param str The header name to search for in the row.
     * @param name The name of the person (not used in this method, but can be used for logging or other purposes).
     */
    public static void getCellValue2(Row row, String str, String name) {

        Map<String, Integer> headerMap = new HashMap<>();
        int RowsCount = sheet.getLastRowNum()+1;
        String cellValue = null;
        boolean found = false;

        for (Cell k : row) {
            headerMap.put(row.getCell(k.getColumnIndex()).toString(), k.getColumnIndex());
        }
        int z = headerMap.get(str);

        if (sheet == null) {
            logger.error("Sheet is null. Cannot retrieve cell value.");
            return;
        } else {
                    if (row.getCell(z).toString().equalsIgnoreCase(str)) {
                    for(int j =0; j < RowsCount; j++) {
                        try {
                            cellValue = sheet.getRow(j + 1).getCell(0).toString();
                        }catch (NullPointerException e) {
                            logger.error("Null Pointer Exception at row: " + (j + 1) + ", column: " + z + ". Cell might be empty.");
                            continue;
                        }
                            if(cellValue.equalsIgnoreCase(name)){
                                logger.info(row.getCell(z).toString() + " of "+sheet.getRow(j+1).getCell(0).toString()+" is " +  sheet.getRow(j+1).getCell(z).toString());
                                found = true;
                                break;
                        }
                    }if(!found) {
                        logger.info("Row for name " + name + " not found.");
                    }
                }
            }

        logger.info("Reading Completed");
    }

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


            getCellValue2(row,"Domain", "Robin");

        } catch (IOException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }
}