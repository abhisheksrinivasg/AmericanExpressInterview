package ui.pages.locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePageLocators {
@FindBy(xpath = "//button[@id='user-consent-management-granular-banner-accept-all-button']")
    public WebElement toutAccepter;
@FindBy(xpath = "//label[@id='label-tab-open-cards']")
    public WebElement devenirClient;
@FindBy(xpath = "(//label[@id='label-tab-open-cards']/following-sibling::div//ul//li[3])[1]")
    public WebElement lesCartesAmericanExpress;
@FindBy(xpath = "//a[contains(@href,'gold-card')][starts-with(@class,'btn btncomp links-item')]")
    public WebElement enSavoirPlus;
@FindBy(xpath = "(//a[contains(@href,'amex-cardshop-')][starts-with(@class,'sc_at_button_btn')])[1]")
    public WebElement demandezVorte;
@FindBy(xpath = "//label[@for='MR']")
    public WebElement civilite_MR;
    @FindBy(xpath = "//label[@for='MS']")
    public WebElement civilite_MS;
@FindBy(xpath = "//input[@name='firstName']")
    public WebElement firstName;
@FindBy(name = "lastName")
    public WebElement lastName;
@FindBy(name="fieldControl-input-dateOfBirth")
    public WebElement dateofBirth;
@FindBy(name="email")
    public WebElement email;
@FindBy(name="mobilePhoneNumber")
    public WebElement mobile;
@FindBy(xpath = "//button[@type='submit']")
    public WebElement submit;





}
