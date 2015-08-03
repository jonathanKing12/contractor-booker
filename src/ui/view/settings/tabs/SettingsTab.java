package ui.view.settings.tabs;

public interface SettingsTab {

    /**
     * Returns the title.
     * 
     * @return the title
     */
    String getTitle();

    /**
     * Returns {@code true} if this instance is to be displayed.
     * 
     * @return {@code true} if this instance is to be displayed
     */
    boolean isToBeDisplayed();

    /**
     * Saves the settings.
     */
    void saveSettings();

    /**
     * loads the settings.
     */
    void loadSettings();
}
