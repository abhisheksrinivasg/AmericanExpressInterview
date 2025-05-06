package Utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class TestData {

    @DataProvider(parallel = false)
        public Object[][] testcaseID() throws IOException {
            ExcelRead excelRead = new ExcelRead();
            System.out.println(excelRead.readExecutionstatus());
            return  excelRead.readExecutionstatus();
        }
}
