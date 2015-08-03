package settings;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Converts the properties to settings and the settings to properties before saved/loaded to the properties file.
 */
public class SettingsFacade implements SettingsService {

    private PropertiesReadWriter propertiesReadWriteStream;
    private SettingsParser settingsParser;

    /**
     * Constructs a instance of SettingsFacade.
     */
    public SettingsFacade() {
        propertiesReadWriteStream = new PropertiesReadWriter();
        settingsParser = new SettingsParser();
    }

    /**
     * Writes the specified settings to the properties file.
     * 
     * @param settings
     *            - the settings
     * @throws SettingsException
     *             if failed to write settings to properties file
     */
    @Override
    public void saveSettings(Map<SettingType, String> updatedSettings) throws SettingsException {
        try {
            List<String> properties = propertiesReadWriteStream.readProperties();
            Map<SettingType, String> settings = settingsParser.convertToSettings(properties);
            settings.putAll(updatedSettings);

            properties = settingsParser.convertToProperties(settings);
            propertiesReadWriteStream.writeProperties(properties);
        } catch (IOException e) {
            String errorMessage = createSetErrorMessage(e);
            throw new SettingsException(errorMessage);
        }
    }

    /**
     * Reads the all settings from the properties file.
     * 
     * @return settings - the settings
     * @throws SettingsException
     *             if failed to read the all settings from properties file
     */
    @Override
    public Map<SettingType, String> loadSettings() throws SettingsException {
        try {
            List<String> properties = propertiesReadWriteStream.readProperties();
            return settingsParser.convertToSettings(properties);
        } catch (IOException e) {
            String errorMessage = createGetErrorMessage(e);
            throw new SettingsException(errorMessage);
        }
    }

    private String createGetErrorMessage(IOException e) {
        String format = " error reading settings from property file\n %s";
        return String.format(format, e.getMessage());
    }

    private String createSetErrorMessage(IOException e) {
        String format = " error when saving settings to property file\n %s";
        return String.format(format, e.getMessage());
    }
}
