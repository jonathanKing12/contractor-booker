package client.model;

import java.util.Map;

import settings.SettingType;

public interface SettingsModel {

	void setSettings(Map<SettingType, String> settings);

	Map<SettingType, String> getSettings();
}
