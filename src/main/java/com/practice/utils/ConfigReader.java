package com.practice.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
    public static String getProperty(String key) {
        Properties prop = new Properties();
        try {
            FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
            prop.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prop.getProperty(key);
    }
}