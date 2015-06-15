package suncertify.db;

import static java.lang.Boolean.TRUE;
import static suncertify.db.record.RecordUtils.createRecord;
import suncertify.db.record.Record;
import suncertify.db.record.RecordWriter;
import datasource.DataSourceException;
import datasource.DataSourceFactory;

public class DataWriter {

	private RecordWriter recordWriter;

	DataWriter(DataSourceFactory factory) {
		recordWriter = new RecordWriter(factory);
	}

	void write(int recordNumber, String[] recordData) throws DataSourceException {
		Record record = createRecord(recordNumber, recordData);
		writeRecord(record);
	}

	public void deleteRecord(int recordNumber, String[] recordData) throws DataSourceException {
		Record record = createRecord(recordNumber, recordData);
		record.setDeleted(TRUE);
		writeRecord(record);
	}

	private void writeRecord(Record record) throws DataSourceException {
		recordWriter.open();
		recordWriter.writeRecord(record);
		recordWriter.close();
	}
}
