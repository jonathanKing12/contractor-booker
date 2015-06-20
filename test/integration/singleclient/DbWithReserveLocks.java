package integration.singleclient;

import suncertify.db.DB;
import suncertify.db.record.RecordNotFoundException;

public class DbWithReserveLocks implements DB {

	private DB db;

	public DbWithReserveLocks(DB db) {
		this.db = db;
	}

	@Override
	public String[] read(int recordNumber) throws RecordNotFoundException {
		return db.read(recordNumber);
	}

	@Override
	public void update(int recNo, String[] data, long lockCookie) throws RecordNotFoundException, SecurityException {
		db.update(recNo, data, lockCookie);
	}

	@Override
	public void delete(int recNo, long lockCookie) throws RecordNotFoundException, SecurityException {
		db.delete(recNo, lockCookie);
	}

	@Override
	public int[] find(String[] data) {
		return db.find(data);
	}

	@Override
	public int create(String[] data) {
		return db.create(data);
	}

	@Override
	public long lock(int recNo) throws RecordNotFoundException {
		if (recNo == 26) {
			throw new RecordNotFoundException("cant lock record number 26 it is reserved a update unit test");
		}
		return db.lock(recNo);
	}

	public long lockRecord26() throws RecordNotFoundException {
		return db.lock(26);
	}

	@Override
	public void unlock(int recNo, long lockCookie) throws RecordNotFoundException, SecurityException {
		db.unlock(recNo, lockCookie);
	}

}
