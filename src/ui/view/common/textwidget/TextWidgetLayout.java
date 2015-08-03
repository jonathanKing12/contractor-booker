package ui.view.common.textwidget;

import static java.awt.FlowLayout.LEFT;
import static javax.swing.BoxLayout.X_AXIS;

import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.*;

public class TextWidgetLayout {

    /**
     * Layout the specified TextFields on a JPanel vertically.
     * 
     * <p>
     * On the left of the JPanel the textWidgets labels will be layout vertically, textWidget2 label layout below textWidget1 label. On the right, the
     * textWidgets text fields will be layout vertically, textWidget2 text field layout below textWidget1 text field. The labels of each textWidget
     * will in line horizontally with the corresponding text field.
     * </p>
     * 
     * @param textWidget1
     *            - the textWidget1 -
     * @param textWidget2
     *            - the textWidget2
     * @return the JPanel
     */
    public JPanel layoutVertically(TextWidget textWidget1, TextWidget textWidget2) {
        Box labelBox = createLabelBox(textWidget1, textWidget2);
        Box textFieldBox = createtextFieldBox(textWidget1, textWidget2);
        return createBoxLayoutPanel(labelBox, textFieldBox);
    }

    private Box createLabelBox(TextWidget textWidget1, TextWidget textWidget2) {
        JPanel leftBottom = createFlowLayoutPanel();
        textWidget1.addMessageLabelToPanel(leftBottom);

        JPanel leftTop = createFlowLayoutPanel();
        textWidget2.addMessageLabelToPanel(leftTop);

        return createBox(leftBottom, leftTop);
    }

    private Box createtextFieldBox(TextWidget textWidget1, TextWidget textWidget2) {
        JPanel centreTop = createFlowLayoutPanel();
        textWidget1.addTextFieldToPanel(centreTop);

        JPanel centreBottom = createFlowLayoutPanel();
        textWidget2.addTextFieldToPanel(centreBottom);

        return createBox(centreTop, centreBottom);
    }

    private Box createBox(Component component1, Component component2) {
        Box box = Box.createVerticalBox();
        box.add(component1);
        box.add(component2);
        return box;
    }

    private JPanel createFlowLayoutPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(LEFT));
        return panel;
    }

    private JPanel createBoxLayoutPanel(Box box1, Box box2) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, X_AXIS));
        panel.add(box1);
        panel.add(box2);
        return panel;
    }
}
