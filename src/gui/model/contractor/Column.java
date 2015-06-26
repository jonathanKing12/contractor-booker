package gui.model.contractor;

public enum Column {
	NAME("Subcontractor name"), LOCATION("City"), SPECIALTIES("Types of work performed"), SIZE("Numbers of staff"), RATE("Hourly rate"), OWNER(
			"Customer's ID", Integer.class), SELECTED("Selected for booking", Boolean.class);

	private String name;
	private Class<?> classType;

	Column(final String name) {
		this.name = name;
		this.classType = String.class;
	}

	Column(final String name, final Class<?> classType) {
		this.name = name;
		this.classType = classType;
	}

	public String getName() {
		return name;
	}

	public Class<?> getClassType() {
		return classType;
	}
}
