package main;

import runmode.RunModeHelper;
import ui.view.mergers.ParentTracker;
import ui.view.settings.SettingsDialog;
import ui.view.settings.SettingsDialogFactory;
import ui.view.windows.ClientMainWindow;
import ui.view.windows.MainWindow;
import ui.view.windows.ServerMainWindow;

public class Main {

	public static void main(String[] args) {
		new Main(args);
	}

	public Main(String[] args) {
		RunModeHelper helper = RunModeHelper.getInstance();
		helper.setRunMode(args);

		SettingsDialog settingsDialog = getSettingsDialog(helper);
		createMainWindow(helper, settingsDialog);

		ParentTracker.getInstance().displayMainWindow();
	}

	private SettingsDialog getSettingsDialog(RunModeHelper helper) {
		SettingsDialogFactory factory = new SettingsDialogFactory();

		if (helper.isRunningInNetworkMode()) {
			return factory.createNetworkSettingsDialog();
		}

		if (helper.isRunningInAloneMode()) {
			return factory.createStandaloneSettingsDialog();
		}

		return factory.createServerSettingsDialog();
	}

	private MainWindow createMainWindow(RunModeHelper helper, SettingsDialog settingsDialog) {
		if (helper.isRunningInServerMode()) {
			return new ServerMainWindow(settingsDialog);
		}
		return new ClientMainWindow(settingsDialog);
	}
}
