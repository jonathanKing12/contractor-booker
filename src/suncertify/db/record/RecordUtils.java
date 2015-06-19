package suncertify.db.record;

import static constants.Constants.INDEX_COUNT;
import static constants.Constants.LOCATION_INDEX;
import static constants.Constants.NAME_INDEX;
import static constants.Constants.OWNER_INDEX;
import static constants.Constants.RATE_INDEX;
import static constants.Constants.SIZE_INDEX;
import static constants.Constants.SPECIALTIES_INDEX;

public class RecordUtils {
	public static <T> String[] toArray(Record record) {
		String[] array = new String[INDEX_COUNT];
		array[NAME_INDEX] = record.getName();
		array[LOCATION_INDEX] = record.getLocation();
		array[SPECIALTIES_INDEX] = record.getSpecialties();
		array[SIZE_INDEX] = record.getSize();
		array[RATE_INDEX] = record.getHourlyRate();
		array[OWNER_INDEX] = record.getOwner();
		return array;
	}

	public static Record createRecord(int recordNo, String[] recordDetails) {
		RecordBuilder builder = getRecordBuilder(recordDetails);
		return builder.addRecordNumber(recordNo).build();
	}

	public static Record toRecord(String[] recordDetails) {
		RecordBuilder builder = getRecordBuilder(recordDetails);
		return builder.build();
	}

	public static RecordBuilder getRecordBuilder(String[] recordDetails) {
		String name = recordDetails[NAME_INDEX];
		String location = recordDetails[LOCATION_INDEX];
		String specialties = recordDetails[SPECIALTIES_INDEX];
		String size = recordDetails[SIZE_INDEX];
		String rate = recordDetails[RATE_INDEX];
		String owner = recordDetails[OWNER_INDEX];

		RecordBuilder builder = new RecordBuilder();
		return builder.addName(name).addLocation(location).addSpecialties(specialties).addSize(size).addRate(rate).addOwner(owner);
	}
}
