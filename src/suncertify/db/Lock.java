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
			waitUntillIsUnlocked();
		}
		isLocked = TRUE;

		lockCookie = createLockCookie();
	}

	synchronized void unlock() {
		isLocked = FALSE;
		notifyAll();
	}

	synchronized boolean isLockedAndHasLockCookie(long lockCookie) {
		return isLocked && hasLockCookie(lockCookie);
	}

	@Override
	public int hashCode() {
		return recordNumber;
	}

	@Override
	public boolean equals(Object object) {

		if (!(object instanceof Lock)) {
			return FALSE;
		}

		Lock otherLock = (Lock) object;
		return recordNumber == otherLock.recordNumber;
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

	private long createLockCookie() {
		long lockCookie = System.currentTimeMillis();
		lockCookie = appendRandomDigit(lockCookie);
		lockCookie = appendRecordNumber(lockCookie);
		return lockCookie;
	}

	private long appendRandomDigit(long lockCookie) {
		int randomDigit = (int) (Math.random() * 100);
		lockCookie *= 100;
		return lockCookie + randomDigit;
	}

	private long appendRecordNumber(long lockCookie) {
		lockCookie *= getTenToThePowerOfNumberOfdigitsInRecordNumber();
		return lockCookie + recordNumber;
	}

	private long getTenToThePowerOfNumberOfdigitsInRecordNumber() {
		int numberOfDigits = Integer.toString(recordNumber).length();
		return (long) Math.pow(10, numberOfDigits);
	}

	private String createInterrupedErrorMessage(InterruptedException e) {
		String message = " Thread interuped when waiting to lock record %d \n %s";
		return String.format(message, recordNumber, e.getMessage());
	}

	public static void main(String[] args) {
		System.out.println(new Lock(1).hashCode());
	}
}
