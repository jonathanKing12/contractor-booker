package ui.model.settings;

import java.util.Map;
import java.util.Set;

import settings.SettingType;
import settings.SettingsException;

/**
 * Represents a Model for handling settings.
 */
public interface SettableModel {

    /**
     * Returns {@code true} if all of the specified settings are not saved in the model.
     * 
     * @param settingTypes
     *            - the types of the specified settings
     * @return {@code true} if all of the specified settings are not saved in the model.
     * @throws SettingsException
     *             if a error occurs loading while verifying if the settings are saved.
     */
    boolean areAllSettingsNotSaved(Set<SettingType> settings) throws SettingsException;

    /**
     * Saves the specified settings.
     * 
     * @param settings
     *            - the settings
     * @throws SettingsException
     *             if the settings cannot be saved
     */
    void saveSettings(Map<SettingType, String> settings) throws SettingsException;

    /**
     * Loads the settings.
     * 
     * @return the settings
     * @throws SettingsException
     *             if the settings cannot be loaded
     */
    Map<SettingType, String> loadSettings() throws SettingsException;

}
