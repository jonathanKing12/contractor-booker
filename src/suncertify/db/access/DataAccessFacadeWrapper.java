package suncertify.db.access;

import java.util.Arrays;

import suncertify.db.DuplicateKeyException;
import suncertify.db.RecordNotFoundException;
import suncertify.db.datasource.DataSourceException;
import suncertify.db.datasource.DataSourceFactory;

public class DataAccessFacadeWrapper {

    private DataAccessFacade dataAccessFacade;

    public DataAccessFacadeWrapper(DataSourceFactory factory) {
        dataAccessFacade = new DataAccessFacade(factory);
    }

    public String[] read(int recordNumber) throws RecordNotFoundException {
        try {
            return dataAccessFacade.read(recordNumber);
        } catch (DataSourceException e) {
            String message = createReadErrorMessage(recordNumber, e);
            throw new DataAccessException(message);
        }
    }

    public void update(int recordNumber, String[] data) throws RecordNotFoundException {
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
