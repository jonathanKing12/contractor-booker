package suncertify.db.record.lock;

import static java.lang.Boolean.FALSE;

public class RecordLockKeeper {

    private RecordLockCabnet lockCabnit;

    /**
     * Creates a new RecordLockKeeper instance.
     */
    public RecordLockKeeper() {
        lockCabnit = new RecordLockCabnet();
    }

    /**
     * Locks the RecordLock that has the specified lockNumber
     * 
     * @param lockNumber
     *            - the lockNumber
     * @return the RecordLock's lockCookie
     */
    public long lockRecord(int lockNumber) {
        RecordLock lock = lockCabnit.getLock(lockNumber);
        lock.lock();
        return lock.getLockCookie();
    }

    /**
     * UnLocks the RecordLock that has the specified lockNumber.
     * 
     * @param lockNumber
     *            - the lockNumber of the RecordLock that is to be unlocked
     * @param lockCookie
     *            - the lockCookie of the RecordLock that is to be unlocked
     * @throws InvalidLockCookieException
     *             if the RecordLock is not locked by the specified lockCookie
     */
    public void unlockRecord(int lockNumber, long lockCookie) throws InvalidLockCookieException {
        verifyRecordIsLockedWithLockCookie(lockNumber, lockCookie);
        RecordLock lock = lockCabnit.getLock(lockNumber);
        lock.unlock();
    }

    /**
     * verify if a RecordLock that has the specified lockNumber is locked by the specified lockCookie
     * 
     * @param lockNumber
     *            - the lockNumber of the RecordLock that is to be unlocked
     * @param lockCookie
     *            - the lockCookie of the RecordLock that is to be unlocked
     * @throws InvalidLockCookieException
     *             if the RecordLock is not locked by the specified lockCookie
     */
    public void verifyRecordIsLockedWithLockCookie(int lockNumber, long lockCookie)
            throws InvalidLockCookieException {

        boolean isLockedWithLockCookie = FALSE;
        boolean doesRecordLockExist = lockCabnit.isContainingRecordLockWithLockNumber(lockNumber);

        if (doesRecordLockExist) {
            RecordLock lock = lockCabnit.getLock(lockNumber);
            isLockedWithLockCookie = lock.isLockedWithLockCookie(lockCookie);
        }

        if (!isLockedWithLockCookie) {
            String message = createInvalidLockCookieMessage(lockNumber, lockCookie);
            throw new InvalidLockCookieException(message);
        }
    }

    private String createInvalidLockCookieMessage(int lockNumber, long lockCookie) {
        String message = "the record lock %d is not locked by the cookie %d";
        return String.format(message, lockNumber, lockCookie);
    }
}
