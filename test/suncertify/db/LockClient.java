package suncertify.db;

import static java.lang.System.out;
import static java.lang.Thread.currentThread;

import java.lang.reflect.Field;

public class LockClient implements Runnable {

	private int lockNumber;
	public long sleepDuration;
	private boolean isLocked;
	private Lock lock;

	public LockClient(Lock lock, int lockNumber, long sleepDuration) {
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

	private void setIsLockLocked(Lock lock) {
		try {
			Field isLockedField = Lock.class.getDeclaredField("isLocked");
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
