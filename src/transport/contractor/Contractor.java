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

	public Contractor(ContractorBuilder builder) {
		name = builder.getName();
		location = builder.getLocation();
		specialties = builder.getSpecialties();
		size = builder.getSize();
		rate = builder.getRate();
		owner = builder.getOwner();
		contractorId = builder.getContractorId();
	}

	public String[] toArray() {
		return ContracorUtills.toArray(this);
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
