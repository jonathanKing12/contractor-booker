package settings;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads and Writes the properties to and from the properties file.
 */
public class PropertiesReadWriter {

    private static final String propertiesFileName = "suncertify.properties";
    private static final String CURRENT_WORKING_DIRECTORY = ".";
    private File propertiesFile;

    /**
     * Constructs a instance of PropertiesReadWriteStream.
     */
    PropertiesReadWriter() {
        propertiesFile = getPropertiesFile();
    }

    /**
     * Writes the specified properties to the properties file.
     * 
     * @param properties
     *            - the properties
     * @throws IOException
     *             if a IOException occurs
     */
    void writeProperties(List<String> properties) throws IOException {

        synchronized (PropertiesReadWriter.class) {
            try (PrintWriter writer = getPrintWriter()) {
                writeProperties(writer, properties);
            }
        }
    }

    /**
     * Returns the properties from the properties file.
     * 
     * @return the properties
     * 
     * @throws IOException
     *             if a IOException occurs
     */
    List<String> readProperties() throws IOException {

        synchronized (PropertiesReadWriter.class) {
            try (BufferedReader reader = getBufferedReader()) {
                return readProperties(reader);
            }
        }
    }

    private File getPropertiesFile() {
        Path workingDirectory = Paths.get(CURRENT_WORKING_DIRECTORY);
        Path propertiesFilePath = workingDirectory.resolve(propertiesFileName);
        return propertiesFilePath.toFile();
    }

    private PrintWriter getPrintWriter() throws IOException {
        return new PrintWriter(new FileWriter(propertiesFile));
    }

    private void writeProperties(PrintWriter writer, List<String> properties) throws IOException {
        for (String property : properties) {
            writer.println(property);
        }
    }

    private BufferedReader getBufferedReader() throws FileNotFoundException {
        return new BufferedReader(new FileReader(propertiesFile));
    }

    private List<String> readProperties(BufferedReader reader) throws IOException {
        List<String> properties = new ArrayList<>();

        String property;
        while ((property = reader.readLine()) != null) {
            properties.add(property);
        }
        return properties;
    }
}
