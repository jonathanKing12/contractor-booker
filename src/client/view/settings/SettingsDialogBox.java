package client.view.settings;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import client.view.ViewMerger;

public class SettingsDialogBox extends JDialog {

	private JTabbedPane tabbedPane;
	private ViewMerger merger;

	public SettingsDialogBox(JFrame parentFrame) {
		super(parentFrame, "settings");
		addChooseFolderTabbedJPanel();
		this.pack();
		this.setModal(true);
		this.setLocationRelativeTo(parentFrame);
		ViewMerger.getInstance().addSettingsDialogBox(this);

	}

	private void addChooseFolderTabbedJPanel() {
		tabbedPane = new JTabbedPane();
		SelectFileTabbedPanel selectFilePanel = new SelectFileTabbedPanel();
		tabbedPane.add(selectFilePanel, selectFilePanel.getTtile());
		this.add(tabbedPane);
	}

	public void display() {
		this.setVisible(true);
	}
}
