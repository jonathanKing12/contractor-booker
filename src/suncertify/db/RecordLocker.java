package suncertify.db;

public class RecordLocker {

	private LockCabnet lockCabnit;

	RecordLocker() {
		lockCabnit = new LockCabnet();
	}

	long lockRecord(int recordNumber) {
		Lock lock = lockCabnit.getLock(recordNumber);
		lock.lock();
		return lock.getLockCookie();
	}

	void unlockRecord(int recordNumber, long lockCookie) throws SecurityException {
		verifyRecordIsLockedWithLockCookie(recordNumber, lockCookie);
		Lock lock = lockCabnit.getLock(recordNumber);
		lock.unlock();
	}

	void verifyRecordIsLockedWithLockCookie(int recordNumber, long lockCookie) throws InvalidLockCookieException {
		Lock lock = lockCabnit.getLock(recordNumber);

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
