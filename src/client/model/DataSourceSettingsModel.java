package client.model;

import java.util.Map;

import settings.DataSourceSettingAccessor;
import settings.SettingsAccessor;
import settings.SettingType;

public class DataSourceSettingsModel implements SettingsModel {

	private SettingsAccessor accessSettings;

	public DataSourceSettingsModel() {
		accessSettings = new DataSourceSettingAccessor();
	}

	@Override
	public void setSettings(Map<SettingType, String> settings) {
		accessSettings.setSettings(settings);
	}

	@Override
	public Map<SettingType, String> getSettings() {
		return accessSettings.getSettings();
	}
}
