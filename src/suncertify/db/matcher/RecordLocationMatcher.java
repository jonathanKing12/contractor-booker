package suncertify.db.matcher;

import suncertify.db.record.Record;

public class RecordLocationMatcher extends RecordMatcher {

	public RecordLocationMatcher(String critea) {
		super(critea);
	}

	public RecordLocationMatcher(String critea, RecordMatcher recordMatcher) {
		super(critea, recordMatcher);
	}

	@Override
	boolean isRecordFieldMatchingCritea(Record record, String critea) {
		String name = record.getLocation();
		return name.toLowerCase().startsWith(critea);
	}
}
