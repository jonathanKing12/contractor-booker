package ui.model.contractor;

/**
 * Represents a column in the contractor table.
 */
public enum Column {
    NAME("Subcontractor name"), LOCATION("City"), SPECIALTIES("Types of work performed"), SIZE(
            "Numbers of staff"), RATE("Hourly rate"), OWNER("Customer's ID", Integer.class), SELECTED(
            "Selected for booking", Boolean.class);

    private String name;
    private Class<?> classType;

    /**
     * Constructs a instance of Column with the specified name. The instance ClassType is set to String.
     * 
     * @param name
     *            - the name
     */
    Column(final String name) {
        this.name = name;
        this.classType = String.class;
    }

    /**
     * Constructs a instance of Column with the specified name and classType.
     * 
     * @param name
     *            - the name
     * @param classType
     *            - the classType
     */
    Column(final String name, final Class<?> classType) {
        this.name = name;
        this.classType = classType;
    }

    /**
     * Returns the column name.
     * 
     * @return the name.
     */
    String getName() {
        return name;
    }

    /**
     * Returns the column class type.
     * 
     * @return the class type.
     */
    Class<?> getClassType() {
        return classType;
    }
}
