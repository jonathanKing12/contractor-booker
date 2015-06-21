package client.view.settings;

import static client.view.ViewMerger.VIEW_MERGER_INSTACE;
import static settings.SettingType.DIRECTORY;

import java.awt.FlowLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import settings.SettingType;

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

		VIEW_MERGER_INSTACE.saveSettingsToController(settings);
	}

	@Override
	public void loadSettings() {
		Map<SettingType, String> settings = VIEW_MERGER_INSTACE.loadSettingsFromController();
		String directory = settings.get(DIRECTORY);
		directoryPanel.setDirectory(directory);
	}
}
