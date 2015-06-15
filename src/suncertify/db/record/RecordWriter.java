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

	public void writeRecord(Record record) throws DataSourceException {
		int recordNumber = record.getRecordNumber();
		System.out.println("write record moving to row " + recordNumber);
		tableWriter.moveToRow(recordNumber);
		writeRecordFields(record);
	}

	public void close() throws DataSourceException {
		tableWriter.close();
	}

	private void writeRecordFields(Record record) throws DataSourceException {
		String deletedStatus = getRecordDeletedStatus(record);

		tableWriter.writeNextColumn(deletedStatus);
		tableWriter.writeNextColumn(record.getName());
		tableWriter.writeNextColumn(record.getLocation());
		tableWriter.writeNextColumn(record.getSpecialties());
		tableWriter.writeNextColumn(record.getSize());
		tableWriter.writeNextColumn(record.getRate());
		tableWriter.writeNextColumn(record.getOwner());
	}

	private String getRecordDeletedStatus(Record record) {
		if (record.isDeleted()) {
			return "8000";
		}
		return "00";
	}
}
