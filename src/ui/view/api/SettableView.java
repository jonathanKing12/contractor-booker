package ui.view.api;

import java.util.Map;
import java.util.Set;

import settings.SettingType;

public interface SettableView {

    void displayErrorMessage(String errorMessage, String title);

    /**
     * @return
     */
    Map<SettingType, String> loadSettings();

    /**
     * @param settings
     * @return
     */
    boolean isAllSettingsMissing(Set<SettingType> settings);

    /**
     * @param settings
     */
    void saveSettings(Map<SettingType, String> settings);
}
