package transport.contractor;

/**
 * Builds a Contractor instance.
 */
public class ContractorBuilder {

    private String name;
    private String location;
    private String specialties;
    private String size;
    private String rate;
    private String owner;
    private int contractorId;

    /**
     * Sets the specified name is to be used as the Contractor's name
     * 
     * @param name
     *            - the name
     * @return the ContractorBuilder instance
     */
    public ContractorBuilder addName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Sets the specified location is to be used as the Contractor's location
     * 
     * @param location
     *            - the location
     * @return the ContractorBuilder instance
     */
    public ContractorBuilder addLocation(String location) {
        this.location = location;
        return this;
    }

    /**
     * Sets the specified specialties is to be used as the Contractor's specialties
     * 
     * @param specialties
     *            - the specialties
     * @return the ContractorBuilder instance
     */
    public ContractorBuilder addSpecialties(String specialties) {
        this.specialties = specialties;
        return this;
    }

    /**
     * Sets the specified size is to be used as the Contractor's size
     * 
     * @param size
     *            - the size
     * @return the ContractorBuilder instance
     */
    public ContractorBuilder addSize(String size) {
        this.size = size;
        return this;
    }

    /**
     * Sets the specified rate is to be used as the Contractor's rate
     * 
     * @param rate
     *            - the rate
     * @return the ContractorBuilder instance
     */
    public ContractorBuilder addRate(String rate) {
        this.rate = rate;
        return this;
    }

    /**
     * Sets the specified owner is to be used as the Contractor's owner
     * 
     * @param owner
     *            - the owner
     * @return the ContractorBuilder instance
     */
    public ContractorBuilder addOwner(String owner) {
        this.owner = owner;
        return this;
    }

    /**
     * Sets the specified contractorId is to be used as the Contractor's contractorId
     * 
     * @param contractorId
     *            - the contractorId
     * @return the ContractorBuilder instance
     */
    public ContractorBuilder addContractorId(int contractorId) {
        this.contractorId = contractorId;
        return this;
    }

    /**
     * Builds a new Contractor instance with all the specified contractorId, name, location, specialties, size, rate, owner; that were specified in
     * the add methods
     * 
     * @return the Contractor
     */
    public Contractor build() {
        return new Contractor(contractorId, name, location, specialties, size, rate, owner);
    }
}
