package settings;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class AccessDataSourceSetting implements AccessSettings {

	private static final String propertiesFileName = "suncertify.properties";
	private static final String CURRENT_WORKING_DIRECTORY = ".";
	private DataSourceSettingsReader dataSourceReader;
	private DataSourceSettingsWriter dataSourceWriter;
	private File propertiesFile;

	public AccessDataSourceSetting() {
		propertiesFile = getPropertiesFile();
		dataSourceReader = new DataSourceSettingsReader(propertiesFile);
		dataSourceWriter = new DataSourceSettingsWriter(propertiesFile);
	}

	private File getPropertiesFile() {
		Path workingDirectory = Paths.get(CURRENT_WORKING_DIRECTORY);
		Path propertiesPath = workingDirectory.resolve(propertiesFileName);
		return propertiesPath.toFile();
	}

	@Override
	public void setSettings(Map<SettingType, String> updatedSettings) throws SettingException {
		Map<SettingType, String> allSettings = combineWithSavedSettings(updatedSettings);
		dataSourceWriter.writeSettings(allSettings);
	}

	@Override
	public Map<SettingType, String> getSettings() throws SettingException {
		return dataSourceReader.getSettings();
	}

	private Map<SettingType, String> combineWithSavedSettings(Map<SettingType, String> updatedSettings) {
		Map<SettingType, String> settings = getSettings();
		settings.putAll(updatedSettings);
		return settings;
	}

	public static void main(String[] args) throws IOException {
		AccessDataSourceSetting test = new AccessDataSourceSetting();
		System.out.println(test.getSettings());
	}
}
