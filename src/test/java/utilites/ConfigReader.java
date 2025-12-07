package utilites;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    public static Properties properties = new Properties();
    public  static InputStream input ;

    static {
        try {
            String path = "src/test/resources/Config.properties";
            input = new FileInputStream(path);
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
