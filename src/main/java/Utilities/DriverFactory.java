package Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class DriverFactory {


    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    public static void setDriver(String browser) throws IOException {
        String remote = ReadProperty.getProperty("browser");
        String headless = ReadProperty.getProperty("headless");
        String path = System.getProperty("user.dir")+"\\src\\test\\resources\\Drivers\\chromedriver.exe";
        System.out.println(path);
        WebDriver remoteWebDriver = null;
        if (remote.contentEquals("true")) {
            System.out.println("LambdaTest");
            //Here need to give Lambdatest code;
        } else {
            switch (browser) {
                case "chrome":
                    try{
                        //System.setProperty("webdriver.chrome.driver", path);
                       // remoteWebDriver = new ChromeDriver();
                        if(headless.contentEquals("true"))
                        {
                            ChromeOptions options = new ChromeOptions();
                            //options.addArguments("--headless");
                            options.addArguments("--disable-popup-blocking");
                            remoteWebDriver = new ChromeDriver(options);
                        }
                        else
                        {
                            remoteWebDriver = new ChromeDriver();
                        }
                    } catch (Exception e) {
                        remoteWebDriver = new ChromeDriver();
                    }
                    break;
                case "firefox":
                    remoteWebDriver = new FirefoxDriver();
                    break;
                case "edge":
                    remoteWebDriver = new EdgeDriver();
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + browser);
            }
            driver.set(remoteWebDriver);
            prepareBrowser();
        }
    }

    private  static void prepareBrowser()
    {
        getDriver().manage().window().maximize();
        getDriver().manage().deleteAllCookies();
        getDriver().manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        getDriver().get("https://www.americanexpress.com/fr-fr/?inav=NavLogo");
    }
    public static WebDriver getDriver()
    {
        return (driver.get());
    }


    public static void closeDriver()
    {
        getDriver().close();
        getDriver().quit();
    }
}
