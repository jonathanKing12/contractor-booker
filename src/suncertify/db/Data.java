package suncertify.db;

import suncertify.db.record.RecordNotFoundException;
import datasource.DataSourceFactory;

public class Data implements DB {

	private static DataAccessLocker dataAccessLocker;
	private static RecordLocker recordLocker;
	private DataAccessWrapper dataAccessWrapper;

	static {
		dataAccessLocker = new DataAccessLocker();
		recordLocker = new RecordLocker();
	}

	public Data(DataSourceFactory factory) {
		dataAccessWrapper = new DataAccessWrapper(factory);
	}

	@Override
	public String[] read(int recNo) throws RecordNotFoundException {
		try {
			dataAccessLocker.getReadLock();
			return dataAccessWrapper.read(recNo);
		} finally {
			dataAccessLocker.returnReadLock();
		}
	}

	@Override
	public void update(int recNo, String[] data, long lockCookie) throws RecordNotFoundException, SecurityException {
		verifyRecordExist(recNo);
		recordLocker.verifyRecordIsLockedWithLockCookie(recNo, lockCookie);
		try {
			dataAccessLocker.getWriteLock();
			dataAccessWrapper.update(recNo, data);
		} finally {
			dataAccessLocker.returnWriteLock();
		}
	}

	@Override
	public int[] find(String[] data) {
		try {
			dataAccessLocker.getReadLock();
			return dataAccessWrapper.find(data);
		} finally {
			dataAccessLocker.returnReadLock();
		}
	}

	@Override
	public int create(String[] data) {
		try {
			dataAccessLocker.getWriteLock();
			return dataAccessWrapper.create(data);
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
			// If same request happens again (request2), and the record still doesn't exist and
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

	private void verifyRecordExist(int recNo) throws RecordNotFoundException {
		try {
			dataAccessLocker.getReadLock();
			dataAccessWrapper.verifyRecordExist(recNo);
		} finally {
			dataAccessLocker.returnReadLock();
		}
	}
}
