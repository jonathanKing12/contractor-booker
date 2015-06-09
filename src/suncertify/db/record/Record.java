package suncertify.db.record;

import static java.lang.Boolean.FALSE;

public class Record {

	private String name;
	private String location;
	private String specialties;
	private String size;
	private String rate;
	private String owner;
	private int recordNumber;
	private String deletedFlag = "  ";

	public Record(RecordBuilder builder) {
		name = builder.getName();
		location = builder.getLocation();
		specialties = builder.getSpecialties();
		size = builder.getSize();
		rate = builder.getRate();
		owner = builder.getOwner();
		recordNumber = builder.getRecordNumber();
		deletedFlag = builder.getDeletedFlag();
	}

	@Override
	public String toString() {
		return "name " + name + " location " + location + " specialities " + specialties + "rate " + rate + " size " + size;
	}

	public String[] toArray() {
		return RecordUtils.toArray(this);
	}

	public boolean isDeleted() {
		return FALSE;
	}

	public String getName() {
		return name;
	}

	public int getRecordNumber() {
		return recordNumber;
	}

	public String getLocation() {
		return location;
	}

	public String getDeletedFlag() {
		return "  ";// deletedFlag;
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

	public void setRecordNumber(int recordNumber) {
		this.recordNumber = recordNumber;
	}
}
