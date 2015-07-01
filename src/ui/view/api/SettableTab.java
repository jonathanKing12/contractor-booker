package ui.view.api;


public interface SettableTab {

    void saveSettings();

    String getTtile();

    void loadSettings();

    /**
     * @return
     */
    boolean isToBeDisplayed();
}
