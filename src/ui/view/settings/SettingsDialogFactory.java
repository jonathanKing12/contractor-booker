package ui.view.settings;

import ui.view.settings.tabs.DirectoryTab;
import ui.view.settings.tabs.PortNumberAndDirectoryTab;
import ui.view.settings.tabs.PortNumberAndIpAddressTab;

public class SettingsDialogFactory {

	public SettingsDialog createStandaloneSettingsDialog() {
		SettingsDialogBoxBuilder builder = new SettingsDialogBoxBuilder();
		builder.addSettingsTab(new DirectoryTab());
		return builder.build();
	}

	public SettingsDialog createNetworkSettingsDialog() {
		SettingsDialogBoxBuilder builder = new SettingsDialogBoxBuilder();
		builder.addSettingsTab(new PortNumberAndIpAddressTab());
		return builder.build();
	}

	public SettingsDialog createServerSettingsDialog() {
		SettingsDialogBoxBuilder builder = new SettingsDialogBoxBuilder();
		builder.addSettingsTab(new PortNumberAndDirectoryTab());
		return builder.build();
	}
}
