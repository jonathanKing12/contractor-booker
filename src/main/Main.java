package main;

import runmode.RunModeHelper;
import ui.view.ParentTracker;
import ui.view.settings.SettingsDialog;
import ui.view.settings.SettingsDialogCreator;
import ui.view.windows.*;

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

    private SettingsDialog getSettingsDialog(RunModeHelper runModeHelper) {
        SettingsDialogCreator creator = new SettingsDialogCreator();

        if (runModeHelper.isRunningInNetworkMode()) {
            return creator.createNetworkSettingsDialog();
        }

        if (runModeHelper.isRunningInAloneMode()) {
            return creator.createStandaloneSettingsDialog();
        }

        return creator.createServerSettingsDialog();
    }

    private MainWindow createMainWindow(RunModeHelper helper, SettingsDialog settingsDialog) {
        if (helper.isRunningInServerMode()) {
            return new ServerMainWindow(settingsDialog);
        }
        return new ClientMainWindow(settingsDialog);
    }
}
