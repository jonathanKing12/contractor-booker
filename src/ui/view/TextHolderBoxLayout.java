package ui.view;

import static java.awt.FlowLayout.LEFT;
import static javax.swing.BoxLayout.X_AXIS;

import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class TextHolderBoxLayout {

	public JPanel layoutVertically(TextHolder textHolder1, TextHolder textHolder2) {
		Box labelBox = createLabelBox(textHolder1, textHolder2);
		Box textFieldBox = createtextFieldBox(textHolder1, textHolder2);
		return createBoxLayoutPanel(labelBox, textFieldBox);
	}

	private Box createLabelBox(TextHolder textHolder1, TextHolder textHolder2) {
		JPanel leftBottom = createFlowLayoutPanel();
		textHolder1.addMessageLabelToPanel(leftBottom);

		JPanel leftTop = createFlowLayoutPanel();
		textHolder2.addMessageLabelToPanel(leftTop);

		return createBox(leftBottom, leftTop);
	}

	private Box createtextFieldBox(TextHolder textHolder1, TextHolder textHolder2) {
		JPanel centreTop = createFlowLayoutPanel();
		textHolder1.addTextFieldToPanel(centreTop);

		JPanel centreBottom = createFlowLayoutPanel();
		textHolder2.addTextFieldToPanel(centreBottom);

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
