package client.controller;

import java.util.Map;

import settings.SettingType;
import client.model.DataSourceSettingsModel;
import client.model.SettingsModel;
import client.view.View;

public class DataSourceSettingsController implements SettingsController {

	private SettingsModel model;
	private View view;

	public DataSourceSettingsController(View view) {
		model = new DataSourceSettingsModel();
		this.view = view;
	}

	@Override
	public void saveSettings(Map<SettingType, String> settings) {
		model.setSettings(settings);
	}

	@Override
	public Map<SettingType, String> getSettings() {
		return model.getSettings();
	}
}
