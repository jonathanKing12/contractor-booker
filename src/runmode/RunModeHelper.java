package runmode;

import static runmode.RunMode.ALONE;
import static runmode.RunMode.NETWORK;
import static runmode.RunMode.SERVER;

public class RunModeHelper {

	private static RunModeHelper runModeHelper;
	private RunMode mode;

	private RunModeHelper() {
	}

	public static RunModeHelper getInstance() {
		if (runModeHelper == null) {
			runModeHelper = new RunModeHelper();
		}
		return runModeHelper;
	}

	public void setRunMode(String[] args) {
		if (isEmpty(args)) {
			mode = NETWORK;
		} else if (isFirstElementAlone(args)) {
			mode = ALONE;
		} else {
			mode = SERVER;
		}
	}

	public boolean isRunningInAloneMode() {
		return mode == ALONE;
	}

	public boolean isRunningInNetworkMode() {
		return mode == NETWORK;
	}

	public boolean isRunningInServerMode() {
		return mode == SERVER;
	}

	private boolean isEmpty(String[] mode) {
		return mode.length == 0;
	}

	private boolean isFirstElementAlone(String[] mode) {
		return mode[0].equals(ALONE.toString().toLowerCase());
	}
}
