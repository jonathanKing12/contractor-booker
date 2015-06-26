package gui.controller;

import gui.controller.api.SettableController;
import gui.model.api.SettableModel;
import gui.model.settings.SettingsModel;
import gui.view.api.SettableView;

import java.util.Map;
import java.util.Set;

import settings.SettingException;
import settings.SettingType;

public class SettingsController implements SettableController {

	private SettableModel model;
	private SettableView view;

	public SettingsController(SettableView view) {
		model = new SettingsModel(this);
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

	@Override
	public void displaySettingsDialogIfHasNotSettingsOfTypes(Set<SettingType> settingsTypes) {
		try {
			if (model.hasNoSettingsOfTypes(settingsTypes)) {
				view.displaySettingsDialogBox();
			}
		} catch (SettingException e) {
			view.displayErrorMessage(e.getMessage(), "error");
		}
	}
}
