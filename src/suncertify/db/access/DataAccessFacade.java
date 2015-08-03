package suncertify.db.access;

import suncertify.db.DuplicateKeyException;
import suncertify.db.RecordNotFoundException;
import suncertify.db.datasource.DataSourceException;
import suncertify.db.datasource.DataSourceFactory;

/**
 * Performs Read and writes operations on the data source
 */
public class DataAccessFacade {

    private DataAccessReader dataReader;
    private DataAccessWriter dataWriter;

    /**
     * Constructs a instance of DataAccessFacade with the specified factory
     * 
     * @param factory
     *            - the factory
     */
    public DataAccessFacade(DataSourceFactory factory) {
        dataReader = new DataAccessReader(factory);
        dataWriter = new DataAccessWriter(factory);
    }

    /**
     * Reads a record from the file. Returns an array where each element is a record value.
     * 
     * @param recordNumber
     *            - the number of the record that is read.
     * @return
     * @throws RecordNotFoundException
     *             if the record does not exist or is marked as deleted in the data source
     * @throws DataSourceException
     *             if a record can't be read from the data source or the connection to the data source fails to close afterwards
     */
    String[] read(int recordNumber) throws RecordNotFoundException, DataSourceException {
        return dataReader.read(recordNumber);
    }

    /**
     * Modifies the fields of a record. The new value for field n appears in data[n].
     * 
     * @param recordNumber
     *            - the number of the record that is to be updated.
     * @param data
     *            - the array where each element contains the new value for the record
     * @throws RecordNotFoundException
     *             if the record does not exist or is marked as deleted in the data source
     * @throws DataSourceException
     *             if a record can't be updated or the connection to the data source fails to close afterwards
     */
    void update(int recordNumber, String[] data) throws RecordNotFoundException,
            DataSourceException {
        dataWriter.writeRecord(recordNumber, data);
    }

    /**
     * Deletes a record, making the record number and associated disk storage available for reuse.
     * 
     * @param recordNumber
     *            - the number of the record that is deleted.
     * @throws RecordNotFoundException
     *             if the record does not exist or is marked as deleted in the data source
     * @throws DataSourceException
     *             if a record can't be updated or the connection to the data source fails to close afterwards
     */
    void deleteRecord(int recordNumber) throws RecordNotFoundException, DataSourceException {
        String[] data = dataReader.read(recordNumber);
        dataWriter.deleteRecord(recordNumber, data);
    }

    /**
     * Returns an array of record numbers that match the specified criteria. Field n in the data source is described by criteria[n]. A null value in
     * criteria[n] matches any field value. A non-null value in criteria[n] matches any field value that begins with criteria[n]. (For example, "Fred"
     * matches "Fred" or "Freddy".)
     * 
     * @param data
     *            - the criteria
     * @return the array of record numbers.
     * @throws DataSourceException
     *             if a record can't be updated or the connection to the data source fails to close afterwards
     */
    int[] find(String[] data) throws DataSourceException {
        return dataReader.find(data);
    }

    /**
     * Creates a new record in the database (possibly reusing a deleted entry). Inserts the given data, and returns the record number of the new
     * record.
     * 
     * @param data
     *            - the contents of the record that is to be created
     * @return the number of the newly created record
     * @throws DuplicateKeyException
     *             if a record with the same name and location already exist.
     * @throws DataSourceException
     *             if a record can't be created or the connection to the data source fails to close afterwards
     */
    int create(String[] data) throws DataSourceException, DuplicateKeyException {
        dataReader.verifyKeyisUnique(data);
        int recordNumber = dataReader.getNextAvailableRecordNumber();
        dataWriter.writeRecord(recordNumber, data);
        return recordNumber;
    }

    /**
     * Checks if a record with the specified recordNumber exists.
     * 
     * @param recordNumber
     *            - the number of the record that is to be verified
     * @throws RecordNotFoundException
     *             if the record does not exist or is marked as deleted in the data source
     * @throws DataSourceException
     *             If the record existence failed to be verified
     */
    void verifyRecordExist(int recordNumber) throws RecordNotFoundException, DataSourceException {
        dataReader.verifyRecordExist(recordNumber);
    }
}
