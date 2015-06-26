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

	public Record(int recordNumber, String name, String location, String specialties, String size, String rate, String owner, boolean isDeleted) {
		this.name = name;
		this.size = size;
		this.rate = rate;
		this.owner = owner;
		this.location = location;
		this.isDeleted = isDeleted;
		this.specialties = specialties;
		this.recordNumber = recordNumber;
	}

	public String[] toArray() {
		RecordParser parser = new RecordParser();
		return parser.toArray(this);
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
		return isDeleted;
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

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
}
