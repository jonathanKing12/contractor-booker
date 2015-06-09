package suncertify.db;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class DataSourceAccessLocker {

	private ReadWriteLock lock;

	DataSourceAccessLocker() {
		lock = new ReentrantReadWriteLock();
	}

	void getReadLock() {
		lock.readLock().lock();
	}

	void getWriteLock() {
		lock.writeLock().lock();
	}

	void returnReadLock() {
		lock.readLock().unlock();
	}

	void returnWriteLock() {
		lock.writeLock().unlock();
	}
}
