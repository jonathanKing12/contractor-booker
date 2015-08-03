package ui.view.settings.tabs;

import static java.lang.Boolean.FALSE;
import static settings.SettingType.DIRECTORY;
import static settings.SettingType.PORT_NUMBER;
import static ui.view.settings.SaveSettingsMessageHelper.hasUserGrantedPermissionToRestartServer;

import java.util.*;

import javax.swing.Box;
import javax.swing.JPanel;

import settings.SettingType;
import ui.view.common.textwidget.*;
import ui.view.server.ServerMediator;
import ui.view.settings.DirectoryPicker;
import ui.view.settings.SettingsMediator;

public class PortNumberAndDirectoryTab extends JPanel implements SettingsTab {

    private String title;
    private TextWidget portNumberWidget;
    private TextWidget directoryWidget;
    private Set<SettingType> settingTypes;
    private SettingsMediator settingsMediatorView;
    private ServerMediator serverMediatorView;

    /**
     * Constructs a instance of PortNumberAndDirectoryTab.
     */
    public PortNumberAndDirectoryTab() {
        title = "Database connection";
        settingsMediatorView = new SettingsMediator();
        serverMediatorView = ServerMediator.getInstance();
        setUpTextHolders();
        addComponents();
        createSettingsTypes();
    }

    /**
     * Returns this instance title.
     * 
     * @return the title
     */
    @Override
    public String getTitle() {
        return title;
    }

    /**
     * Returns {@code true} if there is no Port number and Directory location saved.
     * 
     * @return {@code true} if there is no Port number and Directory location saved
     */
    @Override
    public boolean isToBeDisplayed() {
        return settingsMediatorView.isAllSettingsNotSaved(settingTypes);
    }

    /**
     * Saves the port number and directory location settings selected by the user.
     * 
     * <p>
     * If the server is running then the user is asked for their permission to restart the sever in order for the settings to be saved. If the user
     * does not grant permission to restart server then the settings aren't saved. The server is restart after the settings have been saved.
     * </p>
     */
    @Override
    public void saveSettings() {
        Map<SettingType, String> settings = getSettings();

        if (!serverMediatorView.isServerButtonEnabled()) {
            settingsMediatorView.saveSettings(settings);
            return;
        }

        if (hasUserGrantedPermissionToRestartServer()) {
            serverMediatorView.toggleServerButtonState();
            settingsMediatorView.saveSettings(settings);
            serverMediatorView.toggleServerButtonState();
        }
    }

    /**
     * loads the Port number and directory location settings.
     */
    @Override
    public void loadSettings() {
        Map<SettingType, String> settings = settingsMediatorView.loadSettings();
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

    private Map<SettingType, String> getSettings() {
        Map<SettingType, String> settings = new HashMap<>();
        String portNumber = portNumberWidget.getText();
        settings.put(PORT_NUMBER, portNumber);

        String directory = directoryWidget.getText();
        settings.put(DIRECTORY, directory);
        return settings;
    }
}
