package ui.controller;

import static java.lang.Boolean.FALSE;

import java.util.*;

import settings.SettingType;
import settings.SettingsException;
import ui.controller.api.SettableController;
import ui.model.settings.SettableModel;
import ui.model.settings.SettingsModel;
import ui.view.common.MessageBoxPresenterView;

public class SettingsController implements SettableController {

    private static final String SETTINGS_ERROR_TITLE = "Settings Error";
    private SettableModel settingsModel;
    private MessageBoxPresenterView messageMediatorView;

    /**
     * Constructs a instance of SettingsController with the specified messageMediatorView
     * 
     * @param messageMediatorView
     *            - the messageMediatorView
     */
    public SettingsController(MessageBoxPresenterView messageMediatorView) {
        this.messageMediatorView = messageMediatorView;
        settingsModel = new SettingsModel();
    }

    /**
     * Saves the specified settings to the model.
     * 
     * <p>
     * Delegates the call to {@link SettableModel#saveSettings(settings)} if a {@link SettingsException} is thrown then
     * {@link MessageBoxPresenterView#displayErrorMessage(message, title)} is invoked.
     * </p>
     * 
     * @param settings
     *            - the settings
     */
    @Override
    public void saveSettings(Map<SettingType, String> settings) {
        try {
            settingsModel.saveSettings(settings);
        } catch (SettingsException e) {
            displayErrorMessage(e);
        }
    }

    /**
     * Returns settings from the model.
     * 
     * <p>
     * Delegates the call to {@link SettableModel#loadSettings()} if a {@link SettingsException} is thrown then
     * {@link MessageBoxPresenterView#displayErrorMessage(message, title)} is invoked.
     * </p>
     * 
     * @return the settings
     */
    @Override
    public Map<SettingType, String> loadSettings() {
        Map<SettingType, String> settings = new HashMap<>();
        try {
            settings = settingsModel.loadSettings();
        } catch (SettingsException e) {
            displayErrorMessage(e);
        }
        return settings;
    }

    /**
     * Returns {@code true} if all of the specified settingsTypes are not saved in the model.
     * 
     * <p>
     * Delegates the call to {@link SettableModel#areAllSettingsNotSaved(settingsTypes)} if a {@link SettingsException} is thrown then
     * {@link MessageBoxPresenterView#displayErrorMessage(message, title)} is invoked and {@code false} is returned
     * </p>
     * 
     * @param settingsTypes
     *            - the settingsTypes
     * @return {@code true} if all the settingsTypes are not saved in the model
     */
    @Override
    public boolean areAllSettingsNotSaved(Set<SettingType> settingsTypes) {
        try {
            return settingsModel.areAllSettingsNotSaved(settingsTypes);
        } catch (SettingsException e) {
            displayErrorMessage(e);
            return FALSE;
        }
    }

    private void displayErrorMessage(SettingsException settingsException) {
        String message = settingsException.getMessage();
        messageMediatorView.displayErrorMessageDialog(message, SETTINGS_ERROR_TITLE);
    }
}
