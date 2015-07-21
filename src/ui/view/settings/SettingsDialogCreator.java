package ui.view.settings;

import ui.view.settings.tabs.*;

public class SettingsDialogCreator {

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
