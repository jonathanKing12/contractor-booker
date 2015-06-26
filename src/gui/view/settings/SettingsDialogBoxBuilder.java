package gui.view.settings;

import gui.view.api.SettableTab;

public class SettingsDialogBoxBuilder {

	private SettableTab settingsTab;

	public SettingsDialogBoxBuilder addSettingsTab(SettableTab settingsTab) {
		this.settingsTab = settingsTab;
		return this;
	}

	public SettingsDialogBox build() {
		return new SettingsDialogBox(settingsTab);
	}
}
