package suncertify.db;

import suncertify.db.record.lock.RecordLockCabnet;

public class LockCabnetClient implements Runnable {

	private RecordLockCabnet lockCabnet;
	private int lockNumber;

	public LockCabnetClient(RecordLockCabnet lockCabnet, int lockNumber) {
		this.lockCabnet = lockCabnet;
		this.lockNumber = lockNumber;
	}

	@Override
	public void run() {
		lockCabnet.getLock(lockNumber);
	}
}
