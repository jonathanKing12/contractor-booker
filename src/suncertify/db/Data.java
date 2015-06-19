package suncertify.db;

import suncertify.db.record.RecordNotFoundException;
import datasource.DataSourceFactory;

public class Data implements DB {

	private static DataAccessLocker dataAccessLocker;
	private static RecordLocker recordLocker;
	private DataAccess dataAccess;

	static {
		dataAccessLocker = new DataAccessLocker();
		recordLocker = new RecordLocker();
	}

	public Data(DataSourceFactory factory) {
		dataAccess = new DataAccess(factory);
	}

	@Override
	public void delete(int recNo, long lockCookie) throws RecordNotFoundException, SecurityException {
		verifyRecordExistsAndIsLockedWithLockCookie(recNo, lockCookie);
		try {
			dataAccessLocker.getWriteLock();
			dataAccess.deleteRecord(recNo);
			recordLocker.unlockRecord(recNo, lockCookie);
		} finally {
			dataAccessLocker.returnWriteLock();
		}
	}

	@Override
	public String[] read(int recNo) throws RecordNotFoundException {
		try {
			dataAccessLocker.getReadLock();
			return dataAccess.read(recNo);
		} finally {
			dataAccessLocker.returnReadLock();
		}
	}

	@Override
	public void update(int recNo, String[] data, long lockCookie) throws RecordNotFoundException, SecurityException {

		verifyRecordExistsAndIsLockedWithLockCookie(recNo, lockCookie);
		try {
			dataAccessLocker.getWriteLock();
			dataAccess.update(recNo, data);
		} finally {
			dataAccessLocker.returnWriteLock();
		}
	}

	@Override
	public int[] find(String[] data) {
		try {
			dataAccessLocker.getReadLock();
			return dataAccess.find(data);
		} finally {
			dataAccessLocker.returnReadLock();
		}
	}

	@Override
	public int create(String[] data) {
		try {
			dataAccessLocker.getWriteLock();
			return dataAccess.create(data);
		} catch (Exception e) {
			System.out.println("error " + e.getMessage());
			throw e;
		} finally {

			dataAccessLocker.returnWriteLock();
		}
	}

	@Override
	public long lock(int recNo) throws RecordNotFoundException {
		long lockCookie = recordLocker.lockRecord(recNo);
		try {
			verifyRecordExist(recNo);
			return lockCookie;
		} catch (RecordNotFoundException e) {
			// If this request happens again (request2), and the record still doesn't exist and
			// isn't unlocked, then request2 won't be informed that it doesn't exist and hang
			unlock(recNo, lockCookie);
			throw e;
		}
	}

	@Override
	public void unlock(int recNo, long lockCookie) throws RecordNotFoundException, SecurityException {
		verifyRecordExist(recNo);
		recordLocker.unlockRecord(recNo, lockCookie);
	}

	private void verifyRecordExistsAndIsLockedWithLockCookie(int recNo, long lockCookie) throws RecordNotFoundException {
		verifyRecordExist(recNo);
		recordLocker.verifyRecordIsLockedWithLockCookie(recNo, lockCookie);
	}

	private void verifyRecordExist(int recNo) throws RecordNotFoundException {
		try {
			dataAccessLocker.getReadLock();
			dataAccess.verifyRecordExist(recNo);
		} finally {
			dataAccessLocker.returnReadLock();
		}
	}
}
