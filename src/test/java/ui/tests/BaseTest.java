package ui.tests;

import Utilities.DriverFactory;
import Utilities.LogWrite;
import Utilities.ReadProperty;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;

public class BaseTest {

    LinkedHashMap<String,String> rowData = new LinkedHashMap<>();

    @BeforeMethod
    public void openBrowser() throws IOException
    {
        String remote = ReadProperty.getProperty("browser");
        DriverFactory.setDriver(remote);
    }
    @AfterTest
    public void generateReport(ITestContext Testcontext) throws IOException, InterruptedException {
        String resultFolder = System.getProperty("user.dir")+"\\target\\allure-results";
        String timestamp = new SimpleDateFormat("dd-MM-YYY-hh-mm-ss").format(new Date());
        String outputFolder = System.getProperty("user.dir")+"\\target\\allure-reports\\"+Testcontext.getName()+" "+timestamp;
        System.out.println();
        Process p = Runtime.getRuntime().exec("C:\\Abhishek\\Softwares\\allure-2.33.0\\allure-2.33.0\\bin\\allure.bat generate --single-file "+resultFolder+" -o "+outputFolder);
        int exitVal = p.waitFor();
        System.out.println("C:\\Abhishek\\Softwares\\allure-2.33.0\\allure-2.33.0\\bin\\allure.bat generate --single-file "+resultFolder+" -o "+outputFolder);
        System.out.println("C:\\Abhishek\\Softwares\\allure-2.33.0\\allure-2.33.0\\bin\\allure.bat generate --single-file "+resultFolder+"-o "+outputFolder);
        if(exitVal==0)
        {
            System.out.println("Allure Report is successfull");
        }
        else
        {
            System.out.println("Failure");
        }
       // Runtime.getRuntime().exec("C:\\Abhishek\\Softwares\\allure-2.33.0\\allure-2.33.0\\bin\\allure.bat generate --single-file");
    }
@AfterMethod
    public void closeBrowser()
    {
       // DriverFactory.getDriver().quit();
    }
}
