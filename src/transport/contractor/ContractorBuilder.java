package transport.contractor;

public class ContractorBuilder {

	public ContractorBuilder() {
	}

	private String name;
	private String location;
	private String specialties;
	private String size;
	private String rate;
	private String owner;
	private String deletedFlag;
	private int contractorId;

	public ContractorBuilder addName(String name) {
		this.name = name;
		return this;
	}

	public ContractorBuilder addLocation(String location) {
		this.location = location;
		return this;
	}

	public ContractorBuilder addSpecialties(String specialties) {
		this.specialties = specialties;
		return this;
	}

	public ContractorBuilder addSize(String size) {
		this.size = size;
		return this;
	}

	public ContractorBuilder addRate(String rate) {
		this.rate = rate;
		return this;
	}

	public ContractorBuilder addOwner(String owner) {
		this.owner = owner;
		return this;
	}

	public ContractorBuilder addContractorId(int contractorId) {
		this.contractorId = contractorId;
		return this;
	}

	public ContractorBuilder addDeletedFlag(String deletedFlag) {
		this.deletedFlag = deletedFlag;
		return this;
	}

	public Contractor build() {
		return new Contractor(this);
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

	public String getDeletedFlag() {
		return deletedFlag;
	}

}
