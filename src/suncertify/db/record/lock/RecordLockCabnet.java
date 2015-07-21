package suncertify.db.record.lock;

import java.util.HashSet;
import java.util.Set;

public class RecordLockCabnet {

    private Set<RecordLock> recordLocks;

    /**
     * Creates a new RecordLockCabnet instance.
     */
    RecordLockCabnet() {
        recordLocks = new HashSet<>();
    }

    /**
     * Returns {@code true} if a RecodLock instance exist that has the specified lockNumber
     * 
     * @param lockNumber
     *            - the lockNumber
     * @return {@code true} if a RecodLock instance exists that has the specified lockNumber
     */
    synchronized boolean isContainingRecordLockWithLockNumber(int lockNumber) {
        RecordLock recordLock = new RecordLock(lockNumber);
        return recordLocks.contains(recordLock);
    }

    /**
     * returns a recordLock that has the specified lockNumber. The recordLock will be created if it does not exist.
     * 
     * @param lockNumber
     *            - the lockNumber
     * @return the recordLock;
     */
    synchronized RecordLock getLock(int lockNumber) {

        RecordLock recordLock = new RecordLock(lockNumber);

        if (!recordLocks.contains(recordLock)) {
            recordLocks.add(recordLock);
        }
        return geRecordLockFromRecordLocks(recordLock);
    }

    private RecordLock geRecordLockFromRecordLocks(RecordLock recordLock) {

        for (RecordLock nextRecordLock : recordLocks) {
            if (nextRecordLock.equals(recordLock)) {
                recordLock = nextRecordLock;
                break;
            }
        }
        return recordLock;
    }
}
