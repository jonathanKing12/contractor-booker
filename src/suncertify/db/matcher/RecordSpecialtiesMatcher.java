package suncertify.db.matcher;

import static constants.Constants.SPECIALTIES_DILIMETER;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import suncertify.db.record.Record;

public class RecordSpecialtiesMatcher extends RecordMatcher {

	public RecordSpecialtiesMatcher(String specialtiesPrefix) {
		super(specialtiesPrefix);
	}

	public RecordSpecialtiesMatcher(String sizePrefix, RecordMatcher recordMatcher) {
		super(sizePrefix, recordMatcher);
	}

	@Override
	boolean isRecordFieldBeginningWithPrefix(Record record, String specialtyPrefix) {
		String[] specialties = getSpecialites(record);

		boolean found = FALSE;
		for (String specialty : specialties) {
			if (specialty.toLowerCase().startsWith(specialtyPrefix.toLowerCase())) {
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
