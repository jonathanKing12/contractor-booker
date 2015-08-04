package ui.view.settings.tabs;

import static java.lang.Boolean.FALSE;
import static settings.SettingType.DIRECTORY;
import static ui.view.settings.SaveSettingsMessageHelper.hasUserGrantedPermissionToEmptyTable;

import java.util.*;

import javax.swing.JPanel;

import settings.SettingType;
import ui.view.common.textwidget.TextWidget;
import ui.view.common.textwidget.TextWidgetBuilder;
import ui.view.contractor.ContractorMediator;
import ui.view.settings.DirectoryPicker;
import ui.view.settings.SettingsMediator;

/**
 * A tab in the settings dialog for changing the database folder.
 */
public class DirectoryTab extends JPanel implements SettingsTab {

    private String title;
    private Set<SettingType> settingTypes;
    private TextWidget directoryHolder;
    private DirectoryPicker directorySelectionHandler;
    private SettingsMediator settingsMediator;
    private ContractorMediator contractorMediator;

    /**
     * Constructs a instance of DirectoryTab.
     */
    public DirectoryTab() {
        title = "Database location";
        setUpDirectoryHolder();
        addComponents();
        createSettingType();
        settingsMediator = new SettingsMediator();
        contractorMediator = ContractorMediator.getInstance();
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
     * Returns {@code true} if there is no directory setting saved.
     * 
     * @return {@code true} if there is no directory setting saved
     */
    @Override
    public boolean isToBeDisplayed() {
        return settingsMediator.isAllSettingsNotSaved(settingTypes);
    }

    /**
     * Saves the directory location setting selected by the user.
     * 
     * <p>
     * If there are contractors currently displaying on screen then the user is asked for their permission for the removal of those contractors in
     * order for the setting to be saved. If the user does not grant permission for the removal of those contractors from the screen then the setting
     * is not saved.
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
     * loads the directory location setting.
     */
    @Override
    public void loadSettings() {
        Map<SettingType, String> settings = settingsMediator.loadSettings();
        String directory = settings.get(DIRECTORY);
        directoryHolder.setText(directory);
    }

    private void addComponents() {
        directoryHolder.addMessageLabelToPanel(this);
        directoryHolder.addTextFieldToPanel(this);
        directorySelectionHandler.addBrowseButtonToComponent(this);
    }

    private void setUpDirectoryHolder() {
        createDirectoryholder();
        directorySelectionHandler = new DirectoryPicker(directoryHolder);
    }

    private void createDirectoryholder() {
        TextWidgetBuilder builder = new TextWidgetBuilder();
        directoryHolder = builder.addLabel("select folder database is in:").addIsEditable(FALSE)
                .addNumberOfColumns(35).build();
    }

    private void createSettingType() {
        settingTypes = new HashSet<>();
        settingTypes.add(DIRECTORY);
    }

    private Map<SettingType, String> getSettings() {
        Map<SettingType, String> settings = new HashMap<>();
        String directory = directoryHolder.getText();
        settings.put(DIRECTORY, directory);
        return settings;
    }
}
