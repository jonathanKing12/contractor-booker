package suncertify.db;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class Lock {

	private int recordNumber;
	private long lockCookie;
	private boolean isLocked;

	Lock(int recordNumber) {
		this.recordNumber = recordNumber;
	}

	boolean hasNumber(int recordNumber) {
		return this.recordNumber == recordNumber;
	}

	synchronized long getLockCookie() {
		return lockCookie;
	}

	synchronized void lock() {
		while (isLocked) {
			waitUntillLockIsUnlocked();
		}
		isLocked = TRUE;
		lockCookie = System.currentTimeMillis();
	}

	synchronized void unlock() {
		isLocked = FALSE;
		sendNotificationThatLockIsUnlocked();
	}

	synchronized boolean isLockedAndHasLockCookie(long lockCookie) {
		return isLocked && hasLockCookie(lockCookie);
	}

	private boolean hasLockCookie(long lockCookie) {
		return this.lockCookie == lockCookie;
	}

	private void waitUntillLockIsUnlocked() {
		try {
			wait();
		} catch (InterruptedException e) {
		}
	}

	private void sendNotificationThatLockIsUnlocked() {
		notifyAll();
	}
}
