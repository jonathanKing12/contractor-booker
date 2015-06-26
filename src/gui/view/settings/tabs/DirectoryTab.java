package gui.view.settings.tabs;

import static settings.SettingType.DIRECTORY;
import gui.view.api.SettableTab;
import gui.view.mergers.SettableMerger;
import gui.view.settings.panels.DirectorySelectionHandler;

import java.awt.FlowLayout;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.JPanel;

import settings.SettingType;

public class DirectoryTab extends JPanel implements SettableTab {

	private String title;
	private DirectorySelectionHandler directoryPanel;
	private Set<SettingType> settingTypes;

	public DirectoryTab() {
		title = "Database location";
		this.setLayout(new FlowLayout());

		directoryPanel = new DirectorySelectionHandler();
		this.add(directoryPanel);

		settingTypes = new HashSet<>();
		settingTypes.add(DIRECTORY);
	}

	@Override
	public String getTtile() {
		return title;
	}

	@Override
	public void saveSettings() {
		Map<SettingType, String> settings = new HashMap<>();
		String directory = directoryPanel.getSelectedDirectory();
		settings.put(DIRECTORY, directory);

		sendSettingsToController(settings);
	}

	@Override
	public void loadSettings() {
		Map<SettingType, String> settings = getSettingsFromController();
		String directory = settings.get(DIRECTORY);
		directoryPanel.setDirectory(directory);
	}

	private void sendSettingsToController(Map<SettingType, String> settings) {
		SettableMerger merger = SettableMerger.getInstance();
		merger.saveSettingsToController(settings);
	}

	private Map<SettingType, String> getSettingsFromController() {
		SettableMerger merger = SettableMerger.getInstance();
		return merger.loadSettingsFromController();
	}

	@Override
	public Set<SettingType> getSettingsTypes() {
		return settingTypes;
	}
}
