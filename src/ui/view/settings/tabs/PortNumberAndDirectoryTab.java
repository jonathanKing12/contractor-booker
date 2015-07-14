package ui.view.settings.tabs;

import static java.lang.Boolean.FALSE;
import static settings.SettingType.DIRECTORY;
import static settings.SettingType.PORT_NUMBER;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.Box;
import javax.swing.JPanel;

import settings.SettingType;
import ui.view.api.SettableTab;
import ui.view.api.SettableView;
import ui.view.mergers.ServerMerger;
import ui.view.mergers.SettableMerger;
import ui.view.settings.DirectoryPicker;
import ui.view.textwidget.TextWidget;
import ui.view.textwidget.TextWidgetBuilder;
import ui.view.textwidget.TextWidgetLayout;

public class PortNumberAndDirectoryTab extends JPanel implements SettableTab {

	private String title;
	private TextWidget portNumberWidget;
	private TextWidget directoryWidget;
	private Set<SettingType> settingTypes;
	private SettableView merger;

	public PortNumberAndDirectoryTab() {
		title = "Database connection";
		merger = new SettableMerger();

		setUpTextHolders();
		addComponents();
		createSettingsTypes();
	}

	@Override
	public String getTtile() {
		return title;
	}

	@Override
	public boolean isToBeDisplayed() {
		return merger.isAllSettingsMissing(settingTypes);
	}

	@Override
	public void saveSettings() {
		Map<SettingType, String> settings = new HashMap<>();
		String portNumber = portNumberWidget.getText();
		settings.put(PORT_NUMBER, portNumber);

		String directory = directoryWidget.getText();
		settings.put(DIRECTORY, directory);
		if (okay()) {
			merger.saveSettings(settings);
			ServerMerger.getInstance().enableAgain();
		}
	}

	private boolean okay() {
		ServerMerger merger = ServerMerger.getInstance();
		return merger.isOkay();
	}

	@Override
	public void loadSettings() {
		Map<SettingType, String> settings = merger.loadSettings();
		directoryWidget.setText(settings.get(DIRECTORY));
		portNumberWidget.setText(settings.get(PORT_NUMBER));
	}

	private void setUpTextHolders() {
		portNumberWidget = createPortNumberWidget();
		directoryWidget = createDirectoryWidget();
	}

	private TextWidget createDirectoryWidget() {
		TextWidgetBuilder builder = new TextWidgetBuilder();
		return builder.addLabel("select folder database is in:").addIsEditable(FALSE)
				.addNumberOfColumns(35).build();
	}

	private TextWidget createPortNumberWidget() {
		TextWidgetBuilder builder = new TextWidgetBuilder();
		return builder.addLabel("Enter port number to receive clients requests:")
				.addNumberOfColumns(5).build();
	}

	private void addComponents() {
		TextWidgetLayout textWidgetBoxLayout = new TextWidgetLayout();
		JPanel textWidgetsPanel = textWidgetBoxLayout.layoutVertically(portNumberWidget,
				directoryWidget);

		Box box = createBrowserButtonBox();
		textWidgetsPanel.add(box);
		this.add(textWidgetsPanel);
	}

	private Box createBrowserButtonBox() {
		Box box = Box.createVerticalBox();
		box.add(Box.createVerticalGlue());
		addBrowseButtonToBox(box);
		return box;
	}

	private void addBrowseButtonToBox(Box box) {
		DirectoryPicker directoryPanel = new DirectoryPicker(directoryWidget);
		directoryPanel.addBrowseButtonToComponent(box);
	}

	private void createSettingsTypes() {
		settingTypes = new HashSet<>();
		settingTypes.add(PORT_NUMBER);
		settingTypes.add(DIRECTORY);
	}
}
