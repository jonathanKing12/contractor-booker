package client.view.settings;


public class SettingsDialogBoxBuilder {

	private SettingsTab settingsTab;

	public SettingsDialogBoxBuilder addSettingsTab(SettingsTab settingsTab) {
		this.settingsTab = settingsTab;
		return this;
	}

	public SettingsDialogBox build() {
		return new SettingsDialogBox(settingsTab);
	}
}
