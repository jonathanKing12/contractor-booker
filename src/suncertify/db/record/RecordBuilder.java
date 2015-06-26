package suncertify.db.record;

public class RecordBuilder {
	private String name;
	private String location;
	private String specialties;
	private String size;
	private String rate;
	private String owner;
	private boolean isDeleted;
	private int recordNumber;

	public RecordBuilder addName(String name) {
		this.name = name;
		return this;
	}

	public RecordBuilder addLocation(String location) {
		this.location = location;
		return this;
	}

	public RecordBuilder addSpecialties(String specialties) {
		this.specialties = specialties;
		return this;
	}

	public RecordBuilder addSize(String size) {
		this.size = size;
		return this;
	}

	public RecordBuilder addRate(String rate) {
		this.rate = rate;
		return this;
	}

	public RecordBuilder addOwner(String owner) {
		this.owner = owner;
		return this;
	}

	public RecordBuilder addRecordNumber(int recordNumber) {
		this.recordNumber = recordNumber;
		return this;
	}

	public RecordBuilder addDeletedFlag(boolean isDeleted) {
		this.isDeleted = isDeleted;
		return this;
	}

	public Record build() {
		return new Record(recordNumber, name, location, specialties, size, rate, owner, isDeleted);
	}
}
