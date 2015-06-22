package client.view.settings;

import static client.view.ViewMerger.VIEW_MERGER_INSTACE;
import static settings.SettingType.DIRECTORY;
import static settings.SettingType.PORT_NUMBER;

import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import settings.SettingType;

public class PortNumberDirectorySettingsTab extends JPanel implements SettingsTab {

	private String title;
	private PortNumberPanel portNumberPanel;
	private DirectoryPanel directoryPanel;

	public PortNumberDirectorySettingsTab() {
		title = "Database connection";
		portNumberPanel = new PortNumberPanel();
		directoryPanel = new DirectoryPanel();

		this.setLayout(new GridLayout(2, 1));
		this.add(portNumberPanel);
		this.add(directoryPanel);
	}

	@Override
	public String getTtile() {
		return title;
	}

	@Override
	public void saveSettings() {
		Map<SettingType, String> settings = new HashMap<>();
		String portNumber = portNumberPanel.getPortNumber();
		settings.put(PORT_NUMBER, portNumber);

		String directory = directoryPanel.getDirectory();
		settings.put(DIRECTORY, directory);
		VIEW_MERGER_INSTACE.saveSettingsToController(settings);
	}

	
	@Override
	public void loadSettings() {
		
	}
}
