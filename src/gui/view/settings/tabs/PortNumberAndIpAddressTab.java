package gui.view.settings.tabs;

import static settings.SettingType.DIRECTORY;
import static settings.SettingType.IP_ADDRESS;
import static settings.SettingType.PORT_NUMBER;
import gui.view.TextHolder;
import gui.view.TextHolderFactory;
import gui.view.api.SettableTab;
import gui.view.mergers.SettableMerger;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.JPanel;

import settings.SettingType;

public class PortNumberAndIpAddressTab extends JPanel implements SettableTab {

	private String title;
	private TextHolder portNumberTextHolder;
	private TextHolder ipAddressTextHolder;
	private Set<SettingType> settingTypes;

	public PortNumberAndIpAddressTab() {
		title = "Server connection";

		setUpTextHolders();

		this.add(portNumberSettingPanel);
		this.add(ipAddressPanel);

		settingTypes = new HashSet<>();
		settingTypes.add(PORT_NUMBER);
		settingTypes.add(DIRECTORY);
	}

	private void setUpTextHolders() {
		TextHolderFactory factory = new TextHolderFactory();
		portNumberTextHolder = factory.createPortNumberTextHolder();
		ipAddressTextHolder = factory.createIpAddressTextHolder();
	}

	@Override
	public String getTtile() {
		return title;
	}

	@Override
	public void saveSettings() {
		Map<SettingType, String> settings = new HashMap<>();

		String portNumber = portNumberSettingPanel.getPortNumber();
		settings.put(PORT_NUMBER, portNumber);

		String ipAddress = ipAddressPanel.getIpAddress();
		settings.put(IP_ADDRESS, ipAddress);

		sendSettingsToController(settings);
	}

	@Override
	public void loadSettings() {
		Map<SettingType, String> settings = getSettingsFromController();
		String portNumber = settings.get(PORT_NUMBER);
		portNumberSettingPanel.setPortNumber(portNumber);

		String ipAddress = settings.get(IP_ADDRESS);
		ipAddressPanel.setIpAddress(ipAddress);
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
