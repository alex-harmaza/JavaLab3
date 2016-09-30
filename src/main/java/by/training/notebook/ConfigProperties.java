package by.training.notebook;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectStreamException;
import java.util.Properties;

/**
 * Created by alexh on 29.09.2016.
 */
public class ConfigProperties {

    private final static String PROPERTY_FILE_NAME = "config.properties";

    private static ConfigProperties ourInstance = new ConfigProperties();

    private Properties properties;


    public static ConfigProperties getInstance() {
        return ourInstance;
    }


    private ConfigProperties() {
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
