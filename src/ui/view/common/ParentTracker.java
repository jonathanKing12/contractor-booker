package ui.view.common;

import java.awt.Component;

import ui.view.settings.SettingsDialog;

/**
 * Keeps track of Component is the active window, either the MainWindow or the SettingsDialog instance
 */
public class ParentTracker {

    private static ParentTracker INSTANCE;
    private SettingsDialog settingsDialog;
    private MainWindow mainWindow;
    private Component parent;

    static {
        INSTANCE = new ParentTracker();
    }

    private ParentTracker() {
    }

    /**
     * Returns the only instance of ParentTracker.
     * 
     * @return the only instance of ParentTracker
     */
    public static ParentTracker getInstance() {
        return INSTANCE;
    }

    /**
     * Sets the settingsDialog to the specified settingsDialog
     * 
     * @param settingsDialog
     *            - the settingsDialog
     */
    public void setSettingsDialog(SettingsDialog settingsDialog) {
        this.settingsDialog = settingsDialog;
    }

    /**
     * Sets the mainWindow to the specified mainWindow
     * 
     * @param mainWindow
     *            - the mainWindow
     */
    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    /**
     * Displays the mainWindow and sets the mainWindow as the parent.
     */
    public void displayMainWindow() {
        parent = mainWindow;
        mainWindow.display();
    }

    /**
     * Displays the settingsDialog and sets the settingsDialog as the parent.
     */
    public void displaySettngsDialog() {
        parent = settingsDialog;
        settingsDialog.display();
    }

    /**
     * Stops displaying the settingsDialog and sets the mainWindow as the parent.
     */
    public void stopDisplayingSettingsDialog() {
        parent = mainWindow;
        settingsDialog.stopDisplaying();
    }

    /**
     * Returns the current parent.
     * 
     * @return the current parent
     */
    public Component getParent() {
        return parent;
    }
}
