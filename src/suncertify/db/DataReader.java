package suncertify.db;

import static constants.Constants.SIZE_INDEX;
import static java.lang.Boolean.FALSE;

import java.util.ArrayList;
import java.util.List;

import suncertify.db.datasource.DataSourceException;
import suncertify.db.datasource.DataSourceFactory;
import suncertify.db.matcher.RecordLocationMatcher;
import suncertify.db.matcher.RecordMatcher;
import suncertify.db.matcher.RecordNameMatcher;
import suncertify.db.matcher.RecordOwnerMatcher;
import suncertify.db.matcher.RecordRateMatcher;
import suncertify.db.matcher.RecordSizeMatcher;
import suncertify.db.matcher.RecordSpecialtiesMatcher;
import suncertify.db.record.Record;
import suncertify.db.record.RecordNotFoundException;
import suncertify.db.record.RecordReader;

public class DataReader {

	private RecordReader recordReader;

	DataReader(DataSourceFactory factory) {
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
		while (recordReader.hasRecord(recordNumber)) {
			Record record = recordReader.readRecord(recordNumber);
			if (record.isDeleted()) {
				break;
			}
			recordNumber++;
		}
		recordReader.close();
		return recordNumber;
	}

	void verifyRecordExist(int recordNumber) throws RecordNotFoundException, DataSourceException {
		boolean recordExist = FALSE;
		recordReader.open();

		try {
			if (recordReader.hasRecord(recordNumber)) {
				Record record = recordReader.readRecord(recordNumber);
				recordExist = !record.isDeleted();
			}
			if (!recordExist) {
				throw new RecordNotFoundException("recrod " + recordNumber + " is deleted");
			}
		} finally {
			recordReader.close();
		}
	}

	private RecordMatcher createRecordMatcher(String[] data) {
		String size = data[SIZE_INDEX];

		RecordMatcher matcher = new RecordNameMatcher(data);
		matcher = new RecordLocationMatcher(data, matcher);
		matcher = new RecordSpecialtiesMatcher(data, matcher);
		matcher = new RecordSizeMatcher(size, matcher);
		matcher = new RecordOwnerMatcher(data, matcher);
		return new RecordRateMatcher(data, matcher);
	}

	private List<Integer> getRecordNumbers(RecordMatcher matcher) throws DataSourceException {
		recordReader.open();
		List<Integer> recordNumbers = new ArrayList<>();
		int recordNumber = 0;

		while (recordReader.hasRecord(recordNumber)) {
			Record record = recordReader.readRecord(recordNumber);
			if (isRecordMatchingMatcherAndNotDeleted(matcher, record)) {
				recordNumbers.add(record.getRecordNumber());
			}
			recordNumber++;
		}

		recordReader.close();
		return recordNumbers;
	}

	private boolean isRecordMatchingMatcherAndNotDeleted(RecordMatcher matcher, Record record) {
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
