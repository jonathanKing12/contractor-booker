package suncertify.db.matcher;

import suncertify.db.record.Record;

public class RecordLocationMatcher extends RecordMatcher {

	public RecordLocationMatcher(String locationPrefix) {
		super(locationPrefix);
	}

	public RecordLocationMatcher(String locationPrefix, RecordMatcher recordMatcher) {
		super(locationPrefix, recordMatcher);
	}

	@Override
	boolean isRecordFieldBeginningWithPrefix(Record record, String namePrefix) {
		String name = record.getLocation();
		return name.toLowerCase().startsWith(namePrefix);
	}
}
