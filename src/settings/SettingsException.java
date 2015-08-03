package settings;

/**
 * Occurs when the settings fail to be saved or loaded from the properties file.
 */
public class SettingsException extends Exception {

    /**
     * Constructs a instance of SettingsException.
     */
    public SettingsException() {
        super();
    }

    /**
     * Constructs a instance of SettingsException with the specified message
     * 
     * @param message
     *            - the message
     */
    public SettingsException(String message) {
        super(message);
    }
}
