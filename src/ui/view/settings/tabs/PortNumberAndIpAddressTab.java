package ui.view.settings.tabs;

import static settings.SettingType.IP_ADDRESS;
import static settings.SettingType.PORT_NUMBER;
import static ui.view.settings.SaveSettingsMessageHelper.hasUserGrantedPermissionToEmptyTable;

import java.util.*;

import javax.swing.JPanel;

import settings.SettingType;
import ui.view.common.textwidget.*;
import ui.view.contractor.ContractorMediator;
import ui.view.settings.SettingsMediator;

/**
 * A tab in the settings dialog for changing the port number and the IP address.
 */
public class PortNumberAndIpAddressTab extends JPanel implements SettingsTab {

    private String title;
    private Set<SettingType> settingTypes;
    private TextWidget portNumberWidget;
    private TextWidget ipAddressWidget;
    private SettingsMediator settingsMediator;
    private ContractorMediator contractorMediator;

    /**
     * Constructs a instance of PortNumberAndIpAddressTab.
     */
    public PortNumberAndIpAddressTab() {
        title = "Server connection";
        settingsMediator = new SettingsMediator();
        contractorMediator = ContractorMediator.getInstance();

        setUpTextWidgets();
        addComponents();
        setSettingsTypes();
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
     * Returns {@code true} if there is no Port number and IP address saved.
     * 
     * @return {@code true} if there is no Port number and IP address saved
     */
    @Override
    public boolean isToBeDisplayed() {
        return settingsMediator.isAllSettingsNotSaved(settingTypes);
    }

    /**
     * Saves the Port number and IP address settings selected by the user.
     * 
     * <p>
     * If there are contractors currently displaying on screen then the user is asked for their permission for the removal of those contractors in
     * order for the settings to be saved. If the user does not grant permission for the removal of those contractors from the screen then the
     * settings are not saved.
     * </p>
     */
    @Override
    public void saveSettings() {
        Map<SettingType, String> settings = getSettings();

        if (contractorMediator.isContractorTableEmpty()) {
            settingsMediator.saveSettings(settings);
            return;
        }

        if (hasUserGrantedPermissionToEmptyTable()) {
            contractorMediator.emptyTable();
            settingsMediator.saveSettings(settings);
        }
    }

    /**
     * loads the Port number and IP address settings.
     */
    @Override
    public void loadSettings() {
        Map<SettingType, String> settings = settingsMediator.loadSettings();
        String portNumber = settings.get(PORT_NUMBER);
        portNumberWidget.setText(portNumber);

        String ipAddress = settings.get(IP_ADDRESS);
        ipAddressWidget.setText(ipAddress);
    }

    private void setUpTextWidgets() {
        portNumberWidget = createPortNumberTextWidget();
        ipAddressWidget = createIpAddressTextWidget();
    }

    private TextWidget createIpAddressTextWidget() {
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

    private Map<SettingType, String> getSettings() {
        Map<SettingType, String> settings = new HashMap<>();
        String portNumber = portNumberWidget.getText();
        settings.put(PORT_NUMBER, portNumber);

        String ipAddress = ipAddressWidget.getText();
        settings.put(IP_ADDRESS, ipAddress);
        return settings;
    }
}
