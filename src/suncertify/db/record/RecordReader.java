package suncertify.db.record;

import suncertify.db.table.TableReader;
import datasource.DataSourceException;
import datasource.DataSourceFactory;

public class RecordReader {

	private TableReader tableReader;

	public RecordReader(DataSourceFactory factory) {
		this.tableReader = new TableReader(factory);
	}

	public void open() throws DataSourceException {
		tableReader.open();
	}

	public boolean hasRecord(int recordNumber) throws DataSourceException {
		return tableReader.hasRow(recordNumber);
	}

	public Record readRecord(int recordNumber) throws DataSourceException {
		tableReader.moveToRow(recordNumber);

		String deletedFlag = tableReader.readNextColumn();
		boolean isDeleted = deletedFlag.equals("8000");
		String name = tableReader.readNextColumn();
		String location = tableReader.readNextColumn();
		String specialties = tableReader.readNextColumn();
		String size = tableReader.readNextColumn();
		String rate = tableReader.readNextColumn();
		String owner = tableReader.readNextColumn();

		RecordBuilder builder = new RecordBuilder();
		return builder.addName(name).addLocation(location).addSpecialties(specialties).addSize(size).addRate(rate).addOwner(owner)
				.addRecordNumber(recordNumber).addDeletedFlag(isDeleted).build();
	}

	public void close() throws DataSourceException {
		tableReader.close();
	}
}
