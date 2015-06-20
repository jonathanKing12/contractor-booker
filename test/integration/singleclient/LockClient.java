package integration.singleclient;

import suncertify.db.DB;
import suncertify.db.record.RecordNotFoundException;

public class LockClient implements Runnable {

	private DB db;
	private int recordNumber;
	private boolean isFailed = true;;

	public LockClient(DB db, int recordNumber) {
		this.db = db;
		this.recordNumber = recordNumber;
	}

	@Override
	public void run() {
		try {
			long lockCookie = db.lock(recordNumber);
		} catch (RecordNotFoundException e) {
			isFailed = true;
		}
	}

	public boolean isfailed() {
		return isFailed;
	}
}
