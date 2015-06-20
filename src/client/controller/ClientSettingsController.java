package client.controller;

import java.io.IOException;
import java.util.Map;

import setting.SettingType;
import transport.contractor.ContractorException;
import client.model.ConfigurableModel;
import client.model.ConfigurationModel;

public class ClientSettingsController implements SettingsController {

	private ConfigurableModel model;

	public ClientSettingsController() {
		try {
			model = new ConfigurationModel();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void saveSettings(Map<SettingType, String> settings) {
		try {
			model.setLocation(fileName);
		} catch (ContractorException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Map<SettingType, String> getSettings() {
		Map<SettingType, String> settings;
		try {
			location = model.getLocation();
		} catch (ContractorException e) {
			e.printStackTrace();
		}
		return settings;
	}
}
