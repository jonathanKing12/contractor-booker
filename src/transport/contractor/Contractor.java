package transport.contractor;

import java.io.Serializable;

/**
 * Represents a Contractor.
 *
 */
public class Contractor implements Serializable {

    private String name;
    private String location;
    private String size;
    private String specialties;
    private String rate;
    private String owner;
    private int contractorId;

    /**
     * creates a new contractor instance with the provided arguments
     * 
     * @param contractorId
     *            - - the contractor's ID
     * @param name
     *            - the contractor's name
     * @param location
     *            - the contractor's location
     * @param specialties
     *            - the contractor's specialties
     * @param size
     *            - the contractor's size
     * @param rate
     *            - the contractor's rate
     * @param owner
     *            - the owner who has booked the contractor
     */
    public Contractor(int contractorId, String name, String location, String specialties,
            String size, String rate, String owner) {
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

    /**
     * Returns the contractor's name
     * 
     * @return contractor name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the contractor's location
     * 
     * @return contractor location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Returns the contractor's specialties
     * 
     * @return contractor specialties
     */
    public String getSpecialties() {
        return specialties;
    }

    /**
     * Returns the contractor's size
     * 
     * @return contractor size
     */
    public String getSize() {
        return size;
    }

    /**
     * Returns the contractor's rate
     * 
     * @return contractor rate
     */
    public String getRate() {
        return rate;
    }

    /**
     * Returns the contractor's owner
     * 
     * @return contractor owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Returns the contractor's id
     * 
     * @return contractor id
     */
    public int getContractorId() {
        return contractorId;
    }

    /**
     * sets the owner of the contractor
     * 
     * @param owner
     *            the owner
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * checks if the contractor is booked
     * 
     * @return {@code true} if the contractor is booked
     */
    public boolean isBooked() {
        return !owner.isEmpty();
    }
}
