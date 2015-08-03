package ui.view.common;

import static java.lang.Boolean.TRUE;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import ui.view.settings.SettingsDialog;

public class MainWindow extends JFrame {

    private SettingsDialog settingsDialog;

    /**
     * Constructs a instance of MainWindow with the specified settingsDialog.
     * 
     * @param settingsDialog
     *            - the settingsDialog
     */
    protected MainWindow(SettingsDialog settingsDialog) {
        this.settingsDialog = settingsDialog;
        settingsDialog.setLocationRelativeTo(this);

        addMenuBar();

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        ParentTracker.getInstance().setMainWindow(this);

    }

    /**
     * Displays this instance of MainWindow.
     * 
     * <p>
     * Also displays the settingsDialog if this is the first time this instance is being displayed and the settingsDialog instance
     * {@link SettingsDialog#isToBeDisplayed()} method returns {@code true}.
     * </p>
     */
    public void display() {
        boolean isShowing = this.isShowing();
        this.setVisible(TRUE);

        if (!isShowing && settingsDialog.isToBeDisplayed()) {
            ParentTracker tracker = ParentTracker.getInstance();
            tracker.displaySettngsDialog();
        }
    }

    private void addMenuBar() {
        this.setJMenuBar(new MenuBar());
    }
}
