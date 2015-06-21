package client.view;

import static java.lang.Boolean.TRUE;

import java.io.IOException;

import client.view.settings.PortNumberIpAddressSettingsTab;
import client.view.settings.SettingsDialogBox;
import client.view.settings.SettingsDialogBoxBuilder;

public class Main {

	public static void main(String[] args) throws IOException {
		new Main();
	}

	public Main() throws IOException {
		MainWindow mainScreen = new ClientMainWindow(createSettingsDialogBox());
		mainScreen.setVisible(TRUE);
	}

	private SettingsDialogBox createSettingsDialogBox() {
		SettingsDialogBoxBuilder builder = new SettingsDialogBoxBuilder();
		builder.addSettingsTab(new PortNumberIpAddressSettingsTab());
		return builder.build();
	}
}
