package suncertify.db.matcher;

import suncertify.db.record.Record;

public class RecordOwnerMatcher extends RecordMatcher {

    /**
     * Constructs a RecordLocationMatcher instance with the specified searchCritea and recordMatcher
     * 
     * @param searchCritea
     *            - the searchCritea
     * @param recordMatcher
     *            - the recordMatcher
     */
    public RecordOwnerMatcher(String searchCritea, RecordMatcher recordMatcher) {
        super(searchCritea, recordMatcher);
    }

    /**
     * Returns {@code true} if the specified record owner starts with the specified searchCritea. The Match is case sensitive.
     * 
     * @param record
     *            - the record
     * @param searchCritea
     *            - the searchCritea
     * @return {@code true} if the specified record owner starts with the specified searchCritea.
     */
    @Override
    boolean isRecordFieldMatchingCritea(Record record, String searchCritea) {
        return record.getOwner().startsWith(searchCritea);
    }
}
