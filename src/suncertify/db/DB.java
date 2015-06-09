package suncertify.db;

import suncertify.db.record.RecordNotFoundException;

public interface DB {

	String[] read(int recordNumber) throws RecordNotFoundException;

	void update(int recNo, String[] data, long lockC) throws RecordNotFoundException, SecurityException;

	int[] find(String[] data);

	int create(String[] data);

	long lock(int recNo) throws RecordNotFoundException;

	void unlock(int recNo, long lockCookie) throws RecordNotFoundException, SecurityException;
}
