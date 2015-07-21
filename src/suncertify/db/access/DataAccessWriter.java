package suncertify.db.access;

import static java.lang.Boolean.TRUE;
import suncertify.db.datasource.DataSourceException;
import suncertify.db.datasource.DataSourceFactory;
import suncertify.db.record.Record;
import suncertify.db.record.RecordParser;
import suncertify.db.record.RecordWriter;

public class DataAccessWriter {

	private RecordWriter recordWriter;

	DataAccessWriter(DataSourceFactory factory) {
		recordWriter = new RecordWriter(factory);
	}

	void write(int recordNumber, String[] recordDetails) throws DataSourceException {
		Record record = createRecord(recordNumber, recordDetails);
		writeRecord(record);
	}

	void deleteRecord(int recordNumber, String[] recordDetails) throws DataSourceException {
		Record record = createRecord(recordNumber, recordDetails);
		record.setDeleted(TRUE);
		writeRecord(record);
	}

	private Record createRecord(int recordNumber, String[] recordDetails) {
		RecordParser parser = new RecordParser();
		return parser.createRecord(recordNumber, recordDetails);
	}

	private void writeRecord(Record record) throws DataSourceException {
		recordWriter.open();
		recordWriter.writeRecord(record);
		recordWriter.close();
	}
}
