package ui.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import ui.view.mergers.BookableMerger;

public class FilterPanel extends JPanel implements ActionListener {

	private JButton search;
	private TextHolder name;
	private TextHolder location;

	public FilterPanel() {
		setUpTextHolders();
		setUpSearchButton();
		addComponents();
		this.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					System.out.println("enter key released in the filter panel");
				} else
					System.out.println("a key released in the filter panel");
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					System.out.println("enter key selected in the filter panel");
				} else
					System.out.println("a key selected in the filter panel");

			}
		});
		this.setBackground(Color.cyan);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		BookableMerger merger = BookableMerger.getInstance();
		merger.search(name.getText(), location.getText());
	}

	private void setUpTextHolders() {
		name = createTextHolder("Name", 10);
		location = createTextHolder("Location", 20);
	}

	private TextHolder createTextHolder(String label, int numberOfColumns) {
		TextHolderBuilder builder = new TextHolderBuilder();
		return builder.addLabel(label).addNumberOfColumns(numberOfColumns).build();
	}

	private void setUpSearchButton() {
		search = new JButton("Search");
		search.addActionListener(this);
	}

	private void addComponents() {
		addTextHolder(name);
		addTextHolder(location);
		this.add(search);
	}

	private void addTextHolder(TextHolder textHolder) {
		textHolder.addMessageLabelToPanel(this);
		textHolder.addTextFieldToPanel(this);
	}
}
