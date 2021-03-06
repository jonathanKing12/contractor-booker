package suncertify.db;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;

import suncertify.db.record.lock.InvalidLockCookieException;
import suncertify.db.record.lock.RecordLockKeeper;

public class RecordLockerTest {

	RecordLockKeeper recordLocker;

	@Before
	public void setup() {
		recordLocker = new RecordLockKeeper();
	}

	@Test
	public void shouldVerifyRecordIsLocked() {
		long lockCookie = recordLocker.lockRecord(1);
		recordLocker.verifyRecordIsLockedWithLockCookie(1, lockCookie);
	}

	@Test(expected = InvalidLockCookieException.class)
	public void shouldNotVerifyRecordIsLocked() {
		long lockCookie = recordLocker.lockRecord(1);
		recordLocker.verifyRecordIsLockedWithLockCookie(1, lockCookie + 1);
	}

	@Test(expected = InvalidLockCookieException.class)
	public void shouldNotVerifyRecordIsLocked2() {
		long lockCookie = recordLocker.lockRecord(1);
		recordLocker.verifyRecordIsLockedWithLockCookie(2, lockCookie);
	}

	@Test(expected = InvalidLockCookieException.class)
	public void shouldNotVerifyRecordIsLocked3() {
		long lockCookie = recordLocker.lockRecord(1);
		recordLocker.verifyRecordIsLockedWithLockCookie(2, lockCookie + 1);
	}

	@Test
	public void shouldVerifySecondRecordIsLocked() {
		long lockCookie = recordLocker.lockRecord(1);
		long lockCookie2 = recordLocker.lockRecord(2);
		recordLocker.verifyRecordIsLockedWithLockCookie(2, lockCookie2);
	}

	@Test(expected = InvalidLockCookieException.class)
	public void shouldNotVerifySecondRecordIsLocked() {
		long lockCookie = recordLocker.lockRecord(1);
		long lockCookie2 = recordLocker.lockRecord(2);
		recordLocker.verifyRecordIsLockedWithLockCookie(2, lockCookie);
	}

	@Test
	public void shouldUnlock() {
		long lockCookie = recordLocker.lockRecord(1);
		recordLocker.unlockRecord(1, lockCookie);
	}

	@Test(expected = InvalidLockCookieException.class)
	public void shouldNotUnlock() {
		recordLocker.unlockRecord(1, 0000);
	}

	@Test(expected = InvalidLockCookieException.class)
	public void shouldNotUnlock2() {
		long lockCookie = recordLocker.lockRecord(1);
		recordLocker.unlockRecord(1, 0000);
	}

	@Test(expected = InvalidLockCookieException.class)
	public void shouldNotBeLockedAfterUnlocked() {
		long lockCookie = recordLocker.lockRecord(1);
		recordLocker.unlockRecord(1, lockCookie);
		recordLocker.verifyRecordIsLockedWithLockCookie(1, lockCookie);
	}

	@Test
	public void shouldNotunLockAllLocks() throws InterruptedException {
		long lockCookie = recordLocker.lockRecord(1);
		long lockCookie2 = recordLocker.lockRecord(2);
		recordLocker.unlockRecord(1, lockCookie);
		recordLocker.verifyRecordIsLockedWithLockCookie(2, lockCookie2);
	}

	@Test
	public void shouldVerifyLockedWithNewKey() {
		long lockCookie = recordLocker.lockRecord(1);
		recordLocker.unlockRecord(1, lockCookie);
		long lockCookie2 = recordLocker.lockRecord(1);
		recordLocker.verifyRecordIsLockedWithLockCookie(1, lockCookie2);
	}

	@Test(expected = InvalidLockCookieException.class)
	public void shouldNotVerifyLockedWithOldKey() throws InterruptedException {
		long lockCookie = recordLocker.lockRecord(1);
		recordLocker.unlockRecord(1, lockCookie);
		long lockCookie2 = recordLocker.lockRecord(1);
		recordLocker.verifyRecordIsLockedWithLockCookie(1, lockCookie);
	}

	@Test
	public void shouldProduceSameCookie() throws InterruptedException {
		List<Long> allCookies = new ArrayList<>();
		TreeSet<Long> uniquCookies = new TreeSet<>();

		for (int i = 0; i < 10000; i++) {
			int recordNumber = i + 1;
			long lockCookie = recordLocker.lockRecord(recordNumber);
			recordLocker.unlockRecord(recordNumber, lockCookie);
			allCookies.add(lockCookie);
			uniquCookies.add(lockCookie);
		}

		assertEquals(allCookies.size(), uniquCookies.size());
	}
}
