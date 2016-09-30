package by.training.notebook.source;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by alexh on 29.09.2016.
 */
public class ConfigProvider {

    private final static String PROPERTY_FILE_NAME = "config.properties";

    private static ConfigProvider ourInstance = new ConfigProvider();

    private Properties properties;


    public static ConfigProvider getInstance() {
        return ourInstance;
    }


    private ConfigProvider() {
        properties = new Properties();
        try (InputStream s = this.getClass().getClassLoader()
                .getResourceAsStream(PROPERTY_FILE_NAME)) {
            properties.load(s);
        }
        catch (IOException ex){
            throw new IllegalStateException(ex);
        }
    }


    public String getProperty(String key){
        return properties.getProperty(key);
    }
}
