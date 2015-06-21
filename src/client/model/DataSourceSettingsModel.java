package client.model;

import java.util.Map;

import settings.AccessDataSourceSetting;
import settings.AccessSettings;
import settings.SettingType;

public class DataSourceSettingsModel implements SettingsModel {

	private AccessSettings accessSettings;

	public DataSourceSettingsModel() {
		accessSettings = new AccessDataSourceSetting();
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
