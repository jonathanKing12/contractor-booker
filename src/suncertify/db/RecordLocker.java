package suncertify.db;

public class RecordLocker {

	private LockCabnit lockCabnit;

	RecordLocker() {
		lockCabnit = new LockCabnit();
	}

	long lockRecord(int recordNumber) {
		Lock lock = lockCabnit.getOrCreateLock(recordNumber);
		lock.lock();
		return lock.getLockCookie();
	}

	void unlockRecord(int recordNumber, long lockCookie) throws SecurityException {
		verifyRecordIsLockedWithLockCookie(recordNumber, lockCookie);
		Lock lock = lockCabnit.getOrCreateLock(recordNumber);
		lock.unlock();
	}

	void verifyRecordIsLockedWithLockCookie(int recordNumber, long lockCookie) throws SecurityException {
		Lock lock = lockCabnit.getOrCreateLock(recordNumber);

		if (!lock.isLockedAndHasLockCookie(lockCookie)) {
			String message = createInvalidLockCookieMessage(recordNumber, lockCookie);
			throw new InvalidLockCookieException(message);
		}
	}

	private String createInvalidLockCookieMessage(int recordNumber, long lockCookie) {
		String message = "the record %d is not locked by %d";
		return String.format(message, recordNumber, lockCookie);
	}
}
