package suncertify.db.matcher;

import static java.lang.Integer.parseInt;
import suncertify.db.record.Record;

public class RecordSizeMatcher extends RecordMatcher {

    /**
     * Constructs a RecordLocationMatcher instance with the specified searchCritea and recordMatcher
     * 
     * @param searchCritea
     *            - the searchCritea
     * @param recordMatcher
     *            - the recordMatcher
     */
    public RecordSizeMatcher(String searchCritea, RecordMatcher recordMatcher) {
        super(searchCritea, recordMatcher);
    }

    /**
     * Returns {@code true} if the specified record size is equal to or less than the specified searchCritea
     * 
     * @param record
     *            - the record
     * @param searchCritea
     *            - the searchCritea
     * @return {@code true} if the specified record size is equal to or less than the specified searchCritea
     */
    @Override
    boolean isRecordFieldMatchingCritea(Record record, String searchCritea) {
        String sizeString = record.getSize();
        int size = parseInt(sizeString);
        int critea = parseInt(searchCritea);
        return size <= critea;
    }
}
