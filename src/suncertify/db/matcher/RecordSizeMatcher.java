package suncertify.db.matcher;

import static java.lang.Boolean.FALSE;
import static java.lang.Integer.parseInt;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import suncertify.db.record.Record;

public class RecordSizeMatcher extends RecordMatcher {

	private static final String NATURAL_NUMBER_PATTERN = "\\d+";
	private Pattern natualNumberPattern;

	public RecordSizeMatcher(String maxSize) {
		super(maxSize);
		natualNumberPattern = Pattern.compile(NATURAL_NUMBER_PATTERN);
	}

	public RecordSizeMatcher(String maxSize, RecordMatcher recordMatcher) {
		super(maxSize, recordMatcher);
		natualNumberPattern = Pattern.compile(NATURAL_NUMBER_PATTERN);
	}

	@Override
	boolean isRecordFieldMatchingCritea(Record record, String critea) {
		if (!isNatualNumber(critea)) {
			return FALSE;
		}

		String size = record.getSize();
		return isSizeSmallerOrEqualToCritea(size, critea);
	}

	private boolean isNatualNumber(String critea) {
		Matcher mathcher = natualNumberPattern.matcher(critea);
		return mathcher.matches();
	}

	private boolean isSizeSmallerOrEqualToCritea(String sizeString, String criteaString) {
		int size = parseInt(sizeString);
		int critea = parseInt(criteaString);
		return size <= critea;
	}
}
