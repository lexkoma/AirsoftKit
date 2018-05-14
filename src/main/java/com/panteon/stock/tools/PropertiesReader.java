package com.panteon.stock.tools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {
    private static Properties properties;

    static {
        properties = new Properties();
        try {
            properties.load(PropertiesReader.class.getClassLoader().getResourceAsStream("myProperties.properties"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getPropertyFromFile(String name) {
        if (properties.containsKey(name)) {
            return properties.getProperty(name);
        }
        throw new RuntimeException("Property key not found");
    }
}
