package com.pragmatic.sauce.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import static com.pragmatic.sauce.util.LogManager.*;

public class ConfigReader {
    private static Properties properties;

    static {
        try {
            FileInputStream file = new FileInputStream("src/test/resources/config.properties");
            properties = new Properties();
            properties.load(file);
        } catch (IOException e) {
            fatal("Configuration file read failure " + e.getMessage());
            throw new RuntimeException("Critical file loading failure. Aborting tests.");
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}