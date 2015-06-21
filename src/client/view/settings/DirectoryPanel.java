package client.view.settings;

import static java.lang.Boolean.FALSE;
import static javax.swing.JFileChooser.APPROVE_OPTION;
import static javax.swing.JFileChooser.DIRECTORIES_ONLY;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

import client.view.TextHolder;

public class DirectoryPanel extends JPanel implements ActionListener {

	private JButton browse;
	private JFileChooser fileChooser;
	private TextHolder textHolder;
	private String directory;

	DirectoryPanel() {
		textHolder = new TextHolder("select location", FALSE);
		fileChooser = getFileChooser();

		setUpBrowseButton();
		this.setLayout(new FlowLayout());
		addComponents();

	}

	public void setDirectory(String directory) {
		this.directory = directory;
		textHolder.setText(directory);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		int response = fileChooser.showDialog(this, "choose database file");

		if (isApproved(response)) {
			String directory = getdirectory();
			setDirectory(directory);
		}
	}

	public String getDirectory() {
		return directory;
	}

	private JFileChooser getFileChooser() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(DIRECTORIES_ONLY);
		return fileChooser;
	}

	private void setUpBrowseButton() {
		browse = new JButton("browse");
		browse.addActionListener(this);
	}

	private void addComponents() {
		textHolder.addComponents(this);
		this.add(browse);
	}

	private boolean isApproved(int response) {
		return response == APPROVE_OPTION;
	}

	private String getdirectory() {
		return fileChooser.getSelectedFile().getAbsolutePath();
	}
}
