package main;

import runmode.InvalidRunModeTypeException;
import runmode.RunModeHelper;
import ui.view.common.ParentTracker;
import ui.view.contractor.ContractorWindow;
import ui.view.server.ServerWindow;
import ui.view.settings.SettingsDialog;
import ui.view.settings.SettingsDialogFactory;

/**
 * The Beginning of the application. Creates and Displays the mainWindow.
 */
public class Main {

    private static final String EMPTY_ELEMENT = "";
    private RunModeHelper runModeHelper;

    /**
     * Creates a Main instance with the first argument in the specified args.
     * 
     * @param args
     *            - the arguments passed into the application when its run.
     */
    public static void main(String[] args) {
        String runMode = getFirstElement(args);
        new Main(runMode);
    }

    /**
     * Constructs a Main instance with the specified runMode.
     * 
     * <p>
     * Creates and Displays the MainWindow that correspond to the specified runMode.
     * </p>
     * 
     * @param runMode
     *            -the runMode
     */
    public Main(String runMode) {
        setRunMode(runMode);

        SettingsDialog settingsDialog = getSettingsDialog();
        createMainWindow(settingsDialog);

        ParentTracker.getInstance().displayMainWindow();
    }

    private void setRunMode(String runMode) {
        runModeHelper = RunModeHelper.getInstance();
        try {
            runModeHelper.setRunMode(runMode);
        } catch (InvalidRunModeTypeException e) {
            System.err.println(e.getMessage());
            System.exit(-1);
        }
    }

    private SettingsDialog getSettingsDialog() {
        SettingsDialogFactory creator = new SettingsDialogFactory();

        if (runModeHelper.isRunningInNetworkMode()) {
            return creator.createNetworkSettingsDialog();
        }

        if (runModeHelper.isRunningInAloneMode()) {
            return creator.createStandaloneSettingsDialog();
        }

        return creator.createServerSettingsDialog();
    }

    private void createMainWindow(SettingsDialog settingsDialog) {
        if (runModeHelper.isRunningInServerMode()) {
            new ServerWindow(settingsDialog);
            return;
        }
        new ContractorWindow(settingsDialog);
    }

    private static String getFirstElement(String[] args) {
        return (args.length == 0) ? EMPTY_ELEMENT : args[0];
    }
}
