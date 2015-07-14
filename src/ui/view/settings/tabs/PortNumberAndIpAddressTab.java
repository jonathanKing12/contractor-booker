package ui.view.settings.tabs;

import static settings.SettingType.IP_ADDRESS;
import static settings.SettingType.PORT_NUMBER;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.JPanel;

import settings.SettingType;
import ui.view.api.BookableView;
import ui.view.api.SettableTab;
import ui.view.api.SettableView;
import ui.view.mergers.BookableMerger;
import ui.view.mergers.SettableMerger;
import ui.view.textwidget.TextWidget;
import ui.view.textwidget.TextWidgetBuilder;
import ui.view.textwidget.TextWidgetLayout;

public class PortNumberAndIpAddressTab extends JPanel implements SettableTab {

	private String title;
	private Set<SettingType> settingTypes;
	private TextWidget portNumberWidget;
	private TextWidget ipAddressWidget;
	private SettableView merger;

	public PortNumberAndIpAddressTab() {
		title = "Server connection";
		merger = new SettableMerger();

		setUpTextWidgets();
		addComponents();
		setSettingsTypes();
	}

	@Override
	public String getTtile() {
		return title;
	}

	@Override
	public boolean isToBeDisplayed() {
		SettableView merger = new SettableMerger();
		return merger.isAllSettingsMissing(settingTypes);
	}

	@Override
	public void saveSettings() {
		Map<SettingType, String> settings = new HashMap<>();
		String portNumber = portNumberWidget.getText();
		settings.put(PORT_NUMBER, portNumber);

		String ipAddress = ipAddressWidget.getText();
		settings.put(IP_ADDRESS, ipAddress);

		if (resetSearch()) {
			merger.saveSettings(settings);
		}
	}

	private boolean resetSearch() {
		BookableView merger = BookableMerger.getInstance();
		return merger.clearTable();

	}

	@Override
	public void loadSettings() {
		Map<SettingType, String> settings = merger.loadSettings();
		String portNumber = settings.get(PORT_NUMBER);
		portNumberWidget.setText(portNumber);

		String ipAddress = settings.get(IP_ADDRESS);
		ipAddressWidget.setText(ipAddress);
	}

	private void setUpTextWidgets() {
		portNumberWidget = createPortNumberTextWidget();
		ipAddressWidget = createIpAddressTextWidget();
	}

	public TextWidget createIpAddressTextWidget() {
		TextWidgetBuilder builder = new TextWidgetBuilder();
		return builder.addLabel("Enter server's IP address:").addNumberOfColumns(10).build();
	}

	private TextWidget createPortNumberTextWidget() {
		TextWidgetBuilder builder = new TextWidgetBuilder();
		return builder.addLabel("Enter port number to listen to server:").addNumberOfColumns(5)
				.build();
	}

	private void addComponents() {
		TextWidgetLayout textWidgetLayout = new TextWidgetLayout();
		JPanel panel = textWidgetLayout.layoutVertically(portNumberWidget, ipAddressWidget);
		this.add(panel);
	}

	private void setSettingsTypes() {
		settingTypes = new HashSet<>();
		settingTypes.add(PORT_NUMBER);
		settingTypes.add(IP_ADDRESS);
	}
}
