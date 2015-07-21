package suncertify.db.matcher;

import static constants.Constants.SPECIALTIES_DILIMETER;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import suncertify.db.record.Record;

public class RecordSpecialtiesMatcher extends RecordMatcher {

    /**
     * Constructs a RecordLocationMatcher instance with the specified searchCritea and recordMatcher
     * 
     * @param searchCritea
     *            - the searchCritea
     * @param recordMatcher
     *            - the recordMatcher
     */
    public RecordSpecialtiesMatcher(String critea, RecordMatcher recordMatcher) {
        super(critea, recordMatcher);
    }

    /**
     * Returns {@code true} if any one of the specified record specialties starts with the specified searchCritea. The Match is not case sensitive.
     * 
     * @param record
     *            - the record
     * @param searchCritea
     *            - the searchCritea
     * @return {@code true} if any one of the specified record specialties starts with the specified searchCritea.
     */
    @Override
    boolean isRecordFieldMatchingCritea(Record record, String searchCritea) {
        String[] specialties = getSpecialites(record);

        for (String specialty : specialties) {
            if (specialtyStartsWithSearchCrtiea(specialty, searchCritea)) {
                return TRUE;
            }
        }
        return FALSE;
    }

    private String[] getSpecialites(Record record) {
        return record.getSpecialties().split(SPECIALTIES_DILIMETER);
    }

    private boolean specialtyStartsWithSearchCrtiea(String specialty, String critea) {
        return specialty.toLowerCase().startsWith(critea.toLowerCase());
    }
}
