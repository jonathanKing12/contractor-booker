package suncertify.db;

import suncertify.db.record.RecordNotFoundException;
import datasource.DataSourceException;
import datasource.DataSourceFactory;

public class DataAccessWrapper {

	private DataReader dataReader;
	private DataWriter dataWriter;

	DataAccessWrapper(DataSourceFactory factory) {
		dataReader = new DataReader(factory);
		dataWriter = new DataWriter(factory);
	}

	String[] read(int recNo) throws RecordNotFoundException {
		try {
			return dataReader.read(recNo);
		} catch (DataSourceException e) {
			throw new RuntimeException("");
		}
	}

	void update(int recNo, String[] data) throws RecordNotFoundException {
		try {
			dataWriter.write(recNo, data);
		} catch (DataSourceException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	int[] find(String[] data) {
		try {
			return dataReader.find(data);
		} catch (DataSourceException e) {
			throw new RuntimeException("");
		}
	}

	public int create(String[] data) {
		try {
			int recNo = dataReader.getNextAvailableRecordNumber();
			dataWriter.write(recNo, data);
			return recNo;
		} catch (DataSourceException e) {
			throw new RuntimeException("");
		}
	}

	public void verifyRecordExist(int recordNumber) throws RecordNotFoundException {
		try {
			dataReader.verifyRecordExist(recordNumber);
		} catch (DataSourceException e) {
			throw new RuntimeException("");
		}
	}
}
