package suncertify.db.matcher;

import static java.lang.Boolean.TRUE;
import suncertify.db.record.Record;

public abstract class RecordMatcher {

	private RecordMatcher recordMatcher;
	private String critea;

	public RecordMatcher(String critea) {
		this.critea = critea;
	}

	public RecordMatcher(String critea, RecordMatcher recordMatcher) {
		this(critea);
		this.recordMatcher = recordMatcher;
	}

	public boolean matches(Record record) {
		boolean isMatching = TRUE;

		if (recordMatcher != null) {
			isMatching = recordMatcher.matches(record);
		}

		if (isMatching && critea != null) {
			isMatching = isRecordFieldMatchingCritea(record, critea.toLowerCase());
		}
		return isMatching;
	}

	abstract boolean isRecordFieldMatchingCritea(Record record, String critea);

}
