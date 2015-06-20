package suncertify.db;

public class LockCabnetClient implements Runnable {

	private LockCabnet lockCabnet;
	private int lockNumber;

	public LockCabnetClient(LockCabnet lockCabnet, int lockNumber) {
		this.lockCabnet = lockCabnet;
		this.lockNumber = lockNumber;
	}

	@Override
	public void run() {
		lockCabnet.getLock(lockNumber);
	}
}
