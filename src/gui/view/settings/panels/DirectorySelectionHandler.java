package gui.view.settings.panels;

import static gui.view.mergers.ViewMerger.VIEW_MERGER_INSTACE;
import static javax.swing.JFileChooser.APPROVE_OPTION;
import static javax.swing.JFileChooser.DIRECTORIES_ONLY;
import gui.view.TextHolder;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;

public class DirectorySelectionHandler implements ActionListener {

	private JButton browseButton;
	private JFileChooser fileChooser;
	private TextHolder selectedDirectoryTextHolder;

	public DirectorySelectionHandler(TextHolder textHolder) {
		this.selectedDirectoryTextHolder = textHolder;
		fileChooser = getFileChooser();
		setUpBrowseButton();
	}

	private void setDirectory(String directory) {
		selectedDirectoryTextHolder.setText(directory);
	}

	public JButton getBrowseButton() {
		return browseButton;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Component parent = VIEW_MERGER_INSTACE.getParent();
		int response = fileChooser.showDialog(parent, "choose database file");

		if (isApproved(response)) {
			String directory = getSelectedDirectory();
			setDirectory(directory);
		}
	}

	private String getSelectedDirectory() {
		File selectedFile = fileChooser.getSelectedFile();
		return selectedFile.getAbsolutePath();
	}

	private JFileChooser getFileChooser() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(DIRECTORIES_ONLY);
		return fileChooser;
	}

	private void setUpBrowseButton() {
		browseButton = new JButton("browse");
		browseButton.addActionListener(this);
	}

	private boolean isApproved(int response) {
		return response == APPROVE_OPTION;
	}
}
