package client.view.settings;

import static setting.SettingType.IP_ADDRESS;
import static setting.SettingType.PORT_NUMBER;

import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import setting.SettingType;
import client.view.ViewMerger;

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
		settings.put(PORT_NUMBER, portNumber);

		String ipAddress = ipAddressPanel.getIpAddress();
		settings.put(IP_ADDRESS, ipAddress);

		ViewMerger merger = ViewMerger.getInstance();
		merger.saveSettings(settings);
	}
}
