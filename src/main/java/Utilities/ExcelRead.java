package Utilities;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;


public class ExcelRead {
    static String excelPath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestSheet.xlsx";


    public static void readdata() throws IOException {

        List<LinkedHashMap<String, String>> fileRead = new LinkedList<LinkedHashMap<String, String>>();
        File file = new File(excelPath);
        FileInputStream inputStream = new FileInputStream(excelPath);

        if (file.exists()) {
            System.out.println("Exists");
        }
        Workbook workbook = WorkbookFactory.create(new File(excelPath));
        Sheet sheet = workbook.getSheet("Sheet1");
        Row row = null;
        int lastRow = sheet.getLastRowNum();

        for (int i = 1; i < lastRow; i++) {
            row = sheet.getRow(i);
            Cell execution = row.getCell(1);
            LinkedHashMap<String, String> rowData = new LinkedHashMap<>();
            if (execution.getStringCellValue().equalsIgnoreCase("Yes")) {
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    Cell columnHeader = sheet.getRow(0).getCell(j);
                    Cell rowValue = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    String cellValue = findCelltype(rowValue);
                    if (cellValue == null) {
                        rowData.put(columnHeader.getStringCellValue(), "");
                    } else
                        rowData.put(columnHeader.getStringCellValue(), cellValue);
                }
                fileRead.add(rowData);
                System.out.println(rowData);
            }

        }
        System.out.println(fileRead);
    }

    public Object[][] readExecutionstatus() throws IOException {
        LinkedList<String> testcaseID = new LinkedList<>();
        File file = new File(excelPath);
        FileInputStream inputStream = new FileInputStream(excelPath);
        if (file.exists()) {
            System.out.println("Exists");
        }

        Workbook workbook = WorkbookFactory.create(inputStream);
        Sheet sheet = workbook.getSheet("Sheet1");

        int lastRow = sheet.getLastRowNum();
        int lastCol = sheet.getRow(0).getLastCellNum();
        Row row = null;

        for (int i = 0; i < lastRow; i++) {
            row = sheet.getRow(i);
            Cell execution = row.getCell(1);
            if (execution.getStringCellValue().equalsIgnoreCase("Yes")) {
                Cell columnHeader = sheet.getRow(0).getCell(1);
                Cell rowValue = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

                String cellValue = findCelltype(rowValue);
                if (cellValue == null) {
                    testcaseID.add("");
                } else
                    testcaseID.add(cellValue);
            }
        }

        int size = testcaseID.size();
        Object[][] data = new Object[size][1];

        for(int i=0; i<size; i++) {
            data[i][0] = testcaseID.get(i);
            System.out.println(data[i][0]);
        }

        //System.out.println(data);

//        return testCaseIds.stream().map(id -> new Object[]{id}).toArray(Object[][]::new);
        return data;
    }

    private static void getSheet() throws IOException {
        LinkedList<String> sheetName = new LinkedList<>();
        File file = new File(excelPath);
        FileInputStream inputStream = new FileInputStream(excelPath);

        Workbook workbook = WorkbookFactory.create(new File(excelPath));
        Sheet sheet = workbook.getSheet("Main");
        Row row = null;
        int lastRow = sheet.getLastRowNum();

        for (int i = 0; i < lastRow; i++) {
            row = sheet.getRow(i);
            Cell sheetCell = row.getCell(2);

            if (sheetCell.getStringCellValue().equalsIgnoreCase("Y")) {
                Cell rowValue = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                sheetName.add(String.valueOf(rowValue));
                System.out.println(sheetName);
            }
        }
    }

    private static String findCelltype(Cell cell) {
        if (cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue();
        } else if (cell.getCellType() == CellType.NUMERIC) {
            if (DateUtil.isCellDateFormatted(cell)) {
                return String.valueOf(cell.getDateCellValue());
            }
            return String.valueOf(cell.getNumericCellValue());
        } else if (cell.getCellType() == CellType.FORMULA) {
            return String.valueOf(cell.getCellFormula());
        } else if (cell.getCellType() == CellType.BLANK) {
            return null;
        }
        return null;
    }

    public LinkedHashMap<String, String> readRowdata(String testCaseID) throws IOException {

        LinkedHashMap<String, String> rowData = new LinkedHashMap<>();
        File file = new File(excelPath);
        FileInputStream inputStream = new FileInputStream(excelPath);
        if (file.exists()) {
            System.out.println("Exists");
        }

        Workbook workbook = WorkbookFactory.create(inputStream);
        Sheet sheet = workbook.getSheet("Sheet1");

        int lastRow = sheet.getLastRowNum();
        int lastCol = sheet.getRow(0).getLastCellNum();
        Row row = null;

        for (int i = 0; i < lastRow; i++) {
            row = sheet.getRow(i);
            Cell execution = row.getCell(1);
            Cell testID = row.getCell(0);
            String testCase = findCelltype(testID);
            if (execution.getStringCellValue().equalsIgnoreCase("Yes")&&testCase.equalsIgnoreCase(testCaseID)) {
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    Cell columnHeader = sheet.getRow(0).getCell(j);
                    Cell rowValue = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    String cellValue = findCelltype(rowValue);
                    if (cellValue == null) {
                        rowData.put(columnHeader.getStringCellValue(), "");
                    } else
                        rowData.put(columnHeader.getStringCellValue(), cellValue);
                }
                System.out.println(rowData);
                break;
            }

        }
        return rowData;

    }

}





