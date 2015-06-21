package client.view.settings;

import javax.swing.JPanel;

import client.view.TextHolder;

public class IpAddressPanel extends JPanel {

	private TextHolder textHolder;

	IpAddressPanel() {
		textHolder = new TextHolder("enter server's IP address");
		textHolder.addComponents(this);
	}

	void setIpAddress(String ipAddress) {
		textHolder.setText(ipAddress);
	}

	String getIpAddress() {
		return textHolder.getSettings();
	}
}
