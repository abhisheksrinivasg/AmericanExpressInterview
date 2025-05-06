package listeners;

import Utilities.CommonMethods;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class ITestListeners implements ITestListener {

    CommonMethods commonMethods = new CommonMethods();


    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
        commonMethods.logMessage(result.getName() + " Logging Starts");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ITestListener.super.onTestSuccess(result);
        System.out.println(Reporter.getOutput());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
    }

    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
        commonMethods.logMessage(context.getName() + " Logging Ends");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test failed: " + result.getName());
        commonMethods.getScreenshot( result.getName());
        System.out.println(Reporter.getOutput());

       // LogWrite.log(String.valueOf(Reporter.getOutput()));


    }
}
