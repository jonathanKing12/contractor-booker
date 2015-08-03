package settings;

import java.util.Map;

/**
 * Converts the properties to settings and the settings to properties before saved/loaded to the properties file.
 */
public interface SettingsService {

    /**
     * Writes the specified settings to the properties file.
     * 
     * @param settings
     *            - the settings
     * @throws SettingsException
     *             if failed to write settings to properties file
     */
    void saveSettings(Map<SettingType, String> settings) throws SettingsException;

    /**
     * Reads the all settings from the properties file.
     * 
     * @return settings - the settings
     * @throws SettingsException
     *             if failed to read the all settings from properties file
     */
    Map<SettingType, String> loadSettings() throws SettingsException;
}
