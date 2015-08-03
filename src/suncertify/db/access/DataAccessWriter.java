package suncertify.db.access;

import static java.lang.Boolean.TRUE;
import suncertify.db.datasource.DataSourceException;
import suncertify.db.datasource.DataSourceFactory;
import suncertify.db.record.*;

public class DataAccessWriter {

    private RecordWriter recordWriter;
    private RecordParser recordParser;

    /**
     * Constructs a instance of DataAccessWriter with the specified factory.
     * 
     * @param factory
     */
    DataAccessWriter(DataSourceFactory factory) {
        recordWriter = new RecordWriter(factory);
        recordParser = new RecordParser();
    }

    /**
     * Writes the specified record to the data source.
     * 
     * <p>
     * If the record already exists it is overridden otherwise it is written to the end of the data source. The record is not marked as deleted. The
     * record number is assumed to be a positive number.
     * <p>
     * 
     * @param recordNumber
     *            - the number of the record that is to be written to data source
     * @param recordDetails
     *            - the details of to record to be written to data source
     * @throws DataSourceException
     */
    void writeRecord(int recordNumber, String[] recordDetails) throws DataSourceException {
        Record record = recordParser.createRecord(recordNumber, recordDetails);
        writeRecord(record);
    }

    void deleteRecord(int recordNumber, String[] recordDetails) throws DataSourceException {
        Record record = recordParser.createRecord(recordNumber, recordDetails);
        record.setDeleted(TRUE);
        writeRecord(record);
    }

    private void writeRecord(Record record) throws DataSourceException {
        recordWriter.open();
        recordWriter.writeRecord(record);
        recordWriter.close();
    }
}
