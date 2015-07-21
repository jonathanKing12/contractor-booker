package suncertify.db.access;

import static constants.Constants.*;

import java.util.ArrayList;
import java.util.List;

import suncertify.db.DuplicateKeyException;
import suncertify.db.RecordNotFoundException;
import suncertify.db.datasource.DataSourceException;
import suncertify.db.datasource.DataSourceFactory;
import suncertify.db.matcher.*;
import suncertify.db.record.*;

public class DataAccessReader {

    private RecordReader recordReader;

    DataAccessReader(DataSourceFactory factory) {
        this.recordReader = new RecordReader(factory);
    }

    String[] read(int recordNumber) throws RecordNotFoundException, DataSourceException {
        recordReader.open();
        try {
            if (!recordReader.hasRecord(recordNumber)) {
                // String message =buidRecordNotExistException
                throw new RecordNotFoundException("recrod " + recordNumber + " does not exist");
            }
            Record record = recordReader.readRecord(recordNumber);

            if (record.isDeleted()) {
                throw new RecordNotFoundException("recrod " + recordNumber + " is deleted");
            }

            return record.toArray();
        } finally {
            recordReader.close();
        }
    }

    int[] find(String[] data) throws DataSourceException {
        RecordMatcher matcher = createRecordMatcher(data);
        List<Integer> recordNumbers = getRecordNumbers(matcher);
        return toArray(recordNumbers);
    }

    int getNextAvailableRecordNumber() throws DataSourceException {
        recordReader.open();
        int recordNumber = 0;

        try {
            while (recordReader.hasRecord(recordNumber)) {
                Record record = recordReader.readRecord(recordNumber);
                if (record.isDeleted()) {
                    break;
                }
                recordNumber++;
            }
        } finally {
            recordReader.close();
        }

        return recordNumber;
    }

    void verifyRecordExist(int recordNumber) throws RecordNotFoundException, DataSourceException {
        recordReader.open();

        try {
            if (!recordReader.hasRecord(recordNumber)) {
                return;
            }

            Record record = recordReader.readRecord(recordNumber);
            if (record.isDeleted()) {
                throw new RecordNotFoundException("recrod " + recordNumber + " is deleted");
            }

        } finally {
            recordReader.close();
        }
    }

    void verifyKeyisUnique(String[] data) throws DuplicateKeyException, DataSourceException {
        recordReader.open();
        int recordNumber = 0;
        Record nextRecord = new RecordParser().createRecord(data);

        try {
            while (recordReader.hasRecord(recordNumber)) {

                Record record = recordReader.readRecord(recordNumber);
                if (record.isHasSameKey(nextRecord)) {
                    throw new DuplicateKeyException("recrod " + recordNumber + " is deleted");
                }
                recordNumber++;
            }

        } finally {
            recordReader.close();
        }
    }

    private RecordMatcher createRecordMatcher(String[] data) {
        String name = data[NAME_INDEX];
        String location = data[LOCATION_INDEX];
        String specialties = data[SPECIALTIES_INDEX];
        String size = data[SIZE_INDEX];
        String rate = data[RATE_INDEX];
        String owner = data[OWNER_INDEX];

        RecordMatcher matcher = new RecordNameMatcher(name);
        matcher = new RecordLocationMatcher(location, matcher);
        matcher = new RecordSpecialtiesMatcher(specialties, matcher);
        matcher = new RecordSizeMatcher(size, matcher);
        matcher = new RecordOwnerMatcher(owner, matcher);
        return new RecordRateMatcher(rate, matcher);
    }

    private List<Integer> getRecordNumbers(RecordMatcher matcher) throws DataSourceException {
        recordReader.open();
        List<Integer> recordNumbers = new ArrayList<>();
        int recordNumber = 0;

        while (recordReader.hasRecord(recordNumber)) {
            Record record = recordReader.readRecord(recordNumber);
            if (isRecordMatchingMatcherAndIsNotDeleted(record, matcher)) {
                recordNumbers.add(record.getRecordNumber());
            }
            recordNumber++;
        }

        recordReader.close();
        return recordNumbers;
    }

    private boolean isRecordMatchingMatcherAndIsNotDeleted(Record record, RecordMatcher matcher) {
        return matcher.matches(record) && !record.isDeleted();
    }

    private int[] toArray(List<Integer> recordNumbersList) {
        int[] recordNumbersArray = new int[recordNumbersList.size()];

        for (int i = 0; i < recordNumbersList.size(); i++) {
            int recordNumber = recordNumbersList.get(i);
            recordNumbersArray[i] = recordNumber;
        }
        return recordNumbersArray;
    }
}
