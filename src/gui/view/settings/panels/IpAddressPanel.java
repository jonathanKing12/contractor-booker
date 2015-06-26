package gui.view.settings.panels;

import gui.view.TextHolder;
import gui.view.TextHolderBuilder;

import javax.swing.JPanel;

public class IpAddressPanel extends JPanel {

	private TextHolder ipAddressHolder;

	public IpAddressPanel() {
		ipAddressHolder = createTextHolder();
		ipAddressHolder.addComponents();
		this.add(ipAddressHolder);
	}

	public void setIpAddress(String ipAddress) {
		ipAddressHolder.setText(ipAddress);
	}

	public String getIpAddress() {
		return ipAddressHolder.getText();
	}

	private TextHolder createTextHolder() {
		TextHolderBuilder builder = new TextHolderBuilder();
		return builder.addLabel("enter server's IP address").addNumberOfColumns(10).build();
	}
}
