package ui.view.common.textwidget;

import javax.swing.*;

public class TextWidget {

    private JLabel messageLabel;
    private JTextField textField;

    /**
     * Constructs a instance of TextWidget with the specified messageText,isEditable and numberOfColumns.
     * 
     * @param messageText
     *            - the text of this instance label
     * @param isEditable
     *            - {@true} if this instance textField is editable
     * @param numberOfColumns
     *            - the size of this instance textField
     */
    public TextWidget(String messageText, boolean isEditable, int numberOfColumns) {
        messageLabel = new JLabel(messageText);
        textField = new JTextField(numberOfColumns);
        textField.setEditable(isEditable);
        textField.setColumns(numberOfColumns);
    }

    /**
     * Sets the text of this instance text field to the specified text.
     * 
     * @param text
     *            - the text
     */
    public void setText(String text) {
        textField.setText(text);
    }

    /**
     * Returns the text from this instance text field.
     * 
     * @return the text from this instance text field
     */
    public String getText() {
        String text = textField.getText();
        return text.trim();
    }

    /**
     * Adds this instance label to the specified JPanel.
     * 
     * @param panel
     *            - the panel
     */
    public void addMessageLabelToPanel(JPanel panel) {
        panel.add(messageLabel);
    }

    /**
     * Adds this instance text field to the specified JPanel.
     * 
     * @param panel
     *            - the panel
     */
    public void addTextFieldToPanel(JPanel panel) {
        panel.add(textField);
    }
}
