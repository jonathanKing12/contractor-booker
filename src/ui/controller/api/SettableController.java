package ui.controller.api;

import java.util.Map;
import java.util.Set;

import settings.SettingType;

/**
 * Represents a Controller for handling settings.
 */
public interface SettableController {

    /**
     * Constructs a instance of SettingsController with the specified settingsView
     * 
     * @param settingsView
     *            - the settingsView
     */
    void saveSettings(Map<SettingType, String> settings);

    /**
     * Returns settings from the model.
     * 
     * @return the settings
     */
    Map<SettingType, String> loadSettings();

    /**
     * Returns {@code true} if all of the specified settingsTypes are missing from the model
     * 
     * @param settingsTypes
     *            - the settingsTypes
     * @return {@code true} if all the settingsTypes are missing from the model
     */
    boolean areAllSettingsNotSaved(Set<SettingType> settings);
}
