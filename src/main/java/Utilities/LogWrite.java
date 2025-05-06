package Utilities;

import org.testng.Reporter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class LogWrite {
    static final String logPath = System.getProperty("user.dir") + "\\src\\test\\resources\\LogFile.txt";

    public static void log(List<String> message) {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        //String logMessage = timestamp + " - " + message;

       // writeToFile(logMessage);
    }
    public static void writeToFile(List<String> message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logPath, true))) {
            for (String log : message) {
                writer.write(log);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
