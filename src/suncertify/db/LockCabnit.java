package suncertify.db;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import java.util.HashSet;
import java.util.Set;

public class LockCabnit {
	private Set<Lock> locks;

	LockCabnit() {
		locks = new HashSet<>();
	}

	synchronized Lock getOrCreateLock(int number) {

		if (!doesLockExist(number)) {
			createLock(number);
		}
		return getLock(number);
	}

	private boolean doesLockExist(int number) {
		boolean recordExist = FALSE;

		for (Lock recordLock : locks) {
			if (recordLock.hasNumber(number)) {
				recordExist = TRUE;
				break;
			}
		}
		return recordExist;
	}

	private void createLock(int number) {
		Lock lock = new Lock(number);
		locks.add(lock);
	}

	private Lock getLock(int number) {
		Lock recordLock = new Lock(-1);

		for (Lock nextLock : locks) {
			if (nextLock.hasNumber(number)) {
				recordLock = nextLock;
			}
		}
		return recordLock;
	}
}
