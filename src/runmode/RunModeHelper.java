package runmode;

import static runmode.RunMode.*;

/**
 * Used to determine what runMode was specified when the application started.
 */
public class RunModeHelper {

    private static RunModeHelper INSTANCE;
    private RunMode mode;

    private RunModeHelper() {
    }

    /**
     * Returns a instance of RunModeHelper, creating it if it doesn't exists.
     * 
     * @return a RunModeHelper instance
     */
    public static RunModeHelper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RunModeHelper();
        }
        return INSTANCE;
    }

    /**
     * Sets the run mode to to the specified runMode. If the specified runMode is empty the the run mode is set to {@code network}.
     * 
     * @param runMode
     *            - the run mode
     * @throws InvalidRunModeTypeException
     *             if the runMode is not {@code alone}, {@code server} or empty
     */
    public void setRunMode(String runMode) throws InvalidRunModeTypeException {
        if (runMode.isEmpty()) {
            mode = NETWORK;
        } else if (isAloneRunMode(runMode)) {
            mode = ALONE;
        } else if (isServerRunMode(runMode)) {
            mode = SERVER;
        } else {
            String errorMessage = createErrorMessage(runMode);
            throw new InvalidRunModeTypeException(errorMessage);
        }
    }

    /**
     * returns {@code true} if the mode is equal to alone
     * 
     * @return {@code true} if the mode is equal to alone
     */
    public boolean isRunningInAloneMode() {
        return mode == ALONE;
    }

    /**
     * returns {@code true} if the mode is equal to network
     * 
     * @return {@code true} if the mode is equal to network
     */
    public boolean isRunningInNetworkMode() {
        return mode == NETWORK;
    }

    /**
     * returns {@code true} if the mode is equal to server
     * 
     * @return {@code true} if the mode is equal to server
     */
    public boolean isRunningInServerMode() {
        return mode == SERVER;
    }

    private boolean isAloneRunMode(String runMode) {
        return isSameRunMode(runMode, ALONE);
    }

    private boolean isServerRunMode(String runMode) {
        return isSameRunMode(runMode, SERVER);
    }

    private boolean isSameRunMode(String atualRunMode, RunMode expectedRunMode) {
        String expectedRunModeString = expectedRunMode.toString().toLowerCase();
        return atualRunMode.equals(expectedRunModeString);
    }

    private String createErrorMessage(String runMode) {
        StringBuilder builder = new StringBuilder();
        builder.append("The run mode '%s' is not valid run mode type.");
        builder.append("Valid arguments are 'alone', 'server', or nothing for the run mode to be set to network");
        return String.format(builder.toString(), runMode);
    }
}
