package ui.tests;


import Utilities.LogWrite;
import Utilities.RetryMechanism;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ui.pages.BasePage;
import ui.pages.HomePage;

import java.util.LinkedHashMap;


public class Sample extends BaseTest {
    //@Test(retryAnalyzer = RetryMechanism.class)

    //@Test(dataProvider = "testcaseID",dataProviderClass = Utilities.TestData.class)
    @Test
    public void CreditCard(String TestCaseID) throws InterruptedException {
        System.out.println(TestCaseID);
        BasePage.loadTestdata(TestCaseID);
        HomePage homePage = new HomePage();
        homePage.login();
        homePage.fillData();
    }

    @Test(dataProvider = "testcaseID",dataProviderClass = Utilities.TestData.class)
    public void CreditCardNEg(String TestCaseID) throws InterruptedException {
        System.out.println(TestCaseID);
        BasePage.loadTestdata(TestCaseID);
        HomePage homePage = new HomePage();
        homePage.login();
        homePage.fillData();
        homePage.verify();
    }
    //@Test(dataProvider = "testcaseID",dataProviderClass = Utilities.TestData.class)
}
