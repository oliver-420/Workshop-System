package at.htl.workshopsystem;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Properties;

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
