package ui.view;

import java.awt.Component;

import ui.view.settings.SettingsDialog;
import ui.view.windows.MainWindow;

public class ParentTracker {

	private static ParentTracker INSTANCE;
	private Component parent;
	private MainWindow mainWindow;
	private SettingsDialog settingsDialogBox;

	static {
		INSTANCE = new ParentTracker();
	}

	private ParentTracker() {
	}

	public static ParentTracker getInstance() {
		return INSTANCE;
	}

	public void addSettingsDialogBox(SettingsDialog settingsDialogBox) {
		this.settingsDialogBox = settingsDialogBox;
	}

	public void addMainWindow(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}

	public void displayMainWindow() {
		parent = mainWindow;
		mainWindow.display();
	}

	public void displaySettngsDialogBox() {
		parent = settingsDialogBox;
		settingsDialogBox.display();
	}

	public void stopDisplayingSettingsDialogBox() {
		parent = mainWindow;
		settingsDialogBox.stopDisplaying();
	}

	public Component getCurrentParent() {
		return parent;
	}
}
