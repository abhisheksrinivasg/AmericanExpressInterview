package Utilities;

import com.google.common.cache.Weigher;
import io.qameta.allure.Allure;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Supplier;


public class CommonMethods {

    JavascriptExecutor js = (JavascriptExecutor)DriverFactory.getDriver();

    public <T> T executeSafely(Supplier<T> supplier, String methodName)
    {
        try{
            supplier.get();
        }
        catch (NoSuchElementException e)
        {
            logMessage("Element is not found" + methodName);
            throw e;
        }
        catch(TimeoutException e)
        {
            logMessage("Timeout exception" + methodName);
            throw e;
        }
        return null;
    }
    public void waitForElement_tobeDisplayed(WebElement element, WebDriver driver, long time, String elementName)
    {
        executeSafely(()->{
            logMessage("Waiting for the " +elementName+" element to become visible before applying the wait");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
            wait.until(ExpectedConditions.visibilityOf(element));
            logMessage("Element is now visible");
            return null;
        }
        ,elementName);
    }
    public void waitForElement_tobeClickable(WebElement element, WebDriver driver, long time, String elementName)
    {
        executeSafely(()->{
                    logMessage("Waiting for the element to become clickable before applying the wait");
                    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
                    wait.until(ExpectedConditions.elementToBeClickable(element));
                    logMessage("Element is now clickable");
                    return null;
                }
                ,elementName);

    }

    public List<WebElement> waitForElement_visibilityOfallElements(WebElement element,WebDriver driver,long time, String elementName)
    {
        return executeSafely(()->{
                    logMessage("Waiting for all elements to load before applying the wait");
                    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
                    List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElements(element));
                    logMessage("All elements is loaded");
                    return options;
                }
                ,elementName);
    }
    public String getElementText(WebElement element,WebDriver driver,String elementName)
    {
        waitForElement_tobeDisplayed(element,driver,30,elementName);
        String text = element.getText();
        logMessage(elementName+" Element Text is taken ");
        return text;
    }

    public String getElementAttribute(WebElement element,WebDriver driver, String elementName,String attributeType)
    {
        waitForElement_tobeDisplayed(element,driver,30,elementName);
        String attribute = element.getAttribute(attributeType);
        logMessage(elementName+" Element Attribute is taken");
        return attribute;
    }

    public void clearText(WebElement element,WebDriver driver,String elementName)
    {
        waitForElement_tobeDisplayed(element,driver,30,elementName);
        element.clear();
        logMessage("Textbox is Cleared");

    }
    public void click(WebElement element,WebDriver driver,String elementName)
    {
        waitForElement_tobeDisplayed(element,driver,30,elementName);
        waitForElement_tobeClickable(element,driver,30,elementName);
        element.click();
        logMessage("Element " +elementName +" is clicked");
    }

    public void selectDropdown(WebElement element,int index)
    {
        Select select = new Select(element);
        select.selectByIndex(index);
        logMessage("Element is Selected using Index from the Dropdown");
    }

    public void selectDropdown(WebElement element,String value, boolean isValue)
    {
        Select select = new Select(element);
        select.selectByValue(value);
        logMessage("Element is Selected using Value from the Dropdown");
    }

    public void selectDropdown(WebElement element,String visibleText)
    {
        Select select = new Select(element);
        select.selectByVisibleText(visibleText);
        logMessage("Element is Selected using VisibleText from the Dropdown");
    }

    public void dropDownSelection(WebElement dropdownLocator,WebDriver driver,WebElement optionLocator,String value)
    {
        waitForElement_tobeClickable(dropdownLocator,driver,30,value);
        dropdownLocator.click();
        waitForElement_visibilityOfallElements(optionLocator,driver,30,value);
    }


    public void logMessage(String message) {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        Reporter.log(timestamp + " -- " + message);
    }

    public void getScreenshot(String screenshotName)
    {
        TakesScreenshot sc = (TakesScreenshot)DriverFactory.getDriver();
        byte[] screenshot = sc.getScreenshotAs(OutputType.BYTES);
        Allure.addAttachment(screenshotName,new ByteArrayInputStream(screenshot));
    }

    public void sendKeys(WebElement element,String value)
    {
            element.clear();
            element.sendKeys(value);
            logMessage(value + " is sent");
    }

    public void scrollBottomPage()
    {
        js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
        logMessage("Scolled to End of the Page");
    }

    public void scrollIntoElement(WebElement element,String elementName)
    {
        js.executeScript("arguments[0].scrollIntoView();",element);
        logMessage("Scrolled till the element "+elementName);
    }

    public boolean isVisible(WebElement element,String elementName)
    {
        boolean visible = element.isDisplayed();
        logMessage("Element is " +elementName+ "visible");
        return visible;

    }
}
