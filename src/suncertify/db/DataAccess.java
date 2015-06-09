package suncertify.db;

import suncertify.db.record.RecordNotFoundException;
import datasource.DataSourceException;
import datasource.DataSourceFactory;

public class DataAccess {

	private DataReader dataReader;
	private DataWriter dataWriter;

	public DataAccess(DataSourceFactory factory) {
		dataReader = new DataReader(factory);
		dataWriter = new DataWriter(factory);
	}

	public String[] read(int recNo) throws RecordNotFoundException, DataSourceException {
		return dataReader.read(recNo);
	}

	public void update(int recordNumber, String[] data) throws RecordNotFoundException, DataSourceException {
		verifyRecordExist(recordNumber);
		dataWriter.write(recordNumber, data);
	}

	public int[] find(String[] data) throws DataSourceException {
		return dataReader.find(data);
	}

	public int create(String[] data) throws DataSourceException {
		int recordNumber = dataReader.getNextAvailableRecordNumber();
		dataWriter.write(recordNumber, data);
		return recordNumber;
	}

	public void verifyRecordExist(int recordNumber) throws RecordNotFoundException, DataSourceException {
		dataReader.verifyRecordExist(recordNumber);
	}
}
