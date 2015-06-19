package suncertify.db.matcher;

import suncertify.db.record.Record;

public class RecordOwnerMatcher extends RecordMatcher {

	public RecordOwnerMatcher(String critea) {
		super(critea);
	}

	public RecordOwnerMatcher(String critea, RecordMatcher recordMatcher) {
		super(critea, recordMatcher);
	}

	@Override
	boolean isRecordFieldMatchingCritea(Record record, String critea) {
		return record.getOwner().startsWith(critea);
	}
}
