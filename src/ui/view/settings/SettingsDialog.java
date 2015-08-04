package ui.view.settings;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.SOUTH;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import java.awt.BorderLayout;

import javax.swing.*;

import ui.view.common.ParentTracker;
import ui.view.settings.tabs.SettingsTab;

/**
 * Represents the settings dialog.
 */
public class SettingsDialog extends JDialog {

    private JTabbedPane tabbedPane;
    private SettingsTab settingsTab;
    private SettingsButtonPanel settingsButtonPanel;

    /**
     * Constructs a instance of SettingsDialog with the specified settingsTab.
     * 
     * @param settingsTab
     *            - the settingsTab
     */
    public SettingsDialog(SettingsTab settingsTab) {
        this.settingsTab = settingsTab;
        settingsButtonPanel = new SettingsButtonPanel(this);
        tabbedPane = createTabbedPane();
        addComponents();

        this.pack();
        this.setModal(true);
        this.setTitle("Settings Dialogue");
        addToParentTracker();
    }

    /**
     * loads this instance settingsTab settings and displays this instance.
     */
    public void display() {
        settingsTab.loadSettings();
        setVisible(TRUE);
    }

    /**
     * Stops displaying this instance.
     */
    public void stopDisplaying() {
        setVisible(FALSE);
    }

    /**
     * Saves this instance settingsTab settings.
     */
    public void saveSettings() {
        settingsTab.saveSettings();
    }

    /**
     * Returns {@code true} if this instance settingsTab is to be displayed.
     * 
     * @return {@code true} if this instance settingsTab is to be displayed
     */
    public boolean isToBeDisplayed() {
        return settingsTab.isToBeDisplayed();
    }

    private JTabbedPane createTabbedPane() {
        JPanel tabPanel = (JPanel) settingsTab;
        String title = settingsTab.getTitle();

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.add(tabPanel, title);
        return tabbedPane;
    }

    private void addComponents() {
        this.setLayout(new BorderLayout());
        this.add(tabbedPane, CENTER);
        this.add(settingsButtonPanel, SOUTH);
    }

    private void addToParentTracker() {
        ParentTracker parentTracker = ParentTracker.getInstance();
        parentTracker.setSettingsDialog(this);
    }
}
