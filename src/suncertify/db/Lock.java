package suncertify.db;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class Lock implements Comparable<Lock> {

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
			waitUntillIsUnlocked();
		}
		isLocked = TRUE;
		lockCookie = System.currentTimeMillis();
	}

	synchronized void unlock() {
		isLocked = FALSE;
		notifyAll();
	}

	synchronized boolean isLockedAndHasLockCookie(long lockCookie) {
		return isLocked && hasLockCookie(lockCookie);
	}

	private boolean hasLockCookie(long lockCookie) {
		return this.lockCookie == lockCookie;
	}

	private void waitUntillIsUnlocked() {
		try {
			wait();
		} catch (InterruptedException e) {
			String message = createInterrupedErrorMessage(e);
			throw new LockInterruptException(message);
		}
	}

	@Override
	public int compareTo(Lock lock) {
		if (lock == null) {
			return -1;
		}
		return Integer.compare(recordNumber, lock.recordNumber);
	}

	private String createInterrupedErrorMessage(InterruptedException e) {
		String message = " Thread interuped when waiting to lock record %d \n %s";
		return String.format(message, recordNumber, e.getMessage());
	}
}
