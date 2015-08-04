package ui.view.common.textwidget;

import static java.lang.Boolean.TRUE;

/**
 * Builds a TextWidget instance.
 */
public class TextWidgetBuilder {

    private String labelMessage;
    private boolean isEditable;
    private int numberOfColumns;

    /**
     * Constructs a instance of TextWidgetBuilder. The text field of the text widget that this instance will build, is set to editable by default.
     */
    public TextWidgetBuilder() {
        isEditable = TRUE;
    }

    /**
     * Sets the specified labelMessage as the message of the label of the text widget.
     * 
     * @param labelMessage
     *            -the labelMessage
     * @return this instance
     */
    public TextWidgetBuilder addLabel(String labelMessage) {
        this.labelMessage = labelMessage;
        return this;
    }

    /**
     * Sets the text widget's text field to be editable if the specified isEditable is {@code true}.
     * 
     * @param isEditable
     *            - {@code true} if text widget text field is to be editable
     * @return this instance
     */
    public TextWidgetBuilder addIsEditable(boolean isEditable) {
        this.isEditable = isEditable;
        return this;
    }

    /**
     * Sets the number of columns of the text widget's text field to the specified numberOfColumns.
     * 
     * @param numberOfColumns
     *            - the numberOfColumns
     * @return this instance
     */
    public TextWidgetBuilder addNumberOfColumns(int numberOfColumns) {
        this.numberOfColumns = numberOfColumns;
        return this;
    }

    /**
     * Builds a TextWidget.
     * 
     * @return the built TextWidget
     */
    public TextWidget build() {
        return new TextWidget(labelMessage, isEditable, numberOfColumns);
    }
}
