package client.view.settings;

import static setting.SettingType.DIRECTORY;

import java.awt.FlowLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import setting.SettingType;
import client.view.ViewMerger;

public class DirectoryTab extends JPanel implements SettingsTab {

	private String title;
	private DirectoryPanel directoryPanel;

	public DirectoryTab() {
		title = "Database location";
		this.setLayout(new FlowLayout());

		directoryPanel = new DirectoryPanel();
		this.add(directoryPanel);
	}

	@Override
	public String getTtile() {
		return title;
	}

	@Override
	public void saveSettings() {

		Map<SettingType, String> settings = new HashMap<>();
		String directory = directoryPanel.getDirectory();
		settings.put(DIRECTORY, directory);

		ViewMerger merger = ViewMerger.getInstance();
		merger.saveSettings(settings);
	}
}
