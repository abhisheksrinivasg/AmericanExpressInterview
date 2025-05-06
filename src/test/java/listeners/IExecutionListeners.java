package listeners;

import Utilities.CommonMethods;
import Utilities.LogWrite;
import org.checkerframework.checker.units.qual.C;
import org.testng.IExecutionListener;
import org.testng.Reporter;

public class IExecutionListeners implements IExecutionListener {

    CommonMethods commonMethods = new CommonMethods();
    @Override
    public void onExecutionStart() {
        IExecutionListener.super.onExecutionStart();
        commonMethods.logMessage("Execution Log Started");

    }

    @Override
    public void onExecutionFinish() {
        IExecutionListener.super.onExecutionFinish();
        commonMethods.logMessage("Execution Log Ended");
        System.out.println("LogOnExecutionFinish");
        System.out.println(Reporter.getOutput());
        LogWrite.writeToFile(Reporter.getOutput());
    }
}
