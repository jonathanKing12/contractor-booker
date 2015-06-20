package client.view.settings;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import client.view.ViewMerger;

public class SettingsDialogBox extends JDialog {

	private JTabbedPane tabbedPane;
	private SettingsTab settingsTab;

	public SettingsDialogBox(SettingsTab settingsTab) {
		this.setTitle("settings");
		this.settingsTab = settingsTab;

		addSettingsTab();
		this.pack();
		this.setModal(true);

		ViewMerger.getInstance().addSettingsDialogBox(this);
	}

	private void addSettingsTab() {
		tabbedPane = new JTabbedPane();
		tabbedPane.add((JPanel) settingsTab, settingsTab.getTtile());
		this.add(tabbedPane);
	}

	public void display() {
		this.setVisible(true);
	}
}
