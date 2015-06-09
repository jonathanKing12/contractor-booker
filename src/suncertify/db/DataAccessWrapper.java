package suncertify.db;

import suncertify.db.record.RecordNotFoundException;
import datasource.DataSourceException;
import datasource.DataSourceFactory;

public class DataAccessWrapper {

	private DataAccess dataAccess;

	DataAccessWrapper(DataSourceFactory factory) {
		dataAccess = new DataAccess(factory);
	}

	String[] read(int recNo) throws RecordNotFoundException {
		try {
			return dataAccess.read(recNo);
		} catch (DataSourceException e) {
			throw new RuntimeException("");
		}
	}

	void update(int recNo, String[] data) throws RecordNotFoundException {
		try {
			dataAccess.update(recNo, data);
		} catch (DataSourceException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
	}

	int[] find(String[] data) {
		try {
			return dataAccess.find(data);
		} catch (DataSourceException e) {
			throw new RuntimeException("");
		}
	}

	public int create(String[] data) {
		try {
			return dataAccess.create(data);
		} catch (DataSourceException e) {
			throw new RuntimeException("");
		}
	}

	public void verifyRecordExist(int recordNumber) throws RecordNotFoundException {
		try {
			dataAccess.verifyRecordExist(recordNumber);
		} catch (DataSourceException e) {
			throw new RuntimeException("");
		}
	}
}
