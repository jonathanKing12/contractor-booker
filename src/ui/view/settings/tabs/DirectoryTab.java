package ui.view.settings.tabs;

import static java.lang.Boolean.FALSE;
import static settings.SettingType.DIRECTORY;

import java.util.*;

import javax.swing.JPanel;

import settings.SettingType;
import ui.view.api.*;
import ui.view.mergers.BookableMerger;
import ui.view.mergers.SettableMerger;
import ui.view.settings.DirectoryPicker;
import ui.view.textwidget.TextWidget;
import ui.view.textwidget.TextWidgetBuilder;

public class DirectoryTab extends JPanel implements SettableTab {

    private String title;
    private Set<SettingType> settingTypes;
    private TextWidget directoryHolder;
    private DirectoryPicker directorySelectionHandler;
    private SettableView merger;

    public DirectoryTab() {
        title = "Database location";
        setUpDirectoryHolder();
        addComponents();
        createSettingType();
        merger = new SettableMerger();
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
        String directory = directoryHolder.getText();
        settings.put(DIRECTORY, directory);

        merger.saveSettings(settings);
        resetSearch();
    }

    private void resetSearch() {
        BookableView merger = BookableMerger.getInstance();
        merger.resetContractorSearch();

    }

    @Override
    public void loadSettings() {
        Map<SettingType, String> settings = merger.loadSettings();
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
}
