package gui.view.settings.panels;

import static gui.view.mergers.ViewMerger.VIEW_MERGER_INSTACE;
import gui.view.settings.SettingsDialogBox;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class OkayApplyAndCancelButtonsPanel extends JPanel {

	private JButton okay;
	private JButton apply;
	private JButton cancel;

	public OkayApplyAndCancelButtonsPanel(SettingsDialogBox settingsDialogBox) {

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

	private void addOkayActionListener(final SettingsDialogBox settingsDialogBox) {
		okay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				settingsDialogBox.saveSettings();
				VIEW_MERGER_INSTACE.stopDisplayingSettingsDialogBox();
			}
		});
	}

	private void addApplyActionListener(final SettingsDialogBox settingsDialogBox) {
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
				VIEW_MERGER_INSTACE.stopDisplayingSettingsDialogBox();
			}
		});
	}

}
