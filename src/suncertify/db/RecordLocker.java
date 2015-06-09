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
			throw new SecurityException("the record " + recordNumber + " is not locked by " + lockCookie);
		}
	}
}
