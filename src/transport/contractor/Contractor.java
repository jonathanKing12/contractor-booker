package transport.contractor;

import java.io.Serializable;

public class Contractor implements Serializable {

	private String name;
	private String location;
	private String size;
	private String specialties;
	private String rate;
	private String owner;
	private int contractorId;

	public Contractor(int contractorId, String name, String location, String specialties, String size, String rate, String owner) {
		this.name = name;
		this.size = size;
		this.rate = rate;
		this.owner = owner;
		this.location = location;
		this.specialties = specialties;
		this.contractorId = contractorId;
	}

	public String[] toArray() {
		ContracorParser parser = new ContracorParser();
		return parser.toArray(this);
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

	public int getContractorId() {
		return contractorId;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public boolean isBooked() {
		return !owner.isEmpty();
	}
}
