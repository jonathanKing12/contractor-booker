package ui.view.settings;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import ui.view.common.ParentTracker;

/**
 * Represents the OK, Apply and Cancel button on the SettingsDialog.
 */
public class SettingsButtonPanel extends JPanel {

    private JButton okay;
    private JButton apply;
    private JButton cancel;

    /**
     * Constructs a instance of SettingsButtonPanel with the specified settingsDialog
     * 
     * @param settingsDialog
     *            - the settingsDialog
     */
    public SettingsButtonPanel(SettingsDialog settingsDialog) {

        this.setLayout(new FlowLayout());
        createButtons();
        addButtons();

        addOkayActionListener(settingsDialog);
        addApplyActionListener(settingsDialog);
        addCancelActionListener();
    }

    private void createButtons() {
        okay = new JButton("OK");
        apply = new JButton("Apply");
        cancel = new JButton("Cancel");
    }

    private void addButtons() {
        this.add(okay);
        this.add(apply);
        this.add(cancel);
    }

    private void addOkayActionListener(final SettingsDialog settingsDialog) {
        okay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                settingsDialog.saveSettings();
                stopDisplayingSettingsDialog();
            }
        });
    }

    private void addApplyActionListener(final SettingsDialog settingsDialog) {
        apply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                settingsDialog.saveSettings();
            }
        });
    }

    private void addCancelActionListener() {
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                stopDisplayingSettingsDialog();
            }

        });
    }

    private void stopDisplayingSettingsDialog() {
        ParentTracker parentTracker = ParentTracker.getInstance();
        parentTracker.stopDisplayingSettingsDialog();
    }
}
