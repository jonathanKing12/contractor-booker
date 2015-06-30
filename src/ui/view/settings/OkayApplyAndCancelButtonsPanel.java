package ui.view.settings;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import ui.view.mergers.ParentTracker;

public class OkayApplyAndCancelButtonsPanel extends JPanel {

	private JButton okay;
	private JButton apply;
	private JButton cancel;

	public OkayApplyAndCancelButtonsPanel(SettingsDialog settingsDialogBox) {

		this.setLayout(new FlowLayout());
		createButtons();
		addButtons();

		addOkayActionListener(settingsDialogBox);
		addApplyActionListener(settingsDialogBox);
		addCancelActionListener();
	}

	private void createButtons() {
		okay = new JButton("Okay");
		apply = new JButton("Apply");
		cancel = new JButton("Cancel");
	}

	private void addButtons() {
		this.add(okay);
		this.add(apply);
		this.add(cancel);
	}

	private void addOkayActionListener(final SettingsDialog settingsDialogBox) {
		okay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				settingsDialogBox.saveSettings();
				stopDisplayingSettingsDialog();
			}
		});
	}

	private void addApplyActionListener(final SettingsDialog settingsDialogBox) {
		apply.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				settingsDialogBox.saveSettings();
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
		ParentTracker.getInstance().stopDisplayingSettingsDialogBox();
	}
}
