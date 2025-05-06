package ui.pages;

import Utilities.CommonMethods;
import Utilities.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import ui.pages.locators.HomePageLocators;

import java.time.Duration;
import java.util.LinkedHashMap;


public class HomePage extends BasePage{
    CommonMethods commonMethods;
    HomePageLocators homePageLocators;

    public LinkedHashMap<String,String> testData;


    public HomePage() {
        super();
        commonMethods = new CommonMethods();
        homePageLocators = new HomePageLocators();
        PageFactory.initElements(driver,homePageLocators);
        testData = getTestdata();
    }

    public void closePopup()
    {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5)); // Short wait
                WebElement cookieAcceptBtn = shortWait.until(
                        ExpectedConditions.elementToBeClickable(homePageLocators.toutAccepter));
                cookieAcceptBtn.click();
                System.out.println("Cookie popup handled.");
                //commonMethods.click(homePageLocators.toutAccepter, driver,"Closing PopUp");
            } catch (Exception e) {

            }}

            public void login () throws InterruptedException {
                String expectedTitle = "American Express - CARTE GOLD AMERICAN EXPRESS";
                String actualTitle = driver.getTitle();
                Assert.assertEquals(actualTitle, expectedTitle, "Title does not match");
                commonMethods.click(homePageLocators.devenirClient, driver, "Clicking on DenverClient");
                closePopup();
                commonMethods.click(homePageLocators.lesCartesAmericanExpress, driver, "Clicking on American Express");
                closePopup();
                commonMethods.click(homePageLocators.enSavoirPlus, driver, "Clicking on En Savoir Plus");
                closePopup();
                commonMethods.click(homePageLocators.demandezVorte, driver, "Clicking on Demandez Vorte");
                closePopup();
                Thread.sleep(3000);
            }
            public void fillData()
            {
                if(testData.get("Civilite")=="MR")
                {
                    commonMethods.click(homePageLocators.civilite_MR,driver,"Clicked on MR");
                }
                else
                {
                    commonMethods.click(homePageLocators.civilite_MS,driver,"Clicked on MS");
                }
                commonMethods.scrollIntoElement(homePageLocators.firstName, "FirstName");
                commonMethods.sendKeys(homePageLocators.firstName, testData.get("FirstName"));
                commonMethods.sendKeys(homePageLocators.lastName, testData.get("LastName"));
                commonMethods.sendKeys(homePageLocators.dateofBirth, testData.get("DateofBirth"));
                commonMethods.sendKeys(homePageLocators.email, testData.get("Email"));
                commonMethods.sendKeys(homePageLocators.mobile,testData.get("Mobile"));
                commonMethods.click(homePageLocators.submit, driver, "Clicked on Submit");
            }

            public void verify()
            {
                String error = homePageLocators.alert.getText();
                Assert.assertEquals(error,testData.get("FirstNameError"));
            }

        }


