package suncertify.db;

import static java.lang.System.out;
import static java.lang.Thread.currentThread;

import java.lang.reflect.Field;

import suncertify.db.record.lock.LockInterruptException;
import suncertify.db.record.lock.RecordLock;

public class LockClient implements Runnable {

	private int lockNumber;
	public long sleepDuration;
	private boolean isLocked;
	private RecordLock lock;

	public LockClient(RecordLock lock, int lockNumber, long sleepDuration) {
		this.lock = lock;
		this.sleepDuration = sleepDuration;
		this.lockNumber = lockNumber;
	}

	@Override
	public void run() {
		out.println(currentThread().getName() + " before locking record " + lockNumber);
		lock.lock();
		out.println(currentThread().getName() + " ater record " + lockNumber + " is locked");
		setIsLockLocked(lock);
		sleep();
		out.println(currentThread().getName() + " before unlocking record " + lockNumber);
		lock.unlock();
	}

	public boolean isLocked() {
		return isLocked;
	}

	private void setIsLockLocked(RecordLock lock) {
		try {
			Field isLockedField = RecordLock.class.getDeclaredField("isLocked");
			isLockedField.setAccessible(true);
			isLocked = isLockedField.getBoolean(lock);

		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			throw new RuntimeException("error when getting is locked field " + e.getMessage());
		}
	}

	private void sleep() {
		try {
			Thread.sleep(sleepDuration);
		} catch (InterruptedException e) {
			String errorMessage = currentThread().getName() + " was interrupted while sleeping " + e.getMessage();
			throw new LockInterruptException(errorMessage);
		}
	}
}
