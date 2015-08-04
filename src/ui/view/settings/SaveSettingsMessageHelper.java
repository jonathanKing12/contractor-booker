package ui.view.settings;

import ui.view.common.MessageBoxPresenter;

/**
 * Displays messages to the Screen before the settings are saved.
 */
public class SaveSettingsMessageHelper {

    private static final String SAVE_SETTINGS_TITLE = "Save Settings";
    private static final String MESSAGE_TO_RESTART_SERVER = "For the settings to be saved, the server needs to be restarted.\n Would you like to restart the server?";
    private static final String MESSAGE_TO_CLEAR_CONTRACTORS = "For the settings to be saved, the contractors need to be removed from the table.\n Would you like to remove the contrctors?";

    /**
     * Returns {@code true} if the user has selected yes button to the message box asking for permission to empty the table of contractors.
     * 
     * @return {@code true} if the user has selected yes button to the message box asking for permission to empty the table of contractors
     */
    public static boolean hasUserGrantedPermissionToEmptyTable() {
        return dispayMessageInYesNoDialogBox(MESSAGE_TO_CLEAR_CONTRACTORS);
    }

    /**
     * Returns {@code true} if the user has selected yes button to the message box asking for permission to restart the server.
     * 
     * @return {@code true} if the user has selected yes button to the message box asking for permission to restart the server
     */
    public static boolean hasUserGrantedPermissionToRestartServer() {
        return dispayMessageInYesNoDialogBox(MESSAGE_TO_RESTART_SERVER);
    }

    private static boolean dispayMessageInYesNoDialogBox(String message) {
        MessageBoxPresenter presenter = new MessageBoxPresenter();
        return presenter.dispayYesNoDialog(message, SAVE_SETTINGS_TITLE);
    }
}
