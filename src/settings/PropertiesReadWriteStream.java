package settings;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PropertiesReadWriteStream {

	private static final String propertiesFileName = "suncertify.properties";
	private static final String CURRENT_WORKING_DIRECTORY = ".";
	private File propertiesFile;

	PropertiesReadWriteStream() {
		propertiesFile = getPropertiesFile();
	}

	void writeProperties(List<String> properties) throws IOException {
		synchronized (PropertiesReadWriteStream.class) {

			try (PrintWriter writer = getPrintWriter()) {
				writeProperties(writer, properties);
			}
		}
	}

	List<String> readProperties() throws IOException {
		synchronized (PropertiesReadWriteStream.class) {

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
