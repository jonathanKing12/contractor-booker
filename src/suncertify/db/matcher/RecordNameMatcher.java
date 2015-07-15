package suncertify.db.matcher;

import suncertify.db.record.Record;

public class RecordNameMatcher extends RecordMatcher {

    public RecordNameMatcher(String critea) {
        super(critea);
    }

    @Override
    boolean isRecordFieldMatchingCritea(Record record, String critea) {
        String name = record.getName();
        return name.toLowerCase().startsWith(critea);
    }
}
