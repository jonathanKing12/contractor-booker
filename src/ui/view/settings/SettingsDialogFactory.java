package ui.view.settings;

import ui.view.settings.tabs.*;

/**
 * Creates a SettingsDialog instance.
 */
public class SettingsDialogFactory {

    /**
     * Returns a SettingsDialog with a DirectoryTab.
     * 
     * @return a SettingsDialog with a {@link DirectoryTab}
     */
    public SettingsDialog createStandaloneSettingsDialog() {
        return new SettingsDialog(new DirectoryTab());
    }

    /**
     * Returns a SettingsDialog with a PortNumberAndIpAddressTab.
     * 
     * @return a SettingsDialog with a {@link PortNumberAndIpAddressTab}
     */
    public SettingsDialog createNetworkSettingsDialog() {
        return new SettingsDialog(new PortNumberAndIpAddressTab());
    }

    /**
     * Returns a SettingsDialog with a PortNumberAndDirectoryTab.
     * 
     * @return a SettingsDialog with a {@link PortNumberAndDirectoryTab}
     */
    public SettingsDialog createServerSettingsDialog() {
        return new SettingsDialog(new PortNumberAndDirectoryTab());
    }
}
