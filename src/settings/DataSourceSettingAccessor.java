package settings;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class DataSourceSettingAccessor implements SettingsAccessor {

	private static final String propertiesFileName = "suncertify.properties";
	private static final String CURRENT_WORKING_DIRECTORY = ".";
	private PropertiesFileReader propertiesFileReader;
	private PropertiesFileWriter propertiesFileWriter;
	private File propertiesFile;

	public DataSourceSettingAccessor() {
		propertiesFile = getPropertiesFile();
		propertiesFileReader = new PropertiesFileReader(propertiesFile);
		propertiesFileWriter = new PropertiesFileWriter(propertiesFile);
	}

	@Override
	public void setSettings(Map<SettingType, String> updatedSettings) throws SettingException {
		Map<SettingType, String> allSettings = combineWithSavedSettings(updatedSettings);
		List<String> lines = propertiesFileWriter.convertToLines(allSettings);
		writeLines(lines);
	}

	@Override
	public Map<SettingType, String> getSettings() throws SettingException {
		List<String> lines = getLinesFromPropertiesFile();
		return propertiesFileReader.convertToSettings(lines);
	}
	
	private File getPropertiesFile() {
		Path workingDirectory = Paths.get(CURRENT_WORKING_DIRECTORY);
		Path propertiesPath = workingDirectory.resolve(propertiesFileName);
		return propertiesPath.toFile();
	}

	private Map<SettingType, String> combineWithSavedSettings(Map<SettingType, String> updatedSettings) {
		Map<SettingType, String> settings = getSettings();
		settings.putAll(updatedSettings);
		return settings;
	}
	
	private List<String> getLinesFromPropertiesFile() {	
		synchronized(AccessDataSourceSetting.class){
			return propertiesFileReader.readLines();
		}
	}
	
	private void writeLines(List<String> lines) {
		synchronized(AccessDataSourceSetting.class){
			propertiesFileWriter.writeLines(lines);
		}
	}
}
