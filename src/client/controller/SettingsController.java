package client.controller;

import java.util.Map;

import settings.SettingType;

public interface SettingsController {

	void saveSettings(Map<SettingType, String> settings);

	Map<SettingType, String> getSettings();
}
