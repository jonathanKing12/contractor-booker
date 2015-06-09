package suncertify.db.matcher;

import suncertify.db.record.Record;

public class RecordNameMatcher extends RecordMatcher {

	public RecordNameMatcher(String namePrefix) {
		super(namePrefix);
	}

	public RecordNameMatcher(String namePrefix, RecordMatcher recordMatcher) {
		super(namePrefix, recordMatcher);
	}

	@Override
	boolean isRecordFieldBeginningWithPrefix(Record record, String namePrefix) {
		String name = record.getName();
		return name.toLowerCase().startsWith(namePrefix);
	}
}
