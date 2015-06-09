package suncertify.db.matcher;

import static java.lang.Boolean.TRUE;
import suncertify.db.record.Record;

public abstract class RecordMatcher {

	private RecordMatcher recordMatcher;
	private String prefix;

	public RecordMatcher(String prefix) {
		this.prefix = prefix;
	}

	public RecordMatcher(String prefix, RecordMatcher recordMatcher) {
		this(prefix);
		this.recordMatcher = recordMatcher;
	}

	public boolean matches(Record record) {
		boolean isMatching = TRUE;

		if (recordMatcher != null) {
			isMatching = recordMatcher.matches(record);
		}

		if (isMatching && prefix != null) {
			isMatching = isRecordFieldBeginningWithPrefix(record, prefix.toLowerCase());
		}
		return isMatching;
	}

	abstract boolean isRecordFieldBeginningWithPrefix(Record record, String prefix);

}
