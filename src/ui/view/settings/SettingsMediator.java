package ui.view.settings;

import java.util.Map;
import java.util.Set;

import settings.SettingType;
import ui.controller.ControllerFactory;
import ui.controller.api.SettableController;
import ui.view.common.MessageBoxPresenter;

/**
 * Mediates Settings operations between various instances in view and the SettableController instance.
 */
public class SettingsMediator {

    private SettableController controller;

    /**
     * Constructs a instance of SettingsMediator.
     */
    public SettingsMediator() {
        ControllerFactory factory = new ControllerFactory();
        controller = factory.getSettingsController(new MessageBoxPresenter());
    }

    /**
     * Loads the settings.
     * <p>
     * Delegates to {@link SettableController#loadSettings()}
     * </p>
     * 
     * @return the settings
     */
    public Map<SettingType, String> loadSettings() {
        return controller.loadSettings();
    }

    /**
     * Saves the settings.
     * <p>
     * Delegates to {@link SettableController#saveSettings(settings)}
     * </p>
     * 
     * @param settings
     *            - the settings that are to be saved
     */
    public void saveSettings(Map<SettingType, String> settings) {
        controller.saveSettings(settings);
    }

    /**
     * Returns {@code true} if all the specified settings are not saved.
     * <p>
     * Delegates to {@link SettableController#areAllSettingsNotSaved(settings)}.
     * </p>
     * 
     * @param settingsTypes
     *            - the types of the specified settings
     * 
     * @return {@code true} if all the settings are not saved.
     */
    public boolean isAllSettingsNotSaved(Set<SettingType> settingsTypes) {
        return controller.areAllSettingsNotSaved(settingsTypes);
    }
}
