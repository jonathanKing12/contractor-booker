package client.controller;

import java.util.Map;

import setting.SettingType;

public interface SettingsController {

	void saveSettings(Map<SettingType, String> settings);

	Map<SettingType, String> getSettings();
}
