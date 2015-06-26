package gui.view.mergers;

import gui.view.MessageBoxPresenter;
import gui.view.settings.SettingsDialogBox;
import gui.view.windows.MainWindow;

import java.awt.Component;

public enum ViewMerger {

	VIEW_MERGER_INSTACE;

	private MainWindow mainWindow;
	private SettingsDialogBox settingsDialogBox;
	private MessageBoxPresenter messageBoxCreator;

	private ViewMerger() {
		messageBoxCreator = MessageBoxPresenter.getInstance();
	}

	public void addSettingsDialogBox(SettingsDialogBox settingsDialogBox) {
		this.settingsDialogBox = settingsDialogBox;
	}

	public void addMainWindow(MainWindow mainWindow) {
		this.mainWindow = mainWindow;

	}

	public void displayMainWindow() {
		messageBoxCreator.SetParent(mainWindow);
		mainWindow.display();
	}

	public void displaySettngsDialogBox() {
		messageBoxCreator.SetParent(settingsDialogBox);
		settingsDialogBox.display();
	}

	public void stopDisplayingSettingsDialogBox() {
		messageBoxCreator.SetParent(mainWindow);
		settingsDialogBox.stopDisplaying();
	}

	public Component getParent() {
		// TODO Auto-generated method stub
		return null;
	}
}
