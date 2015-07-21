package integration.singleclient;

import suncertify.db.DB;
import suncertify.db.RecordNotFoundException;

public class LockDeleteClient implements Runnable {

	private DB db;
	private int recordNumber;
	private boolean isFailed = true;;

	public LockDeleteClient(DB db, int recordNumber) {
		this.db = db;
		this.recordNumber = recordNumber;
	}

	@Override
	public void run() {
		try {
			long lockCookie = db.lock(recordNumber);
			Thread.sleep(10);
			db.delete(recordNumber, lockCookie);
			isFailed = false;
		} catch (RecordNotFoundException e) {
			isFailed = true;
		} catch (InterruptedException e) {
		}
	}

	public boolean isfailed() {
		return isFailed;
	}
}
