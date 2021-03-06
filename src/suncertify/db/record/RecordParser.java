package suncertify.db.record;

import static constants.Constants.*;

public class RecordParser {

    public String[] toArray(Record record) {
        String[] array = new String[CONTRACTOR_DETAILS_COUNT];
        array[NAME_INDEX] = record.getName();
        array[SIZE_INDEX] = record.getSize();
        array[OWNER_INDEX] = record.getOwner();
        array[RATE_INDEX] = record.getHourlyRate();
        array[LOCATION_INDEX] = record.getLocation();
        array[SPECIALTIES_INDEX] = record.getSpecialties();
        return array;
    }

    public Record createRecord(int recordNumber, String[] recordDetails) {
        return createRecordBuilder(recordDetails).addRecordNumber(recordNumber).build();
    }

    public Record createRecord(String[] recordDetails) {
        return createRecordBuilder(recordDetails).build();
    }

    private RecordBuilder createRecordBuilder(String[] recordDetails) {
        String name = recordDetails[NAME_INDEX];
        String location = recordDetails[LOCATION_INDEX];
        String specialties = recordDetails[SPECIALTIES_INDEX];
        String size = recordDetails[SIZE_INDEX];
        String rate = recordDetails[RATE_INDEX];
        String owner = recordDetails[OWNER_INDEX];

        RecordBuilder builder = new RecordBuilder();
        builder = builder.addName(name).addLocation(location).addSpecialties(specialties)
                .addSize(size).addRate(rate).addOwner(owner);
        return builder;
    }
}
