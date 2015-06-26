package gui.controller.api;

import java.util.Map;
import java.util.Set;

import settings.SettingType;

public interface SettableController {

	void saveSettings(Map<SettingType, String> settings);

	Map<SettingType, String> getSettings();

	void displaySettingsDialogIfHasNotSettingsOfTypes(Set<SettingType> settings);

}