package suncertify.db.record.lock;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class RecordLock {

    private int recordNumber;
    private long lockCookie;
    private boolean isLocked;

    /**
     * Creates a new instance of RecordLock with the specified recordNumber
     * 
     * @param recordNumber
     *            - the recordNumber
     */
    RecordLock(int recordNumber) {
        this.recordNumber = recordNumber;
    }

    /**
     * returns the hasCode
     * 
     * @param the
     *            hashcode
     */
    @Override
    public int hashCode() {
        return recordNumber;
    }

    /**
     * returns {@code true} if the specified object is a RecordLock which has the same record number
     * 
     * @return {@code true} if the specified object is a RecordLock which has the same record number
     */
    @Override
    public boolean equals(Object object) {

        if (!(object instanceof RecordLock)) {
            return FALSE;
        }

        RecordLock otherLock = (RecordLock) object;
        return recordNumber == otherLock.recordNumber;
    }

    /**
     * returns the lock cookie
     * 
     * @return the lock cookie
     */
    synchronized long getLockCookie() {
        return lockCookie;
    }

    /**
     * locks this instance of RecordLock. If the instance is already locked then the method will wait until it is available to be locked again. After
     * the instance is locked a new lock cookie is generated
     */
    synchronized void lock() {
        while (isLocked) {
            waitUntillIsUnlocked();
        }
        isLocked = TRUE;

        lockCookie = createLockCookie();
    }

    /**
     * unlocks this instance of RecordLock.
     */
    synchronized void unlock() {
        isLocked = FALSE;
        notifyAll();
    }

    /**
     * return {@code true} if this instance is locked with the specified lockCookie
     * 
     * @param lockCookie
     *            - the lockCookie
     * @return {@code true} if this instance is locked with the specified lockCookie
     */
    synchronized boolean isLockedWithLockCookie(long lockCookie) {
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

    private long createLockCookie() {
        long lockCookie = System.currentTimeMillis();
        return appendRecordNumber(lockCookie);
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
}
