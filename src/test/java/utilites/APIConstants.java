package utilites;

import java.util.Properties;

public class APIConstants {
    public static final String BASE_URL= ConfigReader.getProperty("baseurl");
    public static final String ENDPOINT=ConfigReader.getProperty("petendpoint");
    public static final String CONTENT_TYPE=ConfigReader.getProperty("contentType");
}
