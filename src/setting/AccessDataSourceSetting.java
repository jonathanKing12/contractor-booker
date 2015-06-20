package setting;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class AccessDataSourceSetting implements AccessSetting {

	private static final AccessDataSourceSetting locator;

	private String propertiesFileName = "C:/db/suncertify.properties";
	private String propertyNameAndDilimeter = "root location:";
	private File propertiesFile;

	static {
		locator = new AccessDataSourceSetting();
	}

	public static AccessDataSourceSetting getInstance() {
		return locator;
	}

	private AccessDataSourceSetting() {
		propertiesFile = new File(propertiesFileName);
	}

	@Override
	public void setLocation(String location) throws SettingException {
		try (FileWriter writer = new FileWriter(propertiesFile)) {
			write(writer, location);
		} catch (IOException e) {
			throw new SettingException(e.getMessage());
		}
	}

	@Override
	public String getLocation() throws SettingException {
		try (BufferedReader reader = new BufferedReader(new FileReader(propertiesFile))) {
			return read(reader);
		} catch (IOException e) {
			throw new SettingException(e.getMessage());
		}
	}

	private String read(BufferedReader reader) throws IOException {
		String line = reader.readLine();
		String[] property = line.split(propertyNameAndDilimeter);
		return property[1];
	}

	private void write(FileWriter writer, String location) throws IOException {
		StringBuilder builder = new StringBuilder(propertyNameAndDilimeter);
		builder.append(location);
		String property = builder.toString();
		writer.write(property);
	}

	private Map<SettingType, String> readSettings() throws SettingException {
		List<String> lines = new ArrayList<>();

		try (BufferedReader reader = new BufferedReader(new FileReader(propertiesFile))) {
			String line;
			while ((line = reader.readLine()) != null) {
				lines.add(line);
			}

		} catch (IOException e) {
			throw new SettingException(e.getMessage());
		}
	}
}
