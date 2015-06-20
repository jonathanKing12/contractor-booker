package integration.singleclient;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import integration.helper.TestDataUtil;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import suncertify.db.InvalidLockCookieException;
import suncertify.db.record.RecordNotFoundException;

public class LockTest extends BaseTest {

	private static final String FILE_NAME = "lock";

	@BeforeClass
	public static void backupFile() throws IOException {
		TestDataUtil.backupFile(FILE_NAME);
	}

	@Test
	public void test() throws RecordNotFoundException {
		int equal = 0;
		for (int i = 0; i < 100; i++) {
			long lockCookie = lockAndUnLockRecord();
			long lockCookie2 = lockAndUnLockRecord();

			if (lockCookie == lockCookie2) {
				equal++;
			}
		}

		assertTrue(equal <= 3);
	}

	@Test(expected = RecordNotFoundException.class)
	public void shouldNotUnLockRecordThatDoesNotExist() throws SecurityException, RecordNotFoundException {
		db.unlock(-1, 0000);
	}

	@Test(expected = InvalidLockCookieException.class)
	public void shouldNotUnLockRecordThatIsLockedByDifferentCookie() throws SecurityException, RecordNotFoundException {
		long lockCookie = db.lock(1);
		db.unlock(1, lockCookie + 1);
	}

	@Test
	public void shouldNotLockDeletedRecord() throws SecurityException, RecordNotFoundException, InterruptedException {
		LockDeleteClient client1 = new LockDeleteClient(db, 1);
		LockClient client2 = new LockClient(db, 1);

		Thread thread1 = startClient(client1);
		Thread thread2 = startClient(client2);
		thread1.join();
		thread2.join();

		assertFalse(client1.isfailed());
		assertTrue(client2.isfailed());

	}

	private Thread startClient(Runnable runnable) {
		Thread thread1 = new Thread(runnable);
		thread1.start();
		return thread1;
	}

	@AfterClass
	public static void restorFile() throws IOException {
		TestDataUtil.restorFile(FILE_NAME);
	}

	@Override
	protected String getFileName() {
		return FILE_NAME;
	}

	private long lockAndUnLockRecord() throws RecordNotFoundException {
		int recordNumber = 1;
		long lockCookie = db.lock(recordNumber);
		db.unlock(recordNumber, lockCookie);
		return lockCookie;
	}

}
