package suncertify.db.access;

import java.util.Arrays;

import suncertify.db.DuplicateKeyException;
import suncertify.db.RecordNotFoundException;
import suncertify.db.datasource.DataSourceException;
import suncertify.db.datasource.DataSourceFactory;

/**
 * Wraps a dataAccessFacade instance and re-throws DataSourceException as DataAccessException.
 */
public class DataAccessFacadeWrapper {

    private DataAccessFacade dataAccessFacade;

    /**
     * Constructs a instance of DataAccessFacadeWrapper with the specified factory
     * 
     * @param factory
     *            - the factory
     */
    public DataAccessFacadeWrapper(DataSourceFactory factory) {
        dataAccessFacade = new DataAccessFacade(factory);
    }

    /**
     * Reads a record from the data source. Returns an array where each element is a record value.
     * 
     * @param recordNumber
     *            - the number of the record that is read.
     * @return the record data
     * @throws RecordNotFoundException
     *             if a RecordNotFoundException occurs
     * @throws DataAccessException
     *             if a DataSourceException occurs
     */
    public String[] read(int recordNumber) throws RecordNotFoundException, DataAccessException {
        try {
            return dataAccessFacade.read(recordNumber);
        } catch (DataSourceException e) {
            String message = createReadErrorMessage(recordNumber, e);
            throw new DataAccessException(message);
        }
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
     * @throws DataAccessException
     *             if a DataSourceException occurs
     */
    public void update(int recordNumber, String[] data) throws RecordNotFoundException,
            DataAccessException {
        try {
            dataAccessFacade.update(recordNumber, data);
        } catch (DataSourceException e) {
            String message = createUpdateErrorMessage(recordNumber, data, e);
            throw new DataAccessException(message);
        }
    }

    public int[] find(String[] data) {
        try {
            return dataAccessFacade.find(data);
        } catch (DataSourceException e) {
            String message = createFindErrorMessage(data, e);
            throw new DataAccessException(message);
        }
    }

    public int create(String[] data) throws DuplicateKeyException {
        try {
            return dataAccessFacade.create(data);
        } catch (DataSourceException e) {
            String message = createCreateErrorMessage(data, e);
            throw new DataAccessException(message);
        }
    }

    public void verifyRecordExist(int recordNumber) throws RecordNotFoundException {
        try {
            dataAccessFacade.verifyRecordExist(recordNumber);
        } catch (DataSourceException e) {
            String message = createVerifyErrorMessage(recordNumber, e);
            throw new DataAccessException(message);
        }
    }

    public void deleteRecord(int recordNumber) throws RecordNotFoundException {
        try {
            dataAccessFacade.deleteRecord(recordNumber);
        } catch (DataSourceException e) {
            String message = createDeleteErrorMessage(recordNumber, e);
            throw new DataAccessException(message);
        }
    }

    private String createReadErrorMessage(int recordNumber, DataSourceException e) {
        return String.format(" A problem occured when reading record %d \n %s", recordNumber,
                e.getMessage());
    }

    private String createUpdateErrorMessage(int recordNumber, String[] dataArray,
            DataSourceException e) {
        String dataString = Arrays.toString(dataArray);
        String message = " A problem occured when updating record %d with \n %s \n %s";
        return String.format(message, recordNumber, dataString, e.getMessage());
    }

    private String createFindErrorMessage(String[] dataArray, DataSourceException e) {
        String dataString = Arrays.toString(dataArray);
        String message = " A problem occured when finding records that meets the search critea \n %s \n %s";
        return String.format(message, dataString, e.getMessage());
    }

    private String createCreateErrorMessage(String[] dataArray, DataSourceException e) {
        String dataString = Arrays.toString(dataArray);
        String message = " A problem occured when creating record \n %s \n %s";
        return String.format(message, dataString, e.getMessage());
    }

    private String createVerifyErrorMessage(int recordNumber, DataSourceException e) {
        return String.format(" A problem occured when verifying record %d exists \n %s",
                recordNumber, e.getMessage());
    }

    private String createDeleteErrorMessage(int recordNumber, DataSourceException e) {
        return String.format(" A problem occured when deleting record %d \n %s", recordNumber,
                e.getMessage());
    }

}
