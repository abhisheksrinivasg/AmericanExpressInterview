package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadProperty {


    public static Properties readFile(String fileName) throws IOException {
        Properties prop = new Properties();
        try(FileInputStream fis = new FileInputStream(fileName)) {

            prop.load(fis);
        } catch(FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        } finally {
        }
        return prop;
    }

    public static String getProperty(String value) throws IOException {
        Properties prop = readFile(System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties");
        System.out.println(System.getProperty("user.dir"));
        String configValue = prop.getProperty(value);
        return configValue;
    }
}

