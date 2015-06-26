package settings;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class SettingsAccessor implements SettableAccessor {

	private PropertiesReadWriteStream propertiesReadWriteStream;
	private SettingsParser settingsParser;

	public SettingsAccessor() {
		propertiesReadWriteStream = new PropertiesReadWriteStream();
		settingsParser = new SettingsParser();
	}

	@Override
	public void setSettings(Map<SettingType, String> updatedSettings) throws SettingException {
		Map<SettingType, String> allSettings = combineWithSavedSettings(updatedSettings);
		List<String> propeties = settingsParser.convertToProperties(allSettings);
		writeProperties(propeties);
	}

	@Override
	public Map<SettingType, String> getSettings() throws SettingException {
		List<String> properties = readProperties();
		return settingsParser.convertToSettings(properties);
	}

	private Map<SettingType, String> combineWithSavedSettings(Map<SettingType, String> updatedSettings) {
		Map<SettingType, String> settings = getSettings();
		settings.putAll(updatedSettings);
		return settings;
	}

	private List<String> readProperties() throws SettingException {
		try {
			return propertiesReadWriteStream.readProperties();
		} catch (IOException e) {
			String writeFormat = createWriteFormat();
			String errorMessage = formatErrorMessage(writeFormat, e);
			throw new SettingException(errorMessage);
		}
	}

	private void writeProperties(List<String> lines) throws SettingException {
		try {
			propertiesReadWriteStream.writeProperties(lines);
		} catch (IOException e) {
			String format = createReadFormat();
			String errorMessage = formatErrorMessage(format, e);
			throw new SettingException(errorMessage);
		}
	}

	private String createReadFormat() {
		return " error when reading settings from property file\n %s \n %s";
	}

	private String createWriteFormat() {
		return " error when saving settings from property file \n %s \n %s";
	}

	private String formatErrorMessage(String message, IOException e) {
		String suppressedMessage = createSuppressedMessage(e);
		return String.format(message, e.getMessage(), suppressedMessage);
	}

	private String createSuppressedMessage(IOException e) {
		StringBuilder builder = new StringBuilder();
		for (Throwable suppressedException : e.getSuppressed()) {
			builder.append(suppressedException.getMessage());
			builder.append("\n");
		}
		return builder.toString();
	}
}
