package ui.view.settings;

import ui.view.api.SettableTab;

public class SettingsDialogBoxBuilder {

	private SettableTab settingsTab;

	public SettingsDialogBoxBuilder addSettingsTab(SettableTab settingsTab) {
		this.settingsTab = settingsTab;
		return this;
	}

	public SettingsDialog build() {
		return new SettingsDialog(settingsTab);
	}
}
