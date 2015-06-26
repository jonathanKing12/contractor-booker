package gui.view.settings.tabs;

import static javax.swing.BoxLayout.Y_AXIS;
import static settings.SettingType.DIRECTORY;
import static settings.SettingType.IP_ADDRESS;
import static settings.SettingType.PORT_NUMBER;
import gui.view.TextHolder;
import gui.view.TextHolderFactory;
import gui.view.api.SettableTab;
import gui.view.mergers.SettableMerger;
import gui.view.settings.panels.DirectorySelectionHandler;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import settings.SettingType;

public class PortNumberAndDirectoryTab extends JPanel implements SettableTab {

	private String title;
	private TextHolder portNumberTextHolder;
	private TextHolder directoryTextHolder;
	private Set<SettingType> settingTypes;

	public PortNumberAndDirectoryTab() {
		title = "Database connection";
		setUpTextHolders();

		addLabels();
		// addTextFields();
		setUpBrowseButton();
		createSettingsTypes();
	}

	private void createSettingsTypes() {
		settingTypes = new HashSet<>();
		settingTypes.add(PORT_NUMBER);
		settingTypes.add(IP_ADDRESS);
	}

	private void setUpBrowseButton() {
		DirectorySelectionHandler directoryPanel = new DirectorySelectionHandler(directoryTextHolder);
		JButton browseButton = directoryPanel.getBrowseButton();
		this.add(browseButton);
	}

	private void setUpTextHolders() {
		TextHolderFactory factory = new TextHolderFactory();
		portNumberTextHolder = factory.createPortNumberTextHolder();
		directoryTextHolder = factory.createDirectoryTextHolder();
	}

	@Override
	public String getTtile() {
		return title;
	}

	@Override
	public void saveSettings() {
		Map<SettingType, String> settings = new HashMap<>();
		String portNumber = portNumberTextHolder.getText();
		settings.put(PORT_NUMBER, portNumber);

		String directory = directoryTextHolder.getText();
		settings.put(DIRECTORY, directory);
		sendSettingsToController(settings);
	}

	@Override
	public void loadSettings() {
		SettableMerger merger = SettableMerger.getInstance();
		Map<SettingType, String> settings = merger.loadSettingsFromController();
		directoryTextHolder.setText(settings.get(DIRECTORY));
		portNumberTextHolder.setText(settings.get(PORT_NUMBER));
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

	// private void addLabels() {
	//
	// JLabel portNumberLabel = portNumberTextHolder.getMessageLabel();
	// JLabel ipAddressLabel = directoryTextHolder.getMessageLabel();
	//
	// JPanel labelPanel = new JPanel();
	// labelPanel.setLayout(new BorderLayout());
	// labelPanel.add(portNumberLabel, NORTH);
	// labelPanel.add(ipAddressLabel, SOUTH);
	// this.add(labelPanel);
	// }

	// private void addTextFields() {
	//
	// JTextField portNumberTextField = portNumberTextHolder.getTextField();
	// JTextField ipAddressTextField = directoryTextHolder.getTextField();
	//
	// JPanel textFieldPanel = new JPanel();
	// textFieldPanel.setLayout(new BoxLayout(textFieldPanel, Y_AXIS));
	// textFieldPanel.add(portNumberTextField);
	// textFieldPanel.add(ipAddressTextField);
	// this.add(textFieldPanel);
	// }

	private void addLabels() {

		JLabel portNumberLabel = portNumberTextHolder.getMessageLabel();
		JLabel ipAddressLabel = directoryTextHolder.getMessageLabel();

		JPanel labelPanel = new JPanel();
		labelPanel.setLayout(new BoxLayout(labelPanel, Y_AXIS));

		JTextField portNumberTextField = portNumberTextHolder.getTextField();
		JTextField ipAddressTextField = directoryTextHolder.getTextField();

		labelPanel.add(portNumberLabel);
		labelPanel.add(portNumberTextField);
		labelPanel.add(ipAddressLabel);
		labelPanel.add(ipAddressTextField);

		this.add(labelPanel);
	}
}
