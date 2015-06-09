package suncertify.db.matcher;

import static java.lang.Boolean.FALSE;
import static suncertify.db.matcher.RecordMatcherUtils.isNumberSmallerOrEqualToMaxNumber;
import static suncertify.db.matcher.RecordMatcherUtils.isNumeric;
import suncertify.db.record.Record;

public class RecordSizeMatcher extends RecordMatcher {

	public RecordSizeMatcher(String maxSize) {
		super(maxSize);
	}

	public RecordSizeMatcher(String maxSize, RecordMatcher recordMatcher) {
		super(maxSize, recordMatcher);
	}

	@Override
	boolean isRecordFieldBeginningWithPrefix(Record record, String maxSize) {
		if (!isNumeric(maxSize)) {
			return FALSE;
		}

		String size = record.getSize();
		return isNumberSmallerOrEqualToMaxNumber(size, maxSize);
	}
}
