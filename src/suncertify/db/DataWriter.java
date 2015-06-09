package suncertify.db;

import static suncertify.db.record.RecordUtils.toRecord;
import suncertify.db.record.Record;
import suncertify.db.record.RecordWriter;
import datasource.DataSourceException;
import datasource.DataSourceFactory;

public class DataWriter {

	private RecordWriter recordWriter;

	DataWriter(DataSourceFactory factory) {
		recordWriter = new RecordWriter(factory);
	}

	void write(int recordNo, String[] data) throws DataSourceException {
		Record record = toRecord(recordNo, data);
		recordWriter.open();
		recordWriter.updateRecord(record);
		recordWriter.close();
	}

	// int create(String[] data) throws DataSourceException {
	// Record record = toRecord(data);
	// recordWriter.open();
	// int recordNumber = recordWriter.writeRecord(record);
	// recordWriter.close();
	// return recordNumber;
	// }
}
