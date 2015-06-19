package suncertify.db.record;

public class Record {

	private String name;
	private String location;
	private String specialties;
	private String size;
	private String rate;
	private String owner;
	private int recordNumber;
	private boolean isDeleted;

	public Record(RecordBuilder builder) {
		name = builder.getName();
		location = builder.getLocation();
		specialties = builder.getSpecialties();
		size = builder.getSize();
		rate = builder.getRate();
		owner = builder.getOwner();
		recordNumber = builder.getRecordNumber();
		isDeleted = builder.getDeletedFlag();
	}

	@Override
	public String toString() {
		return "name " + name + " location " + location + " specialities " + specialties + "rate " + rate + " size " + size;
	}

	public String[] toArray() {
		return RecordUtils.toArray(this);
	}

	public boolean isDeleted() {
		return isDeleted;
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

	public boolean getDeletedFlag() {
		return isDeleted;// deletedFlag;
	}

	public String getSpecialties() {
		return specialties;
	}

	public String getSize() {
		return size;
	}

	public String getHourlyRate() {
		return rate;
	}

	public String getOwner() {
		return owner;
	}

	public void setRecordNumber(int recordNumber) {
		this.recordNumber = recordNumber;
	}

	public void setDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
}
