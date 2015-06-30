package ui.view.settings.tabs;

import static settings.SettingType.IP_ADDRESS;
import static settings.SettingType.PORT_NUMBER;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.JPanel;

import settings.SettingType;
import ui.view.api.SettableTab;
import ui.view.mergers.SettableMerger;
import ui.view.textholder.TextHolder;
import ui.view.textholder.TextHolderBoxLayout;
import ui.view.textholder.TextHolderBuilder;

public class PortNumberAndIpAddressTab extends JPanel implements SettableTab {

	private String title;
	private Set<SettingType> settingTypes;
	private TextHolder portNumberHolder;
	private TextHolder ipAddressHolder;

	public PortNumberAndIpAddressTab() {
		title = "Server connection";
		setUpTextHolders();
		addComponents();
		setSettingsTypes();
	}

	@Override
	public String getTtile() {
		return title;
	}

	@Override
	public Set<SettingType> getSettingsTypes() {
		return settingTypes;
	}

	@Override
	public void saveSettings() {
		Map<SettingType, String> settings = new HashMap<>();
		String portNumber = portNumberHolder.getText();
		settings.put(PORT_NUMBER, portNumber);

		String ipAddress = ipAddressHolder.getText();
		settings.put(IP_ADDRESS, ipAddress);
		sendSettingsToController(settings);
	}

	@Override
	public void loadSettings() {
		Map<SettingType, String> settings = getSettingsFromController();
		String portNumber = settings.get(PORT_NUMBER);
		portNumberHolder.setText(portNumber);

		String ipAddress = settings.get(IP_ADDRESS);
		ipAddressHolder.setText(ipAddress);
	}

	private void setUpTextHolders() {
		portNumberHolder = createPortNumbeHolder();
		ipAddressHolder = createIpAddressTextHolder();
	}

	public TextHolder createIpAddressTextHolder() {
		TextHolderBuilder builder = new TextHolderBuilder();
		return builder.addLabel("Enter server's IP address:").addNumberOfColumns(10).build();
	}

	private TextHolder createPortNumbeHolder() {
		TextHolderBuilder builder = new TextHolderBuilder();
		return builder.addLabel("Enter port number to listen to server:").addNumberOfColumns(5)
				.build();
	}

	private void addComponents() {
		TextHolderBoxLayout textHolderBoxLayout = new TextHolderBoxLayout();
		JPanel panel = textHolderBoxLayout.layoutVertically(portNumberHolder, ipAddressHolder);
		this.add(panel);
	}

	private void setSettingsTypes() {
		settingTypes = new HashSet<>();
		settingTypes.add(PORT_NUMBER);
		settingTypes.add(IP_ADDRESS);
	}

	private void sendSettingsToController(Map<SettingType, String> settings) {
		SettableMerger merger = SettableMerger.getInstance();
		merger.saveSettingsToController(settings);
	}

	private Map<SettingType, String> getSettingsFromController() {
		SettableMerger merger = SettableMerger.getInstance();
		return merger.loadSettingsFromController();
	}
}
