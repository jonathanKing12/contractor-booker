package suncertify.db.record;

import suncertify.db.table.TableWriter;
import datasource.DataSourceException;
import datasource.DataSourceFactory;

public class RecordWriter {

	private TableWriter tableWriter;

	public RecordWriter(DataSourceFactory factory) {
		this.tableWriter = new TableWriter(factory);
	}

	public void open() throws DataSourceException {
		tableWriter.open();
	}

	public void updateRecord(Record record) throws DataSourceException {
		int recordNumber = record.getRecordNumber();
		tableWriter.moveToRow(recordNumber);
		writeRecordFields(record);
	}

	// public int writeRecord(Record record) throws DataSourceException {
	// int recordNumber = tableWriter.moveToEndOfLastRow();
	// writeRecordFields(record);
	// return recordNumber;
	// }

	public void close() throws DataSourceException {
		tableWriter.close();
	}

	private void writeRecordFields(Record record) throws DataSourceException {
		tableWriter.writeNextColumn(record.getDeletedFlag());
		tableWriter.writeNextColumn(record.getName());
		tableWriter.writeNextColumn(record.getLocation());
		tableWriter.writeNextColumn(record.getSpecialties());
		tableWriter.writeNextColumn(record.getSize());
		tableWriter.writeNextColumn(record.getRate());
		tableWriter.writeNextColumn(record.getOwner());
	}
}
