package ui.pages;

import Utilities.DriverFactory;
import Utilities.ExcelRead;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.LinkedHashMap;

public class BasePage {
//create constructor for base page

   public  WebDriver driver;
   private static final ThreadLocal<LinkedHashMap<String,String>> rowData  = new ThreadLocal<>();

    public BasePage()
    {
        this.driver = DriverFactory.getDriver();
    }

    public static void loadTestdata(String testCaseID)
    {
        ExcelRead excelRead = new ExcelRead();
        try {
            rowData.set(excelRead.readRowdata(testCaseID));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public LinkedHashMap<String,String> getTestdata()
    {
        return rowData.get();
    }


}
