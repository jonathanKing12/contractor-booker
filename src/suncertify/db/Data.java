package suncertify.db;

import suncertify.db.record.RecordNotFoundException;
import datasource.DataSourceFactory;

public class Data implements DB {

	private static DataSourceAccessLocker dataSourceAccessLocker;
	private static RecordLocker recordLocker;
	private DataAccessWrapper dataAccessWrapper;

	static {
		dataSourceAccessLocker = new DataSourceAccessLocker();
		recordLocker = new RecordLocker();
	}

	public Data(DataSourceFactory factory) {
		dataAccessWrapper = new DataAccessWrapper(factory);
	}

	@Override
	public String[] read(int recNo) throws RecordNotFoundException {
		try {
			dataSourceAccessLocker.getReadLock();
			return dataAccessWrapper.read(recNo);
		} finally {
			dataSourceAccessLocker.returnReadLock();
		}
	}

	@Override
	public void update(int recNo, String[] data, long lockCookie) throws RecordNotFoundException, SecurityException {
		recordLocker.verifyRecordIsLockedWithLockCookie(recNo, lockCookie);
		try {
			dataSourceAccessLocker.getWriteLock();
			dataAccessWrapper.update(recNo, data);
		} finally {
			dataSourceAccessLocker.returnWriteLock();
		}
	}

	@Override
	public int[] find(String[] data) {
		try {
			dataSourceAccessLocker.getReadLock();
			return dataAccessWrapper.find(data);
		} finally {
			dataSourceAccessLocker.returnReadLock();
		}
	}

	@Override
	public int create(String[] data) {
		try {
			dataSourceAccessLocker.getWriteLock();
			return dataAccessWrapper.create(data);
		} finally {
			dataSourceAccessLocker.returnWriteLock();
		}
	}

	@Override
	public long lock(int recNo) throws RecordNotFoundException {
		long lockCookie = recordLocker.lockRecord(recNo);
		try {
			dataSourceAccessLocker.getReadLock();
			dataAccessWrapper.verifyRecordExist(recNo);
			return lockCookie;
		} catch (Exception e) {
			unlock(recNo, lockCookie);
			throw e;
		} finally {
			dataSourceAccessLocker.returnReadLock();
		}
	}

	@Override
	public void unlock(int recNo, long lockCookie) throws RecordNotFoundException, SecurityException {
		try {
			dataSourceAccessLocker.getReadLock();
			dataAccessWrapper.verifyRecordExist(recNo);
		} finally {
			dataSourceAccessLocker.returnReadLock();
		}
		recordLocker.unlockRecord(recNo, lockCookie);
	}
}
