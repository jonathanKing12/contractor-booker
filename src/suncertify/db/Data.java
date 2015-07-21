package suncertify.db;

import suncertify.db.access.DataAccessFacadeWrapper;
import suncertify.db.access.DataAccessReadWriteLockKeeper;
import suncertify.db.datasource.DataSourceFactory;
import suncertify.db.record.lock.RecordLockKeeper;

public class Data implements DB {

    private DataAccessReadWriteLockKeeper dataAccessKeeper;
    private RecordLockKeeper recordKeeper;
    private DataAccessFacadeWrapper dataAccessWrapper;

    public Data(DataSourceFactory factory) {
        dataAccessWrapper = new DataAccessFacadeWrapper(factory);
        dataAccessKeeper = new DataAccessReadWriteLockKeeper();
        recordKeeper = new RecordLockKeeper();
    }

    @Override
    public void delete(int recNo, long lockCookie) throws RecordNotFoundException,
            SecurityException {

        verifyRecordExistsAndIsLockedWithLockCookie(recNo, lockCookie);
        try {
            dataAccessKeeper.getWriteLock();
            dataAccessWrapper.deleteRecord(recNo);
            recordKeeper.unlockRecord(recNo, lockCookie);
        } finally {
            dataAccessKeeper.returnWriteLock();
        }
    }

    @Override
    public String[] read(int recNo) throws RecordNotFoundException {
        try {
            dataAccessKeeper.getReadLock();
            return dataAccessWrapper.read(recNo);
        } finally {
            dataAccessKeeper.returnReadLock();
        }
    }

    @Override
    public void update(int recNo, String[] data, long lockCookie) throws RecordNotFoundException,
            SecurityException {

        verifyRecordExistsAndIsLockedWithLockCookie(recNo, lockCookie);
        try {
            dataAccessKeeper.getWriteLock();
            dataAccessWrapper.update(recNo, data);
        } finally {
            dataAccessKeeper.returnWriteLock();
        }
    }

    @Override
    public int[] find(String[] data) {
        try {
            dataAccessKeeper.getReadLock();
            return dataAccessWrapper.find(data);
        } finally {
            dataAccessKeeper.returnReadLock();
        }
    }

    @Override
    public int create(String[] data) throws DuplicateKeyException {
        try {
            dataAccessKeeper.getWriteLock();
            return dataAccessWrapper.create(data);
        } finally {
            dataAccessKeeper.returnWriteLock();
        }
    }

    @Override
    public long lock(int recNo) throws RecordNotFoundException {
        long lockCookie = recordKeeper.lockRecord(recNo);
        try {
            verifyRecordExist(recNo);
            return lockCookie;
        } catch (RecordNotFoundException e) {
            recordKeeper.unlockRecord(recNo, lockCookie);
            throw e;
        }
    }

    @Override
    public void unlock(int recNo, long lockCookie) throws RecordNotFoundException,
            SecurityException {
        verifyRecordExistsAndIsLockedWithLockCookie(recNo, lockCookie);
        recordKeeper.unlockRecord(recNo, lockCookie);
    }

    private void verifyRecordExistsAndIsLockedWithLockCookie(int recNo, long lockCookie)
            throws RecordNotFoundException, SecurityException {
        verifyRecordExist(recNo);
        recordKeeper.verifyRecordIsLockedWithLockCookie(recNo, lockCookie);
    }

    private void verifyRecordExist(int recNo) throws RecordNotFoundException {
        try {
            dataAccessKeeper.getReadLock();
            dataAccessWrapper.verifyRecordExist(recNo);
        } finally {
            dataAccessKeeper.returnReadLock();
        }
    }
}
