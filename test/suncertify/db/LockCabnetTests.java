package suncertify.db;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class LockCabnetTests {

	private LockCabnet lockCabnet;

	@Before
	public void setUp() {
		lockCabnet = new LockCabnet();
	}

	@Test
	public void shouldCreateOneLockFromOneClient() throws InterruptedException {
		LockCabnetClient client = createLockCabnetClient(1);
		runClient(client);

		Set<Lock> locks = getCreatedLocks();
		assertEquals(1, locks.size());
	}

	@Test
	public void shouldCreateTwoLocks() throws InterruptedException {
		LockCabnetClient client1 = createLockCabnetClient(1);
		LockCabnetClient client2 = createLockCabnetClient(2);
		runClient(client1);
		runClient(client2);

		Set<Lock> locks = getCreatedLocks();
		assertEquals(2, locks.size());
	}

	@Test
	public void shouldCreateOneLockFromTwoClient() throws InterruptedException {
		LockCabnetClient client1 = createLockCabnetClient(1);
		LockCabnetClient client2 = createLockCabnetClient(1);
		runClient(client1);
		runClient(client2);

		Set<Lock> locks = getCreatedLocks();
		assertEquals(1, locks.size());
	}

	@Test
	public void shouldCreateThreeLockFromFiveClient() throws InterruptedException {
		List<LockCabnetClient> clients = new ArrayList<>();
		clients.add(createLockCabnetClient(-1));
		clients.add(createLockCabnetClient(2));
		clients.add(createLockCabnetClient(3));
		clients.add(createLockCabnetClient(2));
		clients.add(createLockCabnetClient(-1));

		for (LockCabnetClient client : clients) {
			runClient(client);
		}
		Set<Lock> locks = getCreatedLocks();
		assertEquals(3, locks.size());
	}

	private LockCabnetClient createLockCabnetClient(int lockNumber) {
		return new LockCabnetClient(lockCabnet, lockNumber);
	}

	private void runClient(LockCabnetClient client) throws InterruptedException {
		Thread thread = new Thread(client);
		thread.start();
		thread.join();
	}

	private Set<Lock> getCreatedLocks() {
		try {
			Field locksField = LockCabnet.class.getDeclaredField("locks");
			locksField.setAccessible(true);
			return (Set<Lock>) locksField.get(lockCabnet);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			throw new RuntimeException("error when getting locks " + e.getMessage());
		}
	}
}
