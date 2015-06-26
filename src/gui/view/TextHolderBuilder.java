package gui.view;

import static java.lang.Boolean.TRUE;

public class TextHolderBuilder {

	private String label;
	private boolean isEditable;
	private int numberOfColumns;

	public TextHolderBuilder() {
		isEditable = TRUE;
	}

	public TextHolderBuilder addLabel(String label) {
		this.label = label;
		return this;
	}

	public TextHolderBuilder addIsEditable(boolean isEditable) {
		this.isEditable = isEditable;
		return this;
	}

	public TextHolderBuilder addNumberOfColumns(int numberOfColumns) {
		this.numberOfColumns = numberOfColumns;
		return this;
	}

	public TextHolder build() {
		return new TextHolder(label, isEditable, numberOfColumns);
	}

}
