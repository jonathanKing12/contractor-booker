package ui.view.settings.tabs;

import static java.lang.Boolean.FALSE;
import static settings.SettingType.DIRECTORY;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.JPanel;

import settings.SettingType;
import ui.view.api.SettableTab;
import ui.view.mergers.SettableMerger;
import ui.view.settings.DirectorySelectionHandler;
import ui.view.textholder.TextHolder;
import ui.view.textholder.TextHolderBuilder;

public class DirectoryTab extends JPanel implements SettableTab {

	private String title;
	private Set<SettingType> settingTypes;
	private TextHolder directoryHolder;
	private DirectorySelectionHandler directorySelectionHandler;

	public DirectoryTab() {
		title = "Database location";
		setUpDirectoryHolder();
		addComponents();
		createSettingType();
	}

	private void addComponents() {
		directoryHolder.addMessageLabelToPanel(this);
		directoryHolder.addTextFieldToPanel(this);
		directorySelectionHandler.addBrowseButtonToPanel(this);
	}

	private void setUpDirectoryHolder() {
		createDirectoryholder();
		directorySelectionHandler = new DirectorySelectionHandler(directoryHolder);
	}

	private void createDirectoryholder() {
		TextHolderBuilder builder = new TextHolderBuilder();
		directoryHolder = builder.addLabel("select folder database is in:").addIsEditable(FALSE)
				.addNumberOfColumns(35).build();
	}

	private void createSettingType() {
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
		String directory = directoryHolder.getText();
		settings.put(DIRECTORY, directory);

		sendSettingsToController(settings);
	}

	@Override
	public void loadSettings() {
		Map<SettingType, String> settings = getSettingsFromController();
		String directory = settings.get(DIRECTORY);
		directoryHolder.setText(directory);
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
