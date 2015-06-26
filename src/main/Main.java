package main;

import gui.view.mergers.ViewMerger;
import gui.view.settings.SettingsDialogBox;
import gui.view.settings.SettingsDialogBoxBuilder;
import gui.view.settings.tabs.PortNumberAndDirectoryTab;
import gui.view.windows.ClientMainWindow;
import gui.view.windows.MainWindow;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		new Main();
	}

	public Main() throws IOException {
		MainWindow mainScreen = new ClientMainWindow(createSettingsDialogBox());
		ViewMerger.VIEW_MERGER_INSTACE.displayMainWindow();
		// MainWindow mainScreen = new ServerMainWindow(createSettingsDialogBox());

	}

	private SettingsDialogBox createSettingsDialogBox() {
		SettingsDialogBoxBuilder builder = new SettingsDialogBoxBuilder();
		builder.addSettingsTab(new PortNumberAndDirectoryTab());
		// builder.addSettingsTab(new DirectoryTab());
		return builder.build();
	}
}
