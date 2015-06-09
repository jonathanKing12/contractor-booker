package suncertify.db.matcher;

import static constants.Constants.INDEX_OF_FIRST_DIGIT;
import static java.lang.Boolean.FALSE;
import static suncertify.db.matcher.RecordMatcherUtils.isNumberSmallerOrEqualToMaxNumber;
import static suncertify.db.matcher.RecordMatcherUtils.isNumeric;
import suncertify.db.record.Record;

public class RecordRateMatcher extends RecordMatcher {

	public RecordRateMatcher(String ratePrefix) {
		super(ratePrefix);
	}

	public RecordRateMatcher(String ratePrefix, RecordMatcher recordMatcher) {
		super(ratePrefix, recordMatcher);
	}

	@Override
	boolean isRecordFieldBeginningWithPrefix(Record record, String maxRate) {
		if (!isNumeric(maxRate)) {
			return FALSE;
		}

		String rate = record.getRate().substring(INDEX_OF_FIRST_DIGIT);
		return isNumberSmallerOrEqualToMaxNumber(rate, maxRate);
	}

}
