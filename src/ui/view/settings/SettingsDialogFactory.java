package ui.view.settings;

import ui.view.settings.tabs.DirectoryTab;
import ui.view.settings.tabs.PortNumberAndDirectoryTab;
import ui.view.settings.tabs.PortNumberAndIpAddressTab;

public class SettingsDialogFactory {

	public SettingsDialog createStandaloneSettingsDialog() {
		return new SettingsDialog(new DirectoryTab());
	}

	public SettingsDialog createNetworkSettingsDialog() {
		return new SettingsDialog(new PortNumberAndIpAddressTab());
	}

	public SettingsDialog createServerSettingsDialog() {
		return new SettingsDialog(new PortNumberAndDirectoryTab());
	}
}
