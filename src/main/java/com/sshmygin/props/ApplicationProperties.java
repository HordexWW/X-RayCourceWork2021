package com.sshmygin.props;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;

public class ApplicationProperties {
    protected static FileInputStream fileInputStream;
    protected static Properties PROPERTIES;
    static {
        try (FileInputStream fis = new FileInputStream("src/main/resources/application.properties")){
            fileInputStream = fis;
            PROPERTIES = new Properties();
            PROPERTIES.load(new InputStreamReader(fileInputStream, Charset.forName("UTF-8")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return PROPERTIES.getProperty(key);
    }
}
