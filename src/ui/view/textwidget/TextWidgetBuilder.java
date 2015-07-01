package ui.view.textwidget;

import static java.lang.Boolean.TRUE;

public class TextWidgetBuilder {

	private String label;
	private boolean isEditable;
	private int numberOfColumns;

	public TextWidgetBuilder() {
		isEditable = TRUE;
	}

	public TextWidgetBuilder addLabel(String label) {
		this.label = label;
		return this;
	}

	public TextWidgetBuilder addIsEditable(boolean isEditable) {
		this.isEditable = isEditable;
		return this;
	}

	public TextWidgetBuilder addNumberOfColumns(int numberOfColumns) {
		this.numberOfColumns = numberOfColumns;
		return this;
	}

	public TextWidget build() {
		return new TextWidget(label, isEditable, numberOfColumns);
	}

}
