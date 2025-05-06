package Utilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryMechanism implements IRetryAnalyzer {

    private int retryCount = 0;
    private final int maxRetrycount =2;

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetrycount) {
            retryCount++;
            System.out.println("Retrying test " + result.getName() + " (Attempt " + retryCount + ")");
            return true; // Retry the test
        }
        return false; // Stop retrying after max attempts
    }
}
