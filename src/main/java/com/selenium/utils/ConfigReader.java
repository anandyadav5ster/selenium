package com.selenium.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
    static Properties prop;


public static String get(String key) {
if (prop == null) {
try {
prop = new Properties();
prop.load(new FileInputStream("config.properties"));
} catch (Exception e) {
e.printStackTrace();
}
}
return prop.getProperty(key);
}
}
