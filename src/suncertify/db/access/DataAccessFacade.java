package suncertify.db.access;

import suncertify.db.DuplicateKeyException;
import suncertify.db.RecordNotFoundException;
import suncertify.db.datasource.DataSourceException;
import suncertify.db.datasource.DataSourceFactory;

public class DataAccessFacade {

    private DataAccessReader dataReader;
    private DataAccessWriter dataWriter;

    public DataAccessFacade(DataSourceFactory factory) {
        dataReader = new DataAccessReader(factory);
        dataWriter = new DataAccessWriter(factory);
    }

    String[] read(int recordNumber) throws RecordNotFoundException, DataSourceException {
        return dataReader.read(recordNumber);
    }

    void update(int recordNumber, String[] data) throws RecordNotFoundException,
            DataSourceException {
        dataWriter.write(recordNumber, data);
    }

    int[] find(String[] data) throws DataSourceException {
        return dataReader.find(data);
    }

    int create(String[] data) throws DataSourceException, DuplicateKeyException {
        dataReader.verifyKeyisUnique(data);
        int recordNumber = dataReader.getNextAvailableRecordNumber();
        dataWriter.write(recordNumber, data);
        return recordNumber;
    }

    void verifyRecordExist(int recordNumber) throws RecordNotFoundException, DataSourceException {
        dataReader.verifyRecordExist(recordNumber);
    }

    void deleteRecord(int recordNumber) throws RecordNotFoundException, DataSourceException {
        String[] data = dataReader.read(recordNumber);
        dataWriter.deleteRecord(recordNumber, data);
    }
}
