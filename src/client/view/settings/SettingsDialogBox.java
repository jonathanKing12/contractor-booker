package client.view.settings;

import static client.view.ViewMerger.VIEW_MERGER_INSTACE;
import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.SOUTH;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class SettingsDialogBox extends JDialog {

	private JTabbedPane tabbedPane;
	private SettingsTab settingsTab;

	public SettingsDialogBox(SettingsTab settingsTab) {
		this.setTitle("settings");
		this.settingsTab = settingsTab;

		this.setLayout(new BorderLayout());
		addSettingsTab();
		this.add(new OkayApplyCancelButtonsPanel(this), SOUTH);

		this.pack();
		this.setModal(true);
		VIEW_MERGER_INSTACE.addSettingsDialogBox(this);
	}

	public void display() {
		settingsTab.loadSettings();
		setVisible(TRUE);
	}

	public void stopDisplaying() {
		setVisible(FALSE);
	}

	private void addSettingsTab() {
		tabbedPane = new JTabbedPane();
		tabbedPane.add((JPanel) settingsTab, settingsTab.getTtile());
		this.add(tabbedPane, CENTER);
	}

	public void saveSettings() {
		settingsTab.saveSettings();
	}
}
