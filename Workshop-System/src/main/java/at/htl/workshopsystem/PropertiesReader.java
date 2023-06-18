package at.htl.workshopsystem;

import java.io.Console;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * The class Properties reader.
 */
public final class PropertiesReader {


    /**
     * Constant PROPERTIES.
     */
    private static final Properties PROPERTIES;

    /**
     * Constant PROP_FILE.
     */
    private static final String PROP_FILE = "apikey.properties";

    /**
     * Default private constructor PropertiesReader.
     */
    private PropertiesReader() {
    }

    static {
        PROPERTIES = new Properties();
        final URL props = PropertiesReader.class.getResource(PROP_FILE);
        final Path path = Path.of("api/" + PROP_FILE);
        try {
            PROPERTIES.load(new FileReader(path.toFile()));
        } catch (IOException ex) {
            System.out.println("Could not load properties file: " + PROP_FILE);
        }
    }

    /**
     * Method getProperty.
     *
     * @param name String name file.
     * @return Return property
     */
    public static String getProperty(final String name) {

        return PROPERTIES.getProperty(name);
    }
}
