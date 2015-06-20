package suncertify.db;

import static java.lang.System.out;
import static java.lang.Thread.currentThread;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class LockTest {

	@Test
	public void shouldlockAndUnluck() throws InterruptedException {
		printHeader("shouldlockAndUnluck");
		LockClient client1 = new LockClient(new Lock(0), 0, 0);
		Thread thread = new Thread(client1);
		thread.start();
		thread.join();
		assertTrue(client1.isLocked());
	}

	@Test
	public void shouldlockAndWait() throws InterruptedException {
		printHeader("shouldlockAndWait");
		Lock lock = new Lock(1);
		LockClient client1 = new LockClient(lock, 1, 200);
		LockClient client2 = new LockClient(lock, 1, 200);
		Thread thread1 = new Thread(client1);
		Thread thread2 = new Thread(client2);
		thread1.start();
		thread2.start();
		thread1.join();
		thread2.join();
		assertTrue(client1.isLocked());
		assertTrue(client2.isLocked());
	}

	@Test
	public void shouldlockADifferentLoks() throws InterruptedException {
		printHeader("shouldlockADifferentLoks");
		Lock lock2 = new Lock(2);
		Lock lock3 = new Lock(3);
		LockClient client1 = new LockClient(lock2, 2, 500);
		LockClient client2 = new LockClient(lock3, 3, 0);
		Thread thread1 = new Thread(client1);
		Thread thread2 = new Thread(client2);
		thread1.start();
		thread2.start();
		thread1.join();
		thread2.join();
		assertTrue(client1.isLocked());
		assertTrue(client2.isLocked());
	}

	@Test
	public void shouldlockFor10Cllients() throws InterruptedException {
		printHeader("shouldlockFor10Cllients");
		Lock lock10 = createLock(10);

		List<Thread> threads = new ArrayList<>(10);
		for (int i = 0; i < 10; i++) {
			threads.add(createThread(lock10, 10, 50));
		}

		for (int i = 0; i < threads.size(); i++) {
			threads.get(i).start();
		}

		for (int i = 0; i < threads.size(); i++) {
			threads.get(i).join();
		}

	}

	private Thread createThread(Lock lock, int recordNumber, long duration) {
		LockClient client = new LockClient(lock, recordNumber, duration);
		return new Thread(client);
	}

	private void printHeader(String message) {
		out.println();
		out.println("-- -- test " + message + " -- --");
	}

	private Lock createLock(final int lockNumber) {
		return new Lock(lockNumber) {
			@Override
			synchronized void unlock() {
				out.println(currentThread().getName() + " ater record " + lockNumber + " is unlocked");
				super.unlock();
			}
		};
	}

}
