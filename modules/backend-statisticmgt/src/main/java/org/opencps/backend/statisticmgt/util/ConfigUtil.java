package org.opencps.backend.statisticmgt.util;

import java.io.InputStream;
import java.util.Properties;
import java.io.IOException;
public class ConfigUtil {
	public static String readKey(String key) {
        Properties prop = new Properties();
        InputStream input = null;
        try {
            String filename = "configuration.properties";
            input = ConfigUtil.class.getClassLoader().getResourceAsStream(filename);
            if (input == null) {
                System.out.println("Sorry, unable to find " + filename);
                return "";
            }

            // load a properties file from class path, inside static method
            prop.load(input);

            return prop.getProperty(key);


        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;

    }
}
