package suncertify.db.record;

public class RecordBuilder {
	private String name;
	private String location;
	private String specialties;
	private String size;
	private String rate;
	private String owner;
	private boolean deletedFlag;
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

	public RecordBuilder addDeletedFlag(boolean deletedFlag) {
		this.deletedFlag = deletedFlag;
		return this;
	}

	public Record build() {
		return new Record(this);
	}

	public String getName() {
		return name;
	}

	public String getLocation() {
		return location;
	}

	public String getSpecialties() {
		return specialties;
	}

	public String getSize() {
		return size;
	}

	public String getRate() {
		return rate;
	}

	public String getOwner() {
		return owner;
	}

	public int getRecordNumber() {
		return recordNumber;
	}

	public boolean getDeletedFlag() {
		return deletedFlag;
	}
}
