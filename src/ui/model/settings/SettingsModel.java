package ui.model.settings;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import java.util.Map;
import java.util.Set;

import settings.*;

/**
 * A model for handling settings.
 *
 */
public class SettingsModel implements SettableModel {

    private SettingsService settingsService;

    /**
     * Constructs a instance of SettingsModel
     */
    public SettingsModel() {
        settingsService = new SettingsFacade();
    }

    /**
     * Returns {@code true} if all of the specified settings are not saved in the model.
     * 
     * @param settingTypes
     *            - the types of the specified settings
     * @return {@code true} if all of the specified settings are not saved in the model.
     * @throws SettingsException
     *             if a error occurs loading while verifying if the settings are saved.
     */
    @Override
    public boolean areAllSettingsNotSaved(Set<SettingType> settingTypes) throws SettingsException {

        boolean allSettingsMissing = TRUE;
        Map<SettingType, String> settings = loadSettings();

        for (SettingType settingType : settingTypes) {
            if (hasSettingOfType(settings, settingType)) {
                allSettingsMissing = FALSE;
                break;
            }
        }
        return allSettingsMissing;
    }

    /**
     * Saves the specified settings.
     * <p>
     * Delegates to {@link SettingsService#saveSettings(settings)}
     * </p>
     * 
     * @param settings
     *            - the settings
     * @throws SettingsException
     *             if the settings cannot be saved
     */
    @Override
    public void saveSettings(Map<SettingType, String> settings) throws SettingsException {
        settingsService.saveSettings(settings);
    }

    /**
     * Loads the settings.
     * <p>
     * Delegates to {@link SettingsService#loadSettings()}
     * </p>
     * 
     * @return the settings
     * @throws SettingsException
     *             if the settings cannot be loaded
     */
    @Override
    public Map<SettingType, String> loadSettings() throws SettingsException {
        return settingsService.loadSettings();
    }

    private boolean hasSettingOfType(Map<SettingType, String> settings, SettingType settingType) {
        String settingValue = settings.get(settingType);
        return !settingValue.isEmpty();
    }
}
