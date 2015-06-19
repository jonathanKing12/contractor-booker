package suncertify.db.matcher;

import static constants.Constants.SPECIALTIES_DILIMETER;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import suncertify.db.record.Record;

public class RecordSpecialtiesMatcher extends RecordMatcher {

	public RecordSpecialtiesMatcher(String critia) {
		super(critia);
	}

	public RecordSpecialtiesMatcher(String critea, RecordMatcher recordMatcher) {
		super(critea, recordMatcher);
	}

	@Override
	boolean isRecordFieldMatchingCritea(Record record, String critea) {
		String[] specialties = getSpecialites(record);

		boolean found = FALSE;
		for (String specialty : specialties) {
			if (specialty.toLowerCase().startsWith(critea.toLowerCase())) {
				found = TRUE;
				break;
			}
		}
		return found;
	}

	private String[] getSpecialites(Record record) {
		return record.getSpecialties().split(SPECIALTIES_DILIMETER);
	}
}
