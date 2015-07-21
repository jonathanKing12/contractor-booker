package suncertify.db.access;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class DataAccessReadWriteLockKeeper {

    private ReadWriteLock lock;

    public DataAccessReadWriteLockKeeper() {
        lock = new ReentrantReadWriteLock();
    }

    public void getReadLock() {
        lock.readLock().lock();
    }

    public void getWriteLock() {
        lock.writeLock().lock();
    }

    public void returnReadLock() {
        lock.readLock().unlock();
    }

    public void returnWriteLock() {
        lock.writeLock().unlock();
    }
}
