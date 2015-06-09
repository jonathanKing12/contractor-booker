package suncertify.db;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class DataAccessLocker {

	private ReadWriteLock lock;

	DataAccessLocker() {
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
