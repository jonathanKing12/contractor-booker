package ui.view.settings;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.SOUTH;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import java.awt.BorderLayout;
import java.util.Set;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import settings.SettingType;
import ui.view.api.SettableTab;
import ui.view.mergers.ParentTracker;

public class SettingsDialog extends JDialog {

	private JTabbedPane tabbedPane;
	private SettableTab settingsTab;

	public SettingsDialog(SettableTab settingsTab) {
		this.setTitle("settings");
		this.settingsTab = settingsTab;

		this.setLayout(new BorderLayout());
		addSettingsTab();
		this.add(new OkayApplyAndCancelButtonsPanel(this), SOUTH);

		this.pack();
		this.setModal(true);
		ParentTracker.getInstance().addSettingsDialogBox(this);
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

	public Set<SettingType> getSettingsTypes() {
		return settingsTab.getSettingsTypes();
	}
}
