package client.view.settings;

import static client.view.ViewMerger.VIEW_MERGER_INSTACE;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class OkayApplyCancelButtonsPanel extends JPanel {

	SettingsDialogBox settingsTab;
	private JButton okay;
	private JButton apply;
	private JButton cancel;

	OkayApplyCancelButtonsPanel(SettingsDialogBox settingsDialogBox) {
		this.settingsTab = settingsDialogBox;

		this.setLayout(new FlowLayout());
		createButtons();
		addButtons();

		addOkayActionListener();
		addApplyActionListener();
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

	private void addOkayActionListener() {
		okay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				VIEW_MERGER_INSTACE.saveSettingsDialogBoxSettings();
				VIEW_MERGER_INSTACE.stopDisplayingSettingsDialogBox();
			}
		});
	}

	private void addApplyActionListener() {
		apply.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				VIEW_MERGER_INSTACE.saveSettingsDialogBoxSettings();
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
