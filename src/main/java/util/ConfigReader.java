package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static final Properties properties = new Properties();

    static Logger log = LoggerFactory.getLogger(ConfigReader.class);

    static {
        try {
            FileInputStream input = new FileInputStream("src/test/resources/config.properties");
            properties.load(input);
        } catch (IOException e) {
            log.info("Could not load configuration file: {}", e.getMessage());

        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}



