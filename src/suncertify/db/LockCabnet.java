package suncertify.db;

import java.util.HashSet;
import java.util.Set;

public class LockCabnet {

	private Set<Lock> locks;

	LockCabnet() {
		locks = new HashSet<>();
	}

	synchronized Lock getLock(int lockNumber) {

		Lock lock = new Lock(lockNumber);

		if (!locks.contains(lock)) {
			locks.add(lock);
		}
		return getSameLockFromLocks(lock);
	}

	private Lock getSameLockFromLocks(Lock lock) {

		for (Lock nextLock : locks) {
			if (nextLock.equals(lock)) {
				lock = nextLock;
				break;
			}
		}
		return lock;
	}
}
