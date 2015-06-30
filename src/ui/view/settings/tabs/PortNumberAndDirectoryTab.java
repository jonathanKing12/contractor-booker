package ui.view.settings.tabs;

import static settings.SettingType.DIRECTORY;
import static settings.SettingType.PORT_NUMBER;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.Box;
import javax.swing.JPanel;

import settings.SettingType;
import ui.view.TextHolder;
import ui.view.TextHolderBoxLayout;
import ui.view.TextHolderFactory;
import ui.view.api.SettableTab;
import ui.view.mergers.SettableMerger;
import ui.view.settings.DirectorySelectionHandler;

public class PortNumberAndDirectoryTab extends JPanel implements SettableTab {

	private String title;
	private TextHolder portNumberHolder;
	private TextHolder directoryHolder;
	private Set<SettingType> settingTypes;

	// private JButton browseButton;

	public PortNumberAndDirectoryTab() {
		title = "Database connection";
		setUpTextHolders();
		// setUpBrowseButton();
		addComponents();
		createSettingsTypes();
	}

	@Override
	public String getTtile() {
		return title;
	}

	@Override
	public void saveSettings() {
		Map<SettingType, String> settings = new HashMap<>();
		String portNumber = portNumberHolder.getText();
		settings.put(PORT_NUMBER, portNumber);

		String directory = directoryHolder.getText();
		settings.put(DIRECTORY, directory);
		sendSettingsToController(settings);
	}

	@Override
	public void loadSettings() {
		Map<SettingType, String> settings = getSettingsFromController();
		directoryHolder.setText(settings.get(DIRECTORY));
		portNumberHolder.setText(settings.get(PORT_NUMBER));
	}

	@Override
	public Set<SettingType> getSettingsTypes() {
		return settingTypes;
	}

	private void createSettingsTypes() {
		settingTypes = new HashSet<>();
		settingTypes.add(PORT_NUMBER);
		settingTypes.add(DIRECTORY);
	}

	private void setUpTextHolders() {
		TextHolderFactory factory = new TextHolderFactory();
		portNumberHolder = factory.createServerPortNumberTextHolder();
		directoryHolder = factory.createDirectoryTextHolder();
	}

	private void sendSettingsToController(Map<SettingType, String> settings) {
		SettableMerger merger = SettableMerger.getInstance();
		merger.saveSettingsToController(settings);
	}

	private Map<SettingType, String> getSettingsFromController() {
		SettableMerger merger = SettableMerger.getInstance();
		return merger.loadSettingsFromController();
	}

	private void addComponents() {
		TextHolderBoxLayout textHolderBoxLayout = new TextHolderBoxLayout();
		JPanel panel = textHolderBoxLayout.layoutVertically(portNumberHolder, directoryHolder);

		Box box = createBoxContainingBrowserButton();
		panel.add(box);
		this.add(panel);
	}

	private Box createBoxContainingBrowserButton() {
		Box box = Box.createVerticalBox();
		box.add(Box.createVerticalGlue());
		setUpBrowseButton(box);
		return box;
	}

	private void setUpBrowseButton(Box box) {
		DirectorySelectionHandler directoryPanel = new DirectorySelectionHandler(directoryHolder);
		directoryPanel.addBrowseButtonToPanel(box);
	}
}
