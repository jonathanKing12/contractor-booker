package client.view.settings;

import static client.view.ViewMerger.VIEW_MERGER_INSTACE;
import static settings.SettingType.IP_ADDRESS;
import static settings.SettingType.PORT_NUMBER;

import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import settings.SettingType;

public class PortNumberIpAddressSettingsTab extends JPanel implements SettingsTab {

	private String title;
	private PortNumberPanel portNumberSettingPanel;
	private IpAddressPanel ipAddressPanel;

	public PortNumberIpAddressSettingsTab() {
		title = "Server connection";
		portNumberSettingPanel = new PortNumberPanel();
		ipAddressPanel = new IpAddressPanel();

		this.setLayout(new GridLayout(2, 1));
		this.add(portNumberSettingPanel);
		this.add(ipAddressPanel);
	}

	@Override
	public String getTtile() {
		return title;
	}

	@Override
	public void saveSettings() {
		Map<SettingType, String> settings = new HashMap<>();

		String portNumber = portNumberSettingPanel.getPortNumber();
		// if (!portNumber.isEmpty()) {
		settings.put(PORT_NUMBER, portNumber);
		// }

		String ipAddress = ipAddressPanel.getIpAddress();
		// if (!ipAddress.isEmpty()) {
		settings.put(IP_ADDRESS, ipAddress);
		// }

		VIEW_MERGER_INSTACE.saveSettingsToController(settings);
	}

	@Override
	public void loadSettings() {
		Map<SettingType, String> settings = VIEW_MERGER_INSTACE.loadSettingsFromController();
		String portNumber = settings.get(PORT_NUMBER);
		portNumberSettingPanel.setPortNumber(portNumber);

		String ipAddress = settings.get(IP_ADDRESS);
		ipAddressPanel.setIpAddress(ipAddress);
	}
}
